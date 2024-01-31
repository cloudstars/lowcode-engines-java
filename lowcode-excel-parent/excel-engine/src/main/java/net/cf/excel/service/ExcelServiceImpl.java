package net.cf.excel.service;

import net.cf.excel.service.config.ExcelImportConfig;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelServiceImpl implements ExcelService {

    /**
     * Excel数据存储器
     */
    private ExcelDataStore excelDataStore;

    private ExcelServiceImpl(ExcelDataStore excelDataStore) {
        this.excelDataStore = excelDataStore;
    }


    @Override
    public void _import(ExcelImportConfig config, Workbook workbook) {

    }

    @Override
    public Workbook export(ExcelImportConfig config) {
        return null;
    }
}
