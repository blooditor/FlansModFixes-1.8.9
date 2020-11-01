package com.flansmod.common;

import com.flansmod.common.guns.BulletType;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;
import com.flansmod.common.vector.Vector3f;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.netty.util.internal.ThreadLocalRandom;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.client.event.sound.PlayStreamingSourceEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import paulscode.sound.SoundSystem;

/**
 *  Bolt action sound
 *
 */
public class FlansModSounds {

  public static FlansModSounds inst;

  private HashMap<Integer, EndSound> endSounds = new HashMap<>();
  private HashMap<String, ResourceLocation> resourceLocCache = new HashMap<>();

  private static int noiseTypeCacheChunkSize = 2; //2^x
  private Cache<BlockPos, NoiseType> noiseTypeCache = CacheBuilder.newBuilder().maximumSize(1000).build();
  private static Map<ISound, Integer> delayedSounds = new HashMap<>();

  SoundSystem soundSystem;
  SoundManager sndManager;
  SoundHandler sndHandler;

  public FlansModSounds() {
    inst = this;

    new Thread(this::soundThreadLoop, "Sound thread").start();
  }

  private void soundThreadLoop() {
    while (true) {
      long nano = System.currentTimeMillis();
      tickThread();

      long nano2 = System.currentTimeMillis();
      try {
        long sl = 42 - (nano2 - nano);
        if (sl > 0)
          Thread.sleep(sl);
      } catch (InterruptedException e) {
      }
    }
  }


  public synchronized void tick() {
    Iterator iterator1 = delayedSounds.entrySet().iterator();

    while(iterator1.hasNext()) {
      Entry<ISound, Integer> entry1 = (Entry)iterator1.next();
      if (entry1.getValue() > 0) {
        entry1.setValue(entry1.getValue() - 1);
      } else {
        this.playSoundRecord(entry1.getKey());
        iterator1.remove();
      }
    }
  }
  public synchronized void tickThread() {
    Iterator<Map.Entry<Integer, EndSound>> it = endSounds.entrySet().iterator();

    while (it.hasNext()) {
      EndSound sound = it.next().getValue();
      if (sound.timer-- == 0) {
        playTheSoundOffThread(sound.x, sound.y, sound.z, sound.dist, sound.sound, sound.volume, 1, null, sound.silenced, sound.delay);
       /* PositionedSoundRecord positionedsoundrecord = new PositionedSoundRecord(new ResourceLocation(sound.sound), sound.volume, 1, (float)sound.x, (float)sound.y, (float)sound.z);
        Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);*/
        it.remove();
      }
    }

  }

