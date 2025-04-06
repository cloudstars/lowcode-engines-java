package io.github.cloudstars.lowcode.excel.commons.config;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.config.ConfigTypeClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

@ConfigTypeClass(code = "Excel", name = "Excel")
public class ExcelConfig extends AbstractConfig {

    public ExcelConfig() {
    }

    public ExcelConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
