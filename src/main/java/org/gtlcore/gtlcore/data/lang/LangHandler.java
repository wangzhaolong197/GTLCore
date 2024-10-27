package org.gtlcore.gtlcore.data.lang;

import org.gtlcore.gtlcore.common.data.GTLMaterials;
import org.gtlcore.gtlcore.common.data.GTLRecipeTypes;

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
    }
}
