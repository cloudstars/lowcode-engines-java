package net.cf.object.engine.sqlbuilder;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.data.FieldMapping;

import java.util.ArrayList;
import java.util.List;

/**
 *  插入或更新共用的字段信息
 *
 * @author clouds
 */
public class FieldItemInfo {

    /**
     * 字段的映射信息（OQL语句的一个Expr对应一个mapping，含展开类型的字段：*、模型展开、字段展开Expr）
     */
    private FieldMapping fieldMapping;

    /**
     * 对于非展开字段，只有一个字段，展开类型可以有多个字段
     */
    private final List<SqlExpr> items = new ArrayList<>();

    public FieldItemInfo() {
    }

    public FieldMapping getFieldMapping() {
        return fieldMapping;
    }

    public void setFieldMapping(FieldMapping fieldMapping) {
        this.fieldMapping = fieldMapping;
    }

    public List<SqlExpr> getItems() {
        return items;
    }

    public void addItems(List<SqlExpr> items) {
        this.items.addAll(items);
    }

    public void addItem(SqlExpr item) {
        this.items.add(item);
    }

    /**
     * 添加子查询字段列表
     *
     * @param subItemInfos
     */
    public void addSubItemInfos(List<FieldItemInfo> subItemInfos) {
        for (FieldItemInfo subItemInfo : subItemInfos) {
            this.fieldMapping.addSubField(subItemInfo.getFieldMapping());
            this.addItems(subItemInfo.getItems());
        }
    }
}
