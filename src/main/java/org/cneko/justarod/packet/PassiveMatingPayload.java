package org.cneko.justarod.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static org.cneko.justarod.Justarod.MODID;

public record PassiveMatingPayload(String uuid, String mateUuid) {
    public static final Identifier ID = new Identifier(MODID, "neko_passive_mate");

    public PacketByteBuf toBuf() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(uuid);
        buf.writeString(mateUuid);
        return buf;
    }

    public static PassiveMatingPayload read(PacketByteBuf buf) {
        return new PassiveMatingPayload(buf.readString(), buf.readString());
    }
}
