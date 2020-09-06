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

	public ModelIronSight1() //Same as Filename
	{
		int textureX = 128;
		int textureY = 32;
		attachmentModel = new ModelRendererTurbo[12];
		attachmentModel[0] = new ModelRendererTurbo(this, 9, 1, textureX, textureY); // Box 1
		attachmentModel[1] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Box 2
		attachmentModel[2] = new ModelRendererTurbo(this, 41, 1, textureX, textureY); // Box 3
		attachmentModel[3] = new ModelRendererTurbo(this, 57, 1, textureX, textureY); // Box 4
		attachmentModel[4] = new ModelRendererTurbo(this, 65, 1, textureX, textureY); // Box 5
		attachmentModel[5] = new ModelRendererTurbo(this, 73, 1, textureX, textureY); // Box 8
		attachmentModel[6] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Box 9
		attachmentModel[7] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Box 10
		attachmentModel[8] = new ModelRendererTurbo(this, 97, 1, textureX, textureY); // Box 11
		attachmentModel[9] = new ModelRendererTurbo(this, 105, 1, textureX, textureY); // Box 12
		attachmentModel[10] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Box 13
		attachmentModel[11] = new ModelRendererTurbo(this, 121, 1, textureX, textureY); // Box 14

		attachmentModel[0].addShapeBox(0F, 0F, 0F, 4, 1, 3, 0F, 0F, -0.2F, -2.75F, 0F, -0.7F, -2.75F, 0F, -0.7F, 0F, 0F, -0.2F, 0F, 0.4F, 0F, -2.75F, 0F, 0F, -2.75F, 0F, 0F, 0F, 0.4F, 0F, 0F); // Box 1
		attachmentModel[0].setRotationPoint(0.2F, -2F, -2.5F);

		attachmentModel[1].addShapeBox(0F, 0F, 0F, 4, 1, 3, 0F, 0F, -0.2F, -2.75F, 0F, -0.7F, -2.75F, 0F, -0.7F, 0F, 0F, -0.2F, 0F, 0.4F, 0F, -2.75F, 0F, 0F, -2.75F, 0F, 0F, 0F, 0.4F, 0F, 0F); // Box 2
		attachmentModel[1].setRotationPoint(0.2F, -2F, -3.25F);

		attachmentModel[2].addShapeBox(0F, 0F, 0F, 4, 1, 3, 0F, 0F, -0.45F, -1.25F, 0F, -0.45F, -1.25F, 0F, -0.45F, -1.25F, 0F, -0.45F, -1.25F, 0F, -0.45F, -1.25F, 0F, -0.45F, -1.25F, 0F, -0.45F, -1.25F, 0F, -0.45F, -1.25F); // Box 3
		attachmentModel[2].setRotationPoint(0.2F, -1.45F, -1.5F);

		attachmentModel[3].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0F, 0F, -0.6F, 0F, 0F, -0.6F, 0F, 0F, 0F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F); // Box 4
		attachmentModel[3].setRotationPoint(3.8F, -1F, -0.5F);

		attachmentModel[4].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F); // Box 5
		attachmentModel[4].setRotationPoint(0F, -1.6F, -0.5F);

		attachmentModel[5].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F); // Box 8
		attachmentModel[5].setRotationPoint(0F, -0.9F, -0.2F);
		attachmentModel[5].rotateAngleX = -4.71238898F;

		attachmentModel[6].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F); // Box 9
		attachmentModel[6].setRotationPoint(0F, -0.9F, -0.8F);
		attachmentModel[6].rotateAngleX = -4.71238898F;

		attachmentModel[7].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F, -0.2F, -0.4F, -0.3F); // Box 10
		attachmentModel[7].setRotationPoint(0F, -2.2F, -0.5F);

		attachmentModel[8].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, -0.2F, -0.3F, -0.45F, -0.2F, -0.3F, -0.45F, -0.2F, -0.3F, -0.45F, -0.2F, -0.3F, -0.45F, -0.2F, -0.3F, -0.45F, -0.2F, -0.3F, -0.45F, -0.2F, -0.3F, -0.45F, -0.2F, -0.3F, -0.45F); // Box 11
		attachmentModel[8].setRotationPoint(0F, -1.65F, -0.5F);

		attachmentModel[9].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F); // Box 12
		attachmentModel[9].setRotationPoint(0F, -2.75F, -0.28F);
		attachmentModel[9].rotateAngleX = -0.52359878F;

		attachmentModel[10].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F, -0.2F, -0.95F, -0.97F); // Box 13
		attachmentModel[10].setRotationPoint(0F, -1.75F, -1.46F);
		attachmentModel[10].rotateAngleX = 0.52359878F;

		attachmentModel[11].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F, -0.2F, -0.95F, -0.98F, -0.2F, -0.95F, -0.98F, -0.2F, -0.95F, -0.98F, -0.2F, -0.95F, -0.98F, -0.2F, -0.95F, -0.98F, -0.2F, -0.95F, -0.98F, -0.2F, -0.95F, -0.98F, -0.2F, -0.95F, -0.98F); // Box 14
		attachmentModel[11].setRotationPoint(0F, -2.35F, -1F);



		flipAll();
	}
}