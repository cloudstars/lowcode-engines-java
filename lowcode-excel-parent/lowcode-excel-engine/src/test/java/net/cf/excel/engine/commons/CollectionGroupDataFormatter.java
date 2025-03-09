package net.cf.excel.engine.commons;

import net.cf.excel.engine.DataFormatter;

import java.util.List;
import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-05 20:42
 * @Description: 主子表dataFormatter
 */
public class CollectionGroupDataFormatter implements DataFormatter<List> {
    @Override
    public String getDataFormatterCode() {
        return "detail";
    }

    @Override
    public String getDataFormatterName() {
        return "主子表";
    }

    @Override
    public List<Map<String, Object>> format(Object value, Map<String, Object> dataMap) {
        return (List<Map<String, Object>>) value;
    }

    @Override
    public List unFormat(Object value, Map<String, Object> dataMap) {
        return (List) value;
    }
}