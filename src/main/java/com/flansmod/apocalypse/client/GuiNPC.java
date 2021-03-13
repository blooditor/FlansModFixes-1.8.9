package com.flansmod.apocalypse.client;

import com.flansmod.apocalypse.common.entity.ContainerNPC;
import com.flansmod.apocalypse.common.entity.EntitySurvivor;
import com.flansmod.apocalypse.common.network.PacketNpcEdit;
import com.flansmod.common.FlansMod;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiNPC extends GuiContainer {

  private static final ResourceLocation texture = new ResourceLocation("mcb", "gui/npc.png");

  public ContainerNPC container;
  public InventoryPlayer inventory;
  public World world;
  public EntitySurvivor survivor;
  private GuiButton modeButton;
  int currentMode;
  GuiTextField name;
  private int aiLevel;

  public GuiNPC(InventoryPlayer inventoryplayer, World world1, EntitySurvivor npc, int ownerMode) {
    super(new ContainerNPC(inventoryplayer, world1, npc));
    survivor = npc;
    inventory = inventoryplayer;
    world = world1;
    container = (ContainerNPC) inventorySlots;
    ySize = 180;
    name = new GuiTextField(1, Minecraft.getMinecraft().fontRendererObj, 70, 8, 90, 14);
    name.setMaxStringLength(16);
    name.setEnableBackgroundDrawing(true);
    name.setVisible(true);
    name.setFocused(true);
    //name.setTextColor(16777215);
    name.setText("");
    name.setCursorPosition(0);
    currentMode = ownerMode;
  }

  @Override
  protected void keyTyped(char c, int i) {
    if (i == 1) {
      mc.thePlayer.closeScreen();
    } else {
      // if(i != 14 || name.getText().length() > 5)
      if (i == 14 || c >= 'A' && c <= 'z' && c != '`' && c != '^' || c == ' '
          || c >= '0' && c <= '9') {
        name.textboxKeyTyped(c, i);
      }
    }
  }

  @Override
  public void updateScreen() {
    name.updateCursorCounter();
    super.updateScreen();
  }


  @Override
  protected void drawGuiContainerForegroundLayer(int x, int y) {
    fontRendererObj.drawString("Name:", 39, (ySize - 170) + 2, 0x404040);
    // fontRendererObj.drawString("Movement:", 39, (ySize - 150) + 2, 0x404040);
    fontRendererObj.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);

    NumberFormat decimalFormatter = new DecimalFormat("#0.00");
    fontRendererObj
        .drawString(decimalFormatter.format(survivor.getHealth()), 138, (ySize - 113) + 2,
            survivor.getHealth() > survivor.getMaxHealth() - 2 ? 0x00FF00
                : survivor.getHealth() > survivor.getMaxHealth() / 2 ? 0xFFFF00 : 0xFF0000);
    fontRendererObj
        .drawString("IQ: " + aiLevel + "%", aiLevel == 100 ? 135 : 138, (ySize - 113) + 17,
            0x666666);

    RenderHelper.enableGUIStandardItemLighting();
    GL11.glColor3f(1.0F, 1.0F, 1.0F);

    //draw armor:
    mc.renderEngine.bindTexture(texture);
    for (int i = 0; i < 4; i++) {
      ItemStack stack = survivor.getCurrentArmor(3 - i);
      if (stack == null) {
        drawModalRectWithCustomSizedTexture(13, 11 + i * 18, 176, i * 16, 16, 16, 256, 256);
      }
    }
    if (survivor.getHeldItem() == null) {
      drawModalRectWithCustomSizedTexture(46, 29, 176 + 16, 0, 16, 16, 256, 256);
    }
    for (int i = 0; i < 4; i++) {
      ItemStack stack = survivor.inventory.getStackInSlot(i + 5);
      if (stack == null) {
        drawModalRectWithCustomSizedTexture(46 + 18 * i, 65, 176 + 16, 16, 16, 16, 256, 256);
      }
    }
		/*

        int slotsDone = 0;
			for(int i = 0; i < driveable.getDriveableType().seats.length; i++)
			{
				if(slotsDone >= 3 + scroll)
					continue;
				if(driveable.getDriveableType().seats[i].gunType != null)
				{
					if(slotsDone >= scroll)
					{
						fontRendererObj.drawString(driveable.getDriveableType().seats[i].gunName, 53, 29 + 19 * (slotsDone - scroll), 0x000000);
						drawStack(new ItemStack(driveable.getDriveableType().seats[i].gunType.getItem()), 10, 25 + 19 * (slotsDone - scroll));
					}
					slotsDone++;
				}
			}
			for(int i = 0; i < driveable.getDriveableType().pilotGuns.size(); i++)
			{
				if(slotsDone >= 3 + scroll)
					continue;
				if(driveable.getDriveableType().pilotGuns.get(i).type != null)
				{
					if(slotsDone >= scroll)
					{
						fontRendererObj.drawString("Driver's gun " + (i + 1), 53, 29 + 19 * (slotsDone - scroll), 0x000000);
						drawStack(new ItemStack(driveable.getDriveableType().pilotGuns.get(i).type.getItem()), 10, 25 + 19 * (slotsDone - scroll));
					}
					slotsDone++;
				}
			}
		}*/

    if (name.getText().length() < 5 || name.getText().contains("  ") || !name.getText().trim()
        .equals(name.getText())) {
      name.setTextColor(0xFF0000);
    } else {
      name.setTextColor(16777215);
    }
    name.drawTextBox();

    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    RenderHelper.disableStandardItemLighting();
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    int m = (width - xSize) / 2;
    int n = (height - ySize) / 2;
    buttonList.clear();

    modeButton = new GuiButton(0, m + 91, n + 27, 70, 20, getModeButtonText());

    buttonList.add(modeButton);
    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  private void drawStack(ItemStack itemstack, int i, int j) {
    itemRender.renderItemIntoGUI(itemstack, i, j);
    itemRender.renderItemOverlayIntoGUI(fontRendererObj, itemstack, i, j, null);
  }


  @Override
  protected void drawGuiContainerBackgroundLayer(float f, int i1, int j1) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

    mc.renderEngine.bindTexture(texture);

    int j = (width - xSize) / 2;
    int k = (height - ySize) / 2;
    drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
    //drawTexturedModalRect(j + 9, k + 24 + 19 * row, 7, 97, 18 * ((row + scroll + 1) * 8 <= numItems ? 8 : numItems % 8), 18);
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    super.actionPerformed(button);

    if (button == this.modeButton) {
      currentMode = (currentMode + 1) % 3;

    }
  }

  @Override
  protected void mouseClicked(int i, int j, int k) throws IOException {
    super.mouseClicked(i, j, k);
    int m = i - (width - xSize) / 2;
    int n = j - (height - ySize) / 2;
  }

  @Override
  public boolean doesGuiPauseGame() {
    return false;
  }


  public void receivePacket(PacketNpcEdit packetNpcEdit) {
    currentMode = packetNpcEdit.mode;
    name.setText(packetNpcEdit.name);
    aiLevel = packetNpcEdit.aiLevel;
  }

  private String getModeButtonText() {
    String s = "";
    switch (currentMode) {
      case 0:
        s = "STANDING";
        break;
      case 1:
        s = "PATROLLING";
        break;
      case 2:
        s = "FOLLOWING";
        break;
    }
    return s;
  }

  @Override
  public void onGuiClosed() {
    super.onGuiClosed();

    FlansMod.getPacketHandler()
        .sendToServer(new PacketNpcEdit(name.getText(), (byte) currentMode, container.survivor, 0));
  }
}
