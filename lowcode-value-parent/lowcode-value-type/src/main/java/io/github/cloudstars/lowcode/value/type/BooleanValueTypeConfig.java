package io.github.cloudstars.lowcode.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 布尔数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "BOOLEAN", valueClass = Boolean.class)
public class BooleanValueTypeConfig extends AbstractValueTypeConfig {

    public BooleanValueTypeConfig() {
    }

    public BooleanValueTypeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.BOOLEAN;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
