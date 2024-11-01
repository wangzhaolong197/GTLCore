package org.gtlcore.gtlcore.common.machine.multiblock.water;

import org.gtlcore.gtlcore.api.machine.multiblock.IWaterPurificationMachine;
import org.gtlcore.gtlcore.api.machine.multiblock.NoEnergyMultiblockMachine;
import org.gtlcore.gtlcore.common.data.GTLMaterials;
import org.gtlcore.gtlcore.utils.MachineUtil;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.material.Fluid;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FlocculationPurificationUnitMachine extends NoEnergyMultiblockMachine implements IWaterPurificationMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            FlocculationPurificationUnitMachine.class, NoEnergyMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    private static final Fluid PolyAluminiumChloride = GTLMaterials.PolyAluminiumChloride.getFluid();
    private static final Fluid FlocculationWasteSolution = GTLMaterials.FlocculationWasteSolution.getFluid();

    @Persisted
    private long chance;

    @Persisted
    private int inputCount;

    @Persisted
    private long outputCount;

    public FlocculationPurificationUnitMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if (getRecipeLogic().isWorking()) {
            textList.add(Component.translatable("gtceu.gui.content.chance_1", chance));
        }
    }

    @Override
    public boolean onWorking() {
        boolean result = super.onWorking();
        if (getOffsetTimer() % 20 == 0) {
            long amount = MachineUtil.getFluidAmount(this, PolyAluminiumChloride)[0];
            if (MachineUtil.inputFluid(this, FluidStack.create(PolyAluminiumChloride, amount))) {
                outputCount += amount;
                if (amount % 100000 == 0) {
                    if (chance < 100) chance += amount / 10000;
                } else {
                    chance = (long) (chance * Math.pow(2, -10 * Math.abs((amount - 100000) / 100000)));
                }
            }
        }
        return result;
    }

    @Override
    public void afterWorking() {
        super.afterWorking();
        MachineUtil.outputFluid(this, FluidStack.create(FlocculationWasteSolution, outputCount));
        if (Math.random() * 100 <= chance) MachineUtil.outputFluid(this, FluidStack.create(WaterPurificationPlantMachine.GradePurifiedWater3, inputCount * 9L / 10));
    }

    @Override
    public long test() {
        chance = 0;
        inputCount = (int) Math.min(Integer.MAX_VALUE, MachineUtil.getFluidAmount(this, WaterPurificationPlantMachine.GradePurifiedWater2)[0]);
        return inputCount * 3L;
    }

    @Override
    public void run() {
        getRecipeLogic().setupRecipe(GTRecipeBuilder.ofRaw().duration(WaterPurificationPlantMachine.DURATION).inputFluids(FluidStack.create(WaterPurificationPlantMachine.GradePurifiedWater2, inputCount)).buildRawRecipe());
    }

    @Override
    public WorkableMultiblockMachine getMachine() {
        return this;
    }
}
