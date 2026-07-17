package org.cneko.justarod.item

import net.minecraft.entity.EntityType
import net.minecraft.item.ItemStack
import net.minecraft.item.Item
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.util.StringIdentifiable

/**
 * Minecraft 1.20.1 compatibility storage for the data components introduced
 * by the 1.21 version of JustARod. Values are kept under stable ItemStack NBT
 * keys so they survive saves and network synchronization.
 */
class JRComponentKey<T>(
    val nbtKey: String,
    val read: (NbtCompound) -> T?,
    val write: (NbtCompound, T) -> Unit
)

class JRComponents {
    companion object {
        private fun intKey(name: String) = JRComponentKey<Int>(name,
            { nbt -> if (nbt.contains(name)) nbt.getInt(name) else null },
            { nbt, value -> nbt.putInt(name, value) })

        private fun stringKey(name: String) = JRComponentKey<String>(name,
            { nbt -> if (nbt.contains(name)) nbt.getString(name) else null },
            { nbt, value -> nbt.putString(name, value) })

        private fun booleanKey(name: String) = JRComponentKey<Boolean>(name,
            { nbt -> if (nbt.contains(name)) nbt.getBoolean(name) else null },
            { nbt, value -> nbt.putBoolean(name, value) })

        val USED_TIME_MARK = intKey("JustARodUsedTime")
        val OWNER = stringKey("JustARodOwner")
        val SPEED = intKey("JustARodSpeed")
        val MODE = stringKey("JustARodMode")
        val SECRETIONS_APPEARANCE = stringKey("JustARodSecretionsAppearance")
        val COLLECTED_TIME = intKey("JustARodCollectedTime")
        val CLONER_TRANSFERRED = booleanKey("JustARodClonerTransferred")
        val CLONER_STATE = stringKey("JustARodClonerState")

        val ROD_INSIDE = JRComponentKey<ItemStack>("JustARodInside",
            { nbt -> if (nbt.contains("JustARodInside")) ItemStack.fromNbt(nbt.getCompound("JustARodInside")) else null },
            { nbt, value -> nbt.put("JustARodInside", value.writeNbt(NbtCompound())) })

        val ENTITY_TYPE = JRComponentKey<EntityType<*>>("JustARodEntityType",
            { nbt ->
                if (!nbt.contains("JustARodEntityType")) null
                else Identifier.tryParse(nbt.getString("JustARodEntityType"))?.let { Registries.ENTITY_TYPE.get(it) }
            },
            { nbt, value -> nbt.putString("JustARodEntityType", Registries.ENTITY_TYPE.getId(value).toString()) })

        val PANTSU_STATE = JRComponentKey<PantsuState>("JustARodPantsuState",
            { nbt ->
                if (!nbt.contains("JustARodPantsuState")) null
                else PantsuState.entries.firstOrNull { it.asString() == nbt.getString("JustARodPantsuState") }
            },
            { nbt, value -> nbt.putString("JustARodPantsuState", value.asString()) })

        val CLONER_ENTITY_NBT = JRComponentKey<NbtCompound>("JustARodClonerEntityNbt",
            { nbt -> if (nbt.contains("JustARodClonerEntityNbt")) nbt.getCompound("JustARodClonerEntityNbt").copy() else null },
            { nbt, value -> nbt.put("JustARodClonerEntityNbt", value.copy()) })

        @JvmStatic
        fun <T> get(stack: ItemStack, key: JRComponentKey<T>): T? = stack.nbt?.let(key.read)

        @JvmStatic
        fun <T> getOrDefault(stack: ItemStack, key: JRComponentKey<T>, defaultValue: T): T =
            get(stack, key) ?: defaultValue

        @JvmStatic
        fun <T> set(stack: ItemStack, key: JRComponentKey<T>, value: T?) {
            if (value == null) remove(stack, key) else key.write(stack.orCreateNbt, value)
        }

        @JvmStatic
        fun contains(stack: ItemStack, key: JRComponentKey<*>): Boolean = stack.nbt?.contains(key.nbtKey) == true

        @JvmStatic
        fun remove(stack: ItemStack, key: JRComponentKey<*>) {
            stack.nbt?.remove(key.nbtKey)
        }
    }

    enum class PantsuState(private val id: String, val translationKey: String) : StringIdentifiable {
        CLEAN("clean", "tooltip.justarod.pantsu.clean"),
        WET("wet", "tooltip.justarod.pantsu.wet"),
        SOILED("soiled", "tooltip.justarod.pantsu.soiled"),
        BLOODY("bloody", "tooltip.justarod.pantsu.bloody");

        override fun asString(): String = id
    }
}

operator fun <T> ItemStack.get(key: JRComponentKey<T>): T? = JRComponents.get(this, key)

fun <T> ItemStack.getOrDefault(key: JRComponentKey<T>, defaultValue: T): T =
    JRComponents.getOrDefault(this, key, defaultValue)

fun <T> ItemStack.set(key: JRComponentKey<T>, value: T?) = JRComponents.set(this, key, value)

fun ItemStack.contains(key: JRComponentKey<*>): Boolean = JRComponents.contains(this, key)

fun ItemStack.remove(key: JRComponentKey<*>) = JRComponents.remove(this, key)

class JRComponentView(private val stack: ItemStack) {
    fun contains(key: JRComponentKey<*>): Boolean = JRComponents.contains(stack, key)
    fun <T> get(key: JRComponentKey<T>): T? = JRComponents.get(stack, key)
    fun <T> getOrDefault(key: JRComponentKey<T>, defaultValue: T): T =
        JRComponents.getOrDefault(stack, key, defaultValue)
}

val ItemStack.components: JRComponentView
    get() = JRComponentView(this)

/** 1.21 default components are replaced by NBT defaults at the call sites. */
fun <T> Item.Settings.component(key: JRComponentKey<T>, defaultValue: T): Item.Settings = this
