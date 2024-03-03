package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlOrderBy;
import net.cf.form.repository.sql.ast.statement.SqlSelectGroupByClause;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.ast.OqlSelect;
import net.cf.object.engine.oql.visitor.OqlAstVisitorAdaptor;

import java.util.List;

/**
 * OQL Select 语句 AST 访问器
 *
 * @author clouds
 */
public final class OqlSelectAstVisitor extends OqlAstVisitorAdaptor {

    private final SqlSelectStatementBuilder builder;

    public OqlSelectAstVisitor(SqlSelectStatementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(OqlSelect x) {
        OqlObjectSource from = x.getFrom();
        if (from instanceof OqlExprObjectSource) {
            this.resolvedObject = ((OqlExprObjectSource) from).getResolvedObject();
        }

        // 构建查询列的列表
        List<SqlSelectItem> selectItems = x.getSelectItems();
        for (SqlSelectItem selectItem : selectItems) {
            SqlSelectItem sqlSelectItem = selectItem.cloneMe();
            sqlSelectItem.setExpr(this.buildSqlExpr(selectItem.getExpr()));
            this.builder.appendSelectItem(sqlSelectItem);
        }

        // 输出查询的表
        SqlTableSource tableSource = this.buildSqlTableSource(from);
        this.builder.from(tableSource);

        // 输出where条件
        SqlExpr where = x.getWhere();
        if (where != null) {
            this.builder.where(this.buildSqlExpr(where));
        }

        // 输出groupBy子句
        SqlSelectGroupByClause groupBy = x.getGroupBy();
        if (groupBy != null) {
            this.buildGroupBy(groupBy);
        }

        // 输出orderBy
        SqlOrderBy orderBy = x.getOrderBy();
        if (orderBy != null) {
            this.buildOrderBy(orderBy);
        }

        return false;
    }

    private SqlSelectGroupByClause buildGroupBy(SqlSelectGroupByClause groupBy) {
        return null;
    }

    private SqlOrderBy buildOrderBy(SqlOrderBy orderBy) {
        return null;
    }
}


