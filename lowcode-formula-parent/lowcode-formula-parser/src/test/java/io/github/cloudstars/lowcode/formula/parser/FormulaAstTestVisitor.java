package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.formula.parser.g4.FxExprParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxExprParserBaseVisitor;

public class FormulaAstTestVisitor extends FxExprParserBaseVisitor {

    @Override
    public Object visitProgram(FxExprParser.ProgramContext ctx) {
        System.out.println("visitProgram: " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return super.visitProgram(ctx);
    }

    @Override
    public Object visitLiteralExpression(FxExprParser.LiteralExpressionContext ctx) {
        System.out.println("visitLiteralExpression: " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return super.visitLiteralExpression(ctx);
    }

    @Override
    public Object visitLiteral(FxExprParser.LiteralContext ctx) {
        System.out.println("visitLiteral: " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return super.visitLiteral(ctx);
    }
}
