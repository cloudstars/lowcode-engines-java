package net.cf.form.engine.oql.ast.expr.operation;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.Collections;
import java.util.List;


/**
 * 非表达式
 *
 * @author clouds
 */
public final class OqlNotExpr extends OqlExprImpl {

    public OqlExpr expr;

    public OqlNotExpr() {
    }

    public OqlNotExpr(OqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    public OqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(OqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
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
        return Collections.singletonList(this.expr);
    }

    @Override
    public OqlNotExpr clone() {
        OqlNotExpr x = new OqlNotExpr();
        if (this.expr != null) {
            x.setExpr(this.expr.clone());
        }

        return x;
    }

}