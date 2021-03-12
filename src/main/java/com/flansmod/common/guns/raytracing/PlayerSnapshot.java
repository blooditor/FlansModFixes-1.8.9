package com.flansmod.common.guns.raytracing;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.BulletHit;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.PlayerBulletHit;
import com.flansmod.common.vector.Vector3f;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class takes a snapshot of the player's position rotation and held items at a certain point
 * in time. It is used to handle bullet detection. The server will store a second or two of
 * snapshots so that it can work out where the player thought they were shooting accounting for
 * packet lag
 */
public class PlayerSnapshot {

  /**
   * The player this snapshot is for
   */
  public EntityPlayer player;
  /**
   * The player's position at the point the snapshot was taken
   */
  public Vector3f pos;
  /**
   * The hitboxes for this player
   */
  public ArrayList<PlayerHitbox> hitboxes;
  /**
   * The time at which this snapshot was taken
   */
  public long time;

  public PlayerSnapshot(EntityPlayer p) {
    player = p;
    pos = new Vector3f(p.posX, p.posY, p.posZ);
    //if(FlansMod.proxy.isThePlayer(p))
    //	pos = new Vector3f(p.posX, p.posY - 1.6F, p.posZ);
    hitboxes = new ArrayList<PlayerHitbox>();

    RotatedAxes bodyAxes = new RotatedAxes(p.renderYawOffset, 0F, 0F);
    RotatedAxes headAxes = new RotatedAxes(p.rotationYawHead - p.renderYawOffset + 90, 0F,
        -p.rotationPitch);
    Vector3f bodyBox = new Vector3f(0.5F, 0.67F, 0.3F);
    Vector3f bodyPos = new Vector3f(-0.25F, 0.75F, -0.15F);

    Vector3f headPos = new Vector3f(-0.25F, 0F, -0.25F);
    Vector3f headBox = new Vector3f(0.5F, 0.5F, 0.5F);

    Vector3f legPos = new Vector3f(-0.25F, 0F, -0.15F);
    Vector3f legBox = new Vector3f(0.5F, 0.75F, 0.3F);

    float armOffset = 0;

    if (p.isSneaking()) {
      headPos.y -= 0.25f;
      bodyPos.y -= 0.25f;
      bodyPos.z -= 0.2f;
      legPos.z -= 0.25f;
      legBox.y -= 0.2f;
      armOffset = -0.22f;
    }
    //body
    hitboxes.add(new PlayerHitbox(player, bodyAxes, new Vector3f(0F, 0F, 0F), bodyPos, bodyBox,
        EnumHitboxType.BODY));

    hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(headAxes),
        new Vector3f(0.0F, 1.4F, 0F), headPos, headBox, EnumHitboxType.HEAD));

    //legs TODO
    hitboxes.add(new PlayerHitbox(player, bodyAxes, new Vector3f(0F, 0F, 0F), legPos, legBox,
        EnumHitboxType.LEGS));

    //Calculate rotation of arms using modified code from ModelBiped
    if (p.getHeldItem() != null && p.getHeldItem().getItem() instanceof ItemGun) {
      float yHead = (p.rotationYawHead - p.renderYawOffset - 90) / (180F / (float) Math.PI);
      float xHead = p.rotationPitch / (180F / (float) Math.PI);

      float zRight = 0.0F;
      float zLeft = 0.0F;
      float yRight = -0.1F + yHead - ((float) Math.PI / 2F);
      float yLeft = 0.1F + yHead + 0.4F - ((float) Math.PI / 2F);
      float xRight = -((float) Math.PI / 2F) + xHead;
      float xLeft = -((float) Math.PI / 2F) + xHead;

      RotatedAxes leftArmAxes = (new RotatedAxes()).rotateGlobalPitchInRads(xLeft)
          .rotateGlobalYawInRads((float) Math.PI + yLeft).rotateGlobalRollInRads(-zLeft);
      RotatedAxes rightArmAxes = (new RotatedAxes()).rotateGlobalPitchInRads(xRight)
          .rotateGlobalYawInRads((float) Math.PI + yRight).rotateGlobalRollInRads(-zRight);

      float originZRight = MathHelper.sin(-p.renderYawOffset * 3.14159265F / 180F) * 5.0F / 16F;
      float originXRight = -MathHelper.cos(-p.renderYawOffset * 3.14159265F / 180F) * 5.0F / 16F;

      float originZLeft = -MathHelper.sin(-p.renderYawOffset * 3.14159265F / 180F) * 5.0F / 16F;
      float originXLeft = MathHelper.cos(-p.renderYawOffset * 3.14159265F / 180F) * 5.0F / 16F;

      hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(leftArmAxes),
          new Vector3f(originXLeft, 1.3F + armOffset, originZLeft),
          new Vector3f(-2F / 16F, -0.6F, -2F / 16F), new Vector3f(0.25F, 0.7F, 0.25F),
          EnumHitboxType.LEFTARM));

      hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(rightArmAxes),
          new Vector3f(originXRight, 1.3F + armOffset, originZRight),
          new Vector3f(-2F / 16F, -0.6F, -2F / 16F), new Vector3f(0.25F, 0.7F, 0.25F),
          EnumHitboxType.RIGHTARM));

      //Add box for right hand shield
      ItemStack playerRightHandStack = player.getCurrentEquippedItem();
      if (playerRightHandStack != null && playerRightHandStack.getItem() instanceof ItemGun) {
        GunType gunType = ((ItemGun) playerRightHandStack.getItem()).GetType();
        if (gunType.shield) {
          //for some reason the raytracing sees a different box than the real one (bug in findGlobalAxesLocally?)
          //this is the correct axes to make raytracing work. The hitbox shown in debug correctly would be:
          //hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(rightArmAxes), new Vector3f(originXRight, 1.3F, originZRight), new Vector3f(gunType.shieldOrigin.z, -1.05F + gunType.shieldOrigin.x, -1F / 16F + gunType.shieldOrigin.y), new Vector3f(gunType.shieldDimensions.z, gunType.shieldDimensions.x, gunType.shieldDimensions.y), EnumHitboxType.RIGHTITEM));
          RotatedAxes ax = bodyAxes.findLocalAxesGlobally(rightArmAxes);
          ax.rotateGlobalYaw(90);
          hitboxes.add(new PlayerHitbox(player, ax, new Vector3f(originXRight, 1.3F, originZRight),
              new Vector3f(gunType.shieldOrigin.y, -1.05F + gunType.shieldOrigin.x,
                  -1F / 16F + gunType.shieldOrigin.z),
              new Vector3f(gunType.shieldDimensions.y, gunType.shieldDimensions.x,
                  gunType.shieldDimensions.z), EnumHitboxType.RIGHTITEM));
        }

        //Add left hand shield box
        //not used and not working
        PlayerData data = PlayerHandler.getPlayerData(player);
        if (false && gunType.oneHanded && data.offHandGunSlot != 0) {
          ItemStack leftHandStack = null;
          //Client side other players
          if (player.worldObj.isRemote && !FlansMod.proxy.isThePlayer(player)) {
            leftHandStack = data.offHandGunStack;
          } else {
            leftHandStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
          }

          if (leftHandStack != null && leftHandStack.getItem() instanceof ItemGun) {
            GunType leftGunType = ((ItemGun) leftHandStack.getItem()).GetType();
            if (leftGunType.shield) {
              hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(leftArmAxes),
                  new Vector3f(originXLeft, 1.3F, originZLeft),
                  new Vector3f(leftGunType.shieldOrigin.z, -1.05F + leftGunType.shieldOrigin.x,
                      -1F / 16F + leftGunType.shieldOrigin.y),
                  new Vector3f(leftGunType.shieldDimensions.z, leftGunType.shieldDimensions.x,
                      leftGunType.shieldDimensions.y), EnumHitboxType.LEFTITEM));
            }
          }
        }
      }
    } else {
      // hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(leftArmAxes), new Vector3f(originXLeft, 1.3F, originZLeft), new Vector3f(-2F / 16F, -0.6F, -2F / 16F), new Vector3f(0.25F, 0.7F, 0.25F), EnumHitboxType.LEFTARM));

      RotatedAxes armAxes = new RotatedAxes(p.renderYawOffset, 0F, 0F);

      hitboxes.add(new PlayerHitbox(player, armAxes, new Vector3f(0F, 0F, 0F),
          new Vector3f(-0.44F, 0.65F + armOffset, -0.12F), new Vector3f(0.23F, 0.74F, 0.23F),
          EnumHitboxType.RIGHTARM));
      hitboxes.add(new PlayerHitbox(player, armAxes, new Vector3f(0F, 0F, 0F),
          new Vector3f(0.24F, 0.65F + armOffset, -0.12F), new Vector3f(0.23F, 0.74F, 0.23F),
          EnumHitboxType.LEFTARM));

    }
  }

  public ArrayList<BulletHit> raytrace(Vector3f origin, Vector3f motion) {
    //Get the bullet raytrace vector into local coordinates
    Vector3f localOrigin = Vector3f.sub(origin, pos, null);
    //Prepare a list for the hits
    ArrayList<BulletHit> hits = new ArrayList<BulletHit>();

    //Check each hitbox for a hit
    for (PlayerHitbox hitbox : hitboxes) {
      PlayerBulletHit hit = hitbox.raytrace(localOrigin, motion);
      if (hit != null && hit.intersectTime >= 0F && hit.intersectTime <= 1F) {
        hit.hitPos = Vector3f.add(origin,
            new Vector3f(motion.x * hit.intersectTime, motion.y * hit.intersectTime,
                motion.z * hit.intersectTime), null);
        hits.add(hit);
      }
    }
    return hits;
  }

  @SideOnly(Side.CLIENT)
  public void renderSnapshot() {
    for (PlayerHitbox hitbox : hitboxes) {
      hitbox.renderHitbox(player.worldObj, pos);
    }
  }

  public PlayerHitbox GetHitbox(EnumHitboxType type) {
    for (PlayerHitbox hitbox : hitboxes) {
      if (hitbox.type == type) {
        return hitbox;
      }
    }
    return null;
  }

  public Vector3f GetMuzzleLocation(GunType gunType, AttachmentType barrelAttachment,
      boolean isOffHand) {
    PlayerHitbox hitbox = GetHitbox(isOffHand ? EnumHitboxType.LEFTARM : EnumHitboxType.RIGHTARM);
    Vector3f muzzlePos = new Vector3f(hitbox.o.x, hitbox.o.y + hitbox.d.y * 0.5f,
        hitbox.o.z + hitbox.d.z * 0.5f);

    if (gunType != null && gunType.model != null) {
      Vector3f barrelAttach = new Vector3f(
          gunType.model.barrelAttachPoint.z,
          -gunType.model.barrelAttachPoint.x,
          gunType.model.barrelAttachPoint.y);
      Vector3f.add(muzzlePos, barrelAttach, muzzlePos);
    }

    // something is wrong here	Vector3f.add(muzzlePos, new Vector3f(0.35f, 1.3, 0), muzzlePos);

    muzzlePos = hitbox.axes.findLocalVectorGlobally(muzzlePos);

    Vector3f.add(muzzlePos, hitbox.rP, muzzlePos);
    return muzzlePos;
  }
}
