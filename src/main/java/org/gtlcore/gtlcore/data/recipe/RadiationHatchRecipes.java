package org.gtlcore.gtlcore.data.recipe;

import org.gtlcore.gtlcore.GTLCore;
import org.gtlcore.gtlcore.common.data.GTLRecipeTypes;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.Locale;
import java.util.function.Consumer;

public class RadiationHatchRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        registerRadioactiveMaterial(GTMaterials.Uranium238, 8000, 50, provider);
        registerRadioactiveMaterial(GTMaterials.Uranium235, 6500, 100, provider);
        registerRadioactiveMaterial(GTMaterials.Plutonium241, 6000, 110, provider);
        registerRadioactiveMaterial(GTMaterials.Naquadah, 15000, 120, provider);
        registerRadioactiveMaterial(GTMaterials.NaquadahEnriched, 10000, 140, provider);
        registerRadioactiveMaterial(GTMaterials.Naquadria, 7500, 160, provider);
    }

    private static void registerRadioactiveMaterial(Material material, int duration, int radioactivity, Consumer<FinishedRecipe> provider) {
        if (material.hasFlag(MaterialFlags.GENERATE_ROD))
            GTLRecipeTypes.RADIATION_HATCH_RECIPES.recipeBuilder(GTLCore.id("radioactive_material_rod" + material.getName().toLowerCase(Locale.ROOT)))
                    .inputItems(ChemicalHelper.get(TagPrefix.rod, material, 1))
                    .duration(duration)
                    .addData("radioactivity", radioactivity)
                    .save(provider);

        if (material.hasFlag(MaterialFlags.GENERATE_LONG_ROD))
            GTLRecipeTypes.RADIATION_HATCH_RECIPES.recipeBuilder(GTLCore.id("radioactive_material_long_rod" + material.getName().toLowerCase(Locale.ROOT)))
                    .inputItems(ChemicalHelper.get(TagPrefix.rodLong, material, 1))
                    .duration((int) (duration * 1.5))
                    .addData("radioactivity", (int) (radioactivity * 1.2))
                    .save(provider);
    }
}
