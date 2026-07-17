package org.cneko.justarod.effect

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity
import org.cneko.justarod.JRAttributes
import java.nio.charset.StandardCharsets
import java.util.UUID

/*
在玩耍的过程中呢，润滑还是很必要的，不然会有点疼，还容易受伤
 */
class LubricatingEffect: StatusEffect(StatusEffectCategory.BENEFICIAL, 3507428) {
    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }
    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        // 为玩家属性添加
        if (entity is PlayerEntity){
            val player: PlayerEntity = entity
            val attributes:Multimap<EntityAttribute, EntityAttributeModifier> = ArrayListMultimap.create()
            val modifierId = UUID.nameUUIDFromBytes("justarod:lubricating".toByteArray(StandardCharsets.UTF_8))
            attributes.put(JRAttributes.PLAYER_LUBRICATING, EntityAttributeModifier(modifierId, "justarod:lubricating", (amplifier+1)*2.0, EntityAttributeModifier.Operation.ADDITION))
            player.attributes.addTemporaryModifiers(attributes)
        }
        super.applyUpdateEffect(entity, amplifier)
    }
}
