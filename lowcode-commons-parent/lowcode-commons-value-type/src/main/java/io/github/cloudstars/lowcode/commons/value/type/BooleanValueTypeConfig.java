package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 布尔数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "BOOLEAN")
public class BooleanValueTypeConfig extends AbstractValueTypeConfig<Boolean> {

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
