package org.cneko.justarod.item.syringe

import net.minecraft.entity.LivingEntity

/*
喝~ 长大了
 */
class GrowthAgentItem: BaseSyringeItem(Settings()) {

    override fun applyEffect(target: LivingEntity) {
        // Minecraft 1.20.1 has no generic scale attribute.
    }

}
