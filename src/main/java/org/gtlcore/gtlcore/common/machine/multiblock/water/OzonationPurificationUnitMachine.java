package org.gtlcore.gtlcore.common.machine.multiblock.water;

import org.gtlcore.gtlcore.api.machine.multiblock.IWaterPurificationMachine;
import org.gtlcore.gtlcore.common.data.GTLMaterials;
import org.gtlcore.gtlcore.utils.MachineUtil;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import com.lowdragmc.lowdraglib.side.fluid.FluidStack;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class OzonationPurificationUnitMachine extends WorkableMultiblockMachine implements IWaterPurificationMachine, IExplosionMachine {

    private static final Fluid Ozone = GTLMaterials.Ozone.getFluid();

    private int inputCount;
    private long ozoneCount;

    public OzonationPurificationUnitMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public long test() {
        long[] a = MachineUtil.getFluidAmount(this, WaterPurificationPlantMachine.GradePurifiedWater1, Ozone);
        ozoneCount = a[1];
        if (ozoneCount > 1024000) {
            MachineUtil.inputFluid(this, FluidStack.create(Ozone, ozoneCount));
            doExplosion(10);
        }
        inputCount = (int) Math.min(Integer.MAX_VALUE, Math.min(a[0], ozoneCount * 10000));
        return inputCount * 2L;
    }

    private int getChance(int count) {
        int a = Math.min(80, (int) (ozoneCount / 102400 * 8));
        if (MachineUtil.inputFluid(this, FluidStack.create(WaterPurificationPlantMachine.GradePurifiedWater2, count / 4))) {
            return a + 20;
        } else if (MachineUtil.inputFluid(this, FluidStack.create(WaterPurificationPlantMachine.GradePurifiedWater2, count))) {
            return a + 15;
        }
        return a;
    }

    @Override
    public void run() {
        int outputCount = inputCount * 9 / 10;
        GTRecipeBuilder builder = GTRecipeBuilder.ofRaw();
        builder.duration(WaterPurificationPlantMachine.DURATION).inputFluids(FluidStack.create(Ozone, inputCount / 10000)).inputFluids(FluidStack.create(WaterPurificationPlantMachine.GradePurifiedWater1, inputCount));
        if (Math.random() * 100 <= getChance(outputCount / 10)) {
            builder.outputFluids(FluidStack.create(WaterPurificationPlantMachine.GradePurifiedWater2, outputCount));
        } else {
            builder.outputFluids(FluidStack.create(WaterPurificationPlantMachine.GradePurifiedWater1, outputCount));
        }
        getRecipeLogic().setupRecipe(builder.buildRawRecipe());
    }

    @Override
    public WorkableMultiblockMachine getMachine() {
        return this;
    }
}
