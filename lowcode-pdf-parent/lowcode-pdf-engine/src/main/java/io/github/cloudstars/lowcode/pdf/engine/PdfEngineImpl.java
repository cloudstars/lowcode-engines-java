package io.github.cloudstars.lowcode.pdf.engine;

import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;
import io.github.cloudstars.lowcode.pdf.vendor.PdfBuilder;
import io.github.cloudstars.lowcode.pdf.vendor.PdfBuilderFactory;

import java.io.OutputStream;
import java.util.Map;

public class PdfEngineImpl implements PdfEngine {

    private PdfBuilderFactory factory;

    public PdfEngineImpl(PdfBuilderFactory factory) {
        this.factory = factory;
    }

    @Override
    public void build(PdfBuildConfig config, Map<String, Object> dataMap, OutputStream os) {
        PdfBuilder builder = factory.newInstance(config);
        builder.build(dataMap, os);
    }

}
