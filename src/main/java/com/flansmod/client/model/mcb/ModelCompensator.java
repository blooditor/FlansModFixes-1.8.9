package com.flansmod.client.model.mcb;

import com.flansmod.client.model.ModelAttachment;
import com.flansmod.client.tmt.ModelRendererTurbo;

public class ModelCompensator extends ModelAttachment {

  public ModelCompensator() {
    int textureX = 16;
    int textureY = 4;

    attachmentModel = new ModelRendererTurbo[1];

    attachmentModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
    attachmentModel[0].addBox(0F, -0.8F, -0.8F, 2.2f, 1.6f, 1.6f);
  }
}
