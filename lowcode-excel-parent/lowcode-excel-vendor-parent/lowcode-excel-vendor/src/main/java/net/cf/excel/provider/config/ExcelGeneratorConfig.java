package net.cf.excel.provider.config;

import java.util.List;

/**
 * Excel生成配置
 *
 * @param <T> Excel数据的类型
 * @author clouds
 */
public class ExcelGeneratorConfig<T> {

    /**
     * Excel列配置
     */
    private List<ExcelColumnConfig> columns;

    public List<ExcelColumnConfig> getColumns() {
        return columns;
    }

    public void setColumns(List<ExcelColumnConfig> columns) {
        this.columns = columns;
    }
}
