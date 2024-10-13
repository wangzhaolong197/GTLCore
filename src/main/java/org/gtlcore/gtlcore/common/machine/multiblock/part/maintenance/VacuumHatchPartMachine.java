package org.gtlcore.gtlcore.common.machine.multiblock.part.maintenance;

import org.gtlcore.gtlcore.api.machine.IVacuumMachine;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.machine.multiblock.part.AutoMaintenanceHatchPartMachine;

public class VacuumHatchPartMachine extends AutoMaintenanceHatchPartMachine implements IVacuumMachine {

    public VacuumHatchPartMachine(IMachineBlockEntity blockEntity) {
        super(blockEntity);
    }

    @Override
    public int getVacuumTier() {
        return 4;
    }
}
