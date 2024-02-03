package net.cf.excel.engine.config;

import java.util.List;

/**
 * Excel导入配置
 *
 * @author clouds
 */
public class ExcelImportConfig {

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
