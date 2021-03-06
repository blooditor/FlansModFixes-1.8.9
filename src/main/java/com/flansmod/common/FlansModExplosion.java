package com.flansmod.common;

import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityWheel;
import com.flansmod.common.guns.*;
import com.flansmod.common.guns.raytracing.FlansModRaytracer;
import com.flansmod.common.guns.raytracing.PlayerHitbox;
import com.flansmod.common.network.PacketHitmark.HitMarkType;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.teams.ItemTeamArmour;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class FlansModExplosion extends Explosion {

  /**
   * The type of Flan's Mod weapon that caused this explosion
   */
  private ShootableType type;
  private double x, y, z;
  private float radius;
  private World world;
  private Entity explosive;
  private EntityPlayer detonator;
  private List affectedBlockPositions;
  private final Vec3 position;
  /**
   * whether or not the explosion sets fire to blocks around it
   */
  private final boolean isFlaming;
  /**
   * whether or not this explosion spawns smoke particles
   */
  private final boolean isSmoking;
  private final Random explosionRNG;
  private final Map playerMap;
  private boolean breaksBlocks;

  public FlansModExplosion(World world, Entity entity, EntityPlayer detonator, ShootableType type,
      double x, double y, double z, float radius, boolean flaming, boolean smoking,
      boolean breaksBlocks) {
    super(world, entity, x, y, z, radius, flaming, smoking);
    this.explosionRNG = new Random();
    this.affectedBlockPositions = Lists.newArrayList();
    this.playerMap = Maps.newHashMap();
    this.type = type;
    this.x = x;
    this.y = y;
    this.z = z;
    this.radius = radius;
    this.world = world;
    this.explosive = entity;
    this.detonator = detonator;
    this.affectedBlockPositions = Lists.newArrayList();
    this.isFlaming = flaming;
    this.isSmoking = true;
    this.breaksBlocks = breaksBlocks && TeamsManager.explosions;
    this.position = new Vec3(x, y, z);

    if (!net.minecraftforge.event.ForgeEventFactory.onExplosionStart(world, this)) {
      this.doExplosionA();
      this.doExplosionB(true);
      for (Object obj : world.playerEntities) {
        FlansMod.getPacketHandler().sendTo(
            new S27PacketExplosion(x, y, z, radius, getAffectedBlockPositions(),
                (Vec3) getPlayerKnockbackMap().get(obj)), (EntityPlayerMP) obj);
      }
    }
  }

  /**
   * First part of the explosion. Damage blocks and entities
   */
  @Override
  public void doExplosionA() {
    HashSet hashset = Sets.newHashSet();
    boolean flag = true;
    int j;
    int k;

    boolean sendHitmarker = false;

    if (breaksBlocks) {
      for (int i = 0; i < 16; ++i) {
        for (j = 0; j < 16; ++j) {
          for (k = 0; k < 16; ++k) {
            if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15) {
              double d0 = (float) i / 15.0F * 2.0F - 1.0F;
              double d1 = (float) j / 15.0F * 2.0F - 1.0F;
              double d2 = (float) k / 15.0F * 2.0F - 1.0F;
              double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
              d0 /= d3;
              d1 /= d3;
              d2 /= d3;
              float f = radius * (0.7F + world.rand.nextFloat() * 0.6F);
              double d4 = x;
              double d6 = y;
              double d8 = z;

              for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
                BlockPos blockpos = new BlockPos(d4, d6, d8);
                IBlockState iblockstate = world.getBlockState(blockpos);

                if (iblockstate.getBlock().getMaterial() != Material.air) {
                  float f2 = explosive != null ? explosive
                      .getExplosionResistance(this, world, blockpos, iblockstate)
                      : iblockstate.getBlock()
                          .getExplosionResistance(world, blockpos, null, this);
                  f -= (f2 + 0.3F) * 0.3F;
                }

                if (f > 0.0F && (explosive == null || explosive
                    .verifyExplosion(this, world, blockpos, iblockstate, f))) {
                  hashset.add(blockpos);
                }

                d4 += d0 * 0.30000001192092896D;
                d6 += d1 * 0.30000001192092896D;
                d8 += d2 * 0.30000001192092896D;
              }
            }
          }
        }
      }
    }

    this.affectedBlockPositions.addAll(hashset);
    float diameter = this.radius * 2.0F;
    j = MathHelper.floor_double(this.x - (double) diameter - 1.0D);
    k = MathHelper.floor_double(this.x + (double) diameter + 1.0D);
    int j1 = MathHelper.floor_double(this.y - (double) diameter - 1.0D);
    int l = MathHelper.floor_double(this.y + (double) diameter + 1.0D);
    int k1 = MathHelper.floor_double(this.z - (double) diameter - 1.0D);
    int i1 = MathHelper.floor_double(this.z + (double) diameter + 1.0D);
    List list = this.world.getEntitiesWithinAABBExcludingEntity(explosive,
        new AxisAlignedBB(j, j1, k1, k, l,
            i1));
    net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.world, this, list, diameter);
    Vec3 vec3 = new Vec3(x, y, z);

    for (int l1 = 0; l1 < list.size(); ++l1) {
      Entity entity = (Entity) list.get(l1);

      if (!entity.isImmuneToExplosions()) {
        double relDist = entity.getDistance(x, y, z) / (double) diameter;

        if (relDist <= 1.0D || entity instanceof EntityDriveable && relDist <= 2) {
          double d5 = entity.posX - x;
          double d7 = entity.posY + (double) entity.getEyeHeight() - y;
          double d9 = entity.posZ - z;
          double d13 = MathHelper.sqrt_double(d5 * d5 + d7 * d7 + d9 * d9);

          if (d13 != 0.0D) {
            d5 /= d13;
            d7 /= d13;
            d9 /= d13;
            double exposure = getBlockDensity(vec3, entity.getEntityBoundingBox());
            double d10 = (1.0D - relDist) * exposure;
            float dmg = (float) ((int) ((d10 * d10 + d10) / 2.0D * 8.0D * (double) diameter + 1.0D));
            if (entity instanceof EntityDriveable) {
              EntityDriveable driveable = (EntityDriveable) entity;
              FlansModRaytracer.DriveableHit hit = (FlansModRaytracer.DriveableHit) driveable.getClosestPart(new Vector3f(position));
              if (hit != null) {
                float dist = hit.intersectTime;
                relDist = dist/diameter;

                if (relDist <= 1) {
                  d10 = (float) (relDist);
                  //-(x-1)^3
                  d10 -= 1;
                  dmg = (float) -(d10*d10*d10);
                  dmg *= type.explosionDamageVsDriveable;
                  driveable.attackPart(hit.part, new EntityDamageSourceGun(type.shortName, explosive, detonator, type, false), dmg);
                }
              }

            } else {
                           /* dmg *= 1.2f;
                            dmg *= entityDamageModifier;
                            if (entity instanceof EntityPlayer) {
                                float red = getArmorDamageReduction((EntityPlayer) entity);
                                //   System.out.println("Red: " + red);
                                dmg *= red;
                            }*/
              dmg *= type.explosionDamageVsLiving;
              entity.attackEntityFrom(
                      new EntityDamageSourceGun(type.shortName, explosive, detonator, type, false), dmg);
            }
            //   System.out.println("Damgage: " + dmg);

            double d11 = EnchantmentProtection.func_92092_a(entity, d10);
            sendHitmarker = sendHitmarker || entity != detonator && dmg > 0.1 && (entity instanceof EntityLivingBase || entity instanceof EntityDriveable || entity instanceof EntityAAGun);

            if (!(entity instanceof EntityDriveable || entity instanceof EntitySeat || entity instanceof EntityWheel || entity instanceof EntityItem || entity instanceof EntityGrenade)) {
              d11 /= 2;

              entity.motionX = Math.min(1, entity.motionX + d5 * d11);
              entity.motionY = Math.min(1, entity.motionY + d7 * d11);
              entity.motionZ = Math.min(1, entity.motionZ + d9 * d11);

              if (entity instanceof EntityPlayer) {
                this.playerMap.put(entity, new Vec3(d5 * d10, d7 * d10, d9 * d10));
              }
            }
          }
        }
      }
    }
    if (sendHitmarker && explosive instanceof EntityShootable) {
      EntityShootable bullet = (EntityShootable) explosive;
      if (!bullet.sentHitMarker) {
        bullet.sendHitMarker(HitMarkType.INDIRECT);
      }
    }
  }
  public float getBlockDensity(Vec3 vec, AxisAlignedBB bb) {
    double d0 = 1.0D / ((bb.maxX - bb.minX) * 2.0D + 1.0D);
    double d1 = 1.0D / ((bb.maxY - bb.minY) * 2.0D + 1.0D);
    double d2 = 1.0D / ((bb.maxZ - bb.minZ) * 2.0D + 1.0D);
    double d3 = (1.0D - Math.floor(1.0D / d0) * d0) / 2.0D;
    double d4 = (1.0D - Math.floor(1.0D / d2) * d2) / 2.0D;
    if (d0 >= 0.0D && d1 >= 0.0D && d2 >= 0.0D) {
      float unblockedRays = 0;
      int totalRays = 0;

      for(float f = 0.0F; f <= 1.0F; f = (float)((double)f + d0)) {
        for(float f1 = 0.0F; f1 <= 1.0F; f1 = (float)((double)f1 + d1)) {
          for(float f2 = 0.0F; f2 <= 1.0F; f2 = (float)((double)f2 + d2)) {
            double d5 = bb.minX + (bb.maxX - bb.minX) * (double)f;
            double d6 = bb.minY + (bb.maxY - bb.minY) * (double)f1;
            double d7 = bb.minZ + (bb.maxZ - bb.minZ) * (double) f2;
            List<MovingObjectPosition> pos = FlansModRaytracer.rayTraceBlocks(new Vec3(d5 + d3, d6, d7 + d4), vec, true, true, false, world, 5);
            if (pos == null || pos.isEmpty()) {
              ++unblockedRays;
            } else {
              float strength = radius*3;
              float originalStrength = strength;
              for (MovingObjectPosition p : pos) {
                if (strength <= 0) {
                  strength = 0;
                  break;
                }
                BlockPos b  = p.getBlockPos();
                strength -= world.getBlockState(b).getBlock().getExplosionResistance(world, b, detonator, this);
              }
              unblockedRays += strength / originalStrength;
            }

            ++totalRays;
          }
        }
      }

      return (float)unblockedRays / (float)totalRays;
    } else {
      return 0.0F;
    }
  }

  private float getArmorDamageReduction(EntityPlayer entity) {
    float armorPower = 0;
    for (int i = 0; i < 4; i++) {
      ItemStack stack = entity.getCurrentArmor(i);
      if (stack == null) {
        continue;
      }
      Item item = stack.getItem();

      if (item instanceof ItemTeamArmour && ((ItemTeamArmour) item).type != null) {
        ItemTeamArmour armour = (ItemTeamArmour) item;
        armorPower += (float) armour.type.defence * 5 * (1
            + PlayerHitbox.getArmorPlateStopPower(armour.type.plateLevel) / 100f);
      }
    }

    PlayerData playerData = PlayerHandler.getPlayerData(entity);
    ItemStack itemStack = entity.getHeldItem();
    if (itemStack != null && itemStack.getItem() instanceof ItemGun) {
      GunType type = ((ItemGun) itemStack.getItem()).GetType();
      if (type.shield) {
        Vector3f dir = Vector3f
            .sub(new Vector3f(entity.posX + 0.5f, entity.posY + 1, entity.posZ + 0.5f),
                new Vector3f(position), null);
        ArrayList<FlansModRaytracer.BulletHit> hits = playerData.snapshots[0]
            .raytrace(new Vector3f(position), dir);

        float angle = Vector3f.angle(dir, new Vector3f(entity.getLookVec()));
        if (angle > 3.41f / 4 * 3) {
          armorPower += type.shieldDamageAbsorption;
        }

      }
    }

    if (armorPower == 0) {
      return 1;
    }

    //    armorPower *= 0.08f;
    armorPower *= 0.02f;


    armorPower = Math.max(1, armorPower);

    return 1f / armorPower;
  }

  /**
   * Second part of the explosion (sound, particles, drop spawn)
   */
  public void doExplosionB(boolean p_77279_1_) {
    String exploType = "explosionTiny";
    if (this.radius > 1) {
      exploType = "explosionSmall";
      if (this.radius > 2) {
        exploType = "explosionMedium";
        if (this.radius >= 4) {
          exploType = "explosionBig";
          if (this.radius >= 6) {
            exploType = "explosionHuge";
          }
        }
      }
    }
    if (explosive instanceof EntityDriveable) {
      exploType = "explosionVehicle";
    }
    int chunkRange = (int) (8 + this.radius*2f);

    if (explosive != null) {
      PacketPlaySound.sendAdvancedSound(explosive, "sound_" + exploType, chunkRange,
          (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
    } else {
      this.world.playSoundEffect(this.x, this.y, this.z, "sound_" + exploType, chunkRange,
          (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
    }

    if (this.isSmoking) {
      this.world
          .spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D
          );
    } else {
      this.world
          .spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.x, this.y, this.z, 1.0D, 0.0D,
              0.0D);
    }

    Iterator iterator;
    BlockPos blockpos;

    if (isSmoking) {
      iterator = this.affectedBlockPositions.iterator();

      while (iterator.hasNext()) {
        blockpos = (BlockPos) iterator.next();
        Block block = this.world.getBlockState(blockpos).getBlock();

        if (p_77279_1_) {
          double d0 = (float) blockpos.getX() + this.world.rand.nextFloat();
          double d1 = (float) blockpos.getY() + this.world.rand.nextFloat();
          double d2 = (float) blockpos.getZ() + this.world.rand.nextFloat();
          double d3 = d0 - this.x;
          double d4 = d1 - this.y;
          double d5 = d2 - this.z;
          double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
          d3 /= d6;
          d4 /= d6;
          d5 /= d6;
          double d7 = 0.5D / (d6 / (double) this.radius + 0.1D);
          d7 *= this.world.rand.nextFloat() * this.world.rand.nextFloat() + 0.3F;
          d3 *= d7;
          d4 *= d7;
          d5 *= d7;
          this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.x * 1.0D) / 2.0D,
              (d1 + this.y * 1.0D) / 2.0D, (d2 + this.z * 1.0D) / 2.0D, d3, d4, d5);
          this.world
              .spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5);
        }

        if (block.getMaterial() != Material.air) {
          if (block.canDropFromExplosion(this)) {
            block
                .dropBlockAsItemWithChance(this.world, blockpos, this.world.getBlockState(blockpos),
                    1.0F / this.radius, 0);
          }

          block.onBlockExploded(this.world, blockpos, this);
        }
      }
    }

    if (this.isFlaming) {
      iterator = this.affectedBlockPositions.iterator();

      while (iterator.hasNext()) {
        blockpos = (BlockPos) iterator.next();

        if (this.world.getBlockState(blockpos).getBlock().getMaterial() == Material.air
            && this.world.getBlockState(blockpos.down()).getBlock().isFullBlock()
            && this.explosionRNG.nextInt(3) == 0) {
          this.world.setBlockState(blockpos, Blocks.fire.getDefaultState());
        }
      }
    }
  }

  @Override
  public Map getPlayerKnockbackMap() {
    return this.playerMap;
  }

  /**
   * Returns either the entity that placed the explosive block, the entity that caused the explosion
   * or null.
   */
  @Override
  public EntityLivingBase getExplosivePlacedBy() {
    return detonator;
  }

  @Override
  public void func_180342_d() {
    this.affectedBlockPositions.clear();
  }

  @Override
  public List getAffectedBlockPositions() {
    return this.affectedBlockPositions;
  }

  @Override
  public Vec3 getPosition() {
    return this.position;
  }
}
