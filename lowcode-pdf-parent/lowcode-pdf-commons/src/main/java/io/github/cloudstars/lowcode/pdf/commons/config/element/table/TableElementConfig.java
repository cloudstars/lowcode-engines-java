package io.github.cloudstars.lowcode.pdf.commons.config.element.table;

import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;

import java.util.List;

/**
 * PDF表格配置
 *
 * @author clouds
 */
public class TableElementConfig extends AbstractElementConfig {

    /**
     * 列数
     */
    private int columSize;

    /**
     * 列配置
     */
    private List<TableColumnConfig> columns;

    public TableElementConfig() {
    }

    @Override
    public String getType() {
        return "TABLE";
    }

    public int getColumSize() {
        return columSize;
    }

    public void setColumSize(int columSize) {
        this.columSize = columSize;
    }

    public List<TableColumnConfig> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumnConfig> columns) {
        this.columns = columns;
    }


}
