package io.github.cloudstars.lowcode.commons.data.datasource;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数据源解析器
 *
 * @author clouds
 */
public class DataSourceConfigParser implements XConfigParser<XDataSourceConfig> {

    @Override
    public XDataSourceConfig fromJson(JsonObject configJson) {
        Object typeValue = configJson.get(XConfig.ATTR_TYPE);
        if (typeValue == null) {
            throw new RuntimeException("数据源配置中type不能为空，请检查您的配置：" + configJson);
        }

        return DataSourceConfigFactory.newInstance(configJson);
    }

}
