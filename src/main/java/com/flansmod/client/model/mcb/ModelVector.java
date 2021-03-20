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

public class ModelVector extends ModelGun //Same as Filename
{

  int textureX = 128;
  int textureY = 128;

  public ModelVector() //Same as Filename
  {
    gunModel = new ModelRendererTurbo[190];
    gunModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Import Box0
    gunModel[1] = new ModelRendererTurbo(this, 33, 1, textureX, textureY); // Import Box1
    gunModel[2] = new ModelRendererTurbo(this, 65, 1, textureX, textureY); // Import Box2
    gunModel[3] = new ModelRendererTurbo(this, 73, 1, textureX, textureY); // Import Box3
    gunModel[4] = new ModelRendererTurbo(this, 81, 1, textureX, textureY); // Import Box4
    gunModel[5] = new ModelRendererTurbo(this, 89, 1, textureX, textureY); // Import Box5
    gunModel[6] = new ModelRendererTurbo(this, 97, 1, textureX, textureY); // Import Box6
    gunModel[7] = new ModelRendererTurbo(this, 105, 1, textureX, textureY); // Import Box9
    gunModel[8] = new ModelRendererTurbo(this, 113, 1, textureX, textureY); // Import Box10
    gunModel[9] = new ModelRendererTurbo(this, 121, 1, textureX, textureY); // Import Box11
    gunModel[10] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Import Box12
    gunModel[11] = new ModelRendererTurbo(this, 9, 9, textureX, textureY); // Import Box13
    gunModel[12] = new ModelRendererTurbo(this, 17, 9, textureX, textureY); // Import Box14
    gunModel[13] = new ModelRendererTurbo(this, 25, 9, textureX, textureY); // Import Box15
    gunModel[14] = new ModelRendererTurbo(this, 33, 9, textureX, textureY); // Import Box16
    gunModel[15] = new ModelRendererTurbo(this, 41, 9, textureX, textureY); // Import Box17
    gunModel[16] = new ModelRendererTurbo(this, 49, 9, textureX, textureY); // Import Box18
    gunModel[17] = new ModelRendererTurbo(this, 57, 9, textureX, textureY); // Import Box19
    gunModel[18] = new ModelRendererTurbo(this, 65, 9, textureX, textureY); // Import Box20
    gunModel[19] = new ModelRendererTurbo(this, 73, 9, textureX, textureY); // Import Box21
    gunModel[20] = new ModelRendererTurbo(this, 81, 9, textureX, textureY); // Import Box22
    gunModel[21] = new ModelRendererTurbo(this, 89, 9, textureX, textureY); // Import Box23
    gunModel[22] = new ModelRendererTurbo(this, 97, 9, textureX, textureY); // Import Box24
    gunModel[23] = new ModelRendererTurbo(this, 105, 9, textureX, textureY); // Import Box26
    gunModel[24] = new ModelRendererTurbo(this, 113, 9, textureX, textureY); // Import Box27
    gunModel[25] = new ModelRendererTurbo(this, 121, 9, textureX, textureY); // Import Box28
    gunModel[26] = new ModelRendererTurbo(this, 1, 17, textureX, textureY); // Import Box29
    gunModel[27] = new ModelRendererTurbo(this, 9, 17, textureX, textureY); // Import Box30
    gunModel[28] = new ModelRendererTurbo(this, 17, 17, textureX, textureY); // Import Box31
    gunModel[29] = new ModelRendererTurbo(this, 25, 17, textureX, textureY); // Import Box32
    gunModel[30] = new ModelRendererTurbo(this, 33, 17, textureX, textureY); // Import Box33
    gunModel[31] = new ModelRendererTurbo(this, 41, 17, textureX, textureY); // Import Box34
    gunModel[32] = new ModelRendererTurbo(this, 49, 17, textureX, textureY); // Import Box36
    gunModel[33] = new ModelRendererTurbo(this, 57, 17, textureX, textureY); // Import Box37
    gunModel[34] = new ModelRendererTurbo(this, 65, 17, textureX, textureY); // Import Box38
    gunModel[35] = new ModelRendererTurbo(this, 73, 17, textureX, textureY); // Import Box39
    gunModel[36] = new ModelRendererTurbo(this, 81, 17, textureX, textureY); // Import Box40
    gunModel[37] = new ModelRendererTurbo(this, 89, 17, textureX, textureY); // Import Box41
    gunModel[38] = new ModelRendererTurbo(this, 97, 17, textureX, textureY); // Import Box42
    gunModel[39] = new ModelRendererTurbo(this, 105, 17, textureX, textureY); // Import Box43
    gunModel[40] = new ModelRendererTurbo(this, 113, 17, textureX, textureY); // Import Box44
    gunModel[41] = new ModelRendererTurbo(this, 1, 25, textureX, textureY); // Import Box45
    gunModel[42] = new ModelRendererTurbo(this, 17, 25, textureX, textureY); // Import Box46
    gunModel[43] = new ModelRendererTurbo(this, 25, 25, textureX, textureY); // Import Box47
    gunModel[44] = new ModelRendererTurbo(this, 33, 25, textureX, textureY); // Import Box48
    gunModel[45] = new ModelRendererTurbo(this, 41, 25, textureX, textureY); // Import Box49
    gunModel[46] = new ModelRendererTurbo(this, 49, 25, textureX, textureY); // Import Box50
    gunModel[47] = new ModelRendererTurbo(this, 57, 25, textureX, textureY); // Import Box51
    gunModel[48] = new ModelRendererTurbo(this, 65, 25, textureX, textureY); // Import Box52
    gunModel[49] = new ModelRendererTurbo(this, 73, 25, textureX, textureY); // Import Box53
    gunModel[50] = new ModelRendererTurbo(this, 81, 25, textureX, textureY); // Import Box54
    gunModel[51] = new ModelRendererTurbo(this, 89, 25, textureX, textureY); // Import Box55
    gunModel[52] = new ModelRendererTurbo(this, 97, 25, textureX, textureY); // Import Box56
    gunModel[53] = new ModelRendererTurbo(this, 113, 25, textureX, textureY); // Import Box57
    gunModel[54] = new ModelRendererTurbo(this, 121, 25, textureX, textureY); // Import Box58
    gunModel[55] = new ModelRendererTurbo(this, 1, 33, textureX, textureY); // Import Box59
    gunModel[56] = new ModelRendererTurbo(this, 17, 33, textureX, textureY); // Import Box60
    gunModel[57] = new ModelRendererTurbo(this, 25, 33, textureX, textureY); // Import Box61
    gunModel[58] = new ModelRendererTurbo(this, 33, 33, textureX, textureY); // Import Box62
    gunModel[59] = new ModelRendererTurbo(this, 41, 33, textureX, textureY); // Import Box63
    gunModel[60] = new ModelRendererTurbo(this, 49, 33, textureX, textureY); // Import Box64
    gunModel[61] = new ModelRendererTurbo(this, 57, 33, textureX, textureY); // Import Box66
    gunModel[62] = new ModelRendererTurbo(this, 81, 33, textureX, textureY); // Import Box67
    gunModel[63] = new ModelRendererTurbo(this, 89, 33, textureX, textureY); // Import Box68
    gunModel[64] = new ModelRendererTurbo(this, 97, 33, textureX, textureY); // Import Box69
    gunModel[65] = new ModelRendererTurbo(this, 113, 33, textureX, textureY); // Import Box71
    gunModel[66] = new ModelRendererTurbo(this, 1, 41, textureX, textureY); // Import Box72
    gunModel[67] = new ModelRendererTurbo(this, 17, 41, textureX, textureY); // Import Box73
    gunModel[68] = new ModelRendererTurbo(this, 25, 41, textureX, textureY); // Import Box74
    gunModel[69] = new ModelRendererTurbo(this, 33, 41, textureX, textureY); // Import Box75
    gunModel[70] = new ModelRendererTurbo(this, 41, 41, textureX, textureY); // Import Box76
    gunModel[71] = new ModelRendererTurbo(this, 49, 41, textureX, textureY); // Import Box77
    gunModel[72] = new ModelRendererTurbo(this, 57, 41, textureX, textureY); // Import Box74
    gunModel[73] = new ModelRendererTurbo(this, 65, 41, textureX, textureY); // Import Box75
    gunModel[74] = new ModelRendererTurbo(this, 73, 41, textureX, textureY); // Import Box76
    gunModel[75] = new ModelRendererTurbo(this, 81, 41, textureX, textureY); // Import Box77
    gunModel[76] = new ModelRendererTurbo(this, 97, 41, textureX, textureY); // Import Box78
    gunModel[77] = new ModelRendererTurbo(this, 105, 41, textureX, textureY); // Import Box79
    gunModel[78] = new ModelRendererTurbo(this, 113, 41, textureX, textureY); // Import Box80
    gunModel[79] = new ModelRendererTurbo(this, 1, 49, textureX, textureY); // Import Box84
    gunModel[80] = new ModelRendererTurbo(this, 121, 41, textureX, textureY); // Import Box85
    gunModel[81] = new ModelRendererTurbo(this, 17, 49, textureX, textureY); // Import Box86
    gunModel[82] = new ModelRendererTurbo(this, 25, 49, textureX, textureY); // Import Box87
    gunModel[83] = new ModelRendererTurbo(this, 33, 49, textureX, textureY); // Import Box88
    gunModel[84] = new ModelRendererTurbo(this, 41, 49, textureX, textureY); // Import Box89
    gunModel[85] = new ModelRendererTurbo(this, 49, 49, textureX, textureY); // Import Box90
    gunModel[86] = new ModelRendererTurbo(this, 57, 49, textureX, textureY); // Import Box91
    gunModel[87] = new ModelRendererTurbo(this, 65, 49, textureX, textureY); // Import Box92
    gunModel[88] = new ModelRendererTurbo(this, 73, 49, textureX, textureY); // Import Box93
    gunModel[89] = new ModelRendererTurbo(this, 81, 49, textureX, textureY); // Import Box94
    gunModel[90] = new ModelRendererTurbo(this, 89, 49, textureX, textureY); // Import Box95
    gunModel[91] = new ModelRendererTurbo(this, 97, 49, textureX, textureY); // Import Box96
    gunModel[92] = new ModelRendererTurbo(this, 105, 49, textureX, textureY); // Import Box97
    gunModel[93] = new ModelRendererTurbo(this, 113, 49, textureX, textureY); // Import Box99
    gunModel[94] = new ModelRendererTurbo(this, 121, 49, textureX, textureY); // Import Box100
    gunModel[95] = new ModelRendererTurbo(this, 1, 57, textureX, textureY); // Import Box101
    gunModel[96] = new ModelRendererTurbo(this, 9, 57, textureX, textureY); // Import Box102
    gunModel[97] = new ModelRendererTurbo(this, 17, 57, textureX, textureY); // Import Box103
    gunModel[98] = new ModelRendererTurbo(this, 25, 57, textureX, textureY); // Import Box104
    gunModel[99] = new ModelRendererTurbo(this, 33, 57, textureX, textureY); // Import Box105
    gunModel[100] = new ModelRendererTurbo(this, 41, 57, textureX, textureY); // Import Box106
    gunModel[101] = new ModelRendererTurbo(this, 49, 57, textureX, textureY); // Import Box107
    gunModel[102] = new ModelRendererTurbo(this, 57, 57, textureX, textureY); // Import Box108
    gunModel[103] = new ModelRendererTurbo(this, 65, 57, textureX, textureY); // Import Box109
    gunModel[104] = new ModelRendererTurbo(this, 73, 57, textureX, textureY); // Import Box110
    gunModel[105] = new ModelRendererTurbo(this, 81, 57, textureX, textureY); // Import Box111
    gunModel[106] = new ModelRendererTurbo(this, 89, 57, textureX, textureY); // Import Box112
    gunModel[107] = new ModelRendererTurbo(this, 97, 57, textureX, textureY); // Import Box113
    gunModel[108] = new ModelRendererTurbo(this, 105, 57, textureX, textureY); // Import Box114
    gunModel[109] = new ModelRendererTurbo(this, 113, 57, textureX, textureY); // Import Box115
    gunModel[110] = new ModelRendererTurbo(this, 121, 57, textureX, textureY); // Import Box116
    gunModel[111] = new ModelRendererTurbo(this, 1, 65, textureX, textureY); // Import Box117
    gunModel[112] = new ModelRendererTurbo(this, 9, 65, textureX, textureY); // Import Box118
    gunModel[113] = new ModelRendererTurbo(this, 17, 65, textureX, textureY); // Import Box119
    gunModel[114] = new ModelRendererTurbo(this, 25, 65, textureX, textureY); // Import Box120
    gunModel[115] = new ModelRendererTurbo(this, 33, 65, textureX, textureY); // Import Box121
    gunModel[116] = new ModelRendererTurbo(this, 41, 65, textureX, textureY); // Import Box122
    gunModel[117] = new ModelRendererTurbo(this, 49, 65, textureX, textureY); // Import Box123
    gunModel[118] = new ModelRendererTurbo(this, 57, 65, textureX, textureY); // Import Box124
    gunModel[119] = new ModelRendererTurbo(this, 65, 65, textureX, textureY); // Import Box125
    gunModel[120] = new ModelRendererTurbo(this, 73, 65, textureX, textureY); // Import Box130
    gunModel[121] = new ModelRendererTurbo(this, 81, 65, textureX, textureY); // Import Box131
    gunModel[122] = new ModelRendererTurbo(this, 89, 65, textureX, textureY); // Import Box132
    gunModel[123] = new ModelRendererTurbo(this, 97, 65, textureX, textureY); // Import Box133
    gunModel[124] = new ModelRendererTurbo(this, 105, 65, textureX, textureY); // Import Box134
    gunModel[125] = new ModelRendererTurbo(this, 113, 65, textureX, textureY); // Import Box135
    gunModel[126] = new ModelRendererTurbo(this, 121, 65, textureX, textureY); // Import Box136
    gunModel[127] = new ModelRendererTurbo(this, 1, 73, textureX, textureY); // Import Box137
    gunModel[128] = new ModelRendererTurbo(this, 9, 73, textureX, textureY); // Import Box138
    gunModel[129] = new ModelRendererTurbo(this, 17, 73, textureX, textureY); // Import Box139
    gunModel[130] = new ModelRendererTurbo(this, 25, 73, textureX, textureY); // Import Box140
    gunModel[131] = new ModelRendererTurbo(this, 33, 73, textureX, textureY); // Import Box141
    gunModel[132] = new ModelRendererTurbo(this, 41, 73, textureX, textureY); // Import Box142
    gunModel[133] = new ModelRendererTurbo(this, 49, 73, textureX, textureY); // Import Box143
    gunModel[134] = new ModelRendererTurbo(this, 57, 73, textureX, textureY); // Import Box144
    gunModel[135] = new ModelRendererTurbo(this, 65, 73, textureX, textureY); // Import Box145
    gunModel[136] = new ModelRendererTurbo(this, 73, 73, textureX, textureY); // Import Box146
    gunModel[137] = new ModelRendererTurbo(this, 81, 73, textureX, textureY); // Import Box147
    gunModel[138] = new ModelRendererTurbo(this, 89, 73, textureX, textureY); // Import Box148
    gunModel[139] = new ModelRendererTurbo(this, 97, 73, textureX, textureY); // Import Box149
    gunModel[140] = new ModelRendererTurbo(this, 105, 73, textureX, textureY); // Import Box150
    gunModel[141] = new ModelRendererTurbo(this, 113, 73, textureX, textureY); // Import Box151
    gunModel[142] = new ModelRendererTurbo(this, 121, 73, textureX, textureY); // Import Box152
    gunModel[143] = new ModelRendererTurbo(this, 1, 81, textureX, textureY); // Import Box153
    gunModel[144] = new ModelRendererTurbo(this, 9, 81, textureX, textureY); // Import Box154
    gunModel[145] = new ModelRendererTurbo(this, 17, 81, textureX, textureY); // Import Box155
    gunModel[146] = new ModelRendererTurbo(this, 25, 81, textureX, textureY); // Import Box156
    gunModel[147] = new ModelRendererTurbo(this, 33, 81, textureX, textureY); // Import Box157
    gunModel[148] = new ModelRendererTurbo(this, 41, 81, textureX, textureY); // Import Box158
    gunModel[149] = new ModelRendererTurbo(this, 49, 81, textureX, textureY); // Import Box159
    gunModel[150] = new ModelRendererTurbo(this, 57, 81, textureX, textureY); // Import Box160
    gunModel[151] = new ModelRendererTurbo(this, 65, 81, textureX, textureY); // Import Box161
    gunModel[152] = new ModelRendererTurbo(this, 73, 81, textureX, textureY); // Import Box162
    gunModel[153] = new ModelRendererTurbo(this, 81, 81, textureX, textureY); // Import Box164
    gunModel[154] = new ModelRendererTurbo(this, 89, 81, textureX, textureY); // Import Box165
    gunModel[155] = new ModelRendererTurbo(this, 97, 81, textureX, textureY); // Import Box166
    gunModel[156] = new ModelRendererTurbo(this, 105, 81, textureX, textureY); // Import Box167
    gunModel[157] = new ModelRendererTurbo(this, 113, 81, textureX, textureY); // Import Box168
    gunModel[158] = new ModelRendererTurbo(this, 121, 81, textureX, textureY); // Import Box169
    gunModel[159] = new ModelRendererTurbo(this, 1, 89, textureX, textureY); // Import Box170
    gunModel[160] = new ModelRendererTurbo(this, 9, 89, textureX, textureY); // Import Box171
    gunModel[161] = new ModelRendererTurbo(this, 17, 89, textureX, textureY); // Import Box172
    gunModel[162] = new ModelRendererTurbo(this, 25, 89, textureX, textureY); // Import Box173
    gunModel[163] = new ModelRendererTurbo(this, 33, 89, textureX, textureY); // Import Box174
    gunModel[164] = new ModelRendererTurbo(this, 41, 89, textureX, textureY); // Import Box175
    gunModel[165] = new ModelRendererTurbo(this, 49, 89, textureX, textureY); // Import Box176
    gunModel[166] = new ModelRendererTurbo(this, 57, 89, textureX, textureY); // Import Box177
    gunModel[167] = new ModelRendererTurbo(this, 65, 89, textureX, textureY); // Import Box178
    gunModel[168] = new ModelRendererTurbo(this, 73, 89, textureX, textureY); // Import Box179
    gunModel[169] = new ModelRendererTurbo(this, 81, 89, textureX, textureY); // Import Box180
    gunModel[170] = new ModelRendererTurbo(this, 89, 89, textureX, textureY); // Import Box181
    gunModel[171] = new ModelRendererTurbo(this, 97, 89, textureX, textureY); // Import Box182
    gunModel[172] = new ModelRendererTurbo(this, 105, 89, textureX, textureY); // Import Box183
    gunModel[173] = new ModelRendererTurbo(this, 113, 89, textureX, textureY); // Import Box184
    gunModel[174] = new ModelRendererTurbo(this, 121, 89, textureX, textureY); // Import Box185
    gunModel[175] = new ModelRendererTurbo(this, 1, 97, textureX, textureY); // Import Box186
    gunModel[176] = new ModelRendererTurbo(this, 9, 97, textureX, textureY); // Import Box187
    gunModel[177] = new ModelRendererTurbo(this, 17, 97, textureX, textureY); // Import Box188
    gunModel[178] = new ModelRendererTurbo(this, 25, 97, textureX, textureY); // Import Box189
    gunModel[179] = new ModelRendererTurbo(this, 33, 97, textureX, textureY); // Import Box190
    gunModel[180] = new ModelRendererTurbo(this, 41, 97, textureX, textureY); // Import Box191
    gunModel[181] = new ModelRendererTurbo(this, 49, 97, textureX, textureY); // Import Box192
    gunModel[182] = new ModelRendererTurbo(this, 57, 97, textureX, textureY); // Import Box193
    gunModel[183] = new ModelRendererTurbo(this, 65, 97, textureX, textureY); // Import Box194
    gunModel[184] = new ModelRendererTurbo(this, 73, 97, textureX, textureY); // Import Box195
    gunModel[185] = new ModelRendererTurbo(this, 81, 97, textureX, textureY); // Import Box196
    gunModel[186] = new ModelRendererTurbo(this, 89, 97, textureX, textureY); // Import Box197
    gunModel[187] = new ModelRendererTurbo(this, 97, 97, textureX, textureY); // Import Box198
    gunModel[188] = new ModelRendererTurbo(this, 105, 97, textureX, textureY); // Import Box199
    gunModel[189] = new ModelRendererTurbo(this, 113, 97, textureX, textureY); // Import Box200

    gunModel[0].addShapeBox(-0.8F, -3F, -1F, 10, 1, 2, 0F, 0.25F, -0.05F, -0.2F, 0.25F, -0.05F, -0.2F, 0.25F, -0.05F, -0.2F, 0.25F, -0.05F, -0.2F, 0.25F, -0.05F, -0.2F, 0.25F, -0.05F, -0.2F, 0.25F, -0.05F, -0.2F, 0.25F, -0.05F, -0.2F); // Import Box0
    gunModel[0].setRotationPoint(0F, 0F, 0F);

    gunModel[1].addShapeBox(-0.8F, -3.5F, -1F, 10, 1, 2, 0F, 0.25F, -0.45F, -0.25F, 0.25F, -0.45F, -0.25F, 0.25F, -0.45F, -0.25F, 0.25F, -0.45F, -0.25F, 0.25F, -0.45F, -0.2F, 0.25F, -0.45F, -0.2F, 0.25F, -0.45F, -0.2F, 0.25F, -0.45F, -0.2F); // Import Box1
    gunModel[1].setRotationPoint(0F, 0F, 0F);

    gunModel[2].addShapeBox(9.45F, -1.9F, -0.5F, 2, 1, 1, 0F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F); // Import Box2
    gunModel[2].setRotationPoint(0F, 0F, 0F);

    gunModel[3].addShapeBox(9.95F, -2.26F, -0.5F, 1, 1, 1, 0F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F); // Import Box3
    gunModel[3].setRotationPoint(0F, 0F, 0F);

    gunModel[4].addShapeBox(9.95F, -1.54F, -0.5F, 1, 1, 1, 0F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F); // Import Box4
    gunModel[4].setRotationPoint(0F, 0F, 0F);

    gunModel[5].addShapeBox(9.95F, -0.14F, 0.9F, 1, 1, 1, 0F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F); // Import Box5
    gunModel[5].setRotationPoint(0F, 0F, 0F);
    gunModel[5].rotateAngleX = 1.57079633F;

    gunModel[6].addShapeBox(9.95F, -0.86F, 0.9F, 1, 1, 1, 0F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.4F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F, 0.5F, -0.44F, -0.2F); // Import Box6
    gunModel[6].setRotationPoint(0F, 0F, 0F);
    gunModel[6].rotateAngleX = 1.57079633F;

    gunModel[7].addShapeBox(8.65F, -3.75F, -0.5F, 1, 1, 1, 0F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F); // Import Box9
    gunModel[7].setRotationPoint(0F, 0F, 0F);

    gunModel[8].addShapeBox(-1.3F, -3.6F, -1F, 1, 1, 2, 0F, -0.25F, -0.45F, -0.3F, -0.25F, -0.45F, -0.3F, -0.25F, -0.45F, -0.3F, -0.25F, -0.45F, -0.3F, -0.25F, -0.45F, -0.25F, -0.25F, -0.45F, -0.25F, -0.25F, -0.45F, -0.25F, -0.25F, -0.45F, -0.25F); // Import Box10
    gunModel[8].setRotationPoint(0F, 0F, 0F);

    gunModel[9].addShapeBox(-0.85F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box11
    gunModel[9].setRotationPoint(0F, 0F, 0F);

    gunModel[10].addShapeBox(-0.25F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box12
    gunModel[10].setRotationPoint(0F, 0F, 0F);

    gunModel[11].addShapeBox(-0.55F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box13
    gunModel[11].setRotationPoint(0F, 0F, 0F);

    gunModel[12].addShapeBox(0.05F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box14
    gunModel[12].setRotationPoint(0F, 0F, 0F);

    gunModel[13].addShapeBox(0.35F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box15
    gunModel[13].setRotationPoint(0F, 0F, 0F);

    gunModel[14].addShapeBox(0.65F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box16
    gunModel[14].setRotationPoint(0F, 0F, 0F);

    gunModel[15].addShapeBox(0.95F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box17
    gunModel[15].setRotationPoint(0F, 0F, 0F);

    gunModel[16].addShapeBox(1.25F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box18
    gunModel[16].setRotationPoint(0F, 0F, 0F);

    gunModel[17].addShapeBox(1.55F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box19
    gunModel[17].setRotationPoint(0F, 0F, 0F);

    gunModel[18].addShapeBox(1.85F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box20
    gunModel[18].setRotationPoint(0F, 0F, 0F);

    gunModel[19].addShapeBox(2.15F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box21
    gunModel[19].setRotationPoint(0F, 0F, 0F);

    gunModel[20].addShapeBox(2.45F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box22
    gunModel[20].setRotationPoint(0F, 0F, 0F);

    gunModel[21].addShapeBox(2.75F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box23
    gunModel[21].setRotationPoint(0F, 0F, 0F);

    gunModel[22].addShapeBox(3.05F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box24
    gunModel[22].setRotationPoint(0F, 0F, 0F);

    gunModel[23].addShapeBox(3.35F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box26
    gunModel[23].setRotationPoint(0F, 0F, 0F);

    gunModel[24].addShapeBox(3.65F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box27
    gunModel[24].setRotationPoint(0F, 0F, 0F);

    gunModel[25].addShapeBox(3.95F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box28
    gunModel[25].setRotationPoint(0F, 0F, 0F);

    gunModel[26].addShapeBox(4.25F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box29
    gunModel[26].setRotationPoint(0F, 0F, 0F);

    gunModel[27].addShapeBox(4.55F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box30
    gunModel[27].setRotationPoint(0F, 0F, 0F);

    gunModel[28].addShapeBox(4.85F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box31
    gunModel[28].setRotationPoint(0F, 0F, 0F);

    gunModel[29].addShapeBox(5.15F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box32
    gunModel[29].setRotationPoint(0F, 0F, 0F);

    gunModel[30].addShapeBox(5.75F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box33
    gunModel[30].setRotationPoint(0F, 0F, 0F);

    gunModel[31].addShapeBox(5.45F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box34
    gunModel[31].setRotationPoint(0F, 0F, 0F);

    gunModel[32].addShapeBox(6.05F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box36
    gunModel[32].setRotationPoint(0F, 0F, 0F);

    gunModel[33].addShapeBox(6.35F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box37
    gunModel[33].setRotationPoint(0F, 0F, 0F);

    gunModel[34].addShapeBox(6.65F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box38
    gunModel[34].setRotationPoint(0F, 0F, 0F);

    gunModel[35].addShapeBox(6.95F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box39
    gunModel[35].setRotationPoint(0F, 0F, 0F);

    gunModel[36].addShapeBox(7.25F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box40
    gunModel[36].setRotationPoint(0F, 0F, 0F);

    gunModel[37].addShapeBox(7.55F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box41
    gunModel[37].setRotationPoint(0F, 0F, 0F);

    gunModel[38].addShapeBox(7.85F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box42
    gunModel[38].setRotationPoint(0F, 0F, 0F);

    gunModel[39].addShapeBox(8.15F, -3.6F, -1F, 1, 1, 2, 0F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.3F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F, -0.45F, -0.45F, -0.25F); // Import Box43
    gunModel[39].setRotationPoint(0F, 0F, 0F);

    gunModel[40].addShapeBox(7.2F, -1.95F, -1F, 2, 1, 2, 0F, 0.25F, 0.1F, -0.25F, 0.25F, 0.1F, -0.25F, 0.25F, 0.1F, -0.25F, 0.25F, 0.1F, -0.25F, 0.25F, 0.1F, -0.25F, 0.25F, 0.1F, -0.25F, 0.25F, 0.1F, -0.25F, 0.25F, 0.1F, -0.25F); // Import Box44
    gunModel[40].setRotationPoint(0F, 0F, 0F);

    gunModel[41].addShapeBox(7.2F, -1.25F, -1F, 2, 1, 2, 0F, 0.25F, -0.4F, -0.25F, 0.25F, -0.4F, -0.25F, 0.25F, -0.4F, -0.25F, 0.25F, -0.4F, -0.25F, 0.25F, -0.4F, -0.3F, 0.25F, -0.4F, -0.3F, 0.25F, -0.4F, -0.3F, 0.25F, -0.4F, -0.3F); // Import Box45
    gunModel[41].setRotationPoint(0F, 0F, 0F);

    gunModel[42].addShapeBox(8.8F, -1.1F, -1F, 1, 1, 2, 0F, -0.35F, -0.45F, -0.3F, -0.35F, -0.45F, -0.3F, -0.35F, -0.45F, -0.3F, -0.35F, -0.45F, -0.3F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F); // Import Box46
    gunModel[42].setRotationPoint(0F, 0F, 0F);

    gunModel[43].addShapeBox(6.95F, -1.1F, -1F, 1, 1, 2, 0F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F); // Import Box47
    gunModel[43].setRotationPoint(0F, 0F, 0F);

    gunModel[44].addShapeBox(7.25F, -1.1F, -1F, 1, 1, 2, 0F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F); // Import Box48
    gunModel[44].setRotationPoint(0F, 0F, 0F);

    gunModel[45].addShapeBox(7.55F, -1.1F, -1F, 1, 1, 2, 0F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F); // Import Box49
    gunModel[45].setRotationPoint(0F, 0F, 0F);

    gunModel[46].addShapeBox(7.85F, -1.1F, -1F, 1, 1, 2, 0F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F); // Import Box50
    gunModel[46].setRotationPoint(0F, 0F, 0F);

    gunModel[47].addShapeBox(8.15F, -1.1F, -1F, 1, 1, 2, 0F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F); // Import Box51
    gunModel[47].setRotationPoint(0F, 0F, 0F);

    gunModel[48].addShapeBox(8.45F, -1.1F, -1F, 1, 1, 2, 0F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.3F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F, -0.42F, -0.45F, -0.35F); // Import Box52
    gunModel[48].setRotationPoint(0F, 0F, 0F);

    gunModel[49].addShapeBox(6.6F, -1.1F, -1F, 1, 1, 2, 0F, -0.35F, -0.45F, -0.3F, -0.35F, -0.45F, -0.3F, -0.35F, -0.45F, -0.3F, -0.35F, -0.45F, -0.3F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F); // Import Box53
    gunModel[49].setRotationPoint(0F, 0F, 0F);

    gunModel[50].addShapeBox(-2.05F, -2.95F, -1F, 1, 1, 2, 0F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F, 0F, -0.1F, -0.3F); // Import Box54
    gunModel[50].setRotationPoint(0F, 0F, 0F);

    gunModel[51].addShapeBox(-2.05F, -3.45F, -1F, 1, 1, 2, 0F, 0F, -0.6F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.6F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F); // Import Box55
    gunModel[51].setRotationPoint(0F, 0F, 0F);

    gunModel[52].addShapeBox(-6.05F, -2.65F, -1F, 4, 1, 2, 0F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F); // Import Box56
    gunModel[52].setRotationPoint(0F, 0F, 0F);

    gunModel[53].addShapeBox(-2.05F, -3.1F, -1F, 1, 1, 2, 0F, 0F, -0.6F, -0.3F, 0F, -0.25F, -0.3F, 0F, -0.25F, -0.3F, 0F, -0.6F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F); // Import Box57
    gunModel[53].setRotationPoint(-1F, 0F, 0F);

    gunModel[54].addShapeBox(-2.05F, -2.45F, -1F, 1, 1, 2, 0F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.35F, -0.3F, 0F, -0.6F, -0.3F, 0F, -0.6F, -0.3F, 0F, -0.35F, -0.3F); // Import Box58
    gunModel[54].setRotationPoint(0F, 0F, 0F);

    gunModel[55].addShapeBox(-3.6F, -6.68F, -1F, 4, 1, 2, 0F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F, 0F, -0.15F, -0.3F); // Import Box59
    gunModel[55].setRotationPoint(0F, 0F, 0F);
    gunModel[55].rotateAngleZ = 1.23918377F;

    gunModel[56].addShapeBox(-8.6F, -1.68F, -1F, 1, 4, 2, 0F, -0.15F, 0F, -0.3F, -0.15F, 0F, -0.3F, -0.15F, 0F, -0.3F, -0.15F, 0F, -0.3F, -0.15F, 0F, -0.3F, -0.15F, 0F, -0.3F, -0.15F, 0F, -0.3F, -0.15F, 0F, -0.3F); // Import Box60
    gunModel[56].setRotationPoint(0F, 0F, 0F);

    gunModel[57].addShapeBox(-7.54F, -1.83F, -1F, 1, 1, 2, 0F, 0.21F, -0.15F, -0.3F, 0.21F, -0.15F, -0.3F, 0.21F, -0.15F, -0.3F, 0.21F, -0.15F, -0.3F, 0.21F, -0.15F, -0.3F, -0.03F, -0.15F, -0.3F, -0.03F, -0.15F, -0.3F, 0.21F, -0.15F, -0.3F); // Import Box61
    gunModel[57].setRotationPoint(0F, 0F, 0F);

    gunModel[58].addShapeBox(-7.51F, 1.25F, -1F, 1, 1, 2, 0F, -0.17F, -0.05F, -0.3F, -0.17F, -0.25F, -0.3F, -0.17F, -0.25F, -0.3F, -0.17F, -0.05F, -0.3F, -0.17F, -0.45F, -0.3F, -0.17F, -0.45F, -0.3F, -0.17F, -0.45F, -0.3F, -0.17F, -0.45F, -0.3F); // Import Box62
    gunModel[58].setRotationPoint(0F, 0F, 0F);

    gunModel[59].addShapeBox(-8.05F, 1.05F, -1F, 1, 1, 2, 0F, -0.29F, -0.25F, -0.3F, -0.29F, -0.25F, -0.3F, -0.29F, -0.25F, -0.3F, -0.29F, -0.25F, -0.3F, -0.29F, -0.25F, -0.3F, -0.29F, -0.25F, -0.3F, -0.29F, -0.25F, -0.3F, -0.29F, -0.25F, -0.3F); // Import Box63
    gunModel[59].setRotationPoint(0F, 0F, 0F);

    gunModel[60].addShapeBox(-7.75F, 1.4F, -1F, 1, 1, 2, 0F, 0F, -0.4F, -0.3F, 0.07F, -0.4F, -0.3F, 0.07F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.08F, -0.3F, 0F, -0.57F, -0.3F, 0F, -0.57F, -0.3F, 0F, -0.08F, -0.3F); // Import Box64
    gunModel[60].setRotationPoint(0F, 0F, 0F);

    gunModel[61].addShapeBox(-0.45F, -2.45F, -1F, 7, 1, 2, 0F, 0.4F, -0.4F, -0.25F, 0.4F, -0.4F, -0.25F, 0.4F, -0.4F, -0.25F, 0.4F, -0.4F, -0.25F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F); // Import Box66
    gunModel[61].setRotationPoint(0F, 0F, 0F);

    gunModel[62].addShapeBox(-1.85F, -2.45F, -1F, 1, 1, 2, 0F, -0.7F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, -0.7F, -0.4F, -0.3F, -0.7F, -0.6F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, -0.7F, -0.6F, -0.3F); // Import Box67
    gunModel[62].setRotationPoint(0F, 0F, 0F);

    gunModel[63].addShapeBox(-0.45F, -2.25F, -1F, 1, 1, 2, 0F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F, 0.4F, -0.4F, -0.3F); // Import Box68
    gunModel[63].setRotationPoint(0F, 0F, 0F);

    gunModel[64].addShapeBox(-1.56F, -1.27F, -1F, 2, 3, 2, 0F, -0.17F, -0.06F, -0.3F, -0.17F, -0.06F, -0.3F, -0.17F, -0.06F, -0.3F, -0.17F, -0.06F, -0.3F, -0.17F, -0.06F, -0.3F, -0.17F, -0.06F, -0.3F, -0.17F, -0.06F, -0.3F, -0.17F, -0.06F, -0.3F); // Import Box69
    gunModel[64].setRotationPoint(0F, 0F, 0F);
    gunModel[64].rotateAngleZ = -0.36651914F;

    gunModel[65].addShapeBox(-1.56F, -2.06F, -1F, 2, 1, 2, 0F, -0.17F, -0.8F, -0.3F, -0.17F, -0.15F, -0.3F, -0.17F, -0.15F, -0.3F, -0.17F, -0.8F, -0.3F, -0.17F, -0.15F, -0.3F, -0.17F, -0.15F, -0.3F, -0.17F, -0.15F, -0.3F, -0.17F, -0.15F, -0.3F); // Import Box71
    gunModel[65].setRotationPoint(0F, 0F, 0F);
    gunModel[65].rotateAngleZ = -0.36651914F;

    gunModel[66].addShapeBox(0.29F, 1.05F, -1F, 2, 1, 2, 0F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.4F, -0.3F); // Import Box72
    gunModel[66].setRotationPoint(0F, 0F, 0F);
    gunModel[66].rotateAngleZ = -0.38397244F;

    gunModel[67].addShapeBox(0.1F, -1.65F, -1F, 1, 1, 2, 0F, -0.15F, -0.45F, -0.3F, -0.15F, -0.45F, -0.3F, -0.15F, -0.45F, -0.3F, -0.15F, -0.45F, -0.3F, -0.15F, -0.45F, -0.3F, -0.15F, -0.45F, -0.3F, -0.15F, -0.45F, -0.3F, -0.15F, -0.45F, -0.3F); // Import Box73
    gunModel[67].setRotationPoint(0F, 0F, 0F);
    gunModel[67].rotateAngleZ = -0.38397244F;

    gunModel[68].addShapeBox(1.29F, -1.21F, -1F, 1, 1, 2, 0F, -0.01F, -0.45F, -0.3F, -0.01F, -0.45F, -0.3F, -0.01F, -0.45F, -0.3F, -0.01F, -0.45F, -0.3F, -0.01F, -0.45F, -0.3F, -0.01F, -0.45F, -0.3F, -0.01F, -0.45F, -0.3F, -0.01F, -0.45F, -0.3F); // Import Box74
    gunModel[68].setRotationPoint(0F, 0F, 0F);

    gunModel[69].addShapeBox(1.56F, -2.65F, -1F, 1, 4, 2, 0F, -0.27F, 0.1F, -0.3F, -0.27F, 0.1F, -0.3F, -0.27F, 0.1F, -0.3F, -0.27F, 0.1F, -0.3F, -0.27F, 0.1F, -0.3F, -0.27F, 0.1F, -0.3F, -0.27F, 0.1F, -0.3F, -0.27F, 0.1F, -0.3F); // Import Box75
    gunModel[69].setRotationPoint(0F, 0F, 0F);
    gunModel[69].rotateAngleZ = -0.38397244F;

    gunModel[70].addShapeBox(0F, -1.99F, -1F, 1, 1, 2, 0F, -0.24F, -0.45F, -0.3F, -0.24F, -0.45F, -0.3F, -0.24F, -0.45F, -0.3F, -0.24F, -0.45F, -0.3F, -0.24F, -0.45F, -0.3F, -0.24F, -0.45F, -0.3F, -0.24F, -0.45F, -0.3F, -0.24F, -0.45F, -0.3F); // Import Box76
    gunModel[70].setRotationPoint(0F, 0F, 0F);
    gunModel[70].rotateAngleZ = -0.38397244F;

    gunModel[71].addShapeBox(1F, 0.79F, -1F, 1, 1, 2, 0F, -0.05F, -0.45F, -0.3F, -0.05F, -0.45F, -0.3F, -0.05F, -0.45F, -0.3F, -0.05F, -0.45F, -0.3F, -0.05F, -0.45F, -0.3F, -0.05F, -0.45F, -0.3F, -0.05F, -0.45F, -0.3F, -0.05F, -0.45F, -0.3F); // Import Box77
    gunModel[71].setRotationPoint(0F, 0F, 0F);
    gunModel[71].rotateAngleZ = 1.57079633F;

    gunModel[72].addShapeBox(0.84F, 0.45F, -1F, 1, 1, 2, 0F, -0.35F, -0.99F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, -0.35F, -0.99F, -0.3F, -0.35F, 0F, -0.3F, 0F, 0F, -0.3F, 0F, 0F, -0.3F, -0.35F, 0F, -0.3F); // Import Box74
    gunModel[72].setRotationPoint(0F, 0F, 0F);
    gunModel[72].rotateAngleZ = -0.38397244F;

    gunModel[73].addShapeBox(2.76F, -2.25F, -1F, 1, 1, 2, 0F, 0F, -0.4F, -0.3F, -0.6F, -0.4F, -0.3F, -0.6F, -0.4F, -0.3F, 0F, -0.4F, -0.3F, 0F, -0.6F, -0.3F, -0.6F, -0.44F, -0.3F, -0.6F, -0.44F, -0.3F, 0F, -0.6F, -0.3F); // Import Box75
    gunModel[73].setRotationPoint(0F, 0F, 0F);

    gunModel[74].addShapeBox(1.66F, -1.74F, -1F, 1, 4, 2, 0F, 0.15F, -4.1F, -0.3F, 0.5F, -0.05F, -0.3F, 0.5F, -0.05F, -0.3F, 0.15F, -4.1F, -0.3F, 0.15F, 0.13F, -0.3F, 0.5F, 0.13F, -0.3F, 0.5F, 0.13F, -0.3F, 0.15F, 0.13F, -0.3F); // Import Box76
    gunModel[74].setRotationPoint(0F, 0F, 0F);

    gunModel[75].addShapeBox(3.55F, -1.85F, -1F, 3, 1, 2, 0F, 0.4F, 0F, -0.3F, 0.4F, 0F, -0.3F, 0.4F, 0F, -0.3F, 0.4F, 0F, -0.3F, 0.4F, 0F, -0.3F, 0.4F, 0F, -0.3F, 0.4F, 0F, -0.3F, 0.4F, 0F, -0.3F); // Import Box77
    gunModel[75].setRotationPoint(0F, 0F, 0F);

    gunModel[76].addShapeBox(3.65F, -0.85F, -1F, 1, 3, 2, 0F, 0.5F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, -0.3F); // Import Box78
    gunModel[76].setRotationPoint(0F, 0F, 0F);

    gunModel[77].addShapeBox(5.65F, -0.85F, -1F, 1, 3, 2, 0F, 0.5F, 0F, -0.3F, -0.3F, 0F, -0.3F, -0.3F, 0F, -0.3F, 0.5F, 0F, -0.3F, 0.5F, 0F, -0.3F, -0.3F, -2.6F, -0.3F, -0.3F, -2.6F, -0.3F, 0.5F, 0F, -0.3F); // Import Box79
    gunModel[77].setRotationPoint(0F, 0F, 0F);

    gunModel[78].addShapeBox(6.15F, -1.15F, -1F, 1, 1, 2, 0F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F, -0.2F, -0.3F, -0.3F); // Import Box80
    gunModel[78].setRotationPoint(0F, 0F, 0F);

    gunModel[79].addShapeBox(-1.56F, 1.2F, -1F, 2, 1, 2, 0F, -0.12F, -0.45F, -0.25F, -0.17F, -0.45F, -0.25F, -0.17F, -0.45F, -0.25F, -0.12F, -0.45F, -0.25F, -0.12F, -0.45F, -0.25F, -0.17F, -0.45F, -0.25F, -0.17F, -0.45F, -0.25F, -0.12F, -0.45F, -0.25F); // Import Box84
    gunModel[79].setRotationPoint(0F, 0F, 0F);
    gunModel[79].rotateAngleZ = -0.36651914F;

    gunModel[80].addShapeBox(2.95F, -2.9F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box85
    gunModel[80].setRotationPoint(0F, 0F, 0F);

    gunModel[81].addShapeBox(-2.05F, -3.2F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box86
    gunModel[81].setRotationPoint(0F, 0F, 0F);

    gunModel[82].addShapeBox(-2.05F, -2.83F, -1.25F, 1, 1, 1, 0F, -0.35F, -0.48F, -0.45F, -0.35F, -0.48F, -0.45F, -0.35F, -0.48F, -0.45F, -0.35F, -0.48F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F); // Import Box87
    gunModel[82].setRotationPoint(0F, 0F, 0F);

    gunModel[83].addShapeBox(2F, -2.25F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box88
    gunModel[83].setRotationPoint(0F, 0F, 0F);
    gunModel[83].rotateAngleZ = 1.57079633F;

    gunModel[84].addShapeBox(2F, -1.85F, 0.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box89
    gunModel[84].setRotationPoint(0F, 0F, 0F);
    gunModel[84].rotateAngleZ = 1.57079633F;

    gunModel[85].addShapeBox(-2.05F, -3F, -1.25F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box90
    gunModel[85].setRotationPoint(0F, 0F, 0F);

    gunModel[86].addShapeBox(2F, -2.25F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box91
    gunModel[86].setRotationPoint(0F, 0F, 0F);
    gunModel[86].rotateAngleZ = 1.57079633F;

    gunModel[87].addShapeBox(-2.05F, -3.2F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box92
    gunModel[87].setRotationPoint(0F, 0F, 0F);

    gunModel[88].addShapeBox(2F, -1.85F, -1.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box93
    gunModel[88].setRotationPoint(0F, 0F, 0F);
    gunModel[88].rotateAngleZ = 1.57079633F;

    gunModel[89].addShapeBox(-2.05F, -2.83F, 0.25F, 1, 1, 1, 0F, -0.35F, -0.48F, -0.45F, -0.35F, -0.48F, -0.45F, -0.35F, -0.48F, -0.45F, -0.35F, -0.48F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F, -0.45F); // Import Box94
    gunModel[89].setRotationPoint(0F, 0F, 0F);

    gunModel[90].addShapeBox(0F, -3.1F, 0.35F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box95
    gunModel[90].setRotationPoint(0F, 0F, 0F);

    gunModel[91].addShapeBox(0F, -3.1F, -1.35F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box96
    gunModel[91].setRotationPoint(0F, 0F, 0F);

    gunModel[92].addShapeBox(0.85F, -2.6F, 0.35F, 1, 1, 1, 0F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F); // Import Box97
    gunModel[92].setRotationPoint(0F, 0F, 0F);
    gunModel[92].rotateAngleZ = 0.45378561F;

    gunModel[93].addShapeBox(0.85F, -2.6F, -1.35F, 1, 1, 1, 0F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F); // Import Box99
    gunModel[93].setRotationPoint(0F, 0F, 0F);
    gunModel[93].rotateAngleZ = 0.45378561F;

    gunModel[94].addShapeBox(4F, -3.1F, 0.35F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box100
    gunModel[94].setRotationPoint(0F, 0F, 0F);

    gunModel[95].addShapeBox(3.8F, -3.02F, 0.35F, 1, 1, 1, 0F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F); // Import Box101
    gunModel[95].setRotationPoint(0F, 0F, 0F);

    gunModel[96].addShapeBox(3.76F, -3.18F, 0.35F, 1, 1, 1, 0F, -0.41F, -0.59F, -0.45F, -0.41F, -0.43F, -0.45F, -0.41F, -0.43F, -0.45F, -0.41F, -0.59F, -0.45F, -0.41F, -0.41F, -0.45F, -0.41F, -0.41F, -0.45F, -0.41F, -0.41F, -0.45F, -0.41F, -0.41F, -0.45F); // Import Box102
    gunModel[96].setRotationPoint(0F, 0F, 0F);

    gunModel[97].addShapeBox(4F, -3.1F, -1.35F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box103
    gunModel[97].setRotationPoint(0F, 0F, 0F);

    gunModel[98].addShapeBox(3.8F, -3.02F, -1.35F, 1, 1, 1, 0F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F, -0.37F, -0.43F, -0.45F); // Import Box104
    gunModel[98].setRotationPoint(0F, 0F, 0F);

    gunModel[99].addShapeBox(3.76F, -3.18F, -1.35F, 1, 1, 1, 0F, -0.41F, -0.59F, -0.45F, -0.41F, -0.43F, -0.45F, -0.41F, -0.43F, -0.45F, -0.41F, -0.59F, -0.45F, -0.41F, -0.41F, -0.45F, -0.41F, -0.41F, -0.45F, -0.41F, -0.41F, -0.45F, -0.41F, -0.41F, -0.45F); // Import Box105
    gunModel[99].setRotationPoint(0F, 0F, 0F);

    gunModel[100].addShapeBox(-2.05F, -3F, 0.25F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box106
    gunModel[100].setRotationPoint(0F, 0F, 0F);

    gunModel[101].addShapeBox(2.95F, -3.05F, 0.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box107
    gunModel[101].setRotationPoint(0F, 0F, 0F);

    gunModel[102].addShapeBox(2.95F, -2.75F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box108
    gunModel[102].setRotationPoint(0F, 0F, 0F);

    gunModel[103].addShapeBox(1.9F, 3.1F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box109
    gunModel[103].setRotationPoint(0F, 0F, 0F);
    gunModel[103].rotateAngleZ = 1.57079633F;

    gunModel[104].addShapeBox(1.9F, 2.8F, 0.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box110
    gunModel[104].setRotationPoint(0F, 0F, 0F);
    gunModel[104].rotateAngleZ = 1.57079633F;

    gunModel[105].addShapeBox(5.05F, -2.85F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box111
    gunModel[105].setRotationPoint(0F, 0F, 0F);

    gunModel[106].addShapeBox(1.85F, 5.2F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box112
    gunModel[106].setRotationPoint(0F, 0F, 0F);
    gunModel[106].rotateAngleZ = 1.57079633F;

    gunModel[107].addShapeBox(5.05F, -3F, 0.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box113
    gunModel[107].setRotationPoint(0F, 0F, 0F);

    gunModel[108].addShapeBox(7.65F, -2.85F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box114
    gunModel[108].setRotationPoint(0F, 0F, 0F);

    gunModel[109].addShapeBox(1.85F, 4.9F, 0.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box115
    gunModel[109].setRotationPoint(0F, 0F, 0F);
    gunModel[109].rotateAngleZ = 1.57079633F;

    gunModel[110].addShapeBox(6.65F, -3.2F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box116
    gunModel[110].setRotationPoint(0F, 0F, 0F);

    gunModel[111].addShapeBox(6.65F, -3.35F, 0.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box117
    gunModel[111].setRotationPoint(0F, 0F, 0F);

    gunModel[112].addShapeBox(6.65F, -3.05F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box118
    gunModel[112].setRotationPoint(0F, 0F, 0F);

    gunModel[113].addShapeBox(2.2F, 6.8F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box119
    gunModel[113].setRotationPoint(0F, 0F, 0F);
    gunModel[113].rotateAngleZ = 1.57079633F;

    gunModel[114].addShapeBox(2.2F, 6.5F, 0.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box120
    gunModel[114].setRotationPoint(0F, 0F, 0F);
    gunModel[114].rotateAngleZ = 1.57079633F;

    gunModel[115].addShapeBox(7.65F, -3F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box121
    gunModel[115].setRotationPoint(0F, 0F, 0F);

    gunModel[116].addShapeBox(7.65F, -3.15F, 0.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box122
    gunModel[116].setRotationPoint(0F, 0F, 0F);

    gunModel[117].addShapeBox(2F, 7.8F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box123
    gunModel[117].setRotationPoint(0F, 0F, 0F);
    gunModel[117].rotateAngleZ = 1.57079633F;

    gunModel[118].addShapeBox(2F, 7.5F, 0.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box124
    gunModel[118].setRotationPoint(0F, 0F, 0F);
    gunModel[118].rotateAngleZ = 1.57079633F;

    gunModel[119].addShapeBox(5.05F, -2.7F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box125
    gunModel[119].setRotationPoint(0F, 0F, 0F);

    gunModel[120].addShapeBox(2.2F, -1F, 0.25F, 1, 2, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box130
    gunModel[120].setRotationPoint(0F, 0F, 0F);
    gunModel[120].rotateAngleZ = -0.38397244F;

    gunModel[121].addShapeBox(2.2F, -1F, -1.25F, 1, 2, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box131
    gunModel[121].setRotationPoint(0F, 0F, 0F);
    gunModel[121].rotateAngleZ = -0.38397244F;

    gunModel[122].addShapeBox(4.5F, -2.2F, 0.25F, 1, 2, 1, 0F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F); // Import Box132
    gunModel[122].setRotationPoint(0F, 0F, 0F);
    gunModel[122].rotateAngleZ = -0.41887902F;

    gunModel[123].addShapeBox(4.5F, -2.2F, -1.25F, 1, 2, 1, 0F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F, -0.2F, -0.25F, -0.45F); // Import Box133
    gunModel[123].setRotationPoint(0F, 0F, 0F);
    gunModel[123].rotateAngleZ = -0.41887902F;

    gunModel[124].addShapeBox(2.95F, -2.9F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box134
    gunModel[124].setRotationPoint(0F, 0F, 0F);

    gunModel[125].addShapeBox(2.95F, -3.05F, -1.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box135
    gunModel[125].setRotationPoint(0F, 0F, 0F);

    gunModel[126].addShapeBox(1.9F, 3.1F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box136
    gunModel[126].setRotationPoint(0F, 0F, 0F);
    gunModel[126].rotateAngleZ = 1.57079633F;

    gunModel[127].addShapeBox(2.95F, -2.75F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box137
    gunModel[127].setRotationPoint(0F, 0F, 0F);

    gunModel[128].addShapeBox(1.9F, 2.8F, -1.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box138
    gunModel[128].setRotationPoint(0F, 0F, 0F);
    gunModel[128].rotateAngleZ = 1.57079633F;

    gunModel[129].addShapeBox(5.05F, -2.85F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box139
    gunModel[129].setRotationPoint(0F, 0F, 0F);

    gunModel[130].addShapeBox(5.05F, -3F, -1.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box140
    gunModel[130].setRotationPoint(0F, 0F, 0F);

    gunModel[131].addShapeBox(1.85F, 4.9F, -1.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box141
    gunModel[131].setRotationPoint(0F, 0F, 0F);
    gunModel[131].rotateAngleZ = 1.57079633F;

    gunModel[132].addShapeBox(1.85F, 5.2F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box142
    gunModel[132].setRotationPoint(0F, 0F, 0F);
    gunModel[132].rotateAngleZ = 1.57079633F;

    gunModel[133].addShapeBox(5.05F, -2.7F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box143
    gunModel[133].setRotationPoint(0F, 0F, 0F);

    gunModel[134].addShapeBox(6.65F, -3.2F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box144
    gunModel[134].setRotationPoint(0F, 0F, 0F);

    gunModel[135].addShapeBox(2.2F, 6.8F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box145
    gunModel[135].setRotationPoint(0F, 0F, 0F);
    gunModel[135].rotateAngleZ = 1.57079633F;

    gunModel[136].addShapeBox(6.65F, -3.35F, -1.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box146
    gunModel[136].setRotationPoint(0F, 0F, 0F);

    gunModel[137].addShapeBox(2.2F, 6.5F, -1.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box147
    gunModel[137].setRotationPoint(0F, 0F, 0F);
    gunModel[137].rotateAngleZ = 1.57079633F;

    gunModel[138].addShapeBox(6.65F, -3.05F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box148
    gunModel[138].setRotationPoint(0F, 0F, 0F);

    gunModel[139].addShapeBox(7.65F, -3F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box149
    gunModel[139].setRotationPoint(0F, 0F, 0F);

    gunModel[140].addShapeBox(2F, 7.8F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box150
    gunModel[140].setRotationPoint(0F, 0F, 0F);
    gunModel[140].rotateAngleZ = 1.57079633F;

    gunModel[141].addShapeBox(7.65F, -3.15F, -1.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box151
    gunModel[141].setRotationPoint(0F, 0F, 0F);

    gunModel[142].addShapeBox(2F, 7.5F, -1.35F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box152
    gunModel[142].setRotationPoint(0F, 0F, 0F);
    gunModel[142].rotateAngleZ = 1.57079633F;

    gunModel[143].addShapeBox(7.65F, -2.85F, -1.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box153
    gunModel[143].setRotationPoint(0F, 0F, 0F);

    gunModel[144].addShapeBox(0.95F, 1.3F, 0.25F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box154
    gunModel[144].setRotationPoint(0F, 0F, 0F);

    gunModel[145].addShapeBox(2.2F, 1.5F, 0.25F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box155
    gunModel[145].setRotationPoint(0F, 0F, 0F);

    gunModel[146].addShapeBox(0.95F, 1.15F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box156
    gunModel[146].setRotationPoint(0F, 0F, 0F);

    gunModel[147].addShapeBox(0.95F, 1.45F, 0.25F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box157
    gunModel[147].setRotationPoint(0F, 0F, 0F);

    gunModel[148].addShapeBox(-2.3F, 0.8F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box158
    gunModel[148].setRotationPoint(0F, 0F, 0F);
    gunModel[148].rotateAngleZ = 1.57079633F;

    gunModel[149].addShapeBox(-2.3F, 1.1F, 0.25F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box159
    gunModel[149].setRotationPoint(0F, 0F, 0F);
    gunModel[149].rotateAngleZ = 1.57079633F;

    gunModel[150].addShapeBox(2.2F, 1.35F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box160
    gunModel[150].setRotationPoint(0F, 0F, 0F);

    gunModel[151].addShapeBox(2.2F, 1.65F, 0.25F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box161
    gunModel[151].setRotationPoint(0F, 0F, 0F);

    gunModel[152].addShapeBox(-2.5F, 2.35F, 0.25F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box162
    gunModel[152].setRotationPoint(0F, 0F, 0F);
    gunModel[152].rotateAngleZ = 1.57079633F;

    gunModel[153].addShapeBox(-2.5F, 2.05F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box164
    gunModel[153].setRotationPoint(0F, 0F, 0F);
    gunModel[153].rotateAngleZ = 1.57079633F;

    gunModel[154].addShapeBox(0.95F, 1.3F, -1.25F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box165
    gunModel[154].setRotationPoint(0F, 0F, 0F);

    gunModel[155].addShapeBox(-2.3F, 0.8F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box166
    gunModel[155].setRotationPoint(0F, 0F, 0F);
    gunModel[155].rotateAngleZ = 1.57079633F;

    gunModel[156].addShapeBox(-2.3F, 1.1F, -1.25F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box167
    gunModel[156].setRotationPoint(0F, 0F, 0F);
    gunModel[156].rotateAngleZ = 1.57079633F;

    gunModel[157].addShapeBox(0.95F, 1.15F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box168
    gunModel[157].setRotationPoint(0F, 0F, 0F);

    gunModel[158].addShapeBox(0.95F, 1.45F, -1.25F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box169
    gunModel[158].setRotationPoint(0F, 0F, 0F);

    gunModel[159].addShapeBox(2.2F, 1.5F, -1.25F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F, -0.4F, -0.4F, -0.45F); // Import Box170
    gunModel[159].setRotationPoint(0F, 0F, 0F);

    gunModel[160].addShapeBox(-2.5F, 2.35F, -1.25F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box171
    gunModel[160].setRotationPoint(0F, 0F, 0F);
    gunModel[160].rotateAngleZ = 1.57079633F;

    gunModel[161].addShapeBox(-2.5F, 2.05F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box172
    gunModel[161].setRotationPoint(0F, 0F, 0F);
    gunModel[161].rotateAngleZ = 1.57079633F;

    gunModel[162].addShapeBox(2.2F, 1.35F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box173
    gunModel[162].setRotationPoint(0F, 0F, 0F);

    gunModel[163].addShapeBox(2.2F, 1.65F, -1.25F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F, -0.45F, -0.5F, -0.45F); // Import Box174
    gunModel[163].setRotationPoint(0F, 0F, 0F);

    gunModel[164].addShapeBox(3.6F, -1.75F, 0.25F, 1, 1, 1, 0F, -0.43F, -0.05F, -0.45F, -0.43F, -0.05F, -0.45F, -0.43F, -0.05F, -0.45F, -0.43F, -0.05F, -0.45F, -0.43F, -0.05F, -0.45F, -0.43F, 0.1F, -0.45F, -0.43F, 0.1F, -0.45F, -0.43F, -0.05F, -0.45F); // Import Box175
    gunModel[164].setRotationPoint(0F, 0F, 0F);
    gunModel[164].rotateAngleZ = -0.38397244F;

    gunModel[165].addShapeBox(3F, 1.1F, 0.25F, 1, 1, 1, 0F, -0.43F, -0.25F, -0.45F, -0.43F, -0.25F, -0.45F, -0.43F, -0.25F, -0.45F, -0.43F, -0.25F, -0.45F, -0.43F, 0.05F, -0.45F, -0.43F, -0.1F, -0.45F, -0.43F, -0.1F, -0.45F, -0.43F, 0.05F, -0.45F); // Import Box176
    gunModel[165].setRotationPoint(0F, 0F, 0F);
    gunModel[165].rotateAngleZ = 0.38397244F;

    gunModel[166].addShapeBox(3.6F, -1.75F, -1.25F, 1, 1, 1, 0F, -0.43F, -0.05F, -0.45F, -0.43F, -0.05F, -0.45F, -0.43F, -0.05F, -0.45F, -0.43F, -0.05F, -0.45F, -0.43F, -0.05F, -0.45F, -0.43F, 0.1F, -0.45F, -0.43F, 0.1F, -0.45F, -0.43F, -0.05F, -0.45F); // Import Box177
    gunModel[166].setRotationPoint(0F, 0F, 0F);
    gunModel[166].rotateAngleZ = -0.38397244F;

    gunModel[167].addShapeBox(3F, 1.1F, -1.25F, 1, 1, 1, 0F, -0.43F, -0.25F, -0.45F, -0.43F, -0.25F, -0.45F, -0.43F, -0.25F, -0.45F, -0.43F, -0.25F, -0.45F, -0.43F, 0.05F, -0.45F, -0.43F, -0.1F, -0.45F, -0.43F, -0.1F, -0.45F, -0.43F, 0.05F, -0.45F); // Import Box178
    gunModel[167].setRotationPoint(0F, 0F, 0F);
    gunModel[167].rotateAngleZ = 0.38397244F;

    gunModel[168].addShapeBox(8.2F, -2F, 0.25F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box179
    gunModel[168].setRotationPoint(0F, 0F, 0F);

    gunModel[169].addShapeBox(7.2F, -2F, 0.25F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box180
    gunModel[169].setRotationPoint(0F, 0F, 0F);

    gunModel[170].addShapeBox(7.2F, -2F, -1.25F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box181
    gunModel[170].setRotationPoint(0F, 0F, 0F);

    gunModel[171].addShapeBox(8.2F, -2F, -1.25F, 1, 1, 1, 0F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F, -0.35F, -0.35F, -0.45F); // Import Box182
    gunModel[171].setRotationPoint(0F, 0F, 0F);

    gunModel[172].addShapeBox(8.2F, -2.2F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box183
    gunModel[172].setRotationPoint(0F, 0F, 0F);

    gunModel[173].addShapeBox(7.2F, -2.2F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box184
    gunModel[173].setRotationPoint(0F, 0F, 0F);

    gunModel[174].addShapeBox(7.2F, -2.2F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box185
    gunModel[174].setRotationPoint(0F, 0F, 0F);

    gunModel[175].addShapeBox(8.2F, -2.2F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box186
    gunModel[175].setRotationPoint(0F, 0F, 0F);

    gunModel[176].addShapeBox(7.2F, -1.8F, 0.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box187
    gunModel[176].setRotationPoint(0F, 0F, 0F);

    gunModel[177].addShapeBox(8.2F, -1.8F, 0.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box188
    gunModel[177].setRotationPoint(0F, 0F, 0F);

    gunModel[178].addShapeBox(7.2F, -1.8F, -1.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box189
    gunModel[178].setRotationPoint(0F, 0F, 0F);

    gunModel[179].addShapeBox(8.2F, -1.8F, -1.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box190
    gunModel[179].setRotationPoint(0F, 0F, 0F);

    gunModel[180].addShapeBox(1F, 8.4F, 0.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box191
    gunModel[180].setRotationPoint(0F, 0F, 0F);
    gunModel[180].rotateAngleZ = 1.57079633F;

    gunModel[181].addShapeBox(1F, 7.4F, 0.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box192
    gunModel[181].setRotationPoint(0F, 0F, 0F);
    gunModel[181].rotateAngleZ = 1.57079633F;

    gunModel[182].addShapeBox(1F, 7.4F, -1.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box193
    gunModel[182].setRotationPoint(0F, 0F, 0F);
    gunModel[182].rotateAngleZ = 1.57079633F;

    gunModel[183].addShapeBox(1F, 8.4F, -1.25F, 1, 1, 1, 0F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F); // Import Box194
    gunModel[183].setRotationPoint(0F, 0F, 0F);
    gunModel[183].rotateAngleZ = 1.57079633F;

    gunModel[184].addShapeBox(1F, 8F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box195
    gunModel[184].setRotationPoint(0F, 0F, 0F);
    gunModel[184].rotateAngleZ = 1.57079633F;

    gunModel[185].addShapeBox(1F, 7F, 0.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box196
    gunModel[185].setRotationPoint(0F, 0F, 0F);
    gunModel[185].rotateAngleZ = 1.57079633F;

    gunModel[186].addShapeBox(1F, 8F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box197
    gunModel[186].setRotationPoint(0F, 0F, 0F);
    gunModel[186].rotateAngleZ = 1.57079633F;

    gunModel[187].addShapeBox(1F, 7F, -1.25F, 1, 1, 1, 0F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.45F, -0.48F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F, -0.35F, -0.45F, -0.45F); // Import Box198
    gunModel[187].setRotationPoint(0F, 0F, 0F);
    gunModel[187].rotateAngleZ = 1.57079633F;

    gunModel[188].addShapeBox(5.1F, -2F, 0.3F, 2, 1, 1, 0F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F); // Import Box199
    gunModel[188].setRotationPoint(0F, 0F, 0F);

    gunModel[189].addShapeBox(5.1F, -2F, -1.3F, 2, 1, 1, 0F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F, -0.4F, -0.25F, -0.4F); // Import Box200
    gunModel[189].setRotationPoint(0F, 0F, 0F);


    ammoModel = new ModelRendererTurbo[2];
    ammoModel[0] = new ModelRendererTurbo(this, 121, 97, textureX, textureY); // Import Box81
    ammoModel[1] = new ModelRendererTurbo(this, 1, 105, textureX, textureY); // Import Box83

    ammoModel[0].addShapeBox(4.2F, -0.5F, -1F, 1, 4, 2, 0F, 0.2F, 0F, -0.4F, 0.2F, 0F, -0.4F, 0.2F, 0F, -0.4F, 0.2F, 0F, -0.4F, 0.2F, 0F, -0.4F, 0.2F, 0F, -0.4F, 0.2F, 0F, -0.4F, 0.2F, 0F, -0.4F); // Import Box81
    ammoModel[0].setRotationPoint(0F, 0F, 0F);
    ammoModel[0].rotateAngleZ = -0.38397244F;

    ammoModel[1].addShapeBox(4.2F, 3.05F, -1F, 1, 1, 2, 0F, 0.25F, -0.45F, -0.35F, 0.25F, -0.45F, -0.35F, 0.25F, -0.45F, -0.35F, 0.25F, -0.45F, -0.35F, 0.25F, -0.45F, -0.35F, 0.25F, -0.45F, -0.35F, 0.25F, -0.45F, -0.35F, 0.25F, -0.45F, -0.35F); // Import Box83
    ammoModel[1].setRotationPoint(0F, 0F, 0F);
    ammoModel[1].rotateAngleZ = -0.38397244F;


    slideModel = new ModelRendererTurbo[3];
    slideModel[0] = new ModelRendererTurbo(this, 9, 105, textureX, textureY); // Import Box127
    slideModel[1] = new ModelRendererTurbo(this, 17, 105, textureX, textureY); // Import Box128
    slideModel[2] = new ModelRendererTurbo(this, 25, 105, textureX, textureY); // Import Box129

    slideModel[0].addShapeBox(8.5F, -2.65F, 0.4F, 1, 1, 1, 0F, -0.4F, -0.4F, -0.35F, -0.4F, -0.4F, -0.35F, -0.4F, -0.4F, -0.35F, -0.4F, -0.4F, -0.35F, -0.4F, -0.4F, -0.35F, -0.4F, -0.4F, -0.35F, -0.4F, -0.4F, -0.35F, -0.4F, -0.4F, -0.35F); // Import Box127
    slideModel[0].setRotationPoint(0F, 0F, 0F);

    slideModel[1].addShapeBox(8F, -2.65F, 0.35F, 1, 1, 1, 0F, -0.1F, -0.4F, -0.45F, -0.1F, -0.4F, -0.45F, -0.1F, -0.4F, -0.45F, -0.1F, -0.4F, -0.45F, -0.1F, -0.4F, -0.45F, -0.1F, -0.4F, -0.45F, -0.1F, -0.4F, -0.45F, -0.1F, -0.4F, -0.45F); // Import Box128
    slideModel[1].setRotationPoint(0F, 0F, 0F);

    slideModel[2].addShapeBox(7.5F, -2.6F, 0.35F, 1, 1, 1, 0F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F, -0.4F, -0.45F, -0.45F); // Import Box129
    slideModel[2].setRotationPoint(0F, 0F, 0F);



    barrelAttachPoint = new Vector3f(11.2F /16F, 1.4F /16F, 0F /16F);

    scopeAttachPoint = new Vector3f(0F /16F, 3F /16F, 0F /16F);

    gripAttachPoint = new Vector3f(8.25 /16F, 0.75F /16F, 0F /16F);


    animationType = EnumAnimationType.BOTTOM_CLIP;

    gunSlideDistance *= 0.5f;

    translateAll(0F, 0F, 0F);


    flipAll();
  }
}