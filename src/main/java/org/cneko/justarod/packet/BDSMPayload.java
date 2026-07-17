package org.cneko.justarod.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static org.cneko.justarod.Justarod.MODID;

public record BDSMPayload(String uuid, boolean ballMouth, boolean electricShock,
                          boolean bundled, boolean eyePatch, boolean earplug,
                          boolean handcuffed, boolean shackled, boolean noMatingPlz) {
    public static final Identifier ID = new Identifier(MODID, "bdsm");

    public PacketByteBuf toBuf() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(uuid);
        int flags = 0;
        flags |= ballMouth ? 1 : 0;
        flags |= (electricShock ? 1 : 0) << 1;
        flags |= (bundled ? 1 : 0) << 2;
        flags |= (eyePatch ? 1 : 0) << 3;
        flags |= (earplug ? 1 : 0) << 4;
        flags |= (handcuffed ? 1 : 0) << 5;
        flags |= (shackled ? 1 : 0) << 6;
        flags |= (noMatingPlz ? 1 : 0) << 7;
        buf.writeInt(flags);
        return buf;
    }

    public static BDSMPayload read(PacketByteBuf buf) {
        String uuid = buf.readString();
        int flags = buf.readInt();
        return new BDSMPayload(uuid,
                (flags & 1) != 0,
                (flags & 1 << 1) != 0,
                (flags & 1 << 2) != 0,
                (flags & 1 << 3) != 0,
                (flags & 1 << 4) != 0,
                (flags & 1 << 5) != 0,
                (flags & 1 << 6) != 0,
                (flags & 1 << 7) != 0);
    }
}
