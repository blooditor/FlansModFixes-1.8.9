package com.flansmod.common.network;

import com.flansmod.client.FlansModClient;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//When the client receives one, it "reloads". Basically to stop client side recoil effects when the gun should be in a reload animation
//When the server receives one, it is interpreted as a forced reload
public class PacketReload extends PacketBase {

  //false means animation only, true means reload finished
  public boolean doReload;
  public boolean isOffHand;
  public boolean isForced;
  String playername;

  public PacketReload() {
  }

  public PacketReload(boolean doReload, boolean isOffHand, boolean isForced) {
    this.doReload = doReload;
    this.isOffHand = isOffHand;
    this.isForced = isForced;
  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    data.writeBoolean(doReload);
    data.writeBoolean(isOffHand);
    data.writeBoolean(isForced);
    ByteBufUtils.writeUTF8String(data, playername == null ? "" : playername);
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    doReload = data.readBoolean();
    isOffHand = data.readBoolean();
    isForced = data.readBoolean();
    playername = ByteBufUtils.readUTF8String(data);
  }

  @Override
  public void handleServerSide(EntityPlayerMP playerEntity) {
    PlayerData data = PlayerHandler.getPlayerData(playerEntity);
    ItemStack stack = playerEntity.getCurrentEquippedItem();
    int slot = playerEntity.inventory.currentItem;
    if (isOffHand && data.offHandGunSlot != 0) {
      stack = playerEntity.inventory.getStackInSlot(data.offHandGunSlot - 1);
      slot = data.offHandGunSlot - 1;
    }
    if (data != null && stack != null && stack.getItem() instanceof ItemGun) {
      GunType type = ((ItemGun) stack.getItem()).GetType();

      if (((ItemGun) stack.getItem())
          .Reload(stack, playerEntity.worldObj, playerEntity, playerEntity.inventory, isOffHand,
              data.offHandGunSlot != 0, isForced, playerEntity.capabilities.isCreativeMode, doReload)) {

        //Set the reload delay and animation to other players
        if (!doReload) {

          data.shootTimeRight = data.shootTimeLeft = type.reloadTime;
          if (isOffHand) {
            data.reloadingLeft = type.reloadTime;
          } else {
            data.reloadingRight = type.reloadTime;
          }
          //Play reload sound
          if (type.reloadSound != null) {
            PacketPlaySound.sendSoundPacket(playerEntity.posX, playerEntity.posY, playerEntity.posZ, 30, playerEntity.dimension, type.reloadSound, false);
          }
          if (!isOffHand) {
            playername = playerEntity.getDisplayNameString();
            FlansMod.getPacketHandler()
                .sendToAllAround(this, playerEntity.posX, playerEntity.posY, playerEntity.posZ, 50,
                    playerEntity.dimension);
          }
        }
      }
    }
  }

  //never called
  @Deprecated
  @Override
  @SideOnly(Side.CLIENT)
  public void handleClientSide(EntityPlayer clientPlayer) {
    EntityPlayer player = clientPlayer.worldObj.getPlayerEntityByName(playername);
    if (player != null && player != clientPlayer) {
      ItemStack stack = player.getHeldItem();
      if (stack != null && stack.getItem() instanceof ItemGun) {
        ItemGun gun = (ItemGun) stack.getItem();
        if (gun == null || gun.type == null) {
          return;
        }

        GunAnimations anim = new GunAnimations(null);
        anim.reloading = true;
        anim.reloadAnimationTime = gun.type.getReloadTime(stack);
        FlansModClient.gunAnimationsRight.put(player, anim);
      }

    }
  }
}
