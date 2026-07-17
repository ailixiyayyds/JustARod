package org.cneko.justarod.effect

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.EntityPose
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.server.network.ServerPlayerEntity
import org.cneko.toneko.common.mod.api.EntityPoseManager
import org.cneko.toneko.common.mod.effects.ExcitingEffect
import org.cneko.toneko.common.mod.packets.EntityPosePayload
import org.cneko.justarod.JRUtil.Companion.rodEffectUuid

// 虽然但是，晕乎乎的还是不舒服对吧
class FaintEffect: StatusEffect(StatusEffectCategory.BENEFICIAL, 0x3c3c3c)  {
    init {
        this.addAttributeModifier(
            EntityAttributes.GENERIC_MOVEMENT_SPEED,
            rodEffectUuid("faint"),
            -2.0,
            EntityAttributeModifier.Operation.MULTIPLY_BASE
        )
        this.addAttributeModifier(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            rodEffectUuid("faint"),
            -2.0,
            EntityAttributeModifier.Operation.MULTIPLY_BASE
        )
        this.addAttributeModifier(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            rodEffectUuid("faint"),
            -2.0,
            EntityAttributeModifier.Operation.MULTIPLY_BASE
        )
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        EntityPoseManager.setPose(entity, EntityPose.SLEEPING)
        super.applyUpdateEffect(entity, amplifier)
    }
}
