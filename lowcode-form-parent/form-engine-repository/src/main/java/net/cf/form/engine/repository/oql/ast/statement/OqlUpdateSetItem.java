package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlObjectImpl;
import net.cf.form.engine.repository.oql.ast.OqlReplaceable;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

@Deprecated
public class OqlUpdateSetItem extends OqlObjectImpl implements OqlReplaceable {

    private QqlExpr field;

    private QqlExpr value;

    public OqlUpdateSetItem() {
    }

    public OqlUpdateSetItem(QqlExpr field, QqlExpr value) {
        this.field = field;
        this.value = value;
    }

    public QqlExpr getField() {
        return field;
    }

    public void setField(QqlExpr field) {
        this.field = field;
        this.addChild(field);
    }

    public QqlExpr getValue() {
        return this.value;
    }

    public void setValue(QqlExpr value) {
        this.value = value;
        this.addChild(value);
    }

    @Override
    public OqlUpdateSetItem clone() {
        OqlUpdateSetItem x = new OqlUpdateSetItem();
        if (this.field != null) {
            x.setField(this.field.clone());
        }

        if (this.value != null) {
            x.setValue(this.value.clone());
        }

        return x;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChild(visitor, this.field);
            this.acceptChild(visitor, this.value);
        }

        visitor.endVisit(this);
    }

    @Override
    public boolean replace(QqlExpr source, QqlExpr target) {
        if (source == this.field) {
            this.setField(target);
            return true;
        } else if (source == this.value) {
            this.setValue(target);
            return true;
        } else {
            return false;
        }
    }

}

