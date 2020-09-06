package com.flansmod.client.model;

import com.flansmod.client.ClientProxy;
import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.ItemRenderType;
import com.flansmod.common.ItemHolderType;
import com.flansmod.common.TileEntityItemHolder;
import com.flansmod.common.guns.ItemGun;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class RenderItemHolder extends TileEntitySpecialRenderer {

  public RenderItemHolder() {
  }

  @Override
  public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ,
      float p_180535_8_, int p_180535_9_) {
    TileEntityItemHolder holder = (TileEntityItemHolder) te;
    ModelItemHolder model = holder.type.model;

    if (model != null) {
      bindTexture(getTexture(holder.type));
      GlStateManager.pushMatrix();
      GlStateManager.translate((float) posX, (float) posY, (float) posZ);
      GlStateManager.rotate(180F, 0F, 0F, 1F);

      switch (EnumFacing.HORIZONTALS[holder.getBlockMetadata()]) {
        case NORTH:
          GlStateManager.translate(-1F, 0F, 0F);
          GlStateManager.rotate(0F, 0F, 1F, 0F);
          break;
        case EAST:
          GlStateManager.translate(-1F, 0F, 1F);
          GlStateManager.rotate(90F, 0F, 1F, 0F);
          break;
        case SOUTH:
          GlStateManager.translate(0F, 0F, 1F);
          GlStateManager.rotate(180F, 0F, 1F, 0F);
          break;
        case WEST:
          GlStateManager.rotate(270F, 0F, 1F, 0F);
          break;
        default:
          break;
      }

      model.render();

      ItemStack stack = holder.getStackInSlot(0);
      if (stack != null) {
        GlStateManager.rotate(180F, 0F, 0F, 1F);
        GlStateManager.translate(-0.5F, 0.5F, 0.5F);

        GlStateManager.translate(model.itemOffset.x, model.itemOffset.y, model.itemOffset.z);
        GlStateManager.rotate(model.itemRotation.x, 1F, 0F, 0F);
        GlStateManager.rotate(model.itemRotation.z, 0F, 0F, 1F);
        GlStateManager.rotate(model.itemRotation.y, 0F, 1F, 0F);

        if (stack.getItem() instanceof ItemGun
            && ((ItemGun) stack.getItem()).GetType().model != null) {
          ClientProxy.gunRenderer.renderItem(ItemRenderType.ENTITY, stack);
        } else {
          Minecraft.getMinecraft().getRenderItem()
              .renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
        }
      }

      GlStateManager.popMatrix();
    }
  }

  protected ResourceLocation getTexture(ItemHolderType type) {
    return FlansModResourceHandler.getTexture(type);
  }
}
