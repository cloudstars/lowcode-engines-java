package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.AbstractOqlObjectImpl;
import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

public class OqlUpdateSetItem extends AbstractOqlObjectImpl implements OqlReplaceable {

    private OqlExpr field;

    private OqlExpr value;

    public OqlUpdateSetItem() {
    }

    public OqlUpdateSetItem(OqlExpr field, OqlExpr value) {
        this.field = field;
        this.value = value;
    }

    public OqlExpr getField() {
        return field;
    }

    public void setField(OqlExpr field) {
        this.field = field;
        this.addChild(field);
    }

    public OqlExpr getValue() {
        return this.value;
    }

    public void setValue(OqlExpr value) {
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
    public boolean replace(OqlExpr source, OqlExpr target) {
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

