package org.gtlcore.gtlcore.common.machine.multiblock.electric;

import org.gtlcore.gtlcore.utils.MachineUtil;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import org.jetbrains.annotations.Nullable;

public class SpaceProbeSurfaceReceptionMachine extends WorkableElectricMultiblockMachine {

    public SpaceProbeSurfaceReceptionMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        Level level = getLevel();
        if (level == null) return false;
        BlockPos pos = MachineUtil.getOffsetPos(8, 28, getFrontFacing(), getPos());
        for (int i = -4; i < 5; i++) {
            for (int j = -4; j < 5; j++) {
                if (level.getBrightness(LightLayer.SKY, pos.offset(i, 0, j)) == 0) {
                    return false;
                }
            }
        }
        return level.kjs$getDimension().toString().contains("_orbit") && super.beforeWorking(recipe);
    }
}
