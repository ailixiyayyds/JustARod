package org.cneko.justarod.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static org.cneko.justarod.Justarod.MODID;

// 哼哼
public record FrictionPayload(String message) {
    public static final Identifier ID = new Identifier(MODID, "friction");

    public PacketByteBuf toBuf() {
        return PacketByteBufs.create().writeString(message);
    }

    public static FrictionPayload read(PacketByteBuf buf) {
        return new FrictionPayload(buf.readString());
    }
}
