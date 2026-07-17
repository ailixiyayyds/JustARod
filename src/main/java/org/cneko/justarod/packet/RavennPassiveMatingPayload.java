package org.cneko.justarod.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static org.cneko.justarod.Justarod.MODID;

public record RavennPassiveMatingPayload(String uuid, String mateUuid) {
    public static final Identifier ID = new Identifier(MODID, "ravenn_passive_mate");

    public PacketByteBuf toBuf() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(uuid);
        buf.writeString(mateUuid);
        return buf;
    }

    public static RavennPassiveMatingPayload read(PacketByteBuf buf) {
        return new RavennPassiveMatingPayload(buf.readString(), buf.readString());
    }
}
