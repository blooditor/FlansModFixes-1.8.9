package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.guns.ShotData;
import com.flansmod.common.guns.ShotData.InstantShotData;
import com.flansmod.common.guns.ShotData.SpawnClientEntityShotData;
import com.flansmod.common.guns.raytracing.FlansModRaytracer;
import com.flansmod.common.guns.raytracing.FlansModRaytracer.BulletHit;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketShotData extends PacketBase {

  private List<ShotData> shotData;

  public PacketShotData() {
  }

  public PacketShotData(List<ShotData> shotData) {
    this.shotData = shotData;
  }

  public PacketShotData(ShotData shotData) {
    this.shotData = new ArrayList<ShotData>();
    this.shotData.add(shotData);
  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    data.writeInt(shotData.size());
    for (int i = 0; i < shotData.size(); i++) {
      ShotData current = shotData.get(i);
      data.writeByte((byte) current.slot);
      data.writeInt(current.shotFrom.hashCode());
      data.writeInt(current.shotType.hashCode());

      if (current instanceof ShotData.SpawnClientEntityShotData) {
        ShotData.SpawnClientEntityShotData currentCast = (ShotData.SpawnClientEntityShotData) current;
        data.writeByte(0);
        data.writeInt(currentCast.shooterID);
        currentCast.origin.writeToBuffer(data);
        data.writeInt(currentCast.direction.length);
        for (Vector3f v : currentCast.direction) {
          v.writeToBuffer(data);
        }
        for (int k = 0; k < currentCast.clientBulletId.length; k++) {
          data.writeInt(currentCast.clientBulletId[k]);
        }
      } else if (current instanceof InstantShotData) {
        InstantShotData currentCast = (InstantShotData) current;
        data.writeByte(1);
        data.writeInt(currentCast.shooterID);
        currentCast.origin.writeToBuffer(data);
        FlansModRaytracer.WriteToBuffer(currentCast.hitData, data);
        currentCast.hitPos.writeToBuffer(data);
        data.writeFloat(currentCast.damage);
        data.writeBoolean(currentCast.isExtraBullet);
        data.writeBoolean(currentCast.silenced);
      } else {
        data.writeByte(2);
      }
    }
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    shotData = new ArrayList<ShotData>();
    int numEntries = data.readInt();
    for (int i = 0; i < numEntries; i++) {
      // Lookup types by hash
      byte slot = data.readByte();
      InfoType shotFrom = InfoType.getType(data.readInt());
      ShootableType shotType = ShootableType.getShootableType(data.readInt());

      byte dataType = data.readByte();
      switch (dataType) {
        case 0: // SpawnClientEntityShotData
        {
          int shooterID = data.readInt();
          Vector3f origin = Vector3f.readFromBuffer(data);

          int len = data.readInt();
          Vector3f[] direction = new Vector3f[len];
          int[] clientBulletId = new int[len];
          for (int k = 0; k < len; k++) {
            direction[k] = Vector3f.readFromBuffer(data);
          }
          for (int k = 0; k < len; k++) {
            clientBulletId[k] = data.readInt();
          }
          shotData.add(
              new SpawnClientEntityShotData(slot, shotFrom, shotType, shooterID, origin, direction,
                  clientBulletId));
          break;
        }
        case 1: // InstantShotData
        {
          int shooterID = data.readInt();
          Vector3f origin = Vector3f.readFromBuffer(data);
          BulletHit hitData = FlansModRaytracer.ReadFromBuffer(data);
          Vector3f hit = Vector3f.readFromBuffer(data);
          float damage = data.readFloat();
          boolean isExtraBullet = data.readBoolean();
          boolean silenced = data.readBoolean();

          shotData.add(
              new InstantShotData(slot, shotFrom, shotType, shooterID, origin, hitData, hit, damage,
                  isExtraBullet, silenced));
          break;
        }
        case 2:
          shotData.add(new ShotData(slot, shotFrom, shotType));
        default:
          break;
      }
    }
  }

  @Override
  public void handleServerSide(EntityPlayerMP player) {
    PlayerData data = PlayerHandler.getPlayerData(player, Side.SERVER);

    for (ShotData entry : shotData) {
      if (entry.slot == -1) {
        if (entry.shotFrom instanceof GunType) {
          ((ItemGun) entry.shotFrom.item)
              .ServerHandleShotData(null, entry.slot, player.worldObj, player, false, entry);
        }
      } else {
        ItemStack gunStack = player.inventory.getStackInSlot(entry.slot);
        if (gunStack != null && gunStack.getItem() instanceof ItemGun) {
          ItemGun gunItem = (ItemGun) gunStack.getItem();
          boolean isOffHand = (data.offHandGunSlot + 1 == entry.slot);
          gunItem.ServerHandleShotData(gunStack, entry.slot, player.worldObj, player, isOffHand,
              entry);
        }
      }
    }
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void handleClientSide(EntityPlayer clientPlayer) {
    for (ShotData entry : shotData) {
      if (entry instanceof InstantShotData) {
        InstantShotData instantData = ((InstantShotData) entry);
        if (entry.shotFrom instanceof GunType) {
          ItemGun gunItem = (ItemGun) entry.shotFrom.getItem();

          gunItem.DoInstantShot(clientPlayer.worldObj,
              FlansModRaytracer.GetEntityByID(instantData.shooterID),
              instantData.shotFrom, (BulletType) instantData.shotType,
              instantData.origin, instantData.hitPos,
              instantData.hitData, instantData.damage,
              instantData.isExtraBullet,
              instantData.silenced);
        }
      } else {
        FlansMod.log("Only expect instant shot types on client side.");
      }
    }
  }
}
