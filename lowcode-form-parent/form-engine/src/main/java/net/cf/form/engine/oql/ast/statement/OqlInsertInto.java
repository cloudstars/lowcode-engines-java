package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObject;
import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlInsertInto extends OqlStatementImpl implements OqlReplaceable {

    protected OqlExprObjectSource objectSource;

    protected final List<OqlExpr> fields = new ArrayList();

    protected final List<OqlInsertValues> valuesList = new ArrayList();

    public OqlInsertInto() {
    }

    public OqlExprObjectSource getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(OqlExprObjectSource objectSource) {
        this.objectSource = objectSource;
        this.addChild(objectSource);
    }

    public List<OqlExpr> getFields() {
        return fields;
    }

    public void addFields(List<OqlExpr> fields) {
        for (OqlExpr field : fields) {
            this.addField(field);
        }
    }

    public void addField(OqlExpr field) {
        this.fields.add(field);
        this.addChild(field);
    }

    public void addField(int index, OqlExpr field) {
        this.fields.add(index, field);
        this.addChild(field);
    }

    public List<OqlInsertValues> getValuesList() {
        return valuesList;
    }

    public void addValues(OqlInsertValues values) {
        this.valuesList.add(values);
        this.addChild(values);
    }

    /*public void setValuesList(List<OqlInsertValues> valuesList) {
        this.valuesList.clear();
        this.valuesList.addAll(valuesList);
    }*/

    public void addValuesList(List<OqlInsertValues> valuesList) {
        this.valuesList.addAll(valuesList);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChild(visitor, this.objectSource);
            this.acceptChildren(visitor, this.fields);
            this.acceptChildren(visitor, this.valuesList);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<OqlObject> getChildren() {
        List<OqlObject> children = new ArrayList();
        children.add(this.objectSource);
        children.addAll(this.fields);
        children.addAll(this.valuesList);

        return children;
    }

    @Override
    public OqlInsertInto clone() {
        OqlInsertInto x = new OqlInsertInto();
        x.setObjectSource(this.objectSource._clone());
        for (OqlExpr field : this.fields) {
            x.fields.add(field.clone());
        }
        for (OqlInsertValues valuesItem : this.valuesList) {
            x.valuesList.add(valuesItem.clone());
        }

        return x;
    }


    @Override
    public boolean replace(OqlExpr source, OqlExpr target) {
        return false;
    }
}
