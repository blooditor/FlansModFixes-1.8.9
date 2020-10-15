package com.flansmod.common.network;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.common.FlansMod;
import com.flansmod.common.FlansModSounds;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ShootableType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketPlaySound extends PacketBase {

  public static Random rand = new Random();
  public float posX, posY, posZ;
  public String sound;
  public int origin;
  public float volume = 10;
  public boolean silenced;

  public PacketPlaySound() {
  }

  public PacketPlaySound(float posX, float posY, float posZ, String sound, int origin, float volume, boolean silenced) {
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.sound = sound;
    this.origin = origin;
    this.volume = volume;
    this.silenced = silenced;
  }

  public PacketPlaySound(float posX, float posY, float posZ, String sound) {
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.sound = sound;
  }


  public static void sendSoundPacket(double x, double y, double z, double range, int dimension,
      String s, boolean distort) {
    if(s == null)
      return;
    FlansMod.getPacketHandler()
        .sendToAllAround(new PacketPlaySound((float)x, (float)y, (float)z, s), x, y, z, (float) range,
            dimension);
  }

  //for vehicles
  public static void sendAdvancedSound(Entity shooter, GunType type, ShootableType bullet,
      boolean silenced, boolean sendToShooter) {
    sendAdvancedSound(shooter, type.shortName, bullet, silenced, sendToShooter);
  }
  //for guns
  public static void sendAdvancedSound(Entity shooter, String sound, ShootableType bullet,
      boolean silenced, boolean sendToShooter) {
    float chunkRange = 12;
    if (bullet instanceof BulletType) {
      chunkRange *= ((BulletType) bullet).ammoType.shootVolume;
    }
    sendAdvancedSound(shooter, silenced, "sound_" + sound, chunkRange, sendToShooter);
  }

  //bombs, shells, detonate sounds, idle sounds
  public static void sendAdvancedSound(Entity shooter, String shootSound, int chunkRange) {
    sendAdvancedSound(shooter, false,"sound_" + shootSound, chunkRange, true);
  }

  //explosion
  public static void sendAdvancedSound(Entity shooter, String shootSound, int chunkRange,
      float pitch) {
    sendAdvancedSound(shooter, false, shootSound, chunkRange, true);
  }

  public static void sendAdvancedSound(Entity shooter, boolean silenced, String shootSound,
      float chunkRange, boolean sendToShooter) {

    if (shootSound == null) {
      return;
    }

    if (shooter.worldObj.isRemote) {
      return;
    }
    PacketPlaySound soundPacket = new PacketPlaySound((float)shooter.posX, (float)shooter.posY,
        (float)shooter.posZ, shootSound, shooter.getEntityId(), chunkRange,  silenced);
    FlansMod.getPacketHandler().sendToAllAround(soundPacket, shooter.posX, shooter.posY, shooter.posZ, chunkRange*16, shooter.dimension);
  }


  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    data.writeFloat(posX);
    data.writeFloat(posY);
    data.writeFloat(posZ);
    writeUTF(data, sound);

    if(origin == -1)
      return;
    data.writeInt(origin);
    data.writeFloat(volume);
    data.writeBoolean(silenced);
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    posX = data.readFloat();
    posY = data.readFloat();
    posZ = data.readFloat();
    sound = readUTF(data);
    if(!data.isReadable())
      return;
    origin = data.readInt();
    volume = data.readFloat();
    silenced = data.readBoolean();
  }

  @Override
  public void handleServerSide(EntityPlayerMP playerEntity) {
    FlansMod.log("Received play sound packet on server. Skipping.");
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void handleClientSide(EntityPlayer clientPlayer) {
    if (origin == -1) {
      FMLClientHandler.instance().getClient().getSoundHandler().playSound(
          new PositionedSoundRecord(FlansModResourceHandler.getSound(sound), volume,
              1.0F, posX, posY, posZ));
    } else {

      FlansModSounds.PlaySound(posX, posY, posZ, sound, volume, origin, silenced);
    }
  }


}
