package io.github.cloudstars.lowcode.pdf.commons.config.element;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.datasource.config.AbstractDataSourceConfig;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 抽象的PDF元素配置类
 *
 * @auau
 */
public abstract class AbstractElementConfig<D extends XValueTypeConfig> extends AbstractConfig implements XElementConfig {

    /**
     * 元素的栅格列数（基于24列栅格布局）
     */
    private int size;

    /**
     * 元素的标题
     */
    private String label;

    /**
     * 数据源配置
     */
    private AbstractDataSourceConfig<D> dataSource;

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
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
    public AbstractDataSourceConfig<D> getDataSource() {
        return dataSource;
    }

    public void setDataSource(AbstractDataSourceConfig<D> dataSource) {
        this.dataSource = dataSource;
    }
}
