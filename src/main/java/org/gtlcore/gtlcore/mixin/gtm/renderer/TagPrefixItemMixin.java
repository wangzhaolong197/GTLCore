package org.gtlcore.gtlcore.mixin.gtm.renderer;

import org.gtlcore.gtlcore.api.data.chemical.material.info.GTLMaterialIconSet;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.TagPrefixItem;
import com.gregtechceu.gtceu.api.item.component.ICustomRenderer;

import com.lowdragmc.lowdraglib.Platform;
import com.lowdragmc.lowdraglib.client.renderer.IItemRendererProvider;
import com.lowdragmc.lowdraglib.client.renderer.IRenderer;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TagPrefixItem.class)
public class TagPrefixItemMixin implements IItemRendererProvider {

    @Unique
    private ICustomRenderer gtlcore$customRenderer;

    @Inject(method = "<init>(Lnet/minecraft/world/item/Item$Properties;Lcom/gregtechceu/gtceu/api/data/tag/TagPrefix;Lcom/gregtechceu/gtceu/api/data/chemical/material/Material;)V", at = @At(value = "RETURN"), remap = false)
    private void TagPrefixItem(Item.Properties properties, TagPrefix tagPrefix, Material material, CallbackInfo ci) {
        if (Platform.isClient()) {
            if (material.getMaterialIconSet() instanceof GTLMaterialIconSet iconSet) {
                this.gtlcore$customRenderer = iconSet.getCustomRenderer();
            }
        }
    }

    @Nullable
    @Override
    public IRenderer getRenderer(ItemStack stack) {
        if (gtlcore$customRenderer != null) {
            return gtlcore$customRenderer.getRenderer();
        }
        return null;
    }
}
