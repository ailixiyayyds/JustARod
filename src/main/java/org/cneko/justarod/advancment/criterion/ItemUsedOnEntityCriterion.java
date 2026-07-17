package org.cneko.justarod.advancment.criterion;

import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.cneko.justarod.Justarod;

public class ItemUsedOnEntityCriterion extends AbstractCriterion<ItemUsedOnEntityCriterion.Conditions> {
    public static final Identifier ID = Identifier.of(Justarod.MODID, "item_used_on_entity");

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    protected Conditions conditionsFromJson(
            JsonObject json,
            LootContextPredicate playerPredicate,
            AdvancementEntityPredicateDeserializer predicateDeserializer
    ) {
        ItemPredicate item = ItemPredicate.fromJson(json.get("item"));
        EntityPredicate entity = EntityPredicate.fromJson(json.get("entity"));
        return new Conditions(playerPredicate, item, entity);
    }

    public void trigger(ServerPlayerEntity player, ItemStack stack, Entity entity) {
        this.trigger(player, conditions -> conditions.matches(player, stack, entity));
    }

    public static class Conditions extends AbstractCriterionConditions {
        private final ItemPredicate itemPredicate;
        private final EntityPredicate entityPredicate;

        public Conditions(LootContextPredicate playerPredicate, ItemPredicate itemPredicate, EntityPredicate entityPredicate) {
            super(ID, playerPredicate);
            this.itemPredicate = itemPredicate;
            this.entityPredicate = entityPredicate;
        }

        public boolean matches(ServerPlayerEntity player, ItemStack stack, Entity entity) {
            return itemPredicate.test(stack) && entityPredicate.test(player, entity);
        }

        @Override
        public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
            JsonObject json = super.toJson(predicateSerializer);
            json.add("item", itemPredicate.toJson());
            json.add("entity", entityPredicate.toJson());
            return json;
        }
    }

    public static Conditions create(ItemPredicate item, EntityPredicate entity) {
        return new Conditions(LootContextPredicate.EMPTY, item, entity);
    }
}
