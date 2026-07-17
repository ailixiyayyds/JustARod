package org.cneko.justarod.item.syringe

import net.minecraft.entity.LivingEntity

class ReverseGrowthAgentItem:BaseSyringeItem(Settings()) {
    override fun applyEffect(target: LivingEntity) {
        // Minecraft 1.20.1 has no generic scale attribute.
    }
}
