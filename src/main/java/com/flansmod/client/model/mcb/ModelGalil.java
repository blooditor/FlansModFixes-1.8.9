//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2020 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: 
// Model Creator: 
// Created on: 20.08.2020 - 11:04:37
// Last changed on: 20.08.2020 - 11:04:37

package com.flansmod.client.model.mcb; //Path where the model is located

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.common.vector.Vector3f;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.client.tmt.Coord2D;
import com.flansmod.client.tmt.Shape2D;

public class ModelGalil extends ModelGun //Same as Filename
{
	int textureX = 512;
	int textureY = 32;

	public ModelGalil() //Same as Filename
	{
		gunModel = new ModelRendererTurbo[53];
		gunModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import Box0
		gunModel[1] = new ModelRendererTurbo(this, 17, 1, textureX, textureY); // Import Box2
		gunModel[2] = new ModelRendererTurbo(this, 49, 1, textureX, textureY); // Import Box3
		gunModel[3] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Import Box5
		gunModel[4] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Import Box6
		gunModel[5] = new ModelRendererTurbo(this, 145, 1, textureX, textureY); // Import Box7
		gunModel[6] = new ModelRendererTurbo(this, 153, 1, textureX, textureY); // Import Box8
		gunModel[7] = new ModelRendererTurbo(this, 169, 1, textureX, textureY); // Import Box9
		gunModel[8] = new ModelRendererTurbo(this, 185, 1, textureX, textureY); // Import Box10
		gunModel[9] = new ModelRendererTurbo(this, 201, 1, textureX, textureY); // Import Box11
		gunModel[10] = new ModelRendererTurbo(this, 217, 1, textureX, textureY); // Import Box12
		gunModel[11] = new ModelRendererTurbo(this, 233, 1, textureX, textureY); // Import Box13
		gunModel[12] = new ModelRendererTurbo(this, 257, 1, textureX, textureY); // Import Box14
		gunModel[13] = new ModelRendererTurbo(this, 273, 1, textureX, textureY); // Import Box15
		gunModel[14] = new ModelRendererTurbo(this, 289, 1, textureX, textureY); // Import Box16
		gunModel[15] = new ModelRendererTurbo(this, 313, 1, textureX, textureY); // Import Box17
		gunModel[16] = new ModelRendererTurbo(this, 337, 1, textureX, textureY); // Import Box18
		gunModel[17] = new ModelRendererTurbo(this, 361, 1, textureX, textureY); // Import Box19
		gunModel[18] = new ModelRendererTurbo(this, 385, 1, textureX, textureY); // Import Box20
		gunModel[19] = new ModelRendererTurbo(this, 393, 1, textureX, textureY); // Import Box21
		gunModel[20] = new ModelRendererTurbo(this, 409, 1, textureX, textureY); // Import Box22
		gunModel[21] = new ModelRendererTurbo(this, 417, 1, textureX, textureY); // Import Box23
		gunModel[22] = new ModelRendererTurbo(this, 425, 1, textureX, textureY); // Import Box24
		gunModel[23] = new ModelRendererTurbo(this, 433, 1, textureX, textureY); // Import Box25
		gunModel[24] = new ModelRendererTurbo(this, 465, 1, textureX, textureY); // Import Box26
		gunModel[25] = new ModelRendererTurbo(this, 481, 1, textureX, textureY); // Import Box27
		gunModel[26] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Import Box28
		gunModel[27] = new ModelRendererTurbo(this, 33, 9, textureX, textureY); // Import Box35
		gunModel[28] = new ModelRendererTurbo(this, 41, 9, textureX, textureY); // Import Box37
		gunModel[29] = new ModelRendererTurbo(this, 65, 9, textureX, textureY); // Import Box38
		gunModel[30] = new ModelRendererTurbo(this, 97, 9, textureX, textureY); // Import Box39
		gunModel[31] = new ModelRendererTurbo(this, 129, 9, textureX, textureY); // Import Box40
		gunModel[32] = new ModelRendererTurbo(this, 145, 9, textureX, textureY); // Import Box41
		gunModel[33] = new ModelRendererTurbo(this, 161, 9, textureX, textureY); // Import Box42
		gunModel[34] = new ModelRendererTurbo(this, 177, 9, textureX, textureY); // Import Box43
		gunModel[35] = new ModelRendererTurbo(this, 249, 1, textureX, textureY); // Import Box53
		gunModel[36] = new ModelRendererTurbo(this, 193, 9, textureX, textureY); // Import Box54
		gunModel[37] = new ModelRendererTurbo(this, 201, 9, textureX, textureY); // Import Box55
		gunModel[38] = new ModelRendererTurbo(this, 209, 9, textureX, textureY); // Import Box56
		gunModel[39] = new ModelRendererTurbo(this, 225, 9, textureX, textureY); // Import Box57
		gunModel[40] = new ModelRendererTurbo(this, 233, 9, textureX, textureY); // Import Box58
		gunModel[41] = new ModelRendererTurbo(this, 241, 9, textureX, textureY); // Import Box59
		gunModel[42] = new ModelRendererTurbo(this, 249, 9, textureX, textureY); // Import Box60
		gunModel[43] = new ModelRendererTurbo(this, 257, 9, textureX, textureY); // Import Box61
		gunModel[44] = new ModelRendererTurbo(this, 265, 9, textureX, textureY); // Import Box62
		gunModel[45] = new ModelRendererTurbo(this, 273, 9, textureX, textureY); // Import Box73
		gunModel[46] = new ModelRendererTurbo(this, 289, 9, textureX, textureY); // Import Box74
		gunModel[47] = new ModelRendererTurbo(this, 305, 9, textureX, textureY); // Import Box75
		gunModel[48] = new ModelRendererTurbo(this, 321, 9, textureX, textureY); // Import Box76
		gunModel[49] = new ModelRendererTurbo(this, 337, 9, textureX, textureY); // Import Box77
		gunModel[50] = new ModelRendererTurbo(this, 353, 9, textureX, textureY); // Import Box78
		gunModel[51] = new ModelRendererTurbo(this, 369, 9, textureX, textureY); // Import Box79
		gunModel[52] = new ModelRendererTurbo(this, 385, 9, textureX, textureY); // Import Box80

		gunModel[0].addShapeBox(0F, 0F, 0F, 3, 2, 2, 0F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F, 0F, -0.35F, -0.35F); // Import Box0
		gunModel[0].setRotationPoint(30.5F, -4.8F, -1F);

		gunModel[1].addShapeBox(0F, 0F, 0F, 13, 2, 2, 0F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F); // Import Box2
		gunModel[1].setRotationPoint(18F, -4.8F, -1F);

		gunModel[2].addShapeBox(0F, 0F, 0F, 12, 2, 2, 0F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F, 0F, -0.8F, -0.5F); // Import Box3
		gunModel[2].setRotationPoint(18F, -2.8F, -1F);
		gunModel[2].rotateAngleX = 1.55334303F;

		gunModel[3].addShapeBox(0F, 0F, 0F, 13, 1, 2, 0F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.45F, -0.8F, 0F, -0.45F, -0.8F, 0F, -0.45F, -0.8F, 0F, -0.45F, -0.8F); // Import Box5
		gunModel[3].setRotationPoint(18F, -3.8F, -1F);

		gunModel[4].addShapeBox(0F, 0F, 0F, 13, 1, 2, 0F, 0F, -0.45F, -0.8F, 0F, -0.45F, -0.8F, 0F, -0.45F, -0.8F, 0F, -0.45F, -0.8F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F, 0F, -0.2F, -0.5F); // Import Box6
		gunModel[4].setRotationPoint(18F, -4.8F, -1F);

		gunModel[5].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F, 0F, 0.1F, 0.1F); // Import Box7
		gunModel[5].setRotationPoint(17.9F, -4.3F, -0.5F);

