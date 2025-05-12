package io.github.cloudstars.lowcode.pdf.vendor;

import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;

public class OpenPdfBuilderFactory implements PdfBuilderFactory {

    @Override
    public PdfBuilder newInstance(PdfBuildConfig config) {
        return new OpenPdfBuilderImpl(config);
    }

}
