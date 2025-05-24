package io.github.cloudstars.lowcode.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Arrays;

/**
 * “树”选项数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "TREE-OPTION", valueClass = TreeOption.class)
public class TreeOptionValueTypeConfig extends AbstractMapValueTypeConfig {

    /**
     * 标识字段配置名称
     */
    private String keyField;

    /**
     * 标签配置名称
     */
    private String labelField;

    public TreeOptionValueTypeConfig() {
    }

    public TreeOptionValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.labelField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_LABEL_FIELD);
        this.keyField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_KEY_FIELD);

        MapPropertyConfig labelPropertyConfig = new MapPropertyConfig();
        {
            labelPropertyConfig.setName(this.labelField);
            labelPropertyConfig.setLabel("标签");
            TextValueTypeConfig labelValueTypeConfig = new TextValueTypeConfig();
            labelPropertyConfig.setValueType(labelValueTypeConfig);
        }

        MapPropertyConfig keyPropertyConfig = new MapPropertyConfig();
        {
            keyPropertyConfig.setName(this.keyField);
            keyPropertyConfig.setLabel("标识");
            TextValueTypeConfig keyValueTypeConfig = new TextValueTypeConfig();
            keyPropertyConfig.setValueType(keyValueTypeConfig);
        }

        this.properties = Arrays.asList(labelPropertyConfig, keyPropertyConfig);
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
        ConfigUtils.put(configJson, GlobalAttrNames.ATTR_KEY_FIELD, this.keyField);
        ConfigUtils.put(configJson, GlobalAttrNames.ATTR_LABEL_FIELD, this.labelField);

        return configJson;
    }

}
