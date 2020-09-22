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

public class ModelIronSight5 extends ModelAttachment //Same as Filename
{

	int textureX = 128;
	int textureY = 64;

	public ModelIronSight5() //Same as Filename
	{
		attachmentModel = new ModelRendererTurbo[18];
		attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[1] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[2] = new ModelRendererTurbo(this, 57, 1, textureX, textureY); // Box 5
		attachmentModel[3] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Box 12
		attachmentModel[4] = new ModelRendererTurbo(this, 49, 1, textureX, textureY); // Box 14
		attachmentModel[5] = new ModelRendererTurbo(this, 97, 1, textureX, textureY); // Box 15
		attachmentModel[6] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Box 16
		attachmentModel[7] = new ModelRendererTurbo(this, 105, 9, textureX, textureY); // Box 17
		attachmentModel[8] = new ModelRendererTurbo(this, 113, 9, textureX, textureY); // Box 18
		attachmentModel[9] = new ModelRendererTurbo(this, 89, 17, textureX, textureY); // Box 19
		attachmentModel[10] = new ModelRendererTurbo(this, 105, 17, textureX, textureY); // Box 20
		attachmentModel[11] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // Box 21
		attachmentModel[12] = new ModelRendererTurbo(this, 17, 25, textureX, textureY); // Box 22
		attachmentModel[13] = new ModelRendererTurbo(this, 33, 25, textureX, textureY); // Box 23
		attachmentModel[14] = new ModelRendererTurbo(this, 49, 25, textureX, textureY); // Box 24
		attachmentModel[15] = new ModelRendererTurbo(this, 65, 25, textureX, textureY); // Box 25
		attachmentModel[16] = new ModelRendererTurbo(this, 81, 25, textureX, textureY); // Box 26
		attachmentModel[17] = new ModelRendererTurbo(this, 1, 41, textureX, textureY); // Box 27

		attachmentModel[0].addBox(-11F, -1F, -5F, 17, 6, 10, 0F); // Import
		attachmentModel[0].setRotationPoint(0F, 0F, 0F);
		attachmentModel[0].rotateAngleY = -3.14159265F;
		attachmentModel[0].rotateAngleZ = 3.14159265F;

		attachmentModel[1].addShapeBox(1F, 2.5F, -0.5F, 2, 7, 1, 0F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.1F); // Import
		attachmentModel[1].setRotationPoint(1F, 0F, 0F);
		attachmentModel[1].rotateAngleY = -3.14159265F;
		attachmentModel[1].rotateAngleZ = 3.14159265F;

		attachmentModel[2].addShapeBox(-15.5F, -1F, -5F, 5, 6, 10, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -5.8F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -5.8F, 0F); // Box 5
		attachmentModel[2].setRotationPoint(0F, 0F, 0F);
		attachmentModel[2].rotateAngleY = -3.14159265F;
		attachmentModel[2].rotateAngleZ = 3.14159265F;

		attachmentModel[3].addShapeBox(-9F, -7F, 0F, 3, 3, 6, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 12
		attachmentModel[3].setRotationPoint(10F, 0F, -3F);

		attachmentModel[4].addShapeBox(1F, -9.7F, 1.8F, 3, 4, 1, 0F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F); // Box 14
		attachmentModel[4].setRotationPoint(0F, 0F, 0F);

		attachmentModel[5].addShapeBox(1F, -9.7F, -2.8F, 3, 4, 1, 0F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F); // Box 15
		attachmentModel[5].setRotationPoint(0F, 0F, 0F);

		attachmentModel[6].addShapeBox(1F, 9F, -2.4F, 3, 1, 1, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 16
		attachmentModel[6].setRotationPoint(0F, 0F, 0F);
		attachmentModel[6].rotateAngleY = -3.14159265F;
		attachmentModel[6].rotateAngleZ = 3.14159265F;

		attachmentModel[7].addShapeBox(1F, 9F, 1.4F, 3, 1, 1, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Box 17
		attachmentModel[7].setRotationPoint(0F, 0F, 0F);
		attachmentModel[7].rotateAngleY = -3.14159265F;
		attachmentModel[7].rotateAngleZ = 3.14159265F;

		attachmentModel[8].addBox(1F, -7.75F, -2F, 3, 1, 4, 0F); // Box 18
		attachmentModel[8].setRotationPoint(0F, 0F, 0F);

		attachmentModel[9].addBox(1F, -6.75F, -5.4F, 3, 1, 2, 0F); // Box 19
		attachmentModel[9].setRotationPoint(0F, 0F, 0F);
		attachmentModel[9].rotateAngleX = -0.48869219F;

		attachmentModel[10].addBox(1F, -6.75F, 3.4F, 3, 1, 2, 0F); // Box 20
		attachmentModel[10].setRotationPoint(0F, 0F, 0F);
		attachmentModel[10].rotateAngleX = 0.48869219F;

		attachmentModel[11].addShapeBox(-4F, -10F, 3.5F, 6, 7, 1, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F); // Box 21
		attachmentModel[11].setRotationPoint(0F, 0F, 0F);

		attachmentModel[12].addShapeBox(-4F, -10F, -4.5F, 6, 7, 1, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F); // Box 22
		attachmentModel[12].setRotationPoint(0F, 0F, 0F);

		attachmentModel[13].addShapeBox(-7.6F, 2.8F, -4.5F, 4, 7, 1, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -5.8F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -5.8F, 0F); // Box 23
		attachmentModel[13].setRotationPoint(0F, 0F, 0F);
		attachmentModel[13].rotateAngleY = -3.14159265F;
		attachmentModel[13].rotateAngleZ = 3.14159265F;

		attachmentModel[14].addShapeBox(-7.6F, 2.8F, 3.5F, 4, 7, 1, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -5.8F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -5.8F, 0F); // Box 24
		attachmentModel[14].setRotationPoint(0F, 0F, 0F);
		attachmentModel[14].rotateAngleY = -3.14159265F;
		attachmentModel[14].rotateAngleZ = 3.14159265F;

		attachmentModel[15].addShapeBox(1.6F, 2.8F, 3.5F, 4, 7, 1, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -5.8F, 0F, -0.4F, -5.8F, 0F, -0.4F, 0F, 0F); // Box 25
		attachmentModel[15].setRotationPoint(0F, 0F, 0F);
		attachmentModel[15].rotateAngleY = -3.14159265F;
		attachmentModel[15].rotateAngleZ = 3.14159265F;

		attachmentModel[16].addShapeBox(1.6F, 2.8F, -4.5F, 4, 7, 1, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -5.8F, 0F, -0.4F, -5.8F, 0F, -0.4F, 0F, 0F); // Box 26
		attachmentModel[16].setRotationPoint(0F, 0F, 0F);
		attachmentModel[16].rotateAngleY = -3.14159265F;
		attachmentModel[16].rotateAngleZ = 3.14159265F;

		attachmentModel[17].addBox(6F, -1F, -5F, 10, 1, 10, 0F); // Box 27
		attachmentModel[17].setRotationPoint(0F, 0F, 0F);
		attachmentModel[17].rotateAngleY = -3.14159265F;
		attachmentModel[17].rotateAngleZ = 3.14159265F;



		flipAll();
	}
}