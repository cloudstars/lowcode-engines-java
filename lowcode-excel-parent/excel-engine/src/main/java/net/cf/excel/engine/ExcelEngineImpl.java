package net.cf.excel.engine;

import net.cf.excel.engine.commons.ExcelMergeInfo;
import net.cf.excel.engine.commons.parse.DataParseInfo;
import net.cf.excel.engine.commons.parse.ExcelSheetField;
import net.cf.excel.engine.commons.parse.ExcelTitleInfo;
import net.cf.excel.engine.config.ExcelBuildConfig;
import net.cf.excel.engine.config.ExcelParseConfig;
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
     * 解析excel文件
     *
     * @param workbook
     * @param config
     * @return
     */
    @Override
    public List<Map<String, Object>> parseExcel(Workbook workbook, ExcelParseConfig config) {
        //初始化
        List<Map<String, Object>> records;
        List<ParseField> parseFields = config.getParseFields();
        List<ExcelSheetField> excelSheetFields = buildExcelSheetField(parseFields);
        int titleStartRow = config.getTitleStartRow();
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(0);
        validateSheet(sheet);

        //标题处理
        boolean hasSubField = hasSubField(excelSheetFields);
        int titleEndRow = hasSubField ? config.getTitleStartRow() + 1 : config.getTitleStartRow();
        List<ExcelTitleInfo> titleInfos = parseSheetTitleInfo(sheet, titleStartRow, titleEndRow, excelSheetFields, hasSubField);

        //数据处理
        int dataStartRow = titleEndRow + 1;
        int dataEndRow = sheet.getLastRowNum();
        records = parseSheetData(new DataParseInfo(sheet, formulaEvaluator, titleInfos, excelSheetFields, dataStartRow, dataEndRow));

        return records;
    }

    private List<ExcelSheetField> buildExcelSheetField(List<ParseField> parseFields) {
        List<ExcelSheetField> excelSheetFields = new ArrayList<>();
        for (ParseField parseField : parseFields) {
            if (parseField instanceof SingleParseField) {
                excelSheetFields.add(new ExcelSheetField((SingleParseField) parseField, null));
            } else if (parseField instanceof GroupParseField) {
                List<ExcelSheetField> subFields = new ArrayList<>();
                ExcelSheetField groupSheetField = new ExcelSheetField((GroupParseField) parseField, subFields);
                for (SingleParseField singleParseField : ((GroupParseField) parseField).getSubField()) {
                    subFields.add(new ExcelSheetField(singleParseField, groupSheetField));
                }
                excelSheetFields.addAll(subFields);
                excelSheetFields.add(groupSheetField);
            }
        }
        return excelSheetFields;
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

    private boolean hasSubField(List<ExcelSheetField> excelSheetFields) {
        for (ExcelSheetField excelSheetField : excelSheetFields) {
            if (excelSheetField.isHasSubField()) {
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
     * @param excelSheetFields
     * @param hasSubField
     * @return
     */
    private List<ExcelTitleInfo> parseSheetTitleInfo(Sheet sheet, int titleStartRow, int titleEndRow, List<ExcelSheetField> excelSheetFields,
                                                     boolean hasSubField) {
        List<ExcelTitleInfo> titleInfoList = new ArrayList<>();
        Map<Integer, ExcelMergeInfo> titleMergeMap = getTitleMergeMap(sheet, titleStartRow, titleEndRow, hasSubField);

        int colIndex = 0;
        int colEndIndex = sheet.getRow(titleStartRow).getLastCellNum();
        while (colIndex < colEndIndex) {
            //如果列不属于合并单元格
            if (!titleMergeMap.containsKey(colIndex)) {
                String title = getCellTitle(sheet, titleStartRow, colIndex);
                titleInfoList.add(new ExcelTitleInfo(null, title, colIndex, false, false));
                colIndex++;
            } else {
                //得到所有sheetField名称,用来判断当前列是否需要被解析
                List<String> sheetFieldNames = excelSheetFields.stream().map(ExcelSheetField::getUniqueName).collect(Collectors.toList());
                ExcelMergeInfo titleMergeInfo = titleMergeMap.get(colIndex);
                //仅考虑列合并或者行合并的场景
                if (titleMergeInfo.isRowMerge() && !titleMergeInfo.isColumnMerge()) {
                    String title = getCellTitle(sheet, titleStartRow, colIndex);
                    ExcelTitleInfo titleInfo = new ExcelTitleInfo(null, title, colIndex, true, false);
                    if (existIExcelSheetField(titleInfo, sheetFieldNames)) {
                        titleInfoList.add(titleInfo);
                    }
                } else if (!titleMergeInfo.isRowMerge() && titleMergeInfo.isColumnMerge()) {
                    String parentTitle = getCellTitle(sheet, titleStartRow, colIndex);
                    if (sheetFieldNames.contains(parentTitle)) {
                        ExcelTitleInfo titleInfo = new ExcelTitleInfo(null, parentTitle, colIndex, false, true);
                        if (existIExcelSheetField(titleInfo, sheetFieldNames)) {
                            titleInfoList.add(titleInfo);
                        }
                    }
                    int lastColumn = titleMergeInfo.getColumnEndIndex();
                    int detailColIndex = colIndex;
                    while (detailColIndex <= lastColumn) {
                        String title = getCellTitle(sheet, titleEndRow, detailColIndex);
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
    private String getCellTitle(Sheet sheet, int rowIndex, int colIndex) {
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
    private Map<Integer, ExcelMergeInfo> getTitleMergeMap(Sheet sheet, int titleStartRow, int titleEndRow, boolean hasSubField) {
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
    private boolean existIExcelSheetField(ExcelTitleInfo excelTitleInfo, List<String> sheetFieldNames) {
        return sheetFieldNames.contains(excelTitleInfo.getUniqueTitle());
    }

    private List<Map<String, Object>> parseSheetData(DataParseInfo dataParseInfo) {
        return null;
    }

    @Override
    public Workbook buildExcel(List<Map<String, Object>> data, ExcelBuildConfig config) {
        return null;
    }
}
