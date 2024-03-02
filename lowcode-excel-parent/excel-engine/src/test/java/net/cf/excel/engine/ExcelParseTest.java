package net.cf.excel.engine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.commons.test.util.FileTestUtils;
import net.cf.excel.engine.commons.CollectionGroup;
import net.cf.excel.engine.commons.NumberField;
import net.cf.excel.engine.commons.ShowGroup;
import net.cf.excel.engine.commons.TextField;
import net.cf.excel.engine.config.ExcelParseConfig;
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
        Workbook workbook = null;
        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/TextField测试.xlsx")) {
            workbook = createWorkbook(fis, "TextField测试.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExcelParseConfig config = new ExcelParseConfig();
        List<ExcelField> excelFields = new ArrayList<>();

        excelFields.add(new TextField("peopleName", "姓名"));
        excelFields.add(new TextField("gender", "性别"));
        config.setParseFields(excelFields);
        config.setTitleStartRow(0);
        config.setTitleEndRow(0);

        JSONArray sourceDataList = JSON.parseArray(JSON.toJSONString(engine.parseExcel(workbook, config)));
        JSONArray targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/TextField测试.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(sourceDataList, targetDataList));
    }

    // 主子关系字段解析
    @Test
    public void testParseExcel_CollectionGroup1() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook;
        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/CollectionGroup测试1.xlsx")) {
            workbook = createWorkbook(fis, "CollectionGroup测试1.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExcelParseConfig config = new ExcelParseConfig();
        List<ExcelField> excelFields = new ArrayList<>();

        excelFields.add(new TextField("peopleName", "姓名"));
        excelFields.add(new TextField("gender", "性别"));
        List<SingleExcelField> subFields = new ArrayList<>();
        subFields.add(new TextField("signInTime", "签到时间"));
        subFields.add(new TextField("signOutTime", "签退时间"));
        ExcelFieldGroup parseFieldGroup = new CollectionGroup("attendance", "考勤记录", subFields);
        excelFields.add(parseFieldGroup);

        config.setParseFields(excelFields);
        config.setTitleStartRow(0);
        config.setTitleEndRow(1);

        JSONArray sourceDataList = JSON.parseArray(JSON.toJSONString(engine.parseExcel(workbook, config)));
        JSONArray targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/CollectionGroup测试.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(sourceDataList, targetDataList));

        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/CollectionGroup测试2.xlsx")) {
            workbook = createWorkbook(fis, "CollectionGroup测试2.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sourceDataList = JSON.parseArray(JSON.toJSONString(engine.parseExcel(workbook, config)));
        targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/CollectionGroup测试.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(sourceDataList, targetDataList));
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
        List<ExcelField> excelFields = new ArrayList<>();

        excelFields.add(new TextField("peopleName", "姓名"));
        List<SingleExcelField> subFields = new ArrayList<>();
        subFields.add(new TextField("gender", "性别"));
        subFields.add(new NumberField("age", "年龄"));
        ExcelFieldGroup parseFieldGroup = new ShowGroup("message", "个人信息", subFields);
        excelFields.add(parseFieldGroup);

        config.setParseFields(excelFields);
        config.setTitleStartRow(0);
        config.setTitleEndRow(1);

        JSONArray sourceDataList = JSON.parseArray(JSON.toJSONString(engine.parseExcel(workbook, config)));
        JSONArray targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/ShowGroup测试.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(sourceDataList, targetDataList));
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
        List<ExcelField> excelFields = new ArrayList<>();

        excelFields.add(new TextField("peopleName", "姓名"));
        List<SingleExcelField> subFields = new ArrayList<>();
        subFields.add(new TextField("gender", "性别"));
        subFields.add(new NumberField("age", "年龄"));

        ExcelFieldGroup showGroup = new ShowGroup("message", "个人信息", subFields);
        excelFields.add(showGroup);

        subFields = new ArrayList<>();
        subFields.add(new TextField("signInTime", "签到时间"));
        subFields.add(new TextField("signOutTime", "签退时间"));
        ExcelFieldGroup collectionGroup = new CollectionGroup("attendance", "考勤记录", subFields);
        excelFields.add(collectionGroup);

        config.setParseFields(excelFields);
        config.setTitleStartRow(0);
        config.setTitleEndRow(1);

        JSONArray sourceDataList = JSON.parseArray(JSON.toJSONString(engine.parseExcel(workbook, config)));
        JSONArray targetDataList = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel解析测试/解析后数据/联合测试1.json"));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(sourceDataList, targetDataList));
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