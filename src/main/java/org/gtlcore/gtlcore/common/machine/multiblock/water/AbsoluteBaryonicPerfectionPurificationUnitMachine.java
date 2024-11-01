package org.gtlcore.gtlcore.common.machine.multiblock.water;

import org.gtlcore.gtlcore.api.machine.multiblock.IWaterPurificationMachine;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;

import net.minecraft.MethodsReturnNonnullByDefault;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AbsoluteBaryonicPerfectionPurificationUnitMachine extends WorkableMultiblockMachine implements IWaterPurificationMachine {

    public AbsoluteBaryonicPerfectionPurificationUnitMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public long test() {
        return 0;
    }

    @Override
    public void run() {}

    @Override
    public WorkableMultiblockMachine getMachine() {
        return this;
    }
}
