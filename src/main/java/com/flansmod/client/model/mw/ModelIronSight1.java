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
import com.flansmod.client.tmt.Coord2D;
import com.flansmod.client.tmt.Shape2D;

public class ModelIronSight1 extends ModelAttachment //Same as Filename
{

	int textureX = 128;
	int textureY = 64;

	public ModelIronSight1() //Same as Filename
	{
		attachmentModel = new ModelRendererTurbo[14];
		attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 0
		attachmentModel[1] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 1
		attachmentModel[2] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Box 2
		attachmentModel[3] = new ModelRendererTurbo(this, 1, 17, textureX, textureY); // Box 3
		attachmentModel[4] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // Box 5
		attachmentModel[5] = new ModelRendererTurbo(this, 65, 17, textureX, textureY); // Box 6
		attachmentModel[6] = new ModelRendererTurbo(this, 1, 33, textureX, textureY); // Box 7
		attachmentModel[7] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Box 8
		attachmentModel[8] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Box 9
		attachmentModel[9] = new ModelRendererTurbo(this, 121, 1, textureX, textureY); // Box 10
		attachmentModel[10] = new ModelRendererTurbo(this, 97, 25, textureX, textureY); // Box 11
		attachmentModel[11] = new ModelRendererTurbo(this, 65, 17, textureX, textureY); // Box 12
		attachmentModel[12] = new ModelRendererTurbo(this, 113, 25, textureX, textureY); // Box 13
		attachmentModel[13] = new ModelRendererTurbo(this, 97, 33, textureX, textureY); // Box 14

		attachmentModel[0].addShapeBox(-16F, -1F, -5F, 32, 1, 10, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		attachmentModel[0].setRotationPoint(0F, 0F, 0F);
		attachmentModel[0].rotateAngleY = -3.14159265F;
		attachmentModel[0].rotateAngleZ = 3.14159265F;

		attachmentModel[1].addShapeBox(-10F, 6.5F, -0.5F, 3, 3, 1, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F); // Box 1
		attachmentModel[1].setRotationPoint(0F, 0F, 0F);
		attachmentModel[1].rotateAngleY = -3.14159265F;
		attachmentModel[1].rotateAngleZ = 3.14159265F;

		attachmentModel[2].addShapeBox(-14F, -6F, -5F, 6, 6, 10, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 2F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, 2F, 0F, 0F); // Box 2
		attachmentModel[2].setRotationPoint(0F, 0F, 0F);

		attachmentModel[3].addShapeBox(-13.3F, -12F, -4.25F, 28, 6, 1, 0F, -1F, -0.2F, 0.75F, 0.3F, -2F, 0.75F, 0.3F, -2F, 0F, -1F, -0.2F, 0F, 0.4F, 0F, 0.75F, 0.3F, 0F, 0.75F, 0.3F, 0F, 0F, 0.4F, 0F, 0F); // Box 3
		attachmentModel[3].setRotationPoint(0F, 0F, 0F);

		attachmentModel[4].addShapeBox(-13.3F, -12F, 4F, 28, 6, 1, 0F, -1F, -0.2F, 0.75F, 0.3F, -2F, 0.75F, 0.3F, -2F, 0F, -1F, -0.2F, 0F, 0.4F, 0F, 0.75F, 0.3F, 0F, 0.75F, 0.3F, 0F, 0F, 0.4F, 0F, 0F); // Box 5
		attachmentModel[4].setRotationPoint(0F, 0F, 0F);

		attachmentModel[5].addShapeBox(11.3F, -6F, -5F, 5, 6, 10, 0F, 0.3F, 0F, 0F, -1.3F, 0F, 0F, -1.3F, 0F, 0F, 0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F); // Box 6
		attachmentModel[5].setRotationPoint(0F, 0F, 0F);

		attachmentModel[6].addShapeBox(-8.4F, 5.45F, -5F, 20, 1, 10, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F); // Box 7
		attachmentModel[6].setRotationPoint(0F, 0F, 0F);
		attachmentModel[6].rotateAngleY = -3.14159265F;
		attachmentModel[6].rotateAngleZ = 3.14159265F;

		attachmentModel[7].addShapeBox(-10F, -7F, -2.5F, 3, 1, 5, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 8
		attachmentModel[7].setRotationPoint(0F, 0F, 0F);

		attachmentModel[8].addShapeBox(-10F, -11F, -3F, 1, 4, 1, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F); // Box 9
		attachmentModel[8].setRotationPoint(0F, 0F, 0F);

		attachmentModel[9].addShapeBox(-10F, -11F, 2F, 1, 4, 1, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F, 0F, 0.4F, 0F); // Box 10
		attachmentModel[9].setRotationPoint(0F, 0F, 0F);

		attachmentModel[10].addShapeBox(-10F, -12.3F, -2.5F, 3, 1, 5, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 11
		attachmentModel[10].setRotationPoint(0F, 0F, 0F);

		attachmentModel[11].addShapeBox(-10F, 7.5F, -4.5F, 3, 2, 1, 0F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F); // Box 12
		attachmentModel[11].setRotationPoint(0F, 0F, 0F);
		attachmentModel[11].rotateAngleX = 0.52359878F;
		attachmentModel[11].rotateAngleY = -3.14159265F;
		attachmentModel[11].rotateAngleZ = 3.14159265F;

		attachmentModel[12].addShapeBox(-10F, 7.5F, 3.5F, 3, 2, 1, 0F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F); // Box 13
		attachmentModel[12].setRotationPoint(0F, 0F, 0F);
		attachmentModel[12].rotateAngleX = -0.52359878F;
		attachmentModel[12].rotateAngleY = -3.14159265F;
		attachmentModel[12].rotateAngleZ = 3.14159265F;

		attachmentModel[13].addShapeBox(-10F, -9.2F, -1F, 3, 1, 2, 0F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F, 0F, -0.3F, -0.45F); // Box 14
		attachmentModel[13].setRotationPoint(0F, 0F, 0F);

		translateAll(0, -0.1f/16f, 0);

		flipAll();
	}
}