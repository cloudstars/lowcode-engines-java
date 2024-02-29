package net.cf.excel.engine.config;

import net.cf.excel.engine.ExcelField;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-21 17:03
 * @Description: Excel生成配置
 */
public class ExcelBuildConfig {
    private List<ExcelField> excelFields;

    private String sheetName;

    private int titleStartRow;

    public List<ExcelField> getParseFields() {
        return excelFields;
    }

    public void setParseFields(List<ExcelField> excelFields) {
        this.excelFields = excelFields;
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
