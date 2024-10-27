package org.gtlcore.gtlcore.common.data.material;

import org.gtlcore.gtlcore.GTLCore;
import org.gtlcore.gtlcore.api.data.chemical.material.info.GTLMaterialFlags;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static org.gtlcore.gtlcore.common.data.GTLMaterials.*;

public class MaterialBuilderMachine {

    public static void init() {
        // 魔法注册
        /// 各种魔石4+4+12
        LowGuideMagic = new Material.Builder(GTLCore.id("low_guide_magic"))
                .gem()
                .ore(0, 0, true)
                .color(0xfae6ff)
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister().setFormula(" ༒ ");
        MiddleGuideMagic = new Material.Builder(GTLCore.id("middle_guide_magic"))
                .gem()
                .ore(0, 0, true)
                .color(0xeb99ff)
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister().setFormula(" ༒ ");
        HighGuideMagic = new Material.Builder(GTLCore.id("high_guide_magic"))
                .gem()
                .ore(0, 0, true)
                .color(0xdb4dff)
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister().setFormula(" ༒ ");
        LimitlessGuideMagic = new Material.Builder(GTLCore.id("limitless_guide_magic"))
                .gem()
                .ore(0, 0, true)
                .color(0xcc00ff)
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister().setFormula("§ke§r ༒ §ke§r");
        NonattributeMagicCrystals = new Material.Builder(GTLCore.id("non_attribute_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(LowGuideMagic)
                .color(0xffffff)
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        ChargeMagicCrystals = new Material.Builder(GTLCore.id("charge_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(MiddleGuideMagic)
                .color(0xffffff)
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        FocusMagicCrystals = new Material.Builder(GTLCore.id("focus_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .color(0xffffff)
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        ShiningMagicCrystals = new Material.Builder(GTLCore.id("shining_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .color(0xffffff)
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        NaturalMagicCrystals = new Material.Builder(GTLCore.id("natural_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(LowGuideMagic)
                .color(0x00ff00) // 绿色代表自然
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        OceanMagicCrystals = new Material.Builder(GTLCore.id("ocean_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(LowGuideMagic)
                .color(0x0000ff) // 蓝色代表海洋
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        ColdMagicCrystals = new Material.Builder(GTLCore.id("cold_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(LowGuideMagic)
                .color(0x808080) // 灰色代表寒冷
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        HiddenMagicCrystals = new Material.Builder(GTLCore.id("hidden_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(LowGuideMagic)
                .color(0x008000) // 暗绿色代表隐藏
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        AncientMagicCrystals = new Material.Builder(GTLCore.id("ancient_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(MiddleGuideMagic)
                .color(0x8b4513) // 棕色代表古代
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        StarrySkyMagicCrystals = new Material.Builder(GTLCore.id("starry_sky_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(MiddleGuideMagic)
                .color(0x00ffff) // 青色代表星空
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        PurgatoryMagicCrystals = new Material.Builder(GTLCore.id("purgatory_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(HighGuideMagic)
                .color(0xff0000) // 红色代表炼狱
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        EndMagicCrystals = new Material.Builder(GTLCore.id("end_magic_crystals"))
                .gem()
                .ore(0, 0, true)
                .addOreByproducts(HighGuideMagic)
                .color(0x555555) // 深灰色代表终界
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        AbyssMagicCrystals = new Material.Builder(GTLCore.id("abyss_magic_crystals"))
                .gem()
                .color(0x000080) // 深蓝色代表深渊
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        SunMagicCrystals = new Material.Builder(GTLCore.id("sun_magic_crystals"))
                .gem()
                .color(0xffff00) // 黄色代表太阳
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        TimeMagicCrystals = new Material.Builder(GTLCore.id("time_magic_crystals"))
                .gem()
                .color(0x800080) // 紫色代表时间
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        SpaceMagicCrystals = new Material.Builder(GTLCore.id("space_magic_crystals"))
                .gem()
                .color(0x0000ff) // 深蓝色代表空间
                .iconSet(RUBY)
                .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL, GTLMaterialFlags.DISABLE_RAW_ORE)
                .buildAndRegister();
        /// 4+12种魔石浊液
        NonattributeMagicCrystalsTurbid = new Material.Builder(GTLCore.id("non_attribute_magic_crystals_turbid"))
                .liquid()
                .color(0x808080)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        ChargeMagicCrystalsTurbid = new Material.Builder(GTLCore.id("charge_magic_crystals_turbid"))
                .liquid()
                .color(0x00FF00)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        FocusMagicCrystalsTurbid = new Material.Builder(GTLCore.id("focus_magic_crystals_turbid"))
                .liquid()
                .color(0x008000)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        ShiningMagicCrystalsTurbid = new Material.Builder(GTLCore.id("shining_magic_crystals_turbid"))
                .liquid()
                .color(0xFFFF00)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        NaturalMagicCrystalsTurbid = new Material.Builder(GTLCore.id("natural_magic_crystals_turbid"))
                .liquid()
                .color(0x008000)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        OceanMagicCrystalsTurbid = new Material.Builder(GTLCore.id("ocean_magic_crystals_turbid"))
                .liquid()
                .color(0x0000FF)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        ColdMagicCrystalsTurbid = new Material.Builder(GTLCore.id("cold_magic_crystals_turbid"))
                .liquid()
                .color(0xADD8E6)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        HiddenMagicCrystalsTurbid = new Material.Builder(GTLCore.id("hidden_magic_crystals_turbid"))
                .liquid()
                .color(0x808080)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        AncientMagicCrystalsTurbid = new Material.Builder(GTLCore.id("ancient_magic_crystals_turbid"))
                .liquid()
                .color(0xA0522D)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        PurgatoryMagicCrystalsTurbid = new Material.Builder(GTLCore.id("purgatory_magic_crystals_turbid"))
                .liquid()
                .color(0xFFA07A)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        EndMagicCrystalsTurbid = new Material.Builder(GTLCore.id("end_magic_crystals_turbid"))
                .liquid()
                .color(0x808080)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        StarrySkyMagicCrystalsTurbid = new Material.Builder(GTLCore.id("starry_sky_magic_crystals_turbid"))
                .liquid()
                .color(0x00BFFF)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        AbyssMagicCrystalsTurbid = new Material.Builder(GTLCore.id("abyss_magic_crystals_turbid"))
                .liquid()
                .color(0x00008B)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        SunMagicCrystalsTurbid = new Material.Builder(GTLCore.id("sun_magic_crystals_turbid"))
                .liquid()
                .color(0xFFD700)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        TimeMagicCrystalsTurbid = new Material.Builder(GTLCore.id("time_magic_crystals_turbid"))
                .liquid()
                .color(0x8A2BE2)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        SpaceMagicCrystalsTurbid = new Material.Builder(GTLCore.id("space_magic_crystals_turbid"))
                .liquid()
                .color(0x0000CD)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister();
        /// 各种源质2+12
        MagicSource = new Material.Builder(GTLCore.id("magic_source"))
                .gas()
                .color(0x9e77ba)
                .iconSet(DULL)
                .buildAndRegister();
        HighMagicSource = new Material.Builder(GTLCore.id("high_magic_source"))
                .gas()
                .color(0x9e77ba)
                .iconSet(DULL)
                .buildAndRegister();
        NaturalMagicSource = new Material.Builder(GTLCore.id("natural_magic_source"))
                .gas()
                .color(0x90EE90)
                .iconSet(DULL)
                .buildAndRegister();
        OceanMagicSource = new Material.Builder(GTLCore.id("ocean_magic_source"))
                .gas()
                .color(0xADD8E6)
                .iconSet(DULL)
                .buildAndRegister();
        ColdMagicSource = new Material.Builder(GTLCore.id("cold_magic_source"))
                .gas()
                .color(0xA0CBE2)
                .iconSet(DULL)
                .buildAndRegister();
        HiddenMagicSource = new Material.Builder(GTLCore.id("hidden_magic_source"))
                .gas()
                .color(0x808080)
                .iconSet(DULL)
                .buildAndRegister();
        AncientMagicSource = new Material.Builder(GTLCore.id("ancient_magic_source"))
                .gas()
                .color(0xBDB76B)
                .iconSet(DULL)
                .buildAndRegister();
        PurgatoryMagicSource = new Material.Builder(GTLCore.id("purgatory_magic_source"))
                .gas()
                .color(0xFF8C00)
                .iconSet(DULL)
                .buildAndRegister();
        EndMagicSource = new Material.Builder(GTLCore.id("end_magic_source"))
                .gas()
                .color(0x4B0082)
                .iconSet(DULL)
                .buildAndRegister();
        StarrySkyMagicSource = new Material.Builder(GTLCore.id("starry_sky_magic_source"))
                .gas()
                .color(0x00BFFF)
                .iconSet(DULL)
                .buildAndRegister();
        AbyssMagicSource = new Material.Builder(GTLCore.id("abyss_magic_source"))
                .gas()
                .color(0x00008B)
                .iconSet(DULL)
                .buildAndRegister();
        SunMagicSource = new Material.Builder(GTLCore.id("sun_magic_source"))
                .gas()
                .color(0xFFD700)
                .iconSet(DULL)
                .buildAndRegister();
        TimeMagicSource = new Material.Builder(GTLCore.id("time_magic_source"))
                .gas()
                .color(0x8A2BE2)
                .iconSet(DULL)
                .buildAndRegister();
        SpaceMagicSource = new Material.Builder(GTLCore.id("space_magic_source"))
                .gas()
                .color(0x0000CD)
                .iconSet(DULL)
                .buildAndRegister();
        /// 各种产线材料
        ConcentratedMagicSource = new Material.Builder(GTLCore.id("concentrated_magic_source"))
                .fluid()
                .color(0x9955AA)
                .iconSet(FLUID)
                .buildAndRegister();
    }
    /// private static Material registerMagicStone(String name, int color) {
    /// return new Material.Builder(GTLCore.id(name))
    /// .gem()
    /// .ore(0, 0, true)
    /// .color(color)
    /// .iconSet(RUBY)
    /// .flags(GTLMaterialFlags.GENERATE_MAGICCRYSTAL)
    /// .buildAndRegister();
    /// }
    ///
    /// public static void init() {
    /// }
    ///
    /// public static final Material LowGuideMagic = registerMagicStone("low_guide_magic", 0xfae6ff);
}
