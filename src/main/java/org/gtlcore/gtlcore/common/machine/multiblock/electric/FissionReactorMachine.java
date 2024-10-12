package org.gtlcore.gtlcore.common.machine.multiblock.electric;

import org.gtlcore.gtlcore.api.pattern.util.IValueContainer;
import org.gtlcore.gtlcore.common.data.GTLBlocks;
import org.gtlcore.gtlcore.common.data.GTLMaterials;
import org.gtlcore.gtlcore.utils.MachineIO;

import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import com.mojang.datafixers.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FissionReactorMachine extends WorkableElectricMultiblockMachine implements IExplosionMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            FissionReactorMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Persisted
    private BlockPos centrePos = null;
    @Persisted
    private int heat = 298;
    @Persisted
    private int damaged = 0;
    @Persisted
    private int parallel = 0;
    @Persisted
    private int recipeHeat = 0;
    private int fuel = 0, cooler = 0, heatAdjacent = 1, coolerAdjacent = 0;

    protected ConditionalSubscriptionHandler HeatSubs;

    public FissionReactorMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.HeatSubs = new ConditionalSubscriptionHandler(this, this::HeatUpdate, this::isFormed);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public static int adjacent(Level level, BlockPos pos, Block block) {
        int a = 0;
        BlockPos[] coordinates = new BlockPos[] { pos.offset(1, 0, 0),
                pos.offset(-1, 0, 0),
                pos.offset(0, 1, 0),
                pos.offset(0, -1, 0),
                pos.offset(0, 0, 1),
                pos.offset(0, 0, -1) };
        for (BlockPos blockPos : coordinates) {
            if (level.getBlockState(blockPos).getBlock() == block) {
                a++;
            }
        }
        return a;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        heatAdjacent = 0;
        cooler = 0;
        Level level = getLevel();
        int heatA = 0;
        int coolerA = 0;
        int x = 0, y = 0, z = 0;
        switch (getFrontFacing()) {
            case NORTH -> z = 4;
            case SOUTH -> z = -4;
            case WEST -> x = 4;
            case EAST -> x = -4;
        }
        final BlockPos blockPos = getPos().offset(x, y, z);
        centrePos = blockPos.offset(0, 4, 0);
        for (int i = -3; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = -3; k < 4; k++) {
                    BlockPos assemblyPos = blockPos.offset(i, j, k);
                    if (level != null && level.getBlockState(assemblyPos).getBlock() == GTLBlocks.FISSION_FUEL_ASSEMBLY.get()) {
                        heatA += adjacent(level, assemblyPos, GTLBlocks.FISSION_FUEL_ASSEMBLY.get());
                    }
                    if (level != null && level.getBlockState(assemblyPos).getBlock() == GTLBlocks.COOLER.get()) {
                        coolerA += adjacent(level, assemblyPos, GTLBlocks.COOLER.get());
                    }
                }
            }
        }
        heatAdjacent = (heatA / 2) + 1;
        coolerAdjacent = coolerA / 2;
        IValueContainer<?> FuelContainer = getMultiblockState().getMatchContext()
                .getOrCreate("FuelAssemblyValue", IValueContainer::noop);
        if (FuelContainer.getValue() instanceof Integer Fuel) {
            this.fuel = Fuel;
        }
        IValueContainer<?> CoolerContainer = getMultiblockState().getMatchContext()
                .getOrCreate("CoolerValue", IValueContainer::noop);
        if (CoolerContainer.getValue() instanceof Integer Cooler) {
            this.cooler = Cooler;
        }
        HeatSubs.initialize(getLevel());
    }

    @Override
    public void afterWorking() {
        parallel = 0;
        recipeHeat = 0;
        super.afterWorking();
    }

    @Override
    public void doExplosion(BlockPos pos, float explosionPower) {
        var machine = this.self();
        var level = machine.getLevel();
        if (level != null) {
            level.removeBlock(machine.getPos(), false);
            level.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    explosionPower, Level.ExplosionInteraction.BLOCK);
        }
    }

    private boolean inputWater(long amount) {
        boolean value = MachineIO.inputFluid(this, GTMaterials.DistilledWater.getFluid(amount * 800));
        double steamMultiplier = heat > 373 ? 160 : 160 / Math.pow(1.4, 373 - heat);
        if (value) value = MachineIO.outputFluid(this, GTMaterials.Steam.getFluid((long) (amount * 800 * steamMultiplier)));
        return value;
    }

    private boolean inputSodiumPotassium(long amount) {
        boolean value = MachineIO.inputFluid(this, GTMaterials.SodiumPotassium.getFluid(amount * 20));
        if (heat > 825) {
            if (value) value = MachineIO.outputFluid(this, GTLMaterials.SupercriticalSodiumPotassium.getFluid(amount * 20));
        } else if (value) value = MachineIO.outputFluid(this, GTLMaterials.HotSodiumPotassium.getFluid(amount * 20));
        return value;
    }

    protected void HeatUpdate() {
        if (getOffsetTimer() % 20 == 0) {
            if (heat > 1500) {
                if (damaged > 99) {
                    doExplosion(centrePos, fuel);
                } else {
                    damaged += Math.max(1, heatAdjacent / 6);
                }
            }
            if (getRecipeLogic().isWorking()) {
                int required = recipeHeat * parallel * heat / 1500;
                int surplus = ((cooler - (coolerAdjacent / 3)) * 8) - required;
                boolean isCooler = false;
                if (surplus >= 0) {
                    if (inputWater(required)) {
                        while (surplus >= required && getProgress() < getMaxProgress()) {
                            if (inputWater(required)) {
                                surplus = surplus - required;
                                getRecipeLogic().setProgress(getProgress() + 20);
                            } else {
                                break;
                            }
                        }
                        if (heat > 298 && surplus >= required && inputWater(required)) {
                            heat--;
                        }
                        isCooler = true;
                    } else if (inputSodiumPotassium(required)) {
                        while (surplus >= required && getProgress() < getMaxProgress()) {
                            if (inputSodiumPotassium(required)) {
                                surplus = surplus - required;
                                getRecipeLogic().setProgress(getProgress() + 20);
                            } else {
                                break;
                            }
                        }
                        if (heat > 298 && surplus >= required && inputSodiumPotassium(required)) {
                            heat--;
                        }
                        isCooler = true;
                    }
                }
                if (!isCooler) {
                    heat += recipeHeat * heatAdjacent;
                }
            } else {
                if (heat > 298) {
                    heat--;
                } else if (damaged > 0) {
                    damaged--;
                }
            }
        }
    }

    @Nullable
    public static GTRecipe recipeModifier(MetaMachine machine, @NotNull GTRecipe recipe) {
        if (machine instanceof FissionReactorMachine fissionReactorMachine) {
            Pair<GTRecipe, Integer> result = GTRecipeModifiers.accurateParallel(machine, recipe,
                    fissionReactorMachine.fuel, false);
            GTRecipe recipe1 = result.getFirst();
            fissionReactorMachine.parallel = result.getSecond();
            fissionReactorMachine.recipeHeat = recipe1.data.getInt("FRheat");
            return recipe1;
        }
        return null;
    }

    @Override
    public int getOutputSignal(@Nullable Direction side) {
        return 22500 / heat;
    }

    @Override
    public void onWaiting() {
        super.onWaiting();
        getRecipeLogic().resetRecipeLogic();
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if (isFormed()) {
            textList.add(Component.translatable("gtceu.machine.fission_reactor.fuel", fuel, heatAdjacent - 1));
            textList.add(Component.translatable("gtceu.machine.fission_reactor.cooler", cooler, coolerAdjacent));
            textList.add(Component.translatable("gtceu.machine.fission_reactor.heat", heat));
            textList.add(Component.translatable("gtceu.machine.fission_reactor.damaged", damaged).append("%"));
        }
    }
}
