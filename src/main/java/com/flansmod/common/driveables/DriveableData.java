package com.flansmod.common.driveables;

import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.parts.EnumPartCategory;
import com.flansmod.common.parts.ItemPart;
import com.flansmod.common.parts.PartType;
import com.flansmod.common.vector.Vector3f;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;

public class DriveableData implements IInventory {

  /**
   * The name of this driveable's type
   */
  public String type;
  /**
   * The sizes of each inventory (guns, bombs / mines, missiles / shells, cargo)
   */
  public int numGuns, numBombs, numMissiles, numCargo;
  /**
   * The inventory stacks
   */
  public ItemStack[] ammo, bombs, missiles, cargo;
  /**
   * The engine in this driveable
   */
  public PartType engine;
  /**
   * The stack in the fuel slot
   */
  public ItemStack fuel;
  /**
   * The amount of fuel in the tank
   */
  public float fuelInTank;
  /**
   * Each driveable part has a small class that holds its current status
   */
  public HashMap<EnumDriveablePart, DriveablePart> parts;
  /**
   * Paintjob index
   */

  public boolean ammoSlotContentsChanged;

  public int paintjobID;
  //TODO
  public Vector3f color3f = new Vector3f(1, 1, 1);

  public DriveableData(NBTTagCompound tags, int paintjobID) {
    this(tags);
    this.paintjobID = paintjobID;
  }

  public DriveableData(NBTTagCompound tags) {
    parts = new HashMap<EnumDriveablePart, DriveablePart>();
    readFromNBT(tags);
  }

