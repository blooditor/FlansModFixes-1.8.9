package com.flansmod.common;

import com.flansmod.client.FlansModClient;
import com.flansmod.client.ReloadSoundAtEntity;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.guns.EntityMG;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.raytracing.PlayerSnapshot;
import com.flansmod.common.network.PacketReload;
import com.flansmod.common.network.PacketSelectOffHandGun;
import com.flansmod.common.teams.IPlayerClass;
import com.flansmod.common.teams.Team;
import com.flansmod.common.vector.Vector3f;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import paulscode.sound.SoundSystem;

public class PlayerData {

  /**
   * Their username
   */
  public String username;

  //Movement related fields
  /**
   * Roll variables
   */
  public float prevRotationRoll, rotationRoll;
  /**
   * Snapshots for bullet hit detection. Array size is set to number of snapshots required. When a new one is taken,
   * each snapshot is moved along one place and new one is added at the start, so that when the array fills up, the
   * oldest one is lost
   */
  public PlayerSnapshot[] snapshots;

  //Gun related fields
  /**
   * The slotID of the gun being used by the off-hand. 0 = no slot. 1 ~ 9 = hotbar slots
   */
  public int offHandGunSlot = 0;
  /**
   * The off hand gun stack. For viewing other player's off hand weapons only (since you don't know what is in their
   * inventory and hence just the ID is insufficient)
   */
  @SideOnly(Side.CLIENT)
  public ItemStack offHandGunStack;

  @SideOnly(Side.CLIENT)
  public
  ReloadSoundAtEntity reloadSound;

  @SideOnly(Side.CLIENT)
  public int previousSlot;
  /**
   * The MG this player is using
   */
  public EntityMG mountingGun;
  /**
   * Stops player shooting immediately after swapping weapons
   */
  public int shootClickDelay;
  /**
   * The speed of the minigun the player is using
   */
  public float minigunSpeed = 0F;
  /**
   * When remote explosives are thrown they are added to this list. When the player uses a remote, the first one from
   * this list detonates
   */
  public ArrayList<EntityGrenade> remoteExplosives = new ArrayList<EntityGrenade>();
  /**
   * Sound delay parameters
   */
  public int loopedSoundDelay;
  /**
   * Sound delay parameters
   */
  public boolean shouldPlayCooldownSound, shouldPlayWarmupSound;
  /**
   * Melee weapon custom hit simulation
   */
  public int meleeProgress, meleeLength;

  /**
   * Tickers to stop shooting too fast
   */
  public float shootTimeRight, shootTimeLeft;
  /**
   * True if this player is shooting
   */
  public boolean isShootingRight, isShootingLeft;
  /**
   * Reloading booleans
   */
  public int reloadingRight, reloadingLeft;
  /**
   * When the player shoots a burst fire weapon, one shot is fired immediately and this counter keeps track of how many
   * more should be fired
   */
  public int burstRoundsRemainingLeft = 0, burstRoundsRemainingRight = 0;
  public Vector3f[] lastMeleePositions;
  /**
   * Gametype variables
   */
  public int score, kills, deaths;
  /**
   * Zombies variables
   */
  public int zombieScore;
  /**
   * Gametype variable for Nerf
   */
  public boolean out;
  /**
   * The player's vote for the next round from 1 ~ 5. 0 is not yet voted
   */
  public int vote;

  //Teams related fields
  /**
   * The team this player is currently on
   */
  public Team team;
  /**
   * The team this player will switch to upon respawning
   */
  public Team newTeam;
  /**
   * The class the player is currently using
   */
  public IPlayerClass playerClass;
  /**
   * The class the player will switch to upon respawning
   */
  public IPlayerClass newPlayerClass;
  /**
   * Keeps the player out of having to rechose their team each round
   */
  public boolean builder;
  /**
   * Save the player's skin here, to replace after having done a swap for a certain class override
   */
  @SideOnly(Side.CLIENT)
  public ResourceLocation skin;
  public WeakHashMap<Integer, EntityBullet> controllingBullets = new WeakHashMap<>();

  public PlayerData(String name) {
    username = name;
    snapshots = new PlayerSnapshot[FlansMod.numPlayerSnapshots];
  }

  // Handed getters and setters
  public float GetShootTime(boolean bOffHand) {
    return bOffHand ? shootTimeLeft : shootTimeRight;
  }

  public void SetShootTime(boolean bOffHand, float set) {
    if (bOffHand) {
      shootTimeLeft = set;
    } else {
      shootTimeRight = set;
    }
  }

  public int GetBurstRoundsRemaining(boolean bOffHand) {
    return bOffHand ? burstRoundsRemainingLeft : burstRoundsRemainingRight;
  }

  public void SetBurstRoundsRemaining(boolean bOffHand, int set) {
    if (bOffHand) {
      burstRoundsRemainingLeft = set;
    } else {
      burstRoundsRemainingRight = set;
    }
  }

