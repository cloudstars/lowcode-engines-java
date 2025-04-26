package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * API输出配置
 *
 * @author clouds
 */
public class ApiOutputConfig extends AbstractConfig {

    /**
     * 数据格式
     */
    private XValueTypeConfig valueType;

    /**
     * 是否必填（前端得有返回值）
     */
    private Boolean required;

    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public ApiOutputConfig() {
    }

    public ApiOutputConfig(JsonObject configJson) {
        super(configJson);

        JsonObject valueTypeConfigJson = (JsonObject) configJson.get(XValueTypeConfig.ATTR);
        if (valueTypeConfigJson != null) {
            this.valueType = ValueTypeConfigFactory.newInstance(valueTypeConfigJson);
        }
        this.required = (Boolean) configJson.get(GlobalAttrNames.ATTR_REQUIRED);
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, GlobalAttrNames.ATTR_REQUIRED, this.required);
        ConfigUtils.putJsonIfNotNull(configJson, XValueTypeConfig.ATTR, this.valueType);

        return configJson;
    }


}
