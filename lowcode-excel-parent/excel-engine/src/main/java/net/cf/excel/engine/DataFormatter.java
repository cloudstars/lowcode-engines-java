package net.cf.excel.engine;

import java.util.Map;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-21 19:47
 * @Description: 数据格式化接口
 */
public interface DataFormatter<T> {
    /**
     * 数据格式化 生成Excel用
     */
    Object format(Object value, Map<String, Object> dataMap);

    /**
     * 数据反格式化 解析Excel用
     */
    T unFormat(Object value, Map<String, Object> dataMap);
}
