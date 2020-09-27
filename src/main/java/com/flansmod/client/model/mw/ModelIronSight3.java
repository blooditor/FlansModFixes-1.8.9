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

public class ModelIronSight3 extends ModelAttachment //Same as Filename
{

	int textureX = 128;
	int textureY = 64;

	public ModelIronSight3() //Same as Filename
	{
		attachmentModel = new ModelRendererTurbo[16];
		attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[1] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[2] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Box 17
		attachmentModel[3] = new ModelRendererTurbo(this, 105, 1, textureX, textureY); // Box 18
		attachmentModel[4] = new ModelRendererTurbo(this, 89, 9, textureX, textureY); // Box 19
		attachmentModel[5] = new ModelRendererTurbo(this, 105, 9, textureX, textureY); // Box 20
		attachmentModel[6] = new ModelRendererTurbo(this, 1, 17, textureX, textureY); // Box 14
		attachmentModel[7] = new ModelRendererTurbo(this, 17, 17, textureX, textureY); // Box 10
		attachmentModel[8] = new ModelRendererTurbo(this, 33, 17, textureX, textureY); // Box 11
		attachmentModel[9] = new ModelRendererTurbo(this, 49, 17, textureX, textureY); // Box 12
		attachmentModel[10] = new ModelRendererTurbo(this, 65, 17, textureX, textureY); // Box 13
		attachmentModel[11] = new ModelRendererTurbo(this, 73, 17, textureX, textureY); // Box 14
		attachmentModel[12] = new ModelRendererTurbo(this, 97, 17, textureX, textureY); // Box 17
		attachmentModel[13] = new ModelRendererTurbo(this, 113, 17, textureX, textureY); // Box 18
		attachmentModel[14] = new ModelRendererTurbo(this, 9, 25, textureX, textureY); // Box 19
		attachmentModel[15] = new ModelRendererTurbo(this, 33, 25, textureX, textureY); // Box 20

		attachmentModel[0].addShapeBox(-16F, -1F, -5F, 32, 1, 10, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Import
		attachmentModel[0].setRotationPoint(0F, 0F, 0F);
		attachmentModel[0].rotateAngleY = -3.14159265F;
		attachmentModel[0].rotateAngleZ = 3.14159265F;

		attachmentModel[1].addShapeBox(-10F, 6.5F, -0.5F, 3, 3, 1, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Import
		attachmentModel[1].setRotationPoint(0F, 0F, 0F);
		attachmentModel[1].rotateAngleY = -3.14159265F;
		attachmentModel[1].rotateAngleZ = 3.14159265F;

		attachmentModel[2].addShapeBox(-10F, -6.6F, 0F, 3, 1, 5, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 17
		attachmentModel[2].setRotationPoint(0F, 0F, -2.5F);

		attachmentModel[3].addShapeBox(-10F, -11F, 0F, 3, 4, 1, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F); // Box 18
		attachmentModel[3].setRotationPoint(0F, 0F, -3F);

		attachmentModel[4].addShapeBox(-10F, -11F, 0F, 3, 4, 1, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F); // Box 19
		attachmentModel[4].setRotationPoint(0F, 0F, 2F);

		attachmentModel[5].addShapeBox(-10F, -12.4F, 0F, 3, 1, 5, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 20
		attachmentModel[5].setRotationPoint(0F, 0F, -2.5F);

		attachmentModel[6].addBox(-10F, -6.05F, -1F, 3, 6, 2, 0F); // Box 14
		attachmentModel[6].setRotationPoint(0F, 0F, 0F);

		attachmentModel[7].addShapeBox(-10F, -12.4F, -3F, 3, 1, 1, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 10
		attachmentModel[7].setRotationPoint(0F, 0F, 0F);

		attachmentModel[8].addShapeBox(-10F, -12.4F, 2F, 3, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 11
		attachmentModel[8].setRotationPoint(0F, 0F, 0F);

		attachmentModel[9].addShapeBox(-10F, -6.6F, -3F, 3, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 12
		attachmentModel[9].setRotationPoint(0F, 0F, 0F);

		attachmentModel[10].addShapeBox(-10F, -6.6F, 2F, 3, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F); // Box 13
		attachmentModel[10].setRotationPoint(0F, 0F, 0F);

		attachmentModel[11].addShapeBox(-10.5F, -6F, -4F, 4, 1, 8, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F); // Box 14
		attachmentModel[11].setRotationPoint(0F, 0F, 0F);

		attachmentModel[12].addShapeBox(-10.5F, -10F, 1.8F, 4, 4, 2, 0F, 0F, -4F, -0.1F, 0F, -4F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 17
		attachmentModel[12].setRotationPoint(0F, 0F, 0F);

		attachmentModel[13].addShapeBox(-10.5F, -10F, -3.8F, 4, 4, 2, 0F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, -4F, -0.1F, 0F, -4F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F); // Box 18
		attachmentModel[13].setRotationPoint(0F, 0F, 0F);

		attachmentModel[14].addShapeBox(-10.5F, -5F, -3F, 4, 1, 6, 0F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F); // Box 19
		attachmentModel[14].setRotationPoint(0F, 0F, 0F);

		attachmentModel[15].addShapeBox(-10.5F, -4F, -2.5F, 4, 4, 5, 0F, 1F, 0F, 1F, 1F, 0F, 1F, 1F, 0F, 1F, 1F, 0F, 1F, 1F, 0F, 1F, 1F, 0F, 1F, 1F, 0F, 1F, 1F, 0F, 1F); // Box 20
		attachmentModel[15].setRotationPoint(0F, 0F, 0F);


		translateAll(0, 0.1f/16f, 0);

		flipAll();
	}
}