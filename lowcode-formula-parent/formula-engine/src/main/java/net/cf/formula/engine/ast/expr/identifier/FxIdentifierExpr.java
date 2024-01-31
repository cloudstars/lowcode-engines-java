package net.cf.formula.engine.ast.expr.identifier;

import net.cf.formula.engine.ast.expr.FxExprImpl;
import net.cf.formula.engine.visitor.FxAstVisitor;

/**
 * 标识符
 *
 * @author clouds
 */
public class FxIdentifierExpr extends FxExprImpl implements FxNameExpr {

    protected String name;

    public FxIdentifierExpr() {
    }

    public FxIdentifierExpr(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    protected void accept0(FxAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public FxIdentifierExpr clone() {
        FxIdentifierExpr x = new FxIdentifierExpr(this.name);
        return x;
    }

}
