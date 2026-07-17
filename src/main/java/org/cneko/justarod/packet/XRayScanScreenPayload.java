package org.cneko.justarod.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.cneko.justarod.client.gui.ScanType;

import static org.cneko.justarod.Justarod.MODID;

public record XRayScanScreenPayload(ScanType scanType, int targetEntityId) {
    public static final Identifier ID = new Identifier(MODID, "x_ray_scan_screen");

    public PacketByteBuf toBuf() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(scanType.getId());
        buf.writeInt(targetEntityId);
        return buf;
    }

    public static XRayScanScreenPayload read(PacketByteBuf buf) {
        return new XRayScanScreenPayload(ScanType.fromId(buf.readString()), buf.readInt());
    }
}
