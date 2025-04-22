package io.github.cloudstars.lowcode.object.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.config.ObjectValueTypeConfig;

import java.util.List;

/**
 * Json字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "JSON")
public class JsonBpmFieldConfig extends AbstractBpmFieldConfig {

    // 对象下属性列表的配置名称
    private static final String ATTR_PROPERTIES = "properties";

    /**
     * 对象下的属性
     */
    private List<AbstractBpmFieldConfig> properties;

    public List<AbstractBpmFieldConfig> getProperties() {
        return properties;
    }

    public void setProperties(List<AbstractBpmFieldConfig> properties) {
        this.properties = properties;
    }

    public JsonBpmFieldConfig() {
    }

    public JsonBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new ObjectValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();

        return configJson;
    }

}
