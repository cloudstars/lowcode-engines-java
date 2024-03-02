package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.ExcelTitleGroup;
import net.cf.excel.engine.SingleExcelTitle;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-28 08:45
 * @Description: 仅展示group实现类
 */
public class ShowGroup implements ExcelTitleGroup {
    private String code;

    private String name;

    private List<SingleExcelTitle> subFields;

    public ShowGroup(String code, String name, List<SingleExcelTitle> subFields) {
        this.code = code;
        this.name = name;
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
    public DataType getDataType() {
        return null;
    }

    @Override
    public DataFormatter getDataFormatter() {
        return null;
    }

    @Override
    public List<SingleExcelTitle> getSubTitles() {
        return subFields;
    }

    @Override
    public boolean isCollection() {
        return false;
    }
}