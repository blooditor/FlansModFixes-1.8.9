package com.flansmod.common.network;

import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.raytracing.FlansModRaytracer;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.BulletHit;
import com.flansmod.common.vector.Vector3f;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketClientBulletUpdate extends PacketBase {

  private List<BulletHit> hits;
  private int bulletId;
  private int penetrationResult = 0;

  public PacketClientBulletUpdate() {
  }

  public PacketClientBulletUpdate(List<BulletHit> hits, int bulletId, int penetrationResult) {
    this.hits = hits;
    this.bulletId = bulletId;
    this.penetrationResult = penetrationResult;
  }

  public PacketClientBulletUpdate(List<BulletHit> hits, int bulletId) {
    this.hits = hits;
    this.bulletId = bulletId;
  }

  public PacketClientBulletUpdate(BulletHit shotData) {
    this.hits = new ArrayList<BulletHit>();
    this.hits.add(shotData);
  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    data.writeInt(bulletId);
    data.writeInt(penetrationResult);
    data.writeInt(hits.size());
    for (int i = 0; i < hits.size(); i++) {
      BulletHit current = hits.get(i);
      FlansModRaytracer.WriteToBuffer(current, data);
    }
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    bulletId = data.readInt();
    penetrationResult = data.readInt();
    hits = new ArrayList<BulletHit>();
    int numEntries = data.readInt();
    for (int i = 0; i < numEntries; i++) {
      hits.add(FlansModRaytracer.ReadFromBuffer(data));
    }
  }

  @Override
  public void handleServerSide(EntityPlayerMP player) {
    if (!player.isEntityAlive()) {
      return;
    }

    PlayerData data = PlayerHandler.getPlayerData(player, Side.SERVER);

    EntityBullet bullet = data.controllingBullets.get(bulletId);

    if (bullet == null) {
      EntityBullet.log("Player " + player.getDisplayNameString() + " sent illegal bullet update");
      return;
    }
    if (bullet.isDead) {
      return;
    }

    Vector3f origin = new Vector3f(bullet.posX, bullet.posY, bullet.posZ);
    Vector3f motion = new Vector3f(bullet.motionX, bullet.motionY, bullet.motionZ);
    for (BulletHit entry : hits) {
      if (entry == null) {
        continue;
      }

      float speed = bullet.getBulletSpeed();
      float penetratingPower =
          speed * bullet.type.ammoType.getPenetration() / BulletType.BULLET_SPEED_MODIFIER;

      float relSpeed = (speed / bullet.originalSpeed);
      relSpeed = Math.min(1, relSpeed + 0.5f);
      float damage = relSpeed * bullet.damage;

      if (entry instanceof FlansModRaytracer.DriveableHit) {
        float modifier = entry.GetEntity() instanceof EntityPlane ? bullet.type.damageVsPlane
            : bullet.type.damageVsDriveable;
        if (bullet.firedFrom instanceof DriveableType) {
          damage *= modifier;
        } else {
          damage *= modifier > 10 ? modifier : 0;
        }
      } else if (entry instanceof FlansModRaytracer.EntityHit
          || entry instanceof FlansModRaytracer.PlayerBulletHit) {
        damage *= bullet.type.damageVsLiving;
      }

      //TODO ricochet
      if (bullet
          .doHit(origin, entry.hitPos, player, bullet.firedFrom, bullet.type, entry, speed, damage,
              penetratingPower) == 1) {
        //  bullet.setDead();
        //  break;
      }
    }
    if (penetrationResult == 1) {
      bullet.setDead();
    }
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void handleClientSide(EntityPlayer clientPlayer) {

  }
}
