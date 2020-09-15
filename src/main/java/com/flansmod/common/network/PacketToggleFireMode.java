package com.flansmod.common.network;

import com.flansmod.client.teams.ClientTeamsData;
import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.teams.RoundFinishedData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketToggleFireMode extends PacketBase {

  public PacketToggleFireMode() {

  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
  }

  @Override
  public void handleServerSide(EntityPlayerMP playerEntity) {
    ItemStack gunStack = playerEntity.getHeldItem();
    if (gunStack != null && gunStack.getItem() instanceof ItemGun) {
      GunType gun = ((ItemGun) gunStack.getItem()).type;
      if(gun.hasSingleFire && gun.mode == EnumFireMode.FULLAUTO){
        NBTTagCompound tag = gunStack.getTagCompound();
        if (tag == null) {
          tag = new NBTTagCompound();
        }
        String mode = "FULL AUTO";
        if (tag.hasKey("singleFire")) {
          tag.removeTag("singleFire");
        } else {
          tag.setBoolean("singleFire", true);
          mode = "SINGLE FIRE";
        }
        gunStack.setTagCompound(tag);

        S02PacketChat packet = new S02PacketChat(new ChatComponentText(EnumChatFormatting.GRAY +  mode), (byte) 2);
        playerEntity.playerNetServerHandler.sendPacket(packet);
      }
    }
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void handleClientSide(EntityPlayer clientPlayer) {
  }
}
