//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2019 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: OOW H.C.A.R.
// Model Creator: Lukas Dause   LolTiger
// Created on: 05.12.2019 - 17:04:46
// Last changed on: 05.12.2019 - 17:04:46

package com.flansmod.client.model.mcb; //Path where the model is located

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;

public class ModelHCAR extends ModelGun //Same as Filename
{

  int textureX = 512;
  int textureY = 512;

  public ModelHCAR() //Same as Filename
  {
    gunModel = new ModelRendererTurbo[14];
    gunModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 0
    gunModel[1] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 1
    gunModel[2] = new ModelRendererTurbo(this, 33, 1, textureX, textureY); // Box 2
    gunModel[3] = new ModelRendererTurbo(this, 49, 1, textureX, textureY); // Box 4
    gunModel[4] = new ModelRendererTurbo(this, 65, 1, textureX, textureY); // Box 5
    gunModel[5] = new ModelRendererTurbo(this, 73, 1, textureX, textureY); // Box 6
    gunModel[6] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Box 7
    gunModel[7] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Box 8
    gunModel[8] = new ModelRendererTurbo(this, 97, 1, textureX, textureY); // Box 9
    gunModel[9] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Box 10
    gunModel[10] = new ModelRendererTurbo(this, 129, 1, textureX, textureY); // Box 11
    gunModel[11] = new ModelRendererTurbo(this, 145, 1, textureX, textureY); // Box 12
    gunModel[12] = new ModelRendererTurbo(this, 153, 1, textureX, textureY); // Box 13
    gunModel[13] = new ModelRendererTurbo(this, 169, 1, textureX, textureY); // Box 14

    gunModel[0]
        .addShapeBox(0F, 0F, 0F, 9, 2, 2, 0F, 0F, -0.25F, -0.4F, 0F, -0.25F, -0.4F, 0F, -0.25F,
            -0.4F, 0F, -0.25F, -0.4F, 0F, -0.25F, -0.4F, 0F, -0.25F, -0.4F, 0F, -0.25F, -0.4F, 0F,
            -0.25F, -0.4F); // Box 0
    gunModel[0].setRotationPoint(1F, -3.75F, -1F);

    gunModel[1].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 1
    gunModel[1].setRotationPoint(1F, -2.3F, -0.5F);
    gunModel[1].rotateAngleZ = -0.2443461F;

    gunModel[2]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.15F, 0F, -0.1F, -0.15F, 0F, -0.1F, -0.15F, 0F,
            -0.1F, -0.15F, 0F, -0.1F, -0.15F, 0F, -0.1F, -0.15F, 0F, -0.1F, -0.15F, 0F, -0.1F,
            -0.15F, 0F, -0.1F); // Box 2
    gunModel[2].setRotationPoint(2.85F, -2F, -0.5F);

    gunModel[3]
        .addShapeBox(0F, 0F, 0F, 4, 2, 2, 0F, -0.2F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F,
            -0.4F, -0.2F, -0.5F, -0.4F, -0.2F, -0.5F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F,
            -0.2F, -0.5F, -0.4F); // Box 4
    gunModel[3].setRotationPoint(-3F, -3.5F, -1F);

