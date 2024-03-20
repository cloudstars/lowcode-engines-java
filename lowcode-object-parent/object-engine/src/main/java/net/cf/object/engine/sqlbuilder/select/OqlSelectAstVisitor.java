package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlOrderBy;
import net.cf.form.repository.sql.ast.statement.SqlSelectGroupByClause;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.oql.ast.*;
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
            SqlExpr sqlExprX = this.buildSqlExpr(selectItem.getExpr());
            if (sqlExprX instanceof OqlFieldExpandExpr) {
                OqlFieldExpandExpr fieldExpandExpr = (OqlFieldExpandExpr) sqlExprX;
                XField field = fieldExpandExpr.getResolvedField();
                List<SqlIdentifierExpr> properties = fieldExpandExpr.getProperties();
                for (SqlIdentifierExpr property : properties) {
                    SqlSelectItem sqlSelectItem = new SqlSelectItem();
                    OqlPropertyExpr propertyExpr = new OqlPropertyExpr(field, property.getName());
                    sqlSelectItem.setExpr(this.buildSqlExpr(propertyExpr));
                    this.builder.appendSelectItem(sqlSelectItem);
                }
            } else if (sqlExprX instanceof SqlAllColumnExpr) {
                List<XField> fields = this.resolvedObject.getFields();
                for (XField field : fields) {
                    SqlSelectItem sqlSelectItem = new SqlSelectItem();
                    SqlIdentifierExpr identifierExpr = new SqlIdentifierExpr(field.getColumnName());
                    sqlSelectItem.setExpr(identifierExpr);
                    this.builder.appendSelectItem(sqlSelectItem);
                }
            } else {
                this.builder.appendSelectItem(new SqlSelectItem(sqlExprX));
            }
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

        SqlLimit limit = x.getLimit();
        if (limit != null) {
            this.builder.limit(limit);
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