		gunModel[6].addShapeBox(0F, 0F, 0F, 5, 2, 2, 0F, 0F, 0.44F, -0.12F, 0F, 0.44F, -0.12F, 0F, 0.44F, -0.12F, 0F, 0.44F, -0.12F, 0F, 0.44F, -0.12F, 0F, 0.44F, -0.12F, 0F, 0.44F, -0.12F, 0F, 0.44F, -0.12F); // Import Box8
		gunModel[6].setRotationPoint(12.9F, -5.1F, -1F);

		gunModel[7].addShapeBox(0F, 0F, 0F, 5, 2, 1, 0F, 0F, -0.35F, -0.7F, 0F, -0.35F, -0.7F, 0F, -0.35F, 0F, 0F, -0.35F, 0F, 0F, -0.35F, -1F, 0F, -0.35F, -1F, 0F, -0.35F, 0F, 0F, -0.35F, 0F); // Import Box9
		gunModel[7].setRotationPoint(12.9F, -4.32F, -1.88F);

		gunModel[8].addShapeBox(0F, 0F, 0F, 5, 2, 1, 0F, 0F, -0.35F, -0.7F, 0F, -0.35F, -0.7F, 0F, -0.35F, 0F, 0F, -0.35F, 0F, 0F, -0.35F, -1F, 0F, -0.35F, -1F, 0F, -0.35F, 0F, 0F, -0.35F, 0F); // Import Box10
		gunModel[8].setRotationPoint(17.9F, -3.88F, -1.88F);
		gunModel[8].rotateAngleZ = 3.14159265F;

