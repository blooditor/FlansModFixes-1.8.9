package com.flansmod.common.guns;

import com.flansmod.common.guns.raytracing.FlansModRaytracer.BulletHit;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.entity.Entity;


public class ShotData {

  public int slot; // May include special cases like deployable
  public InfoType shotFrom;
  public ShootableType shotType;
  public Vector3f origin;


  public ShotData(int slot, InfoType shotFrom, ShootableType shotType) {
    this.slot = slot;
    this.shotFrom = shotFrom;
    this.shotType = shotType;
  }

  public static class SpawnClientEntityShotData extends ShotData {

    public SpawnClientEntityShotData(int slot, InfoType shotFrom, ShootableType shotType,
        int shooterID, Vector3f origin, Vector3f direction, int clientBulletId) {
      super(slot, shotFrom, shotType);
      this.shooterID = shooterID;
      this.direction = new Vector3f[]{direction};
      this.clientBulletId = new int[]{clientBulletId};
      this.origin = origin;
    }

    public SpawnClientEntityShotData(int slot, InfoType shotFrom, ShootableType shotType,
        int shooterID, Vector3f origin, Vector3f[] direction, int[] clientBulletId) {
      super(slot, shotFrom, shotType);
      this.shooterID = shooterID;
      this.direction = direction;
      this.clientBulletId = clientBulletId;
      this.origin = origin;
    }

    public SpawnClientEntityShotData(int slot, InfoType shotFrom, ShootableType shotType,
        Entity shooter, Vector3f origin, Vector3f direction, int clientBulletId) {
      this(slot, shotFrom, shotType, shooter.getEntityId(), origin, direction, clientBulletId);
    }

    public int shooterID;
    public Vector3f[] direction;
    public int[] clientBulletId;

  }

  public static class InstantShotData extends ShotData {

    public InstantShotData(int slot, InfoType shotFrom, ShootableType shotType, int shooterID,
        Vector3f origin, BulletHit hitData, Vector3f hit, float damage, boolean isExtraBullet,
        boolean silenced) {
      super(slot, shotFrom, shotType);
      this.shooterID = shooterID;
      this.origin = origin;
      this.hitData = hitData;
      this.hitPos = hit;
      this.damage = damage;
      this.isExtraBullet = isExtraBullet;
      this.silenced = silenced;
    }

    public InstantShotData(int slot, InfoType shotFrom, ShootableType shotType, Entity shooter,
        Vector3f origin, BulletHit hitData, Vector3f hit, float damage, boolean isExtraBullet,
        boolean silenced) {
      this(slot, shotFrom, shotType, shooter.getEntityId(), origin, hitData, hit, damage,
          isExtraBullet, silenced);
    }

    public int shooterID;

    // Can be null for environment hits etc
    public BulletHit hitData;
    public Vector3f hitPos;

    public float damage;
    public boolean isExtraBullet; // For shotgun extra bullets that shouldn't consume ammo
    public boolean silenced;
  }
}
