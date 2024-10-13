package org.gtlcore.gtlcore.common.recipe.condition;

import org.gtlcore.gtlcore.api.machine.IVacuumMachine;
import org.gtlcore.gtlcore.common.data.GTLRecipeConditions;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.utils.GTUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.Level;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import earth.terrarium.adastra.api.systems.GravityApi;
import earth.terrarium.adastra.api.systems.OxygenApi;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public class VacuumCondition extends RecipeCondition {

    public static final Codec<VacuumCondition> CODEC = RecordCodecBuilder
            .create(instance -> RecipeCondition.isReverse(instance)
                    .and(Codec.INT.fieldOf("vacuum").forGetter(val -> val.tier))
                    .apply(instance, VacuumCondition::new));

    public final static VacuumCondition INSTANCE = new VacuumCondition();

    private int tier = 0;

    public VacuumCondition(int tier) {
        this.tier = tier;
    }

    public VacuumCondition(boolean isReverse, int tier) {
        super(isReverse);
        this.tier = tier;
    }

    @Override
    public RecipeConditionType<?> getType() {
        return GTLRecipeConditions.VACUUM;
    }

    @Override
    public Component getTooltips() {
        return Component.translatable("gtlcore.vacuum.tier", tier);
    }

    @Override
    public boolean test(@NotNull GTRecipe recipe, @NotNull RecipeLogic recipeLogic) {
        MetaMachine machine = recipeLogic.getMachine();

        if (machine instanceof MultiblockControllerMachine controllerMachine) {
            if (checkVacuumTier(controllerMachine.self().getParts())) {
                return true;
            }
        }

        Level level = machine.getLevel();
        BlockPos pos = machine.getPos();
        if (level != null) {
            for (Direction side : GTUtil.DIRECTIONS) {
                if (side.getAxis() != Direction.Axis.Y && checkNeighborVacuumTier(level, pos.relative(side))) {
                    return true;
                }
            }
        }
        return !OxygenApi.API.hasOxygen(level, pos) && GravityApi.API.getGravity(level, pos) == 0;
    }

    private boolean checkVacuumTier(Iterable<IMultiPart> parts) {
        for (IMultiPart part : parts) {
            if (part instanceof IVacuumMachine vacuumMachine && vacuumMachine.getVacuumTier() >= tier) {
                return true;
            }
        }
        return false;
    }

    private boolean checkNeighborVacuumTier(Level level, BlockPos neighborPos) {
        if (MetaMachine.getMachine(level, neighborPos) instanceof IVacuumMachine vacuumMachine) {
            return vacuumMachine.getVacuumTier() >= tier;
        }
        return false;
    }

    @Override
    public RecipeCondition createTemplate() {
        return new VacuumCondition();
    }

    @NotNull
    @Override
    public JsonObject serialize() {
        JsonObject config = super.serialize();
        config.addProperty("vacuum", tier);
        return config;
    }

    @Override
    public RecipeCondition deserialize(@NotNull JsonObject config) {
        super.deserialize(config);
        tier = GsonHelper.getAsInt(config, "vacuum", 0);
        return this;
    }

    @Override
    public RecipeCondition fromNetwork(FriendlyByteBuf buf) {
        super.fromNetwork(buf);
        tier = buf.readInt();
        return this;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf) {
        super.toNetwork(buf);
        buf.writeInt(tier);
    }
}
