package org.gtlcore.gtlcore.data.recipe.processing;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static org.gtlcore.gtlcore.api.data.tag.GTLTagPrefix.magiccrystal;
import static org.gtlcore.gtlcore.common.data.GTLMaterials.*;
import static org.gtlcore.gtlcore.common.data.GTLRecipeTypes.*;

public class MagicFormula {

    public static void init(Consumer<FinishedRecipe> provider) {
        YUANHU_HUIXIANG.recipeBuilder("magic_formula_1")
                .inputItems(magiccrystal, LowGuideMagic, 4)
                .inputItems(block, PulsatingAlloy, 1)
                .duration(200)
                .save(provider);

        Material[] SomeMagicstones = {
                LowGuideMagic,
                MiddleGuideMagic,
                HighGuideMagic,
                NonattributeMagicCrystals,
                NaturalMagicCrystals,
                OceanMagicCrystals,
                ColdMagicCrystals,
                HiddenMagicCrystals,
                AncientMagicCrystals,
                PurgatoryMagicCrystals,
                EndMagicCrystals,
                StarrySkyMagicCrystals,
                AbyssMagicCrystals,
                SunMagicCrystals,
                TimeMagicCrystals,
                SpaceMagicCrystals
        };

        int[] MagicStonetime = {
                1000,
                1000000,
                1000000000,
                1000,
                2000,
                2000,
                2000,
                2000,
                2000000,
                2000000,
                2000000,
                2000000,
                2000000000,
                2000000000,
                2000000000,
                2000000000
        };

        for (int i = 0; i < SomeMagicstones.length; i++) {
            Material MagicStone = SomeMagicstones[i];

            ANZHU_QIHUI.recipeBuilder("anzhu_qihui_rawOre_" + i)
                    .inputItems(rawOre, MagicStone, 2)
                    .chancedOutput(magiccrystal, MagicStone, 1, 1000, 0)
                    .duration(MagicStonetime[i])
                    .save(provider);

            ANZHU_QIHUI.recipeBuilder("anzhu_qihui_ore_" + i)
                    .inputItems(ore, MagicStone, 1)
                    .chancedOutput(magiccrystal, MagicStone, 1, 1000, 0)
                    .duration(MagicStonetime[i])
                    .save(provider);

        }
    }
}
