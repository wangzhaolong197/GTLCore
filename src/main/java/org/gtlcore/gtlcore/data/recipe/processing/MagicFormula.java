package org.gtlcore.gtlcore.data.recipe.processing;

import org.gtlcore.gtlcore.GTLCore;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.tterrag.registrate.util.entry.ItemEntry;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.gtlcore.gtlcore.api.data.tag.GTLTagPrefix.*;
import static org.gtlcore.gtlcore.common.data.GTLItems.*;
import static org.gtlcore.gtlcore.common.data.GTLMaterials.*;
import static org.gtlcore.gtlcore.common.data.GTLRecipeTypes.*;

public class MagicFormula {

    public static void init(Consumer<FinishedRecipe> provider) {
        Material[] SomeMagicStones = {
                LowGuideMagic, MiddleGuideMagic, HighGuideMagic, LimitlessGuideMagic,
                NonattributeMagicCrystals, ChargeMagicCrystals, FocusMagicCrystals, ShiningMagicCrystals,
                NaturalMagicCrystals, OceanMagicCrystals, ColdMagicCrystals, HiddenMagicCrystals,
                AncientMagicCrystals, StarrySkyMagicCrystals, PurgatoryMagicCrystals, EndMagicCrystals,
                AbyssMagicCrystals, SunMagicCrystals, TimeMagicCrystals, SpaceMagicCrystals
        };
        Material[] SomeMagicStonesTurbid = {

                NonattributeMagicCrystalsTurbid, ChargeMagicCrystalsTurbid, FocusMagicCrystalsTurbid, ShiningMagicCrystalsTurbid,
                NaturalMagicCrystalsTurbid, OceanMagicCrystalsTurbid, ColdMagicCrystalsTurbid, HiddenMagicCrystalsTurbid,
                AncientMagicCrystalsTurbid, StarrySkyMagicCrystalsTurbid, PurgatoryMagicCrystalsTurbid, EndMagicCrystalsTurbid,
                AbyssMagicCrystalsTurbid, SunMagicCrystalsTurbid, TimeMagicCrystalsTurbid, SpaceMagicCrystalsTurbid
        };
        Material[] SomeMagicSource = {
                NaturalMagicSource, OceanMagicSource, ColdMagicSource, HiddenMagicSource,
                AncientMagicSource, StarrySkyMagicSource, PurgatoryMagicSource, EndMagicSource,
                AbyssMagicSource, SunMagicSource, TimeMagicSource, SpaceMagicSource
        };

        int[] SomeMagicStonetime = {
                200, 120000, 72000000, 2147483647,
                200, 120000, 72000000, 2147483647,
                600, 600, 600, 600,
                30000, 30000, 30000, 30000,
                15000000, 15000000, 2147483647, 2147483647
        };
        int[] SomeMagicSourcNumber = {
                10, 30, 90, 150,
        };
        int[] SomeMagicSourceVoltage = {
                30, 500, 8000, 130000,
                30, 30, 30, 30,
                500, 500, 8000, 8000,
                520000, 520000, 33000000, 33000000
        };

        /// 魔石启辉得到魔晶
        for (int i = 0; i < 16; i++) {
            Material MagicStone = SomeMagicStones[i];

            DARK_PEARL_QIHUI.recipeBuilder(GTLCore.id("anzhu_qihui_rawore_" + i))
                    .inputItems(rawOre, MagicStone, 2)
                    .chancedOutput(magiccrystal, MagicStone, 1, 1000, 0)
                    .duration(SomeMagicStonetime[i])
                    .save(provider);

            DARK_PEARL_QIHUI.recipeBuilder(GTLCore.id("anzhu_qihui_ore_" + i))
                    .inputItems(ore, MagicStone, 1)
                    .chancedOutput(magiccrystal, MagicStone, 1, 1000, 0)
                    .duration(SomeMagicStonetime[i])
                    .save(provider);
        }
        for (int i = 0; i < 20; i++) {
            Material MagicStone = SomeMagicStones[i];

            DARK_PEARL_QIHUI.recipeBuilder(GTLCore.id("anzhu_qihui_gemexquisite_" + i))
                    .inputItems(gemExquisite, MagicStone, 1)
                    .chancedOutput(magiccrystal, MagicStone, 1, 8000, 0)
                    .duration(SomeMagicStonetime[i])
                    .save(provider);

            MACERATOR_RECIPES.recipeBuilder(GTLCore.id("anzhu_qihui_gemexquisite_" + i))
                    .inputItems(magiccrystal, MagicStone, 12)
                    .outputItems(dust, MagicStone, 16)
                    .EUt(VA[ULV])
                    .duration(20)
                    .save(provider);
        }

        /// 魔石粉+盐酸提取源质
        for (int i = 4; i < 8; i++) {
            Material MagicStone = SomeMagicStones[i];
            Material MagicStonesTurbid = SomeMagicStonesTurbid[i - 4];

            MIXER_RECIPES.recipeBuilder(GTLCore.id("mixer_turbid_" + i))
                    .inputItems(dust, MagicStone, 1)
                    .inputFluids(HydrochloricAcid.getFluid(1000))
                    .outputFluids(MagicStonesTurbid.getFluid(1000))
                    .duration(20)
                    .EUt(SomeMagicSourceVoltage[i - 4])
                    .save(provider);

            DISTILLATION_RECIPES.recipeBuilder(GTLCore.id("distillery_turbid_" + i))
                    .inputFluids(MagicStonesTurbid.getFluid(100))
                    .outputFluids(DilutedHydrochloricAcid.getFluid(80))
                    .outputFluids(MagicSource.getFluid(SomeMagicSourcNumber[i - 4]))
                    .outputItems(MAGICAL_RESIDUE, 1)
                    .duration(200)
                    .EUt(SomeMagicSourceVoltage[i - 4])
                    .save(provider);
        }
        for (int i = 8; i < 20; i++) {
            Material MagicStone = SomeMagicStones[i];
            Material MagicStonesTurbid = SomeMagicStonesTurbid[i - 4];
            Material MagicSources = SomeMagicSource[i - 8];
            ItemEntry<Item> TwoResidue;
            if (i < 16) {
                TwoResidue = MAGICAL_RESIDUE;
            } else {
                TwoResidue = ARCANE_RESIDUE;
            } ;

            MIXER_RECIPES.recipeBuilder(GTLCore.id("mixer_turbid_" + i))
                    .inputItems(dust, MagicStone, 1)
                    .inputFluids(HydrochloricAcid.getFluid(1000))
                    .outputFluids(MagicStonesTurbid.getFluid(1000))
                    .duration(20)
                    .EUt(SomeMagicSourceVoltage[i - 4])
                    .save(provider);

            DISTILLATION_RECIPES.recipeBuilder(GTLCore.id("distillery_turbid_" + i))
                    .inputFluids(MagicStonesTurbid.getFluid(100))
                    .outputFluids(DilutedHydrochloricAcid.getFluid(80))
                    .outputFluids(MagicSources.getFluid(10))
                    .outputItems(TwoResidue, 1)
                    .duration(200)
                    .EUt(SomeMagicSourceVoltage[i - 4])
                    .save(provider);
        }

        /// 导魔粉筛选分化
        {
            SIFTER_RECIPES.recipeBuilder(GTLCore.id("sifter_lowguidemagic"))
                    .inputItems(dust, LowGuideMagic, 16)
                    .chancedOutput(dust, LowGuideMagic, 16, 8000, 0)
                    .chancedOutput(dust, MiddleGuideMagic, 12, 2000, 0)
                    .chancedOutput(dust, HighGuideMagic, 8, 1000, 0)
                    .chancedOutput(dust, LimitlessGuideMagic, 4, 500, 0)
                    .chancedOutput(new ItemStack(MAGICAL_RESIDUE, 2), 100, 0)
                    .chancedOutput(new ItemStack(ARCANE_RESIDUE, 1), 10, 0)
                    .EUt(VA[MV])
                    .duration(400)
                    .save(provider);

            SIFTER_RECIPES.recipeBuilder(GTLCore.id("sifter_middleguidemagic"))
                    .inputItems(dust, MiddleGuideMagic, 16)
                    .chancedOutput(dust, LowGuideMagic, 12, 2000, 0)
                    .chancedOutput(dust, MiddleGuideMagic, 16, 8000, 0)
                    .chancedOutput(dust, HighGuideMagic, 12, 2000, 0)
                    .chancedOutput(dust, LimitlessGuideMagic, 8, 1000, 0)
                    .chancedOutput(new ItemStack(MAGICAL_RESIDUE, 4), 500, 0)
                    .chancedOutput(new ItemStack(ARCANE_RESIDUE, 2), 100, 0)
                    .EUt(VA[EV])
                    .duration(400)
                    .save(provider);

            SIFTER_RECIPES.recipeBuilder(GTLCore.id("sifter_highguidemagic"))
                    .inputItems(dust, HighGuideMagic, 16)
                    .chancedOutput(dust, LowGuideMagic, 8, 1000, 0)
                    .chancedOutput(dust, MiddleGuideMagic, 12, 2000, 0)
                    .chancedOutput(dust, HighGuideMagic, 16, 8000, 0)
                    .chancedOutput(dust, LimitlessGuideMagic, 12, 2000, 0)
                    .chancedOutput(new ItemStack(MAGICAL_RESIDUE, 8), 1000, 0)
                    .chancedOutput(new ItemStack(ARCANE_RESIDUE, 4), 500, 0)
                    .EUt(VA[LuV])
                    .duration(400)
                    .save(provider);

            SIFTER_RECIPES.recipeBuilder(GTLCore.id("sifter_limitlessguidemagic"))
                    .inputItems(dust, LimitlessGuideMagic, 16)
                    .chancedOutput(dust, LowGuideMagic, 4, 500, 0)
                    .chancedOutput(dust, MiddleGuideMagic, 8, 1000, 0)
                    .chancedOutput(dust, HighGuideMagic, 12, 2000, 0)
                    .chancedOutput(dust, LimitlessGuideMagic, 16, 8000, 0)
                    .chancedOutput(new ItemStack(MAGICAL_RESIDUE, 12), 2000, 0)
                    .chancedOutput(new ItemStack(ARCANE_RESIDUE, 8), 1000, 0)
                    .EUt(VA[LuV])
                    .duration(400)
                    .save(provider);

        }

        /// 奥术源质提取
        {
            EVAPORATION_RECIPES.recipeBuilder(GTLCore.id("evaporate_magicsource"))
                    .inputFluids(MagicSource.getFluid(10000))
                    .outputFluids(ConcentratedMagicSource.getFluid(100))
                    .duration(1000)
                    .EUt(VA[HV])
                    .save(provider);

            VACUUM_RECIPES.recipeBuilder(GTLCore.id("evaporate_magicsource"))
                    .inputFluids(ConcentratedMagicSource.getFluid(1000))
                    .inputFluids(Helium.getFluid(FluidStorageKeys.LIQUID, 5000))
                    .outputFluids(Helium.getFluid(3000))
                    .outputItems(CONDENSED_MAGIC_SOURCE, 3)
                    .duration(100)
                    .EUt(VA[EV])
                    .save(provider);

            MACERATOR_RECIPES.recipeBuilder(GTLCore.id("crushing_magicsource"))
                    .inputItems(CONDENSED_MAGIC_SOURCE, 1)
                    .outputItems(CRUSHED_CONDENSED_MAGIC_SOURCE, 1)
                    .duration(400)
                    .EUt(VA[ULV])
                    .save(provider);

            SIFTER_RECIPES.recipeBuilder(GTLCore.id("sifter_magicsource"))
                    .inputItems(CRUSHED_CONDENSED_MAGIC_SOURCE, 1)
                    .outputItems(CONCENTRATED_MAGIC_ESSENCE_CRYSTAL, 1)
                    .chancedOutput(new ItemStack(IMPURE_ARCANE_ELEMENTIUM_CRYSTAL, 1), 1500, 0)
                    .chancedOutput(new ItemStack(CRYSTALS_CONTAINING_THE_BREATH_OF_THE_ABYSS, 1), 500, 0)
                    .chancedOutput(new ItemStack(CRYSTALS_CONTAINING_THE_BREATH_OF_THE_SUN, 1), 500, 0)
                    .chancedOutput(new ItemStack(MAGICAL_RESIDUE, 1), 4000, 0)
                    .duration(400)
                    .EUt(VA[HV])
                    .save(provider);
        }
    }
}
