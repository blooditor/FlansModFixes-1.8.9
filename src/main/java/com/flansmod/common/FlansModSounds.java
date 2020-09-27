package com.flansmod.common;

import com.flansmod.common.teams.Team;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;

public class FlansModSounds {

  public static FlansModSounds inst;

  private HashMap<String, EndSound> endSounds = new HashMap<>();

  public FlansModSounds() {
    inst = this;
  }


  public void tick() {
    Iterator<Map.Entry<String, EndSound>> it = endSounds.entrySet().iterator();

    while (it.hasNext()) {
      EndSound sound = it.next().getValue();
      if (sound.timer-- == 0) {
        PositionedSoundRecord positionedsoundrecord = new PositionedSoundRecord(new ResourceLocation(sound.sound), sound.volume, 1, (float)sound.x, (float)sound.y, (float)sound.z);
        Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);
        it.remove();
      }
    }

  }
  public static void playSound(String sound, float volume, String origin) {
    Vec3 v = Minecraft.getMinecraft().thePlayer.getPositionVector();
    v = new Vec3(0, 5, 0);
    inst.playSound("sound_" + sound, v.xCoord, v.yCoord, v.zCoord, volume, origin);
  }

  private void playSound(String sound, double x, double y, double z, float volume, String origin) {
    Vec3 loc = new Vec3(x, y, z);
    Vec3 v = Minecraft.getMinecraft().thePlayer.getPositionVector();
    double dist = Minecraft.getMinecraft().thePlayer.getDistance(x, y, z);
    Vec3 vec = new Vec3(x-v.xCoord, y-v.yCoord, z-v.zCoord);
    vec = vec.normalize();
    x = v.xCoord + vec.xCoord;
    y = v.yCoord + vec.yCoord;
    z = v.zCoord + vec.zCoord;

    volume = 1-(float) (dist/(volume * 16));

    if(volume <= 0)
      return;

    System.out.println(volume);

    SoundType type = SoundType.getSound(sound);

    if (type != null) {
      String noise = type.noise;
      if ("rifle".equalsIgnoreCase(noise)) {
        noise = getNoiseForLocation(loc.xCoord, loc.yCoord, loc.zCoord, dist);
      }
      if (type.high != null) {
        float vol = 1.5f*volume - 0.5f;
        float pitch = 0.25f*volume + 0.75f;
        if(vol > 0)
          playTheSound(x, y, z, "flansmod:" + type.high, vol, pitch);
      }
      if (type.low != null) {
        float pitch = 0.25f*volume + 0.75f;
        playTheSound(x, y, z, "flansmod:" + type.low, volume, pitch);
      }
      if(noise != null){
        float vol = volume-1;
        vol = -(vol * vol) + 1;
        if (vol > 0) {

          playTheSound(x,y,z, "flansmod:" + noise, vol, 1);
          endSounds.put(origin, new EndSound(x,y,z, vol, "flansmod:" + noise + "_end"));
        }
      }
      if (type.reflection != null) {

        float pitch = 0.25f*volume + 0.75f;
        playTheSound(x, y, z, "flansmod:" + type.reflection, volume, pitch);
      }
    }
  }

  private void playTheSound(double x, double y, double z, String soundName, float volume, float pitch) {
    PositionedSoundRecord positionedsoundrecord = new PositionedSoundRecord(new ResourceLocation(soundName), volume, pitch, (float)x, (float)y, (float)z);
    Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);
  }

  private String getNoiseForLocation(double x, double y, double z, double dist) {
    Minecraft mc = Minecraft.getMinecraft();

    NoiseType type = getNoiseType(x, y, z);
    String distType = dist > 30 ? dist > 80 ? "Far" : "Distant" : "Close";
    return "Noise_" + distType + "_" + type.sound;
  }

  private NoiseType getNoiseType(double x, double y, double z) {
    Minecraft mc = Minecraft.getMinecraft();
    for (int i = 0; i < 10; i++) {
      IBlockState state = mc.theWorld.getBlockState(new BlockPos(x, y + i, z));
      if (state.getBlock() != Blocks.air && state.getBlock().getMaterial() != Material.leaves) {
        if (i < 5) {
          return NoiseType.INDOOR_SMALL;
        }
        return NoiseType.INDOOR_GARAGE;
      }
    }

    BiomeGenBase base = mc.theWorld.getBiomeGenForCoords(new BlockPos(x, y, z));
    if (base.biomeName.contains("Forest") || base == BiomeGenBase.swampland) {
      return NoiseType.FOREST;
    }

    for (int i = 0; i < 5; i++) {
      IBlockState state = mc.theWorld.getBlockState(new BlockPos(x, y - i, z));
      if (state.getBlock() != Blocks.air) {
      if(state.getBlock().getMaterial() == Material.rock)
          return NoiseType.URBAN;
        return NoiseType.OPENFIELD;
      }
    }
    return NoiseType.OPENFIELD;
  }

  public enum NoiseType{
    FOREST("Forest_Wave_0"),
    INDOOR_GARAGE("Indoor_Garage_Wave_0"),
    INDOOR_SMALL("Indoor_Small_Wave_0"),
    OPENFIELD("OpenField_Wave_0"),
    URBAN_ALLEY("Urban_Alley_Wave_0"),
    URBAN("Urban_Wave_0"),

    ;


    private final String sound;

    NoiseType(String sound) {
      this.sound = sound;
    }
  }

  public static class SoundType extends InfoType {

    String high;
    String low;
    String noise;
    String reflection;

    static HashMap<String, SoundType> sounds = new HashMap<>();

    public SoundType(TypeFile file) {
      super(file);

      this.shortName = file.name;
      sounds.put(shortName, this);
    }
    public static SoundType getSound(String s) {
      return sounds.get(s);
    }

    @Override
    protected void postRead(TypeFile file) {
      super.postRead(file);

    }

    @Override
    public void read(String[] split, TypeFile file) {
      super.read(split, file);

      high = Read(split, "high", high);
      low = Read(split, "low", low);
      noise = Read(split, "noise", noise);
      reflection = Read(split, "reflection", reflection);

    }
  }

  private class EndSound {
    double x;
    double y;
    double z;
    float volume;
    String sound;
    int timer;

    public EndSound(double x, double y, double z,  float volume, String sound) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.volume = volume;
      this.sound = sound;
      timer = 2;
    }

  }
}
