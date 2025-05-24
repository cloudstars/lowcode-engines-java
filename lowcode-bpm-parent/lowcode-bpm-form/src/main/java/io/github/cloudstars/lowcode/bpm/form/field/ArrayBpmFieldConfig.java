package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ArrayValueTypeConfig;

/**
 * 数组字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "ARRAY")
public class ArrayBpmFieldConfig extends AbstractBpmFieldConfig<ArrayValueTypeConfig> {

    // 对象下属性列表的配置名称
    private static final String ATTR_ITEMS = "items";

    /**
     * 数组下的子项
     */
    private AbstractBpmFieldConfig items;

    public ArrayBpmFieldConfig() {
    }

    public ArrayBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        JsonObject itemConfigJson = (JsonObject) configJson.get(ATTR_ITEMS);
        this.items = BpmFieldConfigFactory.newInstance(itemConfigJson);
        this.setValueType(new ArrayValueTypeConfig(configJson));
    }

    public AbstractBpmFieldConfig getItems() {
        return items;
    }

    public void setItems(AbstractBpmFieldConfig items) {
        this.items = items;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putRequiredJsonObject(configJson, ATTR_ITEMS, this.items);

        return configJson;
    }

}
