package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParserBaseVisitor;

public class FormulaTestFxVisitor extends FxParserBaseVisitor {

    @Override
    public Object visitFx(FxParser.FxContext ctx) {
        System.out.println("visitProgram: " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return super.visitFx(ctx);
    }

    @Override
    public Object visitLiteralExpression(FxParser.LiteralExpressionContext ctx) {
        System.out.println("visitLiteralExpression: " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return super.visitLiteralExpression(ctx);
    }

    @Override
    public Object visitLiteral(FxParser.LiteralContext ctx) {
        System.out.println("visitLiteral: " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return super.visitLiteral(ctx);
    }
}
