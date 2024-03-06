package net.cf.excel.engine;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-22 19:22
 * @Description: excel解析field 顶级接口
 */
public interface ExcelTitle {
    String getCode();

    String getName();

    DataFormatter getDataFormatter();

    // 导入用
    default Boolean isRequired() {
        return false;
    }
}