  public void readFromNBT(NBTTagCompound tag) {
    if (tag == null) {
      return;
    }
    if (!tag.hasKey("Type")) {
      return;
    }

    type = tag.getString("Type");
    DriveableType dType = DriveableType.getDriveable(type);
    numBombs = dType.numBombSlots;
    numCargo = dType.numCargoSlots;
    numMissiles = dType.numMissileSlots;
    numGuns = dType.ammoSlots();
    engine = PartType.getPart(tag.getString("Engine"));
    paintjobID = tag.getInteger("Paint");
    ammo = new ItemStack[numGuns];
    bombs = new ItemStack[numBombs];
    missiles = new ItemStack[numMissiles];
    cargo = new ItemStack[numCargo];
    for (int i = 0; i < numGuns; i++) {
      ammo[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Ammo " + i));
    }

    for (int i = 0; i < numBombs; i++) {
      bombs[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Bombs " + i));
    }

    for (int i = 0; i < numMissiles; i++) {
      missiles[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Missiles " + i));
    }

    for (int i = 0; i < numCargo; i++) {
      cargo[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Cargo " + i));
    }

    fuel = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Fuel"));
    fuelInTank = tag.getInteger("FuelInTank");
    for (EnumDriveablePart part : EnumDriveablePart.values()) {
      parts.put(part, new DriveablePart(part, dType.health.get(part)));
    }
    for (DriveablePart part : parts.values()) {
      part.readFromNBT(tag);
    }
    if (tag.hasKey("color_r")) {
      float cx = tag.getFloat("color_r");
      float cy = tag.getFloat("color_g");
      float cz = tag.getFloat("color_b");
      color3f = new Vector3f(cx, cy, cz);
    }
  }

  public void writeToNBT(NBTTagCompound tag) {
    tag.setString("Type", type);
    tag.setString("Engine", engine.shortName);
    tag.setInteger("Paint", paintjobID);
    for (int i = 0; i < ammo.length; i++) {
      if (ammo[i] != null) {
        tag.setTag("Ammo " + i, ammo[i].writeToNBT(new NBTTagCompound()));
      }
    }
    for (int i = 0; i < bombs.length; i++) {
      if (bombs[i] != null) {
        tag.setTag("Bombs " + i, bombs[i].writeToNBT(new NBTTagCompound()));
      }
    }
    for (int i = 0; i < missiles.length; i++) {
      if (missiles[i] != null) {
        tag.setTag("Missiles " + i, missiles[i].writeToNBT(new NBTTagCompound()));
      }
    }
    for (int i = 0; i < cargo.length; i++) {
      if (cargo[i] != null) {
        tag.setTag("Cargo " + i, cargo[i].writeToNBT(new NBTTagCompound()));
      }
    }
    if (fuel != null) {
      tag.setTag("Fuel", fuel.writeToNBT(new NBTTagCompound()));
    }
    tag.setInteger("FuelInTank", (int) fuelInTank);
    for (DriveablePart part : parts.values()) {
      part.writeToNBT(tag);
    }

    if (color3f.x != 1 || color3f.y != 1 || color3f.z != 1) {
      tag.setFloat("color_r", color3f.x);
      tag.setFloat("color_g", color3f.y);
      tag.setFloat("color_b", color3f.z);
    }
  }

  @Override
  public int getSizeInventory() {
    return getFuelSlot() + 1;
  }

  @Override
  public ItemStack getStackInSlot(int i) {
    //Find the correct inventory
    ItemStack[] inv = ammo;
    if (i >= ammo.length) {
      i -= ammo.length;
      inv = bombs;
      if (i >= bombs.length) {
        i -= bombs.length;
        inv = missiles;
        if (i >= missiles.length) {
          i -= missiles.length;
          inv = cargo;
          if (i >= cargo.length) {
            return fuel;
          }
        }
      }
    }
    //Return the stack in the slot
    return inv[i];
  }

  @Override
  public ItemStack decrStackSize(int i, int j) {
    ammoSlotContentsChanged = true;
    //Find the correct inventory
    ItemStack[] inv = ammo;
    if (i >= ammo.length) {
      i -= ammo.length;
      inv = bombs;
      if (i >= bombs.length) {
        i -= bombs.length;
        inv = missiles;
        if (i >= missiles.length) {
          i -= missiles.length;
          inv = cargo;
          if (i >= cargo.length) {
            //Put the fuel stack in a stack array just to simplify the code
            i -= cargo.length;
            inv = new ItemStack[1];
            inv[0] = fuel;

            setInventorySlotContents(getFuelSlot(), null);
          }
        }
      }
    }
    //Decrease the stack size
    if (inv[i] != null) {
      if (inv[i].stackSize <= j) {
        ItemStack itemstack = inv[i];
        inv[i] = null;
        return itemstack;
      }
      ItemStack itemstack1 = inv[i].splitStack(j);
      if (inv[i].stackSize <= 0) {
        inv[i] = null;
      }
      return itemstack1;
    } else {
      return null;
    }

  }

  @Override
  public ItemStack removeStackFromSlot(int i) {
    ammoSlotContentsChanged = true;
    return getStackInSlot(i);
  }

  @Override
  public void setInventorySlotContents(int i, ItemStack stack) {
    ammoSlotContentsChanged = true;
    //Find the correct inventory
    ItemStack[] inv = ammo;
    if (i >= ammo.length) {
      i -= ammo.length;
      inv = bombs;
      if (i >= bombs.length) {
        i -= bombs.length;
        inv = missiles;
        if (i >= missiles.length) {
          i -= missiles.length;
          inv = cargo;
          if (i >= cargo.length) {
            fuel = stack;
            return;
          }
        }
      }
    }
    //Set the stack
    inv[i] = stack;
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

  public int getAmmoInventoryStart() {
    return 0;
  }

  public int getBombInventoryStart() {
    return ammo.length;
  }

  public int getMissileInventoryStart() {
    return ammo.length + bombs.length;
  }

  public int getCargoInventoryStart() {
    return ammo.length + bombs.length + missiles.length;
  }

  public int getFuelSlot() {
    return ammo.length + bombs.length + missiles.length + cargo.length;
  }

  @Override
  public boolean isItemValidForSlot(int i, ItemStack itemstack) {
    if (i < getBombInventoryStart() && itemstack != null && itemstack
        .getItem() instanceof ItemBullet) //Ammo
    {
      return true;
    }
    if (i >= getBombInventoryStart() && i < getMissileInventoryStart() && itemstack != null
        && itemstack.getItem() instanceof ItemBullet) //Ammo
    {
      return true;
    }
    if (i >= getMissileInventoryStart() && i < getCargoInventoryStart() && itemstack != null
        && itemstack.getItem() instanceof ItemBullet) {
      return true;
    }
    if (i >= getCargoInventoryStart() && i < getFuelSlot()) {
      return true;
    }
    //Fuel
    return i == getFuelSlot() && itemstack != null && itemstack.getItem() instanceof ItemPart
        && ((ItemPart) itemstack.getItem()).type.category == EnumPartCategory.FUEL;
  }

  @Override
  public String getName() {
    return "Flan's Secret Data";
  }

  @Override
  public boolean hasCustomName() {
    return false;
  }

  @Override
  public IChatComponent getDisplayName() {
    return null;
  }

  @Override
  public void openInventory(EntityPlayer player) {
  }

  @Override
  public void closeInventory(EntityPlayer player) {
  }

  @Override
  public int getField(int id) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setField(int id, int value) {
    // TODO Auto-generated method stub

  }

  @Override
  public int getFieldCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }
}
