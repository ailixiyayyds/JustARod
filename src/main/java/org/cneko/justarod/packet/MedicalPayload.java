package org.cneko.justarod.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static org.cneko.justarod.Justarod.MODID;

public record MedicalPayload(String uuid, boolean isAmputated) {
    public static final Identifier ID = new Identifier(MODID, "medical");

    public PacketByteBuf toBuf() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(uuid);
        buf.writeBoolean(isAmputated);
        return buf;
    }

    public static MedicalPayload read(PacketByteBuf buf) {
        return new MedicalPayload(buf.readString(), buf.readBoolean());
    }
}
