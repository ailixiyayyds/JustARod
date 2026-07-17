package org.cneko.justarod.item.armor

import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import org.cneko.justarod.Justarod.MODID

class JRArmorMaterials {
    companion object {
        val FIREWORKS_ROD_MATERIAL: ArmorMaterial = material(
            "fireworks_rod", mapOf(ArmorItem.Type.BOOTS to 1, ArmorItem.Type.LEGGINGS to 1,
                ArmorItem.Type.CHESTPLATE to 1, ArmorItem.Type.HELMET to 1), 0,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, Ingredient.ofItems(Items.PAPER))

        val PANTSU_MATERIAL: ArmorMaterial = material(
            "pantsu", mapOf(ArmorItem.Type.BOOTS to 1, ArmorItem.Type.LEGGINGS to 3,
                ArmorItem.Type.CHESTPLATE to 2, ArmorItem.Type.HELMET to 1), 15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, Ingredient.ofItems(Items.WHITE_WOOL))

        val DIAPER_MATERIAL: ArmorMaterial = material(
            "diaper", mapOf(ArmorItem.Type.BOOTS to 1, ArmorItem.Type.LEGGINGS to 4,
                ArmorItem.Type.CHESTPLATE to 3, ArmorItem.Type.HELMET to 1), 10,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, Ingredient.ofItems(Items.WHITE_WOOL))

        private fun material(
            id: String,
            defense: Map<ArmorItem.Type, Int>,
            enchantability: Int,
            equipSound: SoundEvent,
            repairIngredient: Ingredient,
            toughness: Float = 0f,
            knockbackResistance: Float = 0f
        ): ArmorMaterial = object : ArmorMaterial {
            override fun getDurability(type: ArmorItem.Type): Int = when (type) {
                ArmorItem.Type.HELMET -> 13
                ArmorItem.Type.CHESTPLATE -> 15
                ArmorItem.Type.LEGGINGS -> 16
                ArmorItem.Type.BOOTS -> 11
            } * 15

            override fun getProtection(type: ArmorItem.Type): Int = defense[type] ?: 0
            override fun getEnchantability(): Int = enchantability
            override fun getEquipSound(): SoundEvent = equipSound
            override fun getRepairIngredient(): Ingredient = repairIngredient
            override fun getName(): String = "$MODID:$id"
            override fun getToughness(): Float = toughness
            override fun getKnockbackResistance(): Float = knockbackResistance
        }
    }
}
