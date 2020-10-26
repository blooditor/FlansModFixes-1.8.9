package com.flansmod.common.guns;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.EnumWeaponType;
import com.flansmod.common.types.TypeFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public class BulletType extends ShootableType {

  public static float BULLET_SPEED_MODIFIER = 0.4f;
  public static float BULLET_SPEED_LOSS_MODIFIER = 0.9f;
  /**
   * The number of flak particles to spawn upon exploding
   */
  public int flak = 0;
  /**
   * The type of flak particles to spawn
   */
  public String flakParticles = "largesmoke";

  /**
   * If true then this bullet will burn entites it hits
   */
  public boolean setEntitiesOnFire = false;

  /**
   * Exclusively for driveable usage. Replaces old isBomb and isShell booleans with something more
   * flexible
   */
  public EnumWeaponType weaponType = EnumWeaponType.NONE;

  public String hitSound;
  public float hitSoundRange = 50;

  public boolean hasLight = false;
  public float penetratingPower = 1F;
  /**
   * Lock on variables. If true, then the bullet will search for a target at the moment it is fired
   */
  public boolean lockOnToPlanes = false, lockOnToVehicles = false, lockOnToMechas = false, lockOnToPlayers = false, lockOnToLivings = false;
  /**
   * Lock on maximum angle for finding a target
   */
  public float maxLockOnAngle = 45F;
  /**
   * Lock on force that pulls the bullet towards its prey
   */
  public float lockOnForce = 1F;

  public String trailTexture = "defaultBulletTrail";

  public ArrayList<PotionEffect> hitEffects = new ArrayList<PotionEffect>();

  /**
   * The static bullets list
   */
  public static List<BulletType> bullets = new ArrayList<BulletType>();

  public AmmoType ammoType = AmmoType.DEFAULT;

  public BulletType(TypeFile file) {
    super(file);
    texture = "defaultBullet";
    bullets.add(this);
  }

  @Override
  public void read(String[] split, TypeFile file) {
    super.read(split, file);
    try {
      if (split[0].equals("FlakParticles")) {
        flak = Integer.parseInt(split[1]);
      } else if (split[0].equals("FlakParticleType")) {
        flakParticles = split[1];
      } else if (split[0].equals("SetEntitiesOnFire")) {
        setEntitiesOnFire = Boolean.parseBoolean(split[1]);
      } else if (split[0].equals("HitSound")) {
        hitSound = split[1];
        FlansMod.proxy.loadSound(contentPack, "sound", split[1]);
      } else if (split[0].equals("HitSoundRange")) {
        hitSoundRange = Float.parseFloat(split[1]);
      } else if (split[0].equals("Penetrates")) {
        penetratingPower = (Boolean.parseBoolean(split[1].toLowerCase()) ? 1F : 0.25F);
      } else if (split[0].equals("Penetration") || split[0].equals("PenetratingPower")) {
        penetratingPower = Float.parseFloat(split[1]);
      } else if (split[0].equals("Bomb")) {
        weaponType = EnumWeaponType.BOMB;
      } else if (split[0].equals("Shell")) {
        weaponType = EnumWeaponType.SHELL;
      } else if (split[0].equals("Missile")) {
        weaponType = EnumWeaponType.MISSILE;
      } else if (split[0].equals("WeaponType")) {
        weaponType = EnumWeaponType.valueOf(split[1].toUpperCase());
      } else if (split[0].equals("TrailTexture")) {
        trailTexture = split[1];
      } else if (split[0].equals("HasLight")) {
        hasLight = Boolean.parseBoolean(split[1].toLowerCase());
      } else if (split[0].equals("LockOnToDriveables")) {
        lockOnToPlanes = lockOnToVehicles = lockOnToMechas = Boolean
            .parseBoolean(split[1].toLowerCase());
      } else if (split[0].equals("LockOnToVehicles")) {
        lockOnToVehicles = Boolean.parseBoolean(split[1].toLowerCase());
      } else if (split[0].equals("LockOnToPlanes")) {
        lockOnToPlanes = Boolean.parseBoolean(split[1].toLowerCase());
      } else if (split[0].equals("LockOnToMechas")) {
        lockOnToMechas = Boolean.parseBoolean(split[1].toLowerCase());
      } else if (split[0].equals("LockOnToPlayers")) {
        lockOnToPlayers = Boolean.parseBoolean(split[1].toLowerCase());
      } else if (split[0].equals("LockOnToLivings")) {
        lockOnToLivings = Boolean.parseBoolean(split[1].toLowerCase());
      } else if (split[0].equals("MaxLockOnAngle")) {
        maxLockOnAngle = Float.parseFloat(split[1]);
      } else if (split[0].equals("LockOnForce") || split[0].equals("TurningForce")) {
        lockOnForce = Float.parseFloat(split[1]);
      } else if (split[0].equals("PotionEffect")) {
        hitEffects.add(getPotionEffect(split));
      } else if (split[0].equals("AmmoType")) {
        ammoType = AmmoType.ammoTypes.get(split[1]);
        if (ammoType == null) {
          FlansMod.log("Failed to read ammo type " + split[1] + " for " + shortName);
          ammoType = AmmoType.DEFAULT;
          throw new IllegalArgumentException(split[1]);
        } else {
          description = "Caliber " + ammoType.name;
        }
      }
    } catch (Exception e) {
      System.out.println("Reading bullet file failed.");
      e.printStackTrace();
    }
  }

  @Override
  public void postRead(TypeFile file) {
    super.postRead(file);
    if (ammoType == AmmoType.DEFAULT) {
      switch (weaponType) {
        case SHELL:
          ammoType = AmmoType.SHELL;
          break;
        case MISSILE:
          ammoType = AmmoType.MISSILE;
      }
    }
  }

  public static BulletType getBullet(String s) {
    for (BulletType bullet : bullets) {
      if (bullet.shortName.equals(s)) {
        return bullet;
      }
    }
    return null;
  }

  public static BulletType getBullet(Item item) {
    for (BulletType bullet : bullets) {
      if (bullet.item == item) {
        return bullet;
      }
    }
    return null;
  }

  /**
   * To be overriden by subtypes for model reloading
   */
  public void reloadModel() {
    model = FlansMod.proxy.loadModel(modelString, shortName, ModelBase.class);
  }

  public enum AmmoType {
    MM9("9mm", "9mm", 353, 0.8f, 0.9f, 1.1f, 0.8f, 1.3f),
    ACP45(".45 ACP", "acp", 274, 0.8f, 0.8f, 1.1f, 0.88f, 1.6f),
    MM556("5.56x45mm NATO", "5.56mm", 811, 0.8f, 1.3f, 1.1f), //m16 first before balance: 911, 0.9f, 1.3f, 1.1f
    MM762("7.62x51mm NATO", "7.62mm", 716, 0.9f, 1.55f, 0.85f, 1.1f), //sniper first before balance: 716, 0.8f, 1.5f, 0.9f
    MM762_39("7.62x39mm","7.62x39mm", 730, 0.77f, 1.35f, 1.1f, 1.05f), // 730, 0.8f, 1.3f, 1f
    C50(".50 BMG", ".50", 858, 0.94f, 2.2f, 0.7f, 1.45f), //barrett
    MM57("5.7x28mm", "5.7mm", 716, 0.8f, 1.3f, 1.1f, 0.9f), //p90 ammo
    MM545("5.45x39mm", "5.45mm", 756, 0.85f, 1.2f, 1.1f), //ak47 856, 0.85f, 1.2f, 1.2f
    C50AE(".50 AE", ".50ae", 450, 0.8f, 1.2f, 1.2f, 1, 1.3f), //deagle
    MM9_39("9x39mm", "9x39mm", 280, 0.85f, 3.5f, 1.05f, 0.7f), //vss
    C300(".300 AAC Blackout", ".300aac", 400, 0.01f, 3.5f, 1.05f, 0.8f), //honeybadger
    GAUGE("12 Gauge", "12gauge", 290, 0.27f, 0.5f, 2f, 1, 1.8f),
    GAUGESLUG("12 Gauge Slug", "12gaugeslug", 350, 0.54f, 1.4f, 1.1f, 1, 1.8f),
    GAUGEBUCK("12 Gauge Buckshot", "12gaugebuck", 300, 0.38f, 0.75f, 1.5f, 1, 1.9f),
    GAUGEFLETCHETTE("12 Gauge Fletchette", "12gaugefletchette", 450, 0.60f, 1.55f, 1f, 1f),
    C357(".357 Magnum", ".357", 400f, 0.80f, 1f, 1.3f, 1f, 1.3f),//jury
    MM30("30mm", "30mm", 1010f, 0.90f, 3f, 1f, 1.5f),
    MISSILE("Missile", "missile", 70, 1.015f, 1f, 1f, 1f, 3),
    MISSILE_SLOW("Missile", "missile_slow", 32, 0.98f, 1f, 1f, 1f, 3),
    MISSILE_FAST("Missile", "missile_fast", 90, 1.03f, 1f, 1f, 1f, 3),
    SHELL("Shell", "shell", 200, 0.90f, 3f, 1f, 1.6f, 2),
    SHELL_FAST("Shell", "shell_fast", 350, 0.90f, 3f, 1f, 1.4f, 2),
    //   MM30("30mm", "30mm", 1010f, 0.90f, 3f, 1f, 3f),
    //   MM70("70mm", "70mm", 0, 0.70f, 3f, 1f, 3f, 3),
    DEFAULT("default", "default", 400, 0.8f, 0.9f, 1, 1, 1.2f),


        /*

        penetration power:
        5.56: 59
        as val
        7.62 53
           9mm 10
           acp 7

         */;

    String name;
    String shortName;
    private float speed;
    private float speedLoss; //the percentage it loses every 100m
    private float penetration;
    private float penetrationLoss; //modify how much hitting objects affects penetration

    public float shootVolume = 1;
    private float nonPenetrationDamageModifier = 1;
    private boolean isMissile;


    public float getSpeed() {
      if (isMissile()) {
        return speed;
      }
      return speed * BULLET_SPEED_MODIFIER;
    }

    public float getPenetration() {
      return penetration;
    }

    public float getPenetrationLoss() {
      return penetrationLoss;
    }

    public float getNonPenetrationDamageModifier() {
      return nonPenetrationDamageModifier;
    }

    public float getSpeedLoss() {
      return speedLoss;
    }

    protected static HashMap<String, AmmoType> ammoTypes = new HashMap<>();

    static {
      for (AmmoType t : AmmoType.values()) {
        ammoTypes.put(t.shortName, t);
      }
    }

    AmmoType(String name, String shortName, float speed, float speedLoss, float penetration,
        float penetrationLoss) {
      this.name = name;
      this.shortName = shortName;
      this.speed = speed / 20f;
      isMissile = shortName.startsWith("missile");
      if (isMissile) {
        this.speedLoss = speedLoss;
      } else {
        this.speedLoss = (float) Math.pow(speedLoss * BULLET_SPEED_LOSS_MODIFIER, 1 / 100f);
      }
      this.penetration = penetration;
      this.penetrationLoss = penetrationLoss;
    }

    AmmoType(String name, String shortName, float speed, float speedLoss, float penetration,
        float penetrationLoss, float shootVolume) {
      this(name, shortName, speed, speedLoss, penetration, penetrationLoss);
      this.shootVolume = shootVolume;
    }

    AmmoType(String name, String shortName, float speed, float speedLoss, float penetration,
        float penetrationLoss, float shootVolume, float nonPenetrationDamageModifier) {
      this(name, shortName, speed, speedLoss, penetration, penetrationLoss, shootVolume);
      this.nonPenetrationDamageModifier = nonPenetrationDamageModifier;
    }

    public boolean isMissile() {
      return isMissile;
    }
  }
}
