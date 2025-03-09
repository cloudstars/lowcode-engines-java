package net.cf.excel.engine.commons;

import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-21 17:06
 * @Description: excel合并单元格的记录列表
 */
public class ExcelMergeInfo {
    private int rowStartIndex;

    private int rowEndIndex;

    private int columnStartIndex;

    private int columnEndIndex;

    public ExcelMergeInfo(CellRangeAddress cellAddresses) {
        this.rowStartIndex = cellAddresses.getFirstRow();
        this.rowEndIndex = cellAddresses.getLastRow();
        this.columnStartIndex = cellAddresses.getFirstColumn();
        this.columnEndIndex = cellAddresses.getLastColumn();
    }

    public int getRowStartIndex() {
        return rowStartIndex;
    }

    public void setRowStartIndex(int rowStartIndex) {
        this.rowStartIndex = rowStartIndex;
    }

    public int getRowEndIndex() {
        return rowEndIndex;
    }

    public void setRowEndIndex(int rowEndIndex) {
        this.rowEndIndex = rowEndIndex;
    }

    public int getColumnStartIndex() {
        return columnStartIndex;
    }

    public void setColumnStartIndex(int columnStartIndex) {
        this.columnStartIndex = columnStartIndex;
    }

    public int getColumnEndIndex() {
        return columnEndIndex;
    }

    public void setColumnEndIndex(int columnEndIndex) {
        this.columnEndIndex = columnEndIndex;
    }

    public boolean isRowMerge() {
        return rowStartIndex != rowEndIndex;
    }

    public boolean isColumnMerge() {
        return columnStartIndex != columnEndIndex;
    }
}