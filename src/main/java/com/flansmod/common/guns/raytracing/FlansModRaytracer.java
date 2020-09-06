package com.flansmod.common.guns.raytracing;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.EntityAAGun;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.teams.Team;
import com.flansmod.common.vector.Vector3f;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class FlansModRaytracer {

  public static List<BulletHit> Raytrace(World world, Entity playerToIgnore, boolean canHitSelf,
      Entity entityToIgnore, Vector3f origin, Vector3f motion, int pingOfShooter) {
    //Create a list for all bullet hits
    ArrayList<BulletHit> hits = new ArrayList<BulletHit>();

    float speed = motion.length();

    //Iterate over all entities
    for (int i = 0; i < world.loadedEntityList.size(); i++) {
      Object obj = world.loadedEntityList.get(i);
      boolean shouldDoNormalHitDetect = true;
      //Get driveables
      if (obj instanceof EntityDriveable) {
        EntityDriveable driveable = (EntityDriveable) obj;
        shouldDoNormalHitDetect = false;

        if (driveable.isDead() || driveable.isPartOfThis(playerToIgnore)) {
          continue;
        }

        //If this bullet is within the driveable's detection range
        if (driveable.getDistanceSq(origin.x, origin.y, origin.z)
            <= (driveable.getDriveableType().bulletDetectionRadius + speed) * (
            driveable.getDriveableType().bulletDetectionRadius + speed)) {
          //Raytrace the bullet
          ArrayList<BulletHit> driveableHits = driveable.attackFromBullet(origin, motion);
          hits.addAll(driveableHits);
        }
      }
      //Get players
      else if (obj instanceof EntityPlayer) {
        EntityPlayer player = (EntityPlayer) obj;
        PlayerData data = PlayerHandler.getPlayerData(player);
        shouldDoNormalHitDetect = false;
        if (data != null) {
          if (player.isDead || data.team == Team.spectators) {
            continue;
          }
          if (player == playerToIgnore && !canHitSelf) {
            continue;
          }
          int snapshotToTry = pingOfShooter / 50;
          if (snapshotToTry >= data.snapshots.length) {
            snapshotToTry = data.snapshots.length - 1;
          }

          PlayerSnapshot snapshot = data.snapshots[snapshotToTry];
          if (snapshot == null) {
            snapshot = data.snapshots[0];
          }

          //DEBUG
          //snapshot = new PlayerSnapshot(player);

          //Check one last time for a null snapshot. If this is the case, fall back to normal hit detection
          if (snapshot == null) {
            shouldDoNormalHitDetect = true;
          } else {
            //Raytrace
            ArrayList<BulletHit> playerHits = snapshot.raytrace(origin, motion);
            hits.addAll(playerHits);
          }
        }
      }

      if (shouldDoNormalHitDetect) {
        Entity entity = (Entity) obj;
        if (entity != entityToIgnore && entity != playerToIgnore
            && !entity.isDead
            && (entity instanceof EntityLivingBase || entity instanceof EntityAAGun
            || entity instanceof EntityGrenade && ((EntityGrenade) entity).type != null
            && ((EntityGrenade) entity).type.detonateWhenShot) //TODO added: shoot trough ammobags
            && entity.getEntityBoundingBox() != null) {
          MovingObjectPosition mop = entity.getEntityBoundingBox()
              .calculateIntercept(origin.toVec3(),
                  new Vec3(origin.x + motion.x, origin.y + motion.y, origin.z + motion.z));
          if (mop != null) {
            Vector3f hitPoint = new Vector3f(mop.hitVec.xCoord - origin.x,
                mop.hitVec.yCoord - origin.y, mop.hitVec.zCoord - origin.z);
            float hitLambda = 1F;
            if (motion.x != 0F) {
              hitLambda = hitPoint.x / motion.x;
            } else if (motion.y != 0F) {
              hitLambda = hitPoint.y / motion.y;
            } else if (motion.z != 0F) {
              hitLambda = hitPoint.z / motion.z;
            }
            if (hitLambda < 0) {
              hitLambda = -hitLambda;
            }

            hits.add(new EntityHit(hitLambda, new Vector3f(mop.hitVec), entity));
          }
        }
      }
    }

    //Ray trace the bullet by comparing its next position to its current position
    Vec3 posVec = origin.toVec3();
    Vec3 nextPosVec = motion.toVec3().add(posVec);
    ArrayList<MovingObjectPosition> mops = rayTraceBlocks(posVec, nextPosVec, true, true, true,
        world, 8);

    posVec = origin.toVec3();

    for (MovingObjectPosition hit : mops) {
      if (hit != null) {
        //Calculate the lambda value of the intercept
        Vec3 hitVec = posVec.subtract(hit.hitVec);
        float lambda = 1;
        //Try each co-ordinate one at a time.
        if (motion.x != 0) {
          lambda = (float) (hitVec.xCoord / motion.x);
        } else if (motion.y != 0) {
          lambda = (float) (hitVec.yCoord / motion.y);
        } else if (motion.z != 0) {
          lambda = (float) (hitVec.zCoord / motion.z);
        }

        if (lambda < 0) {
          lambda = -lambda;
        }
        hits.add(new BlockHit(hit, lambda));
      }

    }

    //We hit something
    if (!hits.isEmpty()) {
      //Sort the hits according to the intercept position
      Collections.sort(hits);
    }

    return hits;
  }

  public static Vector3f GetPlayerMuzzlePosition(EntityPlayer player, boolean isOffHand) {
    PlayerSnapshot snapshot = new PlayerSnapshot(player);
    PlayerData data = PlayerHandler.getPlayerData(player);

    ItemStack itemstack = (isOffHand && data != null && data.offHandGunSlot != 0)
        ? player.inventory.getStackInSlot(data.offHandGunSlot - 1)
        : player.getCurrentEquippedItem();

    if (itemstack != null && itemstack.getItem() instanceof ItemGun) {
      GunType gunType = ((ItemGun) itemstack.getItem()).GetType();
      AttachmentType barrelType = gunType.getBarrel(itemstack);

      return Vector3f.add(new Vector3f(player.posX, player.posY, player.posZ),
          snapshot.GetMuzzleLocation(gunType, barrelType, isOffHand), null);
    }

    return new Vector3f(player.getPositionEyes(0.0f));
  }

  public static abstract class BulletHit implements Comparable<BulletHit> {

    /**
     * The time along the ray that the intersection happened. Between 0 and 1
     */
    public float intersectTime;
    public Vector3f hitPos;

    public BulletHit(float intersectTime, Vector3f hitPos) {
      this.intersectTime = intersectTime;
      this.hitPos = hitPos;
    }

    @Override
    public int compareTo(BulletHit other) {
      if (intersectTime < other.intersectTime) {
        return -1;
      } else if (intersectTime > other.intersectTime) {
        return 1;
      }
      return 0;
    }

    public abstract Entity GetEntity();
  }

  public static class BlockHit extends BulletHit {

    public MovingObjectPosition raytraceResult;


    public BlockHit(MovingObjectPosition mop, float f) {
      super(f, new Vector3f(mop.hitVec));
      raytraceResult = mop;
    }

    @Override
    public Entity GetEntity() {
      return null;
    }
  }

  public static class EntityHit extends BulletHit {

    public Entity entity;

    public EntityHit(float intersectTime, Vector3f hitPos, Entity entity) {
      super(intersectTime, hitPos);
      this.entity = entity;
    }

    @Override
    public Entity GetEntity() {
      return entity;
    }
  }

  public static class DriveableHit extends BulletHit {

    public EntityDriveable driveable;
    public EnumDriveablePart part;

    public DriveableHit(EntityDriveable driveable, EnumDriveablePart part, float intersectTime,
        Vector3f hitPos) {
      super(intersectTime, hitPos);
      this.driveable = driveable;
      this.part = part;
    }


    @Override
    public Entity GetEntity() {
      return driveable;
    }
  }

  /**
   * Raytracing will return a load of these objects containing hit data. These will then be compared
   * against each other and against any block hits The hit that occurs first along the path of the
   * bullet is the one that is acted upon. Unless the bullet has penetration of course
   */
  public static class PlayerBulletHit extends BulletHit {

    /**
     * The hitbox hit
     */
    public PlayerHitbox hitbox;

    public PlayerBulletHit(PlayerHitbox box, float intersectTime, Vector3f hitPos) {
      super(intersectTime, hitPos);
      hitbox = box;
    }

    @Override
    public Entity GetEntity() {
      return hitbox.player;
    }
  }

  private static byte GetClassType(BulletHit hit) {
    if (hit instanceof BlockHit) {
      return 0;
    }
    if (hit instanceof EntityHit) {
      return 1;
    }
    if (hit instanceof DriveableHit) {
      return 2;
    }
    if (hit instanceof PlayerBulletHit) {
      return 3;
    }
    return -1;
  }

  public static void WriteToBuffer(BulletHit hit, ByteBuf buffer) {
    buffer.writeByte(GetClassType(hit));
    if (hit != null) {
      buffer.writeFloat(hit.intersectTime);
      if (hit instanceof BlockHit) {
        BlockHit blockHit = (BlockHit) hit;
        buffer.writeByte(blockHit.raytraceResult.sideHit.ordinal());
        buffer.writeInt(blockHit.raytraceResult.getBlockPos().getX());
        buffer.writeInt(blockHit.raytraceResult.getBlockPos().getY());
        buffer.writeInt(blockHit.raytraceResult.getBlockPos().getZ());
      }
      if (hit instanceof EntityHit) {
        EntityHit entityHit = (EntityHit) hit;
        buffer.writeInt(entityHit.entity.getEntityId());
      }
      if (hit instanceof DriveableHit) {
        DriveableHit driveableHit = (DriveableHit) hit;
        buffer.writeInt(driveableHit.driveable.getEntityId());
        buffer.writeByte(driveableHit.part.ordinal());
      }
      if (hit instanceof PlayerBulletHit) {
        PlayerBulletHit playerBulletHit = (PlayerBulletHit) hit;
        buffer.writeInt(playerBulletHit.hitbox.player.getEntityId());
        buffer.writeByte(playerBulletHit.hitbox.type.ordinal());
      }
      hit.hitPos.writeToBuffer(buffer);
    }
  }

  public static BulletHit ReadFromBuffer(ByteBuf buffer) {
    byte type = buffer.readByte();

    switch (type) {
      default:
      case -1: // No hit
      {
        return null;
      }
      case 0: // BlockHit
      {
        float intersectTime = buffer.readFloat();
        EnumFacing facing = EnumFacing.VALUES[buffer.readByte()];
        BlockPos blockPos = new BlockPos(buffer.readInt(), buffer.readInt(), buffer.readInt());
        return new BlockHit(
            new MovingObjectPosition(Vector3f.readFromBuffer(buffer).toVec3(), facing, blockPos),
            intersectTime);
      }
      case 1: // EntityHit
      {
        float intersectTime = buffer.readFloat();
        Entity entity = GetEntityByID(buffer.readInt());
        return new EntityHit(intersectTime, Vector3f.readFromBuffer(buffer), entity);
      }
      case 2: // DriveableHit
      {
        float intersectTime = buffer.readFloat();
        Entity entity = GetEntityByID(buffer.readInt());
        if (entity instanceof EntityDriveable) {
          return new DriveableHit((EntityDriveable) entity,
              EnumDriveablePart.values()[buffer.readByte()], intersectTime,
              Vector3f.readFromBuffer(buffer));
        } else {
          FlansMod.log("Entity was not a driveable");
          buffer.readByte();
          Vector3f.readFromBuffer(buffer);
          return null;
        }
      }
      case 3: // PlayerBulletHit
      {
        float intersectTime = buffer.readFloat();
        Entity entity = GetEntityByID(buffer.readInt());
        if (entity instanceof EntityPlayer) {
          EntityPlayer player = (EntityPlayer) entity;
          PlayerSnapshot snapshot = new PlayerSnapshot(player);
          return new PlayerBulletHit(snapshot.GetHitbox(EnumHitboxType.values()[buffer.readByte()]),
              intersectTime, Vector3f.readFromBuffer(buffer));
        } else {
          FlansMod.log("Entity was not a player");
          buffer.readByte();
          Vector3f.readFromBuffer(buffer);
          return null;
        }
      }
    }
  }

  public static Entity GetEntityByID(int id) {
    if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
      for (World world : MinecraftServer.getServer().worldServers) {
        Entity entity = world.getEntityByID(id);
        if (entity != null) {
          return entity;
        }
      }
    } else {
      return Minecraft.getMinecraft().theWorld.getEntityByID(id);
    }

    return null;
  }

  public static ArrayList<MovingObjectPosition> rayTraceBlocks(Vec3 vec31, Vec3 vec32,
      boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox,
      boolean returnLastUncollidableBlock, World world, int maxHits) {
    ArrayList<MovingObjectPosition> hits = new ArrayList<>();
    if (!Double.isNaN(vec31.xCoord) && !Double.isNaN(vec31.yCoord) && !Double.isNaN(vec31.zCoord)) {
      if (!Double.isNaN(vec32.xCoord) && !Double.isNaN(vec32.yCoord) && !Double
          .isNaN(vec32.zCoord)) {
        Vec3 pvec31 = new Vec3(vec31.xCoord, vec31.yCoord, vec31.zCoord);

        int i = MathHelper.floor_double(vec32.xCoord);
        int j = MathHelper.floor_double(vec32.yCoord);
        int k = MathHelper.floor_double(vec32.zCoord);
        int l = MathHelper.floor_double(vec31.xCoord);
        int i1 = MathHelper.floor_double(vec31.yCoord);
        int j1 = MathHelper.floor_double(vec31.zCoord);
        BlockPos blockpos = new BlockPos(l, i1, j1);
        IBlockState iblockstate = world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();

        if ((!ignoreBlockWithoutBoundingBox || stopOnLiquid && block instanceof BlockLiquid
            || block.getCollisionBoundingBox(world, blockpos, iblockstate) != null) && block
            .canCollideCheck(iblockstate, stopOnLiquid)) {
          MovingObjectPosition movingobjectposition = block
              .collisionRayTrace(world, blockpos, vec31, vec32);

          if (movingobjectposition != null) {
            hits.add(movingobjectposition);
//                        return movingobjectposition;
          }
        }

        MovingObjectPosition movingobjectposition2 = null;
        int k1 = 200;

        boolean prevWasHit = false;
        while (k1-- >= 0) {
          pvec31 = new Vec3(vec31.xCoord, vec31.yCoord, vec31.zCoord);
          if (Double.isNaN(vec31.xCoord) || Double.isNaN(vec31.yCoord) || Double
              .isNaN(vec31.zCoord)) {
            return hits;
          }

          if (l == i && i1 == j && j1 == k) {
            return hits;
          }

          boolean flag2 = true;
          boolean flag = true;
          boolean flag1 = true;
          double d0 = 999.0D;
          double d1 = 999.0D;
          double d2 = 999.0D;

          if (i > l) {
            d0 = (double) l + 1.0D;
          } else if (i < l) {
            d0 = (double) l + 0.0D;
          } else {
            flag2 = false;
          }

          if (j > i1) {
            d1 = (double) i1 + 1.0D;
          } else if (j < i1) {
            d1 = (double) i1 + 0.0D;
          } else {
            flag = false;
          }

          if (k > j1) {
            d2 = (double) j1 + 1.0D;
          } else if (k < j1) {
            d2 = (double) j1 + 0.0D;
          } else {
            flag1 = false;
          }

          double d3 = 999.0D;
          double d4 = 999.0D;
          double d5 = 999.0D;
          double d6 = vec32.xCoord - vec31.xCoord;
          double d7 = vec32.yCoord - vec31.yCoord;
          double d8 = vec32.zCoord - vec31.zCoord;

          if (flag2) {
            d3 = (d0 - vec31.xCoord) / d6;
          }

          if (flag) {
            d4 = (d1 - vec31.yCoord) / d7;
          }

          if (flag1) {
            d5 = (d2 - vec31.zCoord) / d8;
          }

          if (d3 == -0.0D) {
            d3 = -1.0E-4D;
          }

          if (d4 == -0.0D) {
            d4 = -1.0E-4D;
          }

          if (d5 == -0.0D) {
            d5 = -1.0E-4D;
          }

          EnumFacing enumfacing;

          if (d3 < d4 && d3 < d5) {
            enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
            vec31 = new Vec3(d0, vec31.yCoord + d7 * d3, vec31.zCoord + d8 * d3);
          } else if (d4 < d5) {
            enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
            vec31 = new Vec3(vec31.xCoord + d6 * d4, d1, vec31.zCoord + d8 * d4);
          } else {
            enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
            vec31 = new Vec3(vec31.xCoord + d6 * d5, vec31.yCoord + d7 * d5, d2);
          }
          if (prevWasHit) {

            hits.get(hits.size() - 1).hitInfo = vec31.subtract(pvec31);
            prevWasHit = false;
          }

          l = MathHelper.floor_double(vec31.xCoord) - (enumfacing == EnumFacing.EAST ? 1 : 0);
          i1 = MathHelper.floor_double(vec31.yCoord) - (enumfacing == EnumFacing.UP ? 1 : 0);
          j1 = MathHelper.floor_double(vec31.zCoord) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
          blockpos = new BlockPos(l, i1, j1);
          IBlockState iblockstate1 = world.getBlockState(blockpos);
          Block block1 = iblockstate1.getBlock();

          if (!ignoreBlockWithoutBoundingBox || stopOnLiquid && block1 instanceof BlockLiquid
              || block1.getCollisionBoundingBox(world, blockpos, iblockstate1) != null) {
            if (block1.canCollideCheck(iblockstate1, stopOnLiquid)) {
              MovingObjectPosition movingobjectposition1 = block1
                  .collisionRayTrace(world, blockpos, vec31, vec32);

              if (movingobjectposition1 != null) {
                hits.add(movingobjectposition1);
                prevWasHit = true;
                // return movingobjectposition1;
              }
            } else {
              movingobjectposition2 = new MovingObjectPosition(
                  MovingObjectPosition.MovingObjectType.MISS, vec31, enumfacing, blockpos);
            }
          }
        }

        if (hits.size() >= maxHits) {
          return hits;
        }
        // return returnLastUncollidableBlock ? movingobjectposition2 : null;
      } else {
        return hits;
      }
    } else {
      return hits;
    }
    return hits;
  }
}
