package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;
import net.cf.form.repository.sql.ast.expr.SqlExprImpl;

/**
 * Select语中的查询所有列 "*" 的表达式
 *
 * @author clouds
 */
public final class SqlAllColumnExpr extends SqlExprImpl {

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
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlAllColumnExpr _clone() {
        SqlAllColumnExpr x = new SqlAllColumnExpr();
        x.resolvedObjectSource = this.resolvedObjectSource;
        return x;
    }

}
