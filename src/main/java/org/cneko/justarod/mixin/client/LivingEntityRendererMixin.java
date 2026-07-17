package org.cneko.justarod.mixin.client;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.cneko.justarod.JRAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {
    @Inject(method = "scale", at = @At("TAIL"))
    private void justARod$scaleEntity(LivingEntity entity, MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        float scale = (float) entity.getAttributeValue(JRAttributes.Companion.getGENERIC_SCALE());
        if (Math.abs(scale - 1.0F) > 0.0001F) {
            matrices.scale(scale, scale, scale);
        }
    }
}
