package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveablePosition;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.PilotGun;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ShootableType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketVehicleFireMode extends PacketBase {

  public List<FireModeWeapon> modes = new ArrayList<>();
  private int currentMode = 0;
  public boolean secondary;

  public PacketVehicleFireMode() {

  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    data.writeByte(getCurrentMode());
    for (FireModeWeapon t : modes) {
      data.writeInt(t.bullet.hashCode());
      data.writeInt(t.ammoLeft);
    }
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    currentMode = data.readByte();
    while (data.isReadable()) {
      ShootableType b = ShootableType.getShootableType(data.readInt());
      int c = data.readInt();
      if (b instanceof BulletType) {
        modes.add(new FireModeWeapon((BulletType) b, c));
      }
    }
  }

  @Override
  public void handleServerSide(EntityPlayerMP playerEntity) {
    if (playerEntity.ridingEntity instanceof EntitySeat) {
      EntityDriveable driveable = ((EntitySeat) playerEntity.ridingEntity).driveable;
      if (driveable != null && ((EntitySeat) playerEntity.ridingEntity).seatInfo != null &&  ((EntitySeat) playerEntity.ridingEntity).seatInfo.id == 0 && driveable.fireMode != null) {
        driveable.fireMode.setCurrentMode(MathHelper.clamp_int(this.getCurrentMode(), 0, driveable.fireMode.modes.size()-1), driveable);
      //  driveable.fireMode.setCurrentMode(this.getCurrentMode());
    //    if(getCurrentShootable() != null)
      //    playerEntity.addChatMessage(new ChatComponentText("Switched to " + this.getCurrentMode() + " " + getCurrentShootable().name));
      }
    }
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void handleClientSide(EntityPlayer clientPlayer) {
    if (clientPlayer.ridingEntity instanceof EntitySeat) {
      EntityDriveable driveable = ((EntitySeat) clientPlayer.ridingEntity).driveable;
      if (driveable != null) {
        driveable.fireMode = this;
      }
    }
  }

  public void addModes(EntityDriveable driveable, ItemStack[] ammo) {
    for (ItemStack stack : ammo) {
      if (stack != null && stack.getItem() instanceof ItemBullet && stack.stackSize > 0) {
        BulletType shootableType = ((ItemBullet) stack.getItem()).type;
        int rounds = (stack.stackSize-1)*stack.getItem().getMaxDamage()  + (stack.getItem().getMaxDamage()-stack.getItemDamage());
        FireModeWeapon w = new FireModeWeapon(shootableType, rounds);
        if (!modes.contains(w)) {
          if (driveable.getDriveableType().ammo.contains(shootableType)) {
            modes.add(w);
            continue;
          }
          for (PilotGun gun : driveable.getDriveableType().pilotGuns) {
            if (gun.type != null && gun.type.isAmmo(shootableType)) {
              modes.add(w);
              break;
            }
          }
        } else {
          modes.get(modes.indexOf(w)).ammoLeft += w.ammoLeft;
        }
      }
    }
  }

  public int getCurrentMode() {
    return currentMode;
  }

  public void setCurrentMode(int currentMode, EntityDriveable driveable) {
    this.currentMode = currentMode;

    FireModeWeapon type = getCurrentShootable();
    if (type != null) {
      if (driveable.getDriveableType().primary == type.bullet.weaponType) {
        secondary = false;
      } else {
        secondary = true;
      }
      for (DriveablePosition p : driveable.getDriveableType().shootPointsPrimary) {
        if (p instanceof PilotGun) {
          if (((PilotGun) p).type != null && ((PilotGun) p).type.isAmmo(type.bullet)) {
            secondary = false;
            return;
          }
        }
      }
      for (DriveablePosition p : driveable.getDriveableType().shootPointsSecondary) {
        if (p instanceof PilotGun) {
          if (((PilotGun) p).type != null && ((PilotGun) p).type.isAmmo(type.bullet)) {
            secondary = true;
            return;
          }
        }
      }
    } else {
      secondary = false;
    }
  }

  public FireModeWeapon getCurrentShootable() {
    return currentMode >= 0 && modes.size() > currentMode? modes.get(currentMode) : null;
  }

  //client side press
  public void pressToggleButton(EntityDriveable driveable) {
    if(modes.size() == 0)
      return;
    this.setCurrentMode((currentMode+1)%modes.size(), driveable);
    FlansMod.getPacketHandler().sendToServer(this);
  }
  //client side press
  public void switchMode(EntityDriveable driveable, int add) {
    if(modes.size() == 0)
      return;
    int m = (currentMode + add)%modes.size();
    this.setCurrentMode(m < 0? modes.size()+m : m, driveable);
    FlansMod.getPacketHandler().sendToServer(this);
  }
  //client side press
  public void trySetMode(int mode, EntityDriveable driveable) {
    if(modes.size() == 0 || mode < 0 || mode >= modes.size())
      return;
    this.setCurrentMode(mode, driveable);
    FlansMod.getPacketHandler().sendToServer(this);
  }
  public class FireModeWeapon{
    public BulletType bullet;
    public int ammoLeft;

    public FireModeWeapon(BulletType bullet, int ammoLeft) {
      this.bullet = bullet;
      this.ammoLeft = ammoLeft;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null) {
        return false;
      }
      if (getClass() != o.getClass()) {
        if (o instanceof ShootableType) {
          return o.equals(this.bullet);
        }
        return false;
      }

      FireModeWeapon that = (FireModeWeapon) o;

      return bullet.equals(that.bullet);
    }

    @Override
    public int hashCode() {
      return bullet.hashCode();
    }
  }
}
