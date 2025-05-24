package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.commons.lang.util.CalculateUtils;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectPropertyUtils;
import io.github.cloudstars.lowcode.formula.parser.g4.FxLexer;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParserBaseVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 公式计算访问器
 *
 * @author clouds
 */
public class CalculateFxVisitor extends FxParserBaseVisitor<Object> {

    /**
     * 参数Map
     */
    private Map<String, Object> dataMap;

    /**
     * 访问的规则节点栈
     */
    private Stack<RuleNode> visitRuleNodeStack = new Stack<>();

    /**
     * 访问的操作符栈
     */
    private Stack<Integer> visitOperatorStack = new Stack<>();

    /**
     * 运算符号表
     */
    private List<Integer> operators = Arrays.asList(FxLexer.Plus, FxLexer.Minus, FxLexer.Multiply, FxLexer.Divide, FxLexer.Modulus);

    public CalculateFxVisitor() {
    }

    public CalculateFxVisitor(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    protected Object aggregateResult(Object aggregate, Object nextResult) {
        RuleNode topRuleNode = this.visitRuleNodeStack.peek();
        if (!this.visitOperatorStack.isEmpty()) {
            // 最后访问的操作符
            int lastTerminalType = this.visitOperatorStack.peek();
            if (topRuleNode instanceof FxParser.AdditiveExpressionContext) {
                if (lastTerminalType == FxLexer.Plus) {
                    this.visitOperatorStack.pop();
                    return CalculateUtils.plus(aggregate, nextResult);
                }
                if (lastTerminalType == FxLexer.Minus) {
                    this.visitOperatorStack.pop();
                    return CalculateUtils.minus(aggregate, nextResult);
                }
            }
            if (topRuleNode instanceof FxParser.MultiplicativeExpressionContext) {
                if (lastTerminalType == FxLexer.Multiply) {
                    this.visitOperatorStack.pop();
                    return CalculateUtils.multiply(aggregate, nextResult);
                }
                if (lastTerminalType == FxLexer.Divide) {
                    this.visitOperatorStack.pop();
                    return CalculateUtils.divide(aggregate, nextResult);
                }
            }
            if (topRuleNode instanceof FxParser.UnaryMinusExpressionContext) {
                return CalculateUtils.negativeNumber(nextResult);
            }
        }

        return nextResult != null ? nextResult : aggregate;
    }

    @Override
    public Object visitIdentifier(FxParser.IdentifierContext ctx) {
        String variable = ctx.getText();
        Object result = ObjectPropertyUtils.getPropertyValue(dataMap, variable);
        return result;
    }

    @Override
    public Object visitNumericLiteral(FxParser.NumericLiteralContext ctx) {
        String text = ctx.DecimalLiteral().getText();
        Object result = CalculateUtils.parseNumber(text);
        return result;
    }

    @Override
    public Object visitChildren(RuleNode node) {
        this.visitRuleNodeStack.push(node);

        Object result = this.defaultResult();
        int n = node.getChildCount();

        for (int i = 0; i < n && this.shouldVisitNextChild(node, result); ++i) {
            ParseTree c = node.getChild(i);
            Object childResult = c.accept(this);
            if (c instanceof TerminalNode) {
                int ti = ((TerminalNode) c).getSymbol().getType();
                if (operators.contains(ti)) {
                    this.visitOperatorStack.push(ti);
                }
                /*if (this.lastTerminalType == FxLexer.OpenParen) {

                } else if (this.lastTerminalType == FxLexer.CloseParen) {

                }*/
            } else {
                result = this.aggregateResult(result, childResult);
            }
        }

        this.visitRuleNodeStack.pop();

        return result;
    }

}
