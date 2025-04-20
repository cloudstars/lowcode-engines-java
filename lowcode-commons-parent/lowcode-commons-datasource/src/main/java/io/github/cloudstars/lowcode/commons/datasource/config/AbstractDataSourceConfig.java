package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的数据源配置
 *
 * @author clouds
 */
public class AbstractDataSourceConfig extends AbstractTypedConfig implements XDataSourceConfig {

    public AbstractDataSourceConfig() {
    }

    public AbstractDataSourceConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
