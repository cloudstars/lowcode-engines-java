package io.github.cloudstars.lowcode.object.form.config;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.form.config.field.AbstractObjectFieldConfig;
import io.github.cloudstars.lowcode.object.form.config.field.FieldConfigFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 表单配置
 *
 * @author clouds
 */
public class FormConfig extends AbstractResourceConfig {

    // 字段列表配置名称
    private static final String ATTR_FIELDS = "fields";

    /**
     * 字段列表
     */
    private List<AbstractObjectFieldConfig> fields;

    public FormConfig() {
    }

    public FormConfig(JsonObject configJson) {
        super(configJson);

        Object fieldsValue = configJson.get(ATTR_FIELDS);
        if (fieldsValue == null || !(fieldsValue instanceof JsonArray)) {
            throw new RuntimeException("字段信息fields不存在或不是数组格式，请检查：" + configJson);
        }

        List<AbstractObjectFieldConfig> fieldConfigs = new ArrayList<>();
        JsonArray fields = (JsonArray) fieldsValue;
        fields.forEach((field) -> {
            JsonObject fieldConfigJson = (JsonObject) field;
            AbstractObjectFieldConfig fieldConfig = FieldConfigFactory.newInstance(fieldConfigJson);
            if (fieldConfig != null) {
                fieldConfigs.add(fieldConfig);
            }
        });
        this.fields = fieldConfigs;
    }

    public List<AbstractObjectFieldConfig> getFields() {
        return fields;
    }

    public void setFields(List<AbstractObjectFieldConfig> fields) {
        this.fields = fields;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        // 添加字段列表配置
        configJson.put(ATTR_FIELDS, ConfigUtils.toJsonArray(this.fields));

        return configJson;
    }

}
