package io.github.cloudstars.lowcode.object.bpm.form;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.bpm.form.field.AbstractBpmFieldConfig;
import io.github.cloudstars.lowcode.object.bpm.form.field.BpmFieldConfigFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 表单配置
 *
 * @author clouds
 */
public class BpmFormConfig extends AbstractResourceConfig {

    // 字段列表配置名称
    private static final String ATTR_FIELDS = "fields";

    /**
     * 字段列表
     */
    private List<AbstractBpmFieldConfig> fields;

    public BpmFormConfig() {
    }

    public BpmFormConfig(JsonObject configJson) {
        super(configJson);

        Object fieldsValue = configJson.get(ATTR_FIELDS);
        if (fieldsValue == null || !(fieldsValue instanceof JsonArray)) {
            throw new RuntimeException("字段信息fields不存在或不是数组格式，请检查：" + configJson);
        }

        List<AbstractBpmFieldConfig> fieldConfigs = new ArrayList<>();
        JsonArray fields = (JsonArray) fieldsValue;
        fields.forEach((field) -> {
            JsonObject fieldConfigJson = (JsonObject) field;
            AbstractBpmFieldConfig fieldConfig = BpmFieldConfigFactory.newInstance(fieldConfigJson);
            if (fieldConfig != null) {
                fieldConfigs.add(fieldConfig);
            }
        });
        this.fields = fieldConfigs;
    }

    public List<AbstractBpmFieldConfig> getFields() {
        return fields;
    }

    public void setFields(List<AbstractBpmFieldConfig> fields) {
        this.fields = fields;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        // 添加字段列表配置
        ConfigUtils.putArray(configJson, ATTR_FIELDS, this.fields);

        return configJson;
    }

}
