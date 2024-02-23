package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;
import net.cf.excel.engine.SingleParseField;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-23 09:06
 * @Description: Text测试实现类
 */
public class TextField implements SingleParseField {
    private String code;

    private String name;

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
            public void format() {

            }

            @Override
            public String unFormat(Object data) {
                return String.valueOf(data);
            }
        };
    }
}