//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2019 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: 4xScope
// Model Creator: 
// Created on: 23.11.2019 - 12:49:49
// Last changed on: 23.11.2019 - 12:49:49

package com.flansmod.client.model.mcb; //Path where the model is located

import com.flansmod.client.model.ModelAttachment;
import com.flansmod.client.tmt.ModelRendererTurbo;

public class Model4xScope extends ModelAttachment //Same as Filename
{

  int textureX = 32;
  int textureY = 32;

  public Model4xScope() //Same as Filename
  {
    attachmentModel = new ModelRendererTurbo[9];
    attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import Stand
    attachmentModel[1] = new ModelRendererTurbo(this, 9, 1, textureX, textureY); // Import Stand
    attachmentModel[2] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Import LensFront
    attachmentModel[3] = new ModelRendererTurbo(this, 17, 9, textureX, textureY); // Import Dial1
    attachmentModel[4] = new ModelRendererTurbo(this, 25, 9, textureX, textureY); // Import Dial2
    attachmentModel[5] = new ModelRendererTurbo(this, 17, 9, textureX, textureY); // Box 0
    attachmentModel[6] = new ModelRendererTurbo(this, 17, 9, textureX, textureY); // Box 1
    attachmentModel[7] = new ModelRendererTurbo(this, 17, 9, textureX, textureY); // Box 2
    attachmentModel[8] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 4

    attachmentModel[0]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F,
            -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F,
            0F, -0.65F, -0.65F, 0F); // Import Stand
    attachmentModel[0].setRotationPoint(4.25F, -2.4F, -0.5F);

    attachmentModel[1]
        .addShapeBox(0F, -1.5F, 0F, 3, 1, 1, 0F, 0.5F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0.5F,
            0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F); // Import Stand
    attachmentModel[1].setRotationPoint(3F, 0.7F, -0.5F);

    attachmentModel[2]
        .addShapeBox(0F, 0F, 0F, 4, 2, 2, 0F, 0.5F, -0.33F, -0.33F, 0F, -0.33F, -0.33F, 0F, -0.33F,
            -0.33F, 0.5F, -0.33F, -0.33F, 0.5F, -0.33F, -0.33F, 0F, -0.33F, -0.33F, 0F, -0.33F,
            -0.33F, 0.5F, -0.33F, -0.33F); // Import LensFront
    attachmentModel[2].setRotationPoint(2F, -2.2F, -1F);

    attachmentModel[3]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F,
            0F, -0.25F, 0F, -0.75F, 0.16F, 0F, -0.75F, 0.28F, 0F, -0.75F, 0.28F, 0F, -0.75F,
            0.16F); // Import Dial1
    attachmentModel[3].setRotationPoint(5.5F, -2.1F, -0.5F);
    attachmentModel[3].rotateAngleZ = 0.13962634F;

    attachmentModel[4].addBox(0F, 0F, 0F, 1, 1, 1, 0F); // Import Dial2
    attachmentModel[4].setRotationPoint(2.8F, -1.7F, 0F);

    attachmentModel[5]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F,
            0F, -0.25F, 0F, -0.75F, 0.16F, 0F, -0.75F, 0.2775F, 0F, -0.75F, 0.2775F, 0F, -0.75F,
            0.16F); // Box 0
    attachmentModel[5].setRotationPoint(5.5F, -1.7F, 0.9F);
    attachmentModel[5].rotateAngleX = -1.60570291F;
    attachmentModel[5].rotateAngleY = 0.12217305F;

    attachmentModel[6]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F,
            0F, -0.25F, 0F, -0.75F, 0.16F, 0F, -0.75F, 0.2775F, 0F, -0.75F, 0.28F, 0F, -0.75F,
            0.16F); // Box 1
    attachmentModel[6].setRotationPoint(5.5F, -0.7F, -0.9F);
    attachmentModel[6].rotateAngleX = -4.71238898F;
    attachmentModel[6].rotateAngleY = -0.12217305F;

    attachmentModel[7]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F,
            0F, -0.25F, 0F, -0.75F, 0.16F, 0F, -0.75F, 0.23F, 0F, -0.75F, 0.2775F, 0F, -0.75F,
            0.16F); // Box 2
    attachmentModel[7].setRotationPoint(5.5F, -0.3F, 0.5F);
    attachmentModel[7].rotateAngleX = 3.14159265F;
    attachmentModel[7].rotateAngleZ = -0.12217305F;

    attachmentModel[8]
        .addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F,
            -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F,
            0F, -0.65F, -0.65F, 0F); // Box 4
    attachmentModel[8].setRotationPoint(1.75F, -2.4F, -0.5F);

    renderOffset = 0F;

    translateAll(-5F, 0F, 0F);

    flipAll();
  }
}