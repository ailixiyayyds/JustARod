package org.cneko.justarod.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.cneko.justarod.property.JRProperty;
import org.cneko.justarod.property.JRRegistry;
import static org.cneko.justarod.Justarod.MODID;

import java.util.ArrayList;
import java.util.List;

public record JRSyncPayload(List<Object> values) {

    public static final Identifier ID = new Identifier(MODID, "sync");

    // 写入：遍历注册表，取出对应的值写入 Buf
    @SuppressWarnings("unchecked")
    public PacketByteBuf toBuf() {
        PacketByteBuf buf = PacketByteBufs.create();
        List<JRProperty<?>> properties = JRRegistry.INSTANCE.getPROPERTIES();
        for (int i = 0; i < properties.size(); i++) {
            JRProperty<Object> prop = (JRProperty<Object>) properties.get(i);
            Object value = values.get(i);
            prop.writeToBuf(buf, value);
        }
        return buf;
    }

    // 读取：遍历注册表，从 Buf 按顺序读取值存入 List
    public static JRSyncPayload read(PacketByteBuf buf) {
        List<Object> decodedValues = new ArrayList<>();
        for (JRProperty<?> prop : JRRegistry.INSTANCE.getPROPERTIES()) {
            decodedValues.add(prop.readFromBuf(buf));
        }
        return new JRSyncPayload(decodedValues);
    }

}
