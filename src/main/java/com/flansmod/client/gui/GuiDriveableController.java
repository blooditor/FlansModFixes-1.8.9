package com.flansmod.client.gui;

import com.flansmod.api.IControllable;
import com.flansmod.client.FlansModClient;
import com.flansmod.client.KeyInputHandler;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.*;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.network.PacketRequestDebug;
import com.flansmod.common.network.PacketVehicleFireMode;
import com.flansmod.common.network.PacketVehicleFireMode.FireModeWeapon;
import com.flansmod.common.vector.Vector3f;
import com.flansmod.common.vector.Vector3i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Point2f;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GuiDriveableController extends GuiScreen {

  private static final int EJECT_TIME = 20;
  private static Field shaderGroupField;
  private static Method loadShaderMethod;
  public static long safezoneTimer = 0;
  private static BulletType heatSeeker;//performance
  private IControllable plane;
  private boolean leftMouseHeld;
  private boolean rightMouseHeld;
  private int gunnerZoom = 1;
  private int zoomCooldown = 0;
  private int primaryCooldown = 0;
  private int secondaryCooldown = 0;
  private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/driveableController.png");
  public static ArrayList<RadarPos> positions = new ArrayList<RadarPos>();

  int ejectTimer;
  boolean ejecting;

  public GuiDriveableController(IControllable thePlane) {
    super();
    plane = thePlane;

    zoomCooldown = 5;
  }

  private static void loadShader() {
    try {

      EntityRenderer entityRenderer = Minecraft.getMinecraft().entityRenderer;

      if (shaderGroupField == null) {
        shaderGroupField = ReflectionHelper.findField(EntityRenderer.class, "theShaderGroup", "field_147707_d");
      }
      ShaderGroup shaderGroupObj = (ShaderGroup) shaderGroupField.get(entityRenderer);

      if (shaderGroupObj != null) {
        return;
      }
      ResourceLocation object = new ResourceLocation("shaders/post/scan_pincushion.json");

      if (loadShaderMethod == null) {
        loadShaderMethod = ReflectionHelper.findMethod(EntityRenderer.class, entityRenderer, new String[]{"loadShader", "func_175069_a", "func_148057_a","func_148042_a"}, ResourceLocation.class);
      }

      loadShaderMethod.invoke(entityRenderer, object);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  public static boolean isTankScreen(IControllable plane) {
    return plane instanceof EntitySeat && ((EntitySeat) plane).driveable instanceof EntityVehicle && isTankScreen((EntityVehicle) ((EntitySeat) plane).driveable);
  }
  public static boolean isTankScreen(EntityVehicle vehicle) {
    if (!(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntitySeat && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)) {
      return false;
    }
    EntitySeat seat = (EntitySeat) Minecraft.getMinecraft().thePlayer.ridingEntity;
    if (seat.driveable != vehicle) {
      return false;
    }
    if (seat.seatInfo != null && (seat.seatInfo.gunType != null
        || seat.seatInfo.id == 0 && seat.driveable.driveableData != null && vehicle.getVehicleType().primary != EnumWeaponType.NONE)) {

      if(!vehicle.getVehicleType().pilotGuns.isEmpty() || !vehicle.getVehicleType().ammo.isEmpty())
        return true;
    }

    return false;
  }

  @Override
  protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
    super.mouseClicked(mouseX, mouseY, mouseButton);

  }

  public static boolean isHeliGunner(IControllable plane) {
    return plane instanceof EntitySeat && ((EntitySeat) plane).driveable instanceof EntityPlane && isHeliGunner((EntityPlane) ((EntitySeat) plane).driveable);
  }
  public static boolean isHeliGunner(EntityPlane entityPlane) {
    return entityPlane.seats.length > 1 && entityPlane.seats[1] != null && entityPlane.seats[1].riddenByEntity == Minecraft.getMinecraft().thePlayer
        && entityPlane.getPlaneType().mode == EnumPlaneMode.HELI && entityPlane.seats[1].seatInfo != null && entityPlane.seats[1].seatInfo.gunType != null;
  }

  public static boolean isJetPilot(IControllable plane) {
    return plane instanceof EntitySeat && ((EntitySeat) plane).driveable instanceof EntityPlane && isJetPilot((EntityPlane) ((EntitySeat) plane).driveable);
  }
  public static boolean isJetPilot(EntityPlane entityPlane) {
    return entityPlane.seats.length > 0 && entityPlane.seats[0] != null && entityPlane.seats[0].riddenByEntity == Minecraft.getMinecraft().thePlayer
        && entityPlane.getPlaneType().numFlareSlots > 0;
  }
  public static boolean enableRadar(IControllable plane) {
    return plane instanceof EntitySeat && ((EntitySeat) plane).driveable != null && enableRadar(((EntitySeat) plane).driveable);
  }
  public static boolean enableRadar(EntityDriveable entityPlane) {
    return Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null && entityPlane.getDriveableType() != null && entityPlane.seats != null && entityPlane.seats.length > 0 && entityPlane.seats[0] != null && entityPlane.seats[0].riddenByEntity == Minecraft.getMinecraft().thePlayer
        && (entityPlane instanceof EntityPlane && ((EntityPlane)entityPlane).getPlaneType() != null && ((EntityPlane)entityPlane).getPlaneType().numFlareSlots > 0 ||
        entityPlane instanceof EntityVehicle && entityPlane.getDriveableType() != null && entityPlane.getDriveableType().ammo != null && entityPlane.getDriveableType().ammo.contains(getHeatSeeker()));
  }

  private static BulletType getHeatSeeker() {
    if(heatSeeker != null)
      return heatSeeker;
    return heatSeeker = BulletType.getBullet("HeatSeeker");
  }

  @Override
  public void initGui() {
    if (mc.gameSettings.thirdPersonView == 1)
      mc.setRenderViewEntity((plane.getCamera() == null ? mc.thePlayer : plane.getCamera()));
    FlansModClient.originalFOV = mc.gameSettings.fovSetting;
    if (isHeliGunner(plane) || isTankScreen(plane)) {
      if (mc.gameSettings.thirdPersonView != 0) {
        mc.gameSettings.thirdPersonView = 0;
        mc.setRenderViewEntity(mc.thePlayer);
      }
      loadShader();
      if (FlansModClient.currentScope == null) {
        mc.gameSettings.fovSetting = 60;
      }
    }

    if (plane instanceof EntitySeat && ((EntitySeat) plane).driveable != null) {
      ((EntitySeat) plane).driveable.fireMode.setCurrentMode(((EntitySeat) plane).driveable.fireMode.getCurrentMode(), ((EntitySeat) plane).driveable);
    }
  }

  @Override
  public void onGuiClosed() {
    mc.mouseHelper.ungrabMouseCursor();
    //	if(mc.getRenderViewEntity() != mc.thePlayer)
    mc.setRenderViewEntity(mc.thePlayer);
    mc.gameSettings.fovSetting = FlansModClient.originalFOV;
    //positions.clear();
  }

  @Override
  public void handleMouseInput() {
    EntityPlayer player = (EntityPlayer) plane.getControllingEntity();
    if (player != mc.thePlayer) {
      mc.displayGuiScreen(null);
      return;
    }

    int dWheel = Mouse.getDWheel();
    if (dWheel != 0) {
      dWheel = -Math.min(1, Math.max(-1, dWheel));
      //player.inventory.changeCurrentItem(dWheel);
      if (plane instanceof EntitySeat && ((EntitySeat) plane).driveable != null && ((EntitySeat) plane).seatInfo != null
          && ((EntitySeat) plane).seatInfo.id == 0) {
        ((EntitySeat) plane).driveable.fireMode.switchMode(((EntitySeat) plane).driveable, dWheel);
      }
    }

    // Right mouse. Fires shells, drops bombs. Is not a holding thing
    if (Mouse.isButtonDown(1)) {
 //     plane.pressKey(8, player);
    }

    if (Mouse.isButtonDown(1)) {
      if ((isTankScreen(plane) || isHeliGunner(plane)) && zoomCooldown == 0) {
        int ngz = 1;
        switch (gunnerZoom) {
          case 1: ngz = 2;
            break;
          case 2: ngz = isHeliGunner(plane)? 4 : 1;
            break;
          case 4: ngz = 1;
            break;
        }
        gunnerZoom = ngz;

        if (gunnerZoom != 1) {

          float z = 2*gunnerZoom;
          mc.gameSettings.fovSetting = ((60  / z));
        } else {
          mc.gameSettings.fovSetting = 60;
        }
        zoomCooldown = 5;
      }
    }
    if (Mouse.isButtonDown(0)) { //update shoot delay on client side
      if (plane instanceof EntitySeat && ((EntitySeat) plane).driveable != null && ((EntitySeat) plane).seatInfo != null
          && ((EntitySeat) plane).seatInfo.id == 0) {
        EntityDriveable d = ((EntitySeat) plane).driveable;
        boolean secondary = d.fireMode.secondary;
        if(((EntitySeat) plane).driveable.getShootDelay(secondary) == 0)
          ((EntitySeat) plane).driveable.setShootDelay(secondary? d.getDriveableType().shootDelaySecondary : d.getDriveableType().shootDelayPrimary, secondary);
      }
    }

    if (!leftMouseHeld && Mouse.isButtonDown(0)) // Left mouse, for MGs. Is
    // a holding thing
    {
      leftMouseHeld = true;
      plane.updateKeyHeldState(9, true);
    }
    if (leftMouseHeld && !Mouse.isButtonDown(0)) {
      leftMouseHeld = false;
      plane.updateKeyHeldState(9, false);
    }
    if (!rightMouseHeld && Mouse.isButtonDown(1)) // Right mouse
    {
      rightMouseHeld = true;
      plane.updateKeyHeldState(8, true);

    }
    if (rightMouseHeld && !Mouse.isButtonDown(1)) {
      rightMouseHeld = false;
      plane.updateKeyHeldState(8, false);

    }
    if (Mouse.isButtonDown(0)) {

      if(isJetPilot(plane))
        shootDelay(false);
    }
    if (Mouse.isButtonDown(1)) {

      if(isJetPilot(plane))
        shootDelay(true);
    }
  }

  private void shootDelay(boolean secondary) {
    EntityPlane p = (EntityPlane) ((EntitySeat) plane).driveable;
    if (!secondary) {

      primaryCooldown = p.ticksExisted;
    } else {
      secondaryCooldown = p.ticksExisted;
    }
  }

  @Override
  protected void keyTyped(char c, int i) {
    if (i == 1) {
      mc.displayGuiScreen(null);
      mc.displayInGameMenu();
    }
    if (i == 59) {
      mc.gameSettings.hideGUI = !mc.gameSettings.hideGUI;
    }
    if (i == 61) {
      mc.gameSettings.showDebugInfo = !mc.gameSettings.showDebugInfo;
    }
    if (i == 63) {
      if(isHeliGunner(plane))
        return;
      mc.gameSettings.thirdPersonView = (mc.gameSettings.thirdPersonView + 1) % 3;
      if (mc.gameSettings.thirdPersonView == 1)
        mc.setRenderViewEntity((plane.getCamera() == null ? mc.thePlayer : plane.getCamera()));
      else
        mc.setRenderViewEntity(mc.thePlayer);
    }
    if (i == 66) {
      mc.gameSettings.smoothCamera = !mc.gameSettings.smoothCamera;
    }
    if (i == mc.gameSettings.keyBindInventory.getKeyCode()) {
      mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
    }
    if (i == mc.gameSettings.keyBindDrop.getKeyCode()) {
      // mc.thePlayer.dropCurrentItem();
    }
    if (i == mc.gameSettings.keyBindChat.getKeyCode()) {
      mc.displayGuiScreen(new GuiChat());
    }
    if (i == mc.gameSettings.keyBindCommand.getKeyCode()) {
      mc.displayGuiScreen(new GuiChat("/"));
    }
    if (i == KeyInputHandler.debugKey.getKeyCode()) {
      FlansMod.packetHandler.sendToServer(new PacketRequestDebug());
    }
    if (i == KeyInputHandler.reloadModelsKey.getKeyCode()) {
      FlansModClient.reloadModels(false);
    }
    if (i == KeyInputHandler.switchFireModeKey.getKeyCode()) {
      if (plane instanceof EntitySeat && ((EntitySeat) plane).driveable != null && ((EntitySeat) plane).seatInfo != null
          && ((EntitySeat) plane).seatInfo.id == 0) {
        ((EntitySeat) plane).driveable.fireMode.pressToggleButton(((EntitySeat) plane).driveable);
      }
    }

    if (i > 1 && i < 11) {
      int num = i-2;
      if (plane instanceof EntitySeat && ((EntitySeat) plane).driveable != null && ((EntitySeat) plane).seatInfo != null && ((EntitySeat) plane).seatInfo.id == 0) {
        PacketVehicleFireMode m = ((EntitySeat) plane).driveable.fireMode;
        m.trySetMode(num, ((EntitySeat) plane).driveable);
      }
    }
    // fires click event for other mods
    try {
      Class cl = Class.forName("com.mcb.client.KeyHandler");
      Method[] methods = cl.getMethods();
      for (Method m : methods) {
        if ("driveableKeyPress".equals(m.getName())) {
          m.invoke(null, i);
          break;
        }
      }
    } catch (Exception e) {
      // e.printStackTrace();
    }
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    int textureX = 200;
    int textureY = 200;
    //	try {
    if (mc.gameSettings.thirdPersonView == 0 && plane instanceof EntitySeat && ((EntitySeat) plane).driveable != null) {
      GL11.glEnable(3042 /*GL_BLEND*/);
      EntitySeat plane = (EntitySeat) this.plane;

      if (plane.driveable instanceof EntityPlane && isHeliGunner((EntityPlane) plane.driveable)) {
        drawRect(0,0,width, height, 0xb0405040);
        drawGunnerScreen(textureX, textureY, mouseX, mouseY, partialTicks);
      }
      if (plane.driveable instanceof EntityPlane && isJetPilot((EntityPlane) plane.driveable)) {

        drawRadar(textureX, textureY);
        drawJetScreen(textureX, textureY, mouseX, mouseY, partialTicks);
      }
      if (plane.driveable instanceof EntityVehicle && isTankScreen((EntityVehicle) plane.driveable)) {

        drawTankScreen(textureX, textureY, mouseX, mouseY, partialTicks);
      }
      if (enableRadar(plane.driveable)) {
        GL11.glEnable(3042 /*GL_BLEND*/);
        drawRadar(textureX, textureY);
      }
      GL11.glDisable(3042 /*GL_BLEND*/);
    }


    //	} catch (Exception e) {
    //		e.printStackTrace();
    //	}

    drawEject(textureX, textureY, mouseX, mouseY, partialTicks);


    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  private void drawTankScreen(int textureX, int textureY, int mouseX, int mouseY, float partialTicks) {

    mc.renderEngine.bindTexture(texture);

    EntitySeat seat = (EntitySeat) plane;
    EntityDriveable driveable = seat.driveable;

    boolean isMainCannon = seat.seatInfo != null && seat.seatInfo.id == 0;
    boolean radar = enableRadar(plane) || driveable.driveableType.equals("mim23");
    //crosshair
    GlStateManager.pushMatrix();
    float scale = 2;
    float nscale = 1/scale;
    float crossScale = (radar? 1.5f : scale) *gunnerZoom;
    float crossnScale = 1/crossScale;
    GlStateManager.scale(crossScale, crossScale, crossScale);
    if(radar){
      //CROSSHAIR
      drawModalRectWithCustomSizedTexture((int) (crossnScale *width/2-20), (int) (crossnScale *height/2-20), 160, 100, 40, 40, textureX, textureY);
    } else if (isMainCannon) {
      drawModalRectWithCustomSizedTexture((int) (crossnScale * width / 2 - 55 / 2), (int) (crossnScale * height / 2 - 27 / 2), 0, 164, 55, 27, textureX,
          textureY);
    } else {
      drawModalRectWithCustomSizedTexture((int) (crossnScale * width / 2 - 19 / 2), (int) (crossnScale * height / 2 - 19 / 2), 66, 181, 19, 19, textureX,
          textureY);
    }
    GlStateManager.popMatrix();

    int turrX = (int) (nscale * width / 2 + 40);
    int turrY = (int) (nscale*height - 36);
    //turret orientation
    GlStateManager.pushMatrix();
    GlStateManager.scale(scale, scale, scale);
    drawModalRectWithCustomSizedTexture(turrX, turrY, 55, 182, 11, 18, textureX, textureY);


    float yaw = seat.looking.getYaw()+180;

    GlStateManager.pushMatrix();
    int x = turrX+5;
    int y = turrY + (isMainCannon? 7 : 10);
    GlStateManager.translate(x+0.5f, y+0.5f, 0);
    GlStateManager.rotate(yaw, 0, 0, 1);
    GlStateManager.translate(-x-0.5f, -y-0.5f, 0);
    drawModalRectWithCustomSizedTexture(x, y, 55, 187, 1, (isMainCannon? 10 : 7), textureX, textureY);
    GlStateManager.popMatrix();
    GlStateManager.popMatrix();


   drawSelectedFireMode(seat, 0xcccccc, 0xFF7070);

    //angles and zoom

    NumberFormat decimalFormatter = new DecimalFormat("#0.00");

    if (seat.playerLooking != null) {


      drawCenteredString(fontRendererObj, decimalFormatter.format(seat.playerLooking.getYaw()), width/2, 10, 0xcccccc);
      drawCenteredString(fontRendererObj, decimalFormatter.format(seat.playerLooking.getPitch()), width/2, 20, 0xcccccc);
    }
    drawString(fontRendererObj, "ZOOM", 12, height/2-5, 0xcccccc);
    String zoom = (gunnerZoom == 2? 4 : gunnerZoom == 4? 8 : 1) + "x";
    drawString(fontRendererObj, zoom,12, height/2+5, 0xcccccc);
    drawString(fontRendererObj, "MODE: VIS", 12, height/2+20, 0xcccccc);

    //fuel
    int fuel = (int) Math.max(0, Math.min(100, 100 *seat.driveable.driveableData.fuelInTank / Math.max(seat.driveable.getDriveableType().fuelTankSize, 1)));
    drawString(fontRendererObj, "FUEL: " + fuel + "%", 12, height/2+35, fuel < 10? fuel < 1? 0xFF5500 : 0xFF9900 : 0xcccccc);

    //WARN
    int warnSound = 0;
    boolean fire = false;
    for(DriveablePart part : driveable.getDriveableData().parts.values())
    {
      if (part.onFire || part.maxHealth > 0 && part.health < part.maxHealth / 4 &&
          (part.type == EnumDriveablePart.core || part.type == EnumDriveablePart.turret)) {
        fire = part.onFire || fire;
        warnSound = Math.max(warnSound, part.health < part.maxHealth / 8 ? part.health < part.maxHealth / 16 ? 4 : 2 : 1);
      }
      if (part.maxHealth > 0 && driveable instanceof EntityVehicle && (
                    part.type == EnumDriveablePart.backLeftWheel ||part.type == EnumDriveablePart.backRightWheel ||part.type == EnumDriveablePart.frontLeftWheel ||part.type == EnumDriveablePart.frontRightWheel ||
                        part.type == EnumDriveablePart.rightTrack || part.type == EnumDriveablePart.leftTrack)) {

          warnSound = Math.max(warnSound, part.health < part.maxHealth / 8 ? part.health < part.maxHealth / 16 ? 2 : 1 : 0);
      }
    }
    if (warnSound > 0) {
      int col = warnSound == 1 ? 0xD44300 : warnSound == 2 ? 0xD43000 : 0xFF0000;
      if(warnSound == 1 ||  warnSound == 2 && ((EntitySeat) plane).ticksExisted%20 < 10 || warnSound == 4 && ((EntitySeat) plane).ticksExisted%10 < 5)
        drawCenteredString(fontRendererObj, "WARN", width/2, height/2+40, col);
    }
    if (fire) {
      drawCenteredString(fontRendererObj, "- FIRE -", width/2, height/2+52, driveable.ticksExisted%10<5? 0xFF9900 : 0xFF5500);
    }
    GlStateManager.color(1,1,1);


    //border
    drawGradientRect(0, 0, width, height/4, 0xFF000000, 0);
    drawGradientRect(0, height-height/4, width, height, 0, 0xFF000000);
    drawGradientRect(0, height-height/4, width, height, 0, 0xFF000000);
  }

  private void drawSelectedFireMode(EntitySeat seat, int col, int highlightCol) {

    int y = height-60;

    //information text
    String nameOfGun = getCurrentFireMode();
    String gunName = nameOfGun.toUpperCase();
    int gunNameCol = col;
    //draw later because the col might change

    //selected gun
    if (!seat.driveable.fireMode.modes.isEmpty() && seat.seatInfo != null && seat.seatInfo.id == 0) {
      String s = "";
      String space = "";
      int spaceI = Math.max(2, 7 - seat.driveable.fireMode.modes.size());
      while (spaceI-- > 0) space += " ";
      for (int i = 0; i < seat.driveable.fireMode.modes.size(); i++) {
        boolean sel = seat.driveable.fireMode.getCurrentMode() == i;
        //s += space + "[" + (sel? "X" : " ") + "]";
        s += space + (!sel? (i+1) + "" : "["+(i+1)+"]");
      }
      s = s.substring(space.length());

      //s = "[" + Keyboard.getKeyName(KeyInputHandler.switchFireModeKey.getKeyCode()) + "]";
      fontRendererObj.drawStringWithShadow(s, width/2-fontRendererObj.getStringWidth(s)/2, y+25, col);

      float shootDelay = seat.driveable.fireMode.secondary? seat.driveable.shootDelaySecondary : seat.driveable.shootDelayPrimary;
      if (shootDelay > 0) {
        gunNameCol = highlightCol;
      }


      int ammoLeft = seat.driveable.fireMode.getCurrentShootable() == null? 0 : seat.driveable.fireMode.getCurrentShootable().ammoLeft;
      s = ammoLeft + "";
      while (s.length() < 4)
        s = "0" + s;
      fontRendererObj.drawStringWithShadow(s, width/2-fontRendererObj.getStringWidth(s)/2, y, col);
    }


    fontRendererObj.drawStringWithShadow(gunName, width / 2 - fontRendererObj.getStringWidth(gunName) / 2, y + 10, gunNameCol);

    drawRect(width/2-40,y+20,width/2+40,y+22,0xFF000000 | col);
  }

  private String getCurrentFireMode() {

    EntityDriveable driveable = ((EntitySeat) plane).driveable;
    FireModeWeapon bullet = driveable.fireMode.getCurrentShootable();
    if (bullet != null) {
      return bullet.bullet.name;
    }
    return "-";
  }

  private void drawEject(int textureX, int textureY, int mouseX, int mouseY, float partialTicks) {

    if (ejectTimer > 0) {

      int w = 64;
      int h = 64;

      float progress = Math.min(1, (ejectTimer+partialTicks) / EJECT_TIME);
      float nprogress = 1 - progress;

      GL11.glEnable(3042 /*GL_BLEND*/);
      mc.renderEngine.bindTexture(texture);
      GL11.glColor3f(1,1,1);
      drawModalRectWithCustomSizedTexture(width / 2 - w / 2, height / 2 - h / 2, 0, 100, w, (int) (h), textureX, textureY);


      //if(ejectTimer > EJECT_TIME && ejectTimer%4 < 2)
      //		GL11.glColor3f(0,0,0);
      //	else
      GL11.glColor3f(nprogress*2f,progress*0.7f,0);
      drawModalRectWithCustomSizedTexture(width / 2 - w / 2,
          (int) Math.ceil((height / 2f - h / 2f)+h*nprogress), 0,
          (float) Math.ceil(100+h*nprogress), w, (int) Math.floor(h*progress), textureX, textureY);
      GL11.glDisable(3042 /*GL_BLEND*/);
    }

  }

  private void drawJetScreen(int textureX, int textureY, int mouseX, int mouseY, float partialTicks) {
    EntityPlane driveable = (EntityPlane) ((EntitySeat) plane).driveable;

    //CROSSHAIR
    drawModalRectWithCustomSizedTexture(width/2-20, height/2-20, 160, 100, 40, 40, textureX, textureY);


    //HEIGHT RECT
    drawModalRectWithCustomSizedTexture(width/2+50, height/2-70, 112, 128, 32, 12, textureX, textureY);

    //SPEED RECT
    drawModalRectWithCustomSizedTexture(width/2-82, height/2-70, 112, 128, 32, 12, textureX, textureY);


    //PITCH
    float pitch = driveable.axes.getPitch();
    float roll = driveable.axes.getRoll();
    for (int i = 0; i < 20; i++) {
      int num = i-10;
      int h = (int) ( - pitch * 3 + 30 * num);
      if(h > 30 || h < -20)
        continue;
      int x = width / 2 - 28;
      int y = height / 2 + h;
      mc.renderEngine.bindTexture(texture);
      GL11.glColor3f(1,1,1);
      GL11.glPushMatrix();
      GL11.glTranslatef(x+28, y, 0);
      GL11.glRotatef(roll, 0,0,45);
      GL11.glTranslatef(-x-28, -y, 0);
      drawModalRectWithCustomSizedTexture( x, y, 144, num < 1? 146 : 140, 56, 8, textureX, textureY);
      GL11.glScalef(0.5f,0.5f,0.5f);
      if (num != 0) {
        drawString(fontRendererObj, (-num * 10) + "", num < 0? width - 28 * 2 - 16 : width - 28 * 2 - 20, height + h * 2 + 3, 0x00a900);
        drawString(fontRendererObj, (-num * 10) + "", width + 28 * 2 + 5, height + h * 2 + 3, 0x00a900);
      }
      //	GL11.glRotatef(-roll, width/2-28, 0, height / 2+h);
      GL11.glPopMatrix();
    }


    //TEXT
    NumberFormat decimalFormatter = new DecimalFormat("#0.0");

    //HEIGHT
    double h = driveable.posY - driveable.worldObj.getTopSolidOrLiquidBlock(driveable.getPosition()).getY();
    h = Math.min(9999, h);
    String ht = h < 1000 ? decimalFormatter.format(h) : ""+Math.round(h);
    drawCenteredString(fontRendererObj, ht, width/2+50+16, height/2-70+2, 0x00a900);


    //SPEED
    float speed = driveable.getRealSpeedXYZ()*20;
    if(speed < 0.1 && speed > -0.1)
      speed = 0;
    speed = Math.min(9999, speed);
    String speedT = speed < 1000 ? decimalFormatter.format(speed) : ""+Math.round(speed);
    drawCenteredString(fontRendererObj, speedT, width/2-82+16, height/2-70+2, 0x00a900);

    //GEAR
    if (driveable.varGear && driveable.getPlaneType().hasGear) {
      drawString(fontRendererObj, "GEAR DOWN", width/2+50, height/2-50, 0x00a900);
    }

    //WEAPONS
    drawSelectedFireMode((EntitySeat) plane, 0x00a900, 0xFFa900);


    //WARN
    int warnSound = 0;
    boolean fire = false;
    for(DriveablePart part : driveable.getDriveableData().parts.values())
    {
      if (part.onFire || part.health < part.maxHealth / 4 &&
          (part.type == EnumDriveablePart.core || part.type == EnumDriveablePart.turret ||
              driveable instanceof EntityPlane && (
                  part.type == EnumDriveablePart.tail ||
                      (part.type == EnumDriveablePart.leftWing || part.type == EnumDriveablePart.rightWing)) && ((EntityPlane) driveable).getPlaneType().mode != EnumPlaneMode.HELI)) {

        fire = part.onFire || fire;
        warnSound = Math.max(warnSound, part.health < part.maxHealth / 8 ? part.health < part.maxHealth / 16 ? 4 : 2 : 1);
      }
    }
    if (warnSound > 0) {
      int col = warnSound == 1 ? 0xD44300 : warnSound == 2 ? 0xD43000 : 0xFF0000;
      if(warnSound == 1 ||  warnSound == 2 && ((EntitySeat) plane).ticksExisted%20 < 10 || warnSound == 4 && ((EntitySeat) plane).ticksExisted%10 < 5)
        drawCenteredString(fontRendererObj, "WARN", width/2, height/2+40, col);
    }
    if (fire) {
      drawCenteredString(fontRendererObj, "- FIRE -", width/2, height/2+52, driveable.ticksExisted%10<5? 0xFF9900 : 0xFF5500);
    }

    if (driveable.driveableData.fuelInTank < driveable.getDriveableType().fuelTankSize * 0.2f && !Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
      if(driveable.driveableData.fuelInTank > driveable.getDriveableType().fuelTankSize * 0.05f || driveable.ticksExisted%10<5)
        drawCenteredString(fontRendererObj, (driveable.driveableData.fuelInTank <= 0? "NO FUEL" : "LOW FUEL"), width/2, height-85, driveable.driveableData.fuelInTank < driveable.getDriveableType().fuelTankSize * 0.1f? 0xFF5500 : 0xFF9900);
    }


    //small stuff
    GL11.glPushMatrix();
    GL11.glScalef(0.5f,0.5f,0.5f);

    //throttle
    drawString(fontRendererObj,""+Math.round(100*driveable.throttle), width-82*2+4, height-70*2+26, 0x00a900);

    //absolute height
    drawString(fontRendererObj,""+Math.round( driveable.posY), width+100+4, height-70*2+26, 0x00a900);

    GL11.glPopMatrix();
  }

  private void drawRadar(int textureX, int textureY) {
    EntityDriveable driveable = (EntityDriveable) ((EntitySeat) plane).driveable;

    int startX = width - 104;
    int startY = height - 150;
    //RADAR:
    mc.renderEngine.bindTexture(texture);
    drawModalRectWithCustomSizedTexture(startX, startY, 100, 0, 100, 100, textureX, textureY);

    if (!driveable.isPartIntact(EnumDriveablePart.nose)) {
      if (driveable.ticksExisted % 10 < 5) {
        drawCenteredString(fontRendererObj, "RADAR", startX + 50, startY + 30, 0xFF0000);
        drawCenteredString(fontRendererObj, "MALFUNCTION", startX + 50, startY + 40, 0xFF0000);
        mc.renderEngine.bindTexture(texture);
        GL11.glColor3f(1,1,1);
      }
      positions.clear();
      return;
    }else if (safezoneTimer == -1) {
      drawCenteredString(fontRendererObj, "NOT AVAILABLE", startX + 50, startY + 30, 0xAB5353);
      drawCenteredString(fontRendererObj, "IN SAFEZONE", startX + 50, startY + 40, 0xAB5353);
      mc.renderEngine.bindTexture(texture);
      GL11.glColor3f(1,1,1);
      return;
    }else if (driveable.ticksExisted < 200 || safezoneTimer > 0 && safezoneTimer>System.currentTimeMillis()-10000) {
      int perc = driveable.ticksExisted < 200? ((int)(100*driveable.ticksExisted/(float)200)) : (100-(int)((safezoneTimer+10000-System.currentTimeMillis()))/100);
      drawCenteredString(fontRendererObj, "BOOTING RADAR:", startX + 50, startY + 30, 0xaaaaaa);
      drawCenteredString(fontRendererObj, perc+"%", startX + 50, startY + 40, 0xaaaaaa);
      mc.renderEngine.bindTexture(texture);
      GL11.glColor3f(1,1,1);
      return;
    }


    GL11.glPushMatrix();
    GL11.glTranslatef(width - 54,height-100, 0);
    int tx = driveable.ticksExisted%180;
    GL11.glRotatef(tx*2, 0,0,45);
    GL11.glTranslatef(-width + 54,-height+100, 0);
    drawModalRectWithCustomSizedTexture( width - 54, height-111, 112, 116, 48, 12, textureX, textureY);
    GL11.glPopMatrix();



    if(positions == null)
      return;
    for(RadarPos pos : positions){
      if(pos.point == null)
        continue;
      int w = 4;
      int h = 4;
      int x = 0;
      Vector3i color = new Vector3i(220,220,0);

      switch (pos.type) {
        case -1:
          x = -6;
          break;
        case 1:
        case 2:
          x = 3+pos.type*4-4;
          break;
        case 3:
          x=3+4+5;
      }
      switch (pos.type) {
        case -1:
          w = 6;
          h = 6;
          break;
        case 0:
        case 3:
          w = h=3;
          break;
        case 2: w=5;
      }

      switch (pos.status) {
        case 0: color  = new Vector3i(100,100,100);
          break;
        case 2: color  = new Vector3i(0,180,0);
          break;
        case 3:
          if(driveable.ticksExisted%4 < 2)
            color  = new Vector3i(255,0,0);
          else
            color  = new Vector3i(255,255,0);
          break;
        case 4: color  = new Vector3i(255,140,0);
          break;
        case 5: color  = new Vector3i(155,255,155);
          break;
      }

      int pX = (int) (pos.point.x-w/(float)2);
      int pY = (int) (pos.point.y - h / (float) 2);

      if(pX > 47-(int)((float)w/2+0.5) || pX<-49 ||pY>47-h/2 ||pY<-49)
        continue;

      GL11.glColor3f(color.x/(float)255,color.y/(float)255,color.z/(float)255);
      drawModalRectWithCustomSizedTexture( startX+50 + pX, startY+50+pY, 106+x, 100, w, h, textureX, textureY);

    }
    GL11.glColor3f(1,1,1);

  }

  private void drawGunnerScreen(int textureX, int textureY, int mouseX, int mouseY, float partialTicks) {
    EntitySeat seat = (EntitySeat) plane;
    EntityDriveable driveable = ((EntitySeat) plane).driveable;

    String nameOfGun = seat.seatInfo.gunType.name;
    fontRendererObj.drawString("CANNON " + nameOfGun.toUpperCase(), 40, height-20, 0xcccccc);


    //	ItemStack bulletItemStack = driveable.getDriveableData().ammo[seat.seatInfo.gunnerID];
    //	int ammo = bulletItemStack == null? 0 : bulletItemStack.getMaxDamage() - bulletItemStack.getItemDamage() + bulletItemStack.getMaxDamage() * (bulletItemStack.stackSize - 1);
    //	int maxAmmo = bulletItemStack == null? 0 : bulletItemStack.getMaxDamage() * bulletItemStack.getMaxStackSize();
    //	String am = ammo + "/" + maxAmmo;
    //	fontRendererObj.drawString("AMMO " + am.toUpperCase(), 52, height-20, 0xcccccc);

    NumberFormat decimalFormatter = new DecimalFormat("#0.00");

    if (seat.playerLooking != null) {


      drawCenteredString(fontRendererObj, decimalFormatter.format(seat.playerLooking.getYaw()), width/2, 10, 0xcccccc);
      drawCenteredString(fontRendererObj, decimalFormatter.format(seat.playerLooking.getPitch()), width/2, 20, 0xcccccc);
    }
    drawString(fontRendererObj, "ZOOM", width-20-fontRendererObj.getStringWidth("ZOOM"), height/2, 0xcccccc);
    String zoom = (gunnerZoom == 2? 4 : gunnerZoom == 4? 8 : 1) + "x";
    drawString(fontRendererObj, zoom,width-20-fontRendererObj.getStringWidth(zoom), height/2+10, 0xcccccc);
    drawString(fontRendererObj, "MODE: IR", width-20-fontRendererObj.getStringWidth("MODE: IR"), height/2+25, 0xcccccc);


    mc.renderEngine.bindTexture(texture);
    drawModalRectWithCustomSizedTexture(width / 2 - 50, height / 2 - 50, 0, 0, 100, 100, textureX, textureY);
  }

  @Override
  public void updateScreen() {
    if (mc.gameSettings.thirdPersonView == 1)
      mc.setRenderViewEntity((plane.getCamera() == null ? mc.thePlayer : plane.getCamera()));
    else if (mc.getRenderViewEntity() != mc.thePlayer) {
      mc.setRenderViewEntity(mc.thePlayer);
    }
    if(!ejecting)
      ejectTimer = 0;
    ejecting = false;

    if(zoomCooldown > 0)
      zoomCooldown--;
  }

  @Override
  public void handleInput() {
    EntityPlayer player = (EntityPlayer) plane.getControllingEntity();
    if (player != mc.thePlayer) {
      mc.displayGuiScreen(null);
      return;
    }
    if (!Mouse.isGrabbed()) {
      mc.mouseHelper.grabMouseCursor();
    }
    handleMouseInput();

    for (; Keyboard.next();) {
      try {
        handleKeyboardInput();
      } catch (IOException e) {
      }
    }

    int l = Mouse.getDX();
    int m = Mouse.getDY();

    plane.onMouseMoved(l, m);

    if (plane != null && !plane.isDead() && plane.getControllingEntity() != null
        && plane.getControllingEntity() instanceof EntityPlayer) {
      if (FlansMod.proxy.keyDown(mc.gameSettings.keyBindForward.getKeyCode()))// KeyInputHandler.accelerateKey.getKeyCode()))
      {
        plane.pressKey(0, player);
      }
      if (FlansMod.proxy.keyDown(mc.gameSettings.keyBindBack.getKeyCode()))// KeyInputHandler.decelerateKey.getKeyCode()))
      {
        plane.pressKey(1, player);
      }
      if (FlansMod.proxy.keyDown(mc.gameSettings.keyBindLeft.getKeyCode()))// KeyInputHandler.leftKey.getKeyCode()))
      {
        plane.pressKey(2, player);
      }
      if (FlansMod.proxy.keyDown(mc.gameSettings.keyBindRight.getKeyCode()))// KeyInputHandler.rightKey.getKeyCode()))
      {
        plane.pressKey(3, player);
      }
      if (FlansMod.proxy.keyDown(mc.gameSettings.keyBindJump.getKeyCode()))// KeyInputHandler.upKey.getKeyCode()))
      {
        plane.pressKey(4, player);
      }
      if (FlansMod.proxy.keyDown(KeyInputHandler.downKey.getKeyCode())) {
        plane.pressKey(5, player);
      }
      if (FlansMod.proxy.keyDown(mc.gameSettings.keyBindSneak.getKeyCode()))// KeyInputHandler.exitKey.getKeyCode()))
      {
        if(ejectTimer++ > EJECT_TIME || player != null && player.capabilities.isCreativeMode)
          plane.pressKey(6, player);
        ejecting = true;
      }
      if (FlansMod.proxy.keyDown(KeyInputHandler.inventoryKey.getKeyCode())) {
        plane.pressKey(7, player);
      }
      if (FlansMod.proxy.keyDown(KeyInputHandler.controlSwitchKey.getKeyCode())) {
        plane.pressKey(10, player);
      }
      if (FlansMod.proxy.keyDown(KeyInputHandler.leftRollKey.getKeyCode())) {
        plane.pressKey(11, player);
      }
      if (FlansMod.proxy.keyDown(KeyInputHandler.rightRollKey.getKeyCode())) {
        plane.pressKey(12, player);
      }
      if (FlansMod.proxy.keyDown(KeyInputHandler.gearKey.getKeyCode())) {
        plane.pressKey(13, player);
      }
      if (FlansMod.proxy.keyDown(KeyInputHandler.doorKey.getKeyCode())) {
        plane.pressKey(14, player);
      }
      if (FlansMod.proxy.keyDown(KeyInputHandler.modeKey.getKeyCode())) {
        plane.pressKey(15, player);
      }
      if (FlansMod.proxy.keyDown(KeyInputHandler.flareKey.getKeyCode())) {
        plane.pressKey(20, player);
      }
      // if(FlansMod.proxy.keyDown(KeyInputHandler.trimKey.getKeyCode()))
      // {
      // plane.pressKey(16, player);
      // }

    } else {
      mc.displayGuiScreen(null);
    }
  }

  @Override
  public void drawBackground(int i) {
    // Plane gauges overlay
  }

  @Override
  public boolean doesGuiPauseGame() {
    return false;
  }

  public EntityDriveable shouldUpdateRadarPositions() {
    if (positions != null && enableRadar(plane)) {
      return ((EntitySeat)plane).driveable;
    }
    return null;
  }

  public static class RadarPos {

    public byte type = 0; //0: player (dot), 1:tank/turret (square), 2:plane (triangle), 3:missile (T)
    public byte status = 0; //0: inactive(grey), 1: active(yellow), 2: active in safezone(green), 3: active locking player (missiles)(yellow/red), 4: active being in render distance(orange), 5:active friendly (light green)

    public int entityId = 0;
    public BlockPos position;

    //runtime stuff
    public Point2f point;

    public RadarPos(byte type, byte status, int entityId, BlockPos position) {
      this.type = type;
      this.status = status;
      this.entityId = entityId;
      this.position = position;
    }


  }


  public void receiveRadarPositions(ArrayList<RadarPos> positions) {
    GuiDriveableController.positions = positions;
    //	if(!(plane instanceof EntitySeat && ((EntitySeat) plane).driveable != null))
    //		return;
    //	for(RadarPos pos : positions)
    //		pos.update(((EntitySeat) plane).driveable);
    //	this.positions = positions;
  }
}
