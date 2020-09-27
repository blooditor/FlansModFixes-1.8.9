//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2020 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: RedDot
// Model Creator: 
// Created on: 01.09.2020 - 14:41:55
// Last changed on: 01.09.2020 - 14:41:55

package com.flansmod.client.model.mw; //Path where the model is located

import com.flansmod.client.model.ModelAttachment;
import com.flansmod.client.tmt.ModelRendererTurbo;

public class ModelIronSight7 extends ModelAttachment //Same as Filename
{

	int textureX = 128;
	int textureY = 64;

	public ModelIronSight7() //Same as Filename
	{
		attachmentModel = new ModelRendererTurbo[15];
		attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[1] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[2] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Box 17
		attachmentModel[3] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Box 18
		attachmentModel[4] = new ModelRendererTurbo(this, 105, 1, textureX, textureY); // Box 19
		attachmentModel[5] = new ModelRendererTurbo(this, 1, 17, textureX, textureY); // Box 20
		attachmentModel[6] = new ModelRendererTurbo(this, 17, 17, textureX, textureY); // Box 22
		attachmentModel[7] = new ModelRendererTurbo(this, 33, 17, textureX, textureY); // Box 23
		attachmentModel[8] = new ModelRendererTurbo(this, 49, 17, textureX, textureY); // Box 24
		attachmentModel[9] = new ModelRendererTurbo(this, 57, 17, textureX, textureY); // Box 14
		attachmentModel[10] = new ModelRendererTurbo(this, 81, 17, textureX, textureY); // Box 10
		attachmentModel[11] = new ModelRendererTurbo(this, 97, 17, textureX, textureY); // Box 11
		attachmentModel[12] = new ModelRendererTurbo(this, 113, 17, textureX, textureY); // Box 12
		attachmentModel[13] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // Box 13
		attachmentModel[14] = new ModelRendererTurbo(this, 9, 25, textureX, textureY); // Box 14

		attachmentModel[0].addShapeBox(-16F, -1F, -5F, 32, 1, 10, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Import
		attachmentModel[0].setRotationPoint(0F, 0F, 0F);
		attachmentModel[0].rotateAngleY = -3.14159265F;
		attachmentModel[0].rotateAngleZ = 3.14159265F;

		attachmentModel[1].addShapeBox(-10F, 6.5F, -0.5F, 3, 3, 1, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F); // Import
		attachmentModel[1].setRotationPoint(0F, 0F, 0F);
		attachmentModel[1].rotateAngleY = -3.14159265F;
		attachmentModel[1].rotateAngleZ = 3.14159265F;

		attachmentModel[2].addShapeBox(-10F, -7F, 0F, 3, 1, 9, 0F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F, 0F, 0.5F, -0.5F); // Box 17
		attachmentModel[2].setRotationPoint(0F, 0F, -4.5F);

		attachmentModel[3].addShapeBox(-10F, -11F, 0F, 3, 4, 1, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F); // Box 18
		attachmentModel[3].setRotationPoint(0F, 0F, -3F);

		attachmentModel[4].addShapeBox(-10F, -11F, 0F, 3, 4, 1, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F); // Box 19
		attachmentModel[4].setRotationPoint(0F, 0F, 2F);

		attachmentModel[5].addShapeBox(-10F, -16.4F, -2.5F, 3, 1, 5, 0F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F); // Box 20
		attachmentModel[5].setRotationPoint(0F, 0F, 0F);

		attachmentModel[6].addShapeBox(-10F, 7.5F, -4.5F, 3, 2, 1, 0F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F); // Box 22
		attachmentModel[6].setRotationPoint(0F, 0F, 0F);
		attachmentModel[6].rotateAngleX = 0.52359878F;
		attachmentModel[6].rotateAngleY = -3.14159265F;
		attachmentModel[6].rotateAngleZ = 3.14159265F;

		attachmentModel[7].addShapeBox(-10F, 7.5F, 3.5F, 3, 2, 1, 0F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F); // Box 23
		attachmentModel[7].setRotationPoint(0F, 0F, 0F);
		attachmentModel[7].rotateAngleX = -0.52359878F;
		attachmentModel[7].rotateAngleY = -3.14159265F;
		attachmentModel[7].rotateAngleZ = 3.14159265F;

		attachmentModel[8].addShapeBox(-10F, -9.2F, -1F, 3, 1, 2, 0F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F); // Box 24
		attachmentModel[8].setRotationPoint(0F, 0F, 0F);

		attachmentModel[9].addBox(-10F, -7F, -3F, 3, 7, 6, 0F); // Box 14
		attachmentModel[9].setRotationPoint(0F, 0F, 0F);

		attachmentModel[10].addShapeBox(-10F, -16F, -3.2F, 3, 9, 1, 0F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F); // Box 10
		attachmentModel[10].setRotationPoint(0F, 0F, 0F);

		attachmentModel[11].addShapeBox(-10F, -16F, 2.2F, 3, 9, 1, 0F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F, -0.3F, 0.4F, 0.1F); // Box 11
		attachmentModel[11].setRotationPoint(0F, 0F, 0F);

		attachmentModel[12].addShapeBox(-10F, 7.2F, -10.5F, 3, 1, 2, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F); // Box 12
		attachmentModel[12].setRotationPoint(0F, 0F, 0F);
		attachmentModel[12].rotateAngleX = -2.0943951F;

		attachmentModel[13].addShapeBox(-10F, 7.2F, 8.5F, 3, 1, 2, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F); // Box 13
		attachmentModel[13].setRotationPoint(0F, 0F, 0F);
		attachmentModel[13].rotateAngleX = 2.0943951F;

		attachmentModel[14].addShapeBox(-10F, -13.2F, -2.5F, 3, 1, 5, 0F, -0.3F, 0F, -0.7F, -0.3F, 0F, -0.7F, -0.3F, 0F, -0.7F, -0.3F, 0F, -0.7F, -0.3F, 0F, -0.7F, -0.3F, 0F, -0.7F, -0.3F, 0F, -0.7F, -0.3F, 0F, -0.7F); // Box 14
		attachmentModel[14].setRotationPoint(0F, 0F, 0F);

		translateAll(0, 0.1f/16f, 0);

		flipAll();
	}
}