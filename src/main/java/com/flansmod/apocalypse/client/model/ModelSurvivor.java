package com.flansmod.apocalypse.client.model;

import com.flansmod.apocalypse.common.entity.EntitySurvivor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSurvivor extends ModelBiped {

    public EntitySurvivor.EnumGunHeldState gunHeldState = EntitySurvivor.EnumGunHeldState.PATROL;

    public ModelSurvivor(float v) {
        super(v);
    }

    public ModelSurvivor() {
        super();
    }

    @Override
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn) {


        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entityIn);

       // gunHeldState = EntitySurvivor.EnumGunHeldState.LOW;
        float f3 = 0.0F;
        float f4 = 0.0F;
        float swingModifier = 1f;
        float rightRotateAngleY = 0;
        float leftRotateAngleY = 0;
        float rightRotateAngleX = 0;
        float leftRotateAngleX = 0;
        switch (gunHeldState) {
            case DOWN:

                break;
            case PATROL:

                rightRotateAngleY = -(1F - f3 * 0.6F) + this.bipedHead.rotateAngleY*0.0f;
                leftRotateAngleY = 0.0F - f3 * 0.6F + this.bipedHead.rotateAngleY*0.0f + 0.4F;
                rightRotateAngleX = -((float)Math.PI / 3.5F) + this.bipedHead.rotateAngleX*0.0f;
                leftRotateAngleX = -((float)Math.PI / 7F) + this.bipedHead.rotateAngleX*0.0f;
                break;
            case LOW:
                rightRotateAngleY = -(0.4F - f3 * 0.6F) + this.bipedHead.rotateAngleY*0.5f;
                leftRotateAngleY = 0.15F - f3 * 0.6F + this.bipedHead.rotateAngleY*0.5f + 0.4F;
                rightRotateAngleX = -((float)Math.PI / 2.5F) + this.bipedHead.rotateAngleX*0.5f;
                leftRotateAngleX = -((float)Math.PI / 2.5F) + this.bipedHead.rotateAngleX*0.5f;
                break;
            case READY:
                /*rightRotateAngleY = -(0.2F - f3 * 0.6F) + this.bipedHead.rotateAngleY;
                leftRotateAngleY = 0.15F - f3 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
                rightRotateAngleX = -((float)Math.PI / 2.2F) + this.bipedHead.rotateAngleX;
                leftRotateAngleX = -((float)Math.PI / 2.2F) + this.bipedHead.rotateAngleX;
                break;*/
            case AIM:
                swingModifier = 0.2f;
                rightRotateAngleY = -(0.1F - f3 * 0.6F) + this.bipedHead.rotateAngleY*1f;
                leftRotateAngleY = 0.1F - f3 * 0.6F + this.bipedHead.rotateAngleY*1f + 0.4F;
                rightRotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX*1f;
                leftRotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX*1f;
                break;
        }
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleX -= f3 * 1.2F - f4 * 0.4F;
        this.bipedLeftArm.rotateAngleX -= f3 * 1.2F - f4 * 0.4F;

        float delta = 1f;
        this.bipedRightArm.rotateAngleY = (rightRotateAngleY) * delta;
        this.bipedLeftArm.rotateAngleY = (leftRotateAngleY) * delta;
        this.bipedRightArm.rotateAngleX = (rightRotateAngleX) * delta;
        this.bipedLeftArm.rotateAngleX = (leftRotateAngleX) * delta;

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * swingModifier * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * swingModifier * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * swingModifier * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * swingModifier * 0.05F;
    }

    @Override
    public void setModelAttributes(ModelBase model) {
        super.setModelAttributes(model);
        if (model instanceof ModelSurvivor) {
            this.gunHeldState = ((ModelSurvivor) model).gunHeldState;
        }
    }
}
