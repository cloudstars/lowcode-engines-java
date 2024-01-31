package net.cf.excel.service;

import net.cf.excel.service.config.ExcelImportConfig;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel服务
 *
 * @author clouds
 */
public interface ExcelService {

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
