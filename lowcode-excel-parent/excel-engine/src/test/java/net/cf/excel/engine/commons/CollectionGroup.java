package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.ExcelFieldGroup;
import net.cf.excel.engine.SingleExcelField;

import java.util.List;
import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-27 19:06
 * @Description: 主子表字段
 */
public class CollectionGroup implements ExcelFieldGroup {
    private String code;

    private String name;

    private List<SingleExcelField> subFields;

    public CollectionGroup(String code, String name, List<SingleExcelField> subFields) {
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
            public List<Map<String, Object>> format(Object data) {
                return (List<Map<String, Object>>) data;
            }

            @Override
            public List unFormat(Object data) {
                return (List) data;
            }
        };
    }

    @Override
    public List<SingleExcelField> getSubField() {
        return subFields;
    }

    @Override
    public boolean isCollection() {
        return true;
    }
}