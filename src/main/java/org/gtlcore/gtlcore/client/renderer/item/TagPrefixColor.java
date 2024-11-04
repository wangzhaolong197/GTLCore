package org.gtlcore.gtlcore.client.renderer.item;

import org.gtlcore.gtlcore.common.data.GTLMaterials;
import org.gtlcore.gtlcore.utils.ColorUtils;

import committee.nova.mods.avaritia.client.AvaritiaClient;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TagPrefixColor {

    public static final Map<String, Supplier<Integer>> MaterialColors = new HashMap<>();

    static {
        MaterialColors.put(GTLMaterials.ChromaticGlass.getName(), AvaritiaClient::getCurrentRainbowColor);
        MaterialColors.put(GTLMaterials.Hypogen.getName(), () -> ColorUtils.getInterpolatedColor(0xFF3D00, 0xDA9100, Math.abs(1 - (System.currentTimeMillis() % 6000) / 3000F)));
        MaterialColors.put(GTLMaterials.HexaphaseCopper.getName(), () -> {
            float spot = (System.currentTimeMillis() % 4000) / 4000F;
            return ColorUtils.getInterpolatedColor(0xEC7916, 0x00FF15, (spot > 0.1 && spot < 0.15 || spot > 0.18 && spot < 0.22) ? 1 : 0);
        });
    }
}
