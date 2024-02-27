package net.cf.excel.engine;

import net.cf.excel.engine.commons.CollectionGroup;
import net.cf.excel.engine.commons.TextField;
import net.cf.excel.engine.config.ExcelParseConfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelEngineImplTest {

    // 单个字段解析
    @Test
    public void testParseExcel_TextField() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook = null;
        try (FileInputStream fis = new FileInputStream("src/test/resources/TextField测试.xlsx")) {
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

        List<Map<String, Object>> dataList = engine.parseExcel(workbook, config);
        System.out.println(dataList);
    }

    // 主子关系字段解析
    @Test
    public void testParseExcel_CollectionGroup1() {
        ExcelEngine engine = new ExcelEngineImpl();
        Workbook workbook = null;
        try (FileInputStream fis = new FileInputStream("src/test/resources/CollectionGroup测试1.xlsx")) {
            workbook = createWorkbook(fis, "CollectionGroup测试1.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
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

        List<Map<String, Object>> dataList1 = engine.parseExcel(workbook, config);

        try (FileInputStream fis = new FileInputStream("src/test/resources/CollectionGroup测试2.xlsx")) {
            workbook = createWorkbook(fis, "CollectionGroup测试2.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> dataList2 = engine.parseExcel(workbook, config);

        System.out.println(dataList2);
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