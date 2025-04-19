package io.github.cloudstars.lowcode.commons.data.expression.json;

import io.github.cloudstars.lowcode.commons.data.valuetype.ValueTypeConfigParser;
import io.github.cloudstars.lowcode.commons.data.valuetype.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 字段类型的二元操作项
 *
 * @author clouds
 */
public class FieldBinaryItem extends AbstractConfig implements BinaryItem {

    /**
     * 字段名
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

    /**
     * 数据格式配置解析器
     */
    private ValueTypeConfigParser valueTypeConfigParser = new ValueTypeConfigParser();

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

    public FieldBinaryItem() {
    }

    public FieldBinaryItem(JsonObject configJson) {
        super(configJson);

        this.name = (String) configJson.get(XConfig.ATTR_NAME);
        this.title = (String) configJson.get(XConfig.ATTR_TITLE);
        JsonObject valueTypeConfigJson = (JsonObject) configJson.get(XValueTypeConfig.ATTR);
        this.valueType = this.valueTypeConfigParser.fromJson(valueTypeConfigJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_TYPE, BinaryItem.TYPE_FIELD);
        configJson.put(ATTR_NAME, this.name);
        configJson.put(ATTR_TITLE, this.title);
        configJson.put(XValueTypeConfig.ATTR, this.valueType.toJson());
        return configJson;
    }

}
