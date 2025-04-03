package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.DistinctOption;
import net.cf.form.repository.sql.ast.statement.SqlOrderBy;
import net.cf.form.repository.sql.ast.statement.SqlSelectGroupByClause;
import net.cf.object.engine.oql.AbstractOqlObjectImpl;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL select查询
 *
 * @author clouds
 */
public class OqlSelect extends AbstractOqlObjectImpl {

    /**
     * 是否有括号
     */
    protected boolean parenthesized;

    /**
     * distinct选项
     */
    protected DistinctOption distinctOption;

    /**
     * 查询的列表
     */
    private final List<OqlSelectItem> selectItems = new ArrayList<>();

    /**
     * 查询的模型
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

    /**
     * Order By 子句
     */
    private SqlOrderBy orderBy;

    /**
     * Limit 子句
     */
    private SqlLimit limit;

    public List<OqlSelectItem> getSelectItems() {
        return selectItems;
    }

    public void addSelectItem(OqlSelectItem selectItem) {
        this.selectItems.add(selectItem);
        this.addChild(selectItem);
    }

    public void addSelectItems(List<OqlSelectItem> selectItems) {
        this.selectItems.addAll(selectItems);
        this.addChildren(selectItems);
    }

    public boolean isParenthesized() {
        return this.parenthesized;
    }

    public void setParenthesized(boolean parenthesized) {
        this.parenthesized = parenthesized;
    }

    public DistinctOption getDistinctOption() {
        return distinctOption;
    }

    public void setDistinctOption(DistinctOption distinctOption) {
        this.distinctOption = distinctOption;
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
    public void accept(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.selectItems != null) {
                this.nullSafeAcceptChildren(visitor, this.selectItems);
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
        for (OqlSelectItem selectItem : selectItems) {
            select.addSelectItem(selectItem.cloneMe());
        }
        select.setFrom(this.from.cloneMe());
        select.setWhere(this.where.cloneMe());

        return select;
    }

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList<>();
        children.addAll(this.selectItems);
        children.add(this.from);
        children.add(this.where);
        children.add(this.groupBy);
        children.add(this.orderBy);
        children.add(this.limit);
        return children;
    }
}
