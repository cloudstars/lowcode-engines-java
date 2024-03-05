package net.cf.excel.engine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.commons.test.util.FileTestUtils;
import net.cf.excel.engine.bean.ExcelBuildConfig;
import net.cf.excel.engine.bean.ExcelParseConfig;
import net.cf.excel.engine.commons.CollectionGroup;
import net.cf.excel.engine.commons.NumberTitle;
import net.cf.excel.engine.commons.ShowGroup;
import net.cf.excel.engine.commons.TextTitle;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-28 17:25
 * @Description: excel生成测试
 */
public class ExcelBuildTest {
    @Test
    public void testBuildExcel_TextField() {
        ExcelEngine engine = new ExcelEngineImpl();

        JSONArray jsonArray = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel生成测试/原始数据/TextField测试.json"));
        List<Map<String, Object>> sourceDataList = new ArrayList<>();
        for (Object jsonObject : jsonArray) {
            sourceDataList.add((Map<String, Object>) jsonObject);
        }

        ExcelBuildConfig config = new ExcelBuildConfig();
        List<ExcelTitle> excelTitles = new ArrayList<>();
        excelTitles.add(new TextTitle("peopleName", "姓名", false));
        excelTitles.add(new TextTitle("gender", "性别", false));
        config.setExcelTitles(excelTitles);
        config.setTitleStartRow(0);
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/TextField测试.xlsx");

        List<Map<String, Object>> targetDataList = engine.parseExcel(sourceWorkbook, new ExcelParseConfig(excelTitles, 0, 0));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(jsonArray, JSON.parseArray(JSON.toJSONString(targetDataList))));
    }

    @Test
    public void testBuildExcel_CollectionGroup1() {
        ExcelEngine engine = new ExcelEngineImpl();
        JSONArray jsonArray = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel生成测试/原始数据/CollectionGroup测试.json"));
        List<Map<String, Object>> sourceDataList = new ArrayList<>();
        for (Object jsonObject : jsonArray) {
            sourceDataList.add((Map<String, Object>) jsonObject);
        }

        ExcelBuildConfig config = new ExcelBuildConfig();
        List<ExcelTitle> excelTitles = new ArrayList<>();

        excelTitles.add(new TextTitle("peopleName", "姓名"));
        excelTitles.add(new TextTitle("gender", "性别"));
        List<SingleExcelTitle> subFields = new ArrayList<>();
        subFields.add(new TextTitle("signInTime", "签到时间"));
        subFields.add(new TextTitle("signOutTime", "签退时间"));
        ExcelTitleGroup parseFieldGroup = new CollectionGroup("attendance", "考勤记录", subFields);
        excelTitles.add(parseFieldGroup);
        config.setTitleStartRow(0);

        config.setExcelTitles(excelTitles);
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/CollectionGroup测试.xlsx");

        List<Map<String, Object>> targetDataList = engine.parseExcel(sourceWorkbook, new ExcelParseConfig(excelTitles, 0, 1));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(jsonArray, JSON.parseArray(JSON.toJSONString(targetDataList))));
    }

    @Test
    public void testBuildExcel_ShowGroup1() {
        ExcelEngine engine = new ExcelEngineImpl();
        JSONArray jsonArray = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel生成测试/原始数据/ShowGroup测试.json"));
        List<Map<String, Object>> sourceDataList = new ArrayList<>();
        for (Object jsonObject : jsonArray) {
            sourceDataList.add((Map<String, Object>) jsonObject);
        }

        ExcelBuildConfig config = new ExcelBuildConfig();
        List<ExcelTitle> excelTitles = new ArrayList<>();

        excelTitles.add(new TextTitle("peopleName", "姓名"));
        List<SingleExcelTitle> subFields = new ArrayList<>();
        subFields.add(new TextTitle("gender", "性别"));
        subFields.add(new NumberTitle("age", "年龄"));
        ExcelTitleGroup parseFieldGroup = new ShowGroup("message", "个人信息", subFields);
        excelTitles.add(parseFieldGroup);
        config.setTitleStartRow(0);

        config.setExcelTitles(excelTitles);
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/ShowGroup测试.xlsx");

        List<Map<String, Object>> targetDataList = engine.parseExcel(sourceWorkbook, new ExcelParseConfig(excelTitles, 0, 1));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(jsonArray, JSON.parseArray(JSON.toJSONString(targetDataList))));
    }

    @Test
    public void testBuildExcel_1() {
        ExcelEngine engine = new ExcelEngineImpl();
        JSONArray jsonArray = JSON.parseArray(FileTestUtils.loadTextFromClasspath("excel生成测试/原始数据/联合测试1.json"));
        List<Map<String, Object>> sourceDataList = new ArrayList<>();
        for (Object jsonObject : jsonArray) {
            sourceDataList.add((Map<String, Object>) jsonObject);
        }

        ExcelBuildConfig config = new ExcelBuildConfig();
        List<ExcelTitle> excelTitles = new ArrayList<>();

        excelTitles.add(new TextTitle("peopleName", "姓名"));
        List<SingleExcelTitle> subFields = new ArrayList<>();
        subFields.add(new TextTitle("gender", "性别"));
        subFields.add(new NumberTitle("age", "年龄"));

        ExcelTitleGroup showGroup = new ShowGroup("message", "个人信息", subFields);
        excelTitles.add(showGroup);

        subFields = new ArrayList<>();
        subFields.add(new TextTitle("signInTime", "签到时间"));
        subFields.add(new TextTitle("signOutTime", "签退时间"));
        ExcelTitleGroup collectionGroup = new CollectionGroup("attendance", "考勤记录", subFields);
        excelTitles.add(collectionGroup);
        config.setTitleStartRow(0);

        config.setExcelTitles(excelTitles);
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/联合测试1.xlsx");

        List<Map<String, Object>> targetDataList = engine.parseExcel(sourceWorkbook, new ExcelParseConfig(excelTitles, 0, 1));
        Assertions.assertTrue(DataCompareTestUtils.equalsJsonArray(jsonArray, JSON.parseArray(JSON.toJSONString(targetDataList))));
    }

    private void export(Workbook workbook, String path) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FileOutputStream fos = new FileOutputStream(path);
            workbook.write(byteArrayOutputStream);
            fos.write(byteArrayOutputStream.toByteArray());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}