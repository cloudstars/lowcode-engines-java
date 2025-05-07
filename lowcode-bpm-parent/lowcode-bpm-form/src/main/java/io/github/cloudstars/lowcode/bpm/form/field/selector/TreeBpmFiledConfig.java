package io.github.cloudstars.lowcode.bpm.form.field.selector;

import io.github.cloudstars.lowcode.bpm.form.field.BpmFieldConfigClass;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.TreeOptionValueTypeConfig;

/**
 * 树型字段类型
 *
 * @author clouds
 *
 */
@BpmFieldConfigClass(name = "TREE")
public class TreeBpmFiledConfig extends AbstractSelectableSupportedBpmFieldConfig<TreeOptionValueTypeConfig> {

    private String keyField;

    private String labelField;

    public TreeBpmFiledConfig() {
    }

    public TreeBpmFiledConfig(JsonObject configJson) {
        super(configJson);

        this.labelField = (String) configJson.get(GlobalAttrNames.ATTR_LABEL_FIELD);
        this.keyField = (String) configJson.get(GlobalAttrNames.ATTR_KEY_FIELD);
        this.setTargetValueType(new TreeOptionValueTypeConfig(configJson));
    }

    public String getKeyField() {
        return keyField;
    }

    public void setKeyField(String keyField) {
        this.keyField = keyField;
    }

    public String getLabelField() {
        return labelField;
    }

    public void setLabelField(String labelField) {
        this.labelField = labelField;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
