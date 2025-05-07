package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.*;

import java.util.Arrays;

/**
 * 树型数据格式
 *
 * @author clouds 
 */
@ValueTypeConfigClass(name = "TREE", valueClass = TreeNode.class)
public class TreeDataSourceValueType extends AbstractObjectValueTypeConfig {

    private String labelField;

    /**
     * 标识字段名配置名称
     */
    private String keyField;

    public TreeDataSourceValueType() {
    }

    public TreeDataSourceValueType(JsonObject configJson) {
        super(configJson);

        this.keyField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_KEY_FIELD);
        this.labelField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_LABEL_FIELD);

        ObjectPropertyConfig labelPropertyConfig = new ObjectPropertyConfig();
        {
            labelPropertyConfig.setName(this.labelField);
            labelPropertyConfig.setLabel("标签");
            TextValueTypeConfig labelValueTypeConfig = new TextValueTypeConfig();
            labelPropertyConfig.setValueType(labelValueTypeConfig);
        }

        ObjectPropertyConfig keyPropertyConfig = new ObjectPropertyConfig();
        {
            keyPropertyConfig.setName(this.keyField);
            keyPropertyConfig.setLabel("标识");
            TextValueTypeConfig keyValueTypeConfig = new TextValueTypeConfig();
            keyPropertyConfig.setValueType(keyValueTypeConfig);
        }

        ObjectPropertyConfig childrenPropertyConfig = new ObjectPropertyConfig();
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


