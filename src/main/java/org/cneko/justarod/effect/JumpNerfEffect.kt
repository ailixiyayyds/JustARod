package org.cneko.justarod.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import org.cneko.justarod.JRUtil.Companion.rodId

class JumpNerfEffect: StatusEffect(StatusEffectCategory.HARMFUL, 0xe81845) {
    companion object{
        val LOCATION = rodId("jump_nerf")
    }
}
