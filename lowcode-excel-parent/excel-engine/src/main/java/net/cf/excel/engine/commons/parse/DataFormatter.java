package net.cf.excel.engine.commons.parse;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-21 19:47
 * @Description: 数据格式化接口
 */
public interface DataFormatter<T> {
    /**
     * 数据格式化 生成Excel用
     */
    void format();

    /**
     * 数据反格式化 解析Excel用
     */
    T unFormat(Object data);
}
