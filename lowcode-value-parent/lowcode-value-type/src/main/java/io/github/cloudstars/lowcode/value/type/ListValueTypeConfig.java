package io.github.cloudstars.lowcode.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 列表数据格式
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "LIST", valueClass = List.class)
public class ListValueTypeConfig extends AbstractArrayValueTypeConfig{

    /**
     * 标识字段名配置名称
     */
    private String keyField;

    public ListValueTypeConfig() {
    }

    public ListValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.keyField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_KEY_FIELD);
    }


    public String getKeyField() {
        return keyField;
    }

    public void setKeyField(String keyField) {
        this.keyField = keyField;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putRequired(configJson, GlobalAttrNames.ATTR_KEY_FIELD, this.keyField);

        return configJson;
    }
}


