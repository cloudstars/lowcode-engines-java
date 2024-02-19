package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.AbstractOqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

/**
 * OQL 查询的字段
 *
 * @author clouds
 */
public class OqlSelectItem extends AbstractOqlExprImpl implements OqlReplaceable {

    /**
     * 字段表达式
     */
    protected OqlExpr expr;

    /**
     * 别名
     */
    protected String alias;

    public OqlSelectItem() {
    }

    public OqlSelectItem(OqlExpr expr) {
        this(expr, null);
    }
    public OqlSelectItem(OqlExpr expr, String alias) {
        this.expr = expr;
        this.alias = alias;
        this.setExpr(expr);
    }

    public OqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(OqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    protected void accept0(OqlAstVisitor v) {
        if (v.visit(this) && this.expr != null) {
            this.expr.accept(v);
        }

        v.endVisit(this);
    }

    @Override
    public OqlSelectItem clone() {
        OqlSelectItem x = new OqlSelectItem();
        x.alias = this.alias;
        if (this.expr != null) {
            x.setExpr(this.expr.clone());
        }

        return x;
    }

    public boolean replace(OqlExpr expr, OqlExpr target) {
        if (this.expr == expr) {
            this.setExpr(target);
            return true;
        } else {
            return false;
        }
    }
}

