//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2019 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: VSS Vintorez
// Model Creator: Lukas Dause   LolTiger
// Created on: 07.12.2019 - 17:27:08
// Last changed on: 07.12.2019 - 17:27:08

package com.flansmod.client.model.mcb; //Path where the model is located

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;

public class Modelvss extends ModelGun //Same as Filename
{

  int textureX = 512;
  int textureY = 512;

  public Modelvss() //Same as Filename
  {
    gunModel = new ModelRendererTurbo[24];
    gunModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 0
    gunModel[1] = new ModelRendererTurbo(this, 9, 1, textureX, textureY); // Box 1
    gunModel[2] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 2
    gunModel[3] = new ModelRendererTurbo(this, 33, 1, textureX, textureY); // Box 3
    gunModel[4] = new ModelRendererTurbo(this, 49, 1, textureX, textureY); // Box 4
    gunModel[5] = new ModelRendererTurbo(this, 57, 1, textureX, textureY); // Box 6
    gunModel[6] = new ModelRendererTurbo(this, 65, 1, textureX, textureY); // Box 7
    gunModel[7] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Box 9
    gunModel[8] = new ModelRendererTurbo(this, 97, 1, textureX, textureY); // Box 10
    gunModel[9] = new ModelRendererTurbo(this, 105, 1, textureX, textureY); // Box 11
    gunModel[10] = new ModelRendererTurbo(this, 121, 1, textureX, textureY); // Box 12
    gunModel[11] = new ModelRendererTurbo(this, 137, 1, textureX, textureY); // Box 13
    gunModel[12] = new ModelRendererTurbo(this, 145, 1, textureX, textureY); // Box 14
    gunModel[13] = new ModelRendererTurbo(this, 161, 1, textureX, textureY); // Box 16
    gunModel[14] = new ModelRendererTurbo(this, 169, 1, textureX, textureY); // Box 17
    gunModel[15] = new ModelRendererTurbo(this, 177, 1, textureX, textureY); // Box 18
    gunModel[16] = new ModelRendererTurbo(this, 185, 1, textureX, textureY); // Box 19
    gunModel[17] = new ModelRendererTurbo(this, 193, 1, textureX, textureY); // Box 20
    gunModel[18] = new ModelRendererTurbo(this, 201, 1, textureX, textureY); // Box 21
    gunModel[19] = new ModelRendererTurbo(this, 209, 1, textureX, textureY); // Box 22
    gunModel[20] = new ModelRendererTurbo(this, 217, 1, textureX, textureY); // Box 23
    gunModel[21] = new ModelRendererTurbo(this, 225, 1, textureX, textureY); // Box 24
    gunModel[22] = new ModelRendererTurbo(this, 233, 1, textureX, textureY); // Box 25
    gunModel[23] = new ModelRendererTurbo(this, 241, 1, textureX, textureY); // Box 27

    gunModel[0]
        .addShapeBox(0F, 0F, 0F, 1, 3, 1, 0F, 0F, -0.2F, 0F, 0F, -0.02F, 0F, 0F, -0.02F, 0F, 0F,
            -0.2F, 0F, 0F, 0F, 0F, 0F, -0.18F, 0F, 0F, -0.18F, 0F, 0F, 0F, 0F); // Box 0
    gunModel[0].setRotationPoint(1F, -2F, -0.5F);
    gunModel[0].rotateAngleZ = -0.17453293F;

    gunModel[1]
        .addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F,
            -0.1F, 0F, 0.03F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.03F, 0F, 0F); // Box 1
    gunModel[1].setRotationPoint(1F, -2.8F, -0.5F);

