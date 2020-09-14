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

public class ModelIronSight4 extends ModelAttachment //Same as Filename
{

	int textureX = 128;
	int textureY = 64;

	public ModelIronSight4() //Same as Filename
	{
		attachmentModel = new ModelRendererTurbo[9];
		attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[1] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[2] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Box 8
		attachmentModel[3] = new ModelRendererTurbo(this, 1, 17, textureX, textureY); // Box 11
		attachmentModel[4] = new ModelRendererTurbo(this, 33, 17, textureX, textureY); // Box 12
		attachmentModel[5] = new ModelRendererTurbo(this, 73, 1, textureX, textureY); // Box 13
		attachmentModel[6] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Box 15
		attachmentModel[7] = new ModelRendererTurbo(this, 113, 9, textureX, textureY); // Box 16
		attachmentModel[8] = new ModelRendererTurbo(this, 57, 17, textureX, textureY); // Box 17

		attachmentModel[0].addBox(-14F, -1F, -5F, 30, 3, 10, 0F); // Import
		attachmentModel[0].setRotationPoint(0F, 0F, 0F);
		attachmentModel[0].rotateAngleY = -3.14159265F;
		attachmentModel[0].rotateAngleZ = 3.14159265F;

		attachmentModel[1].addShapeBox(-1F, 2.5F, -0.5F, 2, 7, 1, 0F, 0F, 0.3F, -0.1F, 0F, 0.3F, -0.1F, 0F, 0.3F, -0.1F, 0F, 0.3F, -0.1F, 0F, 0.3F, -0.1F, 0F, 0.3F, -0.1F, 0F, 0.3F, -0.1F, 0F, 0.3F, -0.1F); // Import
		attachmentModel[1].setRotationPoint(0F, 0F, 0F);
		attachmentModel[1].rotateAngleY = -3.14159265F;
		attachmentModel[1].rotateAngleZ = 3.14159265F;

		attachmentModel[2].addShapeBox(-7F, 1.5F, 2.2F, 8, 8, 3, 0F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F); // Box 8
		attachmentModel[2].setRotationPoint(0F, 0F, 0F);
		attachmentModel[2].rotateAngleY = -3.14159265F;
		attachmentModel[2].rotateAngleZ = 3.14159265F;

		attachmentModel[3].addShapeBox(-7F, 0.75F, -3F, 8, 6, 6, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 11
		attachmentModel[3].setRotationPoint(0F, 0F, 0F);
		attachmentModel[3].rotateAngleY = -3.14159265F;
		attachmentModel[3].rotateAngleZ = 3.14159265F;

		attachmentModel[4].addShapeBox(-7F, 1.5F, -5.2F, 8, 8, 3, 0F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F, 0F, 0.5F, -0.2F); // Box 12
		attachmentModel[4].setRotationPoint(0F, 0F, 0F);
		attachmentModel[4].rotateAngleY = -3.14159265F;
		attachmentModel[4].rotateAngleZ = 3.14159265F;

		attachmentModel[5].addShapeBox(-3F, -11F, -2.2F, 4, 5, 1, 0F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F); // Box 13
		attachmentModel[5].setRotationPoint(0F, 0F, 0F);

		attachmentModel[6].addShapeBox(-3F, -11F, 1.2F, 4, 5, 1, 0F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F, 0F, -0.7F, -0.15F); // Box 15
		attachmentModel[6].setRotationPoint(0F, 0F, 0F);

		attachmentModel[7].addShapeBox(-3F, -10F, 5.77F, 4, 3, 1, 0F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F); // Box 16
		attachmentModel[7].setRotationPoint(0F, 0F, 0F);
		attachmentModel[7].rotateAngleX = 0.48869219F;

		attachmentModel[8].addShapeBox(-3F, -10F, -6.77F, 4, 3, 1, 0F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, -1F, -0.15F); // Box 17
		attachmentModel[8].setRotationPoint(0F, 0F, 0F);
		attachmentModel[8].rotateAngleX = -0.48869219F;



		flipAll();
	}
}