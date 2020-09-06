//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2019 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: AK Alfa
// Model Creator: LolTigerYT
// Created on: 20.11.2019 - 13:01:32
// Last changed on: 20.11.2019 - 13:01:32

package com.flansmod.client.model.mcb; //Path where the model is located

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;

public class ModelAK_ALFA extends ModelGun //Same as Filename
{

  int textureX = 512;
  int textureY = 512;

  public ModelAK_ALFA() //Same as Filename
  {
    gunModel = new ModelRendererTurbo[12];
    slideModel = new ModelRendererTurbo[1];
    gunModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 0
    gunModel[1] = new ModelRendererTurbo(this, 17, 1, textureX, textureY); // Box 1
    gunModel[2] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 3
    gunModel[3] = new ModelRendererTurbo(this, 41, 1, textureX, textureY); // Box 4
    gunModel[4] = new ModelRendererTurbo(this, 57, 1, textureX, textureY); // Box 5
    gunModel[5] = new ModelRendererTurbo(this, 73, 1, textureX, textureY); // Box 6
    gunModel[6] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Box 7
    gunModel[7] = new ModelRendererTurbo(this, 97, 1, textureX, textureY); // Box 8
    gunModel[8] = new ModelRendererTurbo(this, 121, 1, textureX, textureY); // Box 8
    gunModel[9] = new ModelRendererTurbo(this, 129, 1, textureX, textureY); // Box 9
    gunModel[10] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 15
    slideModel[0] = new ModelRendererTurbo(this, 169, 1, textureX, textureY); // Box 14

    gunModel[0]
        .addShapeBox(0F, 0F, 0F, 5, 2, 1, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F,
            -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
    gunModel[0].setRotationPoint(0F, -3F, -0.5F);

    gunModel[1]
        .addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F, 0F, -1.1F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F,
            -1.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 1
    gunModel[1].setRotationPoint(0.35F, -2.05F, -0.5F);
    gunModel[1].rotateAngleZ = -0.27925268F;

    gunModel[2]
        .addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F,
            -0.5F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, -0.5F, 0.2F, 0F); // Box 3
    gunModel[2].setRotationPoint(-3F, -2.5F, -0.5F);

    gunModel[3].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 4
    gunModel[3].setRotationPoint(-2.38F, -2.6F, -0.5F);
    gunModel[3].rotateAngleZ = 4.71238898F;

    gunModel[4]
        .addShapeBox(0F, 0F, 0F, 5, 2, 1, 0F, 0F, -0.5F, 0F, -0.7F, -0.5F, 0F, -0.7F, -0.5F, 0F, 0F,
            -0.5F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F); // Box 5
    gunModel[4].setRotationPoint(5F, -3F, -0.5F);

    gunModel[5]
        .addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F, 0F, 0F, -0.7F, 0F, 0F, -0.7F, 0F, 0F, -0.7F, 0F, 0F,
            -0.7F, 0F, -0.7F, -0.8F, 0F, -0.7F, -0.8F, 0F, -0.7F, -0.8F, 0F, -0.7F, -0.8F); // Box 6
    gunModel[5].setRotationPoint(5F, -1.3F, -0.5F);

