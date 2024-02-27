package net.cf.excel.engine;

import net.cf.excel.engine.commons.ExcelMergeInfo;
import net.cf.excel.engine.commons.parse.DataParseInfo;
import net.cf.excel.engine.commons.parse.ExcelSheetField;
import net.cf.excel.engine.commons.parse.ExcelTitleInfo;
import net.cf.excel.engine.config.ExcelBuildConfig;
import net.cf.excel.engine.config.ExcelParseConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.*;
import java.util.stream.Collectors;

public class ExcelEngineImpl implements ExcelEngine {

    /**
     * 数据最大列数200
     */
    private static final int MAX_COL = 200;

    /**
     * 数据最大行数10000
     */
    private static final int MAX_ROW = 10000;

    /**
     * 解析Excel表格数据
     *
     * @param workbook 工作簿对象
     * @param config   解析配置对象
     * @return 解析结果列表
     */
    @Override
    public List<Map<String, Object>> parseExcel(Workbook workbook, ExcelParseConfig config) {
        // 初始化
        List<Map<String, Object>> records;
        List<ParseField> parseFields = config.getParseFields();
        List<ExcelSheetField> excelSheetFields = buildExcelSheetField(parseFields);
        int titleStartRow = config.getTitleStartRow();
        int titleEndRow = config.getTitleEndRow();
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(0);

        // 解析表头信息
        List<ExcelTitleInfo> titleInfos = parseSheetTitleInfo(sheet, titleStartRow, titleEndRow, excelSheetFields);

        //数据处理
        int dataStartRow = titleEndRow + 1;
        int dataEndRow = sheet.getLastRowNum();
        validateSheet(sheet, titleEndRow, dataEndRow);

        records = parseSheetData(new DataParseInfo(sheet, formulaEvaluator, titleInfos, excelSheetFields, dataStartRow, dataEndRow));

        return records;
    }

    private List<ExcelSheetField> buildExcelSheetField(List<ParseField> parseFields) {
        List<ExcelSheetField> excelSheetFields = new ArrayList<>();
        for (ParseField parseField : parseFields) {
            if (parseField instanceof SingleParseField) {
                excelSheetFields.add(new ExcelSheetField((SingleParseField) parseField, null));
            } else if (parseField instanceof ParseFieldGroup) {
                if (((ParseFieldGroup) parseField).isCollection()) {
                    List<ExcelSheetField> subFields = new ArrayList<>();
                    ExcelSheetField groupSheetField = new ExcelSheetField((ParseFieldGroup) parseField, subFields);
                    for (SingleParseField singleParseField : ((ParseFieldGroup) parseField).getSubField()) {
                        subFields.add(new ExcelSheetField(singleParseField, groupSheetField));
                    }
                    excelSheetFields.addAll(subFields);
                    excelSheetFields.add(groupSheetField);
                } else {
                    // 对仅展示型Group下的Field进行打平处理,不build该Group对应的ExcelSheetField
                    for (SingleParseField singleParseField : ((ParseFieldGroup) parseField).getSubField()) {
                        ExcelSheetField excelSheetField = new ExcelSheetField(singleParseField, null);
                        excelSheetFields.add(excelSheetField);
                    }
                }
            }
        }
        return excelSheetFields;
    }


    private void validateSheet(Sheet sheet, int titleEndRow, int dataEndRow) {
        int num = sheet.getRow(titleEndRow).getPhysicalNumberOfCells();
        if (num > MAX_COL) {
            throw new RuntimeException();
        }
        if (dataEndRow - titleEndRow > MAX_ROW) {
            throw new RuntimeException();
        }
    }

