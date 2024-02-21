package net.cf.excel.engine;

import net.cf.excel.engine.config.ExcelImportConfig;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

/**
 * Excel服务
 *
 * @author clouds
 */
public interface ExcelEngine {

    /**
     * @param config
     * @param workbook
     * @return List<Map<String,Object>>
     * @description 解析一个Excel文件,返回解析出的数据
     */
    List<Map<String, Object>> parseExcel(Workbook workbook, ExcelImportConfig config);

    /**
     * @param data
     * @param config
     * @return Workbook
     * @description 基于数据和配置生成Excel
     */
    Workbook buildExcel(List<Map<String, Object>> data, ExcelImportConfig config);
}
