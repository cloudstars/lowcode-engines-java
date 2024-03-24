package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class SqlDeleteStatement extends AbstractSqlStatementImpl implements SqlStatement {

    protected SqlExprTableSource from;

    protected SqlExpr where;

    public SqlDeleteStatement() {
    }

    public SqlExprTableSource getFrom() {
        return from;
    }

    public void setFrom(SqlExprTableSource from) {
        this.from = from;
        this.addChild(from);
    }

    public SqlExpr getWhere() {
        return this.where;
    }

    public void setWhere(SqlExpr where) {
        if (where != null) {
            where.setParent(this);
        }

        this.where = where;
    }


    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChildren(visitor, this.from);
            this.nullSafeAcceptChildren(visitor, this.where);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        children.add(this.from);
        if (this.where != null) {
            children.add(this.where);
        }

        return children;
    }

    @Override
    public SqlDeleteStatement cloneMe() {
        SqlDeleteStatement statement = new SqlDeleteStatement();
        statement.setFrom(this.from.cloneMe());
        if (this.where != null) {
            statement.setWhere(this.where.cloneMe());
        }

        return statement;
    }
}
