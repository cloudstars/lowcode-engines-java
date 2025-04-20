package io.github.cloudstars.lowcode.commons.data.field;

import io.github.cloudstars.lowcode.commons.data.datasource.AbstractDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的支持数据源的字段配置
 *
 * @author clouds
 */
public abstract class AbstractDataSourceSupportedFieldConfig extends AbstractFieldConfig implements XFieldConfig {

    // 数据源配置名称
    private static final String ATTR_DATASOURCE = "dataSource";

    /**
     * 数据源
     */
    private AbstractDataSourceConfig dataSource;


    public AbstractDataSourceConfig getDataSource() {
        return dataSource;
    }

    public void setDataSource(AbstractDataSourceConfig dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_DATASOURCE, this.dataSource.toJson());

        return configJson;
    }

}
