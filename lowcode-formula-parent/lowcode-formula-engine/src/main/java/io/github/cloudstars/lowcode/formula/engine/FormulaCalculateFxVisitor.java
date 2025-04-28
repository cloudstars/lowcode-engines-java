package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParserBaseVisitor;

/**
 * 公式计算访问器
 *
 * @author clouds
 */
public class FormulaCalculateFxVisitor extends FxParserBaseVisitor<Object> {

    @Override
    public Object visitFx(FxParser.FxContext ctx) {
        return super.visitFx(ctx);
    }

    @Override
    public Object visitLiteralExpression(FxParser.LiteralExpressionContext ctx) {
        return super.visitLiteralExpression(ctx);
    }

    @Override
    public Object visitLiteral(FxParser.LiteralContext ctx) {
        String literal = ctx.StringLiteral().getText();
        return literal;
    }

    @Override
    public Object visitNumericLiteral(FxParser.NumericLiteralContext ctx) {
        String literal = ctx.DecimalLiteral().getText();
        return Integer.valueOf(literal);
    }

}
