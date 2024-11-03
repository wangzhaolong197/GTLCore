package org.gtlcore.gtlcore.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class GTLEBallGeneration extends Explosion {

    private final BlockPos center;
    private final Level level;
    private final int radius;
    @lombok.Setter
    private boolean isBreakBedrock;

    private Map<String, Integer> blockWeights = new HashMap<>();

    public GTLEBallGeneration(BlockPos center, Level level, int radius) {
        super(level, null, null, null, center.getX(), center.getY(), center.getZ(), radius, false, BlockInteraction.DESTROY);
        this.center = center;
        this.level = level;
        this.radius = radius;
    }

    @Override
    public void finalizeExplosion(boolean spawnParticles) {
        int expandedRadius = radius * 5;
        int expandedRadiusSquared = (expandedRadius - 2) * (expandedRadius - 2);
        int originalRadiusSquared = (radius - 2) * (radius - 2);

        if (checkProjectionForAir(radius)) {  // 使用原始半径进行投影检测
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        if (x * x + y * y + z * z <= originalRadiusSquared) {
                            BlockPos pos = center.offset(x, y, z);
                            BlockState state = level.getBlockState(pos);
                            if (!isBreakBedrock && state.getBlock() == Blocks.BEDROCK) continue;
                            replaceBlockWithRandom(pos, getWeightedRandomBlock());
                        }
                    }
                }
            }
        } else {
            super.explode();
            super.finalizeExplosion(spawnParticles);
        }

        // 执行扩展后的爆炸
        super.explode();
        super.finalizeExplosion(spawnParticles);
    }

    private boolean checkProjectionForAir(int radius) {
        int radiusSquared = (radius - 2) * (radius - 2);
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radiusSquared) {
                    for (int y = 0; y < level.getHeight(); y++) {
                        BlockPos pos = center.offset(x, y, z);
                        if (!level.getBlockState(pos).isAir()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void replaceBlockWithRandom(BlockPos pos, Block block) {
        if (block != null) {
            level.setBlock(pos, block.defaultBlockState(), 3);
        }
    }

    public void setBlocksByWeights(Map<String, Integer> blockWeights) {
        this.blockWeights = blockWeights;
    }

    private Block getWeightedRandomBlock() {
        Random random = new Random();
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(blockWeights.entrySet());

        // Calculate total weight
        int totalWeight = blockWeights.values().stream().mapToInt(Integer::intValue).sum();

        // Generate a random number between 0 and totalWeight
        int randomInt = random.nextInt(totalWeight);

        // Select a block based on the random number
        int currentWeightSum = 0;
        for (Map.Entry<String, Integer> entry : entries) {
            currentWeightSum += entry.getValue();
            if (randomInt < currentWeightSum) {
                return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(entry.getKey()));
            }
        }

        // Fallback to the first block if something goes wrong
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(entries.get(0).getKey()));
    }

    // 静态方法，用于在配方中调用
    public static void performExplosion(Level level, BlockPos center, int radius, Map<String, Integer> blockWeights, boolean isBreakBedrock) {
        GTLEBallGeneration explosion = new GTLEBallGeneration(center, level, radius);
        explosion.setBlocksByWeights(blockWeights);
        explosion.isBreakBedrock = isBreakBedrock;
        explosion.finalizeExplosion(true);
    }
}
