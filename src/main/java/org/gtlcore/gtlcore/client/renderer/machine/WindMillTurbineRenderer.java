package org.gtlcore.gtlcore.client.renderer.machine;

import org.gtlcore.gtlcore.GTLCore;
import org.gtlcore.gtlcore.client.renderer.TurbineModel;
import org.gtlcore.gtlcore.common.machine.generator.WindMillTurbineMachine;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.client.renderer.machine.SimpleGeneratorMachineRenderer;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import java.util.*;

public class WindMillTurbineRenderer extends SimpleGeneratorMachineRenderer {

    private static final TurbineModel MODEL = new TurbineModel();
    public static final List<ResourceLocation> TEXTURES = List.of(
            GTLCore.id("textures/block/generators/wind_mill_turbine/wood_turbine.png"),
            GTLCore.id("textures/block/generators/wind_mill_turbine/metal_turbine.png"),
            GTLCore.id("textures/block/generators/wind_mill_turbine/carbon_turbine.png"));

    public WindMillTurbineRenderer(int tier) {
        super(tier, GTLCore.id("block/generators/wind_mill_turbine"));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(BlockEntity blockEntity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (blockEntity instanceof IMachineBlockEntity machineBlockEntity && machineBlockEntity.getMetaMachine() instanceof WindMillTurbineMachine windMillTurbineMachine && windMillTurbineMachine.isHasRotor()) {
            Level level = blockEntity.getLevel();
            if (level == null) return;
            BlockPos pos = blockEntity.getBlockPos();
            Direction facing = windMillTurbineMachine.getFrontFacing().getOpposite();
            int renderLight = LevelRenderer.getLightColor(level, pos.offset(facing.getNormal()));

            final VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(TEXTURES.get(windMillTurbineMachine.getMaterial())));

            stack.pushPose();
            stack.translate(0.5, 0, 0.5);
            stack.mulPose(Axis.YP.rotationDegrees(-facing.getCounterClockWise().toYRot() + 90));
            stack.translate(0, -1, -0.56);

            float spin = windMillTurbineMachine.getBladeAngle() + partialTicks * windMillTurbineMachine.getSpinSpeed();
            MODEL.setSpin(spin);
            MODEL.renderToBuffer(stack, vertexConsumer, renderLight, combinedOverlay, 1F, 1F, 1F, 1F);

            stack.popPose();
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasTESR(BlockEntity blockEntity) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isGlobalRenderer(BlockEntity blockEntity) {
        return true;
    }
}
