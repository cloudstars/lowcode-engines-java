package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlDeleteStatement extends AbstractOqlStatementImpl implements OqlStatement {

    protected OqlExprObjectSource from;

    protected SqlExpr where;

    public OqlDeleteStatement() {
    }

    public OqlExprObjectSource getFrom() {
        return from;
    }

    public void setFrom(OqlExprObjectSource from) {
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

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChildren(visitor, this.from);
            this.nullSafeAcceptChildren(visitor, this.where);
        }

        visitor.endVisit(this);
    }


    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList<>();
        children.add(this.from);
        if (this.where != null) {
            children.add(this.where);
        }

        return children;
    }

    @Override
    public OqlDeleteStatement cloneMe() {
        OqlDeleteStatement statement = new OqlDeleteStatement();
        statement.setFrom(this.from.cloneMe());
        if (this.where != null) {
            statement.setWhere(this.where.cloneMe());
        }

        return statement;
    }
}
