package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.commons.lang.util.ObjectRef;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParserBaseVisitor;

/**
 * 公式计算访问器
 *
 * @author clouds
 */
public class FormulaCalculateFxVisitor extends FxParserBaseVisitor<Object> {

    /**
     * 计算结果引用
     */
    private ObjectRef<Object> resultRef;

    public FormulaCalculateFxVisitor(ObjectRef<Object> resultRef) {
        this.resultRef = resultRef;
    }

    @Override
    protected Object aggregateResult(Object aggregate, Object nextResult) {
        if (aggregate == null && nextResult != null) {
            // 把最后的结束归并到结果中
            this.resultRef.setRef(nextResult);
        }

        return  super.aggregateResult(aggregate, nextResult);
    }

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
        return super.visitLiteral(ctx);
    }

    @Override
    public Object visitNumericLiteral(FxParser.NumericLiteralContext ctx) {
        String literal = ctx.DecimalLiteral().getText();
        return Integer.valueOf(literal);
    }

}
