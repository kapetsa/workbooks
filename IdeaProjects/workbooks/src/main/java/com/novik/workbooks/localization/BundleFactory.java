package com.novik.workbooks.localization;

import com.novik.workbooks.localization.locale.BundleResource;
import com.novik.workbooks.localization.locale.BundleResourceBY;
import com.novik.workbooks.localization.locale.BundleResourceRU;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BundleFactory {

    @Qualifier(value = "bundleResourceBY")
    private final BundleResource bundleResourceBY;
    @Qualifier(value = "bundleResourceRU")
    private final BundleResource bundleResourceRU;

    public BundleFactory(BundleResource bundleResourceBY, BundleResource bundleResourceRU) {
        this.bundleResourceBY = bundleResourceBY;
        this.bundleResourceRU = bundleResourceRU;
    }

    public BundleResource createBundle(String locale) {
        BundleResource bundleResource;

        switch (locale) {
            case "RU":
                bundleResource = bundleResourceRU;
                break;
            case "BY":
                bundleResource = bundleResourceBY;
                break;
            default:
                bundleResource = bundleResourceRU;
                break;
        }

        return bundleResource;
    }
}
