package io.github.cloudstars.lowcode.object.core.editor;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.core.editor.field.AbstractObjectFieldConfig;
import io.github.cloudstars.lowcode.object.core.editor.field.ObjectFieldConfigFactory;
import io.github.cloudstars.lowcode.object.core.editor.field.objectref.AbstractObjectRefObjectFieldConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 抽象的模型配置
 *
 * @author clouds
 */
public class AbstractObjectConfig<F extends AbstractObjectFieldConfig, RF extends AbstractObjectRefObjectFieldConfig> extends AbstractResourceConfig implements XObjectConfig<F, RF> {

    // 字段列表配置名称
    private static final String ATTR_FIELDS = "fields";

    /**
     * 字段列表
     */
    private List<F> fields;

    public AbstractObjectConfig() {
    }

    public AbstractObjectConfig(JsonObject configJson) {
        super(configJson);

        Object fieldsValue = configJson.get(ATTR_FIELDS);
        if (fieldsValue == null || !(fieldsValue instanceof JsonArray)) {
            throw new RuntimeException("字段信息fields不存在或不是数组格式，请检查：" + configJson);
        }

        List<F> fieldConfigs = new ArrayList<>();
        JsonArray fields = (JsonArray) fieldsValue;
        fields.forEach((field) -> {
            JsonObject fieldConfigJson = (JsonObject) field;
            F fieldConfig = (F) ObjectFieldConfigFactory.newInstance(fieldConfigJson);
            if (fieldConfig != null) {
                fieldConfigs.add(fieldConfig);
            }
        });
        this.fields = fieldConfigs;
    }

    public List<F> getFields() {
        return fields;
    }

    public void setFields(List<F> fields) {
        this.fields = fields;
    }

    @Override
    public F getField(String fieldName) {
        return null;
    }

    @Override
    public RF getObjectRefField(String refFieldName) {
        return null;
    }

    @Override
    public F getPrimaryField() {
        return null;
    }

    @Override
    public RF getMasterField() {
        return null;
    }

    @Override
    public void validate(Map<String, Object> dataMap) {

    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        // 添加字段列表配置
        configJson.put(ATTR_FIELDS, ConfigUtils.toJsonArray(this.fields));

        return configJson;
    }

}
