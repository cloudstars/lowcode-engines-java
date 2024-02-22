package net.cf.excel.engine.config;

import net.cf.excel.engine.commons.parse.ExcelSheetField;

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
    private List<ExcelSheetField> sheetFields;

    public List<ExcelSheetField> getSheetFields() {
        return sheetFields;
    }

    public void setSheetFields(List<ExcelSheetField> sheetFields) {
        this.sheetFields = sheetFields;
    }
}
