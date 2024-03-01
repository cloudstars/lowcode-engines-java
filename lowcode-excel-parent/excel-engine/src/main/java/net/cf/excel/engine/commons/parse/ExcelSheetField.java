package net.cf.excel.engine.commons.parse;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.ExcelTitleGroup;
import net.cf.excel.engine.SingleExcelTitle;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-23 09:41
 * @Description: Excel解析处理字段(不对外暴露)
 */
public class ExcelSheetField {
    private String code;

    private String name;

    private DataFormatter dataFormatter;

    private boolean hasSubField;

    private List<ExcelSheetField> subFields;

    private ExcelSheetField parentField;

    public ExcelSheetField(SingleExcelTitle singleParseField, ExcelSheetField parentField) {
        this.code = singleParseField.getCode();
        this.name = singleParseField.getName();
        this.hasSubField = false;
        this.subFields = null;
        this.dataFormatter = singleParseField.getDataFormatter();
        this.parentField = parentField;
    }

    public ExcelSheetField(ExcelTitleGroup parseFieldGroup, List<ExcelSheetField> subFields) {
        this.code = parseFieldGroup.getCode();
        this.name = parseFieldGroup.getName();
        this.hasSubField = true;
        this.subFields = subFields;
        this.dataFormatter = parseFieldGroup.getDataFormatter();
        this.parentField = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasSubField() {
        return hasSubField;
    }

    public void setHasSubField(boolean hasSubField) {
        this.hasSubField = hasSubField;
    }

    public DataFormatter getDataFormatter() {
        return dataFormatter;
    }

    public void setDataFormatter(DataFormatter dataFormatter) {
        this.dataFormatter = dataFormatter;
    }

    public List<ExcelSheetField> getSubFields() {
        return subFields;
    }

    public void setSubFields(List<ExcelSheetField> subFields) {
        this.subFields = subFields;
    }

    public ExcelSheetField getParentField() {
        return parentField;
    }

    public void setParentField(ExcelSheetField parentField) {
        this.parentField = parentField;
    }

    public boolean isMainField() {
        return parentField == null;
    }

    public String getUniqueName() {
        if (isMainField()) {
            return name;
        } else {
            return parentField.getUniqueName() + "." + name;
        }

    }
}