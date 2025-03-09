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

    private Boolean required;

    private boolean hasSubField;

    private List<ExcelSheetField> subFields;

    private ExcelSheetField parentField;

    public ExcelSheetField(SingleExcelTitle singleExcelTitle, ExcelSheetField parentField) {
        this.code = singleExcelTitle.getCode();
        this.name = singleExcelTitle.getName();
        this.required = singleExcelTitle.isRequired();
        this.hasSubField = false;
        this.subFields = null;
        this.dataFormatter = singleExcelTitle.getDataFormatter();
        this.parentField = parentField;
    }

    public ExcelSheetField(ExcelTitleGroup excelTitleGroup, List<ExcelSheetField> subFields) {
        this.code = excelTitleGroup.getCode();
        this.name = excelTitleGroup.getName();
        this.required = excelTitleGroup.isRequired();
        this.hasSubField = true;
        this.subFields = subFields;
        this.dataFormatter = excelTitleGroup.getDataFormatter();
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

    public Boolean isRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
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