package com.flansmod.common;

import com.flansmod.common.guns.BulletType;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;

/**
 *  Bolt action sound
 *
 */
public class FlansModSounds {

  public static FlansModSounds inst;

  private HashMap<Integer, EndSound> endSounds = new HashMap<>();
  private HashMap<String, ResourceLocation> resourceLocCache = new HashMap<>();

  public FlansModSounds() {
    inst = this;

    new Thread(this::soundThreadLoop, "Sound thread").start();
  }

  private void soundThreadLoop() {
    while (true) {
      long nano = System.currentTimeMillis();
      tick();

      long nano2 = System.currentTimeMillis();
      try {
        long sl = 40 - (nano2 - nano);
        if(sl > 0)
          Thread.sleep(sl);
      } catch (InterruptedException e) {
      }
    }
  }


  public synchronized void tick() {
    Iterator<Map.Entry<Integer, EndSound>> it = endSounds.entrySet().iterator();

    while (it.hasNext()) {
      EndSound sound = it.next().getValue();
      if (sound.timer-- == 0) {
        playTheSound(sound.x, sound.y, sound.z, sound.dist, sound.sound, sound.volume, 1, null, sound.silenced, sound.delay);
       /* PositionedSoundRecord positionedsoundrecord = new PositionedSoundRecord(new ResourceLocation(sound.sound), sound.volume, 1, (float)sound.x, (float)sound.y, (float)sound.z);
        Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);*/
        it.remove();
      }
    }

  }
  public static void PlaySound(double x, double y, double z, String sound, float volume, int origin, boolean silenced) {
  //  Vec3 v = Minecraft.getMinecraft().thePlayer.getPositionVector();
//   x = y = z = 5;
    inst.playSound( x, y, z, sound, volume, origin, silenced);
  }

