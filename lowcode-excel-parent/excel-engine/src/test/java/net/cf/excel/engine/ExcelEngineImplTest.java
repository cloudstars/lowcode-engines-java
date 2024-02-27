package net.cf.excel.engine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.cf.commons.test.util.DataCompareUtils;
import net.cf.commons.test.util.FileUtils;
import net.cf.excel.engine.commons.CollectionGroup;
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

public class ExcelEngineImplTest {

    // 单个字段解析
    @Test
    public void testParseExcel_TextField() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook = null;
        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/TextField测试.xlsx")) {
            workbook = createWorkbook(fis, "TextField测试.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelParseConfig config = new ExcelParseConfig();
        List<ParseField> parseFields = new ArrayList<>();
        parseFields.add(new TextField("peopleName", "姓名"));
        parseFields.add(new TextField("gender", "性别"));
        config.setParseFields(parseFields);
        config.setTitleStartRow(0);
        config.setTitleEndRow(0);

        JSONArray sourceDataList = JSON.parseArray(JSON.toJSONString(engine.parseExcel(workbook, config)));
        JSONArray targetDataList = JSON.parseArray(FileUtils.loadTextFromClasspath("excel解析测试/解析后数据/TextField测试.json"));
        Assertions.assertTrue(DataCompareUtils.equalsJsonArray(sourceDataList, targetDataList));
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
        List<ParseField> parseFields = new ArrayList<>();
        parseFields.add(new TextField("peopleName", "姓名"));
        parseFields.add(new TextField("gender", "性别"));

        List<SingleParseField> subFields = new ArrayList<>();
        subFields.add(new TextField("signInTime", "签到时间"));
        subFields.add(new TextField("signOutTime", "签退时间"));
        ParseFieldGroup parseFieldGroup = new CollectionGroup("attendance", "考勤记录", subFields);
        parseFields.add(parseFieldGroup);

        config.setParseFields(parseFields);
        config.setTitleStartRow(0);
        config.setTitleEndRow(1);

        JSONArray sourceDataList = JSON.parseArray(JSON.toJSONString(engine.parseExcel(workbook, config)));
        JSONArray targetDataList = JSON.parseArray(FileUtils.loadTextFromClasspath("excel解析测试/解析后数据/CollectionGroup测试.json"));
        Assertions.assertTrue(DataCompareUtils.equalsJsonArray(sourceDataList, targetDataList));

        try (FileInputStream fis = new FileInputStream("src/test/resources/excel解析测试/CollectionGroup测试2.xlsx")) {
            workbook = createWorkbook(fis, "CollectionGroup测试2.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sourceDataList = JSON.parseArray(JSON.toJSONString(engine.parseExcel(workbook, config)));
        targetDataList = JSON.parseArray(FileUtils.loadTextFromClasspath("excel解析测试/解析后数据/CollectionGroup测试.json"));
        Assertions.assertTrue(DataCompareUtils.equalsJsonArray(sourceDataList, targetDataList));
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