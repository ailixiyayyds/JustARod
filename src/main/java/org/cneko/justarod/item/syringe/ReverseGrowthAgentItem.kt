package org.cneko.justarod.item.syringe

import net.minecraft.entity.LivingEntity

class ReverseGrowthAgentItem:BaseSyringeItem(Settings()) {
    override fun applyEffect(target: LivingEntity) {
        target.getAttributeInstance(org.cneko.justarod.JRAttributes.GENERIC_SCALE)?.let { scale ->
            if (scale.baseValue > 0.1) {
                scale.baseValue = (scale.baseValue - 0.1).coerceAtLeast(0.1)
                target.calculateDimensions()
            }
        }
    }
}
