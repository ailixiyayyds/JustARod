package org.cneko.justarod.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static org.cneko.justarod.Justarod.MODID;

// 好烫
public record FullHeatPayload(String message) {
    public static final Identifier ID = new Identifier(MODID, "full_heat");

    public PacketByteBuf toBuf() {
        return PacketByteBufs.create().writeString(message);
    }

    public static FullHeatPayload read(PacketByteBuf buf) {
        return new FullHeatPayload(buf.readString());
    }
}
