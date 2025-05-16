package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Arrays;

/**
 * 树型数据格式（用于数据源或者API的返回值数据格式）
 *
 * @author clouds 
 */
@ValueTypeConfigClass(name = "TREE", valueClass = TreeNode.class)
public class TreeValueTypeConfig extends AbstractMapValueTypeConfig {

    /**
     * 标签字段名配置名称
     */
    private String labelField;

    /**
     * 标识字段名配置名称
     */
    private String keyField;

    public TreeValueTypeConfig() {
    }

    public TreeValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.keyField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_KEY_FIELD);
        this.labelField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_LABEL_FIELD);

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

        MapPropertyConfig childrenPropertyConfig = new MapPropertyConfig();
        {
            childrenPropertyConfig.setName("children");
            childrenPropertyConfig.setLabel("儿子节点");
            ArrayValueTypeConfig childrenValueTypeConfig = new ArrayValueTypeConfig();
            childrenPropertyConfig.setValueType(childrenValueTypeConfig);
        }

        this.properties = Arrays.asList(labelPropertyConfig, keyPropertyConfig, childrenPropertyConfig);
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


