package net.cf.excel.engine.bean;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-03-01 09:21
 * @Description: excel标题解析配置
 */
public class ExcelTitleParseConfig {
    /**
     * 表头起始行
     */
    private int titleStartRow;

    /**
     * 表头结束行
     */
    private int titleEndRow;

    public int getTitleStartRow() {
        return titleStartRow;
    }

    public void setTitleStartRow(int titleStartRow) {
        this.titleStartRow = titleStartRow;
    }

    public int getTitleEndRow() {
        return titleEndRow;
    }

    public void setTitleEndRow(int titleEndRow) {
        this.titleEndRow = titleEndRow;
    }
}