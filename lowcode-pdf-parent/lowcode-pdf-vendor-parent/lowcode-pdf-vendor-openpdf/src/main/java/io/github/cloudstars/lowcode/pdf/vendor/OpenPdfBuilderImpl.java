package io.github.cloudstars.lowcode.pdf.vendor;

import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;

import java.io.OutputStream;
import java.util.Map;

public class OpenPdfBuilderImpl implements PdfBuilder {

    private PdfBuildConfig config;

    public OpenPdfBuilderImpl(PdfBuildConfig config) {
        this.config = config;
    }

    @Override
    public void build(Map<String, Object> dataMap, OutputStream os) {

    }

}