		gunModel[9].addShapeBox(0F, 0F, 0F, 5, 2, 1, 0F, 0F, -0.35F, 0F, 0F, -0.35F, 0F, 0F, -0.35F, -0.7F, 0F, -0.35F, -0.7F, 0F, -0.35F, 0F, 0F, -0.35F, 0F, 0F, -0.35F, -1F, 0F, -0.35F, -1F); // Import Box11
		gunModel[9].setRotationPoint(17.9F, -3.88F, 0.88F);
		gunModel[9].rotateAngleZ = 3.14159265F;

		gunModel[10].addShapeBox(0F, 0F, 0F, 5, 2, 1, 0F, 0F, -0.35F, 0F, 0F, -0.35F, 0F, 0F, -0.35F, -0.7F, 0F, -0.35F, -0.7F, 0F, -0.35F, 0F, 0F, -0.35F, 0F, 0F, -0.35F, -1F, 0F, -0.35F, -1F); // Import Box12
		gunModel[10].setRotationPoint(12.9F, -4.32F, 0.88F);

		gunModel[11].addShapeBox(0F, 0F, 0F, 5, 1, 3, 0F, 0F, -0.37F, -0.32F, 0F, -0.37F, -0.32F, 0F, -0.37F, -0.32F, 0F, -0.37F, -0.32F, 0F, -0.37F, -0.32F, 0F, -0.37F, -0.32F, 0F, -0.37F, -0.32F, 0F, -0.37F, -0.32F); // Import Box13
		gunModel[11].setRotationPoint(12.9F, -4.6F, -1.5F);

		gunModel[12].addShapeBox(0F, 0F, 0F, 5, 1, 2, 0F, 0F, -0.45F, -0.4F, 0F, -0.45F, -0.4F, 0F, -0.45F, -0.4F, 0F, -0.45F, -0.4F, 0F, -0.2F, -0.12F, 0F, -0.2F, -0.12F, 0F, -0.2F, -0.12F, 0F, -0.2F, -0.12F); // Import Box14
		gunModel[12].setRotationPoint(12.9F, -6.34F, -1F);

