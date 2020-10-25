package com.flansmod.common.network;

import com.flansmod.client.FlansModClient;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketHitmark extends PacketBase {

  HitMarkType type;

  public PacketHitmark() {

  }

  public PacketHitmark(HitMarkType type) {
    this.type = type;
  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    data.writeByte(type.ordinal());
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    type = HitMarkType.values()[data.readByte()];
  }

  @Override
  public void handleServerSide(EntityPlayerMP playerEntity) {
  }

  @Override
  public void handleClientSide(EntityPlayer clientPlayer) {
    FlansModClient.AddHitMarker(type);
  }

  public static enum HitMarkType{
    INDIRECT,
    DIRECT,
    HEADSHOT
  }
}
