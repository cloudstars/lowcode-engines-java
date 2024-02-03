package net.cf.excel.engine;

import net.cf.excel.engine.config.ExcelImportConfig;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel服务
 *
 * @author clouds
 */
public interface ExcelEngine {

    /**
     * 导入Excel
     *
     * @param config
     * @param workbook
     */
    void _import(ExcelImportConfig config, Workbook workbook);

    /**
     * 导出Excel
     *
     * @param config
     */
    Workbook export(ExcelImportConfig config);
}
