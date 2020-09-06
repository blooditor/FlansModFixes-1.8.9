package com.flansmod.client;

import com.flansmod.api.IControllable;
import com.flansmod.client.gui.GuiDriveableController;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.client.teams.ClientTeamsData;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.IScope;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.network.PacketTeamInfo;
import com.flansmod.common.teams.Team;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3i;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FlansModClient extends FlansMod {
  //Plane / Vehicle control handling
  /**
   * Whether the player has received the vehicle tutorial text
   */
  public static boolean doneTutorial = false;
  /**
   * Whether the player is in mouse control mode
   */
  public static boolean controlModeMouse = true;
  /**
   * A delayer on the mouse control switch
   */
  public static int controlModeSwitchTimer = 20;

  //Recoil variables
  /**
   * The recoil applied to the player view by shooting
   */
  public static GunType.GunRecoil playerRecoil = new GunType.GunRecoil(0);

  //Gun animations
  /**
   * Gun animation variables for each entity holding a gun. Currently only applicable to the player
   */
  public static HashMap<EntityLivingBase, GunAnimations> gunAnimationsRight = new HashMap<EntityLivingBase, GunAnimations>(), gunAnimationsLeft = new HashMap<EntityLivingBase, GunAnimations>();

  //Scope variables
  /**
   * A delayer on the scope button to avoid repeat presses
   */
  public static int scopeTime;
  /**
   * The scope that is currently being looked down
   */
  public static IScope currentScope = null;
  /**
   * The transition variable for zooming in / out with a smoother. 0 = unscoped, 1 = scoped
   */
  public static float zoomProgress = 0F, lastZoomProgress = 0F;
  /**
   * The zoom level of the last scope used, for transitioning out of being scoped, even after the
   * scope is forgotten
   */
  public static float lastZoomLevel = 1F, lastFOVZoomLevel = 1F;

  //Variables to hold the state of some settings so that after being hacked for scopes, they may be restored
  /**
   * The player's mouse sensitivity setting, as it was before being hacked by my mod
   */
  public static float originalMouseSensitivity = 0.5F;
  /**
   * The player's original FOV
   */
  public static float originalFOV = 90F;
  /**
   * The original third person mode, before being hacked
   */
  public static int originalThirdPerson = 0;

  /**
   * Whether the player is in a plane or not
   */
  public static boolean inPlane = false;
  private static boolean sprintKeyLastTick = false;

  /**
   * Packet containing teams mod information from the server
   */
  public static PacketTeamInfo teamInfo;

  public static int hitMarkerTime = 0;
  public static int hitMarkerTimeHeadshot = 0;

  public static ArrayList<Vector3i> blockLightOverrides = new ArrayList<Vector3i>();
  public static int lightOverrideRefreshRate = 5;

  private static WorldRenderer wr;
  private static boolean playedHitMarker = false;

  public static WorldRenderer getWorldRenderer() {
    return wr;
  }

  public void load() {
    log("Loading Flan's mod client side.");
    wr = new WorldRenderer();

  }

  //private static final ResourceLocation zombieSkin = new ResourceLocation("flansmod", "skins/zombie.png");

  public static void tick() {
    if (minecraft.thePlayer == null || minecraft.theWorld == null) {
      return;
    }
    playedHitMarker = false;
    if (minecraft.thePlayer.ridingEntity instanceof IControllable
        && minecraft.currentScreen == null) {
      minecraft.displayGuiScreen(
          new GuiDriveableController((IControllable) minecraft.thePlayer.ridingEntity));
    }

    if (teamInfo != null && teamInfo.timeLeft > 0) {
      teamInfo.timeLeft--;
    }

    ClientTeamsData.Tick();

    if (minecraft.thePlayer.isSprinting() && currentScope != null) {
      if (minecraft.gameSettings.keyBindSprint.isKeyDown()) {
        minecraft.thePlayer.setSprinting(false);
      }
      if (!sprintKeyLastTick) {
        SetScope(null);
      }
    }
    sprintKeyLastTick = minecraft.gameSettings.keyBindSprint.isKeyDown();
    // Guns
    if (scopeTime > 0) {
      scopeTime--;
    }

    EntityPlayerSP p = minecraft.thePlayer;

    if (hitMarkerTime > 0) {
      hitMarkerTime--;
    }
    if (hitMarkerTimeHeadshot > 0) {
      hitMarkerTimeHeadshot--;
    }
    float pspeed = Math.min(0.3f, 3f * (float) (p.motionX * minecraft.thePlayer.motionX
        + minecraft.thePlayer.motionY * minecraft.thePlayer.motionY
        + minecraft.thePlayer.motionZ * minecraft.thePlayer.motionZ));
    boolean sneaking = minecraft.thePlayer.isSneaking();
    if (p.ridingEntity instanceof EntitySeat) {
      pspeed *= 0.1f;
      sneaking = true;
    }
    float recoilToAdd = playerRecoil.update(sneaking, currentScope != null, pspeed);

    if (p.ridingEntity instanceof EntitySeat && ((EntitySeat) p.ridingEntity).seatInfo != null) {
      EntitySeat s = (EntitySeat) p.ridingEntity;
      float newPlayerPitch = s.playerLooking.getPitch() + recoilToAdd;
      float horizontal = playerRecoil.horizontal;
      float newPlayerYaw = s.playerLooking.getYaw() + horizontal;
      if (newPlayerPitch > -s.seatInfo.minPitch) {
        newPlayerPitch = -s.seatInfo.minPitch;
      }
      if (newPlayerPitch < -s.seatInfo.maxPitch) {
        newPlayerPitch = -s.seatInfo.maxPitch;
      }
      s.playerLooking.setAngles(newPlayerYaw, newPlayerPitch, 0);
    } else {
      minecraft.thePlayer.rotationPitch += recoilToAdd;

      if (minecraft.thePlayer.rotationPitch < -90) {
        minecraft.thePlayer.rotationPitch = -90;
      } else if (minecraft.thePlayer.rotationPitch > 90) {
        minecraft.thePlayer.rotationPitch = 90;
      } else {
        float horizontal = playerRecoil.horizontal;
        minecraft.thePlayer.rotationYaw += horizontal;
      }
    }

    //Update gun animations for the gun in hand
    for (GunAnimations g : gunAnimationsRight.values()) {
      g.update();
    }
    for (GunAnimations g : gunAnimationsLeft.values()) {
      g.update();
    }

    for (Object obj : minecraft.theWorld.playerEntities) {
      EntityPlayer player = (EntityPlayer) obj;
      ItemStack currentItem = player.getCurrentEquippedItem();
      if (currentItem != null && currentItem.getItem() instanceof ItemGun) {
        if (player == minecraft.thePlayer && minecraft.gameSettings.thirdPersonView == 0) {
          player.clearItemInUse();
        } else {
          player.setItemInUse(currentItem, 100);
        }
      }
    }

    //If the currently held item is not a gun or is the wrong gun, unscope
    Item itemInHand = null;
    ItemStack itemstackInHand = minecraft.thePlayer.inventory.getCurrentItem();
    if (itemstackInHand != null) {
      itemInHand = itemstackInHand.getItem();
    }
    if (currentScope != null) {
      GameSettings gameSettings = FMLClientHandler.instance().getClient().gameSettings;

      // If we've opened a GUI page, or we switched weapons, close the current scope
      if (FMLClientHandler.instance().getClient().currentScreen != null
          || itemInHand == null
          || !(itemInHand instanceof ItemGun)
          || ((ItemGun) itemInHand).GetType().getCurrentScope(itemstackInHand) != currentScope) {
       /* if (!(Minecraft.getMinecraft().thePlayer.riddenByEntity instanceof IControllable)
            || !GuiDriveableController
            .isHeliGunner((IControllable) Minecraft.getMinecraft().thePlayer.riddenByEntity)) {
          currentScope = null;
          minecraft.gameSettings.fovSetting = originalFOV;
          minecraft.gameSettings.mouseSensitivity = originalMouseSensitivity;
          minecraft.gameSettings.thirdPersonView = originalThirdPerson;
        }*/
        SetScope(null);
      }
    }

    //Calculate new zoom variables
    lastZoomProgress = zoomProgress;
    if (currentScope == null) {
      zoomProgress *= 0.66F;
      if (zoomProgress < 0.001f) {
        zoomProgress = 0;
      }
    } else {
      zoomProgress = 1F - (1F - zoomProgress) * 0.66F;
      if (zoomProgress > 0.999f) {
        zoomProgress = 1;
      }
    }

    if (minecraft.thePlayer.ridingEntity instanceof IControllable) {
      inPlane = true;
      try {
        ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, minecraft.entityRenderer,
            ((IControllable) minecraft.thePlayer.ridingEntity).getCameraDistance(),
            "thirdPersonDistance", "q", "field_78490_B");
      } catch (Exception e) {
        log("I forgot to update obfuscated reflection D:");
        throw new RuntimeException(e);
      }
    } else if (inPlane) {
      try {
        ObfuscationReflectionHelper
            .setPrivateValue(EntityRenderer.class, minecraft.entityRenderer, 4.0F,
                "thirdPersonDistance", "q", "field_78490_B");
      } catch (Exception e) {
        log("I forgot to update obfuscated reflection D:");
        throw new RuntimeException(e);
      }
      inPlane = false;
    }
    if (controlModeSwitchTimer > 0) {
      controlModeSwitchTimer--;
    }
  }

  public static void SetScope(IScope scope) {
    if (getGunAnimations(minecraft.thePlayer, false).reloading && scope != null) {
      return;
    }
    if (scope == null && currentScope == null) {
      return;
    }
    Minecraft.getMinecraft().thePlayer.setSprinting(false);
    GameSettings gameSettings = FMLClientHandler.instance().getClient().gameSettings;

    if (scopeTime <= 0 && FMLClientHandler.instance().getClient().currentScreen == null
        || scope == null) {
      if (currentScope == null && scope != null) {
        currentScope = scope;
        lastZoomLevel = scope.getZoomFactor();
        lastFOVZoomLevel = scope.getFOVFactor();
        float f = originalMouseSensitivity = gameSettings.mouseSensitivity;
        gameSettings.mouseSensitivity = f / (float) Math.sqrt(scope.getZoomFactor());
        originalThirdPerson = gameSettings.thirdPersonView;
        gameSettings.thirdPersonView = 0;
        if(zoomProgress == 0)
          originalFOV = gameSettings.fovSetting;
      } else {
        currentScope = null;
        gameSettings.mouseSensitivity = originalMouseSensitivity;
        gameSettings.thirdPersonView = originalThirdPerson;
        gameSettings.fovSetting = originalFOV;
      }
      if (scope != null) {
        scopeTime = 10;
      }
    }
  }

  public static void UpdateCameraZoom(float smoothing) {
    //If the zoom has changed sufficiently, update it
    if (Math.abs(zoomProgress - lastZoomProgress) > 0F) {
      float actualZoomProgress = lastZoomProgress + (zoomProgress - lastZoomProgress) * smoothing;
      float botchedZoomProgress = zoomProgress > 0.8F ? 1F : 0F;
      double zoomLevel = botchedZoomProgress * lastZoomLevel + (1 - botchedZoomProgress);
      float FOVZoomLevel = actualZoomProgress * lastFOVZoomLevel + (1 - actualZoomProgress);
      if (Math.abs(zoomLevel - 1F) < 0.01F) {
        zoomLevel = 1.0D;
      }

      float zoomToApply = Math.max(FOVZoomLevel, (float) zoomLevel);
      minecraft.gameSettings.fovSetting = (((originalFOV * 40 + 70) / zoomToApply) - 70) / 40;
    }
    if(zoomProgress == 0  && lastZoomProgress != 0){
      minecraft.gameSettings.fovSetting = originalFOV;
    }
  }

  private boolean checkFileExists(File file) {
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (Exception e) {
        FlansMod.log("Failed to create file");
        FlansMod.log(file.getAbsolutePath());
      }
      return false;
    }
    return true;
  }

  public static boolean flipControlMode() {
    if (controlModeSwitchTimer > 0) {
      return false;
    }
    controlModeMouse = !controlModeMouse;
    FMLClientHandler.instance().getClient().displayGuiScreen(
        controlModeMouse ? new GuiDriveableController(
            (IControllable) FMLClientHandler.instance().getClient().thePlayer.ridingEntity) : null);
    controlModeSwitchTimer = 40;
    return true;
  }

  public static void reloadModels(boolean reloadSkins) {
    for (InfoType type : InfoType.infoTypes.values()) {
      type.reloadModel();
    }
    if (reloadSkins) {
      proxy.forceReload();
    }
  }

  public static Minecraft minecraft = FMLClientHandler.instance().getClient();

  /**
   * Gets the team class from an ID
   */
  public static Team getTeam(int spawnerTeamID) {
    if (teamInfo == null) {
      return null;
    } else {
      return teamInfo.getTeam(spawnerTeamID);
    }
  }

  public static boolean isCurrentMap(String map) {
    return !(teamInfo == null || teamInfo.mapShortName == null) && teamInfo.mapShortName
        .equals(map);
  }

  @SideOnly(Side.CLIENT)
  public static EntityFX getParticle(String s, World w, double x, double y, double z) {
    Minecraft mc = Minecraft.getMinecraft();
    //return mc.renderGlobal.doSpawnParticle(s, x, y, z, 0.01D, 0.01D, 0.01D);

    int particleID = 0;
    int[] data = new int[0];

    if (s.equals("hugeexplosion")) {
      particleID = EnumParticleTypes.EXPLOSION_HUGE.getParticleID();
    } else if (s.equals("largeexplode")) {
      particleID = EnumParticleTypes.EXPLOSION_LARGE.getParticleID();
    } else if (s.equals("explode")) {
      particleID = EnumParticleTypes.EXPLOSION_NORMAL.getParticleID();
    } else if (s.equals("fireworksSpark")) {
      particleID = EnumParticleTypes.FIREWORKS_SPARK.getParticleID();
    } else if (s.equals("bubble")) {
      particleID = EnumParticleTypes.WATER_BUBBLE.getParticleID();
    } else if (s.equals("splash")) {
      particleID = EnumParticleTypes.WATER_SPLASH.getParticleID();
    } else if (s.equals("wake")) {
      particleID = EnumParticleTypes.WATER_WAKE.getParticleID();
    } else if (s.equals("drop")) {
      particleID = EnumParticleTypes.WATER_DROP.getParticleID();
    } else if (s.equals("suspended")) {
      particleID = EnumParticleTypes.SUSPENDED.getParticleID();
    } else if (s.equals("depthsuspend")) {
      particleID = EnumParticleTypes.SUSPENDED_DEPTH.getParticleID();
    } else if (s.equals("townaura")) {
      particleID = EnumParticleTypes.TOWN_AURA.getParticleID();
    } else if (s.equals("crit")) {
      particleID = EnumParticleTypes.CRIT.getParticleID();
    } else if (s.equals("magicCrit")) {
      particleID = EnumParticleTypes.CRIT_MAGIC.getParticleID();
    } else if (s.equals("smoke")) {
      particleID = EnumParticleTypes.SMOKE_NORMAL.getParticleID();
    } else if (s.equals("largesmoke")) {
      particleID = EnumParticleTypes.SMOKE_LARGE.getParticleID();
    } else if (s.equals("spell")) {
      particleID = EnumParticleTypes.SPELL.getParticleID();
    } else if (s.equals("instantSpell")) {
      particleID = EnumParticleTypes.SPELL_INSTANT.getParticleID();
    } else if (s.equals("mobSpell")) {
      particleID = EnumParticleTypes.SPELL_MOB.getParticleID();
    } else if (s.equals("mobSpellAmbient")) {
      particleID = EnumParticleTypes.SPELL_MOB_AMBIENT.getParticleID();
    } else if (s.equals("witchMagic")) {
      particleID = EnumParticleTypes.SPELL_WITCH.getParticleID();
    } else if (s.equals("dripWater")) {
      particleID = EnumParticleTypes.DRIP_WATER.getParticleID();
    } else if (s.equals("dripLava")) {
      particleID = EnumParticleTypes.DRIP_LAVA.getParticleID();
    } else if (s.equals("angryVillager")) {
      particleID = EnumParticleTypes.VILLAGER_ANGRY.getParticleID();
    } else if (s.equals("happyVillager")) {
      particleID = EnumParticleTypes.VILLAGER_HAPPY.getParticleID();
    } else if (s.equals("note")) {
      particleID = EnumParticleTypes.NOTE.getParticleID();
    } else if (s.equals("portal")) {
      particleID = EnumParticleTypes.PORTAL.getParticleID();
    } else if (s.equals("enchantmenttable")) {
      particleID = EnumParticleTypes.ENCHANTMENT_TABLE.getParticleID();
    } else if (s.equals("flame")) {
      particleID = EnumParticleTypes.FLAME.getParticleID();
    } else if (s.equals("lava")) {
      particleID = EnumParticleTypes.LAVA.getParticleID();
    } else if (s.equals("footstep")) {
      particleID = EnumParticleTypes.FOOTSTEP.getParticleID();
    } else if (s.equals("cloud")) {
      particleID = EnumParticleTypes.CLOUD.getParticleID();
    } else if (s.equals("reddust")) {
      particleID = EnumParticleTypes.REDSTONE.getParticleID();
    } else if (s.equals("snowballpoof")) {
      particleID = EnumParticleTypes.SNOWBALL.getParticleID();
    } else if (s.equals("snowshovel")) {
      particleID = EnumParticleTypes.SNOW_SHOVEL.getParticleID();
    } else if (s.equals("slime")) {
      particleID = EnumParticleTypes.SLIME.getParticleID();
    } else if (s.equals("heart")) {
      particleID = EnumParticleTypes.HEART.getParticleID();
    } else if (s.equals("barrier")) {
      particleID = EnumParticleTypes.BARRIER.getParticleID();
    } else if (s.contains("_")) {
      int k;
      String[] split = s.split("_", 3);

      if (split[0].equals("iconcrack")) {
        data = new int[]{Item.getIdFromItem(InfoType.getRecipeElement(split[1], 0).getItem())};
        particleID = EnumParticleTypes.ITEM_CRACK.getParticleID();
      } else {
        data = new int[]{Block.getIdFromBlock(
            Block.getBlockFromItem(InfoType.getRecipeElement(split[1], 0).getItem()))};

        if (split[0].equals("blockcrack")) {
          particleID = EnumParticleTypes.BLOCK_CRACK.getParticleID();
        } else if (split[0].equals("blockdust")) {
          particleID = EnumParticleTypes.BLOCK_DUST.getParticleID();
        }
      }
    }

    EntityFX fx = mc.effectRenderer.spawnEffectParticle(particleID, x, y, z, 0D, 0D, 0D, data);

    if (mc.gameSettings.fancyGraphics) {
      fx.renderDistanceWeight = 200D;
    }

    return fx;
  }

  public static GunAnimations getGunAnimations(EntityLivingBase living, boolean offHand) {
    GunAnimations animations = null;
    if (offHand) {
      if (FlansModClient.gunAnimationsLeft.containsKey(living)) {
        animations = FlansModClient.gunAnimationsLeft.get(living);
      } else {
        animations = new GunAnimations(living);
        FlansModClient.gunAnimationsLeft.put(living, animations);
      }
    } else {
      if (FlansModClient.gunAnimationsRight.containsKey(living)) {
        animations = FlansModClient.gunAnimationsRight.get(living);
      } else {
        animations = new GunAnimations(living);
        FlansModClient.gunAnimationsRight.put(living, animations);
      }
    }
    return animations;
  }

  public static void AddHitMarker(boolean headshot) {
    if (headshot) {
      hitMarkerTimeHeadshot = 20;
    } else {
      hitMarkerTime = 20;
    }
    //makes sure only one hit marker per tick
    if (!playedHitMarker && FlansMod.enableHitmarkerSounds) {
      playedHitMarker = true;
      Minecraft.getMinecraft().thePlayer.playSound("flansmod:hitmarker", 1, 1);
    }
  }

  /**
   * Handle flashlight block light override
   */
  public static void UpdateFlashlights(Minecraft mc) {
    //Handle lighting from flashlights and glowing bullets
    if (FlansMod.ticker % lightOverrideRefreshRate == 0 && mc.theWorld != null) {
      //Check graphics setting and adjust refresh rate
      lightOverrideRefreshRate = mc.gameSettings.fancyGraphics ? 10 : 20;

      //Reset old light values
      for (Vector3i v : blockLightOverrides) {
        mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(v.x, v.y, v.z));
      }
      //Clear the list
      blockLightOverrides.clear();

      //Find all flashlights
      for (Object obj : mc.theWorld.playerEntities) {
        EntityPlayer player = (EntityPlayer) obj;
        ItemStack currentHeldItem = player.getCurrentEquippedItem();
        if (currentHeldItem != null && currentHeldItem.getItem() instanceof ItemGun) {
          GunType type = ((ItemGun) currentHeldItem.getItem()).GetType();
          AttachmentType grip = type.getGrip(currentHeldItem);
          if (grip != null && grip.flashlight) {
            for (int i = 0; i < 2; i++) {
              MovingObjectPosition ray = player.rayTrace(grip.flashlightRange / 2F * (i + 1), 1F);
              if (ray != null) {
                int x = ray.getBlockPos().getX();
                int y = ray.getBlockPos().getY();
                int z = ray.getBlockPos().getZ();
                EnumFacing side = ray.sideHit;
                switch (side) {
                  case DOWN:
                    y--;
                    break;
                  case UP:
                    y++;
                    break;
                  case NORTH:
                    z--;
                    break;
                  case SOUTH:
                    z++;
                    break;
                  case WEST:
                    x--;
                    break;
                  case EAST:
                    x++;
                    break;
                }
                blockLightOverrides.add(new Vector3i(x, y, z));
                mc.theWorld.setLightFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z), 12);
                mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y + 1, z));
                mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y - 1, z));
                mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x + 1, y, z));
                mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x - 1, y, z));
                mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z + 1));
                mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z - 1));
              }
            }
          }
        }
      }

      for (Object obj : mc.theWorld.loadedEntityList) {
        if (obj instanceof EntityBullet) {
          EntityBullet bullet = (EntityBullet) obj;
          if (!bullet.isDead && bullet.type.hasLight) {
            int x = MathHelper.floor_double(bullet.posX);
            int y = MathHelper.floor_double(bullet.posY);
            int z = MathHelper.floor_double(bullet.posZ);
            blockLightOverrides.add(new Vector3i(x, y, z));
            mc.theWorld.setLightFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z), 15);
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y + 1, z));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y - 1, z));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x + 1, y, z));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x - 1, y, z));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z + 1));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z - 1));
          }
        } else if (obj instanceof EntityMecha) {
          EntityMecha mecha = (EntityMecha) obj;
          int x = MathHelper.floor_double(mecha.posX);
          int y = MathHelper.floor_double(mecha.posY);
          int z = MathHelper.floor_double(mecha.posZ);
          if (mecha.lightLevel() > 0) {
            blockLightOverrides.add(new Vector3i(x, y, z));
            mc.theWorld.setLightFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z),
                Math.max(mc.theWorld.getLightFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z)),
                    mecha.lightLevel()));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y + 1, z));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y - 1, z));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x + 1, y, z));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x - 1, y, z));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z + 1));
            mc.theWorld.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, new BlockPos(x, y, z - 1));
          }
          if (mecha.forceDark()) {
            for (int i = -3; i <= 3; i++) {
              for (int j = -3; j <= 3; j++) {
                for (int k = -3; k <= 3; k++) {
                  int xd = i + x;
                  int yd = j + y;
                  int zd = k + z;
                  blockLightOverrides.add(new Vector3i(xd, yd, zd));
                  mc.theWorld.setLightFor(EnumSkyBlock.SKY, new BlockPos(xd, yd, zd),
                      Math.abs(i) + Math.abs(j) + Math.abs(k));
                }
              }
            }
          }
        }
      }
    }
  }
}
