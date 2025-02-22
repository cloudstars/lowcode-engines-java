package net.cf.excel.engine.bean;

import net.cf.excel.engine.ExcelTitle;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-21 17:03
 * @Description: Excel生成配置
 */
public class ExcelBuildConfig {
    private List<ExcelTitle> excelTitles;

    private String sheetName;

    private int titleStartRow;

    public List<ExcelTitle> getExcelTitles() {
        return excelTitles;
    }

    public void setExcelTitles(List<ExcelTitle> excelTitles) {
        this.excelTitles = excelTitles;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getTitleStartRow() {
        return titleStartRow;
    }

    public void setTitleStartRow(int titleStartRow) {
        this.titleStartRow = titleStartRow;
    }
}