		gunModel[13].addShapeBox(0F, 0F, 0F, 5, 1, 2, 0F, 0F, -0.2F, -0.12F, 0F, -0.2F, -0.12F, 0F, -0.2F, -0.12F, 0F, -0.2F, -0.12F, 0F, -0.25F, -0.4F, 0F, -0.25F, -0.4F, 0F, -0.25F, -0.4F, 0F, -0.25F, -0.4F); // Import Box15
		gunModel[13].setRotationPoint(12.9F, -2.86F, -1F);

		gunModel[14].addShapeBox(0F, 0F, 0F, 6, 1, 2, 0F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F); // Import Box16
		gunModel[14].setRotationPoint(17.9F, -4.3F, -0.5F);
		gunModel[14].rotateAngleX = 1.57079633F;

		gunModel[15].addShapeBox(0F, 0F, 0F, 6, 1, 2, 0F, 0F, -0.58F, -0.8F, 0F, -0.58F, -0.8F, 0F, -0.58F, -0.8F, 0F, -0.58F, -0.7F, 0F, -0.2F, -0.65F, 0F, -0.2F, -0.65F, 0F, -0.2F, -0.65F, 0F, -0.2F, -0.65F); // Import Box17
		gunModel[15].setRotationPoint(17.9F, -6.25F, -1F);

		gunModel[16].addShapeBox(0F, 0F, 0F, 6, 1, 2, 0F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F, 0F, -0.65F, -0.65F); // Import Box18
		gunModel[16].setRotationPoint(17.9F, -5.8F, -1F);

		gunModel[17].addShapeBox(0F, 0F, 0F, 6, 1, 2, 0F, 0F, -0.2F, -0.65F, 0F, -0.2F, -0.65F, 0F, -0.2F, -0.65F, 0F, -0.2F, -0.65F, 0F, -0.58F, -0.8F, 0F, -0.58F, -0.8F, 0F, -0.58F, -0.8F, 0F, -0.58F, -0.7F); // Import Box19
		gunModel[17].setRotationPoint(17.9F, -5.35F, -1F);

