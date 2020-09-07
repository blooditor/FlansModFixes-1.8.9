package com.flansmod.common.guns;

import com.flansmod.client.model.ModelGun;
import com.flansmod.client.model.ModelMG;
import com.flansmod.common.FlansMod;
import com.flansmod.common.types.PaintableType;
import com.flansmod.common.types.TypeFile;
import com.flansmod.common.vector.Vector3f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GunType extends PaintableType implements IScope {
  //Gun Behaviour Variables
  /**
   * The list of bullet types that can be used in this gun
   */
  public List<ShootableType> ammo = new ArrayList<ShootableType>(), nonExplosiveAmmo = new ArrayList<ShootableType>();
  /**
   * Whether the player can press the reload key (default R) to reload this gun
   */
  public boolean canForceReload = true;
  /**
   * The time (in ticks) it takes to reload this gun
   */
  public int reloadTime;
  /**
   * The amount to recoil the player's view by when firing a single shot from this gun
   */
  public GunRecoil recoil = new GunRecoil();
  /**
   * The amount that bullets spread out when fired from this gun
   */
  public float bulletSpread;
  /**
   * Damage inflicted by this gun. Multiplied by the bullet damage.
   */
  public float damage = 0;
  /**
   * The damage inflicted upon punching someone with this gun
   */
  public float meleeDamage = 1;
  /**
   * The speed of bullets upon leaving this gun. 0.0f means instant.
   */
  public float bulletSpeed = 0.0f;
  /**
   * The number of bullet entities created by each shot
   */
  public int numBullets = 1;
  /**
   * The delay between shots in ticks (1/20ths of seconds)
   */
  public float shootDelay = 1.0f;
  /**
   * Number of ammo items that the gun may hold. Most guns will hold one magazine. Some may hold
   * more, such as Nerf pistols, revolvers or shotguns
   */
  public int numAmmoItemsInGun = 1;
  /**
   * The firing mode of the gun. One of semi-auto, full-auto, minigun or burst
   */
  public EnumFireMode mode = EnumFireMode.FULLAUTO;
  /**
   * The number of bullets to fire per burst in burst mode
   */
  public int numBurstRounds = 3;
  /**
   * The required speed for minigun mode guns to start firing
   */
  public float minigunStartSpeed = 15F;
  /**
   * Whether this gun can be used underwater
   */
  public boolean canShootUnderwater = true;
  /**
   * The amount of knockback to impact upon the player per shot
   */
  public float knockback = 0F;
  /**
   * The secondary function of this gun. By default, the left mouse button triggers this
   */
  public EnumSecondaryFunction secondaryFunction = EnumSecondaryFunction.ADS_ZOOM;
  /**
   * If true, then this gun can be dual wielded
   */
  public boolean oneHanded = false;
  /**
   * For one shot items like a panzerfaust
   */
  public boolean consumeGunUponUse = false;
  /**
   * Item to drop on shooting
   */
  public String dropItemOnShoot = null;
  //Custom Melee Stuff
  /**
   * The time delay between custom melee attacks
   */
  public int meleeTime = 1;
  /**
   * The path the melee weapon takes
   */
  public ArrayList<Vector3f> meleePath = new ArrayList<Vector3f>(), meleePathAngles = new ArrayList<Vector3f>();
  /**
   * The points on the melee weapon that damage is actually done from.
   */
  public ArrayList<Vector3f> meleeDamagePoints = new ArrayList<Vector3f>();
  /**
   * Set these to make guns only usable by a certain type of entity
   */
  public boolean usableByPlayers = true, usableByMechas = true;

  //Information
  //Show any variables into the GUI when hovering over items.
  /**
   * If false, then attachments wil not be listed in item GUI
   */
  public boolean showAttachments = true;
  /**
   * Show statistics
   */
  public boolean showDamage = false, showRecoil = false, showSpread = false;
  /**
   * Show reload time in seconds
   */
  public boolean showReloadTime = false;

  AttachmentType defaultSight = null;
  String defaultSightName = "ironSight2";

  //Shields
  //A shield is actually a gun without any shoot functionality (similar to knives or binoculars)
  //and a load of shield code on top. This means that guns can have in built shields (think Nerf Stampede)
  /**
   * Whether or not this gun has a shield piece
   */
  public boolean shield = false;
  /**
   * Shield collision box definition. In model co-ordinates
   */
  public Vector3f shieldOrigin, shieldDimensions;
  /**
   * Float between 0 and 1 denoting the proportion of damage blocked by the shield
   */
  public float shieldDamageAbsorption = 0F;

  //Sounds
  /**
   * The sound played upon shooting
   */
  public String shootSound;
  /**
   * The length of the sound for looping sounds
   */
  public int shootSoundLength;
  /**
   * Whether to distort the sound or not. Generally only set to false for looping sounds
   */
  public boolean distortSound = true;
  /**
   * The sound to play upon reloading
   */
  public String reloadSound;

  //Looping sounds
  /**
   * Whether the looping sounds should be used. Automatically set if the player sets any one of the
   * following sounds
   */
  public boolean useLoopingSounds = false;
  /**
   * Played when the player starts to hold shoot
   */
  public String warmupSound;
  public int warmupSoundLength = 20;
  /**
   * Played in a loop until player stops holding shoot
   */
  public String loopedSound;
  public int loopedSoundLength = 20;
  /**
   * Played when the player stops holding shoot
   */
  public String cooldownSound;


  /**
   * The sound to play upon weapon swing
   */
  public String meleeSound;
  /**
   * The sound to play while holding the weapon in the hand
   */
  public String idleSound;
  public int idleSoundLength;

  //Deployable Settings
  /**
   * If true, then the bullet does not shoot when right clicked, but must instead be placed on the
   * ground
   */
  public boolean deployable = false;
  /**
   * The deployable model
   */
  @SideOnly(Side.CLIENT)
  public ModelMG deployableModel;
  /**
   * The deployable model's texture
   */
  public String deployableTexture;
  /**
   * Various deployable settings controlling the player view limits and standing position
   */
  public float standBackDist = 1.5F, topViewLimit = -60F, bottomViewLimit = 30F, sideViewLimit = 45F, pivotHeight = 0.375F;

  //Default Scope Settings. Overriden by scope attachments
  //In many cases, this will simply be iron sights
  /**
   * Default scope overlay texture
   */
  public String defaultScopeTexture;
  /**
   * Whether the default scope has an overlay
   */
  public boolean hasScopeOverlay = false;
  /**
   * The zoom level of the default scope
   */
  public float zoomLevel = 1.0F;
  /**
   * The FOV zoom level of the default scope
   */
  public float FOVFactor = 1.5F;

  /**
   * For guns with 3D models
   */
  @SideOnly(Side.CLIENT)
  public ModelGun model;

  //Attachment settings
  /**
   * If this is true, then all attachments are allowed. Otherwise the list is checked
   */
  public boolean allowAllAttachments = false;
  /**
   * The list of allowed attachments for this gun
   */
  public ArrayList<AttachmentType> allowedAttachments = new ArrayList<AttachmentType>();
  /**
   * Whether each attachment slot is available
   */
  public boolean allowBarrelAttachments = false, allowScopeAttachments = false,
      allowStockAttachments = false, allowGripAttachments = false;
  /**
   * The number of generic attachment slots there are on this gun
   */
  public int numGenericAttachmentSlots = 0;

  /**
   * The static hashmap of all guns by shortName
   */
  public static HashMap<Integer, GunType> guns = new HashMap<Integer, GunType>();
  /**
   * The static list of all guns
   */
  public static ArrayList<GunType> gunList = new ArrayList<GunType>();

  //Modifiers
  /**
   * Speeds up or slows down player movement when this item is held
   */
  public float moveSpeedModifier = 1F;
  /**
   * Gives knockback resistance to the player
   */
  public float knockbackModifier = 0F;
  public float switchDelay = 7;
  public boolean silenced;


  public GunType(TypeFile file) {
    super(file);
  }

  @Override
  public void postRead(TypeFile file) {
    super.postRead(file);
    gunList.add(this);
    guns.put(shortName.hashCode(), this);

    if (!ammo.isEmpty() && ammo.get(0) instanceof BulletType) {
      BulletType.AmmoType ammoType = ((BulletType) ammo.get(0)).ammoType;
      if (ammoType != BulletType.AmmoType.DEFAULT) {
        description = "Caliber " + ammoType.name;
      }
    }
    if (switchDelay > 8 && moveSpeedModifier == 1) {
      moveSpeedModifier = Math.max(0.4f, 1 - (switchDelay - 8) / 100f);
    }

    if (defaultSightName != null) {
      defaultSight = AttachmentType.getAttachment(defaultSightName);
    }
  }

  @Override
  protected void read(String[] split, TypeFile file) {
    super.read(split, file);
    try {
      damage = Read(split, "Damage", damage);
      canForceReload = Read(split, "CanForceReload", canForceReload);
      reloadTime = Read(split, "ReloadTime", reloadTime);
      knockback = Read(split, "Knockback", knockback);
      bulletSpread = Read(split, "Accuracy", bulletSpread);
      silenced = Read(split, "silenced", silenced);
      bulletSpread = Read(split, "Spread", bulletSpread);
      numBullets = Read(split, "NumBullets", numBullets);
      consumeGunUponUse = Read(split, "ConsumeGunOnUse", consumeGunUponUse);
      dropItemOnShoot = Read(split, "DropItemOnShoot", dropItemOnShoot);
      numBurstRounds = Read(split, "NumBurstRounds", numBurstRounds);
      minigunStartSpeed = Read(split, "MinigunStartSpeed", minigunStartSpeed);
      defaultSightName = Read(split, "defaultSight", defaultSightName);
      if (split[0].equals("MeleeDamage")) {
        meleeDamage = Float.parseFloat(split[1]);
        if (meleeDamage > 0F) {
          secondaryFunction = EnumSecondaryFunction.MELEE;
        }
      }

      //Information
      showAttachments = Read(split, "ShowAttachments", showAttachments);
      showDamage = Read(split, "ShowDamage", showDamage);
      showRecoil = Read(split, "ShowRecoil", showRecoil);
      showSpread = Read(split, "ShowAccuracy", showSpread);
      showReloadTime = Read(split, "ShowReloadTime", showReloadTime);

      //Sounds
      shootDelay = Read(split, "ShootDelay", shootDelay);
      shootSoundLength = Read(split, "SoundLength", shootSoundLength);
      distortSound = Read(split, "DistortSound", distortSound);
      idleSoundLength = Read(split, "IdleSoundLength", idleSoundLength);
      warmupSoundLength = Read(split, "WarmupSoundLength", warmupSoundLength);
      loopedSoundLength = Read(split, "LoopedSoundLength", loopedSoundLength);
      loopedSoundLength = Read(split, "SpinSoundLength", loopedSoundLength);
      if (split[0].equals("ShootSound")) {
        shootSound = split[1];
        FlansMod.proxy.loadSound(contentPack, "guns", split[1]);
      } else if (split[0].equals("ReloadSound")) {
        reloadSound = split[1];
        FlansMod.proxy.loadSound(contentPack, "guns", split[1]);
      } else if (split[0].equals("NewRecoil") || split[0].equals("Recoil")) {
        try {
          if (split.length > 1) {
            recoil.read(split);
          }
        } catch (Exception e) {
          System.out.println("Failed to read recoil for " + shortName);
          e.printStackTrace();
        }
      } else if (split[0].equals("IdleSound")) {
        idleSound = split[1];
        FlansMod.proxy.loadSound(contentPack, "guns", split[1]);
      } else if (split[0].equals("MeleeSound")) {
        meleeSound = split[1];
        FlansMod.proxy.loadSound(contentPack, "guns", split[1]);
      }

      //Looping sounds
      else if (split[0].equals("WarmupSound")) {
        warmupSound = split[1];
        FlansMod.proxy.loadSound(contentPack, "guns", split[1]);
      } else if (split[0].equals("LoopedSound") || split[0].equals("SpinSound")) {
        loopedSound = split[1];
        useLoopingSounds = true;
        FlansMod.proxy.loadSound(contentPack, "guns", split[1]);
      } else if (split[0].equals("CooldownSound")) {
        cooldownSound = split[1];
        FlansMod.proxy.loadSound(contentPack, "guns", split[1]);
      }

      //Modes and zoom settings
      else if (split[0].equals("Mode")) {
        mode = EnumFireMode.getFireMode(split[1]);
      } else if (split[0].equals("Scope")) {
        hasScopeOverlay = true;
        if (split[1].equals("None")) {
          hasScopeOverlay = false;
        } else {
          defaultScopeTexture = split[1];
        }
      } else if (split[0].equals("ZoomLevel")) {
        zoomLevel = Float.parseFloat(split[1]);
        if (zoomLevel > 1F) {
          secondaryFunction = EnumSecondaryFunction.ZOOM;
        }
      } else if (split[0].equals("FOVZoomLevel")) {
        FOVFactor = Float.parseFloat(split[1]);
        if (FOVFactor > 1F) {
          secondaryFunction = EnumSecondaryFunction.ADS_ZOOM;
        }
      } else if (split[0].equals("Deployable")) {
        deployable = split[1].equals("True");
      } else if (FMLCommonHandler.instance().getSide().isClient() && deployable && split[0]
          .equals("DeployedModel")) {
        deployableModel = FlansMod.proxy.loadModel(split[1], shortName, ModelMG.class);
      } else if (FMLCommonHandler.instance().getSide().isClient() && (split[0].equals("Model"))) {
        model = FlansMod.proxy.loadModel(split[1], shortName, ModelGun.class);
      }

      deployableTexture = Read(split, "DeployedTexture", deployableTexture);
      standBackDist = Read(split, "StandBackDistance", standBackDist);
      topViewLimit = Read(split, "TopViewLimit", topViewLimit);
      bottomViewLimit = Read(split, "BottomViewLimit", bottomViewLimit);
      sideViewLimit = Read(split, "SideViewLimit", sideViewLimit);
      pivotHeight = Read(split, "PivotHeight", pivotHeight);
      numAmmoItemsInGun = Read(split, "NumAmmoSlots", numAmmoItemsInGun);
      numAmmoItemsInGun = Read(split, "NumAmmoItemsInGun", numAmmoItemsInGun);
      numAmmoItemsInGun = Read(split, "LoadIntoGun", numAmmoItemsInGun);
      canShootUnderwater = Read(split, "CanShootUnderwater", canShootUnderwater);
      oneHanded = Read(split, "OneHanded", oneHanded);
      usableByPlayers = Read(split, "UsableByPlayers", usableByPlayers);
      usableByMechas = Read(split, "UsableByMechas", usableByMechas);

      if (split[0].equals("Ammo")) {
        ShootableType type = ShootableType.getShootableType(split[1]);
        if (type != null) {
          ammo.add(type);
          if (type.explosionRadius <= 0F) {
            nonExplosiveAmmo.add(type);
          }
        } else {
          FlansMod.log("Could not find " + split[1] + " when reading ammo types for " + shortName);
        }
      } else if (split[0].equals("BulletSpeed")) {
        if (split[1].toLowerCase().equals("instant")) {
          bulletSpeed = 0.0f;
        } else {
          bulletSpeed = Float.parseFloat(split[1]);
        }

        //if(bulletSpeed > 3.0f)
        //	{
        //		bulletSpeed = 0.0f;
        //	}
      } else if (split[0].equals("SecondaryFunction")) {
        secondaryFunction = EnumSecondaryFunction.get(split[1]);
      }

      //Custom Melee Stuff
      else if (split[0].equals("UseCustomMelee") && Boolean.parseBoolean(split[1])) {
        secondaryFunction = EnumSecondaryFunction.CUSTOM_MELEE;
      }

      meleeTime = Read(split, "MeleeTime", meleeTime);

      if (split[0].equals("AddNode")) {
        meleePath.add(
            new Vector3f(Float.parseFloat(split[1]) / 16F, Float.parseFloat(split[2]) / 16F,
                Float.parseFloat(split[3]) / 16F));
        meleePathAngles.add(new Vector3f(Float.parseFloat(split[4]), Float.parseFloat(split[5]),
            Float.parseFloat(split[6])));
      } else if (split[0].equals("MeleeDamagePoint") || split[0].equals("MeleeDamageOffset")) {
        meleeDamagePoints.add(
            new Vector3f(Float.parseFloat(split[1]) / 16F, Float.parseFloat(split[2]) / 16F,
                Float.parseFloat(split[3]) / 16F));
      }

      switchDelay = Read(split, "switchDelay", switchDelay);

      //Player modifiers
      moveSpeedModifier = Read(split, "MoveSpeedModifier", moveSpeedModifier);
      moveSpeedModifier = Read(split, "Slowness", moveSpeedModifier);
      knockbackModifier = Read(split, "KnockbackReduction", knockbackModifier);
      knockbackModifier = Read(split, "KnockbackModifier", knockbackModifier);

      //Attachment settings
      allowAllAttachments = Read(split, "AllowAllAttachments", allowAllAttachments);
      if (split[0].equals("AllowAttachments")) {
        for (int i = 1; i < split.length; i++) {
          allowedAttachments.add(AttachmentType.getAttachment(split[i]));
        }
      }

      allowBarrelAttachments = Read(split, "AllowBarrelAttachments", allowBarrelAttachments);
      allowScopeAttachments = Read(split, "AllowScopeAttachments", allowScopeAttachments);
      allowStockAttachments = Read(split, "AllowStockAttachments", allowStockAttachments);
      allowGripAttachments = Read(split, "AllowGripAttachments", allowGripAttachments);
      numGenericAttachmentSlots = Read(split, "NumGenericAttachmentSlots",
          numGenericAttachmentSlots);

      //Shield settings
      if (split[0].toLowerCase().equals("shield")) {
        shield = true;
        shieldDamageAbsorption = Float.parseFloat(split[1]);
        shieldOrigin = new Vector3f(Float.parseFloat(split[2]) / 16F,
            Float.parseFloat(split[3]) / 16F, Float.parseFloat(split[4]) / 16F);
        shieldDimensions = new Vector3f(Float.parseFloat(split[5]) / 16F,
            Float.parseFloat(split[6]) / 16F, Float.parseFloat(split[7]) / 16F);
      }
    } catch (Exception e) {
      System.out.println("Reading gun file failed.");
      e.printStackTrace();
    }
  }

  public boolean isAmmo(ShootableType type) {
    return ammo.contains(type);
  }

  public boolean isAmmo(ItemStack stack) {
    if (stack == null) {
      return false;
    } else if (stack.getItem() instanceof ItemBullet) {
      return isAmmo(((ItemBullet) stack.getItem()).type);
    } else if (stack.getItem() instanceof ItemGrenade) {
      return isAmmo(((ItemGrenade) stack.getItem()).type);
    }
    return false;
  }

  /**
   * To be overriden by subtypes for model reloading
   */
  public void reloadModel() {
    model = FlansMod.proxy.loadModel(modelString, shortName, ModelGun.class);
  }

  @Override
  public float getZoomFactor() {
    return zoomLevel;
  }

  @Override
  public boolean hasZoomOverlay() {
    return hasScopeOverlay;
  }

  @Override
  public String getZoomOverlay() {
    return defaultScopeTexture;
  }

  @Override
  public float getFOVFactor() {
    return FOVFactor;
  }

  //ItemStack specific methods

  /**
   * Return the currently active scope on this gun. Search attachments, and by default, simply give
   * the gun
   */
  public IScope getCurrentScope(ItemStack gunStack) {
    IScope attachedScope = getScope(gunStack);
    return attachedScope == null ? this : attachedScope;
  }

  /**
   * Returns all attachments currently attached to the specified gun
   */
  public ArrayList<AttachmentType> getCurrentAttachments(ItemStack gun) {
    checkForTags(gun);
    ArrayList<AttachmentType> attachments = new ArrayList<AttachmentType>();
    NBTTagCompound attachmentTags = gun.getTagCompound().getCompoundTag("attachments");
    NBTTagList genericsList = attachmentTags
        .getTagList("generics", (byte) 10); //TODO : Check this 10 is correct
    for (int i = 0; i < numGenericAttachmentSlots; i++) {
      appendToList(gun, "generic_" + i, attachments);
    }
    appendToList(gun, "barrel", attachments);
    appendToList(gun, "scope", attachments);
    appendToList(gun, "stock", attachments);
    appendToList(gun, "grip", attachments);
    return attachments;
  }

  /**
   * Private method for attaching attachments to a list of attachments with a nullcheck
   */
  private void appendToList(ItemStack gun, String name, ArrayList<AttachmentType> attachments) {
    AttachmentType type = getAttachment(gun, name);
    if (type != null) {
      attachments.add(type);
    }
  }

  //Attachment getter methods
  public AttachmentType getBarrel(ItemStack gun) {
    return getAttachment(gun, "barrel");
  }

  public AttachmentType getScope(ItemStack gun) {

    AttachmentType t = getAttachment(gun, "scope");
    if (t != null) {
      return t;
    }
    return defaultSight;
  }

  public AttachmentType getStock(ItemStack gun) {
    return getAttachment(gun, "stock");
  }

  public AttachmentType getGrip(ItemStack gun) {
    return getAttachment(gun, "grip");
  }

  public AttachmentType getGeneric(ItemStack gun, int i) {
    return getAttachment(gun, "generic_" + i);
  }

  //Attachment ItemStack getter methods
  public ItemStack getBarrelItemStack(ItemStack gun) {
    return getAttachmentItemStack(gun, "barrel");
  }

  public ItemStack getScopeItemStack(ItemStack gun) {
    return getAttachmentItemStack(gun, "scope");
  }

  public ItemStack getStockItemStack(ItemStack gun) {
    return getAttachmentItemStack(gun, "stock");
  }

  public ItemStack getGripItemStack(ItemStack gun) {
    return getAttachmentItemStack(gun, "grip");
  }

  public ItemStack getGenericItemStack(ItemStack gun, int i) {
    return getAttachmentItemStack(gun, "generic_" + i);
  }

  /**
   * Generalised attachment getter method
   */
  public AttachmentType getAttachment(ItemStack gun, String name) {
    checkForTags(gun);
    return AttachmentType
        .getFromNBT(gun.getTagCompound().getCompoundTag("attachments").getCompoundTag(name));
  }

  /**
   * Generalised attachment ItemStack getter method
   */
  public ItemStack getAttachmentItemStack(ItemStack gun, String name) {
    checkForTags(gun);
    return ItemStack.loadItemStackFromNBT(
        gun.getTagCompound().getCompoundTag("attachments").getCompoundTag(name));
  }

  /**
   * Method to check for null tags and assign default empty tags in that case
   */
  private void checkForTags(ItemStack gun) {
    //If the gun has no tags, give it some
    if (!gun.hasTagCompound()) {
      gun.setTagCompound(new NBTTagCompound());
    }
    //If the gun has no attachment tags, give it some
    if (!gun.getTagCompound().hasKey("attachments")) {
      NBTTagCompound attachmentTags = new NBTTagCompound();
      for (int i = 0; i < numGenericAttachmentSlots; i++) {
        attachmentTags.setTag("generic_" + i, new NBTTagCompound());
      }
      attachmentTags.setTag("barrel", new NBTTagCompound());
      attachmentTags.setTag("scope", new NBTTagCompound());
      attachmentTags.setTag("stock", new NBTTagCompound());
      attachmentTags.setTag("grip", new NBTTagCompound());

      gun.getTagCompound().setTag("attachments", attachmentTags);
    }
  }

  /**
   * Get the melee damage of a specific gun, taking into account attachments
   */
  public float getMeleeDamage(ItemStack stack) {
    float stackMeleeDamage = meleeDamage;
    for (AttachmentType attachment : getCurrentAttachments(stack)) {
      stackMeleeDamage *= attachment.meleeDamageMultiplier;
    }
    return stackMeleeDamage;
  }

  /**
   * Get the damage of a specific gun, taking into account attachments
   */
  public float getDamage(ItemStack stack) {
    float stackDamage = damage * 2;
    if (stack != null) {
      for (AttachmentType attachment : getCurrentAttachments(stack)) {
        stackDamage *= attachment.damageMultiplier;
      }
    }
    return stackDamage;
  }

  /**
   * Get the bullet spread of a specific gun, taking into account attachments
   */
  public float getSpread(ItemStack stack) {
    float stackSpread = bulletSpread;
    for (AttachmentType attachment : getCurrentAttachments(stack)) {
      stackSpread *= attachment.spreadMultiplier;
    }
    return stackSpread;
  }

  /**
   * Get the recoil of a specific gun, taking into account attachments
   */
  public GunRecoil getRecoil(ItemStack stack) {
    GunRecoil stackRecoil = recoil.copy();
    for (AttachmentType attachment : getCurrentAttachments(stack)) {
      stackRecoil.applyModifier(attachment.recoilMultiplier);
    }
    return stackRecoil;
  }

  /**
   * Get the bullet speed of a specific gun, taking into account attachments
   */
	/*public float getBulletSpeed(ItemStack stack)
	{
		float stackBulletSpeed = bulletSpeed;
		for(AttachmentType attachment : getCurrentAttachments(stack))
		{
			stackBulletSpeed *= attachment.bulletSpeedMultiplier;
		}
		return stackBulletSpeed;
	}
	*/
  public float getBulletSpeed(ShootableType bulletType, ItemStack stack) {
    float stackBulletSpeed = bulletSpeed;
    if (bulletType instanceof BulletType
        && ((BulletType) bulletType).ammoType != BulletType.AmmoType.DEFAULT
        && !((BulletType) bulletType).ammoType.isMissile()) {
      stackBulletSpeed = ((BulletType) bulletType).ammoType.getSpeed();
    }
    if (stack != null) {
      for (AttachmentType attachment : getCurrentAttachments(stack)) {
        stackBulletSpeed *= attachment.bulletSpeedMultiplier;
      }
    }
    return stackBulletSpeed;
  }

  /**
   * Get the reload time of a specific gun, taking into account attachments
   */
  public float getReloadTime(ItemStack stack) {
    float stackReloadTime = reloadTime;
    for (AttachmentType attachment : getCurrentAttachments(stack)) {
      stackReloadTime *= attachment.reloadTimeMultiplier;
    }
    return stackReloadTime;
  }

  /**
   * Get the firing mode of a specific gun, taking into account attachments
   */
  public EnumFireMode getFireMode(ItemStack stack) {
    for (AttachmentType attachment : getCurrentAttachments(stack)) {
      if (attachment.modeOverride != null) {
        return attachment.modeOverride;
      }
    }
    return mode;
  }

  public float GetShootDelay(ItemStack stack) {
    for (AttachmentType attachment : getCurrentAttachments(stack)) {
      if (attachment.modeOverride == EnumFireMode.BURST) {
        return Math.max(shootDelay, 3);
      }
    }
    return shootDelay;
  }

  /**
   * Static String to GunType method
   */
  public static GunType getGun(String s) {
    return guns.get(s.hashCode());
  }

  public static GunType getGun(int hash) {
    return guns.get(hash);
  }

  @Override
  protected void preRead(TypeFile file) {
  }

  @Override
  @SideOnly(Side.CLIENT)
  public ModelBase GetModel() {
    return model;
  }

  @Override
  public float GetRecommendedScale() {
    return 60.0f;
  }

  public static class GunRecoil {

    public float vertical;
    public float horizontal;

    //how fast the upwards motion stops after shooting. lower is better. 0-2, default 1
    public float recovery;
    //modifies how much scoping modifies recovery (usually 20%). default 1%, lower is better, 0-2
    public float recoveryScope;
    //how much the aim goes back down after shooting. lower is better. 0-2, default 1f
    public float fall;
    //how much spraying increases recoil over time. lower is better
    public float increase;
    //modifies how much sneaking modifies recovery (usually 10%). default 1%, lower is better, 0-2
    public float sneak;
    //how much walking speed affects recoil. lower is bettr
    public float speed;

    public float sprayLength;
    public float antiRecoil;

    public GunRecoil(float vertical, float horizontal, float recovery, float recoveryScope,
        float fall, float increase, float sneak, float speed) {
      this.vertical = vertical;
      this.horizontal = horizontal;
      this.recovery = recovery;
      this.recoveryScope = recoveryScope;
      this.fall = fall;
      this.increase = increase;
      this.sneak = sneak;
      this.speed = speed;
    }

    //create empty recoil
    public GunRecoil(int n) {
      this(0, 0, 0, 0, 0, 0, 0, 0);
    }

    //default recoil
    public GunRecoil() {
      this(3, 2, 1, 1, 1, 1, 1, 1);
    }

    //copy
    public GunRecoil(GunRecoil gunRecoil) {
      this(gunRecoil.vertical, gunRecoil.horizontal, gunRecoil.recovery, gunRecoil.recoveryScope,
          gunRecoil.fall, gunRecoil.increase, gunRecoil.sneak, gunRecoil.speed);
    }

    public GunRecoil read(String[] split) {
      vertical = read(split, 1, vertical);
      horizontal = read(split, 2, horizontal);
      recovery = read(split, 3, recovery);
      recoveryScope = read(split, 4, recoveryScope);
      fall = read(split, 5, fall);
      increase = read(split, 6, increase);
      sneak = read(split, 7, sneak);
      speed = read(split, 8, speed);

      if (split.length < 2) {
        horizontal = vertical * 0.3f;
      }
      return this;
    }

    private float read(String[] split, int i, float alt) {
      return split.length > i ? Float.parseFloat(
          split[i].indexOf('=') == -1 ? split[i] : split[i].substring(split[i].indexOf('=') + 1))
          : alt;
    }

    public GunRecoil copy() {
      //
      //  if (horizontal == 2 && recovery == 1 && recoveryScope == 1 && sneak == 1 && speed == 1) {

      String rc = "Recoil 1.9 horizontal=1.9 recovery=1 recoveryScope=1 fall=0.9 increase=2 sneak=1 speed=1.2";
      //   if(true)
      //     return new GunRecoil().read(rc.split(" "));
      //     {}

      //     }

      return new GunRecoil(this);
    }

    public void applyModifier(float recoilMultiplier) {
      vertical *= recoilMultiplier;
      horizontal *= recoilMultiplier;
    }

    public void addRecoil(GunRecoil recoil) {
      Random rand = Minecraft.getMinecraft().theWorld.rand;
      this.vertical += recoil.vertical;
      this.horizontal += 0.2f * (Minecraft.getMinecraft().theWorld.rand.nextBoolean() ? -1 : 1)
          * recoil.horizontal;
      this.recovery = recoil.recovery;
      this.recoveryScope = recoil.recoveryScope;
      this.increase = recoil.increase;
      this.sneak = recoil.sneak;
      this.fall = recoil.fall;
      this.speed = recoil.speed;

      this.sprayLength += 0.05;
      vertical *= (1 + sprayLength * 2 * recoil.increase);
      horizontal *= (1 + sprayLength * 2 * recoil.increase);

      antiRecoil *= rand.nextFloat() * 0.1f;
    }

    public float update(boolean sneaking, boolean scoping, float playerSpeed) {
      Random rand = Minecraft.getMinecraft().theWorld.rand;

      float recov = 0.5F * recovery;
      if (sneaking) {
        recov *= 0.9f * sneak;
      }
      if (scoping) {
        recov *= 0.8f * recoveryScope;
      }

      if (vertical > 0) {
        vertical *= recov;
      }
      if (horizontal != 0) {
        horizontal *= recov;
      }

      sprayLength *= 0.95f;

      if (playerSpeed > 0.00f) {
        float speedMod = (1 + playerSpeed * speed);
        vertical *= speedMod;
        horizontal *= speedMod;
      }

      float anti = antiRecoil * 0.2f;

      antiRecoil *= 0.8F;
      antiRecoil += vertical * Math.max(0, Math.min(1, 1 - rand.nextFloat() * 0.2f - (fall - 1)));

      float add = -vertical + anti;
      return add;
    }
  }
}
