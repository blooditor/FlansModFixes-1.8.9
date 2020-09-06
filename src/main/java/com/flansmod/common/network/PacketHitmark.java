package com.flansmod.common.network;

import com.flansmod.client.FlansModClient;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketHitmark extends PacketBase {

  boolean headshot;

  public PacketHitmark() {

  }

  public PacketHitmark(boolean headshot) {
    this.headshot = headshot;
  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    data.writeBoolean(headshot);
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    headshot = data.readBoolean();
  }

  @Override
  public void handleServerSide(EntityPlayerMP playerEntity) {
  }

  @Override
  public void handleClientSide(EntityPlayer clientPlayer) {
    FlansModClient.AddHitMarker(headshot);
  }

}
