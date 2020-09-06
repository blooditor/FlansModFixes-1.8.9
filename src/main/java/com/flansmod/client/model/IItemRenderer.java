package com.flansmod.client.model;

import com.flansmod.client.ItemRenderType;
import net.minecraft.item.ItemStack;

public interface IItemRenderer {

  boolean handleRenderType(ItemStack item, ItemRenderType type);

  void renderItem(ItemRenderType type, ItemStack item, Object... data);


}
