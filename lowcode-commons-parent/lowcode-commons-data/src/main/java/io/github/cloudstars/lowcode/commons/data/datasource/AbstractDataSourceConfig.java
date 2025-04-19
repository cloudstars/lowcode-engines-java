package io.github.cloudstars.lowcode.commons.data.datasource;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的数据源配置
 *
 * @author clouds
 */
public class AbstractDataSourceConfig extends AbstractTypedConfig implements XDataSourceConfig {

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
