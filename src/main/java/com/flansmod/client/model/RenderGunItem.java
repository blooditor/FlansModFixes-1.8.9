package com.flansmod.client.model;

import com.flansmod.client.ItemRenderType;
import com.flansmod.common.guns.ItemGun;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class RenderGunItem extends RenderEntityItem {

  private RenderGun gunRenderer;

  public RenderGunItem(RenderManager renderManager, RenderItem renderItem, RenderGun gunRenderer) {
    super(renderManager, renderItem);
    this.gunRenderer = gunRenderer;
  }

  @Override
  public void doRender(EntityItem entity, double x, double y, double z, float p_177075_8_,
      float partialTicks) {
    ItemStack stack = entity.getEntityItem();

    if (stack.getItem() instanceof ItemGun && ((ItemGun) stack.getItem()).GetType().model != null) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(x, y + 0.25D, z);
      GlStateManager.rotate(entity.ticksExisted + partialTicks, 0F, 1F, 0F);

      gunRenderer.renderItem(ItemRenderType.ENTITY, stack);
      GlStateManager.popMatrix();
    } else {
      super.doRender(entity, x, y, z, p_177075_8_, partialTicks);
    }
  }
}
