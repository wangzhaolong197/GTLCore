package org.gtlcore.gtlcore.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.ToggleButtonWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import com.lowdragmc.lowdraglib.gui.widget.TextBoxWidget;
import com.lowdragmc.lowdraglib.gui.widget.TextFieldWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;
import com.lowdragmc.lowdraglib.utils.Position;
import com.lowdragmc.lowdraglib.utils.Size;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SensorPartMachine extends MultiblockPartMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            SensorPartMachine.class, MultiblockPartMachine.MANAGED_FIELD_HOLDER);

    @Setter
    @Persisted
    @DescSynced
    private float min, max;

    @Persisted
    @Setter
    @Getter
    private boolean isInverted;

    @Getter
    @Persisted
    private int redstoneSignalOutput = 0;

    private final int m;

    public SensorPartMachine(IMachineBlockEntity holder, int m) {
        super(holder);
        this.m = m;
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(Position.ORIGIN, new Size(176, 112));
        group.addWidget(new TextBoxWidget(12, 35, 65, List.of(LocalizationUtils.format("cover.advanced_energy_detector.min") + ":")));
        group.addWidget(new TextBoxWidget(12, 80, 65, List.of(LocalizationUtils.format("cover.advanced_energy_detector.max") + ":")));
        group.addWidget(new TextFieldWidget(80, 26, 85, 18, () -> FormattingUtil.formatNumbers(min), stringValue -> setMin(Mth.clamp(Float.parseFloat(stringValue), 0, max))).setNumbersOnly(0, max));

        group.addWidget(new TextFieldWidget(80, 71, 85, 18, () -> FormattingUtil.formatNumbers(max), stringValue -> setMax(Mth.clamp(Float.parseFloat(stringValue), min, m))).setNumbersOnly(min, m));
        group.addWidget(new ToggleButtonWidget(8, 8, 20, 20,
                GuiTextures.INVERT_REDSTONE_BUTTON, this::isInverted, this::setInverted) {

            @Override
            public void updateScreen() {
                super.updateScreen();
                setHoverTooltips(List.copyOf(LangHandler.getMultiLang(
                        "gtlcore.machine.sensor.invert." + (isPressed ? "enabled" : "disabled"))));
            }
        });
        return group;
    }

    public void update(float a) {
        int output = computeRedstoneBetweenValues(a, max, min, isInverted());
        if (redstoneSignalOutput != output) {
            redstoneSignalOutput = output;
            updateSignal();
        }
    }

    private static int computeRedstoneBetweenValues(float value, float maxValue, float minValue, boolean isInverted) {
        if (value < minValue) {
            return isInverted ? 15 : 0;
        }
        if (value > maxValue) {
            return isInverted ? 0 : 15;
        }
        return Math.round(15 * (isInverted ? (maxValue - value) : (value - minValue)) / (maxValue - minValue));
    }

    @Override
    public int getOutputSignal(@Nullable Direction side) {
        if (side == getFrontFacing().getOpposite()) {
            return redstoneSignalOutput;
        }
        return 0;
    }

    @Override
    public boolean canConnectRedstone(Direction side) {
        return side == getFrontFacing();
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
