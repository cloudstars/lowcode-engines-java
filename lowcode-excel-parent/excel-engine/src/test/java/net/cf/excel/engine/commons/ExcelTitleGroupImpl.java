package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.ExcelTitleGroup;
import net.cf.excel.engine.SingleExcelTitle;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-27 19:06
 * @Description: 主子表字段
 */
public class ExcelTitleGroupImpl implements ExcelTitleGroup {
    private String code;

    private String name;

    private boolean collection;

    private DataFormatter dataFormatter;

    private List<SingleExcelTitle> subFields;

    public ExcelTitleGroupImpl(String code, String name, boolean collection, DataFormatter dataFormatter, List<SingleExcelTitle> subFields) {
        this.code = code;
        this.name = name;
        this.collection = collection;
        this.dataFormatter = dataFormatter;
        this.subFields = subFields;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DataFormatter<List> getDataFormatter() {
        return dataFormatter;
    }

    @Override
    public List<SingleExcelTitle> getSubTitles() {
        return subFields;
    }

    @Override
    public boolean isCollection() {
        return collection;
    }
}