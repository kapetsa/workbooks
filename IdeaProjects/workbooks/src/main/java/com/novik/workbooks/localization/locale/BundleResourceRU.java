package com.novik.workbooks.localization.locale;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@Component
public class BundleResourceRU implements BundleResource {

    @Override
    public ResourceBundle getLocalization() {
        ResourceBundle bundle = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        InputStream stream = loader.getResourceAsStream("localization_ru.properties");
        try {
            bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bundle;
    }
}
