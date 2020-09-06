package com.flansmod.apocalypse.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SurvivorInventory implements IInventory {

  ItemStack[] content = new ItemStack[4];
  EntitySurvivor entitySurvivor;

  SurvivorInventory(EntitySurvivor survivor) {
    this.entitySurvivor = survivor;
  }

  @Override
  public int getSizeInventory() {
    return 9;
  }

  @Override
  public ItemStack getStackInSlot(int index) {
    if (index <= 3) {
      return entitySurvivor.getCurrentArmor(3 - index);
    }
    if (index == 4) {
      return entitySurvivor.getHeldItem();
    }
    return content[index - 5];
  }

  @Override
  public ItemStack decrStackSize(int index, int count) {
        /*f(content[index] != null){
            content[index].stackSize -= count;
            if(content[index].stackSize <= 0)
                content[index] = null;
        }
        return content[index];*/
    ItemStack stack = getStackInSlot(index);
    if (stack != null) {
      if (stack.stackSize <= count) {
        setInventorySlotContents(index, null);
        return stack;
      }
      ItemStack itemstack1 = stack.splitStack(count);
      if (stack.stackSize <= 0) {
        setInventorySlotContents(index, null);
      }
      return itemstack1;
    } else {
      return null;
    }
  }

  @Override
  public ItemStack removeStackFromSlot(int index) {
    // return content[index];
      /*  if(index <= 3)
            return entitySurvivor.getCurrentArmor(index);
        if(index == 4)
            return entitySurvivor.getHeldItem();*/
    // return getStackInSlot(index);
    //     return content[index];
    ItemStack stack = getStackInSlot(index);
    if (stack != null) {
      setInventorySlotContents(index, null);
      return stack;
    } else {
      return null;
    }
  }

  @Override
  public void setInventorySlotContents(int index, ItemStack stack) {

    if (index <= 3) {
      //content[index] = stack;
      entitySurvivor.setCurrentItemOrArmor(4 - index, stack);
    } else if (index == 4) {
      // content[index] = stack;
      entitySurvivor.setCurrentItemOrArmor(0, stack);
    } else {
      content[index - 5] = stack;
    }

  }

  @Override
  public int getInventoryStackLimit() {
    return 64;
  }

  @Override
  public void markDirty() {

  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player) {
    return true;
  }

  @Override
  public void openInventory(EntityPlayer player) {

  }

  @Override
  public void closeInventory(EntityPlayer player) {

  }

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    //return index > 3 || stack != null && stack.getItem() instanceof ItemArmor;
    return true;

  }

  @Override
  public int getField(int id) {
    return 0;
  }

  @Override
  public void setField(int id, int value) {

  }

  @Override
  public int getFieldCount() {
    return 0;
  }

  @Override
  public void clear() {

  }

  @Override
  public String getName() {
    return "NPC Inventory";
  }

  @Override
  public boolean hasCustomName() {
    return false;
  }

  @Override
  public IChatComponent getDisplayName() {
    return new ChatComponentText("NPC Inventory");
  }

  public void writeToNBT(NBTTagCompound t) {
    for (int i = 0; i < 4; i++) {
      if (content[i] != null) {
        NBTTagCompound t2 = new NBTTagCompound();
        content[i].writeToNBT(t2);
        t.setTag("ammo " + i, t2);
      }
    }
  }

  public void readFromNBT(NBTTagCompound t) {
    for (int i = 0; i < 4; i++) {
      NBTTagCompound t2 = t.getCompoundTag("ammo " + i);
      if (t2 != null) {
        content[i] = ItemStack.loadItemStackFromNBT(t2);
      }
    }
  }
}
