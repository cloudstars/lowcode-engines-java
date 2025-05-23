package net.cf.excel.engine.commons.parse;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-21 16:49
 * @Description: excel表头信息
 */
public class SheetTitleInfo {
    /**
     * 字段名称
     */
    private String title;

    /**
     * 上级字段名称
     */
    private String parentTitle;

    /**
     * 起始列
     */
    private int firstColumn;

    /**
     * 是否行合并单元格
     */
    private boolean rowMerge;

    /**
     * 是否列合并单元格
     */
    private boolean columnMerge;

    private boolean isCollection;

    public SheetTitleInfo(String parentTitle, String title, int firstColumn, boolean rowMerge, boolean columnMerge) {
        this.parentTitle = parentTitle;
        this.title = title;
        this.firstColumn = firstColumn;
        this.rowMerge = rowMerge;
        this.columnMerge = columnMerge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public int getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(int firstColumn) {
        this.firstColumn = firstColumn;
    }

    public boolean isRowMerge() {
        return rowMerge;
    }

    public void setRowMerge(boolean rowMerge) {
        this.rowMerge = rowMerge;
    }

    public boolean isColumnMerge() {
        return columnMerge;
    }

    public void setColumnMerge(boolean columnMerge) {
        this.columnMerge = columnMerge;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public String getUniqueTitle() {
        if (parentTitle == null || parentTitle.isEmpty()) {
            return title;
        } else {
            return parentTitle + "." + title;
        }
    }

    /**
     * 是否为子表头
     *
     * @return
     */
    public boolean isSubTitle() {
        return parentTitle != null && !parentTitle.isEmpty();
    }
}