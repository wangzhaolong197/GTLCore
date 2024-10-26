package org.gtlcore.gtlcore.api.data.chemical.material.info;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;

public class GTLMaterialFlags {

    public static final MaterialFlag GENERATE_NANOSWARM = new MaterialFlag.Builder("generate_nanoswarm")
            .build();

    public static final MaterialFlag GENERATE_MILLED = new MaterialFlag.Builder("generate_milled")
            .build();

    public static final MaterialFlag GENERATE_MAGICCRYSTAL = new MaterialFlag.Builder("generate_magiccrystal")
            .build();
    public static final MaterialFlag DISABLE_ORE_PROCESS = new MaterialFlag.Builder("disable_ore_process")
            .build();
    public static final MaterialFlag DISABLE_RAW_ORE = new MaterialFlag.Builder("disable_raw_ore")
            .requireFlags(DISABLE_ORE_PROCESS)
            .build();
    public static final MaterialFlag DISABLE_CRUSHED = new MaterialFlag.Builder("disable_crushed")
            .requireFlags(DISABLE_ORE_PROCESS)
            .build();
    public static final MaterialFlag DISABLE_CRUSHED_PURIFIED = new MaterialFlag.Builder("disable_crushed_purified")
            .requireFlags(DISABLE_ORE_PROCESS)
            .build();
    public static final MaterialFlag DISABLE_CRUSHED_REFINED = new MaterialFlag.Builder("disable_crushed_refined")
            .requireFlags(DISABLE_ORE_PROCESS)
            .build();
    public static final MaterialFlag DISABLE_DUST_IMPURE = new MaterialFlag.Builder("disable_dust_impure")
            .requireFlags(DISABLE_ORE_PROCESS)
            .build();
    public static final MaterialFlag DISABLE_DUST_PURE = new MaterialFlag.Builder("disable_dust_pure")
            .requireFlags(DISABLE_ORE_PROCESS)
            .build();
}
