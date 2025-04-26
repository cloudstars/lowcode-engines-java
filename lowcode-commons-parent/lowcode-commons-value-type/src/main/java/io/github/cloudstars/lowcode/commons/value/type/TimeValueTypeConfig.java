package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.sql.Time;

/**
 * 时间数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "TIME")
public class TimeValueTypeConfig extends AbstractValueTypeConfig<Time> {

    // 日期格式配置名称
    private static final String ATTR_FORMAT = "format";

    /**
     * 日期格式
     */
    private String format;

    public TimeValueTypeConfig() {
    }

    public TimeValueTypeConfig(JsonObject configJson) {
        super(configJson);

        Object formatValue = configJson.get(ATTR_FORMAT);
        if (formatValue != null) {
            this.format = (String) formatValue;
        }
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.TIME;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.putIfNotNull(ATTR_FORMAT, this.format);
        return configJson;
    }

}