  public static void PlaySound(double x, double y, double z, String sound, float volume, int origin, boolean silenced) {
    //  Vec3 v = Minecraft.getMinecraft().thePlayer.getPositionVector();
/*   x = 6200;
   y = 83;
   z = -4356;*/
    inst.playSound(x, y, z, sound, volume, origin, silenced);
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

      volume = 1 - (float) (dist / (volume * 16));

      float soundSpeed = 15 * BulletType.BULLET_SPEED_MODIFIER;
      int delay = (int) (dist / soundSpeed);

      if (volume <= 0)
        return;

      double relDist = 1 - volume;
      // System.out.println(volume);

      // String noise = getSoundForLocation(loc.xCoord, loc.yCoord, loc.zCoord, dist, type.noise);

      if (type.close != null) {
        float vol = type.close.volume * volume;
        if (vol > 0.95f)
          playTheSoundOffThread(x, y, z, relDist, type.close.sound, 4 * vol - 3, type.close.pitch, loc, silenced, delay);
      }
      if (type.main != null) {
        float vol = 1.5f * volume * type.main.volume - 0.5f;
        float pitch = 0.25f * volume + 0.75f;
        if (silenced) {
          vol *= 0.6f;
          pitch += 1.5f;
        }
        if (vol > 0.01)
          playTheSoundOffThread(x, y, z, relDist, type.main.sound, vol, pitch * type.main.pitch, loc, silenced, delay);
      }
      if (type.low != null) {
        float pitch = 0.25f * volume + 0.75f;
        playTheSoundOffThread(x, y, z, relDist, type.low.sound, volume * type.low.volume, pitch * type.low.pitch, loc, silenced, delay);
      }
      if (type.noise != null) {
        float vol = volume - 1;
        vol = -(vol * vol) + 1;
        vol *= 1f;
        vol *= type.noise.volume;
        if (silenced)
          vol *= 0.7f;
        if (vol > 0.01) {
          if (hasEndSound(type.noise.sound)) {
            //     EndSound existing = endSounds.get(origin);
            //  if (existing != null && existing.timer >= 1) {
            //    } else {
            String noise = playTheSoundOffThread(x, y, z, relDist, type.noise.sound, vol, type.noise.pitch, loc, silenced, delay);

        //    if (delay == 0) {

              synchronized (this) {
                endSounds.put(origin, new EndSound(x, y, z, relDist, vol, type.noise.pitch, delay, silenced, noise + "_end"));
              }
      //      } else {
      //        playTheSoundOffThread(x, y, z, relDist,noise + "_end", vol, type.noise.pitch, loc, silenced, delay+2);
      //      }
            //     }
          } else {
            String noise = playTheSoundOffThread(x, y, z, relDist, type.noise.sound, vol, type.noise.pitch, loc, silenced, delay);
          }


        }
      }
      if (type.reflection != null) {

        float vol = volume * type.reflection.volume;
        if (silenced)
          vol *= 0.7f;
        float pitch = 0.25f * volume + 0.75f;
        if (vol > 0.01) {
          playTheSound(x, y, z, relDist, type.reflection.sound, vol, pitch * type.reflection.pitch, loc, silenced, delay);
        }
      }
      if (type.bolt != null) {
        if (volume > 0.95f) {
          float vol = type.bolt.volume * volume * 0.8f;
          playTheSound(x, y, z, relDist, type.bolt.sound, vol, type.bolt.pitch, loc, silenced, delay + 5);
        }
      }
    } else {
     /* if (sound.startsWith("sound_")) {
        sound = sound.substring(6);
      }*/
      String resName = "flansmod:" + sound;
      PositionedSoundRecord positionedsoundrecord = new PositionedSoundRecord(new ResourceLocation(resName), volume, 1, (float) x, (float) y,
          (float) z);
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
    PositionedSoundRecord positionedsoundrecord = new RelativeSoundRecord(res, volume, pitch, (float) x, (float) y, (float) z);

    if (delay > 0) {
      Minecraft.getMinecraft().getSoundHandler().playDelayedSound(positionedsoundrecord, delay);
    } else {
      Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);
    }
    return soundName;
  }
  private String playTheSoundOffThread(double x, double y, double z, double dist, String soundName, float volume, float pitch, Vec3 loc, boolean silenced,
  int delay) {
    soundName = getSoundForLocation(loc, dist, soundName, silenced);
    String resName = "flansmod:" + soundName;
    ResourceLocation res = resourceLocCache.get(resName);
    if (res == null) {
      res = new ResourceLocation(resName);
      resourceLocCache.put(resName, res);
    }
    PositionedSoundRecord positionedsoundrecord = new RelativeSoundRecord(res, volume, pitch, (float) x, (float) y, (float) z);

    if (delay > 0) {
      playDelayedSound(positionedsoundrecord, delay);
    } else {
      playSoundRecord(positionedsoundrecord);
      //  Minecraft.getMinecraft().getSoundHandler().playSound(positionedsoundrecord);
    }

    return soundName;
  }

  private synchronized void playDelayedSound(PositionedSoundRecord positionedsoundrecord, int delay) {
    delayedSounds.put(positionedsoundrecord, delay);
  }

  private String getSoundForLocation(Vec3 loc, double dist, String sound, boolean silenced) {

    if (loc == null)
      return sound;

    String distType = dist > 0.3 ? dist > 0.6 ? "Far" : "Distant" : "Close";
    String distType2 = dist > 0.5 ? "Far" : "Close";
    NoiseType type;
    switch (sound) {
      case "rifle":
        if (silenced)
          return "Noise_Close_Silenced_Wave_0";
        type = getNoiseType(loc);
        if (type == NoiseType.INDOOR_GARAGE || type == NoiseType.INDOOR_SMALL) {
          distType = dist > 0.2 ? dist > 0.5 ? "Far" : "Distant" : "Close";
          return "Noise_" + distType + "_" + type.sound + "_Wave_0";
        }
        return "Noise_" + distType + "_" + type.sound + "_Wave_0";
      case "BushmasterCannon":
        return "BushmasterFire" + distType + "";
      case "explosionAmbience":
        return "Explosion_Ambience_" + distType + "_Wave";
      case "missileSmall":
        if (dist > 0.5)
          return "CoreBassDistant_Missile_01";
        return "MissileLauncher_Small_fire_Wave";
      case "missileMedium":
        if (dist > 0.5)
          return "CoreBassDistant_Missile_01";
        return "Projectile_AT_Engine_01_Wave";
      case "missileMedium2":
        if (dist > 0.5)
          return "CoreBassDistant_Missile_01";
        return "Projectile_AT_Engine_02_Wave";
      case "missileBig":
        if (dist > 0.5)
          return "CoreBassDistant_Missile_01";
        return "Projectile_Missile_Engine_Wave";
      case "explosionAmbienceSmall":
        return "Explosion_Ambience_Small_Close_Wave";
      case "sniper":
        return "Single_Sniper_Single_" + distType + "_Openfield_Wave";
      case "heavyRifle":
        return "Single_Sniper_Single_" + distType + "_Openfield_Wave";
      case "shotgun":
        type = getNoiseType(loc);
        return "Reflection_" + distType2 + "_" + type.sound + "_Wave";
      case "sniperReflection":
        if (silenced)
          return "Noise_Close_Silenced_Sniper_Wave_0_end";
        type = getNoiseType(loc);
        if (type.indoor) {
          return type == NoiseType.INDOOR_GARAGE ? "Reflections_Close_Indoor_Garage_Wave" : "Reflections_Close_Indoor_Small_Wave";
        }
        return "Reflection_Sniper_Wave";
      // case "dmr":
      //   return "Reflections_" + distType + "_OpenField";
    }
    return sound;
  }


  private NoiseType getNoiseType(Vec3 pos) {
    int ix = (int) Math.floor(pos.xCoord);
    int iy = (int) Math.floor(pos.yCoord);
    int iz = (int) Math.floor(pos.zCoord);
    int cx = ix >> noiseTypeCacheChunkSize;
    int cy = iy >> noiseTypeCacheChunkSize;
    int cz = iz >> noiseTypeCacheChunkSize;

    BlockPos cachePos = new BlockPos(cx, cy, cz);
    try {
      return noiseTypeCache.get(cachePos, new Callable<NoiseType>() {
        @Override
        public NoiseType call() throws Exception {
          return calcNoiseType(pos);
        }
      });
    } catch (ExecutionException e) {
      e.printStackTrace();
      return NoiseType.OPENFIELD;
    }
  }

  private NoiseType calcNoiseType(Vec3 pos) {
    Minecraft mc = Minecraft.getMinecraft();

    NoiseType indoor = checkIndoor(mc.theWorld, pos);
    if (indoor != null)
      return indoor;

    if (pos.yCoord < 140) {
      BiomeGenBase base = mc.theWorld.getBiomeGenForCoords(new BlockPos(pos));
      if (base.biomeName.contains("Forest") || base.biomeName.contains("Taiga") || base.biomeName.contains("jungle") || base.biomeName.contains("forest") || base.biomeName.contains("taiga") || base == BiomeGenBase.swampland) {
        return NoiseType.FOREST;
      }
      if (base == BiomeGenBase.extremeHills || base == BiomeGenBase.extremeHillsEdge || base == BiomeGenBase.extremeHillsPlus) {
        return NoiseType.FOREST;
      }
    }
    return NoiseType.OPENFIELD;
  }

  private NoiseType checkIndoor(World w, Vec3 pos) {
    int numRays = 4;
    int u = 10; //ray length up
    int s = 8; //ray length side

    Vec3[] dirs = new Vec3[]{new Vec3(0, u, 0), new Vec3(0, u, s), new Vec3(s, u, 0),
        new Vec3(0, u, -s), new Vec3(-s, u, 0), new Vec3(s, u, s),
        new Vec3(-s, u, -s), new Vec3(-s, u, s), new Vec3(s, u, -s)};

    int hitsClose = 0;
    int hitsFar = 0;

    for (int i = 0; i < dirs.length; i++) {
      Vec3 target = pos.add(dirs[i]);
      MovingObjectPosition mop = w.rayTraceBlocks(pos, target, true, true, false);
      if (mop != null) {
        IBlockState state = w.getBlockState(mop.getBlockPos());
        if (state.getBlock() != Blocks.air && state.getBlock().getMaterial() != Material.leaves && state.getBlock().isFullCube()) {

          double dist = pos.squareDistanceTo(mop.hitVec);
          if (dist < 80) {
            hitsClose++;
          } else {
            hitsFar++;
          }

        }
      }
      if (i > 3) {
        if (hitsClose + hitsFar < i / 2) {
          return null;
        }
      }
    }
    if (hitsClose + hitsFar < dirs.length - 3) {
      return null;
    }
    if (hitsClose > hitsFar) {
      return NoiseType.INDOOR_SMALL;
    }
    return NoiseType.INDOOR_GARAGE;
  }

  public void playSoundRecord(ISound sound) {
    if (soundSystem == null || sndHandler == null || Minecraft.getMinecraft().getSoundHandler() != sndHandler) {

      try {
        sndHandler = Minecraft.getMinecraft().getSoundHandler();
        Field soundManagerF = ReflectionHelper.findField(SoundHandler.class, "field_147694_f", "sndManager");
        soundManagerF.setAccessible(true);
        sndManager = (SoundManager) soundManagerF.get(sndHandler);
        Field soundSystemF = ReflectionHelper.findField(SoundManager.class, "field_148620_e", "sndSystem");
        soundSystemF.setAccessible(true);
        soundSystem = (SoundSystem) soundSystemF.get(sndManager);
      } catch (Exception e) {
        e.printStackTrace();
        return;
      }
    }

    if (soundSystem.getMasterVolume() <= 0.0F) {
    } else {

      SoundEventAccessorComposite soundeventaccessorcomposite = sndHandler.getSound(sound.getSoundLocation());
      if (soundeventaccessorcomposite == null) {
      } else {
        SoundPoolEntry soundpoolentry = soundeventaccessorcomposite.cloneEntry();
        if (soundpoolentry == SoundHandler.missing_sound) {
          System.out.println("Unable to play empty soundEvent: " + soundeventaccessorcomposite.getSoundEventLocation());
        } else {
          float f = sound.getVolume();
          float f1 = 16.0F;
          if (f > 1.0F) {
            f1 *= f;
          }

          SoundCategory soundcategory = soundeventaccessorcomposite.getSoundCategory();
          float f2 = this.getNormalizedVolume(sound, soundpoolentry, soundcategory);
          double d0 = (double) this.getNormalizedPitch(sound, soundpoolentry);
          ResourceLocation resourcelocation = soundpoolentry.getSoundPoolEntryLocation();
          if (f2 == 0.0F) {
          } else {
            boolean flag = sound.canRepeat() && sound.getRepeatDelay() == 0;
            String s = MathHelper.getRandomUuid(ThreadLocalRandom.current()).toString();

            soundSystem
                .newSource(false, s, getURLForSoundResource(resourcelocation), resourcelocation.toString(), flag, sound.getXPosF(), sound.getYPosF(),
                    sound.getZPosF(), sound.getAttenuationType().getTypeInt(), f1);

            //  logger.debug(LOG_MARKER, "Playing sound {} for event {} as channel {}", new Object[]{soundpoolentry.getSoundPoolEntryLocation(), soundeventaccessorcomposite.getSoundEventLocation(), s});
            soundSystem.setPitch(s, (float) d0);
            soundSystem.setVolume(s, f2);
            soundSystem.play(s);
          }
        }
      }
    }
  }


  private static URL getURLForSoundResource(final ResourceLocation p_148612_0_) {
    String s = String.format("%s:%s:%s", "mcsounddomain", p_148612_0_.getResourceDomain(), p_148612_0_.getResourcePath());
    URLStreamHandler urlstreamhandler = new URLStreamHandler() {
      protected URLConnection openConnection(URL p_openConnection_1_) {
        return new URLConnection(p_openConnection_1_) {
          public void connect() throws IOException {
          }

          public InputStream getInputStream() throws IOException {
            return Minecraft.getMinecraft().getResourceManager().getResource(p_148612_0_).getInputStream();
          }
        };
      }
    };

    try {
      return new URL((URL)null, s, urlstreamhandler);
    } catch (MalformedURLException var4) {
      throw new Error("TODO: Sanely handle url exception! :D");
    }
  }

  private float getNormalizedVolume(ISound sound, SoundPoolEntry entry, SoundCategory category) {
    return (float)MathHelper.clamp_double((double)sound.getVolume() * entry.getVolume(), 0.0D, 1.0D) * this.getSoundCategoryVolume(category);
  }
  private float getSoundCategoryVolume(SoundCategory category) {
    return category != null && category != SoundCategory.MASTER ? Minecraft.getMinecraft().gameSettings.getSoundLevel(category) : 1.0F;
  }
  private float getNormalizedPitch(ISound sound, SoundPoolEntry entry) {
    return (float)MathHelper.clamp_double((double)sound.getPitch() * entry.getPitch(), 0.5D, 2.0D);
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
