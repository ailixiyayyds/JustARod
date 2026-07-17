package org.cneko.justarod

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.ClampedEntityAttribute
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.cneko.justarod.Justarod.MODID

class JRAttributes {
    companion object{
        val PLAYER_LUBRICATING_ID:Identifier = Identifier(MODID, "player.lubricating")
        val PLAYER_LUBRICATING: EntityAttribute = register(
            PLAYER_LUBRICATING_ID,
            ClampedEntityAttribute(
                "attribute.name.player.lubricating",
                1.0, 0.0, 1000.0
            ).setTracked(true)
        )
        val GENERIC_MAX_POWER_ID = Identifier(MODID, "generic.max_power")
        val GENERIC_MAX_POWER: EntityAttribute = register(
            GENERIC_MAX_POWER_ID,
            ClampedEntityAttribute(
                "attribute.name.generic.max_power",
                100.0, 0.0, 1000.0
            ).setTracked(true)
        )
        fun register(id: Identifier, attribute: EntityAttribute): EntityAttribute {
            return Registry.register(Registries.ATTRIBUTE, id, attribute)
        }
        fun init(){

        }
    }
}
