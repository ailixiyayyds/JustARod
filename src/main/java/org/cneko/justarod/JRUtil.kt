package org.cneko.justarod

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.util.math.Box
import net.minecraft.world.World
import org.cneko.justarod.Justarod.MODID
import org.cneko.toneko.common.mod.entities.INeko
import java.nio.charset.StandardCharsets
import java.util.UUID
import kotlin.jvm.optionals.getOrElse
import kotlin.text.get

// awa
class JRUtil {
    companion object {
        fun World.getNekoInRange(entity: Entity, radius: Float): List<INeko> {
            val box = Box(
                entity.x - radius.toDouble(),
                entity.y - radius.toDouble(),
                entity.z - radius.toDouble(),
                entity.x + radius.toDouble(),
                entity.y + radius.toDouble(),
                entity.z + radius.toDouble()
            )
            val entities = this.getNonSpectatingEntities(LivingEntity::class.java, box)
            return entities.filter { it is INeko  && it != entity } as List<INeko>
        }

        fun World.getPlayerInRange(entity: Entity, radius: Float): List<PlayerEntity> {
            val box = Box(
                entity.x - radius.toDouble(),
                entity.y - radius.toDouble(),
                entity.z - radius.toDouble(),
                entity.x + radius.toDouble(),
                entity.y + radius.toDouble(),
                entity.z + radius.toDouble()
            )
            val entities = this.getNonSpectatingEntities(PlayerEntity::class.java, box)
            return entities.filter {it != entity}
        }
        fun rodId(path:String): Identifier{
            return Identifier(MODID, path)
        }

        fun rodEffectUuid(path: String): String {
            return UUID.nameUUIDFromBytes("$MODID:$path".toByteArray(StandardCharsets.UTF_8)).toString()
        }

        fun ItemStack.containsEnchantment(enchantment: RegistryKey<Enchantment>): Boolean {
            val value = Registries.ENCHANTMENT.get(enchantment.value) ?: return false
            return EnchantmentHelper.getLevel(value, this) > 0
        }
        fun ItemStack.getEnchantmentLevel(world : World,enchantment: RegistryKey<Enchantment>): Int {
            val value = Registries.ENCHANTMENT.get(enchantment.value) ?: return 0
            return EnchantmentHelper.getLevel(value, this)
        }



    }
}
