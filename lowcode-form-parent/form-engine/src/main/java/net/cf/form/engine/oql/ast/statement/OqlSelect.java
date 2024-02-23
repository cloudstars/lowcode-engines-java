package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.AbstractOqlObjectImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlOrderBy;
import net.cf.form.repository.sql.ast.statement.SqlSelectGroupByClause;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL select查询
 *
 * @author clouds
 */
public class OqlSelect extends AbstractOqlObjectImpl {

    /**
     * 查询的列表
     */
    private List<SqlSelectItem> selectItems = new ArrayList<>();

    /**
     * 查询的对象
     */
    private OqlObjectSource from;

    /**
     * 查询条件
     */
    private SqlExpr where;

    /**
     * Group By 子句
     */
    private SqlSelectGroupByClause groupBy;

    private SqlOrderBy orderBy;

    private SqlLimit limit;

    public List<SqlSelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SqlSelectItem> selectItems) {
        this.selectItems = selectItems;
        this.addChildren(selectItems);
    }

    public OqlObjectSource getFrom() {
        return from;
    }

    public void setFrom(OqlObjectSource from) {
        this.from = from;
        this.addChild(from);
    }

    public SqlExpr getWhere() {
        return where;
    }

    public void setWhere(SqlExpr where) {
        this.where = where;
        this.addChild(where);
    }

    public SqlSelectGroupByClause getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(SqlSelectGroupByClause groupBy) {
        this.groupBy = groupBy;
        this.addChild(groupBy);
    }

    public SqlOrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(SqlOrderBy orderBy) {
        this.orderBy = orderBy;
        this.addChild(orderBy);
    }

    public SqlLimit getLimit() {
        return limit;
    }

    public void setLimit(SqlLimit limit) {
        this.limit = limit;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.selectItems != null) {
                this.nullSafeAcceptChild(visitor, this.selectItems);
            }

            if (this.from != null) {
                this.from.accept(visitor);
            }

            if (this.where != null) {
                this.where.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlSelect cloneMe() {
        OqlSelect select = new OqlSelect();
        List<SqlSelectItem> cloneItems = new ArrayList<>();
        for (SqlSelectItem selectItem : selectItems) {
            cloneItems.add(selectItem.cloneMe());
        }
        select.setSelectItems(cloneItems);
        select.setFrom(this.from.cloneMe());
        select.setWhere(this.where.cloneMe());

        return null;
    }

    /*@Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList<>();
        children.addAll(this.selectItems);
        children.add(this.from);
        children.add(this.where);
        children.add(this.groupBy);
        children.add(this.orderBy);
        children.add(this.limit);
        return children;
    }*/
}
