package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlObject;
import net.cf.form.engine.repository.oql.ast.OqlObjectImpl;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class OqlInsertInto extends OqlObjectImpl {

    protected OqlObjectSource objectSource;

    protected final List<QqlExpr> fields = new ArrayList();

    protected final List<OqlInsertValues> valuesList = new ArrayList();

    public OqlInsertInto() {
    }

    public OqlObjectSource getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(OqlObjectSource objectSource) {
        this.objectSource = objectSource;
        this.addChild(objectSource);
    }

    public List<QqlExpr> getFields() {
        return fields;
    }

    public void addFields(List<QqlExpr> fields) {
        for (QqlExpr field : fields) {
            this.addField(field);
        }
    }

    public void addField(QqlExpr field) {
        this.fields.add(field);
        this.addChild(field);
    }

    public void addField(int index, QqlExpr field) {
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
    public void accept0(OqlAstVisitor visitor) {
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
        x.setObjectSource(this.objectSource.clone());
        for (QqlExpr field : this.fields) {
            x.fields.add(field.clone());
        }
        for (OqlInsertValues valuesItem : this.valuesList) {
            x.valuesList.add(valuesItem.clone());
        }

        return x;
    }
}
