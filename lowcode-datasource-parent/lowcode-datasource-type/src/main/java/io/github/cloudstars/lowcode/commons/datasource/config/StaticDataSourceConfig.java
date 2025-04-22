package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 静态数据源配置
 *
 * @author clouds
 */
@DataSourceConfigClass(name = "STATIC")
public class StaticDataSourceConfig extends AbstractDataSourceConfig {

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
    }
}
