package com.flansmod.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class WorldRenderer {

  public Tessellator tessellator;

  public WorldRenderer() {

  }

  public void startDrawingQuads() {
    tessellator = Tessellator.getInstance();
    tessellator.getWorldRenderer().begin(7, DefaultVertexFormats.POSITION_TEX);
  }

  public void addVertexWithUV(double x, double y, double z, double u, double v) {
    tessellator.getWorldRenderer().pos(x, y, z).tex(u, v).endVertex();
  }

  public void draw() {
    tessellator.draw();
  }

  public void addVertex(double x, double y, double z) {
    tessellator.getWorldRenderer().pos(x, y, z).endVertex();
  }
}
