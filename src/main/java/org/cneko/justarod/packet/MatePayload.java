package org.cneko.justarod.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import static org.cneko.justarod.Justarod.MODID;

// 对方向你发送了一个交配请求，你可以接受或接受
// 不准拒绝！草死你喵！草死你喵！草死你喵！草死你喵！草死你喵！
public record MatePayload(String nekoUuid, double amount, int time) {
    public static final Identifier ID = new Identifier(MODID, "mate");

    public PacketByteBuf toBuf() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(nekoUuid);
        buf.writeDouble(amount);
        buf.writeInt(time);
        return buf;
    }

    public static MatePayload read(PacketByteBuf buf) {
        return new MatePayload(buf.readString(), buf.readDouble(), buf.readInt());
    }
}