  private void playSound(double x, double y, double z, String sound, float volume, int origin, boolean silenced) {

    SoundType type = SoundType.getSound(sound);

    if (type != null) {
      Vec3 loc = new Vec3(x, y, z);
      Vec3 v = Minecraft.getMinecraft().thePlayer.getPositionVector();
      double dist = Minecraft.getMinecraft().thePlayer.getDistance(x, y, z);
     /* Vec3 vec = new Vec3(x-v.xCoord, y-v.yCoord, z-v.zCoord);
      vec = vec.normalize();
      x = vec.xCoord;
      y = vec.yCoord;
      z = vec.zCoord;*/
      /*
      x = v.xCoord + vec.xCoord;
      y = v.yCoord + vec.yCoord;
      z = v.zCoord + vec.zCoord;*/

      volume = 1-(float) (dist/(volume * 16));


      float soundSpeed = 15 * BulletType.BULLET_SPEED_MODIFIER;
      int delay = (int) (dist / soundSpeed);

      if(volume <= 0)
        return;

      double relDist = 1 - volume;
   // System.out.println(volume);

     // String noise = getSoundForLocation(loc.xCoord, loc.yCoord, loc.zCoord, dist, type.noise);

      if (type.close != null) {
        float vol = type.close.volume*volume;
        if(vol > 0.95f)
          playTheSound(x, y, z, relDist, type.close.sound, 4*vol-3, type.close.pitch, loc, silenced, delay);
      }
      if (type.main != null) {
        float vol = 1.5f*volume*type.main.volume - 0.5f;
        float pitch = 0.25f*volume + 0.75f;
        if (silenced) {
          vol *= 0.6f;
          pitch += 1.5f;
        }
        if(vol > 0.01)
          playTheSound(x, y, z, relDist, type.main.sound, vol, pitch*type.main.pitch, loc, silenced, delay);
      }
      if (type.low != null) {
        float pitch = 0.25f*volume + 0.75f;
        playTheSound(x, y, z, relDist, type.low.sound, volume*type.low.volume, pitch*type.low.pitch, loc, silenced, delay);
      }
      if(type.noise != null){
        float vol = volume-1;
        vol = -(vol * vol) + 1;
        vol *= 1f;
        vol *= type.noise.volume;
        if(silenced)
          vol *= 0.7f;
        if (vol > 0.01) {
          if (hasEndSound(type.noise.sound)) {
       //     EndSound existing = endSounds.get(origin);
          //  if (existing != null && existing.timer >= 1) {
        //    } else {
              String noise = playTheSound(x, y, z, relDist, type.noise.sound, vol, type.noise.pitch, loc, silenced, delay);

              synchronized (this){
                endSounds.put(origin, new EndSound(x, y, z, relDist, vol, type.noise.pitch, delay, silenced, noise + "_end"));
              }
       //     }
          } else {
            String noise = playTheSound(x,y,z, relDist, type.noise.sound, vol, type.noise.pitch, loc, silenced, delay);
          }


        }
      }
      if (type.reflection != null) {

        float vol = volume*type.reflection.volume;
        if(silenced)
          vol *= 0.7f;
        float pitch = 0.25f*volume + 0.75f;
        if (vol > 0.01) {
          playTheSound(x, y, z, relDist, type.reflection.sound, vol, pitch * type.reflection.pitch, loc, silenced, delay);
        }
      }
      if (type.bolt != null) {
        if (volume > 0.95f) {
          float vol = type.bolt.volume * volume * 0.8f;
          playTheSound(x, y, z, relDist, type.bolt.sound, vol, type.bolt.pitch, loc, silenced, delay+5);
        }
      }
    }else{
      if (sound.startsWith("sound_")) {
        sound = sound.substring(6);
      }
      String resName = "flansmod:" + sound;
      PositionedSoundRecord positionedsoundrecord = new PositionedSoundRecord(new ResourceLocation(resName), volume, 1, (float)x, (float)y, (float)z);
      Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);
    }
  }

  private boolean hasEndSound(String noise) {
    return noise.equalsIgnoreCase("rifle");
  }

  private String playTheSound(double x, double y, double z, double dist, String soundName, float volume, float pitch, Vec3 loc, boolean silenced,
      int delay) {
    soundName = getSoundForLocation(loc, dist, soundName, silenced);
    String resName = "flansmod:" + soundName;
    ResourceLocation res = resourceLocCache.get(resName);
    if (res == null) {
      res = new ResourceLocation(resName);
      resourceLocCache.put(resName, res);
    }
    PositionedSoundRecord positionedsoundrecord = new RelativeSoundRecord(res, volume, pitch, (float)x, (float)y, (float)z);

    if (delay > 0) {
      Minecraft.getMinecraft().getSoundHandler().playDelayedSound(positionedsoundrecord, delay);
    } else {
      Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);
    }

    return soundName;
  }

  private String getSoundForLocation(Vec3 loc, double dist, String sound, boolean silenced) {

    if(loc == null)
      return sound;

    String distType = dist > 0.3 ? dist > 0.6 ? "Far" : "Distant" : "Close";
    String distType2 = dist > 0.5 ? "Far" : "Close";
    NoiseType type;
    switch (sound) {
      case "rifle":
        if(silenced)
          return "Noise_Close_Silenced_Wave_0";
        type = getNoiseType(loc.xCoord, loc.yCoord, loc.zCoord);
        return "Noise_" + distType + "_" + type.sound + "_Wave_0";
      case "BushmasterCannon":
        return "BushmasterFire" + distType + "";
      case "explosionAmbience":
        return "Explosion_Ambience_" + distType + "_Wave";
      case "missileSmall":
        if(dist > 0.5)
          return "CoreBassDistant_Missile_01";
        return "MissileLauncher_Small_fire_Wave";
      case "missileMedium":
        if(dist > 0.5)
          return "CoreBassDistant_Missile_01";
        return "Projectile_AT_Engine_01_Wave";
      case "missileBig":
        if(dist > 0.5)
          return "CoreBassDistant_Missile_01";
        return "Projectile_Missile_Engine_Wave";
      case "explosionAmbienceSmall":
        return "Explosion_Ambience_Small_Close_Wave";
      case "sniper":
        return "Single_Sniper_Single_" + distType + "_Openfield_Wave";
      case "heavyRifle":
        return "Single_Sniper_Single_" + distType + "_Openfield_Wave";
      case "shotgun":
        type = getNoiseType(loc.xCoord, loc.yCoord, loc.zCoord);
        return "Reflection_" + distType2 + "_" + type.sound + "_Wave";
      case "sniperReflection":
        if(silenced)
          return "Noise_Close_Silenced_Sniper_Wave_0_end";
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

    public SoundEffect main;
    public SoundEffect low;
    public SoundEffect noise;
    SoundEffect reflection;
    SoundEffect close;
    SoundEffect bolt;

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

      main = read(split, "main", main);
      low = read(split, "low", low);
      noise = read(split, "noise", noise);
      reflection = read(split, "reflection", reflection);
      close = read(split, "close", close);
      bolt = read(split, "bolt", bolt);

    }

    private float read(String[] split, int i, float alt) {
      return split.length > i? Float.parseFloat(split[i]) : alt;
    }

    private SoundEffect read(String[] split, String name, SoundEffect alt) {
      if (split[0].equalsIgnoreCase(name)) {
        return new SoundEffect(split[1], read(split, 2, 1), read(split, 3, 1));
      }
      return alt;
    }
  }

  public static class SoundEffect{
    String sound;
    float volume;
    float pitch;

    public SoundEffect(String sound, float volume, float pitch) {
      this.sound = sound;
      this.volume = volume;
      this.pitch = pitch;
    }
  }

  private class EndSound {

    double x;
    double y;
    double z;
    double dist;
    float volume;
    float pitch;
    public boolean silenced;
    String sound;
    int timer;
    int delay;

    public EndSound(double x, double y, double z, double dist, float volume, float pitch, int delay, boolean silenced, String sound) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.dist = dist;
      this.volume = volume;
      this.pitch = pitch;
      this.sound = sound;
      this.silenced = silenced;
      this.delay = delay;
      timer = 2;
    }

  }
    public class RelativeSoundRecord extends PositionedSoundRecord {

      public RelativeSoundRecord(ResourceLocation soundResource, float volume, float pitch, float xPosition, float yPosition, float zPosition) {
        super(soundResource, volume, pitch, xPosition, yPosition, zPosition);
        attenuationType = AttenuationType.NONE;
      }
    }
}
