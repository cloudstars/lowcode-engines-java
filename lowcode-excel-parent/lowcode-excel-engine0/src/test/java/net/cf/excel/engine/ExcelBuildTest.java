package net.cf.excel.engine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.github.cloudstars.lowcode.commons.test.util.DataCompareTestUtils;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import net.cf.excel.engine.bean.ExcelBuildConfig;
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

        ExcelBuildConfig config = new ExcelOpConfigLoaderImpl().loadExcelBuildConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/textTitle.json")));
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/TextField测试.xlsx");

        List<Map<String, Object>> targetDataList = engine.parseExcel(sourceWorkbook, new ExcelOpConfigLoaderImpl().loadExcelParseConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/textTitle.json"))));
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

        ExcelBuildConfig config = new ExcelOpConfigLoaderImpl().loadExcelBuildConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/collectionGroup.json")));
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/CollectionGroup测试.xlsx");

        List<Map<String, Object>> targetDataList = engine.parseExcel(sourceWorkbook, new ExcelOpConfigLoaderImpl().loadExcelParseConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/collectionGroup.json"))));
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

        ExcelBuildConfig config = new ExcelOpConfigLoaderImpl().loadExcelBuildConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/showGroup.json")));
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/ShowGroup测试.xlsx");

        List<Map<String, Object>> targetDataList = engine.parseExcel(sourceWorkbook, new ExcelOpConfigLoaderImpl().loadExcelParseConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/showGroup.json"))));
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

        ExcelBuildConfig config = new ExcelOpConfigLoaderImpl().loadExcelBuildConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/联合测试.json")));
        Workbook sourceWorkbook = engine.buildExcel(sourceDataList, config);
        export(sourceWorkbook, "src/test/resources/excel生成测试/生成的excel文件/联合测试1.xlsx");

        List<Map<String, Object>> targetDataList = engine.parseExcel(sourceWorkbook, new ExcelOpConfigLoaderImpl().loadExcelParseConfig(
                JSON.parseObject(FileTestUtils.loadTextFromClasspath("excelParseConfig/联合测试.json"))));
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