//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2019 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: AAC Honey Badger
// Model Creator: Lukas Dause   LolTiger
// Created on: 03.12.2019 - 13:38:49
// Last changed on: 03.12.2019 - 13:38:49

package com.flansmod.client.model.mcb; //Path where the model is located

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;

public class ModelHoneyBadger extends ModelGun //Same as Filename
{

  int textureX = 512;
  int textureY = 512;

  public ModelHoneyBadger() //Same as Filename
  {
    gunModel = new ModelRendererTurbo[9];
    gunModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 0
    gunModel[1] = new ModelRendererTurbo(this, 17, 1, textureX, textureY); // Box 1
    gunModel[2] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 2
    gunModel[3] = new ModelRendererTurbo(this, 41, 1, textureX, textureY); // Box 4
    gunModel[4] = new ModelRendererTurbo(this, 49, 1, textureX, textureY); // Box 5
    gunModel[5] = new ModelRendererTurbo(this, 57, 1, textureX, textureY); // Box 6
    gunModel[6] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Box 7
    gunModel[7] = new ModelRendererTurbo(this, 97, 1, textureX, textureY); // Box 8
    gunModel[8] = new ModelRendererTurbo(this, 161, 1, textureX, textureY); // Box 13

    gunModel[0]
        .addShapeBox(0F, 0F, 0F, 4, 4, 3, 0F, 0F, -1F, -1F, 0F, -1F, -1F, 0F, -1F, -1F, 0F, -1F,
            -1F, 0F, -1.5F, -1F, 0F, -1.5F, -1F, 0F, -1.5F, -1F, 0F, -1.5F, -1F); // Box 0
    gunModel[0].setRotationPoint(1F, -4F, -1.5F);

    gunModel[1]
        .addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F,
            -0.1F, 0F, 0.5F, -0.1F, 0F, 0.5F, -0.1F, 0F, 0.5F, -0.1F, 0F, 0.5F, -0.1F); // Box 1
    gunModel[1].setRotationPoint(1F, -1.8F, -0.5F);
    gunModel[1].rotateAngleZ = -0.31415927F;

    gunModel[2]
        .addShapeBox(0F, 0F, 0F, 3, 2, 2, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F,
            0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F,
            -0.5F); // Box 2
    gunModel[2].setRotationPoint(-2F, -3.5F, -1F);

    gunModel[3]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F,
            0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0F, 0F); // Box 4
    gunModel[3].setRotationPoint(3F, -1.5F, -0.5F);

    gunModel[4]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0.3F, -0.4F, -0.2F, 0F, -0.4F, -0.2F, 0F, -0.4F,
            -0.2F, 0.3F, -0.4F, -0.2F, 0.4F, -0.4F, -0.2F, 0F, -0.4F, -0.2F, 0F, -0.4F, -0.2F, 0.4F,
            -0.4F, -0.2F); // Box 5
    gunModel[4].setRotationPoint(2F, -1.1F, -0.5F);

    gunModel[5]
        .addShapeBox(0F, 0F, 0F, 7, 2, 2, 0F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F,
            -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F,
            0F, -0.35F, -0.35F); // Box 6
    gunModel[5].setRotationPoint(5F, -3.35F, -1F);

    gunModel[6]
        .addShapeBox(0F, 0F, 0F, 2, 2, 2, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F,
            0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F,
            -0.5F); // Box 7
    gunModel[6].setRotationPoint(11.8F, -3.35F, -1F);

    gunModel[7]
        .addShapeBox(0F, 0F, 0F, 11, 1, 1, 0F, -0.4F, -0.8F, -0.2F, -0.1F, -0.8F, -0.2F, -0.1F,
            -0.8F, -0.2F, -0.4F, -0.8F, -0.2F, -0.4F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F,
            -0.4F, 0F, -0.3F); // Box 8
    gunModel[7].setRotationPoint(1F, -4F, -0.5F);

    gunModel[8]
        .addShapeBox(0F, 0F, 0F, 3, 2, 2, 0F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F,
            0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F,
            -0.5F); // Box 13
    gunModel[8].setRotationPoint(-1.5F, -3F, -1F);
    gunModel[8].rotateAngleZ = -1.57079633F;

    ammoModel = new ModelRendererTurbo[2];
    ammoModel[0] = new ModelRendererTurbo(this, 145, 1, textureX, textureY); // Box 11
    ammoModel[1] = new ModelRendererTurbo(this, 153, 1, textureX, textureY); // Box 12

    ammoModel[0]
        .addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F, -0.1F, 0F, -0.1F, -0.1F, 0F, -0.1F, -0.1F, 0F, -0.1F,
            -0.1F, 0F, -0.1F, -0.1F, -0.5F, -0.1F, -0.1F, -0.5F, -0.1F, -0.1F, -0.5F, -0.1F, -0.1F,
            -0.5F, -0.1F); // Box 11
    ammoModel[0].setRotationPoint(3F, -1F, -0.5F);

    ammoModel[1]
        .addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F, -0.1F, 0F, -0.1F, -0.15F, -0.4F, -0.1F, -0.15F, -0.4F,
            -0.1F, -0.1F, 0F, -0.1F, -0.1F, -0.5F, -0.1F, -0.1F, -0.5F, -0.1F, -0.1F, -0.5F, -0.1F,
            -0.1F, -0.5F, -0.1F); // Box 12
    ammoModel[1].setRotationPoint(3F, 0.5F, -0.5F);
    ammoModel[1].rotateAngleZ = 0.2268928F;

    slideModel = new ModelRendererTurbo[2];
    slideModel[0] = new ModelRendererTurbo(this, 129, 1, textureX, textureY); // Box 9
    slideModel[1] = new ModelRendererTurbo(this, 137, 1, textureX, textureY); // Box 10

    slideModel[0]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F,
            0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F,
            -0.3F, -0.4F, 0.2F); // Box 9
    slideModel[0].setRotationPoint(0.7F, -3.6F, -0.5F);

    slideModel[1]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.3F, -0.45F, 0.2F, -0.3F, -0.45F, 0.2F, -0.3F,
            -0.45F, 0.2F, -0.3F, -0.45F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F,
            0.2F, -0.3F, -0.4F, 0.2F); // Box 10
    slideModel[1].setRotationPoint(2.6F, -3.6F, -0.5F);
    slideModel[1].rotateAngleY = 1.55334303F;

    scopeAttachPoint = new Vector3f(3F / 16F, 3F / 16F, 0F / 16F);

    gripAttachPoint = new Vector3f(8 / 16F, 1.5F / 16F, 0F / 16F);

    animationType = EnumAnimationType.BOTTOM_CLIP;
    gunSlideDistance = 0.05F;

    translateAll(0F, 0F, 0F);

    flipAll();
  }
}