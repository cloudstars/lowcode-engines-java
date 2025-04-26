package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 抽象的数据格式配置
 *
 * @author clouds
 */
public abstract class AbstractObjectValueTypeConfig<T extends Object> extends AbstractValueTypeConfig<T> {

    // 对象下的属性列表配置名称
    public static final String ATTR_PROPERTIES = "properties";

    /**
     * 对象值下面的属性列表
     */
    protected List<ObjectPropertyConfig> properties;

    protected AbstractObjectValueTypeConfig() {}

    public AbstractObjectValueTypeConfig(JsonObject configJson) {
        super(configJson);
    }

    public List<ObjectPropertyConfig> getProperties() {
        return properties;
    }

    public void setProperties(List<ObjectPropertyConfig> properties) {
        this.properties = properties;
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.OBJECT;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        if (this.properties != null) {
            configJson.put(ATTR_PROPERTIES, JsonConfigUtils.toJsonArray(this.properties));
        }

        return configJson;
    }
}
