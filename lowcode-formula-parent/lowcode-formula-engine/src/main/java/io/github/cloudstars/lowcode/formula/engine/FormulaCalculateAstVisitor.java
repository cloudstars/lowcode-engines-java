package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.formula.parser.g4.FxExprParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxExprParserBaseVisitor;

/**
 * 公式计算 AST 访问器
 *
 * @author clouds
 */
public class FormulaCalculateAstVisitor extends FxExprParserBaseVisitor<Object> {

    @Override
    public Object visitProgram(FxExprParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }

    @Override
    public Object visitLiteralExpression(FxExprParser.LiteralExpressionContext ctx) {
        return super.visitLiteralExpression(ctx);
    }

    @Override
    public Object visitLiteral(FxExprParser.LiteralContext ctx) {
        String literal = ctx.StringLiteral().getText();
        return literal;
    }

}
