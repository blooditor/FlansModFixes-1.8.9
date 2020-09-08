package com.flansmod.common.guns;

import com.flansmod.apocalypse.common.entity.EntityFlansModShooter;
import com.flansmod.client.FlansModClient;
import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.debug.EntityDebugDot;
import com.flansmod.client.debug.EntityDebugVector;
import com.flansmod.client.model.InstantBulletRenderer;
import com.flansmod.common.FlansMod;
import com.flansmod.common.FlansModExplosion;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.PlaneType;
import com.flansmod.common.driveables.VehicleType;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.guns.raytracing.EnumHitboxType;
import com.flansmod.common.guns.raytracing.FlansModRaytracer;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.BlockHit;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.BulletHit;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.DriveableHit;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.EntityHit;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.PlayerBulletHit;
import com.flansmod.common.network.PacketClientBulletUpdate;
import com.flansmod.common.network.PacketFlak;
import com.flansmod.common.network.PacketHitmark;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import io.netty.buffer.ByteBuf;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.WeakHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBullet extends EntityShootable implements IEntityAdditionalSpawnData {

  private static final int MAX_HITS = 10;
  private static int bulletLife = 600; // Kill bullets after 30 seconds
  public Entity owner;
  private int ticksInAir;
  public BulletType type;
  /**
   * What type of weapon did this come from? For death messages
   */
  public InfoType firedFrom;
  /**
   * The amount of damage the gun imparted upon the bullet. Multiplied by the bullet damage to get
   * total damage
   */
  public float damage;
  public boolean shotgun = false;
  /**
   * If this is non-zero, then the player raytrace code will look back in time to when the player
   * thinks their bullet should have hit
   */
  public int pingOfShooter = 0;
  /**
   * Avoids the fact that using the entity random to calculate spread direction always results in
   * the same direction
   */
  public static Random bulletRandom = new Random();
  /**
   * For homing missiles
   */
  public Entity lockedOnTo;

  // public float penetratingPower;

  private float yOffset;

  @SideOnly(Side.CLIENT)
  private boolean playedFlybySound;

  boolean sentHitMarker;

  public int clientBulletId; //serverside: -1 means server controls, int means a client controls. clientside: -2 means server (or other client) controls, -1 means this client controls

  //Object = either Entity or DriveablePart
  public WeakHashMap<Object, Float> appliedDamage = new WeakHashMap<>();
  int totalHits = 0;
  private boolean showWaterSplash = true;
  public float originalSpeed;

  public boolean renderTrail = true;

  public EntityBullet(World world) {
    super(world);
    renderDistanceWeight = 100f;
    ignoreFrustumCheck = true;
    ticksInAir = 0;
    setSize(0.5F, 0.5F);
    clientBulletId = world.isRemote ? -2 : -1;
  }

  /**
   * Private partial constructor to avoid repeated code. All constructors go through this one
   */
  private EntityBullet(World world, EntityLivingBase shooter, float gunDamage,
      BulletType bulletType,
      InfoType shotFrom) {
    this(world);
    owner = shooter;
      if (shooter instanceof EntityPlayerMP) {
          pingOfShooter = ((EntityPlayerMP) shooter).ping;
      }
    type = bulletType;
    firedFrom = shotFrom;
    damage = gunDamage;
  }

  /**
   * Method called by ItemGun for creating bullets from a hand held weapon
   */
  public EntityBullet(World world, EntityLivingBase shooter, float spread, float gunDamage,
      BulletType type1,
      float speed, boolean shot, InfoType shotFrom) {
    this(world, new Vec3(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ),
        shooter.rotationYaw,
        shooter.rotationPitch, shooter, spread, gunDamage, type1, speed, shotFrom);
    shotgun = shot;
  }

  /**
   * More generalised bullet constructor
   */
  public EntityBullet(World world, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter,
      float spread,
      float gunDamage, BulletType type1, float speed, InfoType shotFrom) {
    this(world, shooter, gunDamage, type1, shotFrom);
    setLocationAndAngles(origin.xCoord, origin.yCoord, origin.zCoord, yaw, pitch);
    setPosition(posX, posY, posZ);
    yOffset = 0.0F;
    motionX = -MathHelper.sin((rotationYaw / 180F) * 3.14159265F)
        * MathHelper.cos((rotationPitch / 180F) * 3.14159265F);
    motionZ = MathHelper.cos((rotationYaw / 180F) * 3.14159265F)
        * MathHelper.cos((rotationPitch / 180F) * 3.14159265F);
    motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
    setArrowHeading(motionX, motionY, motionZ, spread / 2F, speed);
  }

  /**
   *
   */
  public EntityBullet(World world, Vector3f origin, Vector3f direction, EntityLivingBase shooter,
      float spread,
      float gunDamage, BulletType type1, float speed, InfoType shotFrom) {
    this(world, shooter, gunDamage, type1, shotFrom);
    damage = gunDamage;
    setPosition(origin.x, origin.y, origin.z);
    motionX = direction.x;
    motionY = direction.y;
    motionZ = direction.z;
    setArrowHeading(motionX, motionY, motionZ, spread, speed);
  }

  /**
   * Bomb constructor. Inherits the motion and rotation of the plane
   */
  public EntityBullet(World world, Vec3 origin, float yaw, float pitch, double motX, double motY,
      double motZ,
      EntityLivingBase shooter, float gunDamage, BulletType type1, InfoType shotFrom) {
    this(world, shooter, gunDamage, type1, shotFrom);
    setLocationAndAngles(origin.xCoord, origin.yCoord, origin.zCoord, yaw, pitch);
    setPosition(posX, posY, posZ);
    yOffset = 0.0F;
    motionX = motX;
    motionY = motY;
    motionZ = motZ;
  }

  @Override
  protected void entityInit() {
  }

  public AxisAlignedBB getBoundingBox() {
    return getEntityBoundingBox();
  }

  public void setArrowHeading(double d, double d1, double d2, float spread, float speed) {
    spread /= 5F;
    float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
    d /= f2;
    d1 /= f2;
    d2 /= f2;
    d *= speed;
    d1 *= speed;
    d2 *= speed;
    d += rand.nextGaussian() * 0.005D * spread * speed;
    d1 += rand.nextGaussian() * 0.005D * spread * speed;
    d2 += rand.nextGaussian() * 0.005D * spread * speed;
    motionX = d;
    motionY = d1;
    motionZ = d2;
    float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
    prevRotationYaw = rotationYaw = (float) ((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
    prevRotationPitch = rotationPitch = (float) ((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);

    lockedOnTo = getLockOnTarget();

    originalSpeed = speed;
  }

  /**
   * Find the entity nearest to the missile's trajectory, anglewise
   */
  private Entity getLockOnTarget() {
    if (type.lockOnToPlanes || type.lockOnToVehicles || type.lockOnToMechas || type.lockOnToLivings
        || type.lockOnToPlayers) {

      Vector3f motionVec = new Vector3f(motionX, motionY, motionZ);
      Entity closestEntity = null;
      float maxAngle = type.maxLockOnAngle * 3.14159265F / 180F;
    //  float closestAngle = Float.MAX_VALUE;
      double closestDistance = Float.MAX_VALUE;
      for (Object obj : worldObj.loadedEntityList) {
        Entity entity = (Entity) obj;
        if (entity != this && ((type.lockOnToMechas && entity instanceof EntityMecha)
            || (type.lockOnToVehicles && entity instanceof EntityVehicle)
            || (type.lockOnToPlanes && entity instanceof EntityPlane
            || entity instanceof EntityBullet && ((EntityBullet) entity).type != null
            && ((EntityBullet) entity).type.shortName != null
            && ((EntityBullet) entity).type.shortName.equals("flareGunAmmo"))
            || (type.lockOnToPlayers && entity instanceof EntityPlayer)
            || (type.lockOnToLivings && entity instanceof EntityLivingBase))) {

            if (entity == owner || owner != null && owner.ridingEntity instanceof EntitySeat
                && ((EntitySeat) owner.ridingEntity).driveable == entity) {
                continue;
            }
          Vector3f relPosVec = new Vector3f(entity.posX - posX, entity.posY - posY,
              entity.posZ - posZ);

          double dist = this.getDistanceSqToEntity(entity);
          if (dist > 250 * 250) {
            continue;
          }

          float angle = Math.abs(Vector3f.angle(motionVec, relPosVec));

          if (angle > maxAngle) {
            if (lockedOnTo != entity || angle > 1.6f) {
              continue;
            }
          }
          //	if(entity instanceof EntityBullet && ((EntityBullet)entity).type.shortName.equals("flareGunAmmo"))
          //		angle/=3;
          //				log(entity + " " + angle + " " + dist);

          //give more weight to keep target
          float minDiff = 1;
          if (lockedOnTo != null && entity != lockedOnTo && !(entity instanceof EntityBullet)) {
            minDiff = 0.5f;
          }
          if (dist < closestDistance * minDiff) {
            closestEntity = entity;
            closestDistance = dist;
          }
        }
      }

      if (lockedOnTo != closestEntity) {

      }
      return closestEntity;
    }
    return null;
  }

  @Override
  public void setVelocity(double d, double d1, double d2) {
    motionX = d;
    motionY = d1;
    motionZ = d2;
    if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
      float f = MathHelper.sqrt_double(d * d + d2 * d2);
      prevRotationYaw = rotationYaw = (float) ((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
      prevRotationPitch = rotationPitch = (float) ((Math.atan2(d1, f) * 180D)
          / 3.1415927410125732D);
      setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
    }
  }

  /**
   * Static method so we can handle this without creating a bullet entity. Just pass in a null bullet
   */
 /*   public static boolean OnHit(World world, Vector3f origin, Vector3f hit, Entity shooter, InfoType shotFrom, ShootableType shootableType, EntityBullet bullet, float damage, BulletHit bulletHit) {
        if (!(shootableType instanceof BulletType)) {
            FlansMod.log("Tried to fire grenade instantly");
            return true;
        }
        if (world == null || origin == null || hit == null || shooter == null || shotFrom == null || shootableType == null) {
            //FlansMod.log("Something was null");
            return true;
        }

        BulletType bulletType = (BulletType) shootableType;

        float penetratingPower = bullet == null ? bulletType.penetratingPower : bullet.penetratingPower;

        DamageSource source = bullet == null ? GetBulletDamage(shotFrom, bulletType, shooter, false) : bullet.getBulletDamage(false);

        if (bulletHit instanceof DriveableHit) {
            DriveableHit driveableHit = (DriveableHit) bulletHit;
            penetratingPower = driveableHit.driveable.bulletHit(bulletType, damage, driveableHit, penetratingPower);
            if (FlansMod.DEBUG && world.isRemote)
                world.spawnEntityInWorld(new EntityDebugDot(world, hit, 1000, 0F, 0F, 1F));

        } else if (bulletHit instanceof PlayerBulletHit) {
            PlayerBulletHit playerHit = (PlayerBulletHit) bulletHit;

            //rare crash fix
            if (playerHit != null && playerHit.hitbox != null) {
                penetratingPower = playerHit.hitbox.hitByBullet(source, shooter, shotFrom, bulletType, damage, penetratingPower);
            }
            if (FlansMod.DEBUG && world.isRemote)
                world.spawnEntityInWorld(new EntityDebugDot(world, hit, 1000, 1F, 0F, 0F));
        } else if (bulletHit instanceof EntityHit) {
            EntityHit entityHit = (EntityHit) bulletHit;

            if (entityHit.entity != null) {
                if (entityHit.entity.attackEntityFrom(source, damage * bulletType.damageVsLiving) && entityHit.entity instanceof EntityLivingBase) {
                    EntityLivingBase living = (EntityLivingBase) entityHit.entity;
                    for (PotionEffect effect : bulletType.hitEffects) {
                        living.addPotionEffect(new PotionEffect(effect));
                    }
                    //If the attack was allowed, we should remove their immortality cooldown so we can shoot them again. Without this, any rapid fire gun become useless
                    living.hurtResistantTime = 0;
                }
                if (bulletType.setEntitiesOnFire)
                    entityHit.entity.setFire(20);
                penetratingPower -= 1F;
                if (FlansMod.DEBUG && world.isRemote)
                    world.spawnEntityInWorld(new EntityDebugDot(world, hit, 1000, 1F, 1F, 0F));
            }
        } else if (bulletHit instanceof BlockHit) {
            BlockHit blockHit = (BlockHit) bulletHit;
            MovingObjectPosition raytraceResult = blockHit.raytraceResult;
            //If the hit wasn't an entity hit, then it must've been a block hit
            BlockPos pos = raytraceResult.getBlockPos();
            if (FlansMod.DEBUG && world.isRemote)
                world.spawnEntityInWorld(new EntityDebugDot(world, hit, 1000, 0F, 1F, 0F));

            Block block = world.getBlockState(pos).getBlock();
            Material mat = block.getMaterial();
            //If the bullet breaks glass, and can do so according to FlansMod, do so.
            if (!world.isRemote && bulletType.breaksGlass && mat == Material.glass) {
                if (TeamsManager.canBreakGlass) {
                    world.setBlockToAir(pos);
                    FlansMod.proxy.playBlockBreakSound(pos.getX(), pos.getY(), pos.getZ(), block);
                }
            }

            //penetratingPower -= block.getBlockHardness(worldObj, zTile, zTile, zTile);
            if (bullet != null)
                bullet.setPosition(blockHit.raytraceResult.hitVec.xCoord, blockHit.raytraceResult.hitVec.yCoord, blockHit.raytraceResult.hitVec.zCoord);
            //play sound when bullet hits block
            //	if(!world.isRemote)
            //		PacketPlaySound.sendSoundPacket(hit.x, hit.y, hit.z, bulletType.hitSoundRange, shooter.dimension, bulletType.hitSound != null && world.rand.nextBoolean()? bulletType.hitSound : "BulletHit", true);

            if (bullet != null) bullet.penetratingPower = penetratingPower;
            return true;
        }
        if (penetratingPower <= 0F || (bulletType.explodeOnImpact && (bullet == null || bullet.ticksInAir > 1))) {
            if (bullet != null) {
                bullet.setPosition(hit.x, hit.y, hit.z);
                bullet.penetratingPower = penetratingPower;
            }
            return true;
        }
        return false;
    }
*/

  /**
   *
   */
  public int doHit(Vector3f origin, Vector3f hit, Entity shooter, InfoType shotFrom,
      ShootableType shootableType, BulletHit bulletHit, float speed, float damage,
      float penetratingPower) {
    if (!(shootableType instanceof BulletType)) {
      FlansMod.log("Tried to fire grenade instantly");
      return 1;
    }
    World world = worldObj;
    if (world == null || origin == null || hit == null || shooter == null || shotFrom == null
        || shootableType == null) {
      //FlansMod.log("Something was null");
      return 1;
    }
    BulletType bulletType = this.type;

    String hitname = bulletHit instanceof BlockHit ? world
        .getBlockState(((BlockHit) bulletHit).raytraceResult.getBlockPos()).getBlock()
        .getUnlocalizedName() : bulletHit instanceof EntityHit ? "entity"
        : bulletHit instanceof PlayerBulletHit ? "player" : "driveable";

    //  if (!worldObj.isRemote)
    log("Hit: " + hitname + " " + speed + " " + damage + " " + penetratingPower);

    DamageSource source = this.getBulletDamage(false);

    Vector3f ricochetDirection = null;

    if (bulletHit instanceof DriveableHit) {
      DriveableHit driveableHit = (DriveableHit) bulletHit;
      if (!appliedDamage.containsKey(driveableHit.part)) {

        float damageApplied = driveableHit.driveable
            .bulletHit(bulletType, damage, driveableHit, penetratingPower);
          if (FlansMod.DEBUG && world.isRemote) {
              world.spawnEntityInWorld(new EntityDebugDot(world, hit, 1000, 0F, 0F, 1F));
          }
        DriveablePart partHit = driveableHit.driveable.driveableData.parts.get(driveableHit.part);
        doEntityPenetration(bulletType, speed, driveableHit.driveable, partHit.maxHealth,
            penetratingPower);
        appliedDamage.put(partHit,
            appliedDamage.containsKey(partHit) ? appliedDamage.get(partHit) + damageApplied
                : damageApplied);
        log("Dmg applied " + damageApplied);
      }
    } else if (bulletHit instanceof PlayerBulletHit) {
      PlayerBulletHit playerHit = (PlayerBulletHit) bulletHit;

      //rare crash fix
      if (playerHit.hitbox != null) {
        float damageApplied = playerHit.hitbox
            .hitByBullet(shooter, shotFrom, bulletType, damage, penetratingPower, this, speed);
        appliedDamage.put(playerHit.GetEntity(), appliedDamage.containsKey(playerHit.GetEntity()) ?
            appliedDamage.get(playerHit.GetEntity()) + damageApplied : damageApplied);

      }
        if (FlansMod.DEBUG && world.isRemote) {
            world.spawnEntityInWorld(new EntityDebugDot(world, hit, 1000, 1F, 0F, 0F));
        }
    } else if (bulletHit instanceof EntityHit) {
      EntityHit entityHit = (EntityHit) bulletHit;

      if (entityHit.entity != null && !appliedDamage.containsKey(entityHit.entity)) {
        float entityHealth =
            entityHit.entity instanceof EntityLiving ? ((EntityLiving) entityHit.entity)
                .getMaxHealth() : 10;
        if (!worldObj.isRemote) {

          if (entityHit.entity.attackEntityFrom(source, damage)
              && entityHit.entity instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase) entityHit.entity;
            for (PotionEffect effect : bulletType.hitEffects) {
              living.addPotionEffect(new PotionEffect(effect));
            }
            //If the attack was allowed, we should remove their immortality cooldown so we can shoot them again. Without this, any rapid fire gun become useless
            living.hurtResistantTime = 0;
          }
            if (bulletType.setEntitiesOnFire) {
                entityHit.entity.setFire(20);
            }
            if (FlansMod.DEBUG && world.isRemote) {
                world.spawnEntityInWorld(new EntityDebugDot(world, hit, 1000, 1F, 1F, 0F));
            }

        }
        doEntityPenetration(bulletType, speed, entityHit.entity, entityHealth, penetratingPower);
        float damageApplied = damage;
        appliedDamage.put(entityHit.GetEntity(), appliedDamage.containsKey(entityHit.GetEntity()) ?
            appliedDamage.get(entityHit.GetEntity()) + damageApplied : damageApplied);

      }
    } else if (bulletHit instanceof BlockHit) {
      BlockHit blockHit = (BlockHit) bulletHit;
      MovingObjectPosition raytraceResult = blockHit.raytraceResult;
      //If the hit wasn't an entity hit, then it must've been a block hit
      BlockPos pos = raytraceResult.getBlockPos();
        if (FlansMod.DEBUG && world.isRemote) {
            world.spawnEntityInWorld(new EntityDebugDot(world, hit, 1000, 0F, 1F, 0F));
        }

      Block block = world.getBlockState(pos).getBlock();
      Material mat = block.getMaterial();

      double hitLength = 1;
      if (blockHit.raytraceResult.hitInfo instanceof Vec3) {
        hitLength = ((Vec3) blockHit.raytraceResult.hitInfo).lengthVector();
        log("Hit len " + hitLength);
      }
      Vec3i normal = blockHit.raytraceResult.sideHit.getDirectionVec();
      Vector3f normal2 = new Vector3f(normal.getX(), normal.getY(), normal.getZ());
      Vector3f bulletDir = Vector3f.sub(blockHit.hitPos, origin, null);
      bulletDir.normalise();
      double hitAngle = Vector3f.angle(normal2, new Vector3f(motionX, motionY, motionZ));
      int blockPenetrationResult = doBlockPenetration(pos, bulletType, speed, block, mat,
          penetratingPower, hitLength, hitAngle);

      //If the bullet breaks glass, and can do so according to FlansMod, do so.
      if (!world.isRemote && bulletType.breaksGlass && mat == Material.glass && (motionX != 0
          || motionY != 0 || motionZ != 0)) {
        if (TeamsManager.canBreakGlass) {
          world.setBlockToAir(pos);
        }
      }

      if (blockPenetrationResult == 2) {
        float dotProduct = Vector3f.dot(bulletDir, normal2);
        normal2.scale(dotProduct * 2);
        ricochetDirection = Vector3f.sub(bulletDir, normal2, null);
        ricochetDirection.normalise();
        ricochetDirection.scale(speed * 0.1f + speed * rand.nextFloat() * 0.15f);
      } else if (blockPenetrationResult == 0) {
        return -1;
      }

      //play sound when bullet hits block
      //	if(!world.isRemote)
      //		PacketPlaySound.sendSoundPacket(hit.x, hit.y, hit.z, bulletType.hitSoundRange, shooter.dimension, bulletType.hitSound != null && world.rand.nextBoolean()? bulletType.hitSound : "BulletHit", true);

    }

    if (!worldObj.isRemote && damage > 0 && !sentHitMarker && !(bulletHit instanceof BlockHit)) {
      sendHitMarker(bulletHit);
    }
    float newSpeed = getBulletSpeed();
    setPosition(hit.x, hit.y, hit.z);
    boolean explodeOnImpact =
        bulletType.explodeOnImpact && (!bulletType.shortName.equals("minigunExplosiveAmmo") || rand
            .nextBoolean());
    if (newSpeed <= 0F || (explodeOnImpact)) {
      if (ricochetDirection != null) {
        motionX = ricochetDirection.x + 0.04f * rand.nextGaussian();
        motionY = ricochetDirection.y + 0.04f * rand.nextGaussian();
        motionZ = ricochetDirection.z + 0.04f * rand.nextGaussian();
        return 2;
      } else {
        return 1;
      }
    }
    return 0;
  }

  public void doEntityPenetration(BulletType type, float speed, Entity e, float entityHealth,
      float penetratingPower) {

      if (appliedDamage.containsKey(e)) {
          return;
      }
    float stopPower =
        e instanceof EntityDriveable ? Math.max(40, entityHealth / 100) : entityHealth / 2f;
    if (e instanceof EntityFlansModShooter) {
      stopPower = 20;
    }

    applyPenetrationSpeedLoss(stopPower, speed, penetratingPower);
  }

  //0 = went trough (ex. bars) 1= penetrated, 2=ricochet
  private int doBlockPenetration(BlockPos pos, BulletType type, float speed, Block block,
      Material mat, float penetratingPower, double blockDepth, double hitAngle) {
    float hardness = block.getBlockHardness(worldObj, pos);
    float resistance = block.getExplosionResistance(null);

    //glass 0.5, planks 5, stone 20-30
    float sp = getBlockStopPower(block, mat);
    float stopPower = Math.max(sp, hardness + resistance);

      if (mat == Material.water) {
          stopPower = sp;
      }

    if (block == Blocks.iron_bars) {
      if (rand.nextBoolean()) {
        return 0;
      }
      stopPower *= rand.nextBoolean() ? 0 : Math.max(1, rand.nextFloat());
    }

    //still shoot trough thin parts of blocks
    if (blockDepth < 1) {
      stopPower *= blockDepth;
    }
    //    if (mat == Material.water) {
    //        stopPower = 0.4f + penetratingPower * 0.05f;
    //   }

    //TODO test
    //extra modifier if many blocks have been penetrated
    stopPower *= 1 + ((float) this.totalHits - 1) / MAX_HITS * 0.1f;
    stopPower += penetratingPower * 0.01f;

    // float  sp = getBlockStopPower(block, mat);

    stopPower = stopPower * 8.5f;

    applyPenetrationSpeedLoss(stopPower, speed, penetratingPower);
    return motionX == motionY && motionY == motionZ && motionZ == 0 && speed > 5 &&
        hitAngle < Math.PI - 1f &&
        rand.nextGaussian() < hitAngle / Math.PI && rand.nextInt(80) < stopPower &&
        rand.nextBoolean() ? 2 : 1;
  }

  private float getBlockStopPower(Block block, Material mat) {
    if (mat == Material.ground) {
      return 10;
    }
      if (mat == Material.rock) {
          return 10;
      }
      if (mat == Material.clay || mat == Material.sand) {
          return 8;
      }

      if (mat == Material.wood) {
          return 5;
      }

      if (mat == Material.leaves) {
          return 0.6f;
      }

    if (block.getUnlocalizedName().equalsIgnoreCase("tile.blockBulletproofGlass")) {
      return 8f;
    }
      if (mat == Material.glass) {
          return 0.4f;
      }

      if (mat == Material.water) {
          return 0.4f;
      }

    //    if(mat == Material.cloth)
    //         return 3;
    return 4;
  }

  public void applyPenetrationSpeedLoss(float stopPower, float speed, float penetratingPower) {

    float speedLoss = stopPower * type.ammoType.getPenetrationLoss();
    float speedChange = (penetratingPower - speedLoss) / penetratingPower;

      if (worldObj.isRemote) {
          log("penet: " + speedChange + " " + stopPower);
      }
    //failed to penetrate
    if (stopPower > penetratingPower || speedLoss >= penetratingPower) {
      motionX = motionY = motionZ = 0;
      return;
    }

    float spread = (1 - speedChange) * 0.11f;

    motionX *= speedChange;
    motionY *= speedChange;
    motionZ *= speedChange;

    //TODO needs testing
    /*    motionX += spread*rand.nextGaussian();
        motionY += spread*rand.nextGaussian();
        motionZ += spread*rand.nextGaussian();
*/
  }

  public float getBulletSpeed() {
    return (float) Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
  }


  private void sendHitMarker(BulletHit hit) {
    //     if(bullet.ticksExisted > 30)
    //         return;
    if (this.owner instanceof EntityPlayerMP) {
      boolean hs = false;
        if (hit instanceof PlayerBulletHit) {
            hs = ((PlayerBulletHit) hit).hitbox != null
                && ((PlayerBulletHit) hit).hitbox.type == EnumHitboxType.HEAD;
        }
      FlansMod.getPacketHandler().sendTo(new PacketHitmark(hs), (EntityPlayerMP) this.owner);
    }
  }

  public Vector3f intercept(Vector3f tpos, Vector3f tdir, double tspeed, Vector3f P1, double dXYZ,
      Vector3f motion) {

    if (lockedOnTo instanceof EntityPlane && ((EntityPlane) lockedOnTo).onGround()) {
      tdir.y = 0;
    }
    //scale distance
   // dXYZ *= 0.1;
    double angle = (tdir.lengthSquared() == 0 || motion.lengthSquared() == 0)? 1 : Vector3f.angle(tdir, motion);
    tspeed *= angle;

    double preaim = dXYZ + tspeed*1;
    preaim *= 0.3f;

    //calc pre-aim location locally
      if (tspeed != 0) {
          tdir.scale((float) (preaim));
      } else {
          tdir = new Vector3f(0, 0, 0);
      }
    double dX = lockedOnTo.posX - posX;
    double dY = lockedOnTo.posY - posY;
    double dZ = lockedOnTo.posZ - posZ;
    double dXZ = Math.sqrt(dX * dX + dZ * dZ);
    if (lockedOnTo instanceof EntityVehicle) {

      if (dXZ > 35) {
        tdir.y += dXZ * 0.3f;
      }else{
        tdir.y = (float) dY*1.6f * Math.max(0.3f, 0.6f/(Math.abs(motion.y)+0.3f));
      }


    }

    //pre aim globally
    Vector3f tLoc = new Vector3f(tpos.x + tdir.x, tpos.y + tdir.y, tpos.z + tdir.z);

    //target direction
    Vector3f t = new Vector3f(tLoc.x - P1.x, tLoc.y - P1.y, tLoc.z - P1.z);

    //	System.out.println(t + " " + P1);
    return t.normalise(null);
  }

  @Override
  public void onUpdate() {
    super.onUpdate();

    if (type == null) {
      super.setDead();
      return;
    }

      if (FlansMod.DEBUG && worldObj.isRemote) {
          worldObj
              .spawnEntityInWorld(new EntityDebugVector(worldObj, new Vector3f(posX, posY, posZ),
                  new Vector3f(motionX, motionY, motionZ), 20));
      }

    // Check the fuse to see if the bullet should explode
    ticksInAir++;
    if (ticksInAir > type.fuse && type.fuse > 0 && !isDead) {
      setDead();
    }
    if (clientBulletId != -1 && !worldObj.isRemote && owner instanceof EntityPlayerMP && (
        ((EntityPlayerMP) owner).playerNetServerHandler == null)) {
      log("Destroying bullet of offline player");
      setDead();
    }

    if (clientBulletId == -1 && totalHits == MAX_HITS) {
      log("Destroying bullet hits exceeded");
      setDead();
    }

    if (owner != null && type.ammoType != BulletType.AmmoType.DEFAULT) {
      double dy = owner.posY - this.posY;
      if (Math.abs(dy) > 300) {
        log("Destroying bullet too high");
        setDead();

      }
    }

    if ((worldObj.isRemote || clientBulletId == -1) && ticksExisted > bulletLife
        || ticksExisted > bulletLife + 10) {
        if (!worldObj.isRemote) {
            log("Destroying timeout bullet");
        }
      setDead();
    }

      if (isDead) {
          return;
      }

      if (worldObj.isRemote) {
          onUpdateClient();
      }

    Vector3f origin = new Vector3f(posX, posY, posZ);
    Vector3f motion = new Vector3f(motionX, motionY, motionZ);
    float speed = getBulletSpeed();

    if (totalHits < MAX_HITS && (clientBulletId == -1 || worldObj.isRemote)) {
      raytraceHits(origin, motion, speed);

    }
    // Movement dampening variables
    float drag = type.ammoType.getSpeedLoss();
    //     if (motionY > 0) {
    //        drag *= 1-Math.min(1, motionY/5f)*0.2f;
    //    }
    //    System.out.println(motionY + " - " + drag);
    float gravity = 0.02F;
    // If the bullet is in water, spawn particles and increase the drag
    if (isInWater()) {

      drag = 0.6F;
    }

    if (drag > 1 && speed > type.ammoType.getSpeed()) {
      drag = type.ammoType.getSpeed()/speed;
    }
    motionX *= drag;
    motionY *= drag;
    motionZ *= drag;
    float fallSpeed =
        type.ammoType == BulletType.AmmoType.DEFAULT || type.ammoType.isMissile()
            || type.ammoType == BulletType.AmmoType.SHELL ? type.fallSpeed : 1;
    motionY -= gravity * fallSpeed;


    // Apply homing action
    if (lockedOnTo != null) {

      if (ticksExisted % 4 == 0) {

        applyHoming(motion, speed);


      }

    }

    // Apply motion
    posX += motionX;
    posY += motionY;
    posZ += motionZ;
    setPosition(posX, posY, posZ);

    // Recalculate the angles from the new motion
    float motionXZ = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
    rotationYaw = (float) ((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
    rotationPitch = (float) ((Math.atan2(motionY, motionXZ) * 180D) / 3.1415927410125732D);
    // Reset the range of the angles
    for (; rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) {
    }
    for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) {
    }
    for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) {
    }
    for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) {
    }
    rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
    rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;

    // Particles
    if (type.trailParticles && worldObj.isRemote && ticksInAir > 1) {
      spawnParticles();
    }

    // Temporary fire glitch fix
      if (worldObj.isRemote) {
          extinguish();
      }
  }

  private void applyHoming(Vector3f motion, float speed) {
    Vector3f pos = new Vector3f(this.getPositionVector());

    double tSpeeed = lockedOnTo instanceof EntityPlane ? Math
        .abs(((EntityPlane) lockedOnTo).getRealSpeedXYZ()) : Math.sqrt(
        lockedOnTo.motionX * lockedOnTo.motionX + lockedOnTo.motionY * lockedOnTo.motionY
            + lockedOnTo.motionZ * lockedOnTo.motionZ);
    Vector3f tpos = new Vector3f(lockedOnTo.getPositionVector());
    Vector3f tdir = new Vector3f(lockedOnTo.motionX, lockedOnTo.motionY, lockedOnTo.motionZ);

    double dX = lockedOnTo.posX - posX;
    double dY = lockedOnTo.posY - posY;
    double dZ = lockedOnTo.posZ - posZ;
    double dXYZ = Math.sqrt(dX * dX + dY * dY + dZ * dZ);



    Vector3f nDir = intercept(tpos, tdir, tSpeeed, pos, dXYZ, motion);
   // System.out.println("ic " + nDir);

    float angle = Math.min(0.5f * type.lockOnForce,
        Math.abs(Vector3f.angle(motion, new Vector3f(nDir.x, nDir.y, nDir.z))));
    double lockOnPull = angle / 2F * type.lockOnForce;

    float speedModifier = 1 + speed;
    lockOnPull *= speedModifier;

    motion.x += lockOnPull * nDir.x;
    motion.y += lockOnPull * nDir.y;
    motion.z += lockOnPull * nDir.z;

//    System.out.println(speed + " " + lockOnPull + " " + motion + "   " + type.ammoType.getSpeed());
  /*  float maxSpeed = Math
        .min(1 + type.lockOnForce * ticksExisted / 200f, Math.max(2f, (float) (tSpeeed + 0.2)));

    motion.normalise();
    motion.scale(maxSpeed);*/

    motionX = motion.x;
    motionY = motion.y;
    motionZ = motion.z;

    //	}
    //	motionX += lockOnPull * dX / dXYZ;
    //	motionY += lockOnPull * dY / dXYZ;
    //	motionZ += lockOnPull * dZ / dXYZ;

    //blow up on flare
    if (lockedOnTo instanceof EntityBullet && dXYZ < 5) {
      setDead();
      return;
    }
    //
    if (lockedOnTo instanceof EntityDriveable && ticksExisted % 8 == 0) {
      //log(motion.length() + " " + tSpeeed);
      Entity nLock = getLockOnTarget();
  //    if (nLock != null || dXYZ > 30) {
        lockedOnTo = nLock;
     // }

    }
  }

  private void raytraceHits(Vector3f origin, Vector3f motion, float speed) {
    List<BulletHit> hits = FlansModRaytracer
        .Raytrace(worldObj, owner, ticksInAir > 20, this, origin, motion,
            pingOfShooter);

    //  hits.removeIf(hit -> hit instanceof BlockHit && blocksHit.contains(new BlockPos(hit.hitPos.toVec3())));
    //      System.out.println("pos " + getPositionVector() + " " + motion);
    // We hit something
    Vector3f finalHit = null;
    if (!hits.isEmpty()) {
      int penetrationResult = 0;
      int hitsToCount = 0;
      for (BulletHit bulletHit : hits) {
        hitsToCount++;
        totalHits++;
        if (totalHits >= MAX_HITS) {
          break;
        }

        float penetratingPower =
            speed * type.ammoType.getPenetration() / BulletType.BULLET_SPEED_MODIFIER;

        float relSpeed = (speed / this.originalSpeed);
        relSpeed = Math.min(1, relSpeed + 0.5f);
        float damage = relSpeed * this.damage;

        if (bulletHit instanceof DriveableHit) {
          float modifier = bulletHit.GetEntity() instanceof EntityPlane ? type.damageVsPlane
              : type.damageVsDriveable;
          if (firedFrom instanceof DriveableType) {
            damage *= modifier;
          } else {
            damage *= modifier > 10 ? modifier : 0;
          }
        } else if (bulletHit instanceof EntityHit || bulletHit instanceof PlayerBulletHit) {
          damage *= type.damageVsLiving;
        }

        Vector3f prevPos = new Vector3f(posX, posY, posZ);
        //0 = penetrated, 1 = stopped, 2 = ricochet, -1 fly through (e.g. iron bars)
        penetrationResult = doHit(origin, bulletHit.hitPos, owner, firedFrom, type, bulletHit,
            speed, damage, penetratingPower);
        if (worldObj.isRemote && penetrationResult != -1) {
          boolean animate = penetrationResult != 1 || !(bulletHit instanceof PlayerBulletHit);
          if (!animate && bulletHit instanceof PlayerBulletHit && bulletHit.GetEntity() != null
              && ((PlayerBulletHit) bulletHit).hitbox.type != EnumHitboxType.RIGHTITEM) {
            Float dmg = appliedDamage.get(bulletHit.GetEntity());
            animate = !bulletHit.GetEntity().isEntityAlive() || dmg != null && dmg >= 1;
          }
          if (animate) //dont animate blood splash if armor wasnt penetrated
          {
            animateHit(bulletHit, penetrationResult == 0, prevPos, motion, speed, damage);
          }
        }
        if (penetrationResult > 0) {
          if (penetrationResult == 1) {
            setDead();
            log("Stopped after " + totalHits + " hits");
          } else {
            log("Ricochated after " + totalHits + " hits");
          }
          finalHit = bulletHit.hitPos;
          break;
        }
      }

      //dont send hits that dont count
      while (hits.size() > hitsToCount) {
        hits.remove(hits.size() - 1);
      }
      if (worldObj.isRemote && clientBulletId == -1) {
        FlansMod.getPacketHandler().sendToServer(
            new PacketClientBulletUpdate(hits, this.getEntityId(), penetrationResult));
      }

    }

    if (worldObj.isRemote && !playedFlybySound) {

      playFlyBySound(finalHit);

    }
  }


  @SideOnly(Side.CLIENT)
  private void playFlyBySound(Vector3f finalHit) {
      if (clientBulletId == -1) {
          return;
      }
    float speed = getBulletSpeed();
      if (speed < 1) {
          return;
      }

    EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    Vector3f motion = new Vector3f(motionX, motionY, motionZ);
    motion.normalise();
    Vector3f v = new Vector3f(player.getPositionVector().subtract(this.getPositionVector()));
    // v.normalise();
    Vector3f pos = new Vector3f(this.getPositionVector());

    float t = Vector3f.dot(v, motion);
      if (t > speed || t < 0) {
          return;
      }
      if (finalHit != null && Vector3f.sub(finalHit, pos, null).length()
          < new Vector3f(t * motion.x, t * motionY, t * motionZ).length() + 5) {
          return;
      }

    Vector3f p = new Vector3f(posX + t * motion.x, posY + t * motion.y, posZ + t * motion.z);
    log(t + " " + speed + "  p " + p);

    float dist = Vector3f.sub(p, new Vector3f(player.getPositionVector()), null).length();

    float maxDist = 40;
      if (dist > maxDist) {
          return;
      }

    float soundSpeed = 15 * BulletType.BULLET_SPEED_MODIFIER;
    boolean playCrack = speed > soundSpeed;
    boolean playFlyby = !(playCrack && speed > soundSpeed * 2.5f);

    float volCrack = maxDist / 16 * speed * 0.1f;
    float volFlyby = playCrack ? maxDist / 16 * speed * 0.02f : maxDist / 16 * speed * 0.15f;

    playedFlybySound = true;
    if (playCrack) {
      FMLClientHandler.instance().getClient().getSoundHandler()
          .playSound(
              new PositionedSoundRecord(FlansModResourceHandler.getSound("bulletCrack"), volCrack,
                  1.0F / (rand.nextFloat() * 0.4F + 0.8F), p.x, p.y, p.z));
      log("Play crack sound");
    }
    if (playFlyby) {
      FMLClientHandler.instance().getClient().getSoundHandler()
          .playSound(
              new PositionedSoundRecord(FlansModResourceHandler.getSound("bulletFlyby"), volFlyby,
                  1.0F / (rand.nextFloat() * 0.4F + 0.8F), p.x, p.y, p.z));

    }
  }

  @SideOnly(Side.CLIENT)
  private void animateHit(BulletHit hitData, boolean penetrated, Vector3f origin, Vector3f motion,
      float bulletSpeed, float damage) {

    //addFlyByAndHitEffects(world, origin, hit, shooter, shotFrom, shotType, damage, hitData);

    damage = Math.min(damage, 50);

    if (hitData instanceof BlockHit) {

      BlockHit blockHit = (BlockHit) hitData;

      BlockPos blockPos = blockHit.raytraceResult.getBlockPos();
      IBlockState blockState = worldObj.getBlockState(blockHit.raytraceResult.getBlockPos());

      Vec3i normal = blockHit.raytraceResult.sideHit.getDirectionVec();
      Vector3f bulletDir = Vector3f.sub(hitData.hitPos, origin, null);
      bulletDir.normalise();
      bulletDir.scale(0.5f);

      InstantBulletRenderer.bulletHoles.add(
          new InstantBulletRenderer.BulletHole(blockPos, hitData.hitPos,
              blockHit.raytraceResult.sideHit, Math.min(3, (int) damage / 8)));

      int numParticles = (int) (damage * 1.5f);

      if (blockState != null) {
        if ((blockState.getBlock() == Blocks.water
            || blockState.getBlock() == Blocks.flowing_water)) {

          for (int i = 0; i < 4; i++) {
            float bubbleMotion = 0.25F;
            worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE,
                hitData.hitPos.x - motionX * bubbleMotion,
                hitData.hitPos.y - motionY * bubbleMotion,
                hitData.hitPos.z - motionZ * bubbleMotion, motionX, motionY, motionZ);
          }

          if (showWaterSplash) {
            for (int i = 0; i < numParticles * 4; i++) {
              float bubbleMotion = 1 + rand.nextFloat() * 0.5f + damage * 0.01f;
              EntityFX fx = Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(
                  EnumParticleTypes.WATER_SPLASH.getParticleID(), hitData.hitPos.x,
                  hitData.hitPos.y, hitData.hitPos.z, 0.0f, 0.0f, 0.0f,
                  Block.getStateId(blockState));
              fx.motionY *= bubbleMotion;
            }
          }
          showWaterSplash = false;
          FMLClientHandler.instance().getClient().getSoundHandler()
              .playSound(new net.minecraft.client.audio.PositionedSoundRecord(
                  new ResourceLocation("game.neutral.swim.splash"), bulletSpeed * 0.1f,
                  1.0F / (rand.nextFloat() * 0.4F + 0.8F), hitData.hitPos.x, hitData.hitPos.y,
                  hitData.hitPos.z));

          return;
        }
        showWaterSplash = true;

        for (int i = 0; i < numParticles; i++) {
          EntityFX fx = Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(
              EnumParticleTypes.BLOCK_CRACK.getParticleID(), hitData.hitPos.x, hitData.hitPos.y,
              hitData.hitPos.z, 0.0f, 0.0f, 0.0f,
              Block.getStateId(blockState));

          double scale = worldObj.rand.nextGaussian() * 0.1d + +0.5d + 0.01d * damage;

          fx.motionX = (double) normal.getX() * scale + worldObj.rand.nextGaussian() * 0.125d;
          fx.motionY = (double) normal.getY() * scale + worldObj.rand.nextGaussian() * 0.125d;
          fx.motionZ = (double) normal.getZ() * scale + worldObj.rand.nextGaussian() * 0.125d;

          fx.motionX += bulletDir.x;
          fx.motionY += bulletDir.y;
          fx.motionZ += bulletDir.z;

            if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
                fx.renderDistanceWeight = 100D;
            }
        }
        if (penetrated) {

          Vector3f exitPoint;
          EnumFacing exitSide = blockHit.raytraceResult.sideHit.getOpposite();
          if (blockHit.raytraceResult.hitInfo instanceof Vec3) {
            Vector3f hitLength = new Vector3f((Vec3) blockHit.raytraceResult.hitInfo);
            exitPoint = Vector3f.add(hitData.hitPos, hitLength, null);
            hitLength.scale(2);
            Vector3f rayTraceStart = Vector3f.add(hitData.hitPos, hitLength, null);

            MovingObjectPosition exit = blockState.getBlock()
                .collisionRayTrace(worldObj, blockPos, rayTraceStart.toVec3(),
                    hitData.hitPos.toVec3());
              if (exit != null) {
                  exitSide = exit.sideHit;
              }

          } else {
            exitPoint = Vector3f.add(hitData.hitPos, bulletDir, null);
          }

          InstantBulletRenderer.bulletHoles.add(
              new InstantBulletRenderer.BulletHole(blockPos, new Vector3f(exitPoint), exitSide,
                  Math.min(3, (int) damage / 8)));
          normal = exitSide.getDirectionVec();
          for (int i = 0; i < numParticles; i++) {
            EntityFX fx = Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(
                EnumParticleTypes.BLOCK_CRACK.getParticleID(), hitData.hitPos.x + bulletDir.x,
                hitData.hitPos.y + bulletDir.y, hitData.hitPos.z + bulletDir.z, 0.0f, 0.0f, 0.0f,
                Block.getStateId(blockState));

            double scale = worldObj.rand.nextGaussian() * 0.1d + +0.5d + 0.01d * damage;

            fx.motionX = (double) normal.getX() * scale + worldObj.rand.nextGaussian() * 0.125d;
            fx.motionY = (double) normal.getY() * scale + worldObj.rand.nextGaussian() * 0.125d;
            fx.motionZ = (double) normal.getZ() * scale + worldObj.rand.nextGaussian() * 0.125d;

            fx.motionX += bulletDir.x;
            fx.motionY += bulletDir.y;
            fx.motionZ += bulletDir.z;

              if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
                  fx.renderDistanceWeight = 100D;
              }
          }
        }
      }

      EntityFX fx = Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(
          EnumParticleTypes.CLOUD.getParticleID(), hitData.hitPos.x, hitData.hitPos.y,
          hitData.hitPos.z, 0.0f, 0.0f, 0.0f);
      double scale = worldObj.rand.nextGaussian() * 0.05d + 0.05d;
      fx.motionX = (double) normal.getX() * scale + worldObj.rand.nextGaussian() * 0.025d;
      fx.motionY = (double) normal.getY() * scale + worldObj.rand.nextGaussian() * 0.025d;
      fx.motionZ = (double) normal.getZ() * scale + worldObj.rand.nextGaussian() * 0.025d;

      //sounds
      Vector3f relPos = Vector3f
          .sub(hitData.hitPos, new Vector3f(Minecraft.getMinecraft().thePlayer.getPositionVector()),
              null);
      float dist = relPos.length();
      float maxDist = 20;
      if (dist <= maxDist) {
        FMLClientHandler.instance().getClient().getSoundHandler()
            .playSound(new PositionedSoundRecord(FlansModResourceHandler.getSound("bullet"),
                maxDist / 16 * bulletSpeed * 0.1f,
                1.0F / (rand.nextFloat() * 0.4F + 0.8F), hitData.hitPos.x, hitData.hitPos.y,
                hitData.hitPos.z));

        if (blockState != null) {
          Block block = blockState.getBlock();
          FMLClientHandler.instance().getClient().getSoundHandler().playSound(
              new PositionedSoundRecord(new ResourceLocation(block.stepSound.getBreakSound()),
                  (block.stepSound.getVolume() + 1.0F) / 2.0F,
                  block.stepSound.getFrequency() * 0.8F, (float) blockPos.getX() + 0.5F,
                  (float) blockPos.getY() + 0.5F, (float) blockPos.getZ() + 0.5F));

        }
      }

    } else if (FlansMod.enableBlood && (hitData instanceof EntityHit
        || hitData instanceof PlayerBulletHit)) {
      Entity entity = hitData.GetEntity();

        if (!(entity instanceof EntityLiving || entity instanceof EntityPlayer)) {
            return;
        }
        if ((entity instanceof EntityMob || entity instanceof IMob) && !(
            entity instanceof EntityFlansModShooter || entity instanceof EntityZombie
                || entity instanceof EntitySpider || entity instanceof EntityWitch)) {
            return;
        }

      if (hitData instanceof PlayerBulletHit) {
        //   hitData.hitPos = ((PlayerBulletHit) hitData).hitbox.axes.findGlobalVectorLocally(hitData.hitPos);
        //  hitData.hitPos = Vector3f.add(new Vector3f(entity.getPositionVector()), hitData.hitPos, null);
      }

      Vector3f bulletDir = Vector3f.sub(hitData.hitPos, origin, null);
      bulletDir.normalise();
      bulletDir.scale(0.5f);

      int numParticles = (int) (damage * 1.5f);

      if (penetrated) {
        for (int i = 0; i < numParticles; i++) {
          EntityFX fx = Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(
              EnumParticleTypes.BLOCK_CRACK.getParticleID(), hitData.hitPos.x, hitData.hitPos.y,
              hitData.hitPos.z, 0.0f, 0.0f, 0.0f,
              152);

          double scale = worldObj.rand.nextGaussian() * 0.1d + 1.5d + 0.01d * damage;

          fx.motionX = (double) bulletDir.x * scale + worldObj.rand.nextGaussian() * 0.125d;
          fx.motionY = (double) bulletDir.y * scale + worldObj.rand.nextGaussian() * 0.125d;
          fx.motionZ = (double) bulletDir.z * scale + worldObj.rand.nextGaussian() * 0.125d;

            if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
                fx.renderDistanceWeight = 100D;
            }
        }
      }

      for (int i = 0; i < numParticles / 3; i++) {
        EntityFX fx = Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(
            EnumParticleTypes.BLOCK_CRACK.getParticleID(), hitData.hitPos.x, hitData.hitPos.y,
            hitData.hitPos.z, 0.0f, 0.0f, 0.0f,
            152);

        double scale = worldObj.rand.nextGaussian() * 0.1d + 1.5d + 0.01d * damage;

        fx.motionX = worldObj.rand.nextGaussian() * 0.125d;
        fx.motionY = worldObj.rand.nextGaussian() * 0.125d;
        fx.motionZ = worldObj.rand.nextGaussian() * 0.125d;

          if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
              fx.renderDistanceWeight = 100D;
          }
      }

      BlockPos floorPos = new BlockPos(hitData.hitPos.toVec3());
      for (int i = 0; i < 3; i++) {
        floorPos = floorPos.down();
        if (worldObj.getBlockState(floorPos).getBlock() != Blocks.air) {
          InstantBulletRenderer.bulletHoles.add(new InstantBulletRenderer.BloodSplatter(floorPos,
              new Vector3f(hitData.hitPos.x, floorPos.getY() + 1, hitData.hitPos.z), EnumFacing.UP,
              this.rand.nextInt(8)));
          break;
        }
      }

      Vector3f dir = (Vector3f) new Vector3f(bulletDir).scale(10);
      Vector3f nextBlock = Vector3f.add(hitData.hitPos, dir, null);
      MovingObjectPosition bloodMop = worldObj
          .rayTraceBlocks(hitData.hitPos.toVec3(), nextBlock.toVec3(), true, true, true);
      if (bloodMop != null) {
        IBlockState state = worldObj.getBlockState(bloodMop.getBlockPos());
        Block block = state.getBlock();
        if (block == Blocks.air || !block.isFullBlock() || !block.isOpaqueCube() || block
            .isTranslucent()) {
          return;
        }
        //  float dist = Vector3f.sub(hitData.hitPos, new Vector3f(bloodMop.hitVec), null).length();

        InstantBulletRenderer.bulletHoles.add(
            new InstantBulletRenderer.BloodSplatter(bloodMop.getBlockPos(),
                new Vector3f(bloodMop.hitVec), bloodMop.sideHit, this.rand.nextInt(8)));

      }
    }
  }

  @SideOnly(Side.CLIENT)
  private void onUpdateClient() {

  }

  @SideOnly(Side.CLIENT)
  private void spawnParticles() {
    double dX = (posX - prevPosX) / 10;
    double dY = (posY - prevPosY) / 10;
    double dZ = (posZ - prevPosZ) / 10;

    float spread = 0.1F;
    for (int i = 0; i < 10; i++) {
      EntityFX particle = FlansModClient.getParticle(type.trailParticleType, worldObj,
          prevPosX + dX * i + rand.nextGaussian() * spread,
          prevPosY + dY * i + rand.nextGaussian() * spread,
          prevPosZ + dZ * i + rand.nextGaussian() * spread);
        if (particle != null && Minecraft.getMinecraft().gameSettings.fancyGraphics) {
            particle.renderDistanceWeight = 100D;
        }
      // worldObj.spawnEntityInWorld(particle);
    }
  }

  public DamageSource getBulletDamage(boolean headshot) {
      if (owner instanceof EntityPlayer) {
          return (new EntityDamageSourceGun(type.shortName, this, (EntityPlayer) owner, firedFrom,
              headshot))
              .setProjectile();
      } else {
          return (new EntityDamageSourceIndirect(type.shortName, this, owner)).setProjectile();
      }
  }

  public static DamageSource GetBulletDamage(InfoType firedFrom, BulletType type, Entity owner,
      boolean headshot) {
      if (owner instanceof EntityPlayer) {
          return (new EntityDamageSourceGun(type.shortName, owner, (EntityPlayer) owner, firedFrom,
              headshot))
              .setProjectile();
      } else {
          return (new EntityDamageSourceIndirect(type.shortName, owner, owner)).setProjectile();
      }
  }

  private boolean isPartOfOwner(Entity entity) {
      if (owner == null) {
          return false;
      }
      if (entity == owner || entity == owner.riddenByEntity || entity == owner.ridingEntity) {
          return true;
      }
    if (owner instanceof EntityPlayer) {
        if (PlayerHandler.getPlayerData((EntityPlayer) owner,
            worldObj.isRemote ? Side.CLIENT : Side.SERVER) == null) {
            return false;
        }
      EntityMG mg = PlayerHandler.getPlayerData((EntityPlayer) owner,
          worldObj.isRemote ? Side.CLIENT : Side.SERVER).mountingGun;
      if (mg != null && mg == entity) {
        return true;
      }
    }
    return owner.ridingEntity instanceof EntitySeat && (
        ((EntitySeat) owner.ridingEntity).driveable == null
            || ((EntitySeat) owner.ridingEntity).driveable.isPartOfThis(entity));
  }

  @Override
  public void setDead() {
      if (isDead) {
          return;
      }
    super.setDead();

    OnDetonate(worldObj, new Vector3f(posX, posY, posZ), owner, this, firedFrom, type);
  }

  public static void OnDetonate(World world, Vector3f detonatePos, Entity owner,
      EntityBullet bullet, InfoType shotFrom, BulletType bulletType) {
      if (world.isRemote || bulletType == null) {
          return;
      }
    if (bulletType.explosionRadius > 0) {
      if ((owner instanceof EntityPlayer)) {
        new FlansModExplosion(world, bullet, (EntityPlayer) owner, bulletType,
            detonatePos.x, detonatePos.y, detonatePos.z, bulletType.explosionRadius,
            bulletType.fireRadius > 0, bulletType.flak > 0, bulletType.explosionBreaksBlocks);
      } else {
        world.createExplosion(bullet, detonatePos.x, detonatePos.y, detonatePos.z,
            bulletType.explosionRadius,
            TeamsManager.explosions && bulletType.explosionBreaksBlocks);
      }
    }
    if (bulletType.fireRadius > 0) {
      for (float i = -bulletType.fireRadius; i < bulletType.fireRadius; i++) {
        for (float k = -bulletType.fireRadius; k < bulletType.fireRadius; k++) {
          for (int j = -1; j < 1; j++) {
            if (world.getBlockState(
                new BlockPos((int) (detonatePos.x + i), (int) (detonatePos.y + j),
                    (int) (detonatePos.z + k))).getBlock().getMaterial() == Material.air) {
              world.setBlockState(new BlockPos((int) (detonatePos.x + i), (int) (detonatePos.y + j),
                  (int) (detonatePos.z + k)), Blocks.fire.getDefaultState(), 2);
            }
          }
        }
      }
    }
    // Send flak packet
    if (bulletType.flak > 0 && owner != null) {
      FlansMod.getPacketHandler().sendToAllAround(
          new PacketFlak(detonatePos.x, detonatePos.y, detonatePos.z, bulletType.flak,
              bulletType.flakParticles), detonatePos.x, detonatePos.y, detonatePos.z, 200,
          owner.dimension);
    }
    // Drop item on hitting if bullet requires it
    if (bulletType.dropItemOnHit != null) {
      String itemName = bulletType.dropItemOnHit;
      int damage = 0;
      if (itemName.contains(".")) {
        damage = Integer.parseInt(itemName.split("\\.")[1]);
        itemName = itemName.split("\\.")[0];
      }
      ItemStack dropStack = InfoType.getRecipeElement(itemName, damage);

      if (dropStack != null && dropStack.stackSize != 0 && dropStack.getItem() != null) {
        EntityItem entityitem = new EntityItem(world, detonatePos.x, detonatePos.y, detonatePos.z,
            dropStack);
        entityitem.setDefaultPickupDelay();
        world.spawnEntityInWorld(entityitem);
      }
    }
  }

  @Override
  public void writeEntityToNBT(NBTTagCompound tag) {

      /*  tag.setString("type", type.shortName);
        tag.setString("firedFrom", firedFrom.shortName);
        if (owner == null)
            tag.setString("owner", "null");
        else
            tag.setString("owner", owner.getName());
            */
    super.setDead();
  }

  @Override
  public void readEntityFromNBT(NBTTagCompound tag) {


     /*   String typeString = tag.getString("type");
        String fired = tag.getString("firedFrom");
        String ownerName = tag.getString("owner");

        if (typeString != null)
            type = BulletType.getBullet(typeString);
        if (fired != null)
            firedFrom = InfoType.getType(fired);

        if (ownerName != null && !ownerName.equals("null"))
            owner = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager()
                    .getPlayerByUsername(ownerName);
                    */
  }

  public int getBrightnessForRender(float par1) {
      if (true) {
          return 15728880;
      }
    if (type.hasLight) {
      return 15728880;
    } else {
      int i = MathHelper.floor_double(this.posX);
      int j = MathHelper.floor_double(this.posZ);

      if (!worldObj.isAirBlock(new BlockPos(i, 0, j))) {
        double d0 = (getEntityBoundingBox().maxY - getBoundingBox().minY) * 0.66D;
        int k = MathHelper.floor_double(this.posY - (double) this.yOffset + d0);
        return this.worldObj.getLightFromNeighborsFor(EnumSkyBlock.SKY, new BlockPos(i, k, j));
      } else {
        return 0;
      }
    }
  }

  @Override
  public void writeSpawnData(ByteBuf data) {
      if (type == null) {
          return;
      }
    data.writeDouble(motionX);
    data.writeDouble(motionY);
    data.writeDouble(motionZ);
    data.writeInt(clientBulletId != -1 && this.owner != null ? this.owner.getEntityId() : -1);
    data.writeInt(lockedOnTo == null ? -1 : lockedOnTo.getEntityId());
    ByteBufUtils.writeUTF8String(data, type.shortName);
    ByteBufUtils.writeUTF8String(data, firedFrom.shortName);
    data.writeInt(owner == null ? -1 : owner.getEntityId());
    data.writeFloat(damage);
    data.writeBoolean(renderTrail);
  }

  @Override
  public void readSpawnData(ByteBuf data) {
    try {
      motionX = data.readDouble();
      motionY = data.readDouble();
      motionZ = data.readDouble();
      int shooterId = data.readInt();
      if (shooterId != -1) {
        clientBulletId = shooterId;
      }
      int lockedOnToID = data.readInt();
        if (lockedOnToID != -1) {
            lockedOnTo = worldObj.getEntityByID(lockedOnToID);
        }
      type = BulletType.getBullet(ByteBufUtils.readUTF8String(data));
      firedFrom = InfoType.getType(ByteBufUtils.readUTF8String(data));
      //penetratingPower = type.penetratingPower;
      int ownr = data.readInt();
      if (ownr >= 0) {
        owner = worldObj.getEntityByID(ownr);
      }
      damage = data.readFloat();
      originalSpeed = getBulletSpeed();
      renderTrail = data.readBoolean();
    } catch (Exception e) {
      FlansMod.log("Failed to load bullet.");
      super.setDead();
      e.printStackTrace();
    }
    //if a bullet is spawned which is already controlled by the local player, destroy it
    if (worldObj.isRemote && clientBulletId != -1
        && Minecraft.getMinecraft().thePlayer.getEntityId() == clientBulletId) {
      super.setDead();
    }
  }

  @Override
  public boolean isBurning() {

    return false;
  }

  @Override
  public boolean canBePushed() {
    return false;
  }

  public static void log(String s) {
    //  System.out.println(s);
  }
}
