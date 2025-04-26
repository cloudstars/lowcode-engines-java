package io.github.cloudstars.lowcode.commons.predicate.type.json.binary;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.XResourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 字段类型的二元操作项
 *
 * @author clouds
 */
public class FieldBinaryItemConfig extends AbstractTypedConfig implements XBinaryItemConfig {

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

    public FieldBinaryItemConfig() {
    }

    public FieldBinaryItemConfig(JsonObject configJson) {
        super(configJson);

        this.setType(TYPE_FIELD);
        this.code = (String) configJson.get(XResourceConfig.ATTR_CODE);
        this.name = (String) configJson.get(XResourceConfig.ATTR_NAME);
        JsonObject valueTypeConfigJson = (JsonObject) configJson.get(XValueTypeConfig.ATTR);
        this.valueType = ValueTypeConfigFactory.newInstance(valueTypeConfigJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XResourceConfig.ATTR_CODE, this.code);
        configJson.put(XResourceConfig.ATTR_NAME, this.name);
        configJson.put(XValueTypeConfig.ATTR, this.valueType.toJson());
        return configJson;
    }

}
