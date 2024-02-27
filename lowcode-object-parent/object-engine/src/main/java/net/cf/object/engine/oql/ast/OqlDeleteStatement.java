package net.cf.object.engine.oql.ast;

import net.cf.object.engine.oql.visitor.OqlAstVisitor;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;

import java.util.ArrayList;
import java.util.List;

public class OqlDeleteStatement extends AbstractOqlStatementImpl implements OqlStatement {

    protected OqlObjectSource objectSource;

    protected SqlExpr where;

    public OqlDeleteStatement() {
    }

    public OqlObjectSource getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(OqlObjectSource objectSource) {
        this.objectSource = objectSource;
        this.addChild(objectSource);
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
            this.nullSafeAcceptChild(visitor, this.objectSource);
            this.nullSafeAcceptChild(visitor, this.where);
        }

        visitor.endVisit(this);
    }


    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList<>();
        children.add(this.objectSource);
        if (this.where != null) {
            children.add(this.where);
        }

        return children;
    }

    @Override
    public OqlDeleteStatement cloneMe() {
        OqlDeleteStatement statement = new OqlDeleteStatement();
        statement.setObjectSource(this.objectSource.cloneMe());
        if (this.where != null) {
            statement.setWhere(this.where.cloneMe());
        }

        return statement;
    }
}
