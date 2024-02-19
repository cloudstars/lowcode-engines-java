package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL AST 中的标识符
 *
 * @author clouds
 */
public class SqlIdentifierExpr extends AbstractSqlExprImpl implements SqlName {

    protected String name;

    private SqlObject resolvedColumn;

    private SqlObject resolvedOwnerObject;

    public SqlIdentifierExpr() {
    }

    public SqlIdentifierExpr(String name) {
        this.name = name;
    }

    @Override
    public String getSimpleName() {
        return this.name;
    }

    public String getLowerName() {
        return this.name == null ? null : this.name.toLowerCase();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SqlObject getResolvedColumn() {
        return resolvedColumn;
    }

    public void setResolvedColumn(SqlObject resolvedColumn) {
        this.resolvedColumn = resolvedColumn;
    }

    public SqlObject getResolvedOwnerObject() {
        return resolvedOwnerObject;
    }

    public void setResolvedOwnerObject(SqlObject resolvedOwnerObject) {
        this.resolvedOwnerObject = resolvedOwnerObject;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public SqlIdentifierExpr cloneMe() {
        SqlIdentifierExpr x = new SqlIdentifierExpr(this.name);
        return x;
    }

}
