package io.github.cloudstars.lowcode.object.commons;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.field.AbstractObjectFieldConfig;
import io.github.cloudstars.lowcode.object.commons.field.ObjectFieldConfigFactory;
import io.github.cloudstars.lowcode.object.commons.field.objectref.AbstractObjectRefObjectFieldConfig;

import java.util.List;

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

        this.fields = ConfigUtils.getList(configJson, ATTR_FIELDS, (v) -> (F) ObjectFieldConfigFactory.newInstance(v));
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
    public String getTableName() {
        return null;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putArray(configJson, ATTR_FIELDS, this.fields);

        return configJson;
    }

}
