package org.gtlcore.gtlcore.common.machine.multiblock.electric;

import org.gtlcore.gtlcore.api.machine.multiblock.TierCasingMachine;
import org.gtlcore.gtlcore.utils.MachineUtil;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;

import com.lowdragmc.lowdraglib.gui.util.ClickData;
import com.lowdragmc.lowdraglib.gui.widget.ComponentPanelWidget;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import earth.terrarium.adastra.common.menus.base.PlanetsMenuProvider;
import earth.terrarium.botarium.common.menu.MenuHooks;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpaceElevatorMachine extends TierCasingMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            SpaceElevatorMachine.class, TierCasingMachine.MANAGED_FIELD_HOLDER);

    public SpaceElevatorMachine(IMachineBlockEntity holder) {
        super(holder, "SEPMTier");
    }

    @Override
    @NotNull
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Getter
    @Persisted
    @DescSynced
    private double high;
    private int mam = 0;
    private Player player;

    private void update(boolean promptly) {
        if (promptly || getOffsetTimer() % 20 == 0) {
            mam = 0;
            Level level = getLevel();
            BlockPos blockPos = MachineUtil.getOffsetPos(3, -2, getFrontFacing(), getPos());
            BlockPos[] coordinatess = new BlockPos[] { blockPos.offset(8, 2, 3),
                    blockPos.offset(8, 2, -3),
                    blockPos.offset(-8, 2, 3),
                    blockPos.offset(-8, 2, -3),
                    blockPos.offset(3, 2, 8),
                    blockPos.offset(-3, 2, 8),
                    blockPos.offset(3, 2, -8),
                    blockPos.offset(-3, 2, -8) };
            for (BlockPos blockPoss : coordinatess) {
                MetaMachine metaMachine = null;
                if (level != null) {
                    metaMachine = MetaMachine.getMachine(level, blockPoss);
                }
                if (metaMachine instanceof SpaceElevatorModuleMachine moduleMachine && moduleMachine.isFormed()) {
                    moduleMachine.spaceElevatorMachine = this;
                    mam++;
                }
            }
        }
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        high = 40;
        update(true);
    }

    @Override
    public boolean onWorking() {
        boolean value = super.onWorking();
        update(false);
        high = 180 + (140 * Math.sin(getOffsetTimer() / 160D));
        return value;
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        this.player = player;
        return true;
    }

    @Override
    public void addDisplayText(@NotNull List<Component> textList) {
        super.addDisplayText(textList);
        if (!this.isFormed) return;
        update(false);
        textList.add(Component.translatable("gtlcore.machine.module", mam));
        textList.add(ComponentPanelWidget.withButton(Component.translatable("gtlcore.machine.space_elevator.set_out"), "set_out"));
    }

    @Override
    public void handleDisplayClick(String componentData, ClickData clickData) {
        if (componentData.equals("set_out") && getRecipeLogic().isWorking()) {
            player.addTag("spaceelevatorst");
            MenuHooks.openMenu((ServerPlayer) player, new PlanetsMenuProvider());
        }
    }
}
