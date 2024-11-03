package org.gtlcore.gtlcore.data.lang;

import org.gtlcore.gtlcore.common.data.GTLMaterials;
import org.gtlcore.gtlcore.common.data.GTLRecipeTypes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraftforge.common.data.LanguageProvider;

public class LangHandler {

    public static void enInitialize(LanguageProvider provider) {
        ItemLang.init(provider);
        BlockLang.init(provider);
        MachineLang.init(provider);
        for (String name : GTLMaterials.ALL_MATERIAL) {
            provider.add("material.gtceu." + name, FormattingUtil.toEnglishName(name));
        }

        for (String name : GTLRecipeTypes.ALL_RECIPE_TYPE) {
            provider.add("gtceu." + name, FormattingUtil.toEnglishName(name));
        }

        provider.add("gtceu.primitive_void_ore", "Primitive Void Ore");

        for (int tier = GTValues.UHV; tier <= 16; tier++) {
            provider.add("gtceu.machine.parallel_hatch_mk" + tier + ".tooltip", "Allows to run up to " + ((int) Math.pow(4, tier - 4)) + " recipes in parallel.");
        }

        provider.add("gtceu.machine.available_recipe_map_5.tooltip", "Available Recipe Types: %s, %s, %s, %s, %s");
        provider.add("gtceu.machine.available_recipe_map_6.tooltip", "Available Recipe Types: %s, %s, %s, %s, %s, %s");

        provider.add("key.gtlcore.flyingspeed", "Flight Speed Adjustment");
        provider.add("key.gtlcore.nightvision", "Night Vision Toggle");
        provider.add("key.gtlcore.pearl", "Pearl Key");
        provider.add("key.gtlcore.vajra", "Vajra Key");
        provider.add("key.keybinding.gtlcore", "GTL Key Bindings");

        provider.add("structure_writer.export_order", "Export Order: C:%s  S:%s  A:%s");
        provider.add("structure_writer.structural_scale", "Structural Scale: X:%s  Y:%s  Z:%s");

        provider.add("gtlcore.pattern.multiply", "Pattern Recipe x %s");
        provider.add("gtlcore.pattern.tooltip.multiply", "Multiply Pattern materials and recipe amount by %s");
        provider.add("gtlcore.pattern.divide", "Pattern Recipe ÷ %s");
        provider.add("gtlcore.pattern.tooltip.divide", "Divide Pattern materials and recipe amount by %s");

        provider.add("gtlcore.handheld_data_required", "Handheld Data Required");
        provider.add("gtlcore.structural_error", "Structural Error");
        provider.add("gtlcore.position_are_saved_to_this_slot", "Position Are Saved To This Slot");
        provider.add("gtlcore.relocate_mined_blocks", "Relocate Mined Blocks %s");
        provider.add("gtlcore.fly_speed_reset", "fly Speed Reset");
        provider.add("gtlcore.fly_speed", "fly Speed x%s");
        provider.add("gtlcore.reach_limit", "Reach Limit");

        provider.add("config.gtlcore.option.cellType", "AE Disk Storage Type Multiplier");
        provider.add("config.gtlcore.option.disableDrift", "Disable Flight Inertia");
        provider.add("config.gtlcore.option.durationMultiplier", "Recipe Duration Multiplier");
        provider.add("config.gtlcore.option.eioFluidRate", "EIO Fluid Pipe Rate Multiplier");
        provider.add("config.gtlcore.option.enablePrimitiveVoidOre", "Enable Primitive Void Ore Machine");
        provider.add("config.gtlcore.option.exPatternProvider", "Extended Pattern Provider Capacity");
        provider.add("config.gtlcore.option.mobList1", "Slaughterhouse Non-Hostile Creature List");
        provider.add("config.gtlcore.option.mobList2", "Slaughterhouse Hostile Creature List");
        provider.add("config.gtlcore.option.oreMultiplier", "Ore Yield Multiplier");
        provider.add("config.gtlcore.option.spacetimePip", "Spacetime Pipe Flow");

        provider.add("gtceu.jei.bedrock_fluid.benzene_deposit", "Benzene Deposit");
        provider.add("gtceu.jei.bedrock_fluid.ceres_krypton_deposit", "Krypton Deposit");
        provider.add("gtceu.jei.bedrock_fluid.ceres_neon_deposit", "Neon Deposit");
        provider.add("gtceu.jei.bedrock_fluid.ceres_radon_deposit", "Radon Deposit");
        provider.add("gtceu.jei.bedrock_fluid.ceres_xenon_deposit", "Xenon Deposit");
        provider.add("gtceu.jei.bedrock_fluid.charcoal_byproducts", "Charcoal Byproducts Deposit");
        provider.add("gtceu.jei.bedrock_fluid.chlorine", "Chlorine Deposit");
        provider.add("gtceu.jei.bedrock_fluid.coal_gas_deposit", "Coal Gas Deposit");
        provider.add("gtceu.jei.bedrock_fluid.deuterium_deposit", "Deuterium Deposit");
        provider.add("gtceu.jei.bedrock_fluid.flat_heavy_oil_deposit", "Flat Heavy Oil Deposit");
        provider.add("gtceu.jei.bedrock_fluid.flat_light_oil_deposit", "Flat Light Oil Deposit");
        provider.add("gtceu.jei.bedrock_fluid.flat_natural_gas_deposit", "Flat Natural Gas Deposit");
        provider.add("gtceu.jei.bedrock_fluid.flat_oil_deposit", "Flat Oil Deposit");
        provider.add("gtceu.jei.bedrock_fluid.flat_raw_oil_deposit", "Flat Raw Oil Deposit");
        provider.add("gtceu.jei.bedrock_fluid.flat_salt_water_deposit", "Flat Salt Water Deposit");
        provider.add("gtceu.jei.bedrock_fluid.fluorine", "Fluorine Deposit");
        provider.add("gtceu.jei.bedrock_fluid.helium3_deposit", "Helium-3 Deposit");
        provider.add("gtceu.jei.bedrock_fluid.helium_deposit", "Helium Deposit");
        provider.add("gtceu.jei.bedrock_fluid.hydrochloric_acid_deposit", "Hydrochloric Acid Deposit");
        provider.add("gtceu.jei.bedrock_fluid.methane_deposit", "Methane Deposit");
        provider.add("gtceu.jei.bedrock_fluid.nitric_acid_deposit", "Nitric Acid Deposit");
        provider.add("gtceu.jei.bedrock_fluid.radon_deposit", "Radon Deposit");
        provider.add("gtceu.jei.bedrock_fluid.sulfuric_acid_deposit", "Sulfuric Acid Deposit");
        provider.add("gtceu.jei.bedrock_fluid.unknowwater", "Unknown Liquid Deposit");
        provider.add("gtceu.jei.bedrock_fluid.void_heavy_oil_deposit", "Void Heavy Oil Deposit");
        provider.add("gtceu.jei.bedrock_fluid.void_light_oil_deposit", "Void Light Oil Deposit");
        provider.add("gtceu.jei.bedrock_fluid.void_natural_gas_deposit", "Void Natural Gas Deposit");
        provider.add("gtceu.jei.bedrock_fluid.void_oil_deposit", "Void Oil Deposit");
        provider.add("gtceu.jei.bedrock_fluid.void_raw_oil_deposit", "Void Raw Oil Deposit");
        provider.add("gtceu.jei.bedrock_fluid.void_salt_water_deposit", "Void Salt Water Deposit");
        provider.add("gtceu.jei.ore_vein.apatite_vein_ad", "Apatite Vein");
        provider.add("gtceu.jei.ore_vein.bauxite_vein_ad", "Bauxite Vein");
        provider.add("gtceu.jei.ore_vein.beryllium_vein_aw", "Beryllium Vein");
        provider.add("gtceu.jei.ore_vein.calorite_vein_ad", "Calorite Vein");
        provider.add("gtceu.jei.ore_vein.celestine_vein_ad", "Celestine Vein");
        provider.add("gtceu.jei.ore_vein.certus_quartz_vein_aw", "Certus Quartz Vein");
        provider.add("gtceu.jei.ore_vein.desh_vein_ad", "Desh Vein");
        provider.add("gtceu.jei.ore_vein.molybdenum_vein_aw", "Molybdenum Vein");
        provider.add("gtceu.jei.ore_vein.monazite_vein_ad", "Monazite Vein");
        provider.add("gtceu.jei.ore_vein.naquadah_vein_ad", "Naquadah Vein");
        provider.add("gtceu.jei.ore_vein.nickel_vein_ad", "Nickel Vein");
        provider.add("gtceu.jei.ore_vein.olivine_vein_ad", "Olivine Vein");
        provider.add("gtceu.jei.ore_vein.ostrum_vein_ad", "Ostrum Vein");
        provider.add("gtceu.jei.ore_vein.pitchblende_vein_ad", "Pitchblende Vein");
        provider.add("gtceu.jei.ore_vein.plutonium_vein_ad", "Plutonium Vein");
        provider.add("gtceu.jei.ore_vein.quartzite_vein_aw", "Quartzite Vein");
        provider.add("gtceu.jei.ore_vein.saltpeter_vein_aw", "Saltpeter Vein");
        provider.add("gtceu.jei.ore_vein.scheelite_vein_ad", "Scheelite Vein");
        provider.add("gtceu.jei.ore_vein.sheldonite_vein_ad", "Sheldonite Vein");
        provider.add("gtceu.jei.ore_vein.stibnite_vein_aw", "Stibnite Vein");
        provider.add("gtceu.jei.ore_vein.sulfur_vein_ad", "Sulfur Vein");
        provider.add("gtceu.jei.ore_vein.sulfur_vein_aw", "Sulfur Vein");
        provider.add("gtceu.jei.ore_vein.topaz_vein_aw", "Topaz Vein");
        provider.add("gtceu.jei.ore_vein.zircon_vein_ad", "Zircon Vein");

        provider.add("tagprefix.ceresstone", "Ceres %s Ore");
        provider.add("tagprefix.contaminable_nanoswarm", "Contaminable Nano Swarm");
        provider.add("tagprefix.enceladusstone", "Enceladus %s Ore");
        provider.add("tagprefix.ganymedestone", "Ganymede %s Ore");
        provider.add("tagprefix.glacio_stone", "Glacio %s Ore");
        provider.add("tagprefix.iostone", "Io %s Ore");
        provider.add("tagprefix.magiccrystal", "%s Magic Crystal");
        provider.add("tagprefix.mars_stone", "Mars %s Ore");
        provider.add("tagprefix.mercury_stone", "Mercury %s Ore");
        provider.add("tagprefix.milled", "Milled %s");
        provider.add("tagprefix.moon_stone", "Moon %s Ore");
        provider.add("tagprefix.nanoswarm", "%s Nano Swarm");
        provider.add("tagprefix.plutostone", "Pluto %s Ore");
        provider.add("tagprefix.titanstone", "Titan %s Ore");
        provider.add("tagprefix.venus_stone", "Venus %s Ore");

        provider.add("gtlcore.recipe.ca_tier", "Casing Tier: %s");
        provider.add("gtlcore.recipe.ev_max", "Maximum Neutron Energy: %s MeV");
        provider.add("gtlcore.recipe.ev_min", "Minimum Neutron Energy: %s MeV");
        provider.add("gtlcore.recipe.evt", "Energy Consumption per Tick: %s KeV");
        provider.add("gtlcore.recipe.frheat", "Heating per Second: %s K");
        provider.add("gtlcore.recipe.grindball", "macerator Ball Material: %s");
        provider.add("gtlcore.recipe.law_cleanroom.display_name", "Absolute Clean Room");
        provider.add("gtlcore.recipe.nano_forge_tier", "Nano Forge Tier: %s");
        provider.add("gtlcore.recipe.radioactivity", "Radiation Dose: %s Sv");
        provider.add("gtlcore.recipe.sepm_tier", "Required Power Module: MK%s");
        provider.add("gtlcore.recipe.stellar_containment_tier", "Stellar Container Tier: %s");
        provider.add("gtlcore.recipe.vacuum.tier", "Vacuum Tier: %s");
        provider.add("gtlcore.registry.modify", "Modified by GregTech Leisure");
        provider.add("gtlcore.tier.advanced", "Advanced");
        provider.add("gtlcore.tier.base", "Basic");
        provider.add("gtlcore.tier.ultimate", "Ultimate");
        provider.add("gtlcore.universal.tooltip.ampere_out", "§bOutput Current: §r%sA");
        provider.add("gtlcore.casings.tier", "Tier: %s");
        provider.add("gtlcore.condition.gravity", "Requires Strong Gravity Environment");
        provider.add("gtlcore.condition.zero_gravity", "Requires Zero Gravity Environment");
    }
}
