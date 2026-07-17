package org.cneko.justarod.item

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.FoodComponents
import net.minecraft.world.World
import org.cneko.justarod.effect.JREffects

/*
不上润滑也能玩，但是你最好上一个
不然小心痛的嗯啊嗯啊的叫♡
 */
class LubricatingBookItem : Item(
    Settings().maxCount(1).food(FoodComponents.APPLE)
){
    override fun finishUsing(stack: ItemStack, world: World, user: LivingEntity): ItemStack {
            // 为玩家添加状态效果
            user.addStatusEffect(StatusEffectInstance(JREffects.LUBRICATING_EFFECT, 10000000, 0))
            return super.finishUsing(stack, world, user)
    }
}
