package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParserBaseVisitor;

public class FormulaTestFxVisitor extends FxParserBaseVisitor {

    @Override
    public Object visitFx(FxParser.FxContext ctx) {
        System.out.println("visitFx: " + System.currentTimeMillis());
        sleep();

        return super.visitFx(ctx);
    }

    @Override
    public Object visitLiteralExpression(FxParser.LiteralExpressionContext ctx) {
        System.out.println("visitLiteralExpression: " + System.currentTimeMillis());
        sleep();

        return super.visitLiteralExpression(ctx);
    }

    @Override
    public Object visitLiteral(FxParser.LiteralContext ctx) {
        System.out.println("visitLiteral: " + System.currentTimeMillis());
        sleep();

        return super.visitLiteral(ctx);
    }

    @Override
    public Object visitNumericLiteral(FxParser.NumericLiteralContext ctx) {
        System.out.println("visitNumericLiteral: " + System.currentTimeMillis());
        sleep();

        return super.visitNumericLiteral(ctx);
    }

    /**
     *
     */
    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
