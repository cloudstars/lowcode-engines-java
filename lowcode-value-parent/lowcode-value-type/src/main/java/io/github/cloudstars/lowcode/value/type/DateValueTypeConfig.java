package io.github.cloudstars.lowcode.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Date;

/**
 * 日期数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "DATE", valueClass = Date.class)
public class DateValueTypeConfig extends AbstractValueTypeConfig {

    // 日期格式配置名称
    private static final String ATTR_FORMAT = "format";

    /**
     * 日期格式
     */
    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public DateValueTypeConfig() {
    }

    public DateValueTypeConfig(JsonObject configJson) {
        super(configJson);

        Object formatValue = configJson.get(ATTR_FORMAT);
        if (formatValue != null) {
            this.format = (String) formatValue;
        }
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.NUMBER;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, ATTR_FORMAT, this.format);
        return configJson;
    }

}
