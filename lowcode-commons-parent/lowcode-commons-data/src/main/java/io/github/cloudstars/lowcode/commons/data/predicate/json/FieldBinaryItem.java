package io.github.cloudstars.lowcode.commons.data.predicate.json;

import io.github.cloudstars.lowcode.commons.data.valuetype.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.data.valuetype.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XIdentifiedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 字段类型的二元操作项
 *
 * @author clouds
 */
public class FieldBinaryItem extends AbstractTypedConfig implements BinaryItem {

    /**
     * 字段代码
     */
    private String code;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 数据格式
     */
    private XValueTypeConfig valueType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldBinaryItem() {
    }

    public FieldBinaryItem(JsonObject configJson) {
        super(configJson);

        this.setType(BinaryItem.TYPE_FIELD);
        this.code = (String) configJson.get(XIdentifiedConfig.ATTR_CODE);
        this.name = (String) configJson.get(XIdentifiedConfig.ATTR_NAME);
        JsonObject valueTypeConfigJson = (JsonObject) configJson.get(XValueTypeConfig.ATTR);
        this.valueType = ValueTypeConfigFactory.newInstance(valueTypeConfigJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XIdentifiedConfig.ATTR_CODE, this.code);
        configJson.put(XIdentifiedConfig.ATTR_NAME, this.name);
        configJson.put(XValueTypeConfig.ATTR, this.valueType.toJson());
        return configJson;
    }

}
