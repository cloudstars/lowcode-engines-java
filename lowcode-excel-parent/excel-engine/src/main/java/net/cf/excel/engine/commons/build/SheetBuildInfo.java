package net.cf.excel.engine.commons.build;

import net.cf.excel.engine.ExcelField;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-28 15:54
 * @Description: sheet构建信息
 */
public class SheetBuildInfo {
    private XSSFSheet sheet;

    private int startColumn;

    private int startRow;

    private XSSFCellStyle cellStyle;

    private List<ExcelField> excelFields;

    public SheetBuildInfo(XSSFSheet sheet, int startColumn, int startRow, XSSFCellStyle cellStyle, List<ExcelField> excelFields) {
        this.sheet = sheet;
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.cellStyle = cellStyle;
        this.excelFields = excelFields;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public XSSFCellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(XSSFCellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public List<ExcelField> getParseFields() {
        return excelFields;
    }

    public void setParseFields(List<ExcelField> excelFields) {
        this.excelFields = excelFields;
    }
}