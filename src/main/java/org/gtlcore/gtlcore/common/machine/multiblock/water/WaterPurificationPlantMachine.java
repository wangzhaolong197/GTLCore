package org.gtlcore.gtlcore.common.machine.multiblock.water;

import org.gtlcore.gtlcore.api.machine.multiblock.IWaterPurificationMachine;
import org.gtlcore.gtlcore.common.data.GTLMaterials;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WaterPurificationPlantMachine extends WorkableElectricMultiblockMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            WaterPurificationPlantMachine.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    static final int DURATION = 2400;

    static final Fluid GradePurifiedWater1 = GTLMaterials.GradePurifiedWater1.getFluid();
    static final Fluid GradePurifiedWater2 = GTLMaterials.GradePurifiedWater2.getFluid();
    static final Fluid GradePurifiedWater3 = GTLMaterials.GradePurifiedWater3.getFluid();
    static final Fluid GradePurifiedWater4 = GTLMaterials.GradePurifiedWater4.getFluid();
    static final Fluid GradePurifiedWater5 = GTLMaterials.GradePurifiedWater5.getFluid();
    static final Fluid GradePurifiedWater6 = GTLMaterials.GradePurifiedWater6.getFluid();
    static final Fluid GradePurifiedWater7 = GTLMaterials.GradePurifiedWater7.getFluid();
    static final Fluid GradePurifiedWater8 = GTLMaterials.GradePurifiedWater8.getFluid();

    @Getter
    @Persisted
    private final Set<BlockPos> poss = new HashSet<>();

    private final Set<IWaterPurificationMachine> waterPurificationMachines = new HashSet<>();
    private final Set<IWaterPurificationMachine> runMachines = new HashSet<>();

    public WaterPurificationPlantMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    private void run() {
        for (IWaterPurificationMachine machine : runMachines) {
            machine.getMachine().getRecipeLogic().resetRecipeLogic();
            machine.run();
        }
    }

    public boolean addWaterPurificationMachine(BlockPos pos, Level level) {
        MetaMachine machine = MetaMachine.getMachine(level, pos);
        if (machine instanceof IWaterPurificationMachine waterPurificationMachine && waterPurificationMachine.getMachine().isFormed()) {
            return waterPurificationMachines.add(waterPurificationMachine);
        }
        return false;
    }

    private void update(@Nullable Level level) {
        if (level == null) return;
        waterPurificationMachines.clear();
        poss.removeIf(pos -> !addWaterPurificationMachine(pos, level));
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        update(getLevel());
    }

    @Override
    public void onWaiting() {
        super.onWaiting();
        getRecipeLogic().resetRecipeLogic();
        for (IWaterPurificationMachine machine : waterPurificationMachines) {
            machine.getMachine().getRecipeLogic().resetRecipeLogic();
        }
    }

    @Override
    public int getOutputSignal(@Nullable Direction side) {
        return 15 * DURATION / getRecipeLogic().getProgress();
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        textList.add(Component.translatable("gtlcore.machine.water_purification_plant.bind"));
        for (IWaterPurificationMachine machine : waterPurificationMachines) {
            MutableComponent component = Component.translatable(machine.getMachine().getBlockState().getBlock().getDescriptionId()).append(" ");
            if (machine.getMachine().getRecipeLogic().isWorking()) {
                component.append(Component.translatable("gtceu.multiblock.running"));
            } else {
                component.append(Component.translatable("gtceu.multiblock.idling"));
            }
            textList.add(component);
        }
    }

    @Override
    protected RecipeLogic createRecipeLogic(Object... args) {
        return new WaterPurificationPlantRecipesLogic(this);
    }

    @Override
    public WaterPurificationPlantRecipesLogic getRecipeLogic() {
        return (WaterPurificationPlantRecipesLogic) super.getRecipeLogic();
    }

    public class WaterPurificationPlantRecipesLogic extends RecipeLogic {

        public WaterPurificationPlantRecipesLogic(WaterPurificationPlantMachine machine) {
            super(machine);
        }

        @Override
        public WaterPurificationPlantMachine getMachine() {
            return (WaterPurificationPlantMachine) super.getMachine();
        }

        @Override
        public void setWorkingEnabled(boolean isWorkingAllowed) {
            if (isWorkingAllowed) {
                setStatus(Status.IDLE);
                for (IWaterPurificationMachine machine : runMachines) {
                    machine.getMachine().getRecipeLogic().setStatus(Status.IDLE);
                }
            } else {
                setStatus(Status.SUSPEND);
                for (IWaterPurificationMachine machine : runMachines) {
                    machine.getMachine().getRecipeLogic().resetRecipeLogic();
                    machine.getMachine().getRecipeLogic().setStatus(Status.SUSPEND);
                }
            }
        }

        @Override
        public void findAndHandleRecipe() {
            lastRecipe = null;
            GTRecipe match = getRecipe();
            if (match != null) {
                if (match.matchTickRecipe(this.machine).isSuccess()) {
                    setupRecipe(match);
                    run();
                }
            }
        }

        @Nullable
        private GTRecipe getRecipe() {
            if (machine.hasProxies() && !waterPurificationMachines.isEmpty()) {
                update(getLevel());
                long eut = 0;
                for (IWaterPurificationMachine machine : waterPurificationMachines) {
                    if (machine.getMachine().isFormed() && machine.getMachine().getRecipeLogic().isWorkingEnabled()) {
                        long eu = machine.test();
                        if (eu > 0) {
                            runMachines.add(machine);
                            eut += eu;
                        }
                    }
                }
                GTRecipeBuilder builder = GTRecipeBuilder.ofRaw().duration(DURATION);
                if (eut > 0) builder.EUt(eut);
                return builder.buildRawRecipe();
            }
            return null;
        }

        @Override
        public void onRecipeFinish() {
            machine.afterWorking();
            runMachines.clear();
            GTRecipe match = getRecipe();
            if (match != null) {
                if (match.matchTickRecipe(this.machine).isSuccess()) {
                    setupRecipe(match);
                    run();
                    return;
                }
            }
            setStatus(Status.IDLE);
            progress = 0;
            duration = 0;
        }
    }
}
