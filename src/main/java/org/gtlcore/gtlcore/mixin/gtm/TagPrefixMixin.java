package org.gtlcore.gtlcore.mixin.gtm;

import org.gtlcore.gtlcore.api.data.chemical.material.info.GTLMaterialFlags;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(TagPrefix.class)
public class TagPrefixMixin {

    @Shadow(remap = false)
    private Predicate<Material> generationCondition;

    @Inject(method = "generationCondition(Ljava/util/function/Predicate;)Lcom/gregtechceu/gtceu/api/data/tag/TagPrefix;", at = @At("RETURN"), remap = false)
    public void generationCondition(Predicate<Material> generationCondition, CallbackInfoReturnable<TagPrefix> cir) {
        TagPrefix tagPrefix = cir.getReturnValue();
        if (tagPrefix == TagPrefix.rawOre) {
            this.generationCondition = mat -> mat.hasProperty(PropertyKey.ORE) && !mat.hasFlag(GTLMaterialFlags.DISABLE_RAW_ORE);
            return;
        }
        if (tagPrefix == TagPrefix.crushed) {
            this.generationCondition = mat -> mat.hasProperty(PropertyKey.ORE) && !mat.hasFlag(GTLMaterialFlags.DISABLE_CRUSHED);
            return;
        }
        if (tagPrefix == TagPrefix.crushedPurified) {
            this.generationCondition = mat -> mat.hasProperty(PropertyKey.ORE) && !mat.hasFlag(GTLMaterialFlags.DISABLE_CRUSHED_PURIFIED);
            return;
        }
        if (tagPrefix == TagPrefix.crushedRefined) {
            this.generationCondition = mat -> mat.hasProperty(PropertyKey.ORE) && !mat.hasFlag(GTLMaterialFlags.DISABLE_CRUSHED_REFINED);
            return;
        }
        if (tagPrefix == TagPrefix.dustImpure) {
            this.generationCondition = mat -> mat.hasProperty(PropertyKey.ORE) && !mat.hasFlag(GTLMaterialFlags.DISABLE_DUST_IMPURE);
            return;
        }
        if (tagPrefix == TagPrefix.dustPure) {
            this.generationCondition = mat -> mat.hasProperty(PropertyKey.ORE) && !mat.hasFlag(GTLMaterialFlags.DISABLE_DUST_PURE);
        }
    }
}
