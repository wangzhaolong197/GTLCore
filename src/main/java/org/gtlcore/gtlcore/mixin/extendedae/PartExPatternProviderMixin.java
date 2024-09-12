package org.gtlcore.gtlcore.mixin.extendedae;

import com.glodblock.github.extendedae.common.parts.PartExPatternProvider;
import org.gtlcore.gtlcore.config.ConfigHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PartExPatternProvider.class)
public class PartExPatternProviderMixin {

    @ModifyConstant(method = "createLogic", remap = false, constant = @Constant(intValue = 36))
    private int modifyContainer(int constant) {
        return ConfigHolder.INSTANCE.exPatternProvider;
    }
}