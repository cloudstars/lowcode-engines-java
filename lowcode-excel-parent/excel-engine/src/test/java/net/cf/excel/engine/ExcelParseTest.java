package net.cf.excel.engine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.commons.test.util.FileTestUtils;
import net.cf.excel.engine.bean.ExcelParseConfig;
import net.cf.excel.engine.commons.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
        ExcelParseConfig config = new ExcelParseConfig();
        List<ExcelTitle> excelFields = new ArrayList<>();

        excelFields.add(new SingleExcelTitleImpl("peopleName", "姓名", new TextDataFormatter()));
        excelFields.add(new SingleExcelTitleImpl("gender", "性别", new TextDataFormatter()));
        config.setExcelTitles(excelFields);
        config.setTitleStartRow(0);
        config.setTitleEndRow(0);

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

        ExcelParseConfig config = new ExcelParseConfig();
        List<ExcelTitle> excelTitles = new ArrayList<>();

        excelTitles.add(new SingleExcelTitleImpl("peopleName", "姓名", new TextDataFormatter()));
        excelTitles.add(new SingleExcelTitleImpl("gender", "性别", new TextDataFormatter()));
        List<SingleExcelTitle> subFields = new ArrayList<>();
        subFields.add(new SingleExcelTitleImpl("signInTime", "签到时间", new TextDataFormatter()));
        subFields.add(new SingleExcelTitleImpl("signOutTime", "签退时间", new TextDataFormatter()));
        ExcelTitleGroup parseFieldGroup = new ExcelTitleGroupImpl("attendance", "考勤记录", true, new CollectionGroupDataFormatter(), subFields);
        excelTitles.add(parseFieldGroup);

        config.setExcelTitles(excelTitles);
        config.setTitleStartRow(0);
        config.setTitleEndRow(1);

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
        ExcelParseConfig config = new ExcelParseConfig();
        List<ExcelTitle> excelTitles = new ArrayList<>();

        excelTitles.add(new SingleExcelTitleImpl("peopleName", "姓名", new TextDataFormatter()));
        excelTitles.add(new SingleExcelTitleImpl("gender", "性别", new TextDataFormatter()));
        List<SingleExcelTitle> subFields = new ArrayList<>();
        subFields.add(new SingleExcelTitleImpl("signInTime", "签到时间", new TextDataFormatter()));
        subFields.add(new SingleExcelTitleImpl("signOutTime", "签退时间", new TextDataFormatter()));
        ExcelTitleGroup parseFieldGroup = new ExcelTitleGroupImpl("attendance", "考勤记录", true, new CollectionGroupDataFormatter(), subFields);
        excelTitles.add(parseFieldGroup);

        config.setExcelTitles(excelTitles);
        config.setTitleStartRow(0);
        config.setTitleEndRow(1);

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
        ExcelParseConfig config = new ExcelParseConfig();
        List<ExcelTitle> excelTitles = new ArrayList<>();

        excelTitles.add(new SingleExcelTitleImpl("peopleName", "姓名", new TextDataFormatter()));
        List<SingleExcelTitle> subFields = new ArrayList<>();
        subFields.add(new SingleExcelTitleImpl("gender", "性别", new TextDataFormatter()));
        subFields.add(new SingleExcelTitleImpl("age", "年龄", new NumberDataFormatter()));
        ExcelTitleGroup parseFieldGroup = new ExcelTitleGroupImpl("message", "个人信息", false, null, subFields);
        excelTitles.add(parseFieldGroup);

        config.setExcelTitles(excelTitles);
        config.setTitleStartRow(0);
        config.setTitleEndRow(1);

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
        ExcelParseConfig config = new ExcelParseConfig();
        List<ExcelTitle> excelTitles = new ArrayList<>();

        excelTitles.add(new SingleExcelTitleImpl("peopleName", "姓名", new TextDataFormatter()));
        List<SingleExcelTitle> subFields = new ArrayList<>();
        subFields.add(new SingleExcelTitleImpl("gender", "性别", new TextDataFormatter()));
        subFields.add(new SingleExcelTitleImpl("age", "年龄", new NumberDataFormatter()));

        ExcelTitleGroup showGroup = new ExcelTitleGroupImpl("message", "个人信息", false, null, subFields);
        excelTitles.add(showGroup);

        subFields = new ArrayList<>();
        subFields.add(new SingleExcelTitleImpl("signInTime", "签到时间", new TextDataFormatter()));
        subFields.add(new SingleExcelTitleImpl("signOutTime", "签退时间", new TextDataFormatter()));
        ExcelTitleGroup collectionGroup = new ExcelTitleGroupImpl("attendance", "考勤记录", true, new CollectionGroupDataFormatter(), subFields);
        excelTitles.add(collectionGroup);

        config.setExcelTitles(excelTitles);
        config.setTitleStartRow(0);
        config.setTitleEndRow(1);

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