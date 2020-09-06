package com.flansmod.client;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

//TODO : Implement this!
public class MovingSoundAtEntity extends MovingSound {

  private final Entity entity;
  private float field_147669_l = 0.0F;

  public MovingSoundAtEntity(ResourceLocation sound, Entity entity, boolean repeat) {
    super(sound);
    this.entity = entity;
    this.repeat = repeat;
    repeatDelay = 0;
  }

  /**
   * Updates the JList with a new model.
   */
  public void update() {
    if (entity.isDead) {
      donePlaying = true;
    } else {
      xPosF = (float) entity.posX;
      yPosF = (float) entity.posY;
      zPosF = (float) entity.posZ;
		/*	float f = MathHelper.sqrt_double(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
	
			if ((double)f >= 0.01D)
			{
				field_147669_l = MathHelper.clamp_float(field_147669_l + 0.0025F, 0.0F, 1.0F);
				volume = 0.0F + MathHelper.clamp_float(f, 0.0F, 0.5F) * 0.7F;
			}
			else
			{
				field_147669_l = 0.0F;
				volume = 0.0F;
			}*/
    }
  }
}
