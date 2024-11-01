package org.gtlcore.gtlcore.common.machine.multiblock.water;

import org.gtlcore.gtlcore.api.machine.multiblock.IWaterPurificationMachine;
import org.gtlcore.gtlcore.common.machine.multiblock.part.IndicatorHatchPartMachine;
import org.gtlcore.gtlcore.utils.MachineUtil;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.MethodsReturnNonnullByDefault;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ResidualDecontaminantDegasserPurificationUnitMachine extends WorkableMultiblockMachine implements IWaterPurificationMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            ResidualDecontaminantDegasserPurificationUnitMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    private static final List<FluidStack> FLUIDS = List.of(
            GTMaterials.Helium.getFluid(FluidStorageKeys.LIQUID, 10000),
            GTMaterials.Helium.getFluid(10000),
            GTMaterials.SamariumIronArsenicOxide.getFluid(1000),
            GTMaterials.Neon.getFluid(8000),
            GTMaterials.IndiumTinBariumTitaniumCuprate.getFluid(1000),
            GTMaterials.Argon.getFluid(6000),
            GTMaterials.UraniumRhodiumDinaquadide.getFluid(1000),
            GTMaterials.Krypton.getFluid(4000),
            GTMaterials.EnrichedNaquadahTriniumEuropiumDuranide.getFluid(1000),
            GTMaterials.Xenon.getFluid(2000),
            GTMaterials.RutheniumTriniumAmericiumNeutronate.getFluid(1000),
            GTMaterials.Neutronium.getFluid(2000));

    private static FluidStack getFluid(int index) {
        if (index == 13 || index == 15) {
            return FLUIDS.get(11);
        } else if (index == 12 || index == 14) {
            return FluidStack.empty();
        }
        return FLUIDS.get(index);
    }

    @Persisted
    private int inputCount;

    @Persisted
    private boolean successful;

    @Persisted
    private boolean failed;

    private IndicatorHatchPartMachine indicatorHatchPartMachine;

    public ResidualDecontaminantDegasserPurificationUnitMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        for (IMultiPart part : getParts()) {
            if (part instanceof IndicatorHatchPartMachine indicatorHatch) {
                indicatorHatchPartMachine = indicatorHatch;
            }
        }
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        indicatorHatchPartMachine = null;
    }

    @Override
    public boolean onWorking() {
        boolean result = super.onWorking();
        if (getOffsetTimer() % 20 == 0) {
            FluidStack fluidStack = getFluid(indicatorHatchPartMachine.getRedstoneSignalOutput());
            for (IRecipeHandler<?> handler : Objects.requireNonNullElseGet(getCapabilitiesProxy().get(IO.IN, FluidRecipeCapability.CAP), Collections::<IRecipeHandler<?>>emptyList)) {
                for (Object contents : handler.getContents()) {
                    if (contents instanceof FluidStack stack && stack.getAmount() > 0) {
                        MachineUtil.inputFluid(this, stack);
                        if (!fluidStack.isEmpty() && fluidStack.getFluid() == stack.getFluid() && fluidStack.getAmount() <= stack.getAmount()) {
                            successful = true;
                        } else {
                            failed = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void afterWorking() {
        super.afterWorking();
        indicatorHatchPartMachine.setRedstoneSignalOutput(0);
        if (successful && !failed) MachineUtil.outputFluid(this, FluidStack.create(WaterPurificationPlantMachine.GradePurifiedWater7, inputCount * 9L / 10));
    }

    @Override
    public long test() {
        successful = false;
        failed = false;
        indicatorHatchPartMachine.setRedstoneSignalOutput((int) (Math.random() * 15));
        inputCount = (int) Math.min(Integer.MAX_VALUE, MachineUtil.getFluidAmount(this, WaterPurificationPlantMachine.GradePurifiedWater6)[0]);
        return inputCount * 7L;
    }

    @Override
    public void run() {
        getRecipeLogic().setupRecipe(GTRecipeBuilder.ofRaw().duration(WaterPurificationPlantMachine.DURATION).inputFluids(FluidStack.create(WaterPurificationPlantMachine.GradePurifiedWater6, inputCount)).buildRawRecipe());
    }

    @Override
    public WorkableMultiblockMachine getMachine() {
        return this;
    }
}
