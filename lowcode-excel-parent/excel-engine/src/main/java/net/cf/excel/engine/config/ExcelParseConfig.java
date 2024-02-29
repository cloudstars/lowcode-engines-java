package net.cf.excel.engine.config;

import net.cf.excel.engine.ExcelField;

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
    private List<ExcelField> excelFields;

    private int titleStartRow;

    private int titleEndRow;

    public List<ExcelField> getParseFields() {
        return excelFields;
    }

    public void setParseFields(List<ExcelField> excelFields) {
        this.excelFields = excelFields;
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
