package io.github.cloudstars.lowcode.pdf.parser;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.datasource.config.DataSourceConfigFactory;
import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;

/**
 * 抽象的PDF元素配置解析器
 *
 * @param <T> 元素配置类型
 */
public abstract class AbstractElementConfigParser<T extends AbstractElementConfig> implements XConfigParser<T> {

    private static final String ATTR_SIZE = "size";
    private static final String ATTR_LABEL = "label";

    /**
     * 解析公共部分
     *
     * @param config 元素配置对象
     * @param configJson 元素JSON配置
     */
    protected void parseCommons(AbstractElementConfig config, JsonObject configJson) {
        config.setSize(ConfigUtils.getInteger(configJson, ATTR_SIZE));
        config.setLabel(ConfigUtils.getString(configJson, ATTR_LABEL));
        config.setDataSource(ConfigUtils.getObject(configJson, XDataSourceConfig.ATTR, (v) -> DataSourceConfigFactory.newInstance(v)));
    }

}
