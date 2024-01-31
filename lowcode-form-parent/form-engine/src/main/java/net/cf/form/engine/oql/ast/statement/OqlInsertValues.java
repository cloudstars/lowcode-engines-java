package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObjectImpl;
import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlInsertValues extends OqlObjectImpl implements OqlReplaceable {

    private final List<OqlExpr> values = new ArrayList<>();

    public OqlInsertValues() {
    }

    public OqlInsertValues(List<OqlExpr> values) {
        this.values.addAll(values);
    }

    public List<OqlExpr> getValues() {
        return this.values;
    }

    public void addValues(List<OqlExpr> values) {
        for (OqlExpr value : values) {
            this.addValue(value);
        }
    }

    public OqlExpr getValue(int index) {
        return this.values.get(index);
    }

    public void addValue(OqlExpr value) {
        this.values.add(value);
        this.addChild(value);
    }

    public void addValue(int index, OqlExpr value) {
        this.values.add(index, value);
        this.addChild(value);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChildren(visitor, this.values);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<OqlExpr> getChildren() {
        return this.values;
    }

    @Override
    public OqlInsertValues clone() {
        OqlInsertValues x = new OqlInsertValues();
        for (OqlExpr value : this.values) {
            x.addValue(value.clone());
        }
        return x;
    }

    @Override
    public boolean replace(OqlExpr source, OqlExpr target) {
        for (int i = 0; i < this.values.size(); ++i) {
            if (values.get(i) == source) {
                target.setParent(this);
                this.values.set(i, target);
                return true;
            }
        }

        return false;
    }
}