    gunModel[2]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F,
            0.2F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 0.2F, 0F); // Box 2
    gunModel[2].setRotationPoint(3F, -1.8F, -0.5F);

    gunModel[3]
        .addShapeBox(0F, 0F, 0F, 4, 1, 1, 0F, -0.3F, -0.65F, -0.2F, 0F, -0.65F, -0.2F, 0F, -0.65F,
            -0.2F, -0.3F, -0.65F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 3
    gunModel[3].setRotationPoint(1F, -3.7F, -0.5F);

    gunModel[4]
        .addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F, 0F, -0.1F, 0.2F, 0.5F, -0.1F, 0.2F, 0.5F, -0.1F, 0.2F,
            0F, -0.1F, 0.2F, 0F, -0.3F, 0F, 0.5F, -0.9F, 0F, 0.5F, -0.9F, 0F, 0F, -0.3F,
            0F); // Box 4
    gunModel[4].setRotationPoint(5F, -2.8F, -0.5F);

    gunModel[5]
        .addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, -0.5F, -0.6F, -0.2F, 0F, -0.6F, -0.2F, 0F, -0.6F,
            -0.2F, -0.5F, -0.6F, -0.2F, -0.5F, 0F, 0.2F, 0F, 0F, 0.2F, 0F, 0F, 0.2F, -0.5F, 0F,
            0.2F); // Box 6
    gunModel[5].setRotationPoint(8F, -3.7F, 0.5F);
    gunModel[5].rotateAngleY = 3.14159265F;

    gunModel[6]
        .addShapeBox(0F, 0F, 0F, 7, 3, 3, 0F, 0F, -2F, -2F, 0F, -2F, -2F, 0F, -2F, -2F, 0F, -2F,
            -2F, 0F, -2F, -2F, 0F, -2F, -2F, 0F, -2F, -2F, 0F, -2F, -2F); // Box 7
    gunModel[6].setRotationPoint(7.5F, -3.9F, -1.5F);

    gunModel[7]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F,
            -0.1F, 0F, -0.1F, -0.1F, 0F, -0.1F, -0.1F, 0F, 0F, -0.1F, 0F); // Box 9
    gunModel[7].setRotationPoint(0F, -2.2F, -0.5F);

    gunModel[8]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, -0.4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.4F, 0F,
            0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F); // Box 10
    gunModel[8].setRotationPoint(-1F, -2.2F, -0.5F);

    gunModel[9]
        .addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F,
            -0.4F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F); // Box 11
    gunModel[9].setRotationPoint(-4F, -2.2F, -0.5F);

    gunModel[10]
        .addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F, 0F, -0.4F, 0F, -0.4F, -0.4F, 0F, -0.4F, -0.4F, 0F, 0F,
            -0.4F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 12
    gunModel[10].setRotationPoint(-4F, -0.2F, -0.5F);

    gunModel[11]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.2F, 0.5F, -0.2F, 0.2F, 0.5F, -0.2F, 0.2F, 0.5F,
            -0.2F, -0.2F, 0.5F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F,
            -0.2F); // Box 13
    gunModel[11].setRotationPoint(-2F, -0.8F, -0.5F);

    gunModel[12]
        .addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F,
            -0.5F, 0F, 0F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F); // Box 14
    gunModel[12].setRotationPoint(-4F, -1.8F, -0.5F);

    gunModel[13]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.6F, -0.8F, -0.3F, 0F, -0.8F, -0.3F, 0F, -0.8F,
            -0.3F, -0.6F, -0.8F, -0.3F, -0.6F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, -0.6F, 0F,
            -0.3F); // Box 16
    gunModel[13].setRotationPoint(0F, -3.2F, -0.5F);

    gunModel[14]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0.3F, -0.45F, -0.3F, 0.1F, -0.45F, -0.3F, 0.1F,
            -0.45F, -0.3F, 0.3F, -0.45F, -0.3F, 0.4F, -0.45F, -0.3F, 0.1F, -0.45F, -0.3F, 0.1F,
            -0.45F, -0.3F, 0.4F, -0.45F, -0.3F); // Box 17
    gunModel[14].setRotationPoint(2F, -1.2F, -0.5F);

    gunModel[15]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.6F, -0.73F, -0.3F, 0F, -0.73F, -0.3F, 0F, -0.73F,
            -0.3F, -0.6F, -0.73F, -0.3F, -0.6F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, -0.6F, 0F,
            -0.3F); // Box 18
    gunModel[15].setRotationPoint(13.5F, -3.9F, -0.5F);

    gunModel[16]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.2F, -0.75F, -0.3F, 0F, -0.75F, -0.3F, 0F, -0.75F,
            -0.3F, -0.2F, -0.75F, -0.3F, -0.2F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, -0.2F, 0F,
            -0.3F); // Box 19
    gunModel[16].setRotationPoint(7.4F, -3.9F, -0.5F);

    gunModel[17]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.85F, 0F, 0F,
            -0.85F, 0F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.95F, 0F, 0F, -0.95F); // Box 20
    gunModel[17].setRotationPoint(3F, -2.8F, 0.5F);

    gunModel[18]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.85F, 0F, 0F,
            -0.85F, 0F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.95F, 0F, 0F, -0.95F); // Box 21
    gunModel[18].setRotationPoint(4.5F, -2.8F, 0.5F);

    gunModel[19]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.85F, 0F, 0F,
            -0.85F, 0F, -0.7F, 0.1F, -0.6F, -0.7F, 0.1F, -0.6F, -0.7F, -0.95F, 0F, -0.7F,
            -0.95F); // Box 22
    gunModel[19].setRotationPoint(4.9F, -2.8F, 0.5F);
    gunModel[19].rotateAngleZ = -3.14159265F;

    gunModel[20]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, -0.85F, 0F, 0F,
            -0.85F, 0F, -0.7F, 0.1F, -0.6F, -0.7F, 0.1F, -0.6F, -0.7F, -0.95F, 0F, -0.7F,
            -0.95F); // Box 23
    gunModel[20].setRotationPoint(3.4F, -2.8F, 0.5F);
    gunModel[20].rotateAngleZ = -3.14159265F;

    gunModel[21]
        .addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0.2F, -0.8F, -0.2F, 0F, -0.8F, -0.2F, 0F, -0.8F,
            -0.2F, 0.2F, -0.8F, -0.2F, 0.1F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0.1F, 0F,
            -0.3F); // Box 24
    gunModel[21].setRotationPoint(3F, -4F, -0.5F);

    gunModel[22]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, -0.4F, -0.4F, -0.6F, -0.4F, -0.4F, -0.6F, -0.47F,
            -0.35F, 0F, -0.47F, -0.35F, 0F, -0.5F, -0.3F, -0.6F, -0.5F, -0.3F, -0.6F, -0.5F, -0.4F,
            0F, -0.5F, -0.4F); // Box 25
    gunModel[22].setRotationPoint(3F, -3.57F, -0.1F);

    gunModel[23]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, -0.4F, -0.4F, -0.6F, -0.4F, -0.4F, -0.6F, -0.47F,
            -0.35F, 0F, -0.47F, -0.35F, 0F, -0.5F, -0.3F, -0.6F, -0.5F, -0.3F, -0.6F, -0.5F, -0.4F,
            0F, -0.5F, -0.4F); // Box 27
    gunModel[23].setRotationPoint(4.5F, -3.57F, -0.1F);

    ammoModel = new ModelRendererTurbo[1];
    ammoModel[0] = new ModelRendererTurbo(this, 153, 1, textureX, textureY); // Box 15

    ammoModel[0]
        .addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F, -0.1F, 0F, -0.1F, -0.1F, 0F, -0.1F, -0.1F, 0F, -0.1F,
            -0.1F, 0F, -0.1F, -0.1F, 0.5F, -0.1F, -0.1F, 0.5F, -0.1F, -0.1F, 0.5F, -0.1F, -0.1F,
            0.5F, -0.1F); // Box 15
    ammoModel[0].setRotationPoint(2.95F, -1.7F, -0.5F);
    ammoModel[0].rotateAngleZ = 0.15707963F;

    scopeAttachPoint = new Vector3f(4F / 16F, 3.2F / 16F, 0F / 16F);

    animationType = EnumAnimationType.BOTTOM_CLIP;

    translateAll(0F, 0F, 0F);

    flipAll();
  }
}