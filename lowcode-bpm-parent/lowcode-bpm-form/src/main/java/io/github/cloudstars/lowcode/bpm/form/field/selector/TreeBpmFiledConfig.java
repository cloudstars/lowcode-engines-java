package io.github.cloudstars.lowcode.bpm.form.field.selector;

import io.github.cloudstars.lowcode.bpm.form.field.BpmFieldConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ObjectValueTypeConfig;

/**
 * 树型字段类型
 *
 * @author clouds
 *
 */
@BpmFieldConfigClass(name = "TREE")
public class TreeBpmFiledConfig extends AbstractDataSourceSupportedBpmFieldConfig<ObjectValueTypeConfig> {

    private String idField;

    private String parentIdField;

    public TreeBpmFiledConfig() {
    }

    public TreeBpmFiledConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new ObjectValueTypeConfig(configJson));
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getParentIdField() {
        return parentIdField;
    }

    public void setParentIdField(String parentIdField) {
        this.parentIdField = parentIdField;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
