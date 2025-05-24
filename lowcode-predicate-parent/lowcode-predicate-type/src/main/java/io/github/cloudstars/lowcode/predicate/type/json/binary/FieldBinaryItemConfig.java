package io.github.cloudstars.lowcode.predicate.type.json.binary;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.config.XResourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

/**
 * 字段类型的二元操作项
 *
 * @author clouds
 */
public class FieldBinaryItemConfig extends AbstractTypedConfig implements XBinaryItemConfig {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段标题
     */
    private String title;

    /**
     * 数据格式
     */
    private XValueTypeConfig valueType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FieldBinaryItemConfig() {
    }

    public FieldBinaryItemConfig(JsonObject configJson) {
        super(configJson);

        this.setType(TYPE_FIELD);
        this.name = (String) configJson.get(XResourceConfig.ATTR_CODE);
        this.title = (String) configJson.get(GlobalAttrNames.ATTR_NAME);
        JsonObject valueTypeConfigJson = (JsonObject) configJson.get(XValueTypeConfig.ATTR);
        this.valueType = ValueTypeConfigFactory.newInstance(valueTypeConfigJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(GlobalAttrNames.ATTR_NAME, this.name);
        configJson.put(GlobalAttrNames.ATTR_TITLE, this.title);
        configJson.put(XValueTypeConfig.ATTR, this.valueType.toJson());
        return configJson;
    }

}