    /**
     * 获取Sheet表头信息
     *
     * @param sheet
     * @param titleStartRow
     * @param excelSheetFields
     * @return
     */
    private List<ExcelTitleInfo> parseSheetTitleInfo(Sheet sheet, int titleStartRow, int titleEndRow, List<ExcelSheetField> excelSheetFields) {
        List<ExcelTitleInfo> titleInfoList = new ArrayList<>();
        //key为合并单元格开始的列，值为表头中以该列为起始列的所有合并单元格
        Map<Integer, List<ExcelMergeInfo>> titleMergeMap = getTitleMergeMap(sheet, titleStartRow, titleEndRow);

        int colIndex = 0;
        int colEndIndex = sheet.getRow(titleStartRow).getLastCellNum();

        //得到所有sheetField名称,用来判断当前列是否需要被解析
        List<String> sheetFieldNames = excelSheetFields.stream().map(ExcelSheetField::getUniqueName).collect(Collectors.toList());

        if (titleStartRow == titleEndRow) {
            while (colIndex < colEndIndex) {
                //只有一级表头
                String title = getCellTitle(sheet, titleStartRow, colIndex);
                ExcelTitleInfo titleInfo = new ExcelTitleInfo(null, title, colIndex, false, false);
                addExcelTitleInfo(titleInfo, sheetFieldNames, titleInfoList);
                colIndex++;
            }
        } else {
            while (colIndex < colEndIndex) {
                //只有一级表头:列合并单元格完全顶住上下表头区域
                if (titleMergeMap.containsKey(colIndex) && titleMergeMap.get(colIndex).size() == 1
                        && titleMergeMap.get(colIndex).get(0).getRowStartIndex() == titleStartRow
                        && titleMergeMap.get(colIndex).get(0).getRowEndIndex() == titleEndRow) {
                    String title = getCellTitle(sheet, titleStartRow, colIndex);
                    ExcelTitleInfo titleInfo = new ExcelTitleInfo(null, title, colIndex, true, false);
                    addExcelTitleInfo(titleInfo, sheetFieldNames, titleInfoList);
                    colIndex++;
                } else {
                    //二级表头的情况
                    if (!isValidTitleGroup(titleMergeMap, sheet, titleStartRow, titleEndRow, colIndex)) {
                        throw new RuntimeException();
                    }
                    String parentTitle = getCellTitle(sheet, titleStartRow, colIndex);
                    //表头组作为list存储的key时,添加到titleInfoList中
                    boolean isCollection = false;
                    if (sheetFieldNames.contains(parentTitle)) {
                        isCollection = true;
                        ExcelTitleInfo titleInfo = new ExcelTitleInfo(null, parentTitle, colIndex, false, true);
                        addExcelTitleInfo(titleInfo, sheetFieldNames, titleInfoList);
                    }

                    ExcelMergeInfo columnMergeInfo = getColumnMergeInfo(titleMergeMap.get(colIndex));
                    int lastColumn = columnMergeInfo == null ? colIndex : columnMergeInfo.getColumnEndIndex();
                    int detailColIndex = colIndex;
                    while (detailColIndex <= lastColumn) {
                        String title = getCellTitle(sheet, titleEndRow, detailColIndex);
                        ExcelTitleInfo titleInfo = isCollection ? new ExcelTitleInfo(parentTitle, title, detailColIndex, false, false)
                                : new ExcelTitleInfo(null, title, detailColIndex, false, false);
                        addExcelTitleInfo(titleInfo, sheetFieldNames, titleInfoList);
                        detailColIndex++;
                    }
                    colIndex = lastColumn + 1;
                }
            }
        }
        return titleInfoList;
    }

    private void addExcelTitleInfo(ExcelTitleInfo excelTitleInfo, List<String> sheetFieldNames, List<ExcelTitleInfo> titleInfoList) {
        if (sheetFieldNames.contains(excelTitleInfo.getUniqueTitle())) {
            titleInfoList.add(excelTitleInfo);
        }
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
        if (cell == null) {
            return null;
        }
        return cell.getStringCellValue();
    }

    /**
     * 计算获取标题的合并单元格信息,key为合并单元格开始的列,值为表头中以该列为起始列的所有合并单元格
     *
     * @param sheet
     * @param titleStartRow
     * @return
     */
    private Map<Integer, List<ExcelMergeInfo>> getTitleMergeMap(Sheet sheet, int titleStartRow, int titleEndRow) {
        Map<Integer, List<ExcelMergeInfo>> titleMergeMap = new HashMap<>(1);
        for (CellRangeAddress cellAddress : sheet.getMergedRegions()) {
            int firstRow = cellAddress.getFirstRow();
            int lastRow = cellAddress.getLastRow();
            int firstColumn = cellAddress.getFirstColumn();
            int lastColumn = cellAddress.getLastColumn();
            // 跳过不在表头区域内的合并单元格
            if (firstRow < titleStartRow || lastRow > titleEndRow) {
                continue;
            }
            // 跳过非合并单元格
            if (firstRow == lastRow && firstColumn == lastColumn) {
                continue;
            }

            List<ExcelMergeInfo> titleMergeList;
            if (titleMergeMap.containsKey(firstColumn)) {
                titleMergeList = titleMergeMap.get(firstColumn);
                titleMergeList.add(new ExcelMergeInfo(cellAddress));
            } else {
                titleMergeList = new ArrayList<>();
                titleMergeList.add(new ExcelMergeInfo(cellAddress));
                titleMergeMap.put(firstColumn, titleMergeList);
            }
        }
        return titleMergeMap;
    }

