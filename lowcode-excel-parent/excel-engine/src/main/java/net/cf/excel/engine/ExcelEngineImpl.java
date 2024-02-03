package net.cf.excel.engine;

import net.cf.excel.engine.config.ExcelImportConfig;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelEngineImpl implements ExcelEngine {

    /**
     * Excel数据存储器
     */
    private ExcelDataStore excelDataStore;

    private ExcelEngineImpl(ExcelDataStore excelDataStore) {
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
