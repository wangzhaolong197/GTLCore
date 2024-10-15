package org.gtlcore.gtlcore.mixin.gtm.recipe.change;

import org.gtlcore.gtlcore.GTLCore;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.serialized.chemistry.PlatGroupMetalsRecipes;

import net.minecraft.data.recipes.FinishedRecipe;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.gtlcore.gtlcore.common.data.GTLMaterials.*;
import static org.gtlcore.gtlcore.common.data.GTLRecipeTypes.DEHYDRATOR_RECIPES;

@Mixin(PlatGroupMetalsRecipes.class)
public class PlatGroupMetalsRecipesMixin {

    @Inject(method = "init", at = @At("HEAD"), remap = false, cancellable = true)
    private static void init(Consumer<FinishedRecipe> provider, CallbackInfo ci) {
        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("pgs_from_chalcocite")).duration(50).EUt(VA[LV])
                .inputItems(crushedPurified, Chalcocite)
                .inputFluids(NitricAcid.getFluid(100))
                .outputItems(dust, PlatinumGroupSludge, 8)
                .outputFluids(SulfuricCopperSolution.getFluid(1000))
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("pgs_from_bornite")).duration(50).EUt(VA[LV])
                .inputItems(crushedPurified, Bornite)
                .inputFluids(NitricAcid.getFluid(100))
                .outputItems(dust, PlatinumGroupSludge, 8)
                .outputFluids(SulfuricCopperSolution.getFluid(1000))
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("pgs_from_tetrahedrite")).duration(50).EUt(VA[LV])
                .inputItems(crushedPurified, Tetrahedrite)
                .inputFluids(NitricAcid.getFluid(100))
                .outputItems(dust, PlatinumGroupSludge, 8)
                .outputFluids(SulfuricCopperSolution.getFluid(1000))
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("pgs_from_cooperite")).duration(50).EUt(VA[LV])
                .inputItems(crushedPurified, Cooperite)
                .inputFluids(NitricAcid.getFluid(100))
                .outputItems(dust, PlatinumGroupSludge, 16)
                .outputFluids(SulfuricNickelSolution.getFluid(1000))
                .save(provider);

        // Aqua Regia
        // HNO3 + HCl -> [HNO3 + HCl]
        MIXER_RECIPES.recipeBuilder(GTLCore.id("aqua_regia")).duration(30).EUt(VA[LV])
                .inputFluids(NitricAcid.getFluid(1000))
                .inputFluids(HydrochloricAcid.getFluid(2000))
                .outputFluids(AquaRegia.getFluid(3000))
                .save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder(GTLCore.id("pgs_separation")).duration(20).EUt(VA[HV])
                .inputItems(dust, PlatinumGroupSludge, 6)
                .inputFluids(AquaRegia.getFluid(1200))
                .chancedOutput(dust, PlatinumSlag, 6, 7500, 500)
                .chancedOutput(dust, PlatinumRaw, 4, 8000, 500)
                .chancedOutput(dust, PalladiumRaw, 4, 8000, 500)
                .chancedOutput(dust, PlatinumSludgeResidue, 2, 8500, 500)
                .chancedOutput(dustTiny, InertMetalMixture, 4, 9000, 500)
                .chancedOutput(dustTiny, RarestMetalMixture, 1, 9000, 100)
                .save(provider);

        BLAST_RECIPES.recipeBuilder(GTLCore.id("leach_residue"))
                .inputItems(dust, PlatinumSlag, 6)
                .inputItems(dust, PotassiumPyrosulfate, 11)
                .inputFluids(Oxygen.getFluid(1000))
                .outputFluids(RhodiumSulfateGas.getFluid(500))
                .outputItems(dust, LeachResidue, 5)
                .outputItems(dust, PotassiumSulfate, 7)
                .blastFurnaceTemp(775)
                .duration(20).EUt(VA[MV]).save(provider);

        BLAST_RECIPES.recipeBuilder(GTLCore.id("rarest_metal_mixture"))
                .inputItems(dust, LeachResidue, 40)
                .inputItems(dust, SodaAsh, 18)
                .inputFluids(Oxygen.getFluid(3000))
                .outputFluids(CarbonMonoxide.getFluid(3000))
                .outputItems(dust, RarestMetalMixture, 20)
                .outputItems(dust, SodiumRutheniate, 21)
                .blastFurnaceTemp(775)
                .duration(20).EUt(VA[MV]).save(provider);

        // Formic Acid
        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("sodium_formate"))
                .inputItems(dust, SodiumHydroxide, 3)
                .inputFluids(CarbonMonoxide.getFluid(1000))
                .outputFluids(SodiumFormate.getFluid(1000))
                .duration(15).EUt(VA[LV]).save(provider);

        // PLATINUM
        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("raw_platinum_separation"))
                .inputItems(dust, PlatinumRaw, 3)
                .inputItems(dust, Calcium, 1)
                .outputItems(dust, Platinum, 1)
                .outputItems(dust, CalciumChloride, 3)
                .duration(30).EUt(VA[LV]).save(provider);

        // PALLADIUM

        LARGE_CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("raw_palladium_separation"))
                .inputItems(dust, PalladiumRaw, 4)
                .inputFluids(FormicAcid.getFluid(4000))
                .outputItems(dust, Palladium, 1)
                .outputFluids(Ammonia.getFluid(4000))
                .outputFluids(Ethylene.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .duration(40).EUt(VA[LV]).save(provider);

        // RHODIUM / RUTHENIUM
        BLAST_RECIPES.recipeBuilder(GTLCore.id("leach_residue_one"))
                .inputItems(dust, InertMetalMixture, 6)
                .inputItems(dust, SodiumNitrate, 10)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(RhodiumSulfateGas.getFluid(500))
                .outputItems(dust, SodiumRutheniate, 7)
                .blastFurnaceTemp(775)
                .duration(50).EUt(VA[MV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("ruthenium_tetroxide"))
                .inputItems(dust, SodiumRutheniate, 7)
                .inputFluids(Chlorine.getFluid(3000))
                .outputFluids(RutheniumTetroxide.getFluid(3000))
                .outputItems(dust, Salt, 12)
                .duration(30).EUt(VA[LV]).save(provider);

        CRACKING_RECIPES.recipeBuilder(GTLCore.id("hot_ruthenium_tetroxide"))
                .inputFluids(RutheniumTetroxide.getFluid(1000))
                .inputFluids(Steam.getFluid(1000))
                .circuitMeta(1)
                .outputFluids(RutheniumTetroxideHot.getFluid(1000))
                .duration(40).EUt(VA[HV]).save(provider);

        DISTILLATION_RECIPES.recipeBuilder(GTLCore.id("hot_ruthenium_tetroxide_distill"))
                .inputFluids(RutheniumTetroxideHot.getFluid(3000))
                .outputItems(dust, Salt, 2)
                .outputFluids(Water.getFluid(1800))
                .outputFluids(RutheniumTetroxideLQ.getFluid(1000))
                .duration(30).EUt(VA[HV]).save(provider);

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder(GTLCore.id("ruthenium_tetroxide_dust"))
                .inputFluids(RutheniumTetroxideLQ.getFluid(1000))
                .outputItems(dust, RutheniumTetroxide, 5)
                .duration(20).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("ruthenium_tetroxide_separation"))
                .inputItems(dust, RutheniumTetroxide, 5)
                .inputFluids(HydrochloricAcid.getFluid(6000))
                .outputItems(dust, Ruthenium, 1)
                .outputFluids(Water.getFluid(2000))
                .outputFluids(Chlorine.getFluid(6000))
                .duration(60).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("rhodium_sulfate_to_solution"))
                .inputFluids(Water.getFluid(1000))
                .inputFluids(RhodiumSulfateGas.getFluid(1000))
                .outputFluids(RhodiumSulfate.getFluid(1000))
                .duration(40).EUt(VA[LV]).save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder(GTLCore.id("crude_rhodium_metallic_powder"))
                .inputItems(dust, Zinc, 1)
                .inputFluids(RhodiumSulfate.getFluid(1000))
                .outputItems(dust, ZincSulfate, 6)
                .outputItems(dust, RoughlyRhodiumMetal, 1)
                .duration(80).EUt(VA[LV]).save(provider);

        BLAST_RECIPES.recipeBuilder(GTLCore.id("rhodium_salt_ebf"))
                .inputItems(dust, RoughlyRhodiumMetal, 1)
                .inputItems(dust, Salt, 2)
                .inputFluids(Chlorine.getFluid(1000))
                .outputItems(dust, RhodiumSalt, 3)
                .blastFurnaceTemp(600)
                .duration(70).EUt(VA[MV]).save(provider);

        MIXER_RECIPES.recipeBuilder(GTLCore.id("rhodium_salt_solution"))
                .inputItems(dust, RhodiumSalt, 4)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(RhodiumSaltSolution.getFluid(1000))
                .duration(60).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("sodium_nitrate"))
                .inputItems(dust, Sodium)
                .inputFluids(NitricAcid.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(1000))
                .outputItems(dust, SodiumNitrate, 5)
                .duration(60).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("rhodium_nitrate"))
                .inputItems(dust, SodiumNitrate, 5)
                .inputFluids(Oxygen.getFluid(2000))
                .inputFluids(NitrogenDioxide.getFluid(2000))
                .inputFluids(RhodiumSaltSolution.getFluid(1000))
                .outputItems(dust, RhodiumNitrate, 13)
                .outputItems(dust, Salt, 4)
                .duration(60).EUt(VA[LV]).save(provider);

        SIFTER_RECIPES.recipeBuilder(GTLCore.id("rhodium_filter_cake"))
                .inputItems(dust, RhodiumNitrate, 13)
                .chancedOutput(dust, RhodiumFilterCake, 8000, 200)
                .chancedOutput(dust, RhodiumFilterCake, 8000, 200)
                .chancedOutput(dust, RhodiumFilterCake, 6000, 100)
                .chancedOutput(dust, RhodiumFilterCake, 6000, 100)
                .chancedOutput(dust, RhodiumFilterCake, 4000, 50)
                .chancedOutput(dust, RhodiumFilterCake, 4000, 50)
                .duration(50).EUt(VA[LV]).save(provider);

        MIXER_RECIPES.recipeBuilder(GTLCore.id("rhodium_filter_cake_solution"))
                .inputItems(dust, RhodiumFilterCake, 6)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(RhodiumFilterCakeSolution.getFluid(1000))
                .duration(40).EUt(VA[LV]).save(provider);

        DISTILLERY_RECIPES.recipeBuilder(GTLCore.id("reprecipitated_rhodium"))
                .inputFluids(RhodiumFilterCakeSolution.getFluid(1000))
                .circuitMeta(1)
                .outputItems(dust, ReprecipitatedRhodium, 7)
                .duration(50).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("rhodium_dust"))
                .inputItems(dust, ReprecipitatedRhodium, 7)
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputItems(dust, Rhodium, 1)
                .outputItems(dust, AmmoniumChloride, 6)
                .outputFluids(Hydrogen.getFluid(1000))
                .duration(70).EUt(VA[LV]).save(provider);

        // OSMIUM / IRIDIUM
        BLAST_RECIPES.recipeBuilder(GTLCore.id("rarest_metal_residue_ebf"))
                .inputItems(dust, RarestMetalMixture, 7)
                .inputFluids(HydrochloricAcid.getFluid(4000))
                .outputItems(dust, IridiumMetalResidue, 5)
                .outputFluids(AcidicOsmiumSolution.getFluid(1600))
                .blastFurnaceTemp(775)
                .duration(20).EUt(VA[MV]).save(provider);

        DISTILLATION_RECIPES.recipeBuilder(GTLCore.id("acidic_osmium_solution_separation")).duration(80).EUt(VA[LV])
                .inputFluids(AcidicOsmiumSolution.getFluid(2000))
                .outputItems(dust, OsmiumTetroxide, 5)
                .outputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("iridium_dioxide_dissolving"))
                .inputItems(dust, IridiumDioxide, 3)
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(AcidicIridium.getFluid(1000))
                .duration(90).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("iridium_chloride"))
                .inputItems(dust, AmmoniumChloride, 18)
                .inputFluids(AcidicIridium.getFluid(1000))
                .outputItems(dust, IridiumChloride, 4)
                .outputFluids(Ammonia.getFluid(3000))
                .duration(40).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("osmium_tetroxide_separation")).duration(70).EUt(VA[LV])
                .inputItems(dust, OsmiumTetroxide, 5)
                .inputFluids(Hydrogen.getFluid(8000))
                .outputItems(dust, Osmium)
                .outputFluids(Water.getFluid(4000))
                .save(provider);

        CHEMICAL_RECIPES.recipeBuilder(GTLCore.id("iridium_chloride_separation")).duration(100).EUt(GTValues.VA[1])
                .inputItems(TagPrefix.dust, GTMaterials.IridiumChloride, 4)
                .inputFluids(GTMaterials.Hydrogen.getFluid(3000L))
                .outputItems(TagPrefix.dust, GTMaterials.Iridium)
                .outputFluids(GTMaterials.HydrochloricAcid.getFluid(3000L))
                .save(provider);
        ci.cancel();
    }
}
