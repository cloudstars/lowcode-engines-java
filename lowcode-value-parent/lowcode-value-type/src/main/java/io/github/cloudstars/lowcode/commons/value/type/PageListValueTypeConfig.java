package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Arrays;

/**
 * 分页列表数据源
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "PAGE-LIST", valueClass = PageResult.class)
public class PageListValueTypeConfig extends AbstractMapValueTypeConfig {

    /**
     * 列表数据的标识字段名配置名称
     */
    private String keyField;

    public PageListValueTypeConfig() {
    }

    public PageListValueTypeConfig(JsonObject configJson) {
        super(configJson);


        this.keyField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_KEY_FIELD);

        MapPropertyConfig totalPropertyConfig = new MapPropertyConfig();
        {
            totalPropertyConfig.setName("total");
            totalPropertyConfig.setLabel("数据总条数");
            NumberValueTypeConfig totalValueTypeConfig = new NumberValueTypeConfig();
            totalValueTypeConfig.setPrecision(0);
            totalValueTypeConfig.setMaxValue(Integer.MAX_VALUE);
            totalPropertyConfig.setValueType(totalValueTypeConfig);
        }

        MapPropertyConfig listPropertyConfig = new MapPropertyConfig();
        {
            listPropertyConfig.setName("list");
            listPropertyConfig.setLabel("当前页数据");
            listPropertyConfig.setValueType(new ListValueTypeConfig(configJson));
        }

        this.properties = Arrays.asList(totalPropertyConfig, listPropertyConfig);
    }

    public String getKeyField() {
        return keyField;
    }

    public void setKeyField(String keyField) {
        this.keyField = keyField;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putRequired(configJson, GlobalAttrNames.ATTR_KEY_FIELD, this.keyField);

        return configJson;
    }

}


