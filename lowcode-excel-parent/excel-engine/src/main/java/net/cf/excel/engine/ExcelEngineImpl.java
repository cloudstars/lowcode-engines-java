package net.cf.excel.engine;

import net.cf.excel.engine.commons.ExcelMergeInfo;
import net.cf.excel.engine.commons.parse.ExcelSheetField;
import net.cf.excel.engine.config.ExcelBuildConfig;
import net.cf.excel.engine.config.ExcelParseConfig;
import net.cf.excel.engine.commons.ExcelTitleInfo;
import net.cf.excel.engine.converter.ParseConverter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelEngineImpl implements ExcelEngine {

    /**
     * 数据最大列数200
     */
    private static final int MAX_COL = 200;

    /**
     * 标题的起始行
     */
    private static final int TITLE_START_ROW = 0;

    /**
     * 解析excel文件
     * @param workbook
     * @param config
     * @return
     */
    @Override
    public List<Map<String, Object>> parseExcel(Workbook workbook, ExcelParseConfig config) {
        //初始化
        List<Map<String, Object>> records;
        List<ExcelSheetField> sheetFields = config.getSheetFields();
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(0);
        validateSheet(sheet);

        //标题处理
        boolean hasSubField = hasSubField(sheetFields);
        int titleEndRow = hasSubField ? TITLE_START_ROW + 1 : TITLE_START_ROW;
        List<ExcelTitleInfo> titleInfoList = getSheetTitleInfo(sheet, TITLE_START_ROW, titleEndRow, sheetFields, hasSubField);

        //数据处理
        int dataStartRow = titleEndRow + 1;
        int dataEndRow = sheet.getLastRowNum();
        records = getExcelData();

        return records;
    }

    /**
     * 校验Sheet数据大小
     *
     * @param sheet
     */
    private void validateSheet(Sheet sheet) {
        int num = sheet.getRow(0).getPhysicalNumberOfCells();
        if (num > MAX_COL) {
            throw new RuntimeException();
        }
    }

    private boolean hasSubField(List<ExcelSheetField> sheetFields) {
        for (ExcelSheetField sheetField : sheetFields) {
            if (sheetField.hasSubField()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取Sheet表头信息
     *
     * @param sheet
     * @param titleStartRow
     * @param titleEndRow
     * @param ExcelSheetFields
     * @param hasSubField
     * @return
     */
    private static List<ExcelTitleInfo> getSheetTitleInfo(Sheet sheet, int titleStartRow, int titleEndRow, List<ExcelSheetField> ExcelSheetFields,
                                                          boolean hasSubField) {
        List<ExcelTitleInfo> titleInfoList = new ArrayList<>();
        Map<Integer, ExcelMergeInfo> titleMergeMap = getTitleMergeMap(sheet, titleStartRow, titleEndRow, hasSubField);

        int colIndex = 0;
        int colEndIndex = sheet.getRow(titleStartRow).getLastCellNum();
        while (colIndex < colEndIndex) {
            //如果列不属于合并单元格
            if (!titleMergeMap.containsKey(colIndex)) {
                String title = getTitleCell(sheet, titleStartRow, colIndex);
                titleInfoList.add(new ExcelTitleInfo(null, title, colIndex, false, false));
                colIndex++;
            } else {
                //得到所有IExcelSheetField名称,用来判断当前列是否需要被解析
                List<String> sheetFieldNames = ExcelSheetFields.stream().map(ExcelSheetField::getUniqueName).collect(Collectors.toList());
                ExcelMergeInfo titleMergeInfo = titleMergeMap.get(colIndex);
                //仅考虑列合并或者行合并的场景
                if (titleMergeInfo.isRowMerge() && !titleMergeInfo.isColumnMerge()) {
                    String title = getTitleCell(sheet, titleStartRow, colIndex);
                    ExcelTitleInfo titleInfo = new ExcelTitleInfo(null, title, colIndex, true, false);
                    if (existIExcelSheetField(titleInfo, sheetFieldNames)) {
                        titleInfoList.add(titleInfo);
                    }
                } else if (!titleMergeInfo.isRowMerge() && titleMergeInfo.isColumnMerge()) {
                    String parentTitle = getTitleCell(sheet, titleStartRow, colIndex);
                    if (sheetFieldNames.contains(parentTitle)) {
                        ExcelTitleInfo titleInfo = new ExcelTitleInfo(null, parentTitle, colIndex, false, true);
                        if (existIExcelSheetField(titleInfo, sheetFieldNames)) {
                            titleInfoList.add(titleInfo);
                        }
                    }
                    int lastColumn = titleMergeInfo.getColumnEndIndex();
                    int detailColIndex = colIndex;
                    while (detailColIndex <= lastColumn) {
                        String title = getTitleCell(sheet, titleEndRow, detailColIndex);
                        ExcelTitleInfo titleInfo = new ExcelTitleInfo(parentTitle, title, detailColIndex, false, false);
                        if (existIExcelSheetField(titleInfo, sheetFieldNames)) {
                            titleInfoList.add(titleInfo);
                        }
                        detailColIndex++;
                    }
                }
                colIndex = titleMergeInfo.getColumnEndIndex() + 1;
            }
        }
        return titleInfoList;
    }

    /**
     * 获取sheet表头标题
     *
     * @param sheet
     * @param rowIndex
     * @param colIndex
     * @return
     */
    private static String getTitleCell(Sheet sheet, int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(colIndex);
        if (cell == null) return null;
        return cell.getStringCellValue();
    }

    /**
     * 计算获取标题的合并单元格信息
     *
     * @param sheet
     * @param titleStartRow
     * @param titleEndRow
     * @param hasSubField
     * @return
     */
    private static Map<Integer, ExcelMergeInfo> getTitleMergeMap(Sheet sheet, int titleStartRow, int titleEndRow, boolean hasSubField) {
        //key为合并单元格开始的列，值为合并区域信息
        Map<Integer, ExcelMergeInfo> titleMergeMap = new HashMap<>(1);
        for (CellRangeAddress cellAddress : sheet.getMergedRegions()) {
            int firstRow = cellAddress.getFirstRow();
            int lastRow = cellAddress.getLastRow();
            int firstColumn = cellAddress.getFirstColumn();
            int lastColumn = cellAddress.getLastColumn();
            if (firstRow < titleStartRow || firstRow > titleEndRow) {
                continue;
            }
            if (firstRow == lastRow && firstColumn == lastColumn) {
                continue;
            }
            if (hasSubField) {
                if (lastRow > firstRow + 1) {
                    throw new RuntimeException();
                }
                if (firstRow == titleEndRow) {
                    throw new RuntimeException();
                }
            } else {
                if (lastRow > firstRow) {
                    throw new RuntimeException();
                }
            }
            titleMergeMap.put(firstColumn, new ExcelMergeInfo(cellAddress));
        }
        return titleMergeMap;
    }

    /**
     * 判断当前列的title是否
     *
     * @param excelTitleInfo
     * @param sheetFieldNames
     * @return
     */
    private static boolean existIExcelSheetField(ExcelTitleInfo excelTitleInfo, List<String> sheetFieldNames) {
        return sheetFieldNames.contains(excelTitleInfo.getUniqueTitle());
    }

    @Override
    public Workbook buildExcel(List<Map<String, Object>> data, ExcelBuildConfig config) {
        return null;
    }
}
