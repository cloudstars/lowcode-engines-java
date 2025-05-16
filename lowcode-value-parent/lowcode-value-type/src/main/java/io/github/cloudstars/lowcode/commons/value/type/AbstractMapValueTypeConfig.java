package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 抽象的数据格式配置
 *
 * @author clouds
 */
public abstract class AbstractMapValueTypeConfig extends AbstractValueTypeConfig implements XMapValueTypeConfig {

    // 对象下的属性列表配置名称
    protected static final String ATTR_PROPERTIES = "properties";

    /**
     * 对象值下面的属性列表
     */
    protected List<MapPropertyConfig> properties;

    protected AbstractMapValueTypeConfig() {}

    public AbstractMapValueTypeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public List<MapPropertyConfig> getProperties() {
        return properties;
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.OBJECT;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
