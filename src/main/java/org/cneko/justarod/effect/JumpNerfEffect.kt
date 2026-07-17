package org.cneko.justarod.effect

import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import org.cneko.justarod.JRAttributes
import org.cneko.justarod.JRUtil.Companion.rodEffectUuid

class JumpNerfEffect: StatusEffect(StatusEffectCategory.HARMFUL, 0xe81845) {
    init {
        addAttributeModifier(
            JRAttributes.GENERIC_JUMP_STRENGTH,
            rodEffectUuid("jump_nerf"),
            -0.3,
            EntityAttributeModifier.Operation.MULTIPLY_BASE
        )
    }
}
