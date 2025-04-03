package net.cf.formula.engine.ast.expr;

import net.cf.formula.engine.visitor.FxAstVisitor;

import java.util.Collections;
import java.util.List;


/**
 * 非表达式
 *
 * @author clouds
 */
@Deprecated
public final class FxNotExpr extends AbstractFxExprImpl {
    public FxExpr expr;

    public FxNotExpr() {
    }

    public FxNotExpr(FxExpr expr) {
        if (expr != null) {
            expr.setParent(this);
        }

        this.expr = expr;
    }

    public FxExpr getExpr() {
        return this.expr;
    }

    public void setExpr(FxExpr x) {
        if (x != null) {
            x.setParent(this);
        }

        this.expr = x;
    }

    @Override
    public void accept(FxAstVisitor visitor) {
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
    public FxNotExpr clone() {
        FxNotExpr x = new FxNotExpr();
        if (this.expr != null) {
            x.setExpr(this.expr.clone());
        }

        return x;
    }

}