package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.SingleExcelTitle;

import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-23 09:06
 * @Description: TextField
 */
public class TextTitle implements SingleExcelTitle {
    private String code;

    private String name;

    private Boolean required;

    public TextTitle(String code, String name) {
        this.code = code;
        this.name = name;
        this.required = null;
    }

    public TextTitle(String code, String name, Boolean required) {
        this.code = code;
        this.name = name;
        this.required = required;
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
    public Boolean isRequired() {
        return required;
    }

    @Override
    public DataType getDataType() {
        return DataType.TEXT;
    }

    @Override
    public DataFormatter<String> getDataFormatter() {
        return new DataFormatter<String>() {
            @Override
            public String format(Object value, Map<String, Object> dataMap) {
                return String.valueOf(value);
            }

            @Override
            public String unFormat(Object value, Map<String, Object> dataMap) {
                return String.valueOf(value);
            }
        };
    }
}