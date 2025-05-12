package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 静态数据源配置
 *
 * @author clouds
 */
@DataSourceConfigClass(name = "STATIC")
public class StaticDataSourceConfig<V extends XValueTypeConfig> extends AbstractDataSourceConfig<V> {

    // 静态数据配置名称
    private static final String ATTR_DATA = "data";

    /**
     * 静态数据
     */
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public StaticDataSourceConfig() {
    }

    public StaticDataSourceConfig(JsonObject configJson) {
        super(configJson);

        this.data = configJson.get(ATTR_DATA);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_DATA, this.data);

        return configJson;
    }
}