    gunModel[4]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0.1F, 0F, -0.2F, 0.1F, 0F,
            -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0F); // Box 5
    gunModel[4].setRotationPoint(-3F, 0F, -0.5F);
    gunModel[4].rotateAngleZ = 1.57079633F;

    gunModel[5]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0F, -0.8F, 0F, 0F, 1.2F, 0F, 0F, 1.2F, 0F, 0F, -0.8F,
            0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 0F, 0F, 0F); // Box 6
    gunModel[5].setRotationPoint(-2F, -1F, -0.5F);

    gunModel[6]
        .addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F, 0F, -1F, -0.4F, 0F, -0.5F, -0.4F, 0F, -0.5F, -0.4F,
            0F, -1F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F); // Box 7
    gunModel[6].setRotationPoint(0F, -4F, -1F);

    gunModel[7]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.2F, -0.4F, -0.1F, -0.2F, -0.4F, -0.1F, -0.2F,
            -0.4F, -0.1F, -0.2F, -0.4F, -0.1F, -0.2F, -0.4F, -0.1F, -0.2F, -0.4F, -0.1F, -0.2F,
            -0.4F, -0.1F, -0.2F, -0.4F, -0.1F); // Box 8
    gunModel[7].setRotationPoint(1.2F, -1.6F, -0.5F);

    gunModel[8]
        .addShapeBox(0F, 0F, 0F, 3, 2, 2, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F,
            -0.3F, 0F, -1F, -0.3F, 0F, -1F, -0.3F, 0F, -1F, -0.3F, 0F, -1F, -0.3F); // Box 9
    gunModel[8].setRotationPoint(6F, -2.9F, -1F);

    gunModel[9]
        .addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, -0.2F, -0.8F, -0.1F, -0.6F, -0.8F, -0.1F, -0.6F,
            -0.8F, -0.1F, -0.2F, -0.8F, -0.1F, 0F, 0F, -0.3F, -0.4F, 0F, -0.3F, -0.4F, 0F, -0.3F,
            0F, 0F, -0.3F); // Box 10
    gunModel[9].setRotationPoint(1F, -4.5F, -0.5F);

    gunModel[10]
        .addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F, -0.2F, -0.8F, -0.1F, -0.2F, -0.8F, -0.1F, -0.2F,
            -0.8F, -0.1F, -0.2F, -0.8F, -0.1F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F,
            -0.3F); // Box 11
    gunModel[10].setRotationPoint(6F, -4.5F, -0.5F);

    gunModel[11]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.2F, -0.8F, -0.1F, -0.2F, -0.8F, -0.1F, -0.2F,
            -0.8F, -0.1F, -0.2F, -0.8F, -0.1F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F,
            -0.3F); // Box 12
    gunModel[11].setRotationPoint(9F, -1F, 0.5F);
    gunModel[11].rotateAngleX = -3.14159265F;

    gunModel[12]
        .addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F,
            0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F,
            -0.2F); // Box 13
    gunModel[12].setRotationPoint(10F, -3.6F, -0.5F);

    gunModel[13]
        .addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F,
            0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F,
            -0.2F); // Box 14
    gunModel[13].setRotationPoint(10F, -2.9F, -0.5F);

//top mag
    ammoModel = new ModelRendererTurbo[2];
    ammoModel[0] = new ModelRendererTurbo(this, 41, 1, textureX, textureY); // Box 3
    ammoModel[0]
        .addShapeBox(0F, 0F, 0F, 2, 3, 1, 0F, 0F, 0F, -0.005F, 0F, 0F, -0.005F, 0F, 0F, -0.005F, 0F,
            0F, -0.005F, 0F, -0.5F, -0.005F, 0F, -0.5F, -0.005F, 0F, -0.5F, -0.005F, 0F, -0.5F,
            -0.005F); // Box 3
    ammoModel[0].setRotationPoint(3.7F, -2F, -0.5F);

    //bottom mag
    ammoModel[1] = new ModelRendererTurbo(this, 185, 1, textureX, textureY); // Box 16
    ammoModel[1]
        .addShapeBox(0F, 0F, 0F, 2, 3, 1, 0F, 0F, -0.5F, -0.005F, -0.06F, -1.05F, -0.005F, -0.06F,
            -1.05F, -0.005F, 0F, -0.5F, -0.005F, 0F, -0.5F, -0.005F, 0F, -0.5F, -0.005F, 0F, -0.5F,
            -0.005F, 0F, -0.5F, -0.005F); // Box 16
    ammoModel[1].setRotationPoint(3.56F, 0F, -0.5F);
    ammoModel[1].rotateAngleZ = 0.26179939F;

    barrelAttachPoint = new Vector3f(14F / 16F, 3.1F / 16F, 0F / 16F);

    scopeAttachPoint = new Vector3f(2.3F / 16F, 3.5F / 16F, 0F / 16F);

    gripAttachPoint = new Vector3f(7.5 / 16F, 1.8F / 16F, 0F / 16F);

    animationType = EnumAnimationType.BOTTOM_CLIP;

    translateAll(0F, 0F, 0F);

    flipAll();
  }
}