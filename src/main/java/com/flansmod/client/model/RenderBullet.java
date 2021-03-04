package com.flansmod.client.model;

import com.flansmod.client.FlansModClient;
import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.WorldRenderer;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EnumWeaponType;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBullet extends Render {

  public RenderBullet(RenderManager renderManager) {
    super(renderManager);
    shadowSize = 0.5F;
  }

  public void render(EntityBullet bullet, double d, double d1, double d2, float f, float f1) {
    if (bullet.owner == Minecraft.getMinecraft().thePlayer && bullet.ticksExisted < 2 || bullet.type == null) {
      return;
		}
    bindEntityTexture(bullet);
    GL11.glPushMatrix();
    GL11.glTranslatef((float) d, (float) d1, (float) d2);
    GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(
        90F - bullet.prevRotationPitch - (bullet.rotationPitch - bullet.prevRotationPitch) * f1,
        1.0F, 0.0F, 0.0F);
    ModelBase model = bullet.type.model;

		if (model != null) {
		  GlStateManager.scale(bullet.type.modelScale, bullet.type.modelScale, bullet.type.modelScale);
			model.render(bullet, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		}

    GL11.glPopMatrix();
    GL11.glPushMatrix();
    //

    boolean shouldRenderTrail =
        bullet.renderTrail && bullet.type.weaponType != EnumWeaponType.MISSILE
            && bullet.type.ammoType != BulletType.AmmoType.MISSILE
            && bullet.type.weaponType != EnumWeaponType.BOMB;
    if (shouldRenderTrail) {

      GlStateManager.disableLighting();
      Minecraft.getMinecraft().entityRenderer.disableLightmap();
      renderTrail(bullet, d, d1, d2, f1);
      Minecraft.getMinecraft().entityRenderer.enableLightmap();
      GlStateManager.enableLighting();
    }

    GL11.glPopMatrix();
  }

  private void renderTrail(EntityBullet bullet, double d, double d1, double d2, float f1) {

    float width = 0.05f; // type.trailWidth
    float damage = bullet.damage * Math.max(bullet.type.damageVsDriveable * 0.3f,
        Math.max(bullet.type.damageVsPlane * 0.3f, bullet.type.damageVsLiving));
    damage = Math.min(500, damage);
    width = 0.013f + damage * 0.0008f;

    bindTexture(FlansModResourceHandler.getTrailTexture(bullet.type));
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

    //	GlStateManager.pushMatrix();

    Vector3f motion = new Vector3f(bullet.motionX, bullet.motionY, bullet.motionZ);

    double bulletSpeed = motion.length();
    double length = 1;
    length = bulletSpeed - 0.5f;

		if (bullet.ticksExisted < 2 && (bullet.owner != Minecraft.getMinecraft().thePlayer || !(Minecraft.getMinecraft().thePlayer.ridingEntity instanceof EntitySeat && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0))) {
			return;
		}

		if (bulletSpeed < 10 && bullet.ticksExisted < 8) {
			length *= bullet.ticksExisted / 8f;
		}

		if (length <= 0) {
			return;
		}

    motion.normalise();
    double offs = 0.1;
    double start = offs + length;
//		double length = bulletSpeed - 0.5f;
    Vector3f startPos = new Vector3f(d - motion.x * start, d1 - motion.y * start,
        d2 - motion.z * start);
    Vector3f endPos = new Vector3f(d - motion.x * offs, d1 - motion.y * offs, d2 - motion.z * offs);

    //	Vector3f startPos = new Vector3f(-motion.x*length, -motion.y*length, -motion.z*length);
    //	Vector3f endPos = new Vector3f(0, 0, 0);

    Tessellator tessellator = Tessellator.getInstance();
    WorldRenderer worldrenderer = FlansModClient.getWorldRenderer();

    EntityPlayer thePlayer = Minecraft.getMinecraft().thePlayer;
    //Vector3f vectorToPlayer = new Vector3f(thePlayer.posX - hitPos.x, thePlayer.posY - hitPos.y, thePlayer.posZ - hitPos.z);
    Vector3f vectorToPlayer = new Vector3f(thePlayer.posX - bullet.posX,
        (thePlayer.posY + thePlayer.eyeHeight) - bullet.posY, thePlayer.posZ - bullet.posZ);

    vectorToPlayer.normalise();

    Vector3f trailTangent = Vector3f.cross(motion, vectorToPlayer, null);
    //    Vector3f.add(trailTangent, new Vector3f(1, 1, 1), trailTangent);
    trailTangent.normalise();
    trailTangent.scale(-width * 0.5f);

    Vector3f normal = Vector3f.cross(trailTangent, motion, null);
		if (normal.length() != 0) {
			normal.normalise();
		}

    GlStateManager.enableRescaleNormal();
    //	GL11.glNormal3f(normal.x, normal.y, normal.z);

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
    //	GlStateManager.popMatrix();
  }

  @Override
  public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
    render((EntityBullet) entity, d, d1, d2, f, f1);
  }

  @Override
  protected ResourceLocation getEntityTexture(Entity entity) {
    return FlansModResourceHandler.getTexture(((EntityBullet) entity).type);
  }
}
