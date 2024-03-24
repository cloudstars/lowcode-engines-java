package net.cf.object.engine.sqlbuilder.delete;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.sqlbuilder.SqlBuilderOqlAstVisitorAdaptor;

/**
 * MyOql select语句输出 AST访问器
 *
 * @author clouds
 */
public final class OqlDeleteAstVisitor extends SqlBuilderOqlAstVisitorAdaptor {

    private final SqlDeleteStatementBuilder builder;

    public OqlDeleteAstVisitor(SqlDeleteStatementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(OqlDeleteStatement x) {
        this.selfObject = x.getFrom().getResolvedObject();

        SqlExprTableSource from = this.buildSqlTableSource(x.getFrom());
        this.builder.from(from);

        // 输出where条件
        SqlExpr where = x.getWhere();
        if (where != null) {
            this.builder.where(this.buildSqlExpr(this.selfObject, where));
        }

        return false;
    }
}
