package com.flansmod.client.model;

import com.flansmod.client.FlansModClient;
import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.WorldRenderer;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.vector.Vector3f;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class InstantBulletRenderer {

  private static TextureManager textureManager;
  private static ArrayList<InstantShotTrail> trails = new ArrayList<InstantShotTrail>();
  public static ArrayList<BulletHole> bulletHoles = new ArrayList<>();

  public static void AddTrail(InstantShotTrail trail) {
    trails.add(trail);
  }

  public static void RenderAllTrails(float partialTicks) {
    for (InstantShotTrail trail : trails) {
      trail.Render(partialTicks);
    }
    for (Object o : Minecraft.getMinecraft().theWorld.loadedEntityList) {
      if (o instanceof EntityBullet) {
        EntityBullet bullet = (EntityBullet) o;
        //    renderBulletEntityTrail(bullet, partialTicks);
      }
    }

    /*     EntityBullet test = new EntityBullet(Minecraft.getMinecraft().theWorld);
        test.setPosition(0, 70, 0);
        test.motionX = 1;
        GunType type = GunType.getGun("l96");
        test.type = (BulletType) type.ammo.get(0);
        ItemStack gunstack = new ItemStack(test.type.item);
        test.damage = type.getDamage(gunstack);
        float speed = 4;
        test.setArrowHeading(1,0,0,0,speed);
        test.ticksExisted = 10;


        renderBulletEntityTrail(test, partialTicks);
*/
    //	renderBulletHoles(partialTicks);

    // GlStateManager.enableLighting();
    GlStateManager.enableFog();
    GlStateManager.enableBlend();
    int i = 0;
    for (BulletHole h : bulletHoles) {
      h.render(partialTicks, i++);
    }

    GlStateManager.disableBlend();
    GlStateManager.disableFog();
    //   GlStateManager.disableLighting();
  }

  private static void renderBulletEntityTrail(EntityBullet bullet, float partialTicks) {
    if (textureManager == null) {
      textureManager = Minecraft.getMinecraft().renderEngine;
    }

    BulletType type = bullet.type;

    Vector3f origin = new Vector3f(bullet.getPositionVector());
    float width = 0.05f; // type.trailWidth
    float damage = bullet.damage * Math
        .max(type.damageVsDriveable, Math.max(type.damageVsPlane, type.damageVsLiving));
    width = 0.01f + damage * 0.002f;
    double length = 10.0f;

    Vector3f dPos = new Vector3f(bullet.motionX, bullet.motionY, bullet.motionZ);
    double bulletSpeed = dPos.length();
    length = bulletSpeed - 0.5f;

    if (bulletSpeed < 10 && bullet.ticksExisted < 8) {
      length *= bullet.ticksExisted / 8f;
    } else if (bullet.ticksExisted < 2) {
      length *= bullet.ticksExisted / 2f;
    }

    if (length < 0) {
      return;
    }

    textureManager.bindTexture(FlansModResourceHandler.getTrailTexture(type));

    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

    GlStateManager.pushMatrix();

    //Get the camera frustrum for clipping
    Entity camera = Minecraft.getMinecraft().getRenderViewEntity();
    double x = camera.lastTickPosX + (camera.posX - camera.lastTickPosX) * partialTicks;
    double y = camera.lastTickPosY + (camera.posY - camera.lastTickPosY) * partialTicks;
    double z = camera.lastTickPosZ + (camera.posZ - camera.lastTickPosZ) * partialTicks;

    //

    GL11.glTranslated(-x, -y, -z);
    double parametric = partialTicks * length;

    double distFromBullet = 0.5f;

    dPos.normalise();

    double startParametric = (-distFromBullet - 1) * parametric;
    Vector3f startPos = new Vector3f(origin.x + dPos.x * startParametric,
        origin.y + dPos.y * startParametric, origin.z + dPos.z * startParametric);
    double endParametric = (-distFromBullet) * parametric;
    Vector3f endPos = new Vector3f(origin.x + dPos.x * endParametric,
        origin.y + dPos.y * endParametric, origin.z + dPos.z * endParametric);

      /*  Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = FlansModClient.getWorldRenderer();

        dPos.normalise();

        EntityPlayer thePlayer = Minecraft.getMinecraft().thePlayer;
        Vector3f vectorToPlayer = new Vector3f(thePlayer.posX - hitPos.x, thePlayer.posY - hitPos.y, thePlayer.posZ - hitPos.z);

        vectorToPlayer.normalise();

        Vector3f trailTangent = Vector3f.cross(dPos, vectorToPlayer, null);
        trailTangent.normalise();
        trailTangent.scale(-width * 0.5f);

        Vector3f normal = Vector3f.cross(trailTangent, dPos, null);
        normal.normalise();

        GlStateManager.enableRescaleNormal();
        GL11.glNormal3f(normal.x, normal.y, normal.z);

        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV(startPos.x + trailTangent.x, startPos.y + trailTangent.y, startPos.z + trailTangent.z, 0.0f, 0.0f);
        worldrenderer.addVertexWithUV(startPos.x - trailTangent.x, startPos.y - trailTangent.y, startPos.z - trailTangent.z, 0.0f, 1.0f);
        worldrenderer.addVertexWithUV(endPos.x - trailTangent.x, endPos.y - trailTangent.y, endPos.z - trailTangent.z, 1.0f, 1.0f);
        worldrenderer.addVertexWithUV(endPos.x + trailTangent.x, endPos.y + trailTangent.y, endPos.z + trailTangent.z, 1.0f, 0.0f);
        tessellator.draw();

        //

        GL11.glTranslated(-x, -y, -z);

        double parametric = (double) partialTicks;

        dPos.normalise();

        //double startParametric = 0;
        double startParametric = parametric;
        Vector3f startPos = new Vector3f(origin.x + dPos.x * startParametric, origin.y + dPos.y * startParametric, origin.z + dPos.z * startParametric);
        double endParametric = parametric + length;
        Vector3f endPos = new Vector3f(origin.x + dPos.x * endParametric, origin.y + dPos.y * endParametric, origin.z + dPos.z * endParametric);
*/
    Tessellator tessellator = Tessellator.getInstance();
    WorldRenderer worldrenderer = FlansModClient.getWorldRenderer();

    dPos.normalise();

    EntityPlayer thePlayer = Minecraft.getMinecraft().thePlayer;
    //Vector3f vectorToPlayer = new Vector3f(thePlayer.posX - hitPos.x, thePlayer.posY - hitPos.y, thePlayer.posZ - hitPos.z);
    Vector3f vectorToPlayer = new Vector3f(thePlayer.posX - origin.x,
        (thePlayer.posY + thePlayer.eyeHeight) - origin.y, thePlayer.posZ - origin.z);

    vectorToPlayer.normalise();

    Vector3f trailTangent = Vector3f.cross(dPos, vectorToPlayer, null);
    //    Vector3f.add(trailTangent, new Vector3f(1, 1, 1), trailTangent);
    trailTangent.normalise();
    trailTangent.scale(-width * 0.5f);

    Vector3f normal = Vector3f.cross(trailTangent, dPos, null);
    if (normal.length() != 0) {
      normal.normalise();
    }

    GlStateManager.enableRescaleNormal();
    GL11.glNormal3f(normal.x, normal.y, normal.z);

    worldrenderer.startDrawingQuads();
    worldrenderer.addVertexWithUV(startPos.x + trailTangent.x, startPos.y + trailTangent.y,
        startPos.z + trailTangent.z, 0.0f, 0.0f);
    worldrenderer.addVertexWithUV(startPos.x - trailTangent.x, startPos.y - trailTangent.y,
        startPos.z - trailTangent.z, 0.0f, 1.0f);
    worldrenderer.addVertexWithUV(endPos.x - trailTangent.x, endPos.y - trailTangent.y,
        endPos.z - trailTangent.z, 1.0f, 1.0f);
    worldrenderer.addVertexWithUV(endPos.x + trailTangent.x, endPos.y + trailTangent.y,
        endPos.z + trailTangent.z, 1.0f, 0.0f);
    tessellator.draw();

    GlStateManager.disableRescaleNormal();

    GlStateManager.popMatrix();
  }

  public static void UpdateAllTrails() {
    for (int i = trails.size() - 1; i >= 0; i--) {
      if (trails.get(i).Update()) {
        trails.remove(i);
      }
    }
    for (int i = bulletHoles.size() - 1; i >= 0; i--) {
      if (bulletHoles.get(i).Update()) {
        bulletHoles.remove(i);
      }
    }
  }

  public static class InstantShotTrail {

    private Vector3f origin;
    private Vector3f hitPos;
    private float width;
    private float length;
    private float distanceToTarget;
    private float bulletSpeed;
    private int ticksExisted;

    private ResourceLocation texture;

    public InstantShotTrail(Vector3f origin, Vector3f hitPos, BulletType type) {
      this.ticksExisted = 0;
      this.bulletSpeed = 10.0f;
      this.origin = origin;
      this.hitPos = hitPos;
      this.width = 0.05f; // type.trailWidth
      this.length = 10.0f;
      this.texture = FlansModResourceHandler.getTrailTexture(type);

      Vector3f dPos = Vector3f.sub(hitPos, origin, null);
      this.distanceToTarget = dPos.length();

      if (Math.abs(distanceToTarget) > 300.0f) {
        distanceToTarget = 300.0f;
      }
    }

    // Return true if this needs deleting
    public boolean Update() {
      ticksExisted++;
      return (ticksExisted) * bulletSpeed >= distanceToTarget - length;
    }

    public void Render(float partialTicks) {
      //Make sure we actually have the renderEngine
      if (textureManager == null) {
        textureManager = Minecraft.getMinecraft().renderEngine;
      }

      textureManager.bindTexture(texture);

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

      GlStateManager.pushMatrix();

      //Get the camera frustrum for clipping
      Entity camera = Minecraft.getMinecraft().getRenderViewEntity();
      double x = camera.lastTickPosX + (camera.posX - camera.lastTickPosX) * partialTicks;
      double y = camera.lastTickPosY + (camera.posY - camera.lastTickPosY) * partialTicks;
      double z = camera.lastTickPosZ + (camera.posZ - camera.lastTickPosZ) * partialTicks;

      GL11.glTranslated(-x, -y, -z);

      double parametric = ((double) (ticksExisted) + partialTicks) * bulletSpeed;

      Vector3f dPos = Vector3f.sub(hitPos, origin, null);
      dPos.normalise();

      double startParametric = parametric - length * 0.5f;
      Vector3f startPos = new Vector3f(origin.x + dPos.x * startParametric,
          origin.y + dPos.y * startParametric, origin.z + dPos.z * startParametric);
      double endParametric = parametric + length * 0.5f;
      Vector3f endPos = new Vector3f(origin.x + dPos.x * endParametric,
          origin.y + dPos.y * endParametric, origin.z + dPos.z * endParametric);

      Tessellator tessellator = Tessellator.getInstance();
      WorldRenderer worldrenderer = FlansModClient.getWorldRenderer();

      dPos.normalise();

      EntityPlayer thePlayer = Minecraft.getMinecraft().thePlayer;
      Vector3f vectorToPlayer = new Vector3f(thePlayer.posX - hitPos.x, thePlayer.posY - hitPos.y,
          thePlayer.posZ - hitPos.z);

      vectorToPlayer.normalise();

      Vector3f trailTangent = Vector3f.cross(dPos, vectorToPlayer, null);
      trailTangent.normalise();
      trailTangent.scale(-width * 0.5f);

      Vector3f normal = Vector3f.cross(trailTangent, dPos, null);
      normal.normalise();

      GlStateManager.enableRescaleNormal();
      GL11.glNormal3f(normal.x, normal.y, normal.z);

      worldrenderer.startDrawingQuads();
      worldrenderer.addVertexWithUV(startPos.x + trailTangent.x, startPos.y + trailTangent.y,
          startPos.z + trailTangent.z, 0.0f, 0.0f);
      worldrenderer.addVertexWithUV(startPos.x - trailTangent.x, startPos.y - trailTangent.y,
          startPos.z - trailTangent.z, 0.0f, 1.0f);
      worldrenderer.addVertexWithUV(endPos.x - trailTangent.x, endPos.y - trailTangent.y,
          endPos.z - trailTangent.z, 1.0f, 1.0f);
      worldrenderer.addVertexWithUV(endPos.x + trailTangent.x, endPos.y + trailTangent.y,
          endPos.z + trailTangent.z, 1.0f, 0.0f);
      tessellator.draw();

      GlStateManager.disableRescaleNormal();

      GlStateManager.popMatrix();
    }
  }

  public static class BloodSplatter extends BulletHole {

    public BloodSplatter(BlockPos blockPos, Vector3f hit, EnumFacing side, int size) {
      this.blockPos = blockPos;
      this.hit = hit;
      this.side = side;
      this.texture = new ResourceLocation("flansmod", "skins/blood_" + size + ".png");

    }
  }

  public static class BulletHole {

    BlockPos blockPos;
    Vector3f hit;
    EnumFacing side;
    int ticksExisted;

    int LIFETIME = 600;
    ResourceLocation texture;

    public BulletHole(BlockPos blockPos, Vector3f hit, EnumFacing side, int size) {
      this.blockPos = blockPos;
      this.hit = hit;
      this.side = side;
      texture = new ResourceLocation("flansmod", "skins/bulletHole_" + size + ".png");
    }

    public BulletHole() {

    }

    public void render(float partialTicks, int index) {

      World w = Minecraft.getMinecraft().theWorld;
      IBlockState blockState = w.getBlockState(blockPos);
      Block block = blockState.getBlock();
      if (!canBlockRenderHole(0, 0, 0, blockPos)) {
        return;
      }
      IBlockState opp = w.getBlockState(blockPos.offset(side));
      if (opp.getBlock() == Blocks.water || opp.getBlock() == Blocks.flowing_water) {
        return;
      }

      if (textureManager == null) {
        textureManager = Minecraft.getMinecraft().renderEngine;
      }

      textureManager.bindTexture(texture);

      float fadeOut = 100;
      GlStateManager.color(1.0F, 1.0F, 1.0F,
          this.ticksExisted > this.LIFETIME - fadeOut ? (LIFETIME - this.ticksExisted) / fadeOut
              : 1f);

      GlStateManager.pushMatrix();

      //Get the camera frustrum for clipping
      Entity camera = Minecraft.getMinecraft().getRenderViewEntity();
      double x = camera.lastTickPosX + (camera.posX - camera.lastTickPosX) * partialTicks;
      double y = camera.lastTickPosY + (camera.posY - camera.lastTickPosY) * partialTicks;
      double z = camera.lastTickPosZ + (camera.posZ - camera.lastTickPosZ) * partialTicks;

      x = -x + blockPos.getX();
      y = -y + blockPos.getY();
      z = -z + blockPos.getZ();
      GlStateManager.translate(x, y, z);

      //   GlStateManager.translate(-blockPos.getX(), -blockPos.getY(), -blockPos.getZ());

      GlStateManager.enableRescaleNormal();

      Tessellator tessellator = Tessellator.getInstance();
      WorldRenderer worldrenderer = FlansModClient.getWorldRenderer();

      worldrenderer.startDrawingQuads();
      GL11.glEnable(GL11.GL_BLEND);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

      double c = 0.01 + 0.00001 * index;

      double x0;
      double x1;
      double y0;
      double y1;
      double z0;
      double z1;

      Vec3 relHit = new Vec3((double) hit.x - blockPos.getX(), (double) hit.y - blockPos.getY(),
          (double) hit.z - blockPos.getZ());
      x0 = relHit.xCoord - 0.5;
      x1 = relHit.xCoord + 0.5;
      y0 = relHit.yCoord - 0.5;
      y1 = relHit.yCoord + 0.5;
      z0 = relHit.zCoord - 0.5;
      z1 = relHit.zCoord + 0.5;

      double tx0 = 0;
      double tx1 = 1;
      double ty0 = 0;
      double ty1 = 1;

      switch (side) {
        case NORTH:
          if (x0 < 0 && !canBlockRenderHole(-1, 0, 0, blockPos)) {
            tx0 = -x0;
            x0 = 0;
          }
          if (x1 > 1 && !canBlockRenderHole(1, 0, 0, blockPos)) {
            tx1 = 1 - x0;
            x1 = 1;
          }

          if (y0 < 0 && !canBlockRenderHole(0, -1, 0, blockPos)) {
            ty0 = -y0;
            y0 = 0;
          }
          if (y1 > 1 && !canBlockRenderHole(0, 1, 0, blockPos)) {
            ty1 = 1 - y0;
            y1 = 1;
          }
          worldrenderer.addVertexWithUV(x0, y0, -c, tx0, ty0);
          worldrenderer.addVertexWithUV(x0, y1, -c, tx0, ty1);
          worldrenderer.addVertexWithUV(x1, y1, -c, tx1, ty1);
          worldrenderer.addVertexWithUV(x1, y0, -c, tx1, ty0);
          break;
        case SOUTH:
          if (x0 < 0 && !canBlockRenderHole(-1, 0, 0, blockPos)) {
            tx1 = 1 + x0;
            x0 = 0;
          }
          if (x1 > 1 && !canBlockRenderHole(1, 0, 0, blockPos)) {
            tx0 = x0;
            x1 = 1;
          }

          if (y0 < 0 && !canBlockRenderHole(0, -1, 0, blockPos)) {
            ty0 = -y0;
            y0 = 0;
          }
          if (y1 > 1 && !canBlockRenderHole(0, 1, 0, blockPos)) {
            ty1 = 1 - y0;
            y1 = 1;
          }
          worldrenderer.addVertexWithUV(x1, y0, 1 + c, tx0, ty0);
          worldrenderer.addVertexWithUV(x1, y1, 1 + c, tx0, ty1);
          worldrenderer.addVertexWithUV(x0, y1, 1 + c, tx1, ty1);
          worldrenderer.addVertexWithUV(x0, y0, 1 + c, tx1, ty0);
          break;
        case EAST:
          if (z0 < 0 && !canBlockRenderHole(0, 0, -1, blockPos)) {
            tx0 = -z0;
            z0 = 0;
          }
          if (z1 > 1 && !canBlockRenderHole(0, 0, 1, blockPos)) {
            tx1 = 1 - z0;
            z1 = 1;
          }

          if (y0 < 0 && !canBlockRenderHole(0, -1, 0, blockPos)) {
            ty0 = -y0;
            y0 = 0;
          }
          if (y1 > 1 && !canBlockRenderHole(0, 1, 0, blockPos)) {
            ty1 = 1 - y0;
            y1 = 1;
          }
          worldrenderer.addVertexWithUV(1 + c, y0, z0, tx0, ty0);
          worldrenderer.addVertexWithUV(1 + c, y1, z0, tx0, ty1);
          worldrenderer.addVertexWithUV(1 + c, y1, z1, tx1, ty1);
          worldrenderer.addVertexWithUV(1 + c, y0, z1, tx1, ty0);
          break;
        case WEST:
          if (z0 < 0 && !canBlockRenderHole(0, 0, -1, blockPos)) {
            tx1 = 1 + z0;
            z0 = 0;
          }
          if (z1 > 1 && !canBlockRenderHole(0, 0, 1, blockPos)) {
            tx0 = z0;
            z1 = 1;
          }

          if (y0 < 0 && !canBlockRenderHole(0, -1, 0, blockPos)) {
            ty0 = -y0;
            y0 = 0;
          }
          if (y1 > 1 && !canBlockRenderHole(0, 1, 0, blockPos)) {
            ty1 = 1 - y0;
            y1 = 1;
          }
          worldrenderer.addVertexWithUV(-c, y0, z1, tx0, ty0);
          worldrenderer.addVertexWithUV(-c, y1, z1, tx0, ty1);
          worldrenderer.addVertexWithUV(-c, y1, z0, tx1, ty1);
          worldrenderer.addVertexWithUV(-c, y0, z0, tx1, ty0);
          break;
        case UP:
          if (x0 < 0 && !canBlockRenderHole(-1, 0, 0, blockPos)) {
            tx0 = -x0;
            x0 = 0;
          }
          if (x1 > 1 && !canBlockRenderHole(1, 0, 0, blockPos)) {
            tx1 = 1 - x0;
            x1 = 1;
          }

          if (z0 < 0 && !canBlockRenderHole(0, 0, -1, blockPos)) {
            ty0 = -z0;
            z0 = 0;
          }

          if (z1 > 1 && !canBlockRenderHole(0, 0, 1, blockPos)) {
            ty1 = 1 - z0;
            z1 = 1;
          }
          worldrenderer.addVertexWithUV(x0, 1 + c, z0, tx0, ty0);
          worldrenderer.addVertexWithUV(x0, 1 + c, z1, tx0, ty1);
          worldrenderer.addVertexWithUV(x1, 1 + c, z1, tx1, ty1);
          worldrenderer.addVertexWithUV(x1, 1 + c, z0, tx1, ty0);
          break;
        case DOWN:
          if (x0 < 0 && !canBlockRenderHole(-1, 0, 0, blockPos)) {
            tx1 = 1 + x0;
            x0 = 0;
          }
          if (x1 > 1 && !canBlockRenderHole(1, 0, 0, blockPos)) {
            tx0 = x0;
            x1 = 1;
          }

          if (z0 < 0 && !canBlockRenderHole(0, 0, -1, blockPos)) {
            ty0 = -z0;
            z0 = 0;
          }
          if (z1 > 1 && !canBlockRenderHole(0, 0, 1, blockPos)) {
            ty1 = 1 - z0;
            z1 = 1;
          }
          worldrenderer.addVertexWithUV(x1, -c, z0, tx0, ty0);
          worldrenderer.addVertexWithUV(x1, -c, z1, tx0, ty1);
          worldrenderer.addVertexWithUV(x0, -c, z1, tx1, ty1);
          worldrenderer.addVertexWithUV(x0, -c, z0, tx1, ty0);
          break;
        default:

      }
      tessellator.draw();

      GL11.glDisable(GL11.GL_BLEND);

      GlStateManager.disableRescaleNormal();

      GlStateManager.popMatrix();
    }

    private boolean canBlockRenderHole(int x, int y, int z, BlockPos blockPos) {

      Block block = Minecraft.getMinecraft().theWorld.getBlockState(blockPos.add(x, y, z))
          .getBlock();
      return block != Blocks.air && block.isFullBlock() && block.isOpaqueCube() && !block
          .isTranslucent();
    }

    public boolean Update() {
      return ++ticksExisted >= LIFETIME;
    }
  }
}
