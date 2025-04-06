package net.cf.excel.engine.bean;

import net.cf.excel.engine.ExcelTitle;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-21 17:03
 * @Description: Excel解析配置
 */
public class ExcelParseConfig {

    /**
     * EXCEL的列配置
     */
    private List<ExcelTitle> excelTitles;

    /**
     * 表头起始行
     */
    private int titleStartRow;

    /**
     * 表头结束行
     */
    private int titleEndRow;

    public ExcelParseConfig() {
    }

    public ExcelParseConfig(List<ExcelTitle> excelTitles, int titleStartRow, int titleEndRow) {
        this.excelTitles = excelTitles;
        this.titleStartRow = titleStartRow;
        this.titleEndRow = titleEndRow;
    }

    public List<ExcelTitle> getExcelTitles() {
        return excelTitles;
    }

    public void setExcelTitles(List<ExcelTitle> excelTitles) {
        this.excelTitles = excelTitles;
    }

    public int getTitleStartRow() {
        return titleStartRow;
    }

    public void setTitleStartRow(int titleStartRow) {
        this.titleStartRow = titleStartRow;
    }

    public int getTitleEndRow() {
        return titleEndRow;
    }

    public void setTitleEndRow(int titleEndRow) {
        this.titleEndRow = titleEndRow;
    }
}
