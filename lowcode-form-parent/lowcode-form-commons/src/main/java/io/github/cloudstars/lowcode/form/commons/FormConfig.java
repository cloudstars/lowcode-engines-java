package io.github.cloudstars.lowcode.form.commons;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.form.commons.field.AbstractFieldConfig;
import io.github.cloudstars.lowcode.form.commons.field.FieldConfigFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 表单配置
 *
 * @author clouds
 */
public class FormConfig extends AbstractTypedConfig {

    // 字段列表属性
    private static final String ATTR_FIELDS = "fields";

    /**
     * 字段列表
     */
    private List<AbstractFieldConfig> fields;

    public FormConfig() {
    }

    public FormConfig(JsonObject configJson) {
        super(configJson);

        Object fieldsValue = configJson.get(ATTR_FIELDS);
        if (fieldsValue == null || !(fieldsValue instanceof JsonArray)) {
            throw new RuntimeException("字段信息fields不存在或不是数组格式，请检查：" + configJson);
        }

        List<AbstractFieldConfig> fieldConfigs = new ArrayList<>();
        JsonArray fields = (JsonArray) fieldsValue;
        fields.forEach((field) -> {
            JsonObject fieldConfigJson = (JsonObject) field;
            AbstractFieldConfig fieldConfig = FieldConfigFactory.newInstance(fieldConfigJson);
            if (fieldConfig != null) {
                fieldConfigs.add(fieldConfig);
            }
        });
        this.setFields(fieldConfigs);
    }

    public List<AbstractFieldConfig> getFields() {
        return fields;
    }

    public void setFields(List<AbstractFieldConfig> fields) {
        this.fields = fields;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        // 添加字段配置
        JsonArray fieldJsonArray = new JsonArray();
        this.fields.forEach(field -> {
            fieldJsonArray.add(field.toJson());
        });
        configJson.put(ATTR_FIELDS, fieldJsonArray);

        return configJson;
    }

}
