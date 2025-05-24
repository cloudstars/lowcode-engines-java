package io.github.cloudstars.lowcode.pdf.commons.config.element;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.datasource.config.DataSourceConfigFactory;
import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 抽象的PDF元素配置类
 *
 * @author clouds
 */
public abstract class AbstractElementConfig<D extends XValueTypeConfig> extends AbstractTypedConfig implements XElementConfig {

    private static final String ATTR_SIZE = "size";
    private static final String ATTR_LABEL = "label";

    /**
     * 元素的栅格列数（基于24列栅格布局）
     */
    private Integer size;

    /**
     * 元素的标题
     */
    private String label;

    /**
     * 数据源配置
     */
    private XDataSourceConfig<D> dataSource;

    public AbstractElementConfig() {
    }

    public AbstractElementConfig(JsonObject configJson) {
        super(configJson);

        this.size = ConfigUtils.getInteger(configJson, ATTR_SIZE);
        this.label = ConfigUtils.getString(configJson, ATTR_LABEL);
        this.dataSource = ConfigUtils.getObject(configJson, XDataSourceConfig.ATTR, (v) -> DataSourceConfigFactory.newInstance(v));
    }

    @Override
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public XDataSourceConfig<D> getDataSource() {
        return dataSource;
    }

    public void setDataSource(XDataSourceConfig<D> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, ATTR_SIZE, this.size);
        ConfigUtils.put(configJson, ATTR_LABEL, this.label);
        ConfigUtils.putRequired(configJson, XDataSourceConfig.ATTR, this.dataSource);

        return configJson;
    }

}
