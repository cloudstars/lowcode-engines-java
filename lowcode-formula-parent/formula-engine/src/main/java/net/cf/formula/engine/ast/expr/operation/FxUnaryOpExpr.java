package net.cf.formula.engine.ast.expr.operation;

import net.cf.formula.engine.ast.expr.AbstractFxExprImpl;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.visitor.FxAstVisitor;

import java.util.Collections;
import java.util.List;

/**
 * 一元操作表达式
 *
 * @author clouds 
 */
public class FxUnaryOpExpr extends AbstractFxExprImpl {
    private FxExpr expr;
    private FxUnaryOperator operator;

    public FxUnaryOpExpr() {
    }

    public FxUnaryOpExpr(FxUnaryOperator operator, FxExpr expr) {
        this.operator = operator;
        this.setExpr(expr);
    }

    public FxUnaryOpExpr clone() {
        FxUnaryOpExpr x = new FxUnaryOpExpr();
        if (this.expr != null) {
            x.setExpr(this.expr.clone());
        }

        x.operator = this.operator;
        return x;
    }

    public FxUnaryOperator getOperator() {
        return this.operator;
    }

    public void setOperator(FxUnaryOperator operator) {
        this.operator = operator;
    }

    public FxExpr getExpr() {
        return this.expr;
    }

    public void setExpr(FxExpr expr) {
        if (expr != null) {
            expr.setParent(this);
        }

        this.expr = expr;
    }

    @Override
    public void accept(FxAstVisitor visitor) {
        if (visitor.visit(this) && this.expr != null) {
            this.expr.accept(visitor);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<FxExpr> getChildren() {
        return Collections.singletonList(this.expr);
    }
}
