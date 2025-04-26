package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ObjectValueTypeConfig;

import java.util.List;

/**
 * 对象字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "OBJECT")
public class ObjectBpmFieldConfig extends AbstractBpmFieldConfig<ObjectValueTypeConfig> {

    // 对象下属性列表的配置名称
    private static final String ATTR_PROPERTIES = "properties";

    /**
     * 对象下的属性
     */
    private List<AbstractBpmFieldConfig> properties;

    public ObjectBpmFieldConfig() {
    }

    public ObjectBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new ObjectValueTypeConfig(configJson));
    }

    public List<AbstractBpmFieldConfig> getProperties() {
        return properties;
    }

    public void setProperties(List<AbstractBpmFieldConfig> properties) {
        this.properties = properties;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();

        return configJson;
    }

}
