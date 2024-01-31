package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObjectImpl;
import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.Arrays;
import java.util.List;

public final class OqlSelectOrderByItem extends OqlObjectImpl implements OqlReplaceable {

    protected OqlExpr expr;

    protected boolean ascending = true;

    //protected OqlSelectItem resolvedSelectItem;

    public OqlSelectOrderByItem() {
    }

    public OqlSelectOrderByItem(OqlExpr expr) {
        this.setExpr(expr);
    }

    public OqlSelectOrderByItem(OqlExpr expr, boolean ascending) {
        this.setExpr(expr);
        this.ascending = ascending;
    }

    public OqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(OqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public OqlSelectOrderByItem clone() {
        OqlSelectOrderByItem x = new OqlSelectOrderByItem();
        x.setExpr(this.expr.clone());
        x.setAscending(this.isAscending());
        return x;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this) && this.expr != null) {
            this.expr.accept(visitor);
        }

        visitor.endVisit(this);
    }

    @Override
    public List getChildren() {
        return Arrays.asList(this.expr);
    }

    @Override
    public boolean replace(OqlExpr source, OqlExpr target) {
        if (this.expr == source) {
            if (target instanceof OqlIntegerExpr && this.parent instanceof OqlSelectOrderBy) {
                ((OqlSelectOrderBy) this.parent).getItems().remove(this);
            }

            this.setExpr(target);
            return true;
        } else {
            return false;
        }
    }
}

