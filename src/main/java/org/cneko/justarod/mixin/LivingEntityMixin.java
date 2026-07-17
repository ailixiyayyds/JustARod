package org.cneko.justarod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.cneko.justarod.JRAttributes;
import org.cneko.justarod.entity.BDSMable;
import org.cneko.justarod.entity.Insertable;
import org.cneko.justarod.entity.Pregnant;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements Insertable {

    @Unique
    private ItemStack rodInside = ItemStack.EMPTY;

    @Override
    public ItemStack getRodInside() {
        return rodInside;
    }

    @Override
    public void setRodInside(@NotNull ItemStack rodInside) {
        this.rodInside = rodInside;
    }


    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("rodInside")) {
            setRodInside(ItemStack.fromNbt(nbt.getCompound("rodInside")));
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (!getRodInside().isEmpty()) {
            nbt.put("rodInside", getRodInside().writeNbt(new NbtCompound()));
        }
    }

    @Inject(method = "tick",at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (!getRodInside().isEmpty()) {
            this.tickInside(self);
        }
    }

    @Inject(method = "createLivingAttributes",at = @At("RETURN"))
    private static void createLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        DefaultAttributeContainer.Builder builder = cir.getReturnValue();
        builder.add(JRAttributes.Companion.getPLAYER_LUBRICATING());
        builder.add(JRAttributes.Companion.getGENERIC_MAX_POWER());
        builder.add(JRAttributes.Companion.getGENERIC_SCALE());
        builder.add(JRAttributes.Companion.getGENERIC_JUMP_STRENGTH());
    }

    @Inject(method = "jump", at = @At("TAIL"))
    private void justARod$applyJumpNerf(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        double multiplier = self.getAttributeValue(JRAttributes.Companion.getGENERIC_JUMP_STRENGTH()) / 0.42;
        var velocity = self.getVelocity();
        if (velocity.y > 0.0 && Math.abs(multiplier - 1.0) > 0.0001) {
            self.setVelocity(velocity.x, velocity.y * multiplier, velocity.z);
        }
    }

    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void justARod$scaleDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        double scale = self.getAttributeValue(JRAttributes.Companion.getGENERIC_SCALE());
        if (Math.abs(scale - 1.0) > 0.0001) {
            cir.setReturnValue(cir.getReturnValue().scaled((float) scale));
        }
    }

    @Inject(method = "damage", at = @At("HEAD"))
    public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof Pregnant pregnant && amount >=7 && pregnant.isPregnant()){
            // 流产
            pregnant.miscarry();
        }
        // 甲沟炎"磕到"触发
        if (self instanceof Pregnant pregnant && pregnant.getParonychia() > 0 && amount >= 1.0f) {
            int bumpChance = pregnant.getParonychiaBumpChance();
            if (bumpChance > 0 && self.getRandom().nextInt(bumpChance) == 0) {
                pregnant.triggerParonychiaBump("受到" + Math.round(amount) + "点伤害");
            }
        }
    }
}
