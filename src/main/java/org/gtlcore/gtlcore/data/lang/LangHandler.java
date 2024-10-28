package org.gtlcore.gtlcore.data.lang;

import org.gtlcore.gtlcore.common.data.GTLMaterials;
import org.gtlcore.gtlcore.common.data.GTLRecipeTypes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraftforge.common.data.LanguageProvider;

public class LangHandler {

    public static void cnInitialize(CNLanguageProvider provider) {}

    public static void enInitialize(LanguageProvider provider) {
        for (String name : GTLMaterials.ALL_MATERIAL) {
            provider.add("material.gtceu." + name, FormattingUtil.toEnglishName(name));
        }

        for (String name : GTLRecipeTypes.ALL_RECIPE_TYPE) {
            provider.add("gtceu." + name, FormattingUtil.toEnglishName(name));
        }

        for (int tier = GTValues.EV; tier <= GTValues.MAX; tier++) {
            provider.add("block.gtceu." + GTValues.VN[tier].toLowerCase() + "_buffer", "%s Buffer %s".formatted(GTValues.VLVH[tier], GTValues.VLVT[tier]));
        }

        for (int tier = GTValues.LV; tier <= GTValues.IV; tier++) {
            provider.add("block.gtceu." + GTValues.VN[tier].toLowerCase() + "_dual_input_hatch", "%s Dual Input Hatch".formatted(GTValues.VNF[tier]));
        }

        for (int tier = GTValues.LV; tier <= GTValues.IV; tier++) {
            provider.add("block.gtceu." + GTValues.VN[tier].toLowerCase() + "_dual_output_hatch", "%s Dual Output Hatch".formatted(GTValues.VNF[tier]));
        }

        for (int tier = GTValues.UHV; tier <= GTValues.MAX; tier++) {
            provider.add("block.gtceu." + GTValues.VN[tier].toLowerCase() + "_parallel_hatch", GTValues.VNF[tier] + " Parallel Control Hatch");
        }

        for (int tier = GTValues.UHV; tier <= 16; tier++) {
            provider.add("gtceu.multiblock.parallel_hatch_mk" + tier + ".tooltip", "Allows to run up to " + ((int) Math.pow(4, tier - 4)) + " recipes in parallel.");
        }

        provider.add("gtceu.machine.available_recipe_map_5.tooltip", "Available Recipe Types: %s, %s, %s, %s, %s");
        provider.add("gtceu.machine.available_recipe_map_6.tooltip", "Available Recipe Types: %s, %s, %s, %s, %s, %s");

        provider.add("block.gtceu.max_256a_laser_source_hatch", "256A §c§lMAX§r Laser Source Hatch");
        provider.add("block.gtceu.max_256a_laser_target_hatch", "256A §c§lMAX§r Laser Target Hatch");
        provider.add("block.gtceu.max_4096a_laser_source_hatch", "4096A §c§lMAX§r Laser Source Hatch");
        provider.add("block.gtceu.max_4096a_laser_target_hatch", "4096A §c§lMAX§r Laser Target Hatch");

        provider.add("gtceu.primitive_void_ore", "Primitive Void Ore");
        provider.add("block.gtlcore.primitive_void_ore", "Primitive Void Ore");

        provider.add("key.gtlcore.flyingspeed", "Flight Speed Adjustment");
        provider.add("key.gtlcore.nightvision", "Night Vision Toggle");
        provider.add("key.gtlcore.pearl", "Pearl Key");
        provider.add("key.gtlcore.vajra", "Vajra Key");
        provider.add("key.keybinding.gtlcore", "GTL Key Bindings");

        provider.add("structure_writer.export_order", "Export Order: C:%s  S:%s  A:%s");
        provider.add("structure_writer.structural_scale", "Structural Scale: X:%s  Y:%s  Z:%s");

        provider.add("item.gtceu.tool.vajra", "%s Vajra");
        provider.add("item.kinetic_rotor.max", "Max Wind Speed: %s");
        provider.add("item.kinetic_rotor.min", "Min Wind Speed: %s");

        provider.add("tooltip.gtlcore.quantum_glass.0", "Dense but Transparent");
        provider.add("tooltip.gtlcore.quantum_glass.1", "§bGlass & Elegance");

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
        provider.add("gtlcore.machine.advanced_assembly_line.tooltip.0", "Can use larger input buses");
        provider.add("gtlcore.machine.advanced_assembly_line.tooltip.1", "Must ensure each item corresponds to the recipe, only data target chambers can be used");
        provider.add("gtlcore.machine.advanced_hyper_reactor.tooltip.0", "Provides different plasmas to achieve different parallelism");
        provider.add("gtlcore.machine.advanced_hyper_reactor.tooltip.1", "Starmetal: 8, Dense Neutrons: 16");
        provider.add("gtlcore.machine.advanced_integrated_ore_processor.tooltip.0", "Maximum Parallelism: 2147483647");
        provider.add("gtlcore.machine.aggregation_device.tooltip.0", "Voltage tier above UEV increases maximum parallelism x2");
        provider.add("gtlcore.machine.alloy_blast_smelter.duration", "Additional Duration Reduction: %s");
        provider.add("gtlcore.machine.alloy_blast_smelter.tooltip.0", "Continuous operation provides additional Duration reduction, up to 0.2x Duration saved");
        provider.add("gtlcore.machine.alloy_blast_smelter.tooltip.1", "Reduction Multiplier: 20÷√Continuous Operation Time");
        provider.add("gtlcore.machine.arcane_integrated_device.tooltip.1", "Height of the Ender Leap Gate: 75, Height of the Machine Host: 93");
        provider.add("gtlcore.machine.assembly_line.tooltip.0", "Each assembly line unit can increase speed by 1%");
        provider.add("gtlcore.machine.bedrock_drilling_rig.tooltip.0", "Requires bedrock to be below the drill head");
        provider.add("gtlcore.machine.bedrock_drilling_rig.tooltip.1", "Each run has a 10% chance to destroy bedrock");
        provider.add("gtlcore.machine.blaze_blast_furnace.tooltip.0", "Requires to provide §b10x(Power÷120)^(1/2)§r of §eLiquid Blaze§r per second");
        provider.add("gtlcore.machine.blaze_blast_furnace.tooltip.1", "Maximum parallelism fixed at 64");
        provider.add("gtlcore.machine.block_conversion_room.am", "Amount converted each time: %s");
        provider.add("gtlcore.machine.block_conversion_room.tooltip.0", "Randomly selects a block from a position inside the machine for conversion every second, circuit must be set to 1 before running");
        provider.add("gtlcore.machine.block_conversion_room.tooltip.1", "For each voltage tier above MV1, the number of blocks converted increases by 4, and will not repeat");
        provider.add("gtlcore.machine.chemical_distort.tooltip.0", "Coil temperature above recipe temperature by 100K, increases parallelism by 4");
        provider.add("gtlcore.machine.chemical_distort.tooltip.1", "Higher voltage will not provide additional temperature");
        provider.add("gtlcore.machine.chemical_energy_devourer.tooltip.0", "Provides §f320mB/s§7 of Liquid Oxygen and consumes §fdouble§7 fuel to produce up to §f%s§7 EU/t of power.");
        provider.add("gtlcore.machine.chemical_energy_devourer.tooltip.1", "Also provides §f480mB/s§7 of Dinitrogen Tetroxide and consumes §fquadruple§7 fuel to produce up to §f%s§7 EU/t of power.");
        provider.add("gtlcore.machine.chemical_plant.tooltip.0", "Coil tier above Bronze increases energy consumption and speed by 5%");
        provider.add("gtlcore.machine.circuit_assembly_line.tooltip.0", "Placing robots with the same recipe in the mainframe increases parallelism by x2");
        provider.add("gtlcore.machine.coil_parallel", "Coil temperature above 900K increases parallelism x2");
        provider.add("gtlcore.machine.cold_ice_freezer.tooltip.0", "Requires to provide §b10x(Voltage tier)²§r of §bLiquid Ice§r per second");
        provider.add("gtlcore.machine.create_aggregation.tooltip.0", "Provides MAX tier voltage and MAX tier computing power in the creation dimension and sets the circuit to 1 to start running");
        provider.add("gtlcore.machine.create_computation.tooltip.0", "Input Voltage: §4§lMAX§r");
        provider.add("gtlcore.machine.dimensionally_transcendent_dirt_forge.tooltip.0", "Has a maximum parallelism of 524288, directly completing the recipe");
        provider.add("gtlcore.machine.dimensionally_transcendent_mixer.tooltip.0", "Time multiplication factor for running the mixer recipe is 0.2");
        provider.add("gtlcore.machine.dimensionally_transcendent_plasma_forge.coil", "Current recipe mode cannot use this coil");
        provider.add("gtlcore.machine.dissolving_tank.tooltip.0", "Must ensure the ratio of input fluid to recipe fluid is the same, otherwise no product output");
        provider.add("gtlcore.machine.door_of_create.tooltip.0", "Provides MAX tier voltage in the main world and sets the circuit to 1 to start running");
        provider.add("gtlcore.machine.duration_multiplier.tooltip", "Duration Multiplication: %s");
        provider.add("gtlcore.machine.dyson_sphere.number", "Launch Times: %s / 10000");
        provider.add("gtlcore.machine.dyson_sphere.tooltip.0", "Starts working after launching Dyson Sphere modules");
        provider.add("gtlcore.machine.dyson_sphere.tooltip.1", "Each run has a (Module Count / 128 + 1)% chance to damage a module");
        provider.add("gtlcore.machine.dyson_sphere.tooltip.2", "When damage exceeds 60%, output efficiency gradually decreases from 100% to 20% with damage value, and outputs a redstone signal enhanced by the damage value");
        provider.add("gtlcore.machine.dyson_sphere.tooltip.3", "When damage reaches 100%, it reduces the number of module launches by one and resets the damage value");
        provider.add("gtlcore.machine.dyson_sphere.tooltip.4", "When damage value is above 60%, launching will not increase the launch count but will reset the damage value");
        provider.add("gtlcore.machine.dyson_sphere.tooltip.5", "Power capacity and demand computing power are determined by the number of launched modules");
        provider.add("gtlcore.machine.dyson_sphere.tooltip.6", "Each launch can increase power by 1A MAX");
        provider.add("gtlcore.machine.dyson_sphere.voltage", "Maximum Energy Output: %s EU/t");
        provider.add("gtlcore.machine.efficiency.tooltip", "§7Efficiency: §r%s");
        provider.add("gtlcore.machine.engraving_laser_plant.tooltip.0", "Precision laser mode does not support parallelism");
        provider.add("gtlcore.machine.eut_multiplier.tooltip", "Energy Consumption Multiplier: %s");
        provider.add("gtlcore.machine.eye_of_harmony.eu", "Startup Energy Consumption: %s EU");
        provider.add("gtlcore.machine.eye_of_harmony.helium", "Helium Storage: %smB");
        provider.add("gtlcore.machine.eye_of_harmony.hydrogen", "Hydrogen Storage: %smB");
        provider.add("gtlcore.machine.eye_of_harmony.tooltip.0", "Creates a mini-universe and gathers resources inside");
        provider.add("gtlcore.machine.eye_of_harmony.tooltip.1", "This multi-block machine requires too much EU, unable to power with conventional means");
        provider.add("gtlcore.machine.eye_of_harmony.tooltip.2", "Directly supplied with EU from wireless EU network, specific values can be viewed in the GUI");
        provider.add("gtlcore.machine.eye_of_harmony.tooltip.3", "Executes special overclocking mode, increasing speed by 2 times for every 16 times power increase, overclocking is adjusted by programmed circuits");
        provider.add("gtlcore.machine.eye_of_harmony.tooltip.4", "The circuit must be set up before starting, Circuit 1 does not execute overclocking, Circuits 2-4 execute 1, 2, and 3 times overclocking respectively");
        provider.add("gtlcore.machine.eye_of_harmony.tooltip.5", "Startup requires 1024B of cosmic matter and 1024KB of hydrogen and helium");
        provider.add("gtlcore.machine.eye_of_harmony.tooltip.6", "Hydrogen and Helium are stored inside the machine and continue to consume before the machine is ready to work");
        provider.add("gtlcore.machine.flotation_cell_regulator.tooltip.0", "Industrial Flotation Mining Pool");
        provider.add("gtlcore.machine.fission_reactor.cooler", "Number of Cooling Components: %s, Adjacent Count: %s");
        provider.add("gtlcore.machine.fission_reactor.damaged", "Damage: %s");
        provider.add("gtlcore.machine.fission_reactor.fuel", "Number of Fuel Components: %s, Adjacent Count: %s");
        provider.add("gtlcore.machine.fission_reactor.heat", "Reactor Temperature: %s K");
        provider.add("gtlcore.machine.fission_reactor.tooltip.0", "The reactor gains a maximum parallelism equal to the number of fuel components before it runs");
        provider.add("gtlcore.machine.fission_reactor.tooltip.1", "The reactor will heat up based on conditions during operation, heating value (K) per second = heat generated by recipe x (1 + number of adjacent fuel rods)");
        provider.add("gtlcore.machine.fission_reactor.tooltip.10", "If the supply amount is n times the demand amount, a special overclocking will be executed, overclocking times = n");
        provider.add("gtlcore.machine.fission_reactor.tooltip.11", "Consumes one demand amount of cooling liquid again, reducing the time by 1 second. If cooling liquid cannot be supplied, overclocking is interrupted. If progress reaches 100%, overclocking is interrupted and one demand amount of cooling liquid is consumed to reduce temperature by 1K");
        provider.add("gtlcore.machine.fission_reactor.tooltip.12", "Using distilled water as cooling liquid will generate steam, amount generated: consumption x min(160, 160/(1.4^(373-temperature)))");
        provider.add("gtlcore.machine.fission_reactor.tooltip.13", "Using sodium-potassium alloy as cooling liquid will produce hot sodium-potassium alloy, amount generated = consumption. If the temperature exceeds 825K, an equal amount of supercritical sodium-potassium alloy is produced");
        provider.add("gtlcore.machine.fission_reactor.tooltip.14", "Recipes can be executed regardless of whether the reactor consumes cooling liquid");
        provider.add("gtlcore.machine.fission_reactor.tooltip.15", "The temperature of the reactor will decrease by 1K every second after it stops working");
        provider.add("gtlcore.machine.fission_reactor.tooltip.2", "If the temperature exceeds 1500K, the reactor will be damaged; if damage reaches 100%, the reactor will explode");
        provider.add("gtlcore.machine.fission_reactor.tooltip.3", "Provide cooling liquid during operation and control the temperature based on different cooling liquid coefficients");
        provider.add("gtlcore.machine.fission_reactor.tooltip.4", "Cooling liquid coefficients: Distilled Water: 800, Sodium-Potassium Alloy: 20");
        provider.add("gtlcore.machine.fission_reactor.tooltip.5", "Reactor cooling has the following parameters:");
        provider.add("gtlcore.machine.fission_reactor.tooltip.6", "Minimum cooling liquid demand and maximum cooling liquid supply");
        provider.add("gtlcore.machine.fission_reactor.tooltip.7", "Minimum Demand = Recipe Generated Heat x Actual Parallelism x Current Temperature / 1500");
        provider.add("gtlcore.machine.fission_reactor.tooltip.8", "Maximum Supply = (Cooling Components - (Adjacent Count / 3)) x 8");
        provider.add("gtlcore.machine.fission_reactor.tooltip.9", "When supply >= demand, cooling liquid consumption conditions are met, consume provided cooling liquid, consumption amount = demand x cooling liquid coefficient, and prevent the reactor from heating up");
        provider.add("gtlcore.machine.generator_array.tooltip.0", "Strong operational environment will double the power of small generators inside the machine.");
        provider.add("gtlcore.machine.generator_array.tooltip.1", "Can enable wireless network mode, the generated electrical energy will be directly sent to the wireless network.");
        provider.add("gtlcore.machine.generator_array.wireless", "Wireless Network Mode:");
        provider.add("gtlcore.machine.greenhouse.SkyLight", "Current Illumination: %s");
        provider.add("gtlcore.machine.greenhouse.tooltip.0", "Requires sunlight to operate");
        provider.add("gtlcore.machine.greenhouse.tooltip.1", "If sunlight is insufficient, speed will slow down");
        provider.add("gtlcore.machine.heat_exchanger.tooltip.0", "Processes all input hot fluids every time");
        provider.add("gtlcore.machine.heat_exchanger.tooltip.1", "Must ensure the cooling liquid input can cool all fluids");
        provider.add("gtlcore.machine.hyper_reactor.tooltip.0", "Providing additional 1mb plasma before each run will obtain 16 parallelism");
        provider.add("gtlcore.machine.hyper_reactor.tooltip.1", "Different fuels require different plasmas");
        provider.add("gtlcore.machine.hyper_reactor.tooltip.2", "The order from 1-4 is: Orichalcum, Enderium, Infuscolium, Metastable Hassium");
        provider.add("gtlcore.machine.integrated_ore_processor.tooltip.0", "Completes ore processing in one step");
        provider.add("gtlcore.machine.integrated_ore_processor.tooltip.1", "Circuit 1 is macerator-macerator-centrifuging");
        provider.add("gtlcore.machine.integrated_ore_processor.tooltip.2", "Circuit 2 is macerator-ore washer-thermal separation-macerator");
        provider.add("gtlcore.machine.integrated_ore_processor.tooltip.3", "Circuit 3 is macerator-ore washer-macerator-centrifuging");
        provider.add("gtlcore.machine.integrated_ore_processor.tooltip.4", "Circuit 4 is macerator-ore washer-sifter-centrifuging");
        provider.add("gtlcore.machine.integrated_ore_processor.tooltip.5", "Circuit 5 is macerator-chemical bath-thermal centrifuging-macerator");
        provider.add("gtlcore.machine.integrated_ore_processor.tooltip.6", "Circuit 6 is macerator-chemical bath-macerator-centrifuging");
        provider.add("gtlcore.machine.integrated_ore_processor.tooltip.7", "Circuit 7 is macerator-chemical bath-sifter-centrifuging");
        provider.add("gtlcore.machine.isa_mill.tooltip.0", "Industrial Wet macerator");
        provider.add("gtlcore.machine.large_arc_smelter.tooltip.0", "When running lightning treatment recipes, Duration is multiplied by 4");
        provider.add("gtlcore.machine.large_block_conversion_room.tooltip.1", "For each voltage tier above MV1, the number of blocks converted each time increases by 64, and will not repeat");
        provider.add("gtlcore.machine.large_combustion_engine.Joint_boosted", "§bJoint Combustion Boosted");
        provider.add("gtlcore.machine.large_combustion_engine.supply_dinitrogen_tetroxide_to_boost", "Provide Dinitrogen Tetroxide to joint combustion");
        provider.add("gtlcore.machine.large_gas_collector.tooltip.0", "Maximum parallelism: 100000");
        provider.add("gtlcore.machine.large_greenhouse.tooltip.0", "Can operate without sunlight");
        provider.add("gtlcore.machine.large_recycler.tooltip.0", "Maximum parallelism x4 for every voltage tier above EV1");
        provider.add("gtlcore.machine.large_rock_crusher.tooltip.0", "Requires the corresponding fluid to be placed in the input chamber");
        provider.add("gtlcore.machine.large_steam_circuit_assembler.circuit", "Inscribed Circuit: %s");
        provider.add("gtlcore.machine.large_steam_input_hatch.tooltip.0", "Increases steam multi-block recipe limit to MV and unlocks overclocking function");
        provider.add("gtlcore.machine.large_void_miner.tooltip.0", "Precision mode consumes resources to collect specified veins");
        provider.add("gtlcore.machine.large_void_miner.tooltip.1", "Random mode consumes 10KB of drilling fluid and has a longer Duration to randomly collect all ores; ensure enough output space in random mode");
        provider.add("gtlcore.machine.laser.tooltip", "Allows the use of laser chambers");
        provider.add("gtlcore.machine.lightning_rod.tooltip.0", "Large amounts of energy are generated after the lightning rod above is struck");
        provider.add("gtlcore.machine.lightning_rod.tooltip.1", "Can only generate energy once every 0.5 seconds, with a chance to damage the lightning rod above");
        provider.add("gtlcore.machine.lightning_rod.tooltip.2", "If the stored energy is full, the machine will explode");
        provider.add("gtlcore.machine.mega_fluid_heater", "Dormitory power limit is 1500W, this is an illegal appliance!");
        provider.add("gtlcore.machine.mega_turbine.high_speed_mode", "High Speed Mode:");
        provider.add("gtlcore.machine.mega_turbine.tooltip.0", "Operating efficiency is equivalent to 16 large turbines of the same type, can use more power chambers");
        provider.add("gtlcore.machine.mega_turbine.tooltip.1", "Rotors can be installed in the rotor chamber, automatically extracting rotor for installation onto empty rotor brackets");
        provider.add("gtlcore.machine.mega_turbine.tooltip.2", "High speed mode can be enabled to further increase operating speed; in high speed mode, operating speed increases from 16x to 48x, at the cost of increasing maintenance issue probability and turbine durability loss being 12 times");
        provider.add("gtlcore.machine.module", "Number of Installed Modules: %s");
        provider.add("gtlcore.machine.module.am", "Number of Installed Modules: %s");
        provider.add("gtlcore.machine.module.have", "This module has been successfully installed");
        provider.add("gtlcore.machine.module.null", "This module has not been successfully installed");
        provider.add("gtlcore.machine.multiple_recipes.tooltip", "Supports cross-recipe parallelism");
        provider.add("gtlcore.machine.nano_core.tooltip.0", "Can run any tier of nano forge recipes");
        provider.add("gtlcore.machine.nano_core.tooltip.1", "Processing speed is fixed at 20 times");
        provider.add("gtlcore.machine.nano_forge.tooltip.0", "Requires corresponding nano swarms to work, and parallelism depends on the number of swarms");
        provider.add("gtlcore.machine.nano_forge_1.nanoswarm", "Requires Carbon Nano Swarm to be placed");
        provider.add("gtlcore.machine.nano_forge_1.tooltip.0", "Parallelism equals the number of swarms");
        provider.add("gtlcore.machine.nano_forge_2.nanoswarm", "Requires Neutron Nano Swarm to be placed");
        provider.add("gtlcore.machine.nano_forge_2.tooltip.0", "When processing tier 2 recipes, parallelism equals the number of swarms");
        provider.add("gtlcore.machine.nano_forge_2.tooltip.1", "When processing tier 1 recipes, parallelism doubles, overclocking mode changes to lossless overclocking");
        provider.add("gtlcore.machine.nano_forge_3.nanoswarm", "Requires Dragon Nano Swarm to be placed");
        provider.add("gtlcore.machine.nano_forge_3.tooltip.0", "When processing tier 3 recipes, parallelism equals the number of swarms");
        provider.add("gtlcore.machine.nano_forge_3.tooltip.1", "When processing tier 2 recipes, parallelism doubles, overclocking mode changes to lossless overclocking");
        provider.add("gtlcore.machine.nano_forge_3.tooltip.2", "When processing tier 1 recipes, parallelism quadruples, overclocking mode changes to increase speed by 8 times with every 4 times power increase");
        provider.add("gtlcore.machine.neutron_accelerator.tooltip.0", "§6Max EU Consumption: §r%s");
        provider.add("gtlcore.machine.neutron_accelerator.tooltip.1", "§bEach point of EU converts to §e10~20-eV§b neutron kinetic energy");
        provider.add("gtlcore.machine.neutron_activator.efficiency", "Kinetic Energy Consumption Multiplier: %s");
        provider.add("gtlcore.machine.neutron_activator.ev", "Current Neutron Kinetic Energy: %seV");
        provider.add("gtlcore.machine.neutron_activator.height", "Height: %s");
        provider.add("gtlcore.machine.neutron_activator.time", "Time: %s");
        provider.add("gtlcore.machine.neutron_activator.tooltip.0", "§7Superluminal Movement!");
        provider.add("gtlcore.machine.neutron_activator.tooltip.1", "Additional high-speed pipeline blocks provide recipe Duration reductions while lowering neutron accelerator efficiency");
        provider.add("gtlcore.machine.neutron_activator.tooltip.2", "Time reduction and acceleration efficiency is 0.95^Number of Additional Blocks");
        provider.add("gtlcore.machine.neutron_activator.tooltip.3", "When no neutron accelerator is running, neutron kinetic energy decreases by §e72KeV§r per second");
        provider.add("gtlcore.machine.neutron_activator.tooltip.4", "Input graphite/beryllium powder can immediately absorb §e10MeV§r neutron kinetic energy");
        provider.add("gtlcore.machine.neutron_sensor.invert.disabled.0", "Redstone Output: Normal");
        provider.add("gtlcore.machine.neutron_sensor.invert.disabled.1", "Click to toggle inverted redstone logic");
        provider.add("gtlcore.machine.neutron_sensor.invert.disabled.2", "When neutron kinetic energy is between the set minimum and maximum values, the sensor will emit a redstone signal; below the minimum value it will stop emitting a redstone signal");
        provider.add("gtlcore.machine.neutron_sensor.invert.enabled.0", "Redstone Output: Inverted");
        provider.add("gtlcore.machine.neutron_sensor.invert.enabled.1", "Click to switch to normal redstone logic");
        provider.add("gtlcore.machine.neutron_sensor.invert.enabled.2", "When neutron kinetic energy is outside the set minimum and maximum values, the sensor will emit a redstone signal; below the minimum value it will emit a redstone signal");
        provider.add("gtlcore.machine.neutron_sensor.tooltip.0", "Outputs redstone signals based on §6Neutron Kinetic Energy§7, right-click to open GUI for settings");
        provider.add("gtlcore.machine.oc_amount", "Overclocking Times: %s");
        provider.add("gtlcore.machine.off", "Off");
        provider.add("gtlcore.machine.on", "On");
        provider.add("gtlcore.machine.pattern.error.tier", "§cMust use blocks of the same tier§r");
        provider.add("gtlcore.machine.pcb_factory.tooltip.0", "Placing nano swarms can obtain reductions");
        provider.add("gtlcore.machine.pcb_factory.tooltip.1", "Duration factor/per unit: Gold: 0.5%, Vibranium: 1%");
        provider.add("gtlcore.machine.pcb_factory.tooltip.2", "Vibranium also decreases energy consumption by 4 times");
        provider.add("gtlcore.machine.primitive_magic_energy.tooltip.0", "Endlessly absorbs the energy from ender crystals above the machine");
        provider.add("gtlcore.machine.processing_plant.mismatched", "Recipe tier does not match small machine tier");
        provider.add("gtlcore.machine.processing_plant.tooltip.0", "Requires one corresponding tier small machine to operate");
        provider.add("gtlcore.machine.processing_plant.tooltip.1", "For each tier above LV, maximum parallelism increases by 4");
        provider.add("gtlcore.machine.radiation_hatch.inhibition_dose", "Inhibition Amount: %s Sv");
        provider.add("gtlcore.machine.radiation_hatch.time", "Time: %s / %s Tick");
        provider.add("gtlcore.machine.resource_collection.tooltip.0", "Maximum parallelism: 4^(Power Module tier - 1)");
        provider.add("gtlcore.machine.slaughterhouse.is_spawn", "Entity Generation: ");
        provider.add("gtlcore.machine.slaughterhouse.tooltip.0", "Electric Spawner, automatically kills mobs");
        provider.add("gtlcore.machine.slaughterhouse.tooltip.1", "Voltage tier above MV1 increases the number of processes by 8 each time");
        provider.add("gtlcore.machine.slaughterhouse.tooltip.2", "Circuit must be set up before running; Circuit 1 is for non-hostile mobs, 2 is for hostile mobs");
        provider.add("gtlcore.machine.slaughterhouse.tooltip.3", "If an electric spawner is placed in the machine GUI, only the contents of the spawner will spawn");
        provider.add("gtlcore.machine.slaughterhouse.tooltip.4", "Entity generation mode is based on actual drops from player kills, performance is lower, can gain experience");
        provider.add("gtlcore.machine.slaughterhouse.tooltip.5", "Non-entity generation mode is virtual drops; if a spawner exists, it uses parallel processing");
        provider.add("gtlcore.machine.space_elevator.set_out", "Set Off");
        provider.add("gtlcore.machine.space_elevator.tooltip.0", "Can install up to 8 expansion modules");
        provider.add("gtlcore.machine.space_elevator.tooltip.1", "Increasing voltage tier can provide Duration reductions for modules");
        provider.add("gtlcore.machine.space_elevator.tooltip.2", "Circuit must be set to 1 before running, and provide 128 computing power");
        provider.add("gtlcore.machine.space_probe_surface_reception.tooltip.0", "Do not obstruct");
        provider.add("gtlcore.machine.star_ultimate_material_forge_factory.tooltip.0", "Maximum Parallelism: 1000");
        provider.add("gtlcore.machine.steam_parallel_machine.modification_oc", "Modify Overclocking Times: ");
        provider.add("gtlcore.machine.steam_parallel_machine.oc", "Each time of overclocking will reduce time by 2 times and increase steam consumption by 3 times");
        provider.add("gtlcore.machine.super_computation.tooltip.0", "Obtain computing power output based on different voltage tiers");
        provider.add("gtlcore.machine.super_computation.tooltip.1", "And each computing power output requires different circuits with 8 mainframes");
        provider.add("gtlcore.machine.super_computation.tooltip.2", "Providing §2UIV§r tier voltage requires placing §bOptical Processor Mainframe§r, and providing 1024CWU/t");
        provider.add("gtlcore.machine.super_computation.tooltip.3", "Providing §eUXV§r tier voltage requires placing §bExotic Processor Mainframe§r, and providing 2048CWU/t");
        provider.add("gtlcore.machine.super_computation.tooltip.4", "Providing §9§lOpV§r tier voltage requires placing §bCosmic Processor Mainframe§r, and providing 4096CWU/t");
        provider.add("gtlcore.machine.super_computation.tooltip.5", "Providing §4§lMAX§r tier voltage requires placing §bSupra Causal Processor Mainframe§r, and providing 8192CWU/t");
        provider.add("gtlcore.machine.suprachronal_assembly_line.tooltip.0", "§8§lInvisibly Touch§r");
        provider.add("gtlcore.machine.suprachronal_assembly_line.tooltip.1", "Modules can be expanded on both sides, sharing parallelism with the mainframe.");
        provider.add("gtlcore.machine.suprachronal_assembly_line_module.tooltip.0", "Installed on both sides of the Supra-Temporal Assembly Line");
        provider.add("gtlcore.machine.total_time", "Continuous Running Time: %s Tick");
        provider.add("gtlcore.machine.uev_fusion_reactor.description", "The Fusion Reactor MK V is a large multiblock structure used for fusing elements into heavier ones. It can only use UEV Energy Hatches. For every Hatch it has, its buffer increases by 160M EU, and has a maximum of 2560M.");
        provider.add("gtlcore.machine.uhv_fusion_reactor.description", "The Fusion Reactor MK-IV is a large multiblock structure used for fusing elements into heavier ones. It can only use UHV Energy Hatches. For every Hatch it has, its buffer increases by 80M EU, and has a maximum of 1280M.");
        provider.add("gtlcore.machine.vacuum_drying_furnace.tooltip.0", "When running vacuum drying recipes:");
        provider.add("gtlcore.machine.vacuum_drying_furnace.tooltip.1", "When running dehydration recipes:");
        provider.add("gtlcore.machine.weather_control.tooltip.0", "Circuit 1 toggles sunny weather");
        provider.add("gtlcore.machine.weather_control.tooltip.1", "Circuit 2 toggles rainy weather");
        provider.add("gtlcore.machine.weather_control.tooltip.2", "Circuit 3 toggles thunderstorm");
        provider.add("gtlcore.machine.wind_mill_turbine.actualPower", "Energy Output: %s EU/t");
        provider.add("gtlcore.machine.wind_mill_turbine.tooltip.0", "The rotor cannot operate below the minimum wind speed, and it will be damaged quickly above the maximum wind speed");
        provider.add("gtlcore.machine.wind_mill_turbine.tooltip.1", "Wind Speed Bonus: x1.5 for rainy days, x2 for thunderstorms, the wind speed determines the maximum rotation speed");
        provider.add("gtlcore.machine.wind_mill_turbine.wind", "Current Wind Speed: %s");
    }
}
