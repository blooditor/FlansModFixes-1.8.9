package com.flansmod.common.guns;

import com.flansmod.common.network.PacketHitmark.HitMarkType;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class EntityShootable extends Entity {

  public boolean sentHitMarker = false;
  public EntityShootable(World w) {
    super(w);
  }

  public abstract void sendHitMarker(HitMarkType indirect);
}
