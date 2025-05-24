package io.github.cloudstars.lowcode.bpm.form.field.selector;

import io.github.cloudstars.lowcode.bpm.form.field.AbstractBpmFieldConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.datasource.config.DataSourceConfigFactory;
import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.SelectorValueTypeConfig;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

/**
 * 抽象的支持选择的字段配置
 *
 * @author clouds
 */
public abstract class AbstractSelectableSupportedBpmFieldConfig<T extends XValueTypeConfig> extends AbstractBpmFieldConfig<SelectorValueTypeConfig<T>> {

    // 是否多选配置名称
    private static final String ATTR_MULTIPLE = "multiple";

    // 数据源配置名称
    private static final String ATTR_DATASOURCE = "dataSource";

    /**
     * 是否多选
     */
    private Boolean multiple;

    /**
     * 数据源
     */
    private XDataSourceConfig dataSource;

    public AbstractSelectableSupportedBpmFieldConfig() {
    }

    public AbstractSelectableSupportedBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        ConfigUtils.consumeIfPresent(configJson, ATTR_MULTIPLE, (v) -> {
            this.multiple = (Boolean) v;
        });
        ConfigUtils.consume(configJson, ATTR_DATASOURCE, (v) -> {
            JsonObject dataSourceConfigJson = (JsonObject) v;
            // TODO 将field上面的字段配置放到dataSource上面
            this.dataSource = DataSourceConfigFactory.newInstance(dataSourceConfigJson);
        });
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public XDataSourceConfig getDataSource() {
        return dataSource;
    }

    public void setDataSource(XDataSourceConfig dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 根据单端选设置最终的数据格式
     *
     * @param singleValueType 单选时的数据格式
     */
    protected void setTargetValueType(XValueTypeConfig singleValueType) {
        SelectorValueTypeConfig<T> targetValueType = new SelectorValueTypeConfig(singleValueType);
        if (this.multiple != null) {
            targetValueType.setMultiple(this.multiple);
        }
        this.setValueType(targetValueType);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, ATTR_MULTIPLE, this.multiple);
        ConfigUtils.putRequiredJsonObject(configJson, ATTR_DATASOURCE, this.dataSource);

        return configJson;
    }

}
