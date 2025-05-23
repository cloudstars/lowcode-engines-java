package net.cf.excel.engine;

import net.cf.excel.engine.bean.ExcelBuildConfig;
import net.cf.excel.engine.bean.ExcelParseConfig;
import net.cf.excel.engine.bean.ExcelTitleBean;
import net.cf.excel.engine.bean.ExcelTitleParseConfig;
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
     * @return List<Map < String, Object>>
     * @description 解析一个excel文件, 返回解析出的数据
     */
    List<Map<String, Object>> parseExcel(Workbook workbook, ExcelParseConfig config);

    /**
     * 解析一个excel文件,返回解析出的表头信息
     *
     * @param workbook
     * @param config
     * @return
     */
    List<ExcelTitleBean> parseExcelTitles(Workbook workbook, ExcelTitleParseConfig config);

    /**
     * @param data
     * @param config
     * @return Workbook
     * @description 基于数据和配置生成excel
     */
    Workbook buildExcel(List<Map<String, Object>> data, ExcelBuildConfig config);
}
