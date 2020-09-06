package com.flansmod.apocalypse.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerNPC extends Container {

  public InventoryPlayer inventory;
  public World world;
  public EntitySurvivor survivor;

  public ContainerNPC(InventoryPlayer inventoryplayer, World worldy, EntitySurvivor npc) {
    inventory = inventoryplayer;
    world = worldy;
    survivor = npc;

    //armor
    for (int i = 0; i < 4; i++) {
      addSlotToContainer(new SlotNPC(survivor.inventory, i, 13, 11 + i * 18));
    }
    //held item

    addSlotToContainer(new Slot(survivor.inventory, 4, 46, 29));

    //ammo
    for (int i = 0; i < 4; i++) {
      addSlotToContainer(new Slot(survivor.inventory, 5 + i, 46 + 18 * i, 65));
    }

    //Main inventory slots
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 9; col++) {
        addSlotToContainer(
            new Slot(inventoryplayer, col + row * 9 + 9, 8 + col * 18, 98 + row * 18));
      }

    }
    //Quickbar slots
    for (int col = 0; col < 9; col++) {
      addSlotToContainer(new Slot(inventoryplayer, col, 8 + col * 18, 156));
    }
  }


  @Override
  public boolean canInteractWith(EntityPlayer entityplayer) {
    return true;
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
    ItemStack stack = null;
    Slot currentSlot = inventorySlots.get(slotID);

    if (currentSlot != null && currentSlot.getHasStack()) {
      ItemStack slotStack = currentSlot.getStack();
      stack = slotStack.copy();

      int numItems = 9;
      if (slotID >= numItems) {
        if (!mergeItemStack(slotStack, 0, numItems, false)) {
          return null;
        }
      } else {
        if (!mergeItemStack(slotStack, numItems, inventorySlots.size(), true)) {
          return null;
        }
      }

      if (slotStack.stackSize == 0) {
        currentSlot.putStack(null);
      } else {
        currentSlot.onSlotChanged();
      }

      if (slotStack.stackSize == stack.stackSize) {
        return null;
      }

      currentSlot.onPickupFromSlot(player, slotStack);
    }

    return stack;
    //   return super.transferStackInSlot(player, slotID);
  }


  private class SlotNPC extends Slot {

    public SlotNPC(SurvivorInventory inventory, int i, int i1, int i2) {
      super(inventory, i, i1, i2);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
      return stack.getItem() instanceof ItemArmor && (((ItemArmor) stack.getItem()).armorType
          == this.getSlotIndex());
    }
  }
}
