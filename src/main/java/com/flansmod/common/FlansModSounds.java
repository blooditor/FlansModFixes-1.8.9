package com.flansmod.common;

import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import net.minecraft.block.material.Material;
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
  private HashMap<String, MultiDistanceSound> multiSounds = new HashMap<>();
  private HashMap<String, ResourceLocation> resourceLocCache = new HashMap<>();



  public static Queue<String> toPlay = new LinkedList<>();

  public FlansModSounds() {
    inst = this;
  }


  public void tick() {
    Iterator<Map.Entry<String, EndSound>> it = endSounds.entrySet().iterator();

    while (it.hasNext()) {
      EndSound sound = it.next().getValue();
      if (sound.timer-- == 0) {
        playTheSound(sound.x, sound.y, sound.z, sound.dist, sound.sound, sound.volume, 1, null);
       /* PositionedSoundRecord positionedsoundrecord = new PositionedSoundRecord(new ResourceLocation(sound.sound), sound.volume, 1, (float)sound.x, (float)sound.y, (float)sound.z);
        Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);*/
        it.remove();
      }
    }

    while (!toPlay.isEmpty()) {
      playSound(toPlay.poll(), 10, "local");
    }

  }
  public static void playSound(String sound, float volume, String origin) {
    Vec3 v = Minecraft.getMinecraft().thePlayer.getPositionVector();
 //   v = new Vec3(0, 5, 0);
    inst.playSound("sound_" + sound, v.xCoord, v.yCoord, v.zCoord, volume, origin);
  }

  private void playSound(String sound, double x, double y, double z, float volume, String origin) {


    SoundType type = SoundType.getSound(sound);

    if (type != null) {
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

      double relDist = 1 - volume;
   // System.out.println(volume);

     // String noise = getSoundForLocation(loc.xCoord, loc.yCoord, loc.zCoord, dist, type.noise);

      if (type.close != null) {
        if(volume > 0.95f)
          playTheSound(x, y, z, relDist, type.close, 4*volume-3, 1, loc);
      }
      if (type.high != null) {
        float vol = 1.5f*volume - 0.5f;
        float pitch = 0.25f*volume + 0.75f;
        if(vol > 0)
          playTheSound(x, y, z, relDist, type.high, vol, pitch, loc);
      }
      if (type.low != null) {
        float pitch = 0.25f*volume + 0.75f;
        playTheSound(x, y, z, relDist, type.low, volume, pitch, loc);
      }
      if(type.noise != null){
        float vol = volume-1;
        vol = -(vol * vol) + 1;
        vol *= 0.5f;
        if (vol > 0) {

          String noise = playTheSound(x,y,z, relDist, type.noise, vol, 1f, loc);
          if (hasEndSound(type.noise)) {
            endSounds.put(origin, new EndSound(x,y,z, relDist, vol,noise + "_end"));
          }
        }
      }
      if (type.reflection != null) {

        float pitch = 0.25f*volume + 0.75f;
        playTheSound(x, y, z, relDist, type.reflection, volume, pitch, loc);
      }
    }
  }

  private boolean hasEndSound(String noise) {
    return noise.equalsIgnoreCase("rifle");
  }

  private String playTheSound(double x, double y, double z, double dist, String soundName, float volume, float pitch, Vec3 loc) {
    soundName = getSoundForLocation(loc, dist, soundName);
    String resName = "flansmod:" + soundName;
    ResourceLocation res = resourceLocCache.get(resName);
    if (res == null) {
      res = new ResourceLocation(resName);
      resourceLocCache.put(resName, res);
    }
    PositionedSoundRecord positionedsoundrecord = new PositionedSoundRecord(res, volume, pitch, (float)x, (float)y, (float)z);
    Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);

    return soundName;
  }

  private String getSoundForLocation(Vec3 loc, double dist, String sound) {

    if(loc == null)
      return sound;

    String distType = dist > 0.3 ? dist > 0.6 ? "Far" : "Distant" : "Close";
    NoiseType type;
    switch (sound) {
      case "rifle":
        type = getNoiseType(loc.xCoord, loc.yCoord, loc.zCoord);
        return "Noise_" + distType + "_" + type.sound + "_Wave_0";
      case "explosionSmall":
        return "Explosion_Ambience_" + distType + "_Wave";
      case "sniper":
        return "Single_Sniper_Single_" + distType + "_Openfield_Wave";
      case "sniperReflection":
        type = getNoiseType(loc.xCoord, loc.yCoord, loc.zCoord);
        if (type.indoor) {
          return type == NoiseType.INDOOR_GARAGE? "Reflections_Close_Indoor_Garage_Wave" : "Reflections_Close_Indoor_Small_Wave";
        }
        return "Reflection_Sniper_Wave";
     // case "dmr":
     //   return "Reflections_" + distType + "_OpenField";
    }
    return sound;
  }

  private NoiseType getNoiseType(double x, double y, double z) {
    Minecraft mc = Minecraft.getMinecraft();
    for (int i = 0; i < 10; i++) {
      IBlockState state = mc.theWorld.getBlockState(new BlockPos(x, y + i+2, z));
      if (state.getBlock() != Blocks.air && state.getBlock().getMaterial() != Material.leaves && state.getBlock().isFullCube()) {
        if (i < 5) {
          return NoiseType.INDOOR_SMALL;
        }
        return NoiseType.INDOOR_GARAGE;
      }
    }

    BiomeGenBase base = mc.theWorld.getBiomeGenForCoords(new BlockPos(x, y, z));
    if (base.biomeName.contains("Forest") || base.biomeName.contains("Taiga") || base == BiomeGenBase.swampland) {
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
    FOREST("Forest", false),
    INDOOR_GARAGE("Indoor_Garage", true),
    INDOOR_SMALL("Indoor_Small", true),
    OPENFIELD("OpenField", false),
    URBAN_ALLEY("Urban_Alley", false),
    URBAN("Urban", false),

    ;


    private final String sound;
    private final boolean indoor;

    NoiseType(String sound, boolean indoor) {
      this.sound = sound;
      this.indoor = indoor;
    }
  }

  public static class SoundType extends InfoType {

    public String high;
    public String low;
    public String noise;
    String reflection;
    String close;

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
      close = Read(split, "close", close);

    }
  }

  private class EndSound {
    double x;
    double y;
    double z;
    double dist;
    float volume;
    String sound;
    int timer;

    public EndSound(double x, double y, double z, double dist,  float volume, String sound) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.dist = dist;
      this.volume = volume;
      this.sound = sound;
      timer = 2;
    }

  }

  class MultiDistanceSound{
    String close;
    String distant;
    String far;

    public MultiDistanceSound(String close, String distant, String far) {
      this.close = close;
      this.distant = distant;
      this.far = far;
    }
  }
}
