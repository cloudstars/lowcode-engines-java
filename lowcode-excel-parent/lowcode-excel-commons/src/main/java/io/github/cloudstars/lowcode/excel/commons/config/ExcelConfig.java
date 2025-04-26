package io.github.cloudstars.lowcode.excel.commons.config;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ResourceTypeConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

@ResourceTypeConfigClass(name = "Excel", description = "模型Excel导入导出")
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
