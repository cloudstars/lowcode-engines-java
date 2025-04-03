package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlInsertSelectStatement extends AbstractOqlStatementImpl implements OqlStatement {

    protected OqlExprObjectSource objectSource;

    protected final List<OqlExpr> fields = new ArrayList();

    protected OqlSelect query;

    public OqlInsertSelectStatement() {
    }

    public OqlInsertSelectStatement(OqlInsertInto insertInto) {
        this.objectSource = insertInto.objectSource;
        this.fields.addAll(insertInto.fields);
        this.query = insertInto.getQuery();
    }

    public OqlExprObjectSource getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(OqlExprObjectSource objectSource) {
        this.objectSource = objectSource;
    }

    public OqlSelect getQuery() {
        return query;
    }

    public void setQuery(OqlSelect query) {
        this.query = query;
    }

    public List<OqlExpr> getFields() {
        return fields;
    }

    @Override
    public void accept(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.objectSource.accept(visitor);
            this.nullSafeAcceptChildren(visitor, this.fields);
            this.nullSafeAcceptChild(visitor, this.query);
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlInsertSelectStatement cloneMe() {
        OqlInsertSelectStatement x = new OqlInsertSelectStatement();
        x.setObjectSource(this.objectSource.cloneMe());
        for (OqlExpr field : this.fields) {
            x.fields.add(field.cloneMe());
        }
        if (x.getQuery() != null) {
            x.setQuery(x.getQuery().cloneMe());
        }

        return x;
    }

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        children.add(this.objectSource);
        children.addAll(this.fields);
        children.add(this.query);
        return children;
    }
}
