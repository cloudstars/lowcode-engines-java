package net.cf.excel.engine;

import net.cf.excel.engine.bean.ExcelBuildConfig;
import net.cf.excel.engine.bean.ExcelParseConfig;
import net.cf.excel.engine.bean.ExcelTitleBean;
import net.cf.excel.engine.bean.ExcelTitleParseConfig;
import net.cf.excel.engine.commons.ExcelMergeInfo;
import net.cf.excel.engine.commons.ExcelOpException;
import net.cf.excel.engine.commons.build.SheetBuildInfo;
import net.cf.excel.engine.commons.parse.DataParseInfo;
import net.cf.excel.engine.commons.parse.ExcelSheetField;
import net.cf.excel.engine.commons.parse.SheetTitleInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        List<ExcelTitle> excelTitles = config.getExcelTitles();
        List<ExcelSheetField> excelSheetFields = buildExcelSheetField(excelTitles);
        int titleStartRow = config.getTitleStartRow();
        int titleEndRow = config.getTitleEndRow();
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(0);

        // 解析表头信息
        List<SheetTitleInfo> titleInfos = parseSheetTitleInfo(sheet, titleStartRow, titleEndRow, excelSheetFields);

        //数据处理
        int dataStartRow = titleEndRow + 1;
        int dataEndRow = sheet.getLastRowNum();
        validateSheet(sheet, titleEndRow, dataEndRow);

        records = parseSheetData(new DataParseInfo(sheet, formulaEvaluator, titleInfos, excelSheetFields, dataStartRow, dataEndRow));

        return records;
    }

    @Override
    public List<ExcelTitleBean> parseExcelTitles(Workbook workbook, ExcelTitleParseConfig config) {
        List<ExcelTitleBean> excelTitles = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);
        int titleStartRow = config.getTitleStartRow();
        int titleEndRow = config.getTitleEndRow();
        Map<Integer, List<ExcelMergeInfo>> titleMergeMap = getTitleMergeMap(sheet, titleStartRow, titleEndRow);

        int colIndex = sheet.getRow(titleStartRow).getFirstCellNum();
        int colEndIndex = sheet.getRow(titleStartRow).getLastCellNum();

        if (titleStartRow == titleEndRow) {
            while (colIndex < colEndIndex) {
                ExcelTitleBean excelTitle = new ExcelTitleBean();
                //只有一级表头
                String name = getCellTitle(sheet, titleStartRow, colIndex);
                excelTitle.setName(name);
                excelTitles.add(excelTitle);
                colIndex++;
            }
        } else {
            while (colIndex < colEndIndex) {
                ExcelTitleBean excelTitle = new ExcelTitleBean();
                //只有一级表头:列合并单元格完全顶住上下表头区域
                if (titleMergeMap.containsKey(colIndex) && titleMergeMap.get(colIndex).size() == 1
                        && titleMergeMap.get(colIndex).get(0).getRowStartIndex() == titleStartRow
                        && titleMergeMap.get(colIndex).get(0).getRowEndIndex() == titleEndRow) {
                    String name = getCellTitle(sheet, titleStartRow, colIndex);
                    excelTitle.setName(name);
                    excelTitles.add(excelTitle);
                    colIndex++;
                } else {
                    //二级表头的情况
                    if (!isValidTitleGroup(titleMergeMap, sheet, titleStartRow, titleEndRow, colIndex)) {
                        throw new ExcelOpException("不是一个合法的表头");
                    }
                    String parentName = getCellTitle(sheet, titleStartRow, colIndex);
                    excelTitle.setName(parentName);
                    List<ExcelTitleBean> subTitles = new ArrayList<>();
                    excelTitle.setSubTitles(subTitles);

                    ExcelMergeInfo columnMergeInfo = getColumnMergeInfo(titleMergeMap.get(colIndex));
                    int lastColumn = columnMergeInfo == null ? colIndex : columnMergeInfo.getColumnEndIndex();
                    int detailColIndex = colIndex;
                    while (detailColIndex <= lastColumn) {
                        ExcelTitleBean subTitle = new ExcelTitleBean();
                        String subTitleName = getCellTitle(sheet, titleEndRow, detailColIndex);
                        subTitle.setName(subTitleName);
                        subTitles.add(subTitle);
                        detailColIndex++;
                    }
                    excelTitles.add(excelTitle);
                    colIndex = lastColumn + 1;
                }
            }
        }
        return excelTitles;
    }

    @Override
    public Workbook buildExcel(List<Map<String, Object>> data, ExcelBuildConfig config) {
        //初始化
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(StringUtils.isBlank(config.getSheetName()) ? "Sheet1" : config.getSheetName());
        XSSFCellStyle style = setCellStyle(workbook);
        int titleStartRow = config.getTitleStartRow();

        //生成表头,返回数据结束列
        int titleEndCol = buildSheetTitle(new SheetBuildInfo(sheet, 0, titleStartRow, style, config.getExcelTitles()));

        //生成数据
        int dataStartRow = config.getExcelTitles().stream().anyMatch(excelTitle -> excelTitle instanceof ExcelTitleGroup) ?
                titleStartRow + 2 : titleStartRow + 1;
        buildSheetData(new SheetBuildInfo(sheet, 0, dataStartRow, style, config.getExcelTitles()), data);

        autoColumnWidthForChineseChars(sheet, titleEndCol);
        return workbook;
    }

    private List<ExcelSheetField> buildExcelSheetField(List<ExcelTitle> excelTitles) {
        List<ExcelSheetField> excelSheetFields = new ArrayList<>();
        for (ExcelTitle excelTitle : excelTitles) {
            if (excelTitle instanceof SingleExcelTitle) {
                excelSheetFields.add(new ExcelSheetField((SingleExcelTitle) excelTitle, null));
            } else if (excelTitle instanceof ExcelTitleGroup) {
                if (((ExcelTitleGroup) excelTitle).isCollection()) {
                    List<ExcelSheetField> subFields = new ArrayList<>();
                    ExcelSheetField groupSheetField = new ExcelSheetField((ExcelTitleGroup) excelTitle, subFields);
                    for (SingleExcelTitle singleExcelTitle : ((ExcelTitleGroup) excelTitle).getSubTitles()) {
                        subFields.add(new ExcelSheetField(singleExcelTitle, groupSheetField));
                    }
                    excelSheetFields.addAll(subFields);
                    excelSheetFields.add(groupSheetField);
                } else {
                    // 对仅展示型Group下的Field进行打平处理,不build该Group对应的ExcelSheetField
                    for (SingleExcelTitle singleExcelTitle : ((ExcelTitleGroup) excelTitle).getSubTitles()) {
                        ExcelSheetField excelSheetField = new ExcelSheetField(singleExcelTitle, null);
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
            throw new ExcelOpException("excel文件数据列数:" + num + ",超过最大列数限制:" + MAX_COL);
        }
        if (dataEndRow - titleEndRow > MAX_ROW) {
            throw new ExcelOpException("excel文件数据行数:" + (dataEndRow - titleEndRow) + ",超过最大行数限制:" + MAX_ROW);
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
    private List<SheetTitleInfo> parseSheetTitleInfo(Sheet sheet, int titleStartRow, int titleEndRow, List<ExcelSheetField> excelSheetFields) {
        List<SheetTitleInfo> titleInfoList = new ArrayList<>();
        //key为合并单元格开始的列，值为表头中以该列为起始列的所有合并单元格
        Map<Integer, List<ExcelMergeInfo>> titleMergeMap = getTitleMergeMap(sheet, titleStartRow, titleEndRow);

        int colIndex = sheet.getRow(titleStartRow).getFirstCellNum();
        int colEndIndex = sheet.getRow(titleStartRow).getLastCellNum();

        //得到所有sheetField名称,用来判断当前列是否需要被解析
        List<String> sheetFieldNames = excelSheetFields.stream().map(ExcelSheetField::getUniqueName).collect(Collectors.toList());

        if (titleStartRow == titleEndRow) {
            while (colIndex < colEndIndex) {
                //只有一级表头
                String title = getCellTitle(sheet, titleStartRow, colIndex);
                SheetTitleInfo titleInfo = new SheetTitleInfo(null, title, colIndex, false, false);
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
                    SheetTitleInfo titleInfo = new SheetTitleInfo(null, title, colIndex, true, false);
                    addExcelTitleInfo(titleInfo, sheetFieldNames, titleInfoList);
                    colIndex++;
                } else {
                    //二级表头的情况
                    if (!isValidTitleGroup(titleMergeMap, sheet, titleStartRow, titleEndRow, colIndex)) {
                        throw new ExcelOpException("不是一个合法的表头");
                    }
                    String parentTitle = getCellTitle(sheet, titleStartRow, colIndex);
                    //表头组作为list存储的key时,添加到titleInfoList中
                    boolean isCollection = false;
                    if (sheetFieldNames.contains(parentTitle)) {
                        isCollection = true;
                        SheetTitleInfo titleInfo = new SheetTitleInfo(null, parentTitle, colIndex, false, true);
                        addExcelTitleInfo(titleInfo, sheetFieldNames, titleInfoList);
                    }

                    ExcelMergeInfo columnMergeInfo = getColumnMergeInfo(titleMergeMap.get(colIndex));
                    int lastColumn = columnMergeInfo == null ? colIndex : columnMergeInfo.getColumnEndIndex();
                    int detailColIndex = colIndex;
                    while (detailColIndex <= lastColumn) {
                        String title = getCellTitle(sheet, titleEndRow, detailColIndex);
                        SheetTitleInfo titleInfo = isCollection ? new SheetTitleInfo(parentTitle, title, detailColIndex, false, false)
                                : new SheetTitleInfo(null, title, detailColIndex, false, false);
                        addExcelTitleInfo(titleInfo, sheetFieldNames, titleInfoList);
                        detailColIndex++;
                    }
                    colIndex = lastColumn + 1;
                }
            }
        }
        return titleInfoList;
    }

    private void addExcelTitleInfo(SheetTitleInfo sheetTitleInfo, List<String> sheetFieldNames, List<SheetTitleInfo> titleInfoList) {
        if (sheetFieldNames.contains(sheetTitleInfo.getUniqueTitle())) {
            titleInfoList.add(sheetTitleInfo);
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
        if (excelMergeInfoList == null || excelMergeInfoList.isEmpty()) {
            return null;
        }
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
        List<SheetTitleInfo> masterTitleInfoList = dataParseInfo.getTitleInfoList().stream()
                .filter(sheetTitleInfo -> !sheetTitleInfo.isSubTitle()).collect(Collectors.toList());
        // 获取二级表头信息(主表头标题与二级表头的映射关系)
        Map<String, List<SheetTitleInfo>> subTitleInfoMap = dataParseInfo.getTitleInfoList().stream()
                .filter(SheetTitleInfo::isSubTitle)
                .collect(Collectors.groupingBy(SheetTitleInfo::getParentTitle));

        // 数据行迭代器
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
            for (SheetTitleInfo titleInfo : masterTitleInfoList) {
                if (fieldMap.containsKey(titleInfo.getUniqueTitle())) {
                    ExcelSheetField sheetField = fieldMap.get(titleInfo.getUniqueTitle());
                    // 如果需要进行数据聚合
                    if (fieldMap.get(titleInfo.getUniqueTitle()).isHasSubField()) {
                        List<SheetTitleInfo> subTitleInfo = subTitleInfoMap.getOrDefault(titleInfo.getUniqueTitle(), new ArrayList<>());
                        List<Map<String, Object>> subDataList = collectSubData(subTitleInfo, rowData, fieldMap);
                        verifyRequired(sheetField, subDataList);
                        record.put(sheetField.getCode(), sheetField.getDataFormatter() == null ?
                                subDataList : sheetField.getDataFormatter().unFormat(subDataList, record));
                    } else {
                        Object cellValue = rowData.get(0).get(titleInfo.getFirstColumn());
                        verifyRequired(sheetField, cellValue);
                        record.put(sheetField.getCode(), sheetField.getDataFormatter() == null ?
                                cellValue : sheetField.getDataFormatter().unFormat(cellValue, record));
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
                                                  int rowEndIndex, List<SheetTitleInfo> titleInfoList) {
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

    /**
     * 获取聚合数据
     *
     * @param subTitleInfoList
     * @param rowValues
     * @param fieldMap
     * @return
     */
    private List<Map<String, Object>> collectSubData(List<SheetTitleInfo> subTitleInfoList,
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
                    verifyRequired(field, cellValue);
                    subRecord.put(field.getCode(), field.getDataFormatter() == null ? cellValue :
                            field.getDataFormatter().unFormat(cellValue, subRecord));
                }
            });
            subRecords.add(subRecord);
        });
        return subRecords;
    }

    private void verifyRequired(ExcelSheetField field, Object value) {
        if (value instanceof String) {
            if (Boolean.TRUE.equals(field.isRequired()) && StringUtils.isBlank((String) value)) {
                throw new ExcelOpException("字段[" + field.getName() + "]不能为空");
            }
        } else if (value instanceof List) {
            if (Boolean.TRUE.equals(field.isRequired()) && CollectionUtils.isEmpty((List) value)) {
                throw new ExcelOpException("字段[" + field.getName() + "]不能为空");
            }
        }
    }


    //-----------------------------------------生成excel函数------------------------------------//

    private XSSFCellStyle setCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("@"));
        return cellStyle;
    }

    /**
     * 生成表头
     *
     * @param buildInfo
     * @return
     */
    private int buildSheetTitle(SheetBuildInfo buildInfo) {
        XSSFSheet sheet = buildInfo.getSheet();
        XSSFCellStyle cellStyle = buildInfo.getCellStyle();
        int titleStartRow = buildInfo.getStartRow();
        int columnIndex = buildInfo.getStartColumn();
        XSSFRow titleRow = sheet.getRow(titleStartRow) == null ? sheet.createRow(titleStartRow) : sheet.getRow(titleStartRow);

        boolean cellMerge = buildInfo.getExcelTitles().stream().anyMatch(excelTitle -> excelTitle instanceof ExcelTitleGroup);

        for (ExcelTitle excelTitle : buildInfo.getExcelTitles()) {
            buildSheetCell(excelTitle.getName(), columnIndex, titleRow, cellStyle);
            if (excelTitle instanceof ExcelTitleGroup) {
                // 如果当前列是表头组,对表头组进行单元格列合并
                CellRangeAddress cellMergeRegion = new CellRangeAddress(titleStartRow, titleStartRow, columnIndex,
                        columnIndex + ((ExcelTitleGroup) excelTitle).getSubTitles().size() - 1);
                if (((ExcelTitleGroup) excelTitle).getSubTitles().size() > 1) {
                    // 表头组下的表头个数大于1才需要合并
                    sheet.addMergedRegion(cellMergeRegion);
                }

                // 处理下级表头
                int subTitleRow = titleStartRow + 1;
                XSSFRow subRow = sheet.getRow(subTitleRow) == null ? sheet.createRow(subTitleRow) : sheet.getRow(subTitleRow);
                for (SingleExcelTitle singleExcelTitle : ((ExcelTitleGroup) excelTitle).getSubTitles()) {
                    buildSheetCell(singleExcelTitle.getName(), columnIndex, subRow, cellStyle);
                    columnIndex++;
                }
            } else {
                if (cellMerge) {
                    // 如果存在表头组,对独立表头进行单元格行合并
                    CellRangeAddress cellMergeRegion = new CellRangeAddress(titleStartRow, titleStartRow + 1, columnIndex, columnIndex);
                    sheet.addMergedRegion(cellMergeRegion);
                }
                columnIndex++;
            }
        }

        return columnIndex - 1;
    }

    /**
     * 生成数据
     *
     * @param buildInfo
     * @return
     */
    private void buildSheetData(SheetBuildInfo buildInfo, List<Map<String, Object>> data) {
        int rowIndex = buildInfo.getStartRow();
        List<ExcelTitle> excelTitles = buildInfo.getExcelTitles();

        for (Map<String, Object> rowData : data) {
            int columnIndex = buildInfo.getStartColumn();
            int endRow = calculateDataEndRow(excelTitles, rowData, rowIndex);
            for (ExcelTitle excelTitle : excelTitles) {
                //处理非聚合数据
                if (!(excelTitle instanceof ExcelTitleGroup)) {
                    columnIndex = buildSingleData(buildInfo, rowIndex, rowData, columnIndex, endRow, excelTitle);
                } else {
                    columnIndex = buildDataWithGroup(buildInfo, rowData, excelTitle, columnIndex, rowIndex, endRow);
                }
            }
            rowIndex = endRow + 1;
        }
    }

    private int buildDataWithGroup(SheetBuildInfo buildInfo, Map<String, Object> rowData, ExcelTitle excelTitle, int columnIndex, int rowIndex, int endRow) {
        int colIndex = columnIndex;
        if (((ExcelTitleGroup) excelTitle).isCollection()) {
            //处理聚合数据
            colIndex = buildCollectedData(buildInfo, rowData, excelTitle, rowIndex, columnIndex);
        } else {
            for (SingleExcelTitle singleExcelTitle : ((ExcelTitleGroup) excelTitle).getSubTitles()) {
                colIndex = buildSingleData(buildInfo, rowIndex, rowData, colIndex, endRow, singleExcelTitle);
            }

        }
        return colIndex;
    }

    /**
     * 计算非聚合字段单元格行合并的最后一行
     *
     * @param fields
     * @param rowData
     * @param startRow
     * @return
     */
    private int calculateDataEndRow(List<ExcelTitle> fields, Map<String, Object> rowData, int startRow) {
        int maxSize = 1;
        for (ExcelTitle field : fields) {
            if (field instanceof ExcelTitleGroup && ((ExcelTitleGroup) field).isCollection()) {
                List value = (List) (field.getDataFormatter() == null ? rowData.get(field.getCode()) :
                        field.getDataFormatter().format(rowData.get(field.getCode()), rowData));
                if (!CollectionUtils.isEmpty(value)) {
                    //取聚合数据数据条数的最大值
                    maxSize = Math.max(maxSize, value.size());
                }
            }
        }
        return startRow + maxSize - 1;
    }

    private void buildSheetCell(Object value, int columnIndex, XSSFRow row, XSSFCellStyle cellStyle) {
        XSSFCell cell = row.createCell(columnIndex);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(Objects.isNull(value) ? null : String.valueOf(value));
    }


    /**
     * 生成聚合的excel数据
     *
     * @param buildInfo
     * @param rowData
     * @param field
     * @param rowIndex
     * @param sheet
     * @param columnIndex
     * @return
     */
    private int buildCollectedData(SheetBuildInfo buildInfo, Map<String, Object> rowData, ExcelTitle field, int rowIndex, int columnIndex) {
        XSSFSheet sheet = buildInfo.getSheet();
        List<Map<String, Object>> value = (List) (field.getDataFormatter() == null ? rowData.get(field.getCode()) :
                field.getDataFormatter().format(rowData.get(field.getCode()), rowData));
        if (!CollectionUtils.isEmpty(value)) {
            int subRowIndex = rowIndex;
            for (Map<String, Object> subRowData : value) {
                XSSFRow subRow = sheet.getRow(subRowIndex) == null ? sheet.createRow(subRowIndex) : sheet.getRow(subRowIndex);
                int subColumnIndex = columnIndex;
                for (SingleExcelTitle singleExcelTitle : ((ExcelTitleGroup) field).getSubTitles()) {
                    String subValue = (String) (singleExcelTitle.getDataFormatter() == null ? subRowData.get(singleExcelTitle.getCode()) :
                            singleExcelTitle.getDataFormatter().format(subRowData.get(singleExcelTitle.getCode()), subRowData));
                    buildSheetCell(subValue, subColumnIndex, subRow, buildInfo.getCellStyle());
                    subColumnIndex++;
                }
                subRowIndex++;
            }
        }
        return columnIndex + ((ExcelTitleGroup) field).getSubTitles().size();
    }


    /**
     * 生成单个的excel数据
     *
     * @param buildInfo
     * @param sheet
     * @param rowIndex
     * @param rowData
     * @param columnIndex
     * @param endRow
     * @param row
     * @param excelTitle
     * @return
     */
    private int buildSingleData(SheetBuildInfo buildInfo, int rowIndex, Map<String, Object> rowData, int columnIndex, int endRow, ExcelTitle excelTitle) {
        XSSFSheet sheet = buildInfo.getSheet();
        XSSFRow row = sheet.getRow(rowIndex) == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
        String value = (String) (excelTitle.getDataFormatter() == null ? rowData.get(excelTitle.getCode()) :
                excelTitle.getDataFormatter().format(rowData.get(excelTitle.getCode()), rowData));
        buildSheetCell(value, columnIndex, row, buildInfo.getCellStyle());

        if (rowIndex != endRow) {
            CellRangeAddress cellRangeAddress = new CellRangeAddress(rowIndex, endRow, columnIndex, columnIndex);
            sheet.addMergedRegion(cellRangeAddress);
        }

        return columnIndex + 1;
    }

    /**
     * 自动调整列表宽度适应中文字符串
     *
     * @param sheet
     * @param size  要调整的列表数量
     */
    private void autoColumnWidthForChineseChars(Sheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            // 获取列宽
            final int columnWidth = sheet.getColumnWidth(columnNum);
            if (columnNum >= 256 * 256) {
                //列宽已经超过最大列宽则放弃当前列遍历
                continue;
            }
            //新列宽
            int newWidth = getMaxWidth(sheet, columnNum);

            if (newWidth != columnWidth) {
                //设置列宽
                sheet.setColumnWidth(columnNum, newWidth);
            }
        }
    }

    private int getMaxWidth(Sheet sheet, int columnNum) {
        int maxWidth = sheet.getColumnWidth(columnNum);
        // 遍历所有的行,查找有汉字的列计算新的最大列宽
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row currentRow;
            if (sheet.getRow(rowNum) == null) {
                continue;
            } else {
                currentRow = sheet.getRow(rowNum);
            }
            if (currentRow.getCell(columnNum) != null) {
                Cell currentCell = currentRow.getCell(columnNum);
                if (currentCell.getCellType() == CellType.STRING) {
                    String value = currentCell.getStringCellValue();
                    // 计算字符串中中文字符的数量
                    int count = chineseCharCountOf(value);
                    // 在该列字符长度的基础上加上汉字个数计算列宽 (+1为给单元格左右空白余量)
                    int length = value.length() * 256 + (count + 1) * 256 * 2;
                    // 使用字符串的字节长度计算列宽
                    if (maxWidth < length && length < 256 * 256) {
                        maxWidth = length;
                    }
                }
            }
        }
        return maxWidth;
    }

    /**
     * 计算字符串中中文字符的数量
     * 参见 <a hrft="https://www.cnblogs.com/straybirds/p/6392306.html">《汉字unicode编码范围》</a >
     *
     * @param input
     * @return
     */
    private int chineseCharCountOf(String input) {
        int count = 0; //汉字数量
        if (null != input) {
            String regEx = "[\\u4e00-\\u9fa5]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(input);
            int len = m.groupCount();
            //获取汉字个数
            while (m.find()) {
                for (int i = 0; i <= len; i++) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

}
