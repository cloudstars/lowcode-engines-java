package io.github.cloudstars.lowcode.form.commons.field;

import io.github.cloudstars.lowcode.commons.data.source.XDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的支持数据源的字段配置
 *
 * @author clouds
 */
public class AbstractDataSourceSupportedFieldConfig extends AbstractFieldConfig implements XFieldConfig {

    // 数据源属性
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

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_DATASOURCE, this.dataSource.toJson());
        return configJson;
    }

}
