package io.github.cloudstars.lowcode.object.method.editor;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.AbstractObjectResourceConfig;
import io.github.cloudstars.lowcode.object.commons.ObjectAttrNames;

import java.util.List;

/**
 * 抽象的模型方法配置
 *
 * @author clouds
 */
public abstract class AbstractObjectMethodConfig extends AbstractObjectResourceConfig implements XObjectMethodConfig {

    /**
     * 模型的字段列表编号
     */
    private List<String> fieldKeys;


    public AbstractObjectMethodConfig() {
    }

    public AbstractObjectMethodConfig(JsonObject configJson) {
        super(configJson);

        this.fieldKeys = (List<String>) ConfigUtils.get(configJson, ObjectAttrNames.ATTR_FIELD_KEYS);
    }

    public List<String> getFieldKeys() {
        return fieldKeys;
    }

    public void setFieldKeys(List<String> fieldKeys) {
        this.fieldKeys = fieldKeys;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putRequired(configJson, ObjectAttrNames.ATTR_FIELD_KEYS, this.fieldKeys);

        return configJson;
    }
}
