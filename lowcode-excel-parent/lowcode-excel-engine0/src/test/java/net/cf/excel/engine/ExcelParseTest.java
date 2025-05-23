package net.cf.excel.engine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.github.cloudstars.lowcode.commons.test.util.DataCompareTestUtils;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import net.cf.excel.engine.bean.ExcelParseConfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-28 17:25
 * @Description: excel解析测试
 */
public class ExcelParseTest {

    // 单个字段解析
    @Test
    public void testParseExcel_TextField() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook;
        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/TextField测试.xlsx")) {
            workbook = createWorkbook(fis, "TextField测试.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExcelParseConfig config = new ExcelOpConfigLoaderImpl().loadExcelParseConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/textTitle.json")));

        List<Map<String, Object>> sourceDataList = engine.parseExcel(workbook, config);
        JSONArray targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/TextField测试.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(JSON.parseArray(JSON.toJSONString(sourceDataList)), targetDataList));
    }

    // 主子关系字段解析
    @Test
    public void testParseExcel_CollectionGroup1() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook;

        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/CollectionGroup测试2.xlsx")) {
            workbook = createWorkbook(fis, "CollectionGroup测试2.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExcelParseConfig config = new ExcelOpConfigLoaderImpl().loadExcelParseConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/collectionGroup.json")));

        List<Map<String, Object>> sourceDataList = engine.parseExcel(workbook, config);
        JSONArray targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/CollectionGroup测试.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(JSON.parseArray(JSON.toJSONString(sourceDataList)), targetDataList));
    }

    @Test
    public void testParseExcel_CollectionGroup2() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook;
        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/CollectionGroup测试1.xlsx")) {
            workbook = createWorkbook(fis, "CollectionGroup测试1.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExcelParseConfig config = new ExcelOpConfigLoaderImpl().loadExcelParseConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/collectionGroup.json")));

        List<Map<String, Object>> sourceDataList = engine.parseExcel(workbook, config);
        JSONArray targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/CollectionGroup测试.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsList(sourceDataList, targetDataList));
    }

    // 展示型表头字段解析
    @Test
    public void testParseExcel_ShowGroup1() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook;
        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/ShowGroup测试1.xlsx")) {
            workbook = createWorkbook(fis, "ShowGroup测试1.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExcelParseConfig config = new ExcelOpConfigLoaderImpl().loadExcelParseConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/showGroup.json")));

        List<Map<String, Object>> sourceDataList = engine.parseExcel(workbook, config);
        JSONArray targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/ShowGroup测试.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(JSON.parseArray(JSON.toJSONString(sourceDataList)), targetDataList));
    }

    // 联合解析测试1
    @Test
    public void testParseExcel_1() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook;
        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/联合测试1.xlsx")) {
            workbook = createWorkbook(fis, "联合测试1.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExcelParseConfig config = new ExcelOpConfigLoaderImpl().loadExcelParseConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/联合测试.json")));

        List<Map<String, Object>> sourceDataList = engine.parseExcel(workbook, config);
        JSONArray targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/联合测试1.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(JSON.parseArray(JSON.toJSONString(sourceDataList)), targetDataList));
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