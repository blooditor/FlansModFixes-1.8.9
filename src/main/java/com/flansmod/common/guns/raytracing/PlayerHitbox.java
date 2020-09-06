package com.flansmod.common.guns.raytracing;

import com.flansmod.client.debug.EntityDebugAABB;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.PlayerBulletHit;
import com.flansmod.common.teams.ItemTeamArmour;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerHitbox {

  /**
   *
   */
  public EntityPlayer player;
  /**
   * The angles of this box
   */
  public RotatedAxes axes;
  /**
   * The origin of rotation for this box
   */
  public Vector3f rP;
  /**
   * The lower left corner of this box
   */
  public Vector3f o;
  /**
   * The dimensions of this box
   */
  public Vector3f d;
  /**
   * The type of hitbox
   */
  public EnumHitboxType type;

  public PlayerHitbox(EntityPlayer player, RotatedAxes axes, Vector3f rotationPoint,
      Vector3f origin, Vector3f dimensions, EnumHitboxType type) {
    this.player = player;
    this.axes = axes;
    this.o = origin;
    this.d = dimensions;
    this.type = type;
    this.rP = rotationPoint;
  }

  @SideOnly(Side.CLIENT)
  public void renderHitbox(World world, Vector3f pos) {

    Vector3f boxOrigin = new Vector3f(pos.x + rP.x, pos.y + rP.y, pos.z + rP.z);
    world.spawnEntityInWorld(
        new EntityDebugAABB(world, boxOrigin, d, 2, 1F, 1F, 0F, axes.getYaw(), axes.getPitch(),
            axes.getRoll(), o));
/*		if(type != EnumHitboxType.RIGHTARM)
			return;
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				for(int k = 0; k < 3; k++)
				{
				//	Vector3f point = new Vector3f(o.x + d.x * i / 2, o.y + d.y * j / 2, o.z + d.z * k / 2);
				//	point = axes.findLocalVectorGlobally(point);
				//TODO	if(FlansMod.DEBUG && world.isRemote)
				//		world.spawnEntityInWorld(new EntityDebugDot(world, new Vector3f(pos.x + rP.x + point.x, pos.y + rP.y + point.y, pos.z + rP.z + point.z), 1, 0F, 1F, 0F));
				}
				*/

  }

  public PlayerBulletHit raytrace(Vector3f origin, Vector3f motion) {
    //Move to local coords for this hitbox, but don't modify the original "origin" vector
    origin = Vector3f.sub(origin, rP, null);
    origin = axes.findGlobalVectorLocally(origin);
    motion = axes.findGlobalVectorLocally(motion);

    //We now have an AABB starting at o and with dimensions d and our ray in the same coordinate system
    //We are looking for a point at which the ray enters the box, so we need only consider faces that the ray can see. Partition the space into 3 areas in each axis

    //X - axis and faces x = o.x and x = o.x + d.x
    if (motion.x != 0F) {
      if (origin.x < o.x) //Check face x = o.x
      {
        float intersectTime = (o.x - origin.x) / motion.x;
        float intersectY = origin.y + motion.y * intersectTime;
        float intersectZ = origin.z + motion.z * intersectTime;
        if (intersectY >= o.y && intersectY <= o.y + d.y && intersectZ >= o.z
            && intersectZ <= o.z + d.z) {
          return new PlayerBulletHit(this, intersectTime,
              new Vector3f(o.x - origin.x, intersectY, intersectZ));
        }
      } else if (origin.x > o.x + d.x) //Check face x = o.x + d.x
      {
        float intersectTime = (o.x + d.x - origin.x) / motion.x;
        float intersectY = origin.y + motion.y * intersectTime;
        float intersectZ = origin.z + motion.z * intersectTime;
        if (intersectY >= o.y && intersectY <= o.y + d.y && intersectZ >= o.z
            && intersectZ <= o.z + d.z) {
          return new PlayerBulletHit(this, intersectTime,
              new Vector3f(o.x + d.x - origin.x, intersectY, intersectZ));
        }
      }
    }

    //Z - axis and faces z = o.z and z = o.z + d.z
    if (motion.z != 0F) {
      if (origin.z < o.z) //Check face z = o.z
      {
        float intersectTime = (o.z - origin.z) / motion.z;
        float intersectX = origin.x + motion.x * intersectTime;
        float intersectY = origin.y + motion.y * intersectTime;
        if (intersectX >= o.x && intersectX <= o.x + d.x && intersectY >= o.y
            && intersectY <= o.y + d.y) {
          return new PlayerBulletHit(this, intersectTime,
              new Vector3f(intersectX, intersectY, o.z - origin.z));
        }
      } else if (origin.z > o.z + d.z) //Check face z = o.z + d.z
      {
        float intersectTime = (o.z + d.z - origin.z) / motion.z;
        float intersectX = origin.x + motion.x * intersectTime;
        float intersectY = origin.y + motion.y * intersectTime;
        if (intersectX >= o.x && intersectX <= o.x + d.x && intersectY >= o.y
            && intersectY <= o.y + d.y) {
          return new PlayerBulletHit(this, intersectTime,
              new Vector3f(intersectX, intersectY, o.z + d.z - origin.z));
        }
      }
    }

    //Y - axis and faces y = o.y and y = o.y + d.y
    if (motion.y != 0F) {
      if (origin.y < o.y) //Check face y = o.y
      {
        float intersectTime = (o.y - origin.y) / motion.y;
        float intersectX = origin.x + motion.x * intersectTime;
        float intersectZ = origin.z + motion.z * intersectTime;
        if (intersectX >= o.x && intersectX <= o.x + d.x && intersectZ >= o.z
            && intersectZ <= o.z + d.z) {
          return new PlayerBulletHit(this, intersectTime,
              new Vector3f(intersectX, o.y - origin.y, intersectZ));
        }
      } else if (origin.y > o.y + d.y) //Check face x = o.x + d.x
      {
        float intersectTime = (o.y + d.y - origin.y) / motion.y;
        float intersectX = origin.x + motion.x * intersectTime;
        float intersectZ = origin.z + motion.z * intersectTime;
        if (intersectX >= o.x && intersectX <= o.x + d.x && intersectZ >= o.z
            && intersectZ <= o.z + d.z) {
          return new PlayerBulletHit(this, intersectTime,
              new Vector3f(intersectX, o.y + d.y - origin.y, intersectZ));
        }
      }
    }

    return null;
  }

  public float hitByBullet(Entity damageOwner, InfoType firedFrom, BulletType bulletType,
      float damage, float penetratingPower, EntityBullet bullet, float speed) {
    //no multiple armor damage when hitting multiple body parts
    float indirectProtection = bullet.appliedDamage.containsKey(player) ? 1
        : hitArmorByBullet(damageOwner, firedFrom, bulletType, damage, penetratingPower, bullet,
            speed);
    float newSpeed = bullet.getBulletSpeed();

    boolean penetratedArmor = newSpeed > 0;
    if (!penetratedArmor) {
      //   useArmorDefence = true;
      damage = Math.min(damage,
          damage * indirectProtection * bulletType.ammoType.getNonPenetrationDamageModifier());
      EntityBullet.log("Stopped by armor dealt " + damage + " prot " + indirectProtection);
      // if the armor wasnt penetrated, still deal some damage
    } else {

      speed = newSpeed;
      penetratingPower = speed * bulletType.ammoType.getPenetration();
      damage = Math
          .max(damage * indirectProtection * bulletType.ammoType.getNonPenetrationDamageModifier(),
              (speed / bullet.originalSpeed) * damage);

      //   if (!bullet.appliedDamage.containsKey(player))
      //       bullet.doEntityPenetration(bulletType, speed, player, 20, penetratingPower);

      if (!player.worldObj.isRemote) {
        if (bulletType.setEntitiesOnFire) {
          player.setFire(20);
        }
        for (PotionEffect effect : bulletType.hitEffects) {
          player.addPotionEffect(new PotionEffect(effect));
        }
      }

    }

    switch (type) {
      case HEAD:
        damage *= 1.6F;
        break;
      case LEFTARM:
      case RIGHTARM:
      case LEGS:
        damage *= 0.6F;
        break;
      case RIGHTITEM:
      case LEFTITEM:
        return 0;
    }

    //dont deal more damage on multiple body parts hit
    if (bullet.appliedDamage.containsKey(player)) {
      damage -= bullet.appliedDamage.get(player);
      if (damage < 0) {
        damage = 0;
      }
    }

    switch (type) {
      case BODY:
      case HEAD:
      case LEGS:
      case LEFTARM:
      case RIGHTARM: {
        //Calculate the hit damage

        if (player.worldObj.isRemote) {
          return damage;
        }

        EntityBullet.log("Hit damage: " + damage);
        //Create a damage source object
        DamageSource damagesource = damageOwner == null ? DamageSource.generic
            : EntityBullet
                .GetBulletDamage(firedFrom, bulletType, damageOwner, type == EnumHitboxType.HEAD);

        //When the damage is 0 (such as with Nerf guns) the entityHurt Forge hook is not called, so this hacky thing is here
        if (!player.worldObj.isRemote && damage == 0
            && TeamsManager.getInstance().currentRound != null) {
          TeamsManager.getInstance().currentRound.gametype
              .playerAttacked((EntityPlayerMP) player, damagesource);
        }

        //Attack the entity!
        if (player.attackEntityFrom(damagesource, damage)) {
          //If the attack was allowed, we should remove their immortality cooldown so we can shoot them again. Without this, any rapid fire gun become useless
          player.arrowHitTimer++;
          player.hurtResistantTime = player.maxHurtResistantTime / 2;
          //Yuck.
          //PacketDispatcher.sendPacketToAllAround(posX, posY, posZ, 50, dimension, PacketPlaySound.buildSoundPacket(posX, posY, posZ, type.hitSound, true));
        }
        if (penetratedArmor && !bullet.appliedDamage.containsKey(player)) {
          bullet.doEntityPenetration(bulletType, speed, player, 20, penetratingPower);
        }
        return damage;
      }
    }
    return 0;
  }

  public float hitArmorByBullet(Entity damageOwner, InfoType firedFrom, BulletType bulletType,
      float damage, float penetratingPower, EntityBullet entityBullet, float speed) {

    int slot = 0;
    ItemStack armor = null;
    switch (type) {
      case HEAD:
        armor = player.getCurrentArmor(3);
        slot = 3 + player.inventory.getSizeInventory() - 5;
        break;
      case BODY:
      case LEFTARM:
      case RIGHTARM:
        armor = player.getCurrentArmor(2);
        slot = 2 + player.inventory.getSizeInventory() - 5;
        break;
      case LEGS:
        armor = player.getCurrentArmor(1);
        slot = 1 + player.inventory.getSizeInventory() - 5;
        break;
      case RIGHTITEM:
        armor = player.getCurrentEquippedItem();
        slot = player.inventory.currentItem;
        break;
      case LEFTITEM:
        PlayerData data = PlayerHandler.getPlayerData(player);
        if (data.offHandGunSlot != 0) {
          armor = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
          slot = data.offHandGunSlot - 1;
        }
        break;
    }

    if (armor == null || !(armor.getItem() instanceof ItemTeamArmour || armor
        .getItem() instanceof ItemGun || armor.getItem() instanceof ItemArmor)) {
      return 1;
    }

    float indirectProtection = 0.25f;

    float stopPower = 0;
    Item item = armor.getItem();

    if (item instanceof ItemGun) {
      GunType type = ((ItemGun) item).GetType();
      if (type.shield) {
        indirectProtection = 0;
        stopPower = type.shieldDamageAbsorption;
      } else {
        return 0;
      }
    } else if (item instanceof ItemTeamArmour) {
      ItemTeamArmour armour = (ItemTeamArmour) item;
      stopPower = (float) armour.type.defence * 10 + getArmorPlateStopPower(armour.type.plateLevel);
      indirectProtection *= 1 - getArmorPlateIndirectProtection(armour.type.plateLevel);
    } else if (item instanceof ItemArmor) {
      indirectProtection = 1;
      stopPower = ((ItemArmor) item).damageReduceAmount * 0.5f;
    }

    //apply armor health modifier
    //TODO might not work if the player doesnt know enemies armor damage values
   /*     if (item instanceof ItemArmor && item.getMaxDamage() > 0) {
            float stopModifier = 1 - (float) armor.getItemDamage() / armor.getMaxDamage();
            stopModifier = Math.max(0.35f, stopModifier);
            if(stopModifier > 0.8f)
                stopModifier = 1;
            stopPower *= stopModifier;
        }*/

    EntityBullet.log("Armor stop power " + stopPower);

    if (!player.worldObj.isRemote) {
      //damage armor
      float armorDamage = 0.04f * penetratingPower + 0.005f * damage;
      //more dmg to shield
      if (item instanceof ItemTeamArmour) {
        armorDamage *= Math.max(0.1f, 0.5f - ((ItemTeamArmour) item).type.defence);
      }
      armorDamage = Math.max(1, armorDamage);
      armor.damageItem((int) (armorDamage), player);
      if (armor.getItemDamage() >= armor.getMaxDamage()) {
        armor.stackSize--;
        player.playSound("random.break", 1, 1);
        if (armor.stackSize <= 0) {
          player.inventory.setInventorySlotContents(slot, null);
        }
      }
    }
    entityBullet.applyPenetrationSpeedLoss(stopPower, speed, penetratingPower);
    return indirectProtection;
  }

  public static float getArmorPlateStopPower(int plateLevel) {
    switch (plateLevel) {
      case 1:
        return 3;
      case 2:
        return 12;
      case 3:
        return 27;
      case 4:
        return 40;
      case 5:
        return 50;
    }
    return 0;
  }

  //how much the armor reduces the damage a bullet causes when it didnt penetrate
  public static float getArmorPlateIndirectProtection(int plateLevel) {
    switch (plateLevel) {
      case 1:
        return 0f;
      case 2:
        return 0.05f;
      case 3:
        return 0.2f;
      case 4:
        return 0.45f;
      case 5:
        return 0.7f;
    }
    return 0;
  }
}
