package net.cf.excel.engine;

public interface CellDataType {

    /**
     * 单元格的格式化
     */
    void format();

    /**
     * 单元格的反格式化
     */
    void unformat();
}
