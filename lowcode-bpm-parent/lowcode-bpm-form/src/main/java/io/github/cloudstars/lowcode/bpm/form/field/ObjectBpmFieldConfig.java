package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.MapValueTypeConfig;

import java.util.List;

/**
 * 对象字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "OBJECT")
public class ObjectBpmFieldConfig extends AbstractBpmFieldConfig<MapValueTypeConfig> {

    /**
     * 对象下的属性
     */
    private List<AbstractBpmFieldConfig> properties;

    public ObjectBpmFieldConfig() {
    }

    public ObjectBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new MapValueTypeConfig(configJson));
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
