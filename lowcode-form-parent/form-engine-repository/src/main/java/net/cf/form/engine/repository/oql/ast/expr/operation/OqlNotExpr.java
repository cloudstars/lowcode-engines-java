package net.cf.form.engine.repository.oql.ast.expr.operation;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

import java.util.Collections;
import java.util.List;


/**
 * 非表达式
 *
 * @author clouds
 */
@Deprecated
public final class OqlNotExpr extends OqlExprImpl {

    public QqlExpr expr;

    public OqlNotExpr() {
    }

    public OqlNotExpr(QqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    public QqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(QqlExpr expr) {
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