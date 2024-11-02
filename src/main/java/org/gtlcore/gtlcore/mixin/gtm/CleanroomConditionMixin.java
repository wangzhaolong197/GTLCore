package org.gtlcore.gtlcore.mixin.gtm;

import org.gtlcore.gtlcore.common.machine.multiblock.steam.LargeSteamParallelMultiblockMachine;

import com.gregtechceu.gtceu.api.capability.ICleanroomReceiver;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ICleanroomProvider;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.recipe.condition.CleanroomCondition;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CleanroomCondition.class)
public class CleanroomConditionMixin {

    @Shadow(remap = false)
    private CleanroomType cleanroom;

    @Inject(method = "test", at = @At("HEAD"), remap = false, cancellable = true)
    public void test(GTRecipe recipe, RecipeLogic recipeLogic, CallbackInfoReturnable<Boolean> cir) {
        boolean value = true;
        MetaMachine machine = recipeLogic.getMachine();
        if (machine instanceof LargeSteamParallelMultiblockMachine) {
            value = false;
        } else if (machine instanceof ICleanroomReceiver receiver && this.cleanroom != null) {
            ICleanroomProvider provider = receiver.getCleanroom();
            if (provider != null) {
                value = provider.isClean() && provider.getTypes().contains(this.cleanroom);
            }
        }
        cir.setReturnValue(value);
    }
}
