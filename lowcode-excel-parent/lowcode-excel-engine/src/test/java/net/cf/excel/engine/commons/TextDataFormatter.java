package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;

import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-05 21:14
 * @Description: 文本dataFormatter
 */
public class TextDataFormatter implements DataFormatter<String> {
    @Override
    public String getDataFormatterCode() {
        return "text";
    }

    @Override
    public String getDataFormatterName() {
        return "文本";
    }

    @Override
    public String format(Object value, Map<String, Object> dataMap) {
        return String.valueOf(value);
    }

    @Override
    public String unFormat(Object value, Map<String, Object> dataMap) {
        return String.valueOf(value);
    }
}