package com.flansmod.apocalypse.common.network;

import com.flansmod.apocalypse.client.GuiNPC;
import com.flansmod.common.network.PacketBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class PacketNpcEdit extends PacketBase {

  public String name;
  public byte mode;
  public int entity;
  public int aiLevel;

  public PacketNpcEdit() {

  }

  public PacketNpcEdit(String name, byte mode, Entity entity, int aiLevel) {
    this.name = name;
    this.mode = mode;
    this.entity = entity.getEntityId();
    this.aiLevel = aiLevel;
  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    ByteBufUtils.writeUTF8String(data, name);
    data.writeByte(mode);
    data.writeInt(entity);
    data.writeInt(aiLevel);
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {

    name = ByteBufUtils.readUTF8String(data);
    mode = data.readByte();
    entity = data.readInt();
    aiLevel = data.readInt();
  }

  @Override
  public void handleServerSide(EntityPlayerMP playerEntity) {
    //MCB server side only
  }

  @Override
  public void handleClientSide(EntityPlayer clientPlayer) {
    if (Minecraft.getMinecraft().currentScreen instanceof GuiNPC) {
      ((GuiNPC) Minecraft.getMinecraft().currentScreen).receivePacket(this);
    }
  }
}
