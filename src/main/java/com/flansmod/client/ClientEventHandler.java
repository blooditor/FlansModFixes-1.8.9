package com.flansmod.client;

import com.flansmod.api.IControllable;
import com.flansmod.client.gui.GuiDriveableController;
import com.flansmod.client.model.InstantBulletRenderer;
import com.flansmod.client.model.RenderFlag;
import com.flansmod.client.model.RenderGun;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.guns.ItemGun;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 * All handled events for the client should go through here and be passed on. Makes it easier to see
 * which events are being handled by the mod
 */
public class ClientEventHandler {

  private KeyInputHandler keyInputHandler = new KeyInputHandler();
  private ClientRenderHooks renderHooks = new ClientRenderHooks();
  private Field renderOutlinesField;

  @SubscribeEvent
  public void renderTick(TickEvent.RenderTickEvent event) {
    switch (event.phase) {
      case START: {
        RenderGun.smoothing = event.renderTickTime;
        FlansModClient.UpdateCameraZoom(event.renderTickTime);
        renderHooks.SetPartialTick(event.renderTickTime);
        break;
      }
      case END: {

        break;
      }
    }
  }

  @SubscribeEvent
  public void clientTick(TickEvent.ClientTickEvent event) {
    switch (event.phase) {
      case START: {
        //Handle all packets received since last tick
        FlansMod.getPacketHandler().handleClientPackets();
        FlansModClient.UpdateFlashlights(Minecraft.getMinecraft());
        FlansModClient.tickStart();
        break;
      }
      case END: {
        InstantBulletRenderer.UpdateAllTrails();
        renderHooks.update();
        RenderFlag.angle += 2F;
        FlansModClient.tick();

        break;
      }
    }
  }

  @SubscribeEvent
  public void chatMessage(ClientChatReceivedEvent event) {
    if (event.message.getUnformattedText().equals("#flansmod")) {
      event.setCanceled(true);
    }
  }

/*  @SubscribeEvent
  public void CheckForOffHandWeaponSwitch(MouseEvent event) {
    EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem()
        .getItem() instanceof ItemGun) {
      try {
        if (((ItemGun) player.getCurrentEquippedItem().getItem()).GetType().oneHanded &&
            Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())
            && Math.abs(event.dwheel) > 0) {
          event.setCanceled(true);
        }
      } catch (Exception e) {
      } //fixes crash for illegal key bindings

    }
  }*/

  @SubscribeEvent
  public void CheckKeyInput(KeyInputEvent event) {
    keyInputHandler.CheckKeyInput(event);
  }

  @SubscribeEvent
  public void renderWorld(RenderWorldLastEvent event) {
    InstantBulletRenderer.RenderAllTrails(event.partialTicks);
  }

  // ----------------------------------------
  // Lots of events for the ClientRenderHooks
  // ----------------------------------------
  @SubscribeEvent
  public void renderItemFrame(RenderItemInFrameEvent event) {
    renderHooks.renderItemFrame(event);
  }

  @SubscribeEvent
  public void renderHeldItem(RenderHandEvent event) {
    renderHooks.renderHeldItem(event);
  }

  @SubscribeEvent
  public void renderThirdPersonWeapons(RenderLivingEvent.Post event) {
    renderHooks.renderThirdPersonWeapons(event);
  }

  @SubscribeEvent
  public void renderMobs(RenderLivingEvent.Pre event) {
    if (Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntitySeat && Minecraft
        .getMinecraft().currentScreen instanceof GuiDriveableController && GuiDriveableController
        .isHeliGunner((IControllable) Minecraft.getMinecraft().thePlayer.ridingEntity)) {
      if (renderOutlinesField == null) {
        renderOutlinesField = ReflectionHelper.findField(RendererLivingEntity.class, "renderOutlines", "field_177098_i");
        renderOutlinesField.setAccessible(true);
      }
      try {
        renderOutlinesField.set(event.renderer, true);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  @SubscribeEvent
  public void renderPlayer(RenderPlayerEvent.Pre event) {
    renderHooks.renderPlayer(event);
  }

  @SubscribeEvent
  public void cameraSetup(CameraSetup event) {
    renderHooks.cameraSetup(event);
  }

  @SubscribeEvent(priority = EventPriority.HIGH)
  public void ModifyHUD(RenderGameOverlayEvent event) {
    renderHooks.ModifyHUD(event);
  }
}
