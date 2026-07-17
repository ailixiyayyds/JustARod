package org.cneko.justarod.mixin;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerCommonNetworkingHandlerMixin {
    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, CallbackInfo ci) {
        ServerPlayNetworkHandler playerHandler = (ServerPlayNetworkHandler) (Object) this;
        if (playerHandler.player.getEarplug() > 0 && packet instanceof PlaySoundS2CPacket) {
            ci.cancel();
        }
    }
}
