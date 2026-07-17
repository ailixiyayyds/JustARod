package org.cneko.justarod.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.cneko.justarod.Justarod.MODID

class JREffects {
    companion object{
        var ORGASM_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "orgasm"),
            OrgasmEffect()
        )
        var LUBRICATING_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "lubricating"),
            LubricatingEffect()
        )
        var ESTRUS_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "estrus"),
            EstrusEffect()
        )
        var STRONG_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "strong"),
            StrongEffect()
        )
        var FAINT_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "faint"),
            FaintEffect()
        )
        var PREGNANT_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "pregnant"),
            PregnantEffect()
        )
        var AIDS_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "aids"),
            AIDSEffect()
        )
        val HPV_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "hpv"),
            HPVEffect()
        )
        val VAGINITIS_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "vaginitis"),
            VaginitisEffect()
        )
        val OVARIAN_CANCER_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "ovarian_cancer"),
            OvarianCancerEffect()
        )
        val SYPHILIS_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "syphilis"),
            SyphilisEffect()
        )
        val JUMP_NERF_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "jump_nerf"),
            JumpNerfEffect()
        )
        val KENJA_TIME_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "kenja_time"),
            KenjaTimeEffect()
        )
        val SMEARY_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "smeary"),
            SmearyEffect()
        )
        val UTERINE_COLD_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "uterine_cold"),
            UterineColdEffect()
        )
        val URETHRITIS_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "urethritis"),
            UrethritisEffect()
        )
        val PROSTATITIS_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "prostatitis"),
            ProstatitisEffect()
        )
        val LILY_PHEROMONE_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "lily_pheromone"),
            LilyPheromoneEffect()
        )
        val PARONYCHIA_EFFECT: StatusEffect? = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier(MODID, "paronychia"),
            ParonychiaEffect()
        )
        fun init(){
        }
    }

}
