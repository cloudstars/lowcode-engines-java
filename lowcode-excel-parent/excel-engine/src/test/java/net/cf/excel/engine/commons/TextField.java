package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.SingleExcelField;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-23 09:06
 * @Description: TextField
 */
public class TextField implements SingleExcelField {
    private String code;

    private String name;

    public TextField(String code, String name) {
        this.code = code;
        this.name = name;
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
        return DataType.TEXT;
    }

    @Override
    public DataFormatter<String> getDataFormatter() {
        return new DataFormatter<String>() {
            @Override
            public String format(Object data) {
                return String.valueOf(data);
            }

            @Override
            public String unFormat(Object data) {
                return String.valueOf(data);
            }
        };
    }
}