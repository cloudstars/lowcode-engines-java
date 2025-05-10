package io.github.cloudstars.lowcode.pdf.commons.config;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

public class PdfCellConfig extends AbstractConfig {

    private String label;

    private Integer labelSize;

    private Integer valueSize;

    public PdfCellConfig() {
    }

    public PdfCellConfig(JsonObject configJson) {
        super(configJson);
    }

}
