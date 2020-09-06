package com.flansmod.client;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

//TODO : Implement this!
public class ReloadSoundAtEntity extends MovingSoundAtEntity {

  public ReloadSoundAtEntity(ResourceLocation sound, Entity entity, boolean repeat) {
    super(sound, entity, repeat);
  }
  public String uuid;
}
