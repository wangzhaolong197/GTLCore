package org.gtlcore.gtlcore.data;

import org.gtlcore.gtlcore.data.lang.CNLanguageProvider;
import org.gtlcore.gtlcore.data.lang.LangHandler;

import com.tterrag.registrate.providers.ProviderType;

import static org.gtlcore.gtlcore.api.registries.GTLRegistration.REGISTRATE;

public class GTLDatagen {

    public static final ProviderType<CNLanguageProvider> CNLANG = ProviderType.register("gtl_cn_lang", (p, e) -> new CNLanguageProvider(p, e.getGenerator().getPackOutput()));

    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::enInitialize);
        REGISTRATE.addDataGenerator(CNLANG, LangHandler::cnInitialize);
    }
}
