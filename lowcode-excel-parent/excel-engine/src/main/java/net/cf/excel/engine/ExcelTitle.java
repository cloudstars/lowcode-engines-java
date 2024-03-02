package net.cf.excel.engine;

import net.cf.excel.engine.commons.DataType;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-22 19:22
 * @Description: excel解析field 顶级接口
 */
public interface ExcelTitle {
    String getCode();

    String getName();

    DataType getDataType();

    DataFormatter getDataFormatter();

}