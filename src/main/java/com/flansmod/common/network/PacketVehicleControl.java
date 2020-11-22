package com.flansmod.common.network;

import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.EntityWheel;
import com.flansmod.common.vector.Vector3f;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.Vec3;

public class PacketVehicleControl extends PacketDriveableControl {

  public boolean doors;
  Vector3f[] wheels;

  public PacketVehicleControl() {
    wheels = new Vector3f[12];
  }

  public PacketVehicleControl(EntityDriveable driveable) {
    super(driveable);
    EntityVehicle vehicle = (EntityVehicle) driveable;
    doors = vehicle.varDoor;

    wheels = new Vector3f[vehicle.wheels.length];
    for (int i = 0; i < wheels.length; i++) {
      if(vehicle.wheels[i] != null)
        wheels[i] = new Vector3f(vehicle.wheels[i].getPositionVector());
    }
  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    super.encodeInto(ctx, data);
    data.writeBoolean(doors);
    for (int i = 0; i < wheels.length; i++) {
      if (wheels[i] == null) {
        data.writeFloat(Float.NaN);
      } else {
        data.writeFloat(wheels[i].x);
        data.writeFloat(wheels[i].y);
        data.writeFloat(wheels[i].z);
      }
    }
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    super.decodeInto(ctx, data);
    doors = data.readBoolean();
    for (int i = 0; i < wheels.length && data.isReadable(); i++) {
      float x = data.readFloat();
      if (!Float.isNaN(x)) {
        float y = data.readFloat();
        float z = data.readFloat();
        wheels[i] = new Vector3f(x,y,z);
      }
    }
  }

  @Override
  protected void updateDriveable(EntityDriveable driveable, boolean clientSide) {
    super.updateDriveable(driveable, clientSide);
    EntityVehicle vehicle = (EntityVehicle) driveable;
    vehicle.varDoor = doors;

      for (int i = 0; i < vehicle.wheels.length; i++) {
        if (vehicle.wheels[i] != null && wheels[i] != null) {
          vehicle.wheels[i].setPosition(wheels[i].x, wheels[i].y, wheels[i].z);
      }
    }
  }
}
