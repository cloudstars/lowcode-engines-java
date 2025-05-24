package io.github.cloudstars.lowcode.commons.api.config.request;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * API请求体配置
 *
 * @author clouds
 */
public class ApiRequestBodyConfig extends AbstractConfig {

    // 请求体配置名称
    private static final String ATTR_BODY = "body";

    /**
     * 请求体的数据格式配置
     */
    private XValueTypeConfig valueType;

    public ApiRequestBodyConfig() {
    }

    public ApiRequestBodyConfig(JsonObject configJson) {
        super(configJson);

        this.valueType = ValueTypeConfigFactory.newInstance(configJson);
    }

    public ApiRequestBodyConfig(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }


    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putAllRequired(configJson, this.valueType);

        return configJson;
    }

}
