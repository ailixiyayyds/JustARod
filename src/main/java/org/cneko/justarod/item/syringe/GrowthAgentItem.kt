package org.cneko.justarod.item.syringe

import net.minecraft.entity.LivingEntity

/*
喝~ 长大了
 */
class GrowthAgentItem: BaseSyringeItem(Settings()) {

    override fun applyEffect(target: LivingEntity) {
        target.getAttributeInstance(org.cneko.justarod.JRAttributes.GENERIC_SCALE)?.let { scale ->
            if (scale.baseValue < 4.0) {
                scale.baseValue = (scale.baseValue + 0.1).coerceAtMost(4.0)
                target.calculateDimensions()
            }
        }
    }

}
