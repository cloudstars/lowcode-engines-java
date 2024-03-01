package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.ExcelTitleGroup;
import net.cf.excel.engine.SingleExcelTitle;

import java.util.List;
import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-27 19:06
 * @Description: 主子表字段
 */
public class CollectionGroup implements ExcelTitleGroup {
    private String code;

    private String name;

    private List<SingleExcelTitle> subFields;

    public CollectionGroup(String code, String name, List<SingleExcelTitle> subFields) {
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
        return DataType.LIST;
    }

    @Override
    public DataFormatter<List> getDataFormatter() {
        return new DataFormatter<List>() {
            @Override
            public List<Map<String, Object>> format(Object value, Map<String, Object> dataMap) {
                return (List<Map<String, Object>>) value;
            }

            @Override
            public List unFormat(Object value, Map<String, Object> dataMap) {
                return (List) value;
            }
        };
    }

    @Override
    public List<SingleExcelTitle> getSubTitles() {
        return subFields;
    }

    @Override
    public boolean isCollection() {
        return true;
    }
}