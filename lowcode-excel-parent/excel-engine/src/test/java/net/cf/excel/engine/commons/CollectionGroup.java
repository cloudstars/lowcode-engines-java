package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.ParseFieldGroup;
import net.cf.excel.engine.SingleParseField;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-27 19:06
 * @Description: 主子表字段
 */
public class CollectionGroup implements ParseFieldGroup {
    private String code;

    private String name;

    private List<SingleParseField> subFields;

    public CollectionGroup(String code, String name, List<SingleParseField> subFields) {
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
            public void format() {

            }

            @Override
            public List unFormat(Object data) {
                return (List) data;
            }
        };
    }

    @Override
    public List<SingleParseField> getSubField() {
        return subFields;
    }

    @Override
    public boolean isCollection() {
        return true;
    }
}