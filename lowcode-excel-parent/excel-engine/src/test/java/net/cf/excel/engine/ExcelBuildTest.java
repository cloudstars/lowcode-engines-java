package net.cf.excel.engine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.cf.commons.test.util.FileTestUtils;
import net.cf.excel.engine.commons.CollectionGroup;
import net.cf.excel.engine.commons.NumberField;
import net.cf.excel.engine.commons.ShowGroup;
import net.cf.excel.engine.commons.TextField;
import net.cf.excel.engine.config.ExcelBuildConfig;
import org.apache.poi.ss.usermodel.Workbook;
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
        List<ExcelField> excelFields = new ArrayList<>();
        excelFields.add(new TextField("peopleName", "姓名"));
        excelFields.add(new TextField("gender", "性别"));
        config.setParseFields(excelFields);
        config.setTitleStartRow(0);
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/TextField测试.xlsx");
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
        List<ExcelField> excelFields = new ArrayList<>();

        excelFields.add(new TextField("peopleName", "姓名"));
        excelFields.add(new TextField("gender", "性别"));
        List<SingleExcelField> subFields = new ArrayList<>();
        subFields.add(new TextField("signInTime", "签到时间"));
        subFields.add(new TextField("signOutTime", "签退时间"));
        ExcelFieldGroup parseFieldGroup = new CollectionGroup("attendance", "考勤记录", subFields);
        excelFields.add(parseFieldGroup);
        config.setTitleStartRow(0);

        config.setParseFields(excelFields);
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/CollectionGroup测试.xlsx");
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
        List<ExcelField> excelFields = new ArrayList<>();

        excelFields.add(new TextField("peopleName", "姓名"));
        List<SingleExcelField> subFields = new ArrayList<>();
        subFields.add(new TextField("gender", "性别"));
        subFields.add(new NumberField("age", "年龄"));
        ExcelFieldGroup parseFieldGroup = new ShowGroup("message", "个人信息", subFields);
        excelFields.add(parseFieldGroup);
        config.setTitleStartRow(0);

        config.setParseFields(excelFields);
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/ShowGroup测试.xlsx");
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
        config.setTitleStartRow(0);

        config.setParseFields(excelFields);
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/联合测试1.xlsx");
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