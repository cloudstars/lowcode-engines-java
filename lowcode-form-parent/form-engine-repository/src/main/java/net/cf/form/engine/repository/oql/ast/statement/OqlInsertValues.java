package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlObjectImpl;
import net.cf.form.engine.repository.oql.ast.OqlReplaceable;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class OqlInsertValues extends OqlObjectImpl implements OqlReplaceable {

    private final List<QqlExpr> values = new ArrayList<>();

    public OqlInsertValues() {
    }

    public OqlInsertValues(List<QqlExpr> values) {
        this.values.addAll(values);
    }

    public List<QqlExpr> getValues() {
        return this.values;
    }

    public void addValues(List<QqlExpr> values) {
        for (QqlExpr value : values) {
            this.addValue(value);
        }
    }

    public QqlExpr getValue(int index) {
        return this.values.get(index);
    }

    public void addValue(QqlExpr value) {
        this.values.add(value);
        this.addChild(value);
    }

    public void addValue(int index, QqlExpr value) {
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
    public List<QqlExpr> getChildren() {
        return this.values;
    }

    @Override
    public OqlInsertValues clone() {
        OqlInsertValues x = new OqlInsertValues();
        for (QqlExpr value : this.values) {
            x.addValue(value.clone());
        }
        return x;
    }

    @Override
    public boolean replace(QqlExpr source, QqlExpr target) {
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
