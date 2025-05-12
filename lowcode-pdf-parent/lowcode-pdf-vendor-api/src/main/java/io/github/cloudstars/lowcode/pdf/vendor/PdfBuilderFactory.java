package io.github.cloudstars.lowcode.pdf.vendor;

import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;

public interface PdfBuilderFactory {

    PdfBuilder newInstance(PdfBuildConfig config);

}
