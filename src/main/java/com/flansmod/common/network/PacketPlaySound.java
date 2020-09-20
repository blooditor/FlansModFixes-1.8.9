package com.flansmod.common.network;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.common.FlansMod;
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
  public boolean distort;

  public PacketPlaySound() {
  }

  public static void sendSoundPacket(double x, double y, double z, double range, int dimension,
      String s, boolean distort) {
    FlansMod.getPacketHandler()
        .sendToAllAround(new PacketPlaySound(x, y, z, s, distort), x, y, z, (float) range,
            dimension);
  }

  public PacketPlaySound(double x, double y, double z, String s) {
    this(x, y, z, s, false);
  }


  public PacketPlaySound(double x, double y, double z, String s, boolean distort) {
    posX = (float) x;
    posY = (float) y;
    posZ = (float) z;
    sound = s;
    this.distort = distort;
  }

  //for guns
  public static void sendAdvancedSound(Entity shooter, GunType type, ShootableType bullet,
      boolean silenced, boolean sendToShooter) {
    float chunkRange = 12;
    if (bullet instanceof BulletType) {
      chunkRange *= ((BulletType) bullet).ammoType.shootVolume;
    }
    float pitch = 1;
    if (type.distortSound) {
      pitch *= 1.0F / (rand.nextFloat() * 0.4F + 0.8F);
    }
    sendAdvancedSound(shooter, silenced, type.shootSound, chunkRange, pitch, sendToShooter);
  }

  //bombs, shells, detonate sounds, idle sounds
  public static void sendAdvancedSound(Entity shooter, String shootSound, int chunkRange) {
    sendAdvancedSound(shooter, false, shootSound, chunkRange, 1, true);
  }

  //explosion
  public static void sendAdvancedSound(Entity shooter, String shootSound, int chunkRange,
      float pitch) {
    sendAdvancedSound(shooter, false, shootSound, chunkRange, pitch, true);
  }
  public static void sendAdvancedSound(Entity shooter, boolean silenced, String shootSound,
      float chunkRange, float pitch, boolean sendToShooter) {

    String distShootSound = FlansModResourceHandler.soundsWithDistFile.contains(shootSound) ? shootSound + "-dist" : null;
    if (shootSound == null) {
      return;
    }

    float volume = chunkRange; //1 chunk for each vol
    if (silenced) {
      volume *= 0.25f;
    }

    pitch *= silenced ? 1.75f : 1f;

    float soundSpeed = 15 * BulletType.BULLET_SPEED_MODIFIER;
    float soundDist = volume * 16;

    if (shooter.worldObj.isRemote) {
      playSoundClient(shootSound, volume, pitch, shooter);
      return;
    }

    for (Object player : shooter.worldObj.playerEntities) {
      EntityPlayerMP p = (EntityPlayerMP) player;
      if (!sendToShooter && p == shooter) {
        continue;
      }
      float dist = p.getDistanceToEntity(shooter);

      if (dist > soundDist) {
        if(distShootSound == null || dist > soundDist*1.5f)
          continue;
      }

      int delay = (int) (dist / soundSpeed);

      float distVol = volume;
      float distPitch = 1;
      if (distShootSound != null) {
        if (delay > 0) {
          float relDist = dist / soundDist;
        //  distVol *= relDist  * 1.5f;
          distVol = volume*1.5f;
         // volume *= 1.6f - relDist;
          distPitch = pitch - (dist / soundDist) * 0.2f;
        } else {
          distShootSound = null;
        }
      }

      //   float vol = volume * Math.min((soundDist / dist * 3  + 0.9f), 1); //TODO better curve to simulate sound distance vol
      float vol = volume; //TODO better curve to simulate sound distance vol
      //float pit = pitch;
      float pit = pitch - (dist / soundDist) * 0.5f;
   //   System.out.println("Normal " + vol + "   dist: " + (distShootSound == null? 0 : distVol));
      PacketPlaySoundAdvanced soundPacket = new PacketPlaySoundAdvanced(shooter.posX, shooter.posY,
          shooter.posZ, shootSound,  false, vol, pit, distShootSound, distVol, distPitch, delay);
      FlansMod.getPacketHandler().sendTo(soundPacket, p);
    }

  }

  @SideOnly(Side.CLIENT)
  private static void playSoundClient(String shootSound, float volume, float pitch,
      Entity shooter) {
    if (shootSound == null) {
      return;
    }
    ResourceLocation resourceLocation =
        shootSound.startsWith("minecraft:") ? new ResourceLocation(shootSound.substring(10))
            : FlansModResourceHandler.getSound(shootSound);
    FMLClientHandler.instance().getClient().getSoundHandler().playSound(
        new PositionedSoundRecord(resourceLocation, volume, pitch, (float) shooter.posX,
            (float) shooter.posY, (float) shooter.posZ));

  }

  @Override
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    data.writeFloat(posX);
    data.writeFloat(posY);
    data.writeFloat(posZ);
    writeUTF(data, sound);
    data.writeBoolean(distort);
  }

  @Override
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
    posX = data.readFloat();
    posY = data.readFloat();
    posZ = data.readFloat();
    sound = readUTF(data);
    distort = data.readBoolean();
  }

  @Override
  public void handleServerSide(EntityPlayerMP playerEntity) {
    FlansMod.log("Received play sound packet on server. Skipping.");
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void handleClientSide(EntityPlayer clientPlayer) {
    FMLClientHandler.instance().getClient().getSoundHandler().playSound(
        new PositionedSoundRecord(FlansModResourceHandler.getSound(sound), 10F,
            (distort ? 1.0F / (rand.nextFloat() * 0.4F + 0.8F) : 1.0F), posX, posY, posZ));
  }

  public static class PacketPlaySoundAdvanced extends PacketPlaySound {

    float volume;
    float pitch;
    int delay;
    String distSound;
    float distVolume;
    float distPitch;

    public PacketPlaySoundAdvanced() {

    }

    public PacketPlaySoundAdvanced(double x, double y, double z, String s, boolean distort,
        float volume, float pitch, String distSound, float distVolume, float distPitch, int delay) {
      super(x, y, z, s, distort);
      this.volume = volume;
      this.pitch = pitch;
      this.delay = delay;
      this.distSound = distSound;
      this.distVolume = distVolume;
      this.distPitch = distPitch;
    }
    public PacketPlaySoundAdvanced(double x, double y, double z, String s, boolean distort,
        float volume, float pitch, int delay) {
      super(x, y, z, s, distort);
      this.volume = volume;
      this.pitch = pitch;
      this.delay = delay;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      super.encodeInto(ctx, data);
      data.writeFloat(volume);
      data.writeFloat(pitch);
      data.writeInt(delay);

      if (distSound != null) {
        writeUTF(data, distSound);
        data.writeFloat(distVolume);
        data.writeFloat(distPitch);
      }
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      super.decodeInto(ctx, data);
      volume = data.readFloat();
      pitch = data.readFloat();
      delay = data.readInt();

      if (data.isReadable()) {
        distSound = readUTF(data);
        distVolume = data.readFloat();
        distPitch = data.readFloat();
      }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientSide(EntityPlayer clientPlayer) {
      ResourceLocation resourceLocation =
          sound.startsWith("minecraft:") ? new ResourceLocation(sound.substring(10))
              : FlansModResourceHandler.getSound(sound);
      if (delay == 0) {
        FMLClientHandler.instance().getClient().getSoundHandler().playSound(
            new PositionedSoundRecord(resourceLocation, volume, pitch, posX, posY, posZ));
      } else {
        FMLClientHandler.instance().getClient().getSoundHandler().playDelayedSound(
            new PositionedSoundRecord(resourceLocation, volume, pitch, posX, posY, posZ), delay);

        if (distSound != null) {
          resourceLocation = distSound.startsWith("minecraft:") ? new ResourceLocation(distSound.substring(10))
                : FlansModResourceHandler.getSound(distSound);
          FMLClientHandler.instance().getClient().getSoundHandler().playDelayedSound(
              new PositionedSoundRecord(resourceLocation, distVolume, distPitch, posX, posY, posZ), delay);
        }
      }

    }
  }


}
