package net.cf.excel.engine.commons.parse;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-23 11:21
 * @Description: excel对数据进行解析所需信息
 */
public class DataParseInfo {
    /**
     * sheet表
     */
    private Sheet sheet;

    /**
     * excel公式计算器
     */
    private FormulaEvaluator formulaEvaluator;

    /**
     * sheet表标题信息集合
     */
    private List<SheetTitleInfo> titleInfoList;

    /**
     * sheet表字段集合
     */
    private List<ExcelSheetField> sheetFieldList;

    /**
     * 数据起始行
     */
    private int dataStartRow;

    /**
     * 数据结束行
     */
    private int dataEndRow;

    public DataParseInfo(Sheet sheet, FormulaEvaluator formulaEvaluator, List<SheetTitleInfo> titleInfoList, List<ExcelSheetField> sheetFieldList, int dataStartRow, int dataEndRow) {
        this.sheet = sheet;
        this.formulaEvaluator = formulaEvaluator;
        this.titleInfoList = titleInfoList;
        this.sheetFieldList = sheetFieldList;
        this.dataStartRow = dataStartRow;
        this.dataEndRow = dataEndRow;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public FormulaEvaluator getFormulaEvaluator() {
        return formulaEvaluator;
    }

    public void setFormulaEvaluator(FormulaEvaluator formulaEvaluator) {
        this.formulaEvaluator = formulaEvaluator;
    }

    public List<SheetTitleInfo> getTitleInfoList() {
        return titleInfoList;
    }

    public void setTitleInfoList(List<SheetTitleInfo> titleInfoList) {
        this.titleInfoList = titleInfoList;
    }

    public List<ExcelSheetField> getSheetFieldList() {
        return sheetFieldList;
    }

    public void setSheetFieldList(List<ExcelSheetField> sheetFieldList) {
        this.sheetFieldList = sheetFieldList;
    }

    public int getDataStartRow() {
        return dataStartRow;
    }

    public void setDataStartRow(int dataStartRow) {
        this.dataStartRow = dataStartRow;
    }

    public int getDataEndRow() {
        return dataEndRow;
    }

    public void setDataEndRow(int dataEndRow) {
        this.dataEndRow = dataEndRow;
    }
}