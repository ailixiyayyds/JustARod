package org.cneko.justarod.item.armor

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.FireworkRocketEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList
import net.minecraft.world.World

/*
我嘞个豆~~~~~~~~~~~~~
 */
class FireworksRodItem : RodArmorItem<FireworksRodItem>(JRArmorMaterials.FIREWORKS_ROD_MATERIAL, Settings().maxCount(1).maxDamage(1000)) {
    companion object{
        const val ID = "fireworks_rod"
    }
    override fun getId(): String {
        return ID
    }

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun onUse(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        super.onUse(stack, world, entity, slot, selected)
        if (entity is PlayerEntity){
            // 1/5的概率放一个烟花
            if (entity.random.nextInt(5) == 0){
                val stack = Items.FIREWORK_ROCKET.defaultStack
                val explosion = NbtCompound().apply {
                    putByte("Type", 2)
                    putIntArray("Colors", intArrayOf(0xDC143C, 0xFFD700, 0xFFE4E1))
                    putIntArray("FadeColors", intArrayOf(0xDB7093, 0xFFF8DC, 0xC0C0C0))
                    putBoolean("Flicker", true)
                    putBoolean("Trail", true)
                }
                stack.getOrCreateSubNbt("Fireworks").apply {
                    putByte("Flight", 3)
                    put("Explosions", NbtList().apply { add(explosion) })
                }
                entity.world.spawnEntity(FireworkRocketEntity(entity.world,stack, entity))
            }
        }
    }

}
