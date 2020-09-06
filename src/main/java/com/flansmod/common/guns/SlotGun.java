package com.flansmod.common.guns;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SlotGun extends Slot {

  private int slotID;
  private SlotGun gunSlot;

  public SlotGun(IInventory inventory, int i, int x, int y, SlotGun s) {
    super(inventory, i, x, y);
    slotID = i;
    gunSlot = s;
  }

  public boolean isItemValid(ItemStack stack) {
    if (stack != null && stack.getItem() instanceof ItemGun && !((ItemGun) stack.getItem())
        .GetType().deployable) {
      //((ItemGun)stack.getItem()).addInformation(stack, );
      ItemGun g = (ItemGun) stack.getItem();

      if (!stack.hasTagCompound()) {
        stack.setTagCompound(new NBTTagCompound());
      }
      if (!stack.getTagCompound().hasKey("ammo")) {
        NBTTagList ammoTagsList = new NBTTagList();
        for (int i = 0; i < g.GetType().numAmmoItemsInGun; i++) {
          ammoTagsList.appendTag(new NBTTagCompound());
        }
        stack.getTagCompound().setTag("ammo", ammoTagsList);
      }
    }
    switch (slotID) {
      case 0:
        return stack == null || (stack.getItem() instanceof ItemGun && !((ItemGun) stack.getItem())
            .GetType().deployable && stack.getTagCompound() != null);
      case 1:
        return stack == null || (canAttachToCurrentGun(stack)
            && ((ItemAttachment) stack.getItem()).type.type == EnumAttachmentType.barrel);
      case 2:
        return stack == null || (canAttachToCurrentGun(stack)
            && ((ItemAttachment) stack.getItem()).type.type == EnumAttachmentType.sights);
      case 3:
        return stack == null || (canAttachToCurrentGun(stack)
            && ((ItemAttachment) stack.getItem()).type.type == EnumAttachmentType.stock);
      case 4:
        return stack == null || (canAttachToCurrentGun(stack)
            && ((ItemAttachment) stack.getItem()).type.type == EnumAttachmentType.grip);
      default:
        return stack == null || (canAttachToCurrentGun(stack)
            && ((ItemAttachment) stack.getItem()).type.type == EnumAttachmentType.generic);
    }
  }

  public boolean canAttachToCurrentGun(ItemStack stack) {
    if (stack == null || !(stack.getItem() instanceof ItemAttachment) || !gunSlot.getHasStack()
        || !(gunSlot.getStack().getItem() instanceof ItemGun)) {
      return false;
    }
    AttachmentType attachmentType = ((ItemAttachment) stack.getItem()).type;
    GunType gunType = ((ItemGun) gunSlot.getStack().getItem()).GetType();
    return gunType.allowAllAttachments || gunType.allowedAttachments.contains(attachmentType) || true; //TODO
  }
}
