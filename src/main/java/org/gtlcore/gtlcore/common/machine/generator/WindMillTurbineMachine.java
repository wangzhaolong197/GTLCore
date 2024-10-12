package org.gtlcore.gtlcore.common.machine.generator;

import org.gtlcore.gtlcore.common.item.KineticRotorItem;
import org.gtlcore.gtlcore.common.machine.multiblock.part.BallHatchPartMachine;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyTooltip;
import com.gregtechceu.gtceu.api.gui.fancy.TooltipsPanel;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.TieredEnergyMachine;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineModifyDrops;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import com.mojang.blaze3d.MethodsReturnNonnullByDefault;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WindMillTurbineMachine extends TieredEnergyMachine implements IMachineModifyDrops, IFancyUIMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            WindMillTurbineMachine.class, TieredEnergyMachine.MANAGED_FIELD_HOLDER);

    @Getter
    @Persisted
    @DescSynced
    private float spinSpeed;

    @Getter
    @DescSynced
    private float bladeAngle;

    @Getter
    @DescSynced
    private int material;

    @Getter
    @DescSynced
    private boolean hasRotor;

    @Getter
    @DescSynced
    private boolean obstructed;

    @DescSynced
    private float wind;

    @DescSynced
    private int actualPower;

    @Persisted
    private final NotifiableItemStackHandler inventory;

    @Nullable
    protected TickableSubscription energySubs;

    public WindMillTurbineMachine(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, args);
        this.inventory = createMachineStorage();
        this.energySubs = subscribeServerTick(energySubs, this::checkEnergy);
    }

    protected NotifiableItemStackHandler createMachineStorage() {
        var storage = new NotifiableItemStackHandler(this, 1, IO.NONE, IO.BOTH);
        storage.setFilter(i -> i.getItem() instanceof KineticRotorItem);
        return storage;
    }

    protected void checkEnergy() {
        if (getOffsetTimer() % 20 == 0) {
            Level level = getLevel();
            if (level == null) return;

            actualPower = 0;

            BlockPos pos = getPos();
            wind = (float) ((level.isThundering() ? 2 : level.isRaining() ? 1.5 : 1) * Math.sqrt(pos.getY()));

            ItemStack stack = inventory.storage.getStackInSlot(0);
            int damage = stack.getDamageValue();
            int maxDamage = stack.getMaxDamage();

            if (damage < maxDamage && stack.getItem() instanceof KineticRotorItem rotorItem) {
                hasRotor = true;
                material = rotorItem.getMaterial();
                obstructed = false;

                Direction facing = getFrontFacing();
                Direction back = facing.getOpposite();
                boolean permuteXZ = back.getAxis() == Direction.Axis.Z;
                BlockPos centerPos = pos.relative(back);
                loop1:
                for (int x = -2; x < 3; x++) {
                    for (int y = -2; y < 3; y++) {
                        if (x == 0 && y == 0) continue;
                        if (MetaMachine.getMachine(level, pos.offset(permuteXZ ? x : 0, y, permuteXZ ? 0 : x)) instanceof WindMillTurbineMachine machine && machine.isHasRotor() && machine.getFrontFacing() == facing) {
                            obstructed = true;
                            break loop1;
                        }
                    }
                }
                loop2:
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        BlockPos blockPos = centerPos.offset(permuteXZ ? x : 0, y, permuteXZ ? 0 : x);
                        List<Entity> entityList = level.getEntitiesOfClass(Entity.class, AABB.ofSize(new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ()), 1, 1, 1));
                        for (Entity e : entityList) {
                            if (e.kjs$isLiving()) e.kjs$attack(20 * spinSpeed);
                            obstructed = true;
                        }
                        if (!level.getBlockState(blockPos).isAir()) {
                            obstructed = true;
                            break loop2;
                        }
                    }
                }

                if (obstructed) {
                    stack.setDamageValue(damage + (int) (40 * spinSpeed));
                    spinSpeed = 0;
                } else if (wind > rotorItem.getMinWind()) {
                    stack.setDamageValue(damage + (int) Math.pow(Math.ceil(wind / rotorItem.getMaxWind()), 8));
                    spinSpeed = Math.min(0.04F * wind, spinSpeed + 0.04F);
                    actualPower = (int) (GTValues.V[tier] * spinSpeed);
                    energyContainer.addEnergy(20L * actualPower);
                }
            } else {
                spinSpeed = 0;
                hasRotor = false;
                inventory.storage.setStackInSlot(0, new ItemStack(Items.AIR));
            }
        }
        bladeAngle += spinSpeed;
    }

    @Override
    public Widget createUIWidget() {
        return BallHatchPartMachine.createSLOTWidget(inventory);
    }

    @Override
    public void attachTooltips(TooltipsPanel tooltipsPanel) {
        tooltipsPanel.attachTooltips(new Basic(() -> GuiTextures.INDICATOR_NO_STEAM.get(false), () -> List.of(Component.translatable("gtceu.multiblock.large_combustion_engine.obstructed").setStyle(Style.EMPTY.withColor(ChatFormatting.RED))), this::isObstructed, () -> null));
        tooltipsPanel.attachTooltips(new IFancyTooltip.Basic(() -> GuiTextures.INFO_ICON,
                () -> List.of(Component.literal("当前风力：" + FormattingUtil.formatNumbers(wind)),
                        Component.literal("能量输出：" + actualPower + " EU/t")),
                () -> true, () -> null));
    }

    @Override
    public void onDrops(List<ItemStack> list) {
        clearInventory(this.inventory.storage);
    }

    @Override
    protected NotifiableEnergyContainer createEnergyContainer(Object... args) {
        long tierVoltage = GTValues.V[getTier()];
        return NotifiableEnergyContainer.emitterContainer(this,
                tierVoltage * 64, tierVoltage, getMaxInputOutputAmperage());
    }

    @Override
    protected boolean isEnergyEmitter() {
        return true;
    }

    @Override
    protected long getMaxInputOutputAmperage() {
        return 2;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
