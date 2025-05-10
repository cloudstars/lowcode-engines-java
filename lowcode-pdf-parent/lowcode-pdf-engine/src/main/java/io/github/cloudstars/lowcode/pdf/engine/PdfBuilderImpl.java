package io.github.cloudstars.lowcode.pdf.engine;

import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;
import io.github.cloudstars.lowcode.pdf.vendor.PdfVendorApi;

import java.io.InputStream;
import java.util.Map;

public class PdfBuilderImpl implements PdfBuilder {

    private PdfBuildConfig config;

    private PdfVendorApi vendorApi;

    public PdfBuilderImpl(PdfVendorApi vendorApi, PdfBuildConfig config) {
        this.vendorApi = vendorApi;
        this.config = config;
    }

    @Override
    public InputStream build(Map<String, Object> dataMap) {

        return null;
    }


}
