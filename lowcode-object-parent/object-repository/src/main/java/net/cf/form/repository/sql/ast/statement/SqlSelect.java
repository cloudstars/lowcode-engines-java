package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL Select 查询
 *
 * @author clouds
 */
public class SqlSelect extends AbstractSqlObjectImpl {

    /**
     * 是否有小手号
     */
    protected boolean parenthesized;

    /**
     * distinct选项
     */
    protected DistinctOption distinctOption;

    /**
     * 查询的列表
     */
    private List<SqlSelectItem> selectItems = new ArrayList<>();

    /**
     * 查询的表
     */
    private SqlTableSource from;

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

    public SqlSelect() {
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

    public List<SqlSelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SqlSelectItem> selectItems) {
        this.selectItems = selectItems;
        this.addChildren(selectItems);
    }

    public void addSelectItem(SqlSelectItem selectItem) {
        this.selectItems.add(selectItem);
        this.addChild(selectItem);
    }

    public SqlTableSource getFrom() {
        return from;
    }

    public void setFrom(SqlTableSource from) {
        this.from = from;
        this.addChild(from);
    }

    public SqlExpr getWhere() {
        return where;
    }

    public void setWhere(SqlExpr where) {
        this.where = where;
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
    protected void accept0(SqlAstVisitor visitor) {
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

            if (this.groupBy != null) {
                this.groupBy.accept(visitor);
            }

            if (this.orderBy != null) {
                this.orderBy.accept(visitor);
            }

            if (this.limit != null) {
                this.limit.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlSelect cloneMe() {
        SqlSelect select = new SqlSelect();
        List<SqlSelectItem> cloneItems = new ArrayList<>();
        for (SqlSelectItem selectItem : selectItems) {
            cloneItems.add(selectItem.cloneMe());
        }
        select.setSelectItems(cloneItems);
        if (this.from != null) {
            select.setFrom(this.from.cloneMe());
        }
        if (this.where != null) {
            select.setWhere(this.where.cloneMe());
        }
        if (this.groupBy != null) {
            select.setGroupBy(this.groupBy.cloneMe());
        }
        if (this.orderBy != null) {
            select.setOrderBy(this.orderBy.cloneMe());
        }
        if (this.limit != null) {
            setLimit(this.limit.cloneMe());
        }

        return select;
    }
}
