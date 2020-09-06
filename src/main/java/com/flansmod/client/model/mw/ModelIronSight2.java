//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2020 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: RedDot
// Model Creator: 
// Created on: 05.09.2020 - 11:19:27
// Last changed on: 05.09.2020 - 11:19:27

package com.flansmod.client.model.mw; //Path where the model is located

import com.flansmod.client.model.ModelAttachment;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.client.tmt.Coord2D;
import com.flansmod.client.tmt.Shape2D;

public class ModelIronSight2 extends ModelAttachment  //Same as Filename
{
	int textureX = 128;
	int textureY = 64;

	public ModelIronSight2() //Same as Filename
	{
		attachmentModel = new ModelRendererTurbo[12];
		attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[1] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import
		attachmentModel[2] = new ModelRendererTurbo(this, 1, 17, textureX, textureY); // Box 0
		attachmentModel[3] = new ModelRendererTurbo(this, 65, 17, textureX, textureY); // Box 1
		attachmentModel[4] = new ModelRendererTurbo(this, 1, 33, textureX, textureY); // Box 4
		attachmentModel[5] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Box 5
		attachmentModel[6] = new ModelRendererTurbo(this, 73, 1, textureX, textureY); // Box 6
		attachmentModel[7] = new ModelRendererTurbo(this, 65, 25, textureX, textureY); // Box 7
		attachmentModel[8] = new ModelRendererTurbo(this, 105, 1, textureX, textureY); // Box 8
		attachmentModel[9] = new ModelRendererTurbo(this, 121, 1, textureX, textureY); // Box 9
		attachmentModel[10] = new ModelRendererTurbo(this, 121, 9, textureX, textureY); // Box 10
		attachmentModel[11] = new ModelRendererTurbo(this, 89, 25, textureX, textureY); // Box 11

		attachmentModel[0].addBox(-11F, -1F, -5F, 27, 5, 10, 0F); // Import
		attachmentModel[0].setRotationPoint(0F, 0F, 0F);
		attachmentModel[0].rotateAngleY = -3.14159265F;
		attachmentModel[0].rotateAngleZ = 3.14159265F;

		attachmentModel[1].addShapeBox(13F, 2.5F, -0.5F, 3, 7, 1, 0F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0.25F, 0F, 0F, 0.25F); // Import
		attachmentModel[1].setRotationPoint(0F, 0F, 0F);
		attachmentModel[1].rotateAngleY = -3.14159265F;
		attachmentModel[1].rotateAngleZ = 3.14159265F;

		attachmentModel[2].addShapeBox(-11F, 3.75F, -4F, 27, 1, 8, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		attachmentModel[2].setRotationPoint(0F, 0F, 0F);
		attachmentModel[2].rotateAngleY = -3.14159265F;
		attachmentModel[2].rotateAngleZ = 3.14159265F;

		attachmentModel[3].addShapeBox(-11F, 3.87F, 4F, 27, 1, 1, 0F, 0F, -0.12F, 0F, 0F, -0.12F, 0F, 0F, -0.12F, 0F, 0F, -0.12F, 0F, 0F, -0.12F, 0F, 0F, -0.12F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F); // Box 1
		attachmentModel[3].setRotationPoint(0F, 0F, 0F);
		attachmentModel[3].rotateAngleY = -3.14159265F;
		attachmentModel[3].rotateAngleZ = 3.14159265F;

		attachmentModel[4].addShapeBox(-11F, 3.87F, -5F, 27, 1, 1, 0F, 0F, -0.12F, 0F, 0F, -0.12F, 0F, 0F, -0.12F, 0F, 0F, -0.12F, 0F, 0F, -0.8F, 0F, 0F, -0.8F, 0F, 0F, -0.12F, 0F, 0F, -0.12F, 0F); // Box 4
		attachmentModel[4].setRotationPoint(0F, 0F, 0F);
		attachmentModel[4].rotateAngleY = -3.14159265F;
		attachmentModel[4].rotateAngleZ = 3.14159265F;

		attachmentModel[5].addShapeBox(-15.5F, -1F, -5F, 5, 5, 10, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -4.8F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, -4.8F, 0F); // Box 5
		attachmentModel[5].setRotationPoint(0F, 0F, 0F);
		attachmentModel[5].rotateAngleY = -3.14159265F;
		attachmentModel[5].rotateAngleZ = 3.14159265F;

		attachmentModel[6].addShapeBox(-1F, 2.5F, -3.5F, 4, 7, 1, 0F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F); // Box 6
		attachmentModel[6].setRotationPoint(0F, 0F, 0F);
		attachmentModel[6].rotateAngleY = -3.14159265F;
		attachmentModel[6].rotateAngleZ = 3.14159265F;

		attachmentModel[7].addShapeBox(-2F, 4.75F, -4F, 6, 1, 8, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 7
		attachmentModel[7].setRotationPoint(0F, 0F, 0F);
		attachmentModel[7].rotateAngleY = -3.14159265F;
		attachmentModel[7].rotateAngleZ = 3.14159265F;

		attachmentModel[8].addShapeBox(-1F, 2.5F, 2.45F, 4, 7, 1, 0F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F); // Box 8
		attachmentModel[8].setRotationPoint(0F, 0F, 0F);
		attachmentModel[8].rotateAngleY = -3.14159265F;
		attachmentModel[8].rotateAngleZ = 3.14159265F;

		attachmentModel[9].addShapeBox(1F, 2.5F, 1.45F, 2, 6, 1, 0F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F); // Box 9
		attachmentModel[9].setRotationPoint(0F, 0F, 0F);
		attachmentModel[9].rotateAngleY = -3.14159265F;
		attachmentModel[9].rotateAngleZ = 3.14159265F;

		attachmentModel[10].addShapeBox(1F, 2.5F, -2.5F, 2, 6, 1, 0F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F, 0F, 0.1F, 0.15F); // Box 10
		attachmentModel[10].setRotationPoint(0F, 0F, 0F);
		attachmentModel[10].rotateAngleY = -3.14159265F;
		attachmentModel[10].rotateAngleZ = 3.14159265F;

		attachmentModel[11].addShapeBox(-1F, 5.75F, -3F, 4, 1, 6, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 11
		attachmentModel[11].setRotationPoint(0F, 0F, 0F);
		attachmentModel[11].rotateAngleY = -3.14159265F;
		attachmentModel[11].rotateAngleZ = 3.14159265F;



		flipAll();
	}
}