package io.github.cloudstars.lowcode.object.bpm.form.field.selector;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.datasource.config.DataSourceConfigFactory;
import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.bpm.form.field.AbstractBpmFieldConfig;

/**
 * 抽象的支持数据源的字段配置
 *
 * @author clouds
 */
public abstract class AbstractDataSourceSupportedBpmFieldConfig extends AbstractBpmFieldConfig {

    // 数据源配置名称
    private static final String ATTR_DATASOURCE = "dataSource";

    /**
     * 数据源
     */
    private XDataSourceConfig dataSource;


    public XDataSourceConfig getDataSource() {
        return dataSource;
    }

    public void setDataSource(XDataSourceConfig dataSource) {
        this.dataSource = dataSource;
    }

    public AbstractDataSourceSupportedBpmFieldConfig() {
    }

    public AbstractDataSourceSupportedBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        JsonObject dataSourceConfigJson = (JsonObject) configJson.get(XDataSourceConfig.ATTR);
        if (dataSourceConfigJson != null) {
            this.dataSource = DataSourceConfigFactory.newInstance(dataSourceConfigJson);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putJsonIfNotNull(configJson, ATTR_DATASOURCE, this.dataSource);

        return configJson;
    }

}
