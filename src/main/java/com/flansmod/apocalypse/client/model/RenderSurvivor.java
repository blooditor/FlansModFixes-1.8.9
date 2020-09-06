package com.flansmod.apocalypse.client.model;

import com.flansmod.apocalypse.common.entity.EntitySurvivor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderSurvivor extends RenderBiped {
  private static final ResourceLocation SURVIVOR_SKIN = new ResourceLocation("flansmodapocalypse", "textures/entity/Survivor.png");


  public RenderSurvivor(RenderManager man, ModelBiped model, float f) {
    super(man, model, f);

    this.addLayer(new LayerBipedArmorSurvivor(this));


  }

  @Override
  public void doRender(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks) {
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
    EntitySurvivor survivor = (EntitySurvivor) entity;

    EntitySurvivor.EnumGunHeldState state = survivor.getGunHeldState();
  //  if(survivor.shooting)
  //    state = EntitySurvivor.EnumGunHeldState.AIM;
 //   else if(survivor.reloading || !survivor.hasAmmo)
  //    state = EntitySurvivor.EnumGunHeldState.DOWN;
    ((ModelSurvivor) this.mainModel).gunHeldState = state;
  }

  protected ResourceLocation getEntityTexture(EntityLiving entity) {
    return SURVIVOR_SKIN;
  }


}