    gunModel[6]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F,
            -0.8F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.8F, 0F); // Box 7
    gunModel[6].setRotationPoint(2F, -1F, -0.5F);

    gunModel[7]
        .addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F, -0.3F, -0.1F, -0.8F, -0.8F, -0.1F, -0.8F, -0.8F,
            -0.1F, -0.8F, -0.3F, -0.1F, -0.8F, 0F, -0.7F, -0.7F, -0.7F, -0.7F, -0.7F, -0.7F, -0.7F,
            -0.7F, 0F, -0.7F, -0.7F); // Box 8
    gunModel[7].setRotationPoint(0F, -2.81F, -0.5F);

    gunModel[8]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.6F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.6F, 0F, 0F,
            -0.6F, -1F, 0F, 0F, -0.7F, 0F, 0F, -0.7F, 0F, -0.6F, -1F, 0F); // Box 8
    gunModel[8].setRotationPoint(-1F, -1.3F, -0.5F);

    gunModel[9]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F,
            -0.2F, -1F, 0F, 0F, -0.7F, 0F, 0F, -0.7F, 0F, -0.2F, -1F, 0F); // Box 9
    gunModel[9].setRotationPoint(6F, -1.3F, 0.5F);
    gunModel[9].rotateAngleY = 3.14159265F;

    gunModel[10]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.6F, 0F, 0F, -0.6F,
            0F, -0.6F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, -0.6F, 0F, -0.6F, -0.6F); // Box 15
    gunModel[10].setRotationPoint(9.2F, -2.4F, -0.2F);

    slideModel[0]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.8F, 0F, 0F,
            -0.8F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, -0.8F, -0.8F, 0F); // Box 14
    slideModel[0].setRotationPoint(3F, -2.25F, 0F);
    gunSlideDistance = 1f / 6f;

    defaultBarrelModel = new ModelRendererTurbo[1];
    gunModel[11] = new ModelRendererTurbo(this, 185, 1, textureX, textureY); // Box 16
    defaultBarrelModel[0] = new ModelRendererTurbo(this, 193, 1, textureX, textureY); // Box 17

    gunModel[11]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.6F, 0F, 0F, -0.6F,
            0F, -0.6F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, -0.6F, 0F, -0.6F, -0.6F); // Box 16
    gunModel[11].setRotationPoint(9.2F, -1.9F, -0.2F);

    defaultBarrelModel[0]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.57F, 0F, 0F,
            -0.57F, 0F, -0.57F, 0F, -0.5F, -0.57F, 0F, -0.5F, -0.57F, -0.57F, 0F, -0.57F,
            -0.57F); // Box 17
    defaultBarrelModel[0].setRotationPoint(11.2F, -1.92F, -0.22F);

    ammoModel = new ModelRendererTurbo[4];
    ammoModel[0] = new ModelRendererTurbo(this, 137, 1, textureX, textureY); // Box 10
    ammoModel[1] = new ModelRendererTurbo(this, 145, 1, textureX, textureY); // Box 11
    ammoModel[2] = new ModelRendererTurbo(this, 153, 1, textureX, textureY); // Box 12
    ammoModel[3] = new ModelRendererTurbo(this, 161, 1, textureX, textureY); // Box 13

    ammoModel[0]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.2F, 0F, -0.14F, -0.2F, 0F, -0.14F, -0.2F, 0F,
            -0.14F, -0.2F, 0F, -0.14F, -0.2F, 0.2F, -0.14F, -0.2F, 0F, -0.14F, -0.2F, 0F, -0.14F,
            -0.2F, 0.2F, -0.14F); // Box 10
    ammoModel[0].setRotationPoint(1.9F, -0.8F, -0.5F);
    ammoModel[0].rotateAngleZ = 0.2443461F;

    ammoModel[1]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.2F, 0F, -0.14F, -0.2F, -0.05F, -0.14F, -0.2F,
            -0.05F, -0.14F, -0.2F, 0F, -0.14F, -0.2F, 0.2F, -0.14F, -0.2F, 0F, -0.14F, -0.2F, 0F,
            -0.14F, -0.2F, 0.2F, -0.14F); // Box 11
    ammoModel[1].setRotationPoint(2.2F, 0.4F, -0.5F);
    ammoModel[1].rotateAngleZ = 0.40142573F;

    ammoModel[2]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.2F, 0.08F, -0.14F, -0.2F, -0.05F, -0.14F, -0.2F,
            -0.05F, -0.14F, -0.2F, 0.08F, -0.14F, -0.2F, 0.2F, -0.14F, -0.2F, 0F, -0.14F, -0.2F, 0F,
            -0.14F, -0.2F, 0.2F, -0.14F); // Box 12
    ammoModel[2].setRotationPoint(2.74F, 1.6F, -0.5F);
    ammoModel[2].rotateAngleZ = 0.61086524F;

    ammoModel[3]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.2F, -0.8F, -0.1F, 0F, -0.8F, -0.1F, 0F, -0.8F,
            -0.1F, -0.2F, -0.8F, -0.1F, -0.2F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, -0.2F, 0F,
            -0.1F); // Box 13
    ammoModel[3].setRotationPoint(2.75F, 1.9F, -0.5F);
    ammoModel[3].rotateAngleZ = 0.73303829F;

    barrelAttachPoint = new Vector3f(11F / 16F, 1.76F / 16F, 0F / 16F);

    scopeAttachPoint = new Vector3f(2F / 16F, 2.6F / 16F, 0F / 16F);

    gripAttachPoint = new Vector3f(7 / 16F, 1.2F / 16F, 0F / 16F);

    translateAll(0F, 0F, 0F);

    animationType = EnumAnimationType.BOTTOM_CLIP;

    flipAll();
  }
}