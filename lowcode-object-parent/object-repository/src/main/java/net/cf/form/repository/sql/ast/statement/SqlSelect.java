package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
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
     * 是否有小括号
     */
    protected boolean parenthesized;

    /**
     * distinct选项
     */
    protected DistinctOption distinctOption;

    /**
     * 查询的列表
     */
    private final List<SqlSelectItem> selectItems = new ArrayList<>();

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

    /**
     * 添加一组查询字段
     *
     * @param selectItems
     */
    public void addSelectItems(List<SqlSelectItem> selectItems) {
        for (SqlSelectItem selectItem : selectItems) {
            this.addSelectItem(selectItem);
        }
    }

    /**
     * 添加一个查询字段
     *
     * @param selectItem
     */
    public void addSelectItem(SqlSelectItem selectItem) {
        SqlExpr selectItemExpr = selectItem.getExpr();
        if (selectItemExpr instanceof SqlIdentifierExpr) {
            String identName = ((SqlIdentifierExpr) selectItemExpr).getName();
            if (this.existIdentifierSelectItem(identName)) {
                return;
            }
        }

        this.selectItems.add(selectItem);
        this.addChild(selectItem);
    }

    /**
     * 是否存在某个标识符的查询字段
     *
     * @param identName
     * @return
     */
    private boolean existIdentifierSelectItem(String identName) {
        for (SqlSelectItem selectItem : selectItems) {
            if (selectItem.getExpr() instanceof SqlIdentifierExpr) {
                SqlIdentifierExpr itemExpr = (SqlIdentifierExpr) selectItem.getExpr();
                if (itemExpr.getName().equals(identName)) {
                    return true;
                }
            }
        }

        return false;
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
    public void accept(SqlAstVisitor visitor) {
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
        for (SqlSelectItem selectItem : selectItems) {
            select.addSelectItem(selectItem.cloneMe());
        }
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
