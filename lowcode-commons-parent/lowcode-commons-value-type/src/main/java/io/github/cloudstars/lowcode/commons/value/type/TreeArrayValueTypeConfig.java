package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 树形数组数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "TABLE-ARRAY", valueClass = List.class)
public class TreeArrayValueTypeConfig extends ArrayValueTypeConfig {

    /**
     * 父标识字段名配置名称
     */
    private String parentKeyField;

    public TreeArrayValueTypeConfig() {
    }

    public TreeArrayValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.parentKeyField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_KEY_FIELD);
    }

    public String getParentKeyField() {
        return parentKeyField;
    }

    public void setParentKeyField(String parentKeyField) {
        this.parentKeyField = parentKeyField;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, GlobalAttrNames.ATTR_KEY_FIELD, this.parentKeyField);

        return configJson;
    }

}