    private boolean isValidTitleGroup(Map<Integer, List<ExcelMergeInfo>> titleMergeMap, Sheet sheet,
                                      int titleStartRow, int titleEndRow, int colIndex) {
        // 表头每一列的行合并单元格不能超过1(即最多只有二级表头)
        int count = 0;
        List<ExcelMergeInfo> excelMergeInfoList = titleMergeMap.get(colIndex);
        if (excelMergeInfoList == null) {
            return true;
        }
        for (ExcelMergeInfo excelMergeInfo : excelMergeInfoList) {
            if (excelMergeInfo.isRowMerge()) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        // 表头区域每一列最多只有两个不同的标题
        Set<String> titleSet = new HashSet<>();
        for (int i = titleStartRow; i <= titleEndRow; i++) {
            titleSet.add(getCellTitle(sheet, i, colIndex));
        }
        return titleSet.size() <= 2;
    }

    private ExcelMergeInfo getColumnMergeInfo(List<ExcelMergeInfo> excelMergeInfoList) {
        for (ExcelMergeInfo excelMergeInfo : excelMergeInfoList) {
            if (excelMergeInfo.isColumnMerge()) {
                return excelMergeInfo;
            }
        }
        return null;
    }

    private List<Map<String, Object>> parseSheetData(DataParseInfo dataParseInfo) {
        List<Map<String, Object>> records = new ArrayList<>();
        // 获取数据开始行与合并单元格的映射关系
        Map<Integer, ExcelMergeInfo> dataMergeMap = getDataMergeMap(dataParseInfo.getSheet(), dataParseInfo.getDataStartRow());
        // 获取字段标题与字段的映射关系
        Map<String, ExcelSheetField> fieldMap = dataParseInfo.getSheetFieldList().stream()
                .collect(Collectors.toMap(ExcelSheetField::getUniqueName, sheetField -> sheetField));
        // 获取主表头信息
        List<ExcelTitleInfo> masterTitleInfoList = dataParseInfo.getTitleInfoList().stream()
                .filter(excelTitleInfo -> !excelTitleInfo.isSubTitle()).collect(Collectors.toList());
        // 获取二级表头信息(主表头标题与二级表头的映射关系)
        Map<String, List<ExcelTitleInfo>> subTitleInfoMap = dataParseInfo.getTitleInfoList().stream()
                .filter(ExcelTitleInfo::isSubTitle)
                .collect(Collectors.groupingBy(ExcelTitleInfo::getParentTitle));

        // 数据行遍历器
        int dataRowIndex = dataParseInfo.getDataStartRow();
        while (dataRowIndex <= dataParseInfo.getDataEndRow()) {
            int dataRowEndIndex = dataRowIndex;
            // 如果数据属于列合并单元格(即一个数据字段占了多行的情况)
            if (dataMergeMap.containsKey(dataRowIndex)) {
                ExcelMergeInfo dataMergeInfo = dataMergeMap.get(dataRowIndex);
                dataRowEndIndex = dataMergeInfo.getRowEndIndex();
            }
            // 获取一行数据
            List<Map<Integer, String>> rowData = getRowData(dataParseInfo.getFormulaEvaluator(), dataParseInfo.getSheet(),
                    dataRowIndex, dataRowEndIndex, dataParseInfo.getTitleInfoList());
            if (isEmptyRow(rowData)) {
                dataRowIndex = dataRowEndIndex + 1;
                continue;
            }

            Map<String, Object> record = new HashMap<>(rowData.size());
            for (ExcelTitleInfo titleInfo : masterTitleInfoList) {
                if (fieldMap.containsKey(titleInfo.getUniqueTitle())) {
                    ExcelSheetField sheetField = fieldMap.get(titleInfo.getUniqueTitle());
                    // 如果需要进行数据聚合
                    if (fieldMap.get(titleInfo.getUniqueTitle()).isHasSubField()) {
                        List<ExcelTitleInfo> subTitleInfo = subTitleInfoMap.getOrDefault(titleInfo.getUniqueTitle(), new ArrayList<>());
                        List<Map<String, Object>> subDataList = collectSubData(subTitleInfo, rowData, fieldMap);
                        record.put(sheetField.getCode(), sheetField.getDataFormatter().unFormat(subDataList));
                    } else {
                        Object cellValue = rowData.get(0).get(titleInfo.getFirstColumn());
                        record.put(sheetField.getCode(), sheetField.getDataFormatter().unFormat(cellValue));
                    }
                }
            }
            dataRowIndex = dataRowEndIndex + 1;
            records.add(record);
        }
        return records;
    }

    private boolean isEmptyRow(List<Map<Integer, String>> rowData) {
        for (Map<Integer, String> rowValue : rowData) {
            for (String value : rowValue.values()) {
                if (StringUtils.isNotBlank(value)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取一行data数据,如果该行是合并单元格,则遍历单元格下的所有行
     *
     * @param formulaEvaluator
     * @param sheet
     * @param rowStartIndex
     * @param rowEndIndex
     * @param titleInfoList
     * @return
     */
    private List<Map<Integer, String>> getRowData(FormulaEvaluator formulaEvaluator, Sheet sheet, int rowStartIndex,
                                                  int rowEndIndex, List<ExcelTitleInfo> titleInfoList) {
        List<Map<Integer, String>> rowRecords = new ArrayList<>();
        int rowIndex = rowStartIndex;
        while (rowIndex <= rowEndIndex) {
            Map<Integer, String> rowRecord = new HashMap<>();
            Row row = sheet.getRow(rowIndex);
            if (Objects.nonNull(row)) {
                titleInfoList.forEach(titleInfo -> rowRecord.put(titleInfo.getFirstColumn(),
                        getCellValue(row.getCell(titleInfo.getFirstColumn()), formulaEvaluator)));
                rowRecords.add(rowRecord);
            }
            rowIndex++;
        }
        return rowRecords;
    }

    private String getCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (Objects.isNull(cell)) {
            return null;
        }
        DataFormatter df = new DataFormatter();
        return df.formatCellValue(cell, formulaEvaluator);
    }

    /**
     * 获取数据区域的合并单元格信息
     *
     * @param sheet        表格sheet
     * @param dataStartRow 数据开始行
     * @return Map<Integer, ExcelMergeInfo>
     */
    private Map<Integer, ExcelMergeInfo> getDataMergeMap(Sheet sheet, int dataStartRow) {
        Map<Integer, ExcelMergeInfo> dataMergeMap = new HashMap<>();
        for (CellRangeAddress cellAddress : sheet.getMergedRegions()) {
            int firstRow = cellAddress.getFirstRow();
            if (firstRow >= dataStartRow) {
                dataMergeMap.put(firstRow, new ExcelMergeInfo(cellAddress));
            }
        }
        return dataMergeMap;
    }

    private List<Map<String, Object>> collectSubData(List<ExcelTitleInfo> subTitleInfoList,
                                                     List<Map<Integer, String>> rowValues,
                                                     Map<String, ExcelSheetField> fieldMap) {
        // 聚合数据列记录 key为聚合字段的code, value为聚合数据
        List<Map<String, Object>> subRecords = new ArrayList<>();
        // 聚合数据行 key为列坐标, value为单个cell的数据值
        List<Map<Integer, String>> subRowValueList = new ArrayList<>();

        // 从一组数据中过滤出需要进行聚合的数据行
        for (Map<Integer, String> rowValue : rowValues) {
            Map<Integer, String> subRowValue = new HashMap<>();
            subTitleInfoList.forEach(subTitleInfo -> {
                if (rowValue.containsKey(subTitleInfo.getFirstColumn())) {
                    subRowValue.put(subTitleInfo.getFirstColumn(), rowValue.get(subTitleInfo.getFirstColumn()));
                }
            });
            subRowValueList.add(subRowValue);
        }

        // 数据结构转化
        subRowValueList.forEach(subRowValue -> {
            Map<String, Object> subRecord = new HashMap<>();
            subTitleInfoList.forEach(subTitleInfo -> {
                if (fieldMap.containsKey(subTitleInfo.getUniqueTitle())) {
                    String cellValue = subRowValue.get(subTitleInfo.getFirstColumn());
                    ExcelSheetField field = fieldMap.get(subTitleInfo.getUniqueTitle());
                    subRecord.put(field.getCode(), field.getDataFormatter().unFormat(cellValue));
                }
            });
            subRecords.add(subRecord);
        });
        return subRecords;
    }

    @Override
    public Workbook buildExcel(List<Map<String, Object>> data, ExcelBuildConfig config) {
        return null;
    }
}
