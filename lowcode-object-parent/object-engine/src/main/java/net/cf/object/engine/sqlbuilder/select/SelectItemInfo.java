package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.statement.SqlSelectItem;

import java.util.List;

/**
 *  查询字段信息
 *
 * @author clouds
 */
public class SelectItemInfo {

    private String fieldName;

    private String columnName;

    private boolean isArray;

    private List<SqlSelectItem> selectItems;

    private List<SelectItemInfo> subItemInfos;

    public SelectItemInfo() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public List<SqlSelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SqlSelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    public List<SelectItemInfo> getSubItemInfos() {
        return subItemInfos;
    }

    public void setSubItemInfos(List<SelectItemInfo> subItemInfos) {
        this.subItemInfos = subItemInfos;
    }
}
