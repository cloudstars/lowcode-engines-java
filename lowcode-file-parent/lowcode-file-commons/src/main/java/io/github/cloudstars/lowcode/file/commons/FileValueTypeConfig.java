package io.github.cloudstars.lowcode.file.commons;

import io.github.cloudstars.lowcode.commons.value.type.config.AbstractObjectValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.ValueTypeConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 文件数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "FILE")
public class FileValueTypeConfig extends AbstractObjectValueTypeConfig {

    public FileValueTypeConfig() {
    }

    public FileValueTypeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }
}