  public void tick(EntityPlayer player) {
    controllingBullets.entrySet().removeIf(o -> ((Entity) ((Map.Entry) o).getValue()).isDead);
    if (player.worldObj.isRemote) {
      clientTick(player);
    }
    if (shootTimeRight > 0) {
      shootTimeRight--;
    }

    if (shootTimeLeft > 0) {
      shootTimeLeft--;
    }

    if (shootClickDelay > 0) {
      shootClickDelay--;
    }

    if (loopedSoundDelay > 0) {
      loopedSoundDelay--;
      if (loopedSoundDelay == 0 && !isShootingRight) {
        shouldPlayCooldownSound = true;
      }
    }

    //Move all snapshots along one place
    System.arraycopy(snapshots, 0, snapshots, 1, snapshots.length - 2 + 1);
    //Take new snapshot
    snapshots[0] = new PlayerSnapshot(player);
  }
@SideOnly(Side.CLIENT)
  private void finishReload(boolean offHand) {

    ItemStack gunStack = offHand ? Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(offHandGunSlot)
        : Minecraft.getMinecraft().thePlayer.getHeldItem();
    reloadSound = null;
    if (gunStack == null || !(gunStack.getItem() instanceof ItemGun)) {
      return;
    }
    ItemGun gun = (ItemGun) gunStack.getItem();
    gun.Reload(gunStack, Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer,
        Minecraft.getMinecraft().thePlayer.inventory, offHand, false, true,
        Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode, true);
    FlansMod.getPacketHandler().sendToServer(new PacketReload(true, offHand, true));
  }
@SideOnly(Side.CLIENT)
  public void clientTick(EntityPlayer player) {
    if (player.getCurrentEquippedItem() == null
        || !(player.getCurrentEquippedItem().getItem() instanceof ItemGun)
        || !((ItemGun) player.getCurrentEquippedItem().getItem()).GetType().oneHanded
        || player.getCurrentEquippedItem() == offHandGunStack) {
      //offHandGunSlot = 0;
      offHandGunStack = null;
      reloadingLeft = 0;
    }

    if(Minecraft.getMinecraft().currentScreen instanceof GuiContainer)
      previousSlot = -1;

    if (player == Minecraft.getMinecraft().thePlayer) {

      if (reloadingRight > 0) {
        if (--reloadingRight == 0) {
          finishReload(false);
        }
      }
      if (reloadingLeft > 0) {
        if (--reloadingLeft == 0) {
          finishReload(true);
        }
      }

      int slot = Minecraft.getMinecraft().thePlayer.inventory.currentItem;

      if (slot != previousSlot) {
        reloadingRight = reloadingLeft = 0;
        previousSlot = slot;
        GunAnimations animations = FlansModClient.getGunAnimations(Minecraft.getMinecraft().thePlayer, false);
        animations.reloading = false;
        if (reloadSound != null) {
          Minecraft.getMinecraft().getSoundHandler().stopSound(reloadSound);
          reloadSound = null;
        }
        GunAnimations.lastInventorySlot = slot;
      }
    }
  }

  public IPlayerClass getPlayerClass() {
    if (playerClass != newPlayerClass) {
      playerClass = newPlayerClass;
    }
    return playerClass;
  }

  public void resetScore() {
    score = zombieScore = kills = deaths = 0;
    team = newTeam = null;
    playerClass = newPlayerClass = null;
  }

  public void playerKilled() {
    mountingGun = null;
    isShootingRight = isShootingLeft = false;
    snapshots = new PlayerSnapshot[FlansMod.numPlayerSnapshots];
  }

  public void selectOffHandWeapon(EntityPlayer player, int slot) {
    if (isValidOffHandWeapon(player, slot)) {
      offHandGunSlot = slot;
    }
  }

  public boolean isValidOffHandWeapon(EntityPlayer player, int slot) {
    if (slot == 0) {
      return true;
    }
    if (slot - 1 == player.inventory.currentItem) {
      return false;
    }
    ItemStack stackInSlot = player.inventory.getStackInSlot(slot - 1);
    if (stackInSlot == null) {
      return false;
    }
    if (stackInSlot.getItem() instanceof ItemGun) {
      ItemGun item = ((ItemGun) stackInSlot.getItem());
      return item.GetType().oneHanded;
    }
    return false;
  }

  public void cycleOffHandItem(EntityPlayer player, int dWheel) {
    if (dWheel < 0) {
      for (offHandGunSlot = ((offHandGunSlot + 1) % 10);
          !isValidOffHandWeapon(player, offHandGunSlot);
          offHandGunSlot = ((offHandGunSlot + 1) % 10)) {
      }
    } else if (dWheel > 0) {
      for (offHandGunSlot = ((offHandGunSlot + 9) % 10);
          !isValidOffHandWeapon(player, offHandGunSlot);
          offHandGunSlot = ((offHandGunSlot + 9) % 10)) {
      }
    }

    FlansModClient.currentScope = null;

    FlansMod.getPacketHandler().sendToServer(new PacketSelectOffHandGun(offHandGunSlot));
    reloadingLeft = 0;
  }

  public void doMelee(EntityPlayer player, int meleeTime, GunType type) {
    meleeLength = meleeTime;
    lastMeleePositions = new Vector3f[type.meleePath.size()];

    for (int k = 0; k < type.meleeDamagePoints.size(); k++) {
      Vector3f meleeDamagePoint = type.meleeDamagePoints.get(k);
      //Do a raytrace from the prev pos to the current pos and attack anything in the way
      Vector3f nextPos = type.meleePath.get(0);
      Vector3f nextAngles = type.meleePathAngles.get(0);
      RotatedAxes nextAxes = new RotatedAxes(-nextAngles.y, -nextAngles.z, nextAngles.x);

      Vector3f nextPosInPlayerCoords = new RotatedAxes(player.rotationYaw + 90F,
          player.rotationPitch, 0F)
          .findLocalVectorGlobally(nextAxes.findLocalVectorGlobally(meleeDamagePoint));
      Vector3f.add(nextPos, nextPosInPlayerCoords, nextPosInPlayerCoords);

      if (!FlansMod.proxy.isThePlayer(player)) {
        nextPosInPlayerCoords.y += 1.6F;
      }

      lastMeleePositions[k] = new Vector3f(player.posX + nextPosInPlayerCoords.x,
          player.posY + nextPosInPlayerCoords.y, player.posZ + nextPosInPlayerCoords.z);
    }
  }

  public void WriteToFile() {

  }

  public void ReadFromFile() {

  }

}
