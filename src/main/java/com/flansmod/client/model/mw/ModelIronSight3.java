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
	int textureY = 32;

	public ModelIronSight3() //Same as Filename
	{
		attachmentModel = new ModelRendererTurbo[13];
		attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import Import
		attachmentModel[1] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import Import
		attachmentModel[2] = new ModelRendererTurbo(this, 33, 1, textureX, textureY); // Box 48
		attachmentModel[3] = new ModelRendererTurbo(this, 41, 1, textureX, textureY); // Box 49
		attachmentModel[4] = new ModelRendererTurbo(this, 49, 1, textureX, textureY); // Box 50
		attachmentModel[5] = new ModelRendererTurbo(this, 57, 1, textureX, textureY); // Box 51
		attachmentModel[6] = new ModelRendererTurbo(this, 65, 1, textureX, textureY); // Box 51
		attachmentModel[7] = new ModelRendererTurbo(this, 73, 1, textureX, textureY); // Box 52
		attachmentModel[8] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Box 53
		attachmentModel[9] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Box 54
		attachmentModel[10] = new ModelRendererTurbo(this, 97, 1, textureX, textureY); // Box 55
		attachmentModel[11] = new ModelRendererTurbo(this, 105, 1, textureX, textureY); // Box 56
		attachmentModel[12] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Box 57

		attachmentModel[0].addBox(-8.9F, 2F, -5F, 10, 1, 10, 0F); // Import Import
		attachmentModel[0].setRotationPoint(0F, 0F, 0F);
		attachmentModel[0].rotateAngleY = -3.14159265F;
		attachmentModel[0].rotateAngleZ = 3.14159265F;

		attachmentModel[1].addShapeBox(-4F, 6.5F, -0.5F, 1, 3, 1, 0F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.4F); // Import Import
		attachmentModel[1].setRotationPoint(0F, 0F, 0F);
		attachmentModel[1].rotateAngleY = -3.14159265F;
		attachmentModel[1].rotateAngleZ = 3.14159265F;

		attachmentModel[2].addShapeBox(-4F, -8.75F, -0.5F, 1, 6, 1, 0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F); // Box 48
		attachmentModel[2].setRotationPoint(0F, 0F, 0F);

		attachmentModel[3].addShapeBox(-4F, -9.4F, 1.1F, 1, 1, 1, 0F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F); // Box 49
		attachmentModel[3].setRotationPoint(0F, 0F, 0F);
		attachmentModel[3].rotateAngleX = 0.2268928F;

		attachmentModel[4].addShapeBox(-4F, -9.4F, -2.1F, 1, 1, 1, 0F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F); // Box 50
		attachmentModel[4].setRotationPoint(0F, 0F, 0F);
		attachmentModel[4].rotateAngleX = -0.2268928F;

		attachmentModel[5].addShapeBox(-4F, -9F, -0.5F, 1, 1, 1, 0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F); // Box 51
		attachmentModel[5].setRotationPoint(0F, 0F, 0F);

		attachmentModel[6].addShapeBox(-4F, 5.15F, 6.4F, 1, 1, 1, 0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F); // Box 51
		attachmentModel[6].setRotationPoint(0F, 0F, 0F);
		attachmentModel[6].rotateAngleX = 2.35619449F;

		attachmentModel[7].addShapeBox(-4F, 5.15F, -7.4F, 1, 1, 1, 0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F); // Box 52
		attachmentModel[7].setRotationPoint(0F, 0F, 0F);
		attachmentModel[7].rotateAngleX = -2.35619449F;

		attachmentModel[8].addShapeBox(-4F, -10.25F, -1.75F, 1, 1, 1, 0F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F); // Box 53
		attachmentModel[8].setRotationPoint(0F, 0F, 0F);

		attachmentModel[9].addShapeBox(-4F, -10.25F, 0.75F, 1, 1, 1, 0F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F, 0F, 0.1F, -0.3F); // Box 54
		attachmentModel[9].setRotationPoint(0F, 0F, 0F);

		attachmentModel[10].addShapeBox(-4F, -11.5F, -0.5F, 1, 1, 1, 0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F); // Box 55
		attachmentModel[10].setRotationPoint(0F, 0F, 0F);

		attachmentModel[11].addShapeBox(-4F, 7.65F, -7.4F, 1, 1, 1, 0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F); // Box 56
		attachmentModel[11].setRotationPoint(0F, 0F, 0F);
		attachmentModel[11].rotateAngleX = -2.35619449F;

		attachmentModel[12].addShapeBox(-4F, 7.65F, 6.4F, 1, 1, 1, 0F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F, 0F, -0.3F, 0.1F); // Box 57
		attachmentModel[12].setRotationPoint(0F, 0F, 0F);
		attachmentModel[12].rotateAngleX = 2.35619449F;



		flipAll();
	}
}