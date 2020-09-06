package com.flansmod.common.driveables;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.network.PacketDriveableControl;
import com.flansmod.common.network.PacketDriveableKey;
import com.flansmod.common.network.PacketPlaneControl;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.tools.ItemTool;
import com.flansmod.common.vector.Matrix4f;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class EntityPlane extends EntityDriveable {

  /**
   * The flap positions, used for renderering and for controlling the plane rotations
   */
  public float flapsYaw, flapsPitchLeft, flapsPitchRight;
  /**
   * Position of looping engine sound
   */
  public int soundPosition;
  /**
   * The angle of the propeller for the renderer
   */
  public float propAngle;
  /**
   * Weapon delays
   */
  public int bombDelay, gunDelay;
  /**
   * Despawn timer
   */
  public int ticksSinceUsed = 0;
  /**
   * Mostly aesthetic model variables. Gear actually has a variable hitbox
   */
  public boolean varGear = true, varDoor = false, varWing = false;
  /**
   * Delayer for gear, door and wing buttons
   */
  public int toggleTimer = 0;
  /**
   * Current plane mode
   */
  public EnumPlaneMode mode;
  private float speedChange;
  private float onGroundTimer;

  public EntityPlane(World world) {
    super(world);
  }

  public EntityPlane(World world, double x, double y, double z, PlaneType type,
      DriveableData data) {
    super(world, type, data);
    setPosition(x, y, z);
    prevPosX = x;
    prevPosY = y;
    prevPosZ = z;
    initType(type, false);
  }

  public EntityPlane(World world, double x, double y, double z, EntityPlayer placer, PlaneType type,
      DriveableData data) {
    this(world, x, y, z, type, data);
    rotateYaw(placer.rotationYaw + 90F);
    rotatePitch(type.restingPitch);
  }

  @Override
  public void initType(DriveableType type, boolean clientSide) {
    super.initType(type, clientSide);
    mode = (((PlaneType) type).mode == EnumPlaneMode.HELI ? EnumPlaneMode.HELI
        : EnumPlaneMode.PLANE);
  }

  @Override
  protected void writeEntityToNBT(NBTTagCompound tag) {
    super.writeEntityToNBT(tag);
    tag.setTag("Pos", this.newDoubleNBTList(this.posX, this.posY + 1D, this.posZ));
    tag.setBoolean("VarGear", varGear);
    tag.setBoolean("VarDoor", varDoor);
    tag.setBoolean("VarWing", varWing);
  }

  @Override
  protected void readEntityFromNBT(NBTTagCompound tag) {
    super.readEntityFromNBT(tag);
    varGear = tag.getBoolean("VarGear");
    varDoor = tag.getBoolean("VarDoor");
    varWing = tag.getBoolean("VarWing");
  }

  /**
   * Called with the movement of the mouse. Used in controlling vehicles if need be.
   *
   * @param deltaY
   * @param deltaX
   */
  @Override
  public void onMouseMoved(int deltaX, int deltaY) {
    if (!FMLCommonHandler.instance().getSide().isClient()) {
      return;
    }
    if (!FlansMod.proxy.mouseControlEnabled()) {
      return;
    }

    float sensitivity = 0.02F;

    float flapsY = sensitivity * deltaY;
    float flapsX = sensitivity * deltaX;

    flapsPitchLeft -= flapsY;
    flapsPitchRight -= flapsY;

    flapsPitchLeft -= flapsX;
    flapsPitchRight += flapsX;
  }

  @Override
  public void setPositionRotationAndMotion(double x, double y, double z, float yaw, float pitch,
      float roll, double motX, double motY, double motZ, float velYaw, float velPitch,
      float velRoll, float throt, float steeringYaw) {
    super
        .setPositionRotationAndMotion(x, y, z, yaw, pitch, roll, motX, motY, motZ, velYaw, velPitch,
            velRoll, throt, steeringYaw);
    flapsYaw = steeringYaw;
  }

  @Override
  public boolean interactFirst(EntityPlayer entityplayer) {
    if (isDead) {
      return false;
    }
    if (worldObj.isRemote) {
      return false;
    }

    //If they are using a repair tool, don't put them in
    ItemStack currentItem = entityplayer.getCurrentEquippedItem();
    if (currentItem != null && currentItem.getItem() instanceof ItemTool && ((ItemTool) currentItem
        .getItem()).type.healDriveables) {
      return true;
    }

    PlaneType type = this.getPlaneType();
    //Check each seat in order to see if the player can sit in it
    for (int i = 0; i <= type.numPassengers; i++) {
      if (seats[i].interactFirst(entityplayer)) {
        if (i == 0) {
          bombDelay = type.planeBombDelay;
          FlansMod.proxy.doTutorialStuff(entityplayer, this);
        }
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean pressKey(int key, EntityPlayer player) {
    PlaneType type = this.getPlaneType();
    //Send keys which require server side updates to the server
    if (worldObj.isRemote && (key == 6 || key == 8 || key == 9 || key == 20)) {
      FlansMod.getPacketHandler().sendToServer(new PacketDriveableKey(key));
      return true;
    }
    boolean canThrust = ((seats[0] != null && seats[0].riddenByEntity instanceof EntityPlayer
        && ((EntityPlayer) seats[0].riddenByEntity).capabilities.isCreativeMode)
        || getDriveableData().fuelInTank > 0) && hasWorkingProp();
    switch (key) {
      case 0: //Accelerate : Increase the throttle, up to 1.
      {
        if (canThrust || throttle < 0F) {
          if (mode == EnumPlaneMode.PLANE) {

            throttle += 0.01F;
            if (throttle > 1F) {
              throttle = 1F;
            }
          } else {
            throttle += 0.002F;
            if (throttle > 1F) {
              throttle = 1F;
            }
          }
        }
        return true;
      }
      case 1: //Decelerate : Decrease the throttle, down to -1, or 0 if the plane cannot reverse
      {
        if (canThrust || throttle > 0F) {
          if (mode == EnumPlaneMode.PLANE) {
            throttle -= 0.01F;
            if (throttle < -1F) {
              throttle = -1F;
            }
            if (throttle < 0F && type.maxNegativeThrottle == 0F) {
              throttle = 0F;
            }
          } else {
            throttle -= 0.005F;
            if (throttle < -1F) {
              throttle = -1F;
            }
            if (throttle < 0F && type.maxNegativeThrottle == 0F) {
              throttle = 0F;
            }
          }

        }
        return true;
      }
      case 2: //Left : Yaw the flaps left
      {
        flapsYaw -= 1F;
        return true;
      }
      case 3: //Right : Yaw the flaps right
      {
        flapsYaw += 1F;
        return true;
      }
      case 4: //Up : Pitch the flaps up
      {
        flapsPitchLeft += 1F;
        flapsPitchRight += 1F;
        return true;
      }
      case 5: //Down : Pitch the flaps down
      {
        flapsPitchLeft -= 1F;
        flapsPitchRight -= 1F;
        return true;
      }
      case 6: //Exit : Get out
      {
        if (seats[0].riddenByEntity != null) {
          seats[0].riddenByEntity.mountEntity(null);
        }
        return true;
      }
      case 7: //Inventory : Check to see if this plane allows in-flight inventory editing or if the plane is on the ground
      {
        if (worldObj.isRemote && (type.invInflight || (Math.abs(throttle) < 0.1F && onGround))) {
          FlansMod.proxy.openDriveableMenu((EntityPlayer) seats[0].riddenByEntity, worldObj, this);
        }
        return true;
      }
      case 8: //Drop bomb
      case 9: //Shoot bullet
      {
        return super.pressKey(key, player);
      }
      case 10: //Change control mode
      {
        FlansMod.proxy.changeControlMode((EntityPlayer) seats[0].riddenByEntity);
        return true;
      }
      case 11: //Roll left
      {
        flapsPitchLeft += 1F;
        flapsPitchRight -= 1F;
        return true;
      }
      case 12: //Roll right
      {
        flapsPitchLeft -= 1F;
        flapsPitchRight += 1F;
        return true;
      }
      case 13: // Gear
      {
        if (toggleTimer <= 0) {
          varGear = !varGear;
          player.addChatMessage(new ChatComponentText("Landing gear " + (varGear ? "down" : "up")));
          toggleTimer = 10;
          FlansMod.getPacketHandler().sendToServer(new PacketDriveableControl(this));
        }
        return true;
      }
      case 14: // Door
      {
        if (toggleTimer <= 0) {
          varDoor = !varDoor;
          if (type.hasDoor) {
            player.addChatMessage(new ChatComponentText("Doors " + (varDoor ? "open" : "closed")));
          }
          toggleTimer = 10;
          FlansMod.getPacketHandler().sendToServer(new PacketDriveableControl(this));
        }
        return true;
      }
      case 15: // Wing
      {
        if (toggleTimer <= 0) {
          if (type.hasWing) {
            varWing = !varWing;
            player.addChatMessage(new ChatComponentText("Switching mode"));
          }
          if (type.mode == EnumPlaneMode.VTOL) {
            if (mode == EnumPlaneMode.HELI) {
              mode = EnumPlaneMode.PLANE;
            } else {
              mode = EnumPlaneMode.HELI;
            }
            player.addChatMessage(new ChatComponentText(
                mode == EnumPlaneMode.HELI ? "Entering hover mode" : "Entering plane mode"));
          }
          toggleTimer = 10;
          FlansMod.getPacketHandler().sendToServer(new PacketDriveableControl(this));
        }
        return true;
      }
      case 16: // Trim Button
      {
        axes.setAngles(axes.getYaw(), 0, 0);
        return true;
      }
      case 17: //Park
      {
        break;
      }
      case 20: //Park
      {
        if (type.numFlareSlots > 0) {
          return deployFlares(player);
        }
        break;
      }
    }
    return false;
  }

  protected boolean deployFlares(EntityPlayer player) {

    GunType gunType = GunType.getGun("flareGun");

    for (int i = 0; i < driveableData.cargo.length; i++) {
      if (driveableData.cargo[i] == null) {
        continue;
      }

      ItemStack bulletItemStack = driveableData.cargo[i];
      //Check that neither is null and that the bullet item is actually a bullet
      if (gunType != null && bulletItemStack != null && bulletItemStack
          .getItem() instanceof ItemShootable && TeamsManager.bulletsEnabled) {
        ShootableType bullet = ((ItemShootable) bulletItemStack.getItem()).type;
        if (gunType.isAmmo(bullet) && shootDelaySecondary <= 0) {
          //Spawn a new bullet item
          float bulletZDir = (-driveableData.cargo.length + 1) + 2 * i;
          worldObj.spawnEntityInWorld(((ItemShootable) bulletItemStack.getItem())
              .getEntity(worldObj, new Vector3f(this.posX, this.posY + 1, this.posZ),
                  this.axes.findLocalVectorGlobally(new Vector3f(5, -1, bulletZDir)),
                  (EntityLivingBase) seats[0].riddenByEntity, gunType.bulletSpread / 2,
                  gunType.damage, getRealSpeedXYZ(), this.getDriveableType()));
          //Play the shoot sound
          PacketPlaySound
              .sendSoundPacket(posX, posY, posZ, FlansMod.soundRange, dimension, gunType.shootSound,
                  false);
          //Get the bullet item damage and increment it
          int damage = bulletItemStack.getItemDamage();
          bulletItemStack.setItemDamage(damage + 1);
          //If the bullet item is completely damaged (empty)
          if (damage + 1 == bulletItemStack.getMaxDamage()) {
            //Set the damage to 0 and consume one ammo item (unless in creative)
            bulletItemStack.setItemDamage(0);
            if (seats[0].riddenByEntity instanceof EntityPlayer
                && !((EntityPlayer) seats[0].riddenByEntity).capabilities.isCreativeMode) {
              bulletItemStack.stackSize--;
              if (bulletItemStack.stackSize <= 0) {
                bulletItemStack = null;
              }
              driveableData.cargo[i] = bulletItemStack;
            }
          }
        }

      }
    }

    setShootDelay(2, true);
    return true;
  }

  @Override
  public void updateKeyHeldState(int key, boolean held) {
    super.updateKeyHeldState(key, held);
    //On the server. For semi-auto weapons, shoot!
    if (!worldObj.isRemote) {
      switch (key) {
        case 9: //Left Mouse
        case 8: //Right Mouse
      }
    }
  }

  @Override
  public void onUpdate() {

    boolean thePlayerIsDrivingThis =
        worldObj.isRemote && seats[0] != null && seats[0].riddenByEntity instanceof EntityPlayer
            && FlansMod.proxy.isThePlayer((EntityPlayer) seats[0].riddenByEntity);

    if (thePlayerIsDrivingThis) {
      if (speedLagModifierTimer > 0 && speedLagModifier < 1) {

        motionX *= speedLagModifier;
        motionY *= speedLagModifier;
        motionZ *= speedLagModifier;

        super.onUpdate();

        if (speedLagModifier == 0) {

          FlansMod.getPacketHandler().sendToServer(new PacketPlaneControl(this));
          return;
        }

      }
    }
    super.onUpdate();

    //Get plane type
    PlaneType type = getPlaneType();
    DriveableData data = getDriveableData();
    if (type == null) {
      FlansMod.log("Plane type null. Not ticking plane");
      return;
    }

    //Work out if this is the client side and the player is driving

    //Despawning
    ticksSinceUsed++;
    if (!worldObj.isRemote && seats[0].riddenByEntity != null) {
      ticksSinceUsed = 0;
    }
    if (!worldObj.isRemote && TeamsManager.planeLife > 0
        && ticksSinceUsed > TeamsManager.planeLife * 20) {
      setDead();
    }

    //Shooting, inventories, etc.
    //Decrement bomb and gun timers
    if (bombDelay > 0) {
      bombDelay--;
    }
    if (gunDelay > 0) {
      gunDelay--;
    }
    if (toggleTimer > 0) {
      toggleTimer--;
    }

    //Aesthetics
    //Rotate the propellers
    if (hasEnoughFuel()) {
      propAngle += (Math.pow(throttle, 0.4)) * 1.5;
    }

    //Return the flaps to their resting position
    flapsYaw *= 0.9F;
    flapsPitchLeft *= 0.9F;
    flapsPitchRight *= 0.9F;

    //Limit flap angles
    if (flapsYaw > 10) {
      flapsYaw = 10;
    }
    if (flapsYaw < -10) {
      flapsYaw = -10;
    }
    if (flapsPitchRight > 10) {
      flapsPitchRight = 10;
    }
    if (flapsPitchRight < -10) {
      flapsPitchRight = -10;
    }
    if (flapsPitchLeft > 10) {
      flapsPitchLeft = 10;
    }
    if (flapsPitchLeft < -10) {
      flapsPitchLeft = -10;
    }

    //Player is not driving this. Update its position from server update packets
    if (worldObj.isRemote && !thePlayerIsDrivingThis) {
      //The driveable is currently moving towards its server position. Continue doing so.
      if (serverPositionTransitionTicker > 0) {
        double x = posX + (serverPosX - posX) / serverPositionTransitionTicker;
        double y = posY + (serverPosY - posY) / serverPositionTransitionTicker;
        double z = posZ + (serverPosZ - posZ) / serverPositionTransitionTicker;
        double dYaw = MathHelper.wrapAngleTo180_double(serverYaw - axes.getYaw());
        double dPitch = MathHelper.wrapAngleTo180_double(serverPitch - axes.getPitch());
        double dRoll = MathHelper.wrapAngleTo180_double(serverRoll - axes.getRoll());
        rotationYaw = (float) (axes.getYaw() + dYaw / serverPositionTransitionTicker);
        rotationPitch = (float) (axes.getPitch() + dPitch / serverPositionTransitionTicker);
        float rotationRoll = (float) (axes.getRoll() + dRoll / serverPositionTransitionTicker);
        --serverPositionTransitionTicker;
        setPosition(x, y, z);
        setRotation(rotationYaw, rotationPitch, rotationRoll);
        //return;
      }
      //If the driveable is at its server position and does not have the next update, it should just simulate itself as a server side plane would, so continue
    }

    //Movement

    //Throttle handling
    //Without a player, default to 0
    //With a player default to 0.5 for helicopters (hover speed)
    //And default to the range 0.25 ~ 0.5 for planes (taxi speed ~ take off speed)

    if (seats[0] == null || seats[0].riddenByEntity == null) {
      throttle = Math.max(0, throttle - 0.1f);
    }
    float throttlePull = 0.99F;
    if (seats[0] != null && seats[0].riddenByEntity != null && mode == EnumPlaneMode.HELI
        && canThrust()) {
      throttle = (throttle - 0.5F) * throttlePull + 0.5F;
    }

    boolean isOnGround = isOnGround();
    onGroundTimer = Math.max(0, Math.min(1, onGroundTimer + (isOnGround ? -0.05f : 0.05f)));

    //Some constants
    float g = 0.98F / 10F;
    float drag = 1F - (0.05F * type.drag);
    float wobbleFactor = 0F;//.005F;
    float fuelConsumptionMultiplier = 2F;

    int numPropsWorking = 0;
    int numProps = 0;

    if (mode == EnumPlaneMode.PLANE) {
      int numWingsIntact = 0;
      if (isPartIntact(EnumDriveablePart.rightWing)) {
        numWingsIntact++;
      }
      if (isPartIntact(EnumDriveablePart.leftWing)) {
        numWingsIntact++;
      }

      //Get the speed of the plane
      float lastTickSpeed = (float) getSpeedXYZ();
      float actualSpeed = getRealSpeedXYZ();
      float relativeSpeed = actualSpeed / type.drag;
      float onGround = isOnGround ? 0.055f : 0;

      float tailLift = isPartIntact(EnumDriveablePart.tail) ? 0.2f : 0;
      float amountOfLift = tailLift + 0.4f * numWingsIntact;
      float upForce = (float) Math.min(Math.pow(relativeSpeed * 2, 2), 1);
      float forceY = upForce * amountOfLift;
      float stall = (1 - forceY);

      float relativeSpeedLimit = Math.min(1f, relativeSpeed);

      //Alter angles
      //Sensitivity function
      float sensYaw = (float) Math.max(0, (-Math.pow(relativeSpeed * 1.5 - 1, 2) + 1));
      float flyingYawMod =
          1 - onGroundTimer * Math.max(0, (float) (-Math.pow(relativeSpeed * 2, 2) + 1));
      sensYaw *= flyingYawMod;
      float sensPitch = Math.max(0, upForce - 0.4f);
      float sensRoll = sensYaw * onGroundTimer;

      //Scalar
      sensYaw *= 0.125F;
      sensYaw *= 0.5f;
      sensPitch *= 0.125F;
      sensRoll *= 0.125F;
      sensRoll *= 1.2F;

      float yaw =
          flapsYaw * (flapsYaw > 0 ? type.turnLeftModifier : type.turnRightModifier) * sensYaw;

      //if(throttle < 0.2F)
      //	sensitivityAdjust = throttle * 2.5F;
      //Pitch according to the sum of flapsPitchLeft and flapsPitchRight / 2
      float flapsPitch = (flapsPitchLeft + flapsPitchRight) / 2F;
      float pitch =
          flapsPitch * (flapsPitch > 0 ? type.lookUpModifier : type.lookDownModifier) * sensPitch;

      //Roll according to the difference between flapsPitchLeft and flapsPitchRight / 2
      float flapsRoll = (flapsPitchRight - flapsPitchLeft) / 2F;
      float roll =
          flapsRoll * (flapsRoll > 0 ? type.rollLeftModifier : type.rollRightModifier) * sensRoll;
      //	axes.rotateLocalPitch(-stall * 1f * onGroundTimer * (Math.abs(axes.getRoll()) > 90? -1 : 1));
      pitch += (-stall * 1f * onGroundTimer) * (1 - Math.abs(axes.getRoll()) / 90);

      if (!isPartIntact(EnumDriveablePart.tail)) {
        yaw = 0;
        pitch = 0;
        roll = 0;
      }
      if (!isPartIntact(EnumDriveablePart.leftWing)) {
        roll = (float) (roll * 0.1 - 10F * relativeSpeed * (sensRoll + 0.2) * onGroundTimer);
      }
      if (!isPartIntact(EnumDriveablePart.rightWing)) {
        roll = (float) (roll * 0.1 + 10F * relativeSpeed * (sensRoll + 0.2) * onGroundTimer);
      }

      axes.rotateLocalYaw(yaw);
      axes.rotateLocalPitch(pitch);
      axes.rotateLocalRoll(-roll);

      float throttleScaled =
          0.01F * (type.maxThrottle + (data.engine == null ? 0 : data.engine.engineSpeed));

      if (!canThrust() || throttle == 0) {
        throttleScaled = 0;
      }

      //Count the number of working propellers
      for (Propeller prop : type.propellers) {
        if (isPartIntact(prop.planePart)) {
          numPropsWorking++;
        }
      }
      numProps = type.propellers.size();

      float throttleTemp = throttle * (numProps == 0 ? 0 : numPropsWorking / numProps);

      //Apply forces
      Vector3f forwards = (Vector3f) axes.getXAxis().normalise();

      //Sanity limiter
      if (lastTickSpeed > 2F) {
        lastTickSpeed = 2F;
      }

      float fspeed = actualSpeed > 0 ? actualSpeed : 0;
      //System.out.println("speed " + actualSpeed);
      float flapsMod = 0;
      float forcesBackward = (0.01f) * fspeed + onGround + flapsMod * 0.05f;
      if (fspeed == 0) {
        forcesBackward = 0;
      }

      float forcesForward = throttleTemp * type.drag * 1.25f;

      //Calculate the amount to alter motion by
      //float proportionOfMotionToCorrect = 2F * throttleTemp - 0.5F;
      //float proportionOfMotionToCorrect = (float)Math.sqrt(type.maxThrottle)*throttleTemp - 0.01f - onGround;
      float proportionOfMotionToCorrect = 1;
      //	if(proportionOfMotionToCorrect < 0F)
      //		proportionOfMotionToCorrect = 0F;
      //		if(proportionOfMotionToCorrect > 0.5F)
      //			proportionOfMotionToCorrect = 0.5F;

      //Apply gravity
      g = 0.98F / 20F;

      //speed gain/loose by flying upwards or downwards
      //double speedY = isOnGround() || ticksExisted < 160? 0 : motionY;
      double speedY =
          isOnGround() || ticksExisted < 160 || throttle == 0 || getControllingEntity() == null ? 0
              : motionY - prevRotationPitch * 0.005 * type.drag;
      if (speedY > 0) {
        speedY *= 1.9f;
      }
      float speedChange = forcesForward - forcesBackward - (float) (speedY) * 0.25f;
      float newSpeed = proportionOfMotionToCorrect =
          this.speedChange < speedChange ? this.speedChange * 1.01f + 0.005f
              : this.speedChange > speedChange + 0.001f ? this.speedChange * 0.990f - 0.005f
                  : speedChange;
      this.speedChange = newSpeed;
      motionY -= g;

      amountOfLift = g * (numWingsIntact / 2F) * (relativeSpeed) * actualSpeed;
      if (amountOfLift > g) {
        amountOfLift = g;
      }

      //System.out.println(amountOfLift + " " + g + " " + relativeSpeed);

      motionY += amountOfLift;

      if (proportionOfMotionToCorrect < 0F) {
        proportionOfMotionToCorrect *= -1F;
      }
      if (proportionOfMotionToCorrect > 0.5F) {
        proportionOfMotionToCorrect = 0.5F;
      }

      if (actualSpeed < 0.01f && actualSpeed > -0.01f && throttleScaled == 0) {

        motionX = motionZ = 0;
        //	break;
      }

      motionX *= 1F - proportionOfMotionToCorrect;
      motionY *= 1F - proportionOfMotionToCorrect;
      motionZ *= 1F - proportionOfMotionToCorrect;

      //Add the corrected motion
      motionX += proportionOfMotionToCorrect * newSpeed * forwards.x * 0.8f;
      motionY += proportionOfMotionToCorrect * newSpeed * forwards.y * 0.8f;
      motionZ += proportionOfMotionToCorrect * newSpeed * forwards.z * 0.8f;

      data.fuelInTank -=
          throttleScaled * fuelConsumptionMultiplier * data.engine.fuelConsumption * throttle;
    } else {
      //HELI

      float sensitivityAdjust = (throttle > 0.5F ? 1.5F - throttle : 4F * throttle - 1F);
      float sensitivityAdjustYaw = sensitivityAdjust;

      if (sensitivityAdjust < 0F) {
        sensitivityAdjust = 0F;
      }
      if (sensitivityAdjustYaw < 0F) {
        sensitivityAdjustYaw = 0F;
      }
      //Scalar
      sensitivityAdjust *= 0.125F;
      sensitivityAdjustYaw *= 0.125F;

      float yaw = flapsYaw * (flapsYaw > 0 ? type.turnLeftModifier : type.turnRightModifier)
          * sensitivityAdjustYaw;

      //if(throttle < 0.2F)
      //	sensitivityAdjust = throttle * 2.5F;
      //Pitch according to the sum of flapsPitchLeft and flapsPitchRight / 2
      float flapsPitch = (flapsPitchLeft + flapsPitchRight) / 2F;
      float pitch = flapsPitch * (flapsPitch > 0 ? type.lookUpModifier : type.lookDownModifier)
          * sensitivityAdjust;

      //Roll according to the difference between flapsPitchLeft and flapsPitchRight / 2
      float flapsRoll = (flapsPitchRight - flapsPitchLeft) / 2F;
      float roll = flapsRoll * (flapsRoll > 0 ? type.rollLeftModifier : type.rollRightModifier)
          * sensitivityAdjust;

      //Damage modifiers

      if (!isPartIntact(EnumDriveablePart.tail) && throttle > 0.01) {
        yaw += 6 * (throttle - 0.01);
      }

      axes.rotateLocalYaw(yaw);
      axes.rotateLocalPitch(pitch);
      axes.rotateLocalRoll(-roll);

      //Count the number of working propellers
      for (Propeller prop : type.heliPropellers) {
        if (isPartIntact(prop.planePart)) {
          numPropsWorking++;
        }
      }
      numProps = type.heliPropellers.size();

      Vector3f up = axes.getYAxis();

      float throttleScaled =
          0.01F * (type.maxThrottle + (data.engine == null ? 0 : data.engine.engineSpeed));
      if (!canThrust() || throttle == 0) {
        throttleScaled = 0;
      }
      throttleScaled *= numProps == 0 ? 0 : (float) numPropsWorking / numProps * 2F;

      float upwardsForce = throttle * throttleScaled + (g - throttleScaled / 2F);
      if (throttle < 0.5F) {
        upwardsForce = g * throttle * 2F;
      }

      if (!isPartIntact(EnumDriveablePart.blades)) {
        upwardsForce = 0F;
      }

      //Move up
      //Throttle - 0.5 means that the positive throttle scales from -0.5 to +0.5. Thus it accounts for gravity-ish
      motionX += upwardsForce * up.x * 0.5F;
      motionY += upwardsForce * up.y;
      motionZ += upwardsForce * up.z * 0.5F;
      //Apply gravity
      motionY -= g;

      //Apply wobble
      //motionX += rand.nextGaussian() * wobbleFactor;
      //motionY += rand.nextGaussian() * wobbleFactor;
      //motionZ += rand.nextGaussian() * wobbleFactor;

      //Apply drag
      motionX *= drag;
      motionY *= drag;
      motionZ *= drag;

      data.fuelInTank -=
          upwardsForce * fuelConsumptionMultiplier * data.engine.fuelConsumption * throttle;

    }

    double motion = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
    if (motion > 10) {
      motionX *= 10 / motion;
      motionY *= 10 / motion;
      motionZ *= 10 / motion;
    }

    for (EntityWheel wheel : wheels) {
      if (wheel != null && worldObj != null) {
        wheel.prevPosX = wheel.posX;
        wheel.prevPosY = wheel.posY;
        wheel.prevPosZ = wheel.posZ;
      }
    }
    for (EntityWheel wheel : wheels) {
      if (wheel != null && worldObj != null) {
        if (type.floatOnWater && worldObj.isAnyLiquid(wheel.getEntityBoundingBox())) {
          motionY += type.buoyancy;
        }
      }
    }

    //Move the wheels first
    for (EntityWheel wheel : wheels) {
      if (wheel != null) {
        wheel.prevPosY = wheel.posY;
        wheel.moveEntity(motionX, motionY, motionZ);
      }
    }
    //Update wheels
    for (int i = 0; i < 2; i++) {
      Vector3f amountToMoveCar = new Vector3f(motionX / 2F, motionY / 2F, motionZ / 2F);

      for (EntityWheel wheel : wheels) {
        if (wheel == null) {
          continue;
        }

        //Hacky way of forcing the car to step up blocks
        this.onGround = true;
        wheel.onGround = true;

        //Update angles
        wheel.rotationYaw = axes.getYaw();

        //Pull wheels towards car
        Vector3f targetWheelPos = axes.findLocalVectorGlobally(getPlaneType().wheelPositions[wheel.ID].position);
        Vector3f currentWheelPos = new Vector3f(wheel.posX - posX, wheel.posY - posY, wheel.posZ - posZ);
        //    System.out.println(currentWheelPos + " tar: " + targetWheelPos);
        float targetWheelLength = targetWheelPos.length();
        float currentWheelLength = currentWheelPos.length();

        float dLength = targetWheelLength - currentWheelLength;
        float dAngle = Vector3f.angle(targetWheelPos, currentWheelPos);

        {
          //Now Lerp by wheelSpringStrength and work out the new positions
          float newLength = currentWheelLength + dLength * type.wheelSpringStrength;
          Vector3f rotateAround = Vector3f.cross(targetWheelPos, currentWheelPos, null);

          Matrix4f mat = new Matrix4f();
          mat.m00 = currentWheelPos.x;
          mat.m10 = currentWheelPos.y;
          mat.m20 = currentWheelPos.z;
          mat.rotate(dAngle * type.wheelSpringStrength, rotateAround);

          axes.rotateGlobal(-dAngle * type.wheelSpringStrength, rotateAround);

          Vector3f newWheelPos = new Vector3f(mat.m00, mat.m10, mat.m20);
          newWheelPos.normalise().scale(newLength);

          //The proportion of the spring adjustment that is applied to the wheel. 1 - this is applied to the plane
          float wheelProportion = ticksExisted > 50? 0.75f : 1f-ticksExisted*0.005f;
          //wheel.motionX = (newWheelPos.x - currentWheelPos.x) * wheelProportion;
          //wheel.motionY = (newWheelPos.y - currentWheelPos.y) * wheelProportion;
          //wheel.motionZ = (newWheelPos.z - currentWheelPos.z) * wheelProportion;

          Vector3f amountToMoveWheel = new Vector3f();

       /*   amountToMoveWheel.x = (newWheelPos.x - currentWheelPos.x) * (wheelProportion);
          amountToMoveWheel.y = (newWheelPos.y - currentWheelPos.y) * (wheelProportion);
          amountToMoveWheel.z = (newWheelPos.z - currentWheelPos.z) * (wheelProportion);

          if (mode == EnumPlaneMode.HELI) {*/
            wheelProportion = 0.75f;
            amountToMoveWheel.x = (newWheelPos.x - currentWheelPos.x) * (1F - wheelProportion);
            amountToMoveWheel.y = (newWheelPos.y - currentWheelPos.y) * (1F - wheelProportion);
            amountToMoveWheel.z = (newWheelPos.z - currentWheelPos.z) * (1F - wheelProportion);
      //    }
          amountToMoveCar.x -= (newWheelPos.x - currentWheelPos.x) * (1F - wheelProportion);
          amountToMoveCar.y -= (newWheelPos.y - currentWheelPos.y) * (1F - wheelProportion);
          amountToMoveCar.z -= (newWheelPos.z - currentWheelPos.z) * (1F - wheelProportion);

          //       System.out.println("Move wheel " + amountToMoveWheel + " car " + amountToMoveCar);
          //The difference between how much the wheel moved and how much it was meant to move. i.e. the reaction force from the block
          //amountToMoveCar.x += ((wheel.posX - wheel.prevPosX) - (motionX)) * 0.616F / wheels.length;
          amountToMoveCar.y += ((wheel.posY - wheel.prevPosY) - (motionY)) * 0.5F / wheels.length;
          //amountToMoveCar.z += ((wheel.posZ - wheel.prevPosZ) - (motionZ)) * 0.0616F / wheels.length;

          wheel.moveEntity(amountToMoveWheel.x, amountToMoveWheel.y, amountToMoveWheel.z);
        }
      }

      moveEntity(amountToMoveCar.x, amountToMoveCar.y, amountToMoveCar.z);

    }

    checkForCollisions();

    //Sounds
    //Starting sound
    if (throttle > 0.01F && throttle < 0.2F && soundPosition == 0 && hasEnoughFuel()) {
      PacketPlaySound
          .sendSoundPacket(posX, posY, posZ, FlansMod.soundRange, dimension, type.startSound,
              false);
      soundPosition = type.startSoundLength;
    }
    //Flying sound
    if (throttle > 0.2F && soundPosition == 0 && hasEnoughFuel()) {
      PacketPlaySound
          .sendSoundPacket(posX, posY, posZ, FlansMod.soundRange, dimension, type.engineSound,
              false);
      soundPosition = type.engineSoundLength;
    }

    //Sound decrementer
    if (soundPosition > 0) {
      soundPosition--;
    }

    for (EntitySeat seat : seats) {
      if (seat != null) {
        seat.updatePosition();
      }
    }

    //Calculate movement on the client and then send position, rotation etc to the server
    if (thePlayerIsDrivingThis) {
      FlansMod.getPacketHandler().sendToServer(new PacketPlaneControl(this));
      serverPosX = posX;
      serverPosY = posY;
      serverPosZ = posZ;
      serverYaw = axes.getYaw();
    }

    //If this is the server, send position updates to everyone, having received them from the driver
    float updateSpeed = 0.01F;
    if (!worldObj.isRemote)// && (Math.abs(posX - prevPosX) > updateSpeed || Math.abs(posY - prevPosY) > updateSpeed || Math.abs(posZ - prevPosZ) > updateSpeed))
    {
      FlansMod.getPacketHandler().sendToAllAround(new PacketPlaneControl(this), posX, posY, posZ,
          FlansMod.driveableUpdateRange, dimension);
    }
  }

  private boolean isOnGround() {
    for (Entity wh : wheels) {
      if (wh != null && wh.onGround) {
        return true;
      }
    }
    return false;
  }

  public boolean canThrust() {
    return (seats[0] != null && seats[0].riddenByEntity instanceof EntityPlayer
        && ((EntityPlayer) seats[0].riddenByEntity).capabilities.isCreativeMode)
        || driveableData.fuelInTank > 0 && (getPlaneType().floatOnWater || timeUnderWater < 60);
  }

  @Override
  public void setDead() {
    super.setDead();
    for (EntityWheel wheel : wheels) {
      if (wheel != null) {
        wheel.setDead();
      }
    }
  }

  @Override
  public boolean gearDown() {
    return varGear;
  }

  private boolean hasWorkingProp() {
    PlaneType type = getPlaneType();
    if (type.mode == EnumPlaneMode.HELI || type.mode == EnumPlaneMode.VTOL) {
      for (Propeller prop : type.heliPropellers) {
        if (isPartIntact(prop.planePart)) {
          return true;
        }
      }
    }
    if (type.mode == EnumPlaneMode.PLANE || type.mode == EnumPlaneMode.VTOL) {
      for (Propeller prop : type.propellers) {
        if (isPartIntact(prop.planePart)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean attackEntityFrom(DamageSource damagesource, float i, boolean doDamage) {
    if (worldObj.isRemote || isDead) {
      return true;
    }

    PlaneType type = PlaneType.getPlane(driveableType);

    if (damagesource.damageType.equals("player") && damagesource.getEntity().onGround && (
        seats[0] == null || seats[0].riddenByEntity == null)) {
      ItemStack planeStack = new ItemStack(type.item, 1, driveableData.paintjobID);
      NBTTagCompound tags = new NBTTagCompound();
      planeStack.setTagCompound(tags);
      driveableData.writeToNBT(tags);
      entityDropItem(planeStack, 0.5F);
      setDead();
    }
    return true;
  }

  @Override
  public boolean canHitPart(EnumDriveablePart part) {
    return varGear || (part != EnumDriveablePart.coreWheel
        && part != EnumDriveablePart.leftWingWheel && part != EnumDriveablePart.rightWingWheel
        && part != EnumDriveablePart.tailWheel);
  }

  @Override
  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    return attackEntityFrom(damagesource, i, true);
  }

  public PlaneType getPlaneType() {
    return PlaneType.getPlane(driveableType);
  }

  @Override
  protected void dropItemsOnPartDeath(Vector3f midpoint, DriveablePart part) {
  }

  @Override
  public String getBombInventoryName() {
    return "Bombs";
  }

  @Override
  public String getMissileInventoryName() {
    return "Missiles";
  }

  @Override
  public boolean hasMouseControlMode() {
    return true;
  }
}
