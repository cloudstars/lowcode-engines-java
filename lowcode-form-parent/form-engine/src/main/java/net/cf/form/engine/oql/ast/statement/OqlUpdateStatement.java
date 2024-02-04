package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObject;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlUpdateStatement extends OqlStatementImpl implements OqlStatement {

    protected OqlObjectSource objectSource;

    protected final List<OqlUpdateSetItem> setItems = new ArrayList<>();

    protected OqlWhereClause whereClause;

    public OqlUpdateStatement() {
    }

    public OqlObjectSource getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(OqlObjectSource objectSource) {
        this.objectSource = objectSource;
        this.addChild(objectSource);
    }

    public List<OqlUpdateSetItem> getSetItems() {
        return setItems;
    }

    public void addSetItems(List<OqlUpdateSetItem> setItems) {
        for (OqlUpdateSetItem setItem : setItems) {
            this.addSetItem(setItem);
        }
    }

    public void addSetItem(OqlUpdateSetItem setItem) {
        this.setItems.add(setItem);
        this.addChild(setItem);
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
            this.acceptChildren(visitor, this.setItems);
            this.acceptChild(visitor, this.whereClause);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<OqlObject> getChildren() {
        List<OqlObject> children = new ArrayList<>();
        children.add(this.objectSource);
        children.addAll(this.setItems);
        if (this.whereClause != null) {
            children.add(this.whereClause);
        }

        return children;
    }

    @Override
    public OqlUpdateStatement clone() {
        OqlUpdateStatement statement = new OqlUpdateStatement();
        statement.setObjectSource(this.objectSource._clone());
        for (OqlUpdateSetItem setItem : this.setItems) {
            statement.addSetItem(setItem);
        }
        if (this.whereClause != null) {
            statement.setWhereClause(this.whereClause.clone());
        }

        return statement;
    }
}
