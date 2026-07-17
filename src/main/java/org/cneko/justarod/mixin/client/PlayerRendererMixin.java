package org.cneko.justarod.mixin.client;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.cneko.justarod.JRAttributes;
import org.cneko.justarod.client.feature.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerRendererMixin {

    @Inject(method = "scale", at = @At("TAIL"))
    private void justARod$scalePlayer(AbstractClientPlayerEntity player, MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        float scale = (float) player.getAttributeValue(JRAttributes.Companion.getGENERIC_SCALE());
        if (Math.abs(scale - 1.0F) > 0.0001F) {
            matrices.scale(scale, scale, scale);
        }
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        PlayerEntityRenderer self = (PlayerEntityRenderer) (Object) this;
        self.addFeature(new RashFeatureRenderer(self));
        self.addFeature(new BallMouthFeatureRenderer(self));
        self.addFeature(new ElectricShockFeatureRenderer(self));
        self.addFeature(new BundledFeatureRenderer(self));
        self.addFeature(new EyePatchFeatureRenderer(self));
        self.addFeature(new EarplugFeatureRenderer(self));
        self.addFeature(new HandcuffFeatureRenderer(self,ctx.getHeldItemRenderer()));
        self.addFeature(new ShacklesFeatureRenderer(self,ctx.getHeldItemRenderer()));
    }

}
