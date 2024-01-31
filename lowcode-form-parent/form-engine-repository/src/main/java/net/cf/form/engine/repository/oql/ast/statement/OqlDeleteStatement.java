package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlObject;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class OqlDeleteStatement extends OqlStatementImpl implements OqlStatement {

    protected OqlObjectSource objectSource;

    protected OqlWhereClause whereClause;

    public OqlDeleteStatement() {
    }

    public OqlObjectSource getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(OqlObjectSource objectSource) {
        this.objectSource = objectSource;
        this.addChild(objectSource);
    }

    public OqlWhereClause getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(OqlWhereClause whereClause) {
        this.whereClause = whereClause;
        this.addChild(whereClause);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChild(visitor, this.objectSource);
            this.acceptChild(visitor, this.whereClause);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<OqlObject> getChildren() {
        List<OqlObject> children = new ArrayList<>();
        children.add(this.objectSource);
        if (this.whereClause != null) {
            children.add(this.whereClause);
        }

        return children;
    }

    @Override
    public OqlDeleteStatement clone() {
        OqlDeleteStatement statement = new OqlDeleteStatement();
        statement.setObjectSource(this.objectSource.clone());
        if (this.whereClause != null) {
            statement.setWhereClause(this.whereClause.clone());
        }

        return statement;
    }
}
