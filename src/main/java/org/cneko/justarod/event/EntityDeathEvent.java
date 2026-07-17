package org.cneko.justarod.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import org.cneko.justarod.entity.Insertable;

// 啊我死了
public class EntityDeathEvent {

    public static void init(){
        ServerLivingEntityEvents.AFTER_DEATH.register(DROP_ROD_AFTER_DEATH);
    }
    private static final ServerLivingEntityEvents.AfterDeath DROP_ROD_AFTER_DEATH = (entity,dmgSource) -> {
        Insertable insertable = (Insertable) entity;
        if (insertable.hasRodInside()){
            var rodInside = insertable.getRodInside();
            var bindingCurseFlag = EnchantmentHelper.getLevel(Enchantments.BINDING_CURSE, rodInside) > 0;

            if (!entity.getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)
                    && !bindingCurseFlag
                    && !rodInside.isEmpty()
            ){//这个是死亡掉落,看情况决定要不要
                entity.dropStack(rodInside);
                insertable.setRodInside(ItemStack.EMPTY);//我试过null,但是在tick那边会npe,看了看发现有empty.我提议另一个也得改
            }
        }
    };


}
