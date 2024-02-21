package net.cf.excel.engine.config;

import net.cf.excel.engine.commons.ExcelColumn;

import java.util.List;

/**
 * Excel解析配置
 *
 * @author clouds
 */
public class ExcelParseConfig {

    /**
     * EXCEL的列配置
     */
    private List<ExcelColumn> columns;

    public List<ExcelColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<ExcelColumn> columns) {
        this.columns = columns;
    }
}
