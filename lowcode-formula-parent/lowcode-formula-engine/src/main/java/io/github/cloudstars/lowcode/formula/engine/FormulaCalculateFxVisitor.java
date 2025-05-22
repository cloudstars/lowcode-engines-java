package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.commons.lang.util.CalculateUtils;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParserBaseVisitor;
import org.antlr.v4.runtime.tree.RuleNode;

import java.util.Stack;

/**
 * 公式计算访问器
 *
 * @author clouds
 */
public class FormulaCalculateFxVisitor extends FxParserBaseVisitor<Object> {

    /**
     * 访问的节点栈
     */
    private Stack<RuleNode> visitNodeStack = new Stack<>();

    public FormulaCalculateFxVisitor() {
    }

    @Override
    protected Object aggregateResult(Object aggregate, Object nextResult) {
        RuleNode topRuleNode = this.visitNodeStack.peek();
        if (topRuleNode instanceof FxParser.AdditiveExpressionContext) {
            return CalculateUtils.add(aggregate, nextResult);
        }

        return nextResult != null ? nextResult : aggregate;
    }

    @Override
    public Object visitNumericLiteral(FxParser.NumericLiteralContext ctx) {
        String text = ctx.DecimalLiteral().getText();
        Object result = CalculateUtils.parseNumber(text);
        return result;
    }

    @Override
    public Object visitChildren(RuleNode node) {
        this.visitNodeStack.push(node);
        Object result = super.visitChildren(node);
        this.visitNodeStack.pop();
        return result;
    }

}
