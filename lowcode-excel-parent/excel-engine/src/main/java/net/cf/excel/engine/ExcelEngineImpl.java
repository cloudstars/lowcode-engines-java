package net.cf.excel.engine;

import net.cf.excel.engine.config.ExcelBuildConfig;
import net.cf.excel.engine.config.ExcelParseConfig;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

public class ExcelEngineImpl implements ExcelEngine {

    @Override
    public List<Map<String, Object>> parseExcel(Workbook workbook, ExcelParseConfig config) {
        return null;
    }

    @Override
    public Workbook buildExcel(List<Map<String, Object>> data, ExcelBuildConfig config) {
        return null;
    }
}
