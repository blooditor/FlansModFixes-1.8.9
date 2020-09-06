package com.flansmod.apocalypse.client.model;

import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;

public class LayerBipedArmorSurvivor extends LayerBipedArmor {
    public LayerBipedArmorSurvivor(RenderSurvivor renderSurvivor) {
        super(renderSurvivor);
    }

    @Override
    protected void initArmor() {
        this.field_177189_c = new ModelSurvivor(0.5F);
        this.field_177186_d = new ModelSurvivor(1.0F);
    }
}
