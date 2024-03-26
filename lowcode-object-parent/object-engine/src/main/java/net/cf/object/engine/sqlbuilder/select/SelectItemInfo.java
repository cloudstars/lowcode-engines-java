package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.object.engine.data.FieldMapping;

import java.util.ArrayList;
import java.util.List;

/**
 *  查询字段信息
 *
 * @author clouds
 */
public class SelectItemInfo {

    private FieldMapping fieldMapping;

    private final List<SqlSelectItem> selectItems = new ArrayList<>();

    public SelectItemInfo() {
    }

    public FieldMapping getFieldMapping() {
        return fieldMapping;
    }

    public void setFieldMapping(FieldMapping fieldMapping) {
        this.fieldMapping = fieldMapping;
    }

    public List<SqlSelectItem> getSelectItems() {
        return selectItems;
    }

    public void addSelectItems(List<SqlSelectItem> selectItems) {
        this.selectItems.addAll(selectItems);
    }

    public void addSelectItem(SqlSelectItem selectItem) {
        this.selectItems.add(selectItem);
    }

    /**
     * 添加子查询字段列表
     *
     * @param subItemInfos
     */
    public void addSubItemInfos(List<SelectItemInfo> subItemInfos) {
        for (SelectItemInfo subItemInfo : subItemInfos) {
            this.fieldMapping.addSubField(subItemInfo.getFieldMapping());
            this.addSelectItems(subItemInfo.getSelectItems());
        }
    }
}
