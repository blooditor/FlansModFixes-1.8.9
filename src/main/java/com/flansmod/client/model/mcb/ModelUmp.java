//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2019 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: OOW H.C.A.R.
// Model Creator: Lukas Dause   LolTiger
// Created on: 05.12.2019 - 17:04:46
// Last changed on: 05.12.2019 - 17:04:46

package com.flansmod.client.model.mcb; //Path where the model is located

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;

public class ModelUmp extends ModelGun //Same as Filename
{

  int textureX = 128;
  int textureY = 64;

  public ModelUmp() //Same as Filename
  {
    gunModel = new ModelRendererTurbo[54];
    gunModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import Box3
    gunModel[1] = new ModelRendererTurbo(this, 9, 1, textureX, textureY); // Import Box4
    gunModel[2] = new ModelRendererTurbo(this, 17, 1, textureX, textureY); // Import Box6
    gunModel[3] = new ModelRendererTurbo(this, 25, 1, textureX, textureY); // Import Box9
    gunModel[4] = new ModelRendererTurbo(this, 33, 1, textureX, textureY); // Import Box10
    gunModel[5] = new ModelRendererTurbo(this, 41, 1, textureX, textureY); // Import Box11
    gunModel[6] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Import Box12
    gunModel[7] = new ModelRendererTurbo(this, 105, 1, textureX, textureY); // Import Box13
    gunModel[8] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Import Box14
    gunModel[9] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Import Box15
    gunModel[10] = new ModelRendererTurbo(this, 121, 1, textureX, textureY); // Import Box17
    gunModel[11] = new ModelRendererTurbo(this, 25, 9, textureX, textureY); // Import Box18
    gunModel[12] = new ModelRendererTurbo(this, 33, 9, textureX, textureY); // Import Box21
    gunModel[13] = new ModelRendererTurbo(this, 41, 9, textureX, textureY); // Import Box22
    gunModel[14] = new ModelRendererTurbo(this, 81, 9, textureX, textureY); // Import Box23
    gunModel[15] = new ModelRendererTurbo(this, 97, 9, textureX, textureY); // Import Box24
    gunModel[16] = new ModelRendererTurbo(this, 105, 9, textureX, textureY); // Import Box25
    gunModel[17] = new ModelRendererTurbo(this, 1, 17, textureX, textureY); // Import Box26
    gunModel[18] = new ModelRendererTurbo(this, 17, 17, textureX, textureY); // Import Box27
    gunModel[19] = new ModelRendererTurbo(this, 121, 9, textureX, textureY); // Import Box29
    gunModel[20] = new ModelRendererTurbo(this, 33, 17, textureX, textureY); // Import Box30
    gunModel[21] = new ModelRendererTurbo(this, 41, 17, textureX, textureY); // Import Box31
    gunModel[22] = new ModelRendererTurbo(this, 49, 17, textureX, textureY); // Import Box32
    gunModel[23] = new ModelRendererTurbo(this, 57, 17, textureX, textureY); // Import Box33
    gunModel[24] = new ModelRendererTurbo(this, 65, 17, textureX, textureY); // Import Box34
    gunModel[25] = new ModelRendererTurbo(this, 73, 17, textureX, textureY); // Import Box35
    gunModel[26] = new ModelRendererTurbo(this, 81, 17, textureX, textureY); // Import Box36
    gunModel[27] = new ModelRendererTurbo(this, 89, 17, textureX, textureY); // Import Box37
    gunModel[28] = new ModelRendererTurbo(this, 97, 17, textureX, textureY); // Import Box38
    gunModel[29] = new ModelRendererTurbo(this, 105, 17, textureX, textureY); // Import Box39
    gunModel[30] = new ModelRendererTurbo(this, 113, 17, textureX, textureY); // Import Box40
    gunModel[31] = new ModelRendererTurbo(this, 121, 17, textureX, textureY); // Import Box43
    gunModel[32] = new ModelRendererTurbo(this, 33, 25, textureX, textureY); // Box 0
    gunModel[33] = new ModelRendererTurbo(this, 41, 25, textureX, textureY); // Box 1
    gunModel[34] = new ModelRendererTurbo(this, 49, 25, textureX, textureY); // Box 2
    gunModel[35] = new ModelRendererTurbo(this, 57, 25, textureX, textureY); // Box 3
    gunModel[36] = new ModelRendererTurbo(this, 65, 25, textureX, textureY); // Box 4
    gunModel[37] = new ModelRendererTurbo(this, 73, 25, textureX, textureY); // Box 5
    gunModel[38] = new ModelRendererTurbo(this, 81, 25, textureX, textureY); // Box 6
    gunModel[39] = new ModelRendererTurbo(this, 89, 25, textureX, textureY); // Box 7
    gunModel[40] = new ModelRendererTurbo(this, 97, 25, textureX, textureY); // Box 8
    gunModel[41] = new ModelRendererTurbo(this, 105, 25, textureX, textureY); // Box 9
    gunModel[42] = new ModelRendererTurbo(this, 113, 25, textureX, textureY); // Box 10
    gunModel[43] = new ModelRendererTurbo(this, 121, 25, textureX, textureY); // Box 11
    gunModel[44] = new ModelRendererTurbo(this, 1, 33, textureX, textureY); // Box 12
    gunModel[45] = new ModelRendererTurbo(this, 9, 33, textureX, textureY); // Box 13
    gunModel[46] = new ModelRendererTurbo(this, 17, 33, textureX, textureY); // Box 14
    gunModel[47] = new ModelRendererTurbo(this, 25, 33, textureX, textureY); // Box 51
    gunModel[48] = new ModelRendererTurbo(this, 33, 33, textureX, textureY); // Box 52
    gunModel[49] = new ModelRendererTurbo(this, 41, 33, textureX, textureY); // Box 53
    gunModel[50] = new ModelRendererTurbo(this, 49, 33, textureX, textureY); // Box 54
    gunModel[51] = new ModelRendererTurbo(this, 57, 33, textureX, textureY); // Box 55
    gunModel[52] = new ModelRendererTurbo(this, 65, 33, textureX, textureY); // Box 56
    gunModel[53] = new ModelRendererTurbo(this, 73, 33, textureX, textureY); // Box 57

    gunModel[0].addShapeBox(11.31F, -3.7F, -0.5F, 2, 1, 1, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Import Box3
    gunModel[0].setRotationPoint(0F, 0F, 0F);

    gunModel[1].addShapeBox(11.31F, -4.06F, -0.5F, 2, 1, 1, 0F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F); // Import Box4
    gunModel[1].setRotationPoint(0F, 0F, 0F);

    gunModel[2].addShapeBox(11.31F, -3.34F, -0.5F, 2, 1, 1, 0F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F); // Import Box6
    gunModel[2].setRotationPoint(0F, 0F, 0F);

    gunModel[3].addShapeBox(11.31F, -0.86F, 2.7F, 2, 1, 1, 0F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F); // Import Box9
    gunModel[3].setRotationPoint(0F, 0F, 0F);
    gunModel[3].rotateAngleX = 1.57079633F;

    gunModel[4].addShapeBox(11.31F, -0.14F, 2.7F, 2, 1, 1, 0F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.2F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F, 0F, -0.44F, -0.4F); // Import Box10
    gunModel[4].setRotationPoint(0F, 0F, 0F);
    gunModel[4].rotateAngleX = 1.57079633F;

    gunModel[5].addShapeBox(-2.7F, -4.8F, -1F, 14, 1, 2, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Import Box11
    gunModel[5].setRotationPoint(0F, 0F, 0F);

    gunModel[6].addShapeBox(-9F, -3.85F, -0.5F, 7, 1, 1, 0F, 0.15F, -0.1F, 0F, 0.15F, -0.1F, 0F, 0.15F, -0.1F, 0F, 0.15F, -0.1F, 0F, 0.15F, -0.1F, 0F, 0.15F, -0.1F, 0F, 0.15F, -0.1F, 0F, 0.15F, -0.1F, 0F); // Import Box12
    gunModel[6].setRotationPoint(0F, 0F, 0F);
    gunModel[6].rotateAngleZ = 0.33161256F;

    gunModel[7].addShapeBox(-9.97F, -2.69F, -0.5F, 1, 2, 1, 0F, -0.1F, 0.4F, 0F, -0.1F, 0.4F, 0F, -0.1F, 0.4F, 0F, -0.1F, 0.4F, 0F, -0.1F, 0.13F, 0F, -0.1F, -0.15F, 0F, -0.1F, -0.15F, 0F, -0.1F, 0.13F, 0F); // Import Box13
    gunModel[7].setRotationPoint(0F, 0F, 0F);

    gunModel[8].addShapeBox(-9.86F, -3.95F, -0.5F, 7, 1, 1, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F); // Import Box14
    gunModel[8].setRotationPoint(0F, 0F, 0F);

    gunModel[9].addShapeBox(-6.7F, -2.82F, -0.5F, 1, 1, 1, 0F, -0.1F, 0.28F, 0F, -0.1F, 0.28F, 0F, -0.1F, 0.28F, 0F, -0.1F, 0.28F, 0F, -0.1F, 0.13F, 0F, -0.1F, -0.15F, 0F, -0.1F, -0.15F, 0F, -0.1F, 0.13F, 0F); // Import Box15
    gunModel[9].setRotationPoint(0F, 0F, 0F);

    gunModel[10].addShapeBox(-3.7F, -3.3F, -0.5F, 1, 1, 1, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.45F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.45F, 0F); // Import Box17
    gunModel[10].setRotationPoint(0F, 0F, 0F);

    gunModel[11].addShapeBox(-1.15F, -1.8F, -0.5F, 2, 3, 1, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F); // Import Box18
    gunModel[11].setRotationPoint(0F, 0F, 0F);
    gunModel[11].rotateAngleZ = -0.45378561F;

    gunModel[12].addShapeBox(-0.65F, 0.85F, -0.5F, 1, 1, 1, 0F, 0.25F, -0.35F, 0.05F, 0.25F, -0.35F, 0.05F, 0.25F, -0.35F, 0.05F, 0.25F, -0.35F, 0.05F, 0.25F, -0.35F, 0.05F, 0.25F, -0.35F, 0.05F, 0.25F, -0.35F, 0.05F, 0.25F, -0.35F, 0.05F); // Import Box21
    gunModel[12].setRotationPoint(0F, 0F, 0F);
    gunModel[12].rotateAngleZ = -0.45378561F;

    gunModel[13].addShapeBox(-2.7F, -3.9F, -1F, 14, 1, 2, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F, 0F, 0.1F, 0F); // Import Box22
    gunModel[13].setRotationPoint(0F, 0F, 0F);

    gunModel[14].addShapeBox(6.5F, -3F, -1F, 4, 1, 2, 0F, 0.2F, -0.2F, 0F, 0.2F, -0.2F, 0F, 0.2F, -0.2F, 0F, 0.2F, -0.2F, 0F, 0.2F, -0.25F, -0.35F, 0.2F, -0.25F, -0.35F, 0.2F, -0.25F, -0.35F, 0.2F, -0.25F, -0.35F); // Import Box23
    gunModel[14].setRotationPoint(0F, 0F, 0F);

    gunModel[15].addShapeBox(10.45F, -3F, -0.5F, 1, 1, 1, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Import Box24
    gunModel[15].setRotationPoint(0F, 0F, 0F);

    gunModel[16].addShapeBox(4.1F, -2.4F, -1F, 2, 1, 2, 0F, 0.2F, 0.4F, 0F, 0.2F, 0.4F, 0F, 0.2F, 0.4F, 0F, 0.2F, 0.4F, 0F, 0.2F, 0.4F, 0F, 0.2F, 0.4F, 0F, 0.2F, 0.4F, 0F, 0.2F, 0.4F, 0F); // Import Box25
    gunModel[16].setRotationPoint(0F, 0F, 0F);

    gunModel[17].addShapeBox(-2.5F, -3F, -1F, 2, 1, 2, 0F, 0.2F, -0.2F, 0F, 0.2F, -0.2F, 0F, 0.2F, -0.2F, 0F, 0.2F, -0.2F, 0F, 0.2F, -0.25F, -0.45F, 0.2F, -0.25F, -0.45F, 0.2F, -0.25F, -0.45F, 0.2F, -0.25F, -0.45F); // Import Box26
    gunModel[17].setRotationPoint(0F, 0F, 0F);

    gunModel[18].addShapeBox(-0.2F, -2.65F, -1F, 4, 1, 2, 0F, 0.1F, 0.15F, -0.15F, 0.1F, 0.15F, -0.15F, 0.1F, 0.15F, -0.15F, 0.1F, 0.15F, -0.15F, 0.1F, 0.28F, -0.15F, 0.1F, 0.28F, -0.15F, 0.1F, 0.28F, -0.15F, 0.1F, 0.28F, -0.15F); // Import Box27
    gunModel[18].setRotationPoint(0F, 0F, 0F);

    gunModel[19].addShapeBox(2F, -0.85F, -0.5F, 1, 1, 1, 0F, 0.2F, -0.45F, 0F, 0.2F, -0.45F, 0F, 0.2F, -0.45F, 0F, 0.2F, -0.45F, 0F, 0.2F, -0.45F, 0F, 0.2F, -0.45F, 0F, 0.2F, -0.45F, 0F, 0.2F, -0.45F, 0F); // Import Box29
    gunModel[19].setRotationPoint(0F, 0F, 0F);

    gunModel[20].addShapeBox(1.5F, -2.95F, -0.5F, 2, 1, 1, 0F, -0.95F, 0F, 0F, -0.95F, 0F, 0F, -0.95F, 0F, 0F, -0.95F, 0F, 0F, -0.95F, 0F, 0F, -0.95F, 0F, 0F, -0.95F, 0F, 0F, -0.95F, 0F, 0F); // Import Box30
    gunModel[20].setRotationPoint(0F, 0F, 0F);
    gunModel[20].rotateAngleZ = -0.55850536F;

    gunModel[21].addShapeBox(0.5F, -1.55F, -0.5F, 1, 1, 1, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F); // Import Box31
    gunModel[21].setRotationPoint(0F, 0F, 0F);
    gunModel[21].rotateAngleZ = -0.41887902F;

    gunModel[22].addShapeBox(3.3F, -2.01F, -0.5F, 1, 1, 1, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F); // Import Box32
    gunModel[22].setRotationPoint(0F, 0F, 0F);

    gunModel[23].addShapeBox(0.6F, 1.3F, -0.5F, 1, 1, 1, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F); // Import Box33
    gunModel[23].setRotationPoint(0F, 0F, 0F);
    gunModel[23].rotateAngleZ = 1.57079633F;

    gunModel[24].addShapeBox(0.4F, 3.8F, -0.5F, 1, 1, 1, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F); // Import Box34
    gunModel[24].setRotationPoint(0F, 0F, 0F);
    gunModel[24].rotateAngleZ = 1.57079633F;

    gunModel[25].addShapeBox(0.3F, -2.6F, 0.46F, 1, 1, 1, 0F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F); // Import Box35
    gunModel[25].setRotationPoint(0F, 0F, 0F);

    gunModel[26].addShapeBox(-0.1F, -2.5F, -1.5F, 1, 1, 1, 0F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F); // Import Box36
    gunModel[26].setRotationPoint(0F, 0F, 0F);

    gunModel[27].addShapeBox(4.63F, -2.91F, 0.56F, 1, 1, 1, 0F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F); // Import Box37
    gunModel[27].setRotationPoint(0F, 0F, 0F);

    gunModel[28].addShapeBox(2.21F, 3.87F, 0.56F, 1, 1, 1, 0F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F); // Import Box38
    gunModel[28].setRotationPoint(0F, 0F, 0F);
    gunModel[28].rotateAngleZ = 1.57079633F;

    gunModel[29].addShapeBox(2.21F, 5.39F, 0.56F, 1, 1, 1, 0F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F); // Import Box39
    gunModel[29].setRotationPoint(0F, 0F, 0F);
    gunModel[29].rotateAngleZ = 1.57079633F;

    gunModel[30].addShapeBox(4.62F, -3.2F, 0.51F, 1, 1, 1, 0F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F); // Import Box40
    gunModel[30].setRotationPoint(0F, 0F, 0F);

    gunModel[31].addShapeBox(10.3F, -5.3F, -0.5F, 1, 1, 1, 0F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F); // Import Box43
    gunModel[31].setRotationPoint(0F, 0F, 0F);

    gunModel[32].addShapeBox(6F, -5.2F, -1F, 1, 1, 2, 0F, -0.2F, -0.4F, -0.25F, -0.2F, -0.4F, -0.25F, -0.2F, -0.4F, -0.25F, -0.2F, -0.4F, -0.25F, -0.2F, -0.4F, -0.2F, -0.2F, -0.4F, -0.2F, -0.2F, -0.4F, -0.2F, -0.2F, -0.4F, -0.2F); // Box 0
    gunModel[32].setRotationPoint(0F, 0F, 0F);

    gunModel[33].addShapeBox(0.6F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 1
    gunModel[33].setRotationPoint(0F, 0F, 0F);

    gunModel[34].addShapeBox(1.4F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 2
    gunModel[34].setRotationPoint(0F, 0F, 0F);

    gunModel[35].addShapeBox(1F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 3
    gunModel[35].setRotationPoint(0F, 0F, 0F);

    gunModel[36].addShapeBox(1.8F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 4
    gunModel[36].setRotationPoint(0F, 0F, 0F);

    gunModel[37].addShapeBox(2.2F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 5
    gunModel[37].setRotationPoint(0F, 0F, 0F);

    gunModel[38].addShapeBox(2.6F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 6
    gunModel[38].setRotationPoint(0F, 0F, 0F);

    gunModel[39].addShapeBox(3F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 7
    gunModel[39].setRotationPoint(0F, 0F, 0F);

    gunModel[40].addShapeBox(3.4F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 8
    gunModel[40].setRotationPoint(0F, 0F, 0F);

    gunModel[41].addShapeBox(3.8F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 9
    gunModel[41].setRotationPoint(0F, 0F, 0F);

    gunModel[42].addShapeBox(4.2F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 10
    gunModel[42].setRotationPoint(0F, 0F, 0F);

    gunModel[43].addShapeBox(4.6F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 11
    gunModel[43].setRotationPoint(0F, 0F, 0F);

    gunModel[44].addShapeBox(5F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 12
    gunModel[44].setRotationPoint(0F, 0F, 0F);

    gunModel[45].addShapeBox(5.4F, -5.2F, -1F, 1, 1, 2, 0F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F, -0.4F, -0.4F, -0.2F); // Box 13
    gunModel[45].setRotationPoint(0F, 0F, 0F);

    gunModel[46].addShapeBox(0F, -5.2F, -1F, 1, 1, 2, 0F, -0.2F, -0.4F, -0.25F, -0.2F, -0.4F, -0.25F, -0.2F, -0.4F, -0.25F, -0.2F, -0.4F, -0.25F, -0.2F, -0.4F, -0.2F, -0.2F, -0.4F, -0.2F, -0.2F, -0.4F, -0.2F, -0.2F, -0.4F, -0.2F); // Box 14
    gunModel[46].setRotationPoint(0F, 0F, 0F);

    gunModel[47].addShapeBox(0.3F, -2.6F, -1.5F, 1, 1, 1, 0F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F, -0.3F, -0.3F, -0.4F); // Box 51
    gunModel[47].setRotationPoint(0F, 0F, 0F);

    gunModel[48].addShapeBox(-0.1F, -2.5F, 0.46F, 1, 1, 1, 0F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F, -0.3F, -0.4F, -0.4F); // Box 52
    gunModel[48].setRotationPoint(0F, 0F, 0F);

    gunModel[49].addShapeBox(4.62F, -3.2F, -1.5F, 1, 1, 1, 0F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F, 0.21F, -0.2F, -0.48F); // Box 53
    gunModel[49].setRotationPoint(0F, 0F, 0F);

    gunModel[50].addShapeBox(2.21F, 5.39F, -1.5F, 1, 1, 1, 0F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F); // Box 54
    gunModel[50].setRotationPoint(0F, 0F, 0F);
    gunModel[50].rotateAngleZ = 1.57079633F;

    gunModel[51].addShapeBox(2.21F, 3.87F, -1.5F, 1, 1, 1, 0F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F, -0.15F, -0.45F, -0.45F); // Box 55
    gunModel[51].setRotationPoint(0F, 0F, 0F);
    gunModel[51].rotateAngleZ = 1.57079633F;

    gunModel[52].addShapeBox(4.63F, -2.91F, -1.5F, 1, 1, 1, 0F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F); // Box 56
    gunModel[52].setRotationPoint(0F, 0F, 0F);

    gunModel[53].addShapeBox(4.63F, -3.52F, -1.5F, 1, 1, 1, 0F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F, 0.2F, -0.45F, -0.45F); // Box 57
    gunModel[53].setRotationPoint(0F, 0F, 0F);


    ammoModel = new ModelRendererTurbo[2];
    ammoModel[0] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // Import Box0
    ammoModel[1] = new ModelRendererTurbo(this, 9, 25, textureX, textureY); // Import Box2

    ammoModel[0].addShapeBox(4.9F, 0F, -0.5F, 1, 6, 1, 0F, 0.15F, 0F, 0F, 0.15F, 0F, 0F, 0.15F, 0F, 0F, 0.15F, 0F, 0F, 0.15F, 0F, 0F, 0.15F, 0F, 0F, 0.15F, 0F, 0F, 0.15F, 0F, 0F); // Import Box0
    ammoModel[0].setRotationPoint(0F, 0F, 0F);
    ammoModel[0].rotateAngleZ = 0.20943951F;

    ammoModel[1].addShapeBox(4.95F, 5.6F, -0.5F, 1, 1, 1, 0F, 0.2F, -0.35F, 0.05F, 0.2F, -0.35F, 0.05F, 0.2F, -0.35F, 0.05F, 0.2F, -0.35F, 0.05F, 0.2F, -0.35F, 0.05F, 0.2F, -0.35F, 0.05F, 0.2F, -0.35F, 0.05F, 0.2F, -0.35F, 0.05F); // Import Box2
    ammoModel[1].setRotationPoint(0F, 0F, 0F);
    ammoModel[1].rotateAngleZ = 0.20943951F;
/*


    slideModel = new ModelRendererTurbo[2];
    slideModel[0] = new ModelRendererTurbo(this, 17, 25, textureX, textureY); // Import Box41
    slideModel[1] = new ModelRendererTurbo(this, 25, 25, textureX, textureY); // Import Box42

    slideModel[0].addShapeBox(-2F, -4F, -1.55F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box41
    slideModel[0].setRotationPoint(0F, 0F, 0F);

    slideModel[1].addShapeBox(-2F, -4F, -1.65F, 1, 1, 1, 0F, -0.35F, -0.1F, -0.45F, -0.35F, -0.1F, -0.45F, -0.35F, -0.1F, -0.45F, -0.35F, -0.1F, -0.45F, -0.35F, -0.1F, -0.45F, -0.35F, -0.1F, -0.45F, -0.35F, -0.1F, -0.45F, -0.35F, -0.1F, -0.45F); // Import Box42
    slideModel[1].setRotationPoint(0F, 0F, 0F);

*/


    barrelAttachPoint = new Vector3f(13F /16F, 3.25F /16F, 0F /16F);

    scopeAttachPoint = new Vector3f(0F /16F, 4.5F /16F, 0F /16F);

    gripAttachPoint = new Vector3f(8.5 /16F, 2.75F /16F, 0F /16F);


    animationType = EnumAnimationType.BOTTOM_CLIP;

   // gunSlideDistance = 0;

    translateAll(0F, 0F, 0F);


    flipAll();
  }
}