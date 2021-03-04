package com.flansmod.common.network;

import com.flansmod.apocalypse.common.entity.EntityFlansModShooter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSurvivorGunHeldState extends PacketBase {

    int id;
    int state;
    public PacketSurvivorGunHeldState() {

    }

    public PacketSurvivorGunHeldState(int id, int state) {
        this.id = id;
        this.state = state;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(id);
        data.writeInt(state);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        id = data.readInt();
        state = data.readInt();

    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientSide(EntityPlayer clientPlayer) {
        Entity survivor = Minecraft.getMinecraft().theWorld.getEntityByID(id);
        if (survivor instanceof EntityFlansModShooter) {
            ((EntityFlansModShooter) survivor).readAiState(state);
        }
    }
}
