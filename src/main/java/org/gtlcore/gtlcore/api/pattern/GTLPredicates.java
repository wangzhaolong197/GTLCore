package org.gtlcore.gtlcore.api.pattern;

import org.gtlcore.gtlcore.api.pattern.util.IValueContainer;
import org.gtlcore.gtlcore.api.pattern.util.SimpleValueContainer;

import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IRotorHolderMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.MultiblockState;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.error.PatternStringError;
import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;

import com.lowdragmc.lowdraglib.utils.BlockInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GTLPredicates {

    public static TraceabilityPredicate createTierPredicate(Map<Integer, Supplier<?>> map, String tierType) {
        BlockInfo[] blockInfos = new BlockInfo[map.size()];
        int index = 0;
        for (Supplier<?> blockSupplier : map.values()) {
            Block block = (Block) blockSupplier.get();
            blockInfos[index++] = BlockInfo.fromBlockState(block.defaultBlockState());
        }
        return new TraceabilityPredicate(blockWorldState -> {
            BlockState blockState = blockWorldState.getBlockState();
            for (Map.Entry<Integer, Supplier<?>> entry : map.entrySet()) {
                if (blockState.is((Block) entry.getValue().get())) {
                    int tier = entry.getKey();
                    int type = blockWorldState.getMatchContext().getOrPut(tierType, tier);
                    if (type != tier) {
                        blockWorldState.setError(new PatternStringError("gtlcore.machine.pattern.error.tier"));
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }, () -> blockInfos).addTooltips(Component.translatable("gtlcore.machine.pattern.error.tier"));
    }

    public static TraceabilityPredicate countBlock(String name, Block... blocks) {
        TraceabilityPredicate inner = Predicates.blocks(blocks);
        Predicate<MultiblockState> predicate = state -> {
            if (inner.test(state)) {
                IValueContainer<?> currentContainer = state.getMatchContext().getOrPut(name + "Value",
                        new SimpleValueContainer<>(0, (integer, block, tierType) -> ++integer));
                currentContainer.operate(state.getBlockState().getBlock(), null);
                return true;
            }
            return false;
        };
        BlockInfo[] candidates = inner.common.stream()
                .map(p -> p.candidates)
                .filter(Objects::nonNull)
                .map(Supplier::get)
                .flatMap(Arrays::stream)
                .toArray(BlockInfo[]::new);
        return new TraceabilityPredicate(new SimplePredicate(predicate, () -> candidates));
    }

    public static TraceabilityPredicate RotorBlock(int tier) {
        return new TraceabilityPredicate(
                new SimplePredicate(state -> {
                    Level world = state.getWorld();
                    BlockPos pos = state.getPos();
                    if (MetaMachine.getMachine(world, pos) instanceof IRotorHolderMachine holder) {
                        return world.getBlockState(pos.relative(holder.self().getFrontFacing())).isAir();
                    }
                    return false;
                }, () -> PartAbility.ROTOR_HOLDER.getAllBlocks()
                        .stream()
                        .filter(b -> b instanceof MetaMachineBlock metaMachineBlock && metaMachineBlock.getDefinition().getTier() >= tier)
                        .map(BlockInfo::fromBlock)
                        .toArray(BlockInfo[]::new)))
                .addTooltips(Component.translatable("gtceu.multiblock.pattern.clear_amount_3"));
    }
}
