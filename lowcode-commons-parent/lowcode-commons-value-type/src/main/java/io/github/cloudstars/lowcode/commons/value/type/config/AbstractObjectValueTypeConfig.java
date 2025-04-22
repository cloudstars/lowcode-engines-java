package io.github.cloudstars.lowcode.commons.value.type.config;

import io.github.cloudstars.lowcode.commons.value.type.DataTypeEnum;
import io.github.cloudstars.lowcode.commons.lang.json.JsonConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 抽象的数据格式配置
 *
 * @author clouds
 */
public abstract class AbstractObjectValueTypeConfig extends AbstractValueTypeConfig {

    // 对象下的属性列表配置名称
    public static final String ATTR_PROPERTIES = "properties";

    /**
     * 对象值下面的属性列表
     */
    protected List<ObjectValueProperty> properties;

    public List<ObjectValueProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ObjectValueProperty> properties) {
        this.properties = properties;
    }

    protected AbstractObjectValueTypeConfig() {}

    public AbstractObjectValueTypeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.OBJECT;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        if (this.properties != null) {
            configJson.put("properties", JsonConfigUtils.toJsonArray(this.properties));
        }

        return configJson;
    }
}
