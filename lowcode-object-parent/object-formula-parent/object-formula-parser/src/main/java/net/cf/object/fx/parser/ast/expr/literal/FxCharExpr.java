package net.cf.object.fx.parser.ast.expr.literal;


import net.cf.object.fx.parser.ast.expr.FxValuableExpr;
import net.cf.object.fx.parser.ast.visitor.FxAstVisitor;

/**
 * 字符串表达式
 *
 * @author clouds
 */
public class FxCharExpr extends AbstractFxTextLiteralExpr implements FxValuableExpr {

    public FxCharExpr(String text) {
        super(text);
    }

    @Override
    public void accept(FxAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public String getValue() {
        return this.text;
    }

    @Override
    public FxCharExpr clone() {
        return new FxCharExpr(this.text);
    }
}
