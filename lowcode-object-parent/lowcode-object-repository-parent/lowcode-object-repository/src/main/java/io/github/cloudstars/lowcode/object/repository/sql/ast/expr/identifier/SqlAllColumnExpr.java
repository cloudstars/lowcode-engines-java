package io.github.cloudstars.lowcode.object.repository.sql.ast.expr.identifier;


import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.AbstractSqlExprImpl;
import io.github.cloudstars.lowcode.object.repository.sql.ast.statement.SqlTableSource;
import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

/**
 * Select语中的查询所有列 "*" 的表达式
 *
 * @author clouds
 */
public final class SqlAllColumnExpr extends AbstractSqlExprImpl {

    private transient SqlTableSource resolvedObjectSource;

    public SqlAllColumnExpr() {
    }

    public SqlTableSource getResolvedObjectSource() {
        return this.resolvedObjectSource;
    }

    public void setResolvedObjectSource(SqlTableSource resolvedObjectSource) {
        this.resolvedObjectSource = resolvedObjectSource;
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlAllColumnExpr cloneMe() {
        SqlAllColumnExpr x = new SqlAllColumnExpr();
        x.resolvedObjectSource = this.resolvedObjectSource;
        return x;
    }

}