		gunModel[18].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F); // Import Box20
		gunModel[18].setRotationPoint(18.5F, -5.65F, -0.5F);

		gunModel[19].addShapeBox(0F, 0F, 0F, 3, 3, 1, 0F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F, 0F, -0.65F, -0.7F); // Import Box21
		gunModel[19].setRotationPoint(18.5F, -5.3F, -0.5F);

		gunModel[20].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F); // Import Box22
		gunModel[20].setRotationPoint(18F, -2.6F, -0.5F);
		gunModel[20].rotateAngleZ = 1.57079633F;

		gunModel[21].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F); // Import Box23
		gunModel[21].setRotationPoint(19F, -2.6F, -0.5F);
		gunModel[21].rotateAngleZ = 1.57079633F;

		gunModel[22].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F, 0F, -0.75F, -0.7F); // Import Box24
		gunModel[22].setRotationPoint(20F, -2.6F, -0.5F);
		gunModel[22].rotateAngleZ = 1.57079633F;

		gunModel[23].addShapeBox(0F, 0F, 0F, 12, 3, 3, 0F, 0F, -0.2F, -0.15F, 0F, -0.2F, -0.15F, 0F, -0.2F, -0.15F, 0F, -0.2F, -0.15F, 0F, -0.2F, -0.15F, 0F, -0.2F, -0.15F, 0F, -0.2F, -0.15F, 0F, -0.2F, -0.15F); // Import Box25
		gunModel[23].setRotationPoint(0.9F, -5.68F, -1.5F);

		gunModel[24].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Import Box26
		gunModel[24].setRotationPoint(2.5F, -4.7F, -1.9F);

		gunModel[25].addShapeBox(0F, 0F, 0F, 12, 1, 2, 0F, 0F, -0.45F, -0.4F, 0F, -0.45F, -0.4F, 0F, -0.45F, -0.4F, 0F, -0.45F, -0.4F, 0F, -0.2F, 0.36F, 0F, -0.2F, 0.36F, 0F, -0.2F, 0.36F, 0F, -0.2F, 0.36F); // Import Box27
		gunModel[25].setRotationPoint(0.9F, -6.28F, -1F);

		gunModel[26].addShapeBox(0F, 0F, 0F, 12, 1, 2, 0F, 0F, -0.2F, 0.36F, 0F, -0.2F, 0.36F, 0F, -0.2F, 0.36F, 0F, -0.2F, 0.36F, 0F, -0.45F, -0.4F, 0F, -0.45F, -0.4F, 0F, -0.45F, -0.4F, 0F, -0.45F, -0.4F); // Import Box28
		gunModel[26].setRotationPoint(0.9F, -3.08F, -1F);

		gunModel[27].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F, 0F, 0.2F, 0.3F, 0F, 0.2F, 0.3F, 0F, 0.2F, 0.3F, 0F, 0.2F, 0.3F, 0F, 0.2F, 0.3F, 0F, 0.2F, 0.3F, 0F, 0.2F, 0.3F, 0F, 0.2F, 0.3F); // Import Box35
		gunModel[27].setRotationPoint(-0.1F, -4.8F, -0.5F);

		gunModel[28].addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F, 0F, -0.15F, 0.3F, 0F, -0.15F, 0.3F, 0F, -0.15F, 0.3F, 0F, -0.15F, 0.3F, 0F, -0.15F, 0.3F, 0F, -0.15F, 0.3F, 0F, -0.15F, 0.3F, 0F, -0.15F, 0.3F); // Import Box37
		gunModel[28].setRotationPoint(-10F, -4.72F, -0.5F);

		gunModel[29].addShapeBox(0F, 0F, 0F, 10, 1, 2, 0F, 0F, -0.3F, -0.4F, 0F, -0.3F, -0.4F, 0F, -0.3F, -0.4F, 0F, -0.3F, -0.4F, 0F, -0.3F, -0.2F, 0F, -0.3F, -0.2F, 0F, -0.3F, -0.2F, 0F, -0.3F, -0.2F); // Import Box38
		gunModel[29].setRotationPoint(-10F, -5.27F, -1F);

		gunModel[30].addShapeBox(0F, 0F, 0F, 10, 1, 2, 0F, 0F, -0.3F, -0.2F, 0F, -0.3F, -0.2F, 0F, -0.3F, -0.2F, 0F, -0.3F, -0.2F, 0F, -0.3F, -0.4F, 0F, -0.3F, -0.4F, 0F, -0.3F, -0.4F, 0F, -0.3F, -0.4F); // Import Box39
		gunModel[30].setRotationPoint(-10F, -4.17F, -1F);

		gunModel[31].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, -0.02F, 0F, 0.2F, -0.02F, 0F, 0.2F, -0.02F, 0F, 0.2F, -0.02F, 0F, 0.2F, -0.02F, 0F, 0.2F, -0.02F, 0F, 0.2F, -0.02F, 0F, 0.2F, -0.02F, 0F, 0.2F); // Import Box40
		gunModel[31].setRotationPoint(-9F, -3.67F, -0.5F);
		gunModel[31].rotateAngleZ = -1.57079633F;

		gunModel[32].addShapeBox(0F, 0F, 0F, 5, 1, 1, 0F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F); // Import Box41
		gunModel[32].setRotationPoint(-3.6F, -3.29F, -0.5F);

		gunModel[33].addShapeBox(0F, 0F, 0F, 6, 1, 1, 0F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F, 0F, -0.3F, 0.2F); // Import Box42
		gunModel[33].setRotationPoint(-9.3F, -1.35F, -0.5F);
		gunModel[33].rotateAngleZ = 0.33161256F;

		gunModel[34].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F); // Import Box43
		gunModel[34].setRotationPoint(6.05F, -2.85F, -0.5F);

		gunModel[35].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F, 0F, -0.2F, 0.2F); // Import Box53
		gunModel[35].setRotationPoint(-3.6F, -3.77F, -0.5F);

		gunModel[36].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, -0.2F, 0.25F, 0F, -0.2F, 0.25F, 0F, -0.2F, 0.25F, 0F, -0.2F, 0.25F, 0F, -0.2F, 0.25F, 0F, -0.2F, 0.25F, 0F, -0.2F, 0.25F, 0F, -0.2F, 0.25F); // Import Box54
		gunModel[36].setRotationPoint(-0.6F, -4.62F, -0.5F);
		gunModel[36].rotateAngleZ = 1.57079633F;

		gunModel[37].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F); // Import Box55
		gunModel[37].setRotationPoint(1.5F, -2.3F, -0.5F);

		gunModel[38].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, 0.2F, 0.3F, 0.2F, 0.2F, 0.3F, 0.2F, 0.2F, 0.3F, 0.2F, 0.2F, 0.3F, 0.2F, 0.2F, 0.3F, 0.2F, 0.2F, 0.3F, 0.2F, 0.2F, 0.3F, 0.2F, 0.2F, 0.3F, 0.2F); // Import Box56
		gunModel[38].setRotationPoint(0.9F, 0.7F, -0.5F);
		gunModel[38].rotateAngleZ = 1.25663706F;

		gunModel[39].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F); // Import Box57
		gunModel[39].setRotationPoint(3.2F, -1.7F, -0.5F);

		gunModel[40].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F); // Import Box58
		gunModel[40].setRotationPoint(4.3F, -0.95F, -0.5F);
		gunModel[40].rotateAngleZ = 1.57079633F;

		gunModel[41].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F, 0F, 0.2F, 0.2F); // Import Box59
		gunModel[41].setRotationPoint(4.8F, -2.3F, -0.5F);

		gunModel[42].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F); // Import Box60
		gunModel[42].setRotationPoint(4.85F, 2.22044604925031E-16F, -0.5F);
		gunModel[42].rotateAngleZ = 1.57079633F;

		gunModel[43].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F); // Import Box61
		gunModel[43].setRotationPoint(4.2F, -0.3F, -0.5F);
		gunModel[43].rotateAngleZ = 1.22173048F;

		gunModel[44].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.3F, -0.4F, 0.05F, -0.3F, -0.4F, 0.05F, -0.3F, -0.4F, 0.05F, -0.3F, -0.4F, 0.05F, -0.3F, -0.4F, 0.05F, -0.3F, -0.4F, 0.05F, -0.3F, -0.4F, 0.05F, -0.3F, -0.4F, 0.05F); // Import Box62
		gunModel[44].setRotationPoint(3.4F, -1.3F, -0.5F);
		gunModel[44].rotateAngleZ = 1.57079633F;

		gunModel[45].addShapeBox(0F, 0F, 0F, 3, 1, 2, 0F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.3F, -0.4F, 0.2F, -0.4F, -0.3F, 0.2F, -0.4F, -0.3F, 0.2F, -0.4F, -0.3F, 0.2F, -0.4F, -0.3F); // Import Box73
		gunModel[45].setRotationPoint(0.04F, 0.42F, -1F);
		gunModel[45].rotateAngleZ = 1.25663706F;

		gunModel[46].addShapeBox(0F, 0F, 0F, 3, 1, 2, 0F, 0.2F, -0.3F, -0.3F, 0.2F, -0.3F, -0.3F, 0.2F, -0.3F, -0.3F, 0.2F, -0.3F, -0.3F, 0.2F, -0.4F, -0.4F, 0.2F, -0.4F, -0.4F, 0.2F, -0.4F, -0.4F, 0.2F, -0.4F, -0.4F); // Import Box74
		gunModel[46].setRotationPoint(1.85F, 1F, -1F);
		gunModel[46].rotateAngleZ = 1.25663706F;

		gunModel[47].addShapeBox(0F, 0F, 0F, 3, 2, 1, 0F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, -0.8F, 0.2F, -0.7F, -0.8F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, -1F, 0.2F, -0.7F, -1F); // Import Box75
		gunModel[47].setRotationPoint(0.9F, 0.7F, 0.7F);
		gunModel[47].rotateAngleZ = 1.25663706F;

		gunModel[48].addShapeBox(0F, 0F, 0F, 3, 2, 1, 0F, 0.2F, -0.7F, -0.8F, 0.2F, -0.7F, -0.8F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, -1F, 0.2F, -0.7F, -1F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, 0F); // Import Box76
		gunModel[48].setRotationPoint(0.9F, 0.71F, -1.7F);
		gunModel[48].rotateAngleZ = 1.25663706F;

		gunModel[49].addShapeBox(0F, 0F, 0F, 3, 2, 1, 0F, 0.2F, -0.7F, -1F, 0.2F, -0.7F, -1F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, -0.8F, 0.2F, -0.7F, -0.8F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, 0F); // Import Box77
		gunModel[49].setRotationPoint(-0.05F, 0.4F, -1.7F);
		gunModel[49].rotateAngleZ = 1.25663706F;

		gunModel[50].addShapeBox(0F, 0F, 0F, 3, 2, 1, 0F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, -1F, 0.2F, -0.7F, -1F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, 0F, 0.2F, -0.7F, -0.8F, 0.2F, -0.7F, -0.8F); // Import Box78
		gunModel[50].setRotationPoint(-0.05F, 0.4F, 0.7F);
		gunModel[50].rotateAngleZ = 1.25663706F;

		gunModel[51].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F); // Import Box79
		gunModel[51].setRotationPoint(0.9F, 0.7F, -1.3F);
		gunModel[51].rotateAngleZ = 1.25663706F;

		gunModel[52].addShapeBox(0F, 0F, 0F, 3, 1, 1, 0F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F, 0.2F, -0.7F, -0.6F); // Import Box80
		gunModel[52].setRotationPoint(0.9F, 0.7F, 0.3F);
		gunModel[52].rotateAngleZ = 1.25663706F;


		defaultScopeModel = new ModelRendererTurbo[8];
		defaultScopeModel[0] = new ModelRendererTurbo(this, 393, 9, textureX, textureY); // Import Box65
		defaultScopeModel[1] = new ModelRendererTurbo(this, 409, 9, textureX, textureY); // Import Box66
		defaultScopeModel[2] = new ModelRendererTurbo(this, 425, 9, textureX, textureY); // Import Box67
		defaultScopeModel[3] = new ModelRendererTurbo(this, 433, 9, textureX, textureY); // Import Box68
		defaultScopeModel[4] = new ModelRendererTurbo(this, 441, 9, textureX, textureY); // Import Box69
		defaultScopeModel[5] = new ModelRendererTurbo(this, 449, 9, textureX, textureY); // Import Box70
		defaultScopeModel[6] = new ModelRendererTurbo(this, 457, 9, textureX, textureY); // Import Box71
		defaultScopeModel[7] = new ModelRendererTurbo(this, 465, 9, textureX, textureY); // Import Box72

		defaultScopeModel[0].addShapeBox(0F, 0F, 0F, 3, 1, 3, 0F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F); // Import Box65
		defaultScopeModel[0].setRotationPoint(4.5F, -4.8F, -1.5F);
		defaultScopeModel[0].rotateAngleX = 1.57079633F;

		defaultScopeModel[1].addShapeBox(0F, 0F, 0F, 3, 1, 3, 0F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F); // Import Box66
		defaultScopeModel[1].setRotationPoint(4.5F, -4.8F, 0.5F);
		defaultScopeModel[1].rotateAngleX = 1.57079633F;

		defaultScopeModel[2].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F, -0.25F, -0.25F, -0.1F); // Import Box67
		defaultScopeModel[2].setRotationPoint(4.5F, -5.5F, -0.5F);
		defaultScopeModel[2].rotateAngleX = 1.57079633F;

		defaultScopeModel[3].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F); // Import Box68
		defaultScopeModel[3].setRotationPoint(4.5F, -6.05F, -0.5F);
		defaultScopeModel[3].rotateAngleX = 1.57079633F;

		defaultScopeModel[4].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F); // Import Box69
		defaultScopeModel[4].setRotationPoint(4.5F, -6.85F, -0.5F);
		defaultScopeModel[4].rotateAngleX = 1.57079633F;

		defaultScopeModel[5].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F); // Import Box70
		defaultScopeModel[5].setRotationPoint(4.5F, -6.45F, -0.9F);
		defaultScopeModel[5].rotateAngleX = 1.57079633F;

		defaultScopeModel[6].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F, -0.3F); // Import Box71
		defaultScopeModel[6].setRotationPoint(4.5F, -6.45F, -0.1F);
		defaultScopeModel[6].rotateAngleX = 1.57079633F;

		defaultScopeModel[7].addShapeBox(0F, 0F, 0F, 2, 1, 1, 0F, 0F, -0.45F, -0.35F, 0F, -0.45F, -0.35F, 0F, -0.45F, -0.35F, 0F, -0.45F, -0.35F, 0F, -0.45F, -0.35F, 0F, -0.45F, -0.35F, 0F, -0.45F, -0.35F, 0F, -0.45F, -0.35F); // Import Box72
		defaultScopeModel[7].setRotationPoint(23F, -4.9F, 0.5F);
		defaultScopeModel[7].rotateAngleY = -1.57079633F;
		defaultScopeModel[7].rotateAngleZ = 1.57079633F;


		ammoModel = new ModelRendererTurbo[2];
		ammoModel[0] = new ModelRendererTurbo(this, 473, 9, textureX, textureY); // Import Box78
		ammoModel[1] = new ModelRendererTurbo(this, 1, 17, textureX, textureY); // Box 0

		ammoModel[0].addShapeBox(0F, 0F, 0F, 3, 3, 1, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F); // Import Box78
		ammoModel[0].setRotationPoint(6.05F, -2F, -0.5F);
		ammoModel[0].rotateAngleZ = 0.03490659F;

		ammoModel[1].addShapeBox(0F, 0F, 0F, 3, 4, 1, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F); // Box 0
		ammoModel[1].setRotationPoint(6.05F, 0.65F, -0.5F);
		ammoModel[1].rotateAngleZ = 0.52359878F;


		slideModel = new ModelRendererTurbo[2];
		slideModel[0] = new ModelRendererTurbo(this, 489, 9, textureX, textureY); // Import Box63
		slideModel[1] = new ModelRendererTurbo(this, 497, 9, textureX, textureY); // Import Box64

		slideModel[0].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F); // Import Box63
		slideModel[0].setRotationPoint(9.3F, -4.9F, -2.9F);

		slideModel[1].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F, -0.1F); // Import Box64
		slideModel[1].setRotationPoint(9.3F, -4.1F, -2.9F);
		slideModel[1].rotateAngleX = 1.57079633F;


		barrelAttachPoint = new Vector3f(28.5F /16F, 3.8F /16F, 0F /16F);

		scopeAttachPoint = new Vector3f(6F /16F, 5.8F /16F, 0F /16F);

		gripAttachPoint = new Vector3f(13.1 /16F, 2.2F /16F, 0F /16F);


		gunSlideDistance = 0.4F;


		animationType = EnumAnimationType.BOTTOM_CLIP;


		translateAll(0F, 0F, 0F);


		flipAll();
	}
}