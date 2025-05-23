package net.cf.excel.engine;

import com.alibaba.fastjson.JSON;
import io.github.cloudstars.lowcode.commons.test.util.DataCompareTestUtils;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import net.cf.excel.engine.bean.ExcelTitleBean;
import net.cf.excel.engine.bean.ExcelTitleParseConfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-29 16:03
 * @Description: 导入表头解析测试
 */
public class ExcelFieldsParseTest {
    @Test
    public void testParseExcelTitle_1() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook;
        try (FileInputStream fis = new FileInputStream("src/test/resources/excel表头解析测试/联合测试1.xlsx")) {
            workbook = createWorkbook(fis, "联合测试1.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExcelTitleParseConfig config = new ExcelTitleParseConfig();

        config.setTitleStartRow(0);
        config.setTitleEndRow(1);

        List<ExcelTitleBean> sourceDataList = engine.parseExcelTitles(workbook, config);
        List<ExcelTitleBean> targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel表头解析测试/解析后数据/联合测试1.json"))
                .toJavaList(ExcelTitleBean.class);
        Assertions.assertTrue(DataCompareTestUtils.equalsList(sourceDataList, targetDataList));
    }

    private Workbook createWorkbook(InputStream in, String excelFileName) throws IOException {
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(in);
        } else if (excelFileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(in);
        }
        throw new RuntimeException();
    }
}