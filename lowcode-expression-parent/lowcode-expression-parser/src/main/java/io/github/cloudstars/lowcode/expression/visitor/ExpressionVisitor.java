package io.github.cloudstars.lowcode.expression.visitor;

import io.github.cloudstars.lowcode.expression.enums.VariableDeclarationEnums;
import io.github.cloudstars.lowcode.expression.exception.ExpressionParseException;
import io.github.cloudstars.lowcode.expression.parser.ExprParser;
import io.github.cloudstars.lowcode.expression.parser.ExprParserBaseVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2025/4/21
 */
public class ExpressionVisitor extends ExprParserBaseVisitor<StringBuilder> {

    private final StringBuilder stringBuilder;

    private final Stack<VariableDeclarationEnums> declarationEnums;

    private static final Set<String> BLACK_LIST = new HashSet<>(8);

    private static final Map<String, Object> TERMINAL_MAP = new HashMap<>(8);

    private final Set<String> dynamicFunction = new HashSet<>();

    static {
        TERMINAL_MAP.put("===", "==");
        TERMINAL_MAP.put("!==", "!=");
        TERMINAL_MAP.put("<EOF>", "");
        TERMINAL_MAP.put("\n", "\n");
        TERMINAL_MAP.put("??", "?:");

        BLACK_LIST.add("eval");
    }

    public ExpressionVisitor() {
        stringBuilder = new StringBuilder();
        declarationEnums = new Stack<>();
    }

    public ExpressionVisitor(List<String> functionNames) {
        stringBuilder = new StringBuilder();
        declarationEnums = new Stack<>();
        dynamicFunction.addAll(functionNames);
    }

    @Override
    public StringBuilder visitIdentifier(ExprParser.IdentifierContext ctx) {
        if (BLACK_LIST.contains(ctx.getText())) {
            throw new ExpressionParseException("语法解析异常：不支持语法" + ctx.getText());
        }

        return this.stringBuilder.append(ctx.getText());
    }


    @Override
    public StringBuilder visitTerminal(TerminalNode ctx) {
        if (TERMINAL_MAP.containsKey(ctx.getText())) {
            stringBuilder.append(TERMINAL_MAP.get(ctx.getText()));
            return this.stringBuilder;
        }

        if ("{".equals(ctx.getText())) {
            if (!ObjectUtils.isEmpty(declarationEnums) && declarationEnums.peek() == VariableDeclarationEnums.OBJECT) {
                return stringBuilder.append("[");
            }
        }

        if ("}".equals(ctx.getText())) {
            if (!ObjectUtils.isEmpty(declarationEnums) && declarationEnums.peek() == VariableDeclarationEnums.OBJECT) {
                return stringBuilder.append("]");
            }
        }

        return this.stringBuilder.append(ctx.getText());
    }

    @Override
    public StringBuilder visitObjectLiteralExpression(ExprParser.ObjectLiteralExpressionContext ctx) {
        declarationEnums.push(VariableDeclarationEnums.OBJECT);
        if ("{}".equals(ctx.getText())) {
            stringBuilder.append("[:]");
        } else {
            visitChildren(ctx);
        }
        declarationEnums.pop();
        return stringBuilder;
    }

    @Override
    public StringBuilder visitArgumentsExpression(ExprParser.ArgumentsExpressionContext ctx) {
        String expression = ctx.singleExpression().getText();
        if (dynamicFunction.contains(expression)) {
            return visitChildren(ctx);
        } else {
            throw new ExpressionParseException("语法解析异常：不支持语法" + ctx.singleExpression().getText());
        }
    }

    @Override
    public StringBuilder visitOptionalChainExpression(ExprParser.OptionalChainExpressionContext ctx) {
        // 修改数组即可，数组样式a.arr?.[1] 替换成 a.arr?.getAt(1)
        int childCount = ctx.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (ctx.getChild(i) instanceof ExprParser.ArrayLiteralExpressionContext) {

                stringBuilder.append("getAt(");
                int startPos = stringBuilder.length();
                ctx.getChild(i).accept(this);
                int endPos = stringBuilder.length();
                stringBuilder.deleteCharAt(endPos - 1);
                stringBuilder.deleteCharAt(startPos);
                stringBuilder.append(")");
            } else {
                ctx.getChild(i).accept(this);
            }
        }

        return stringBuilder;
    }

    @Override
    public StringBuilder visitTemplateStringExpression(ExprParser.TemplateStringExpressionContext ctx) {
        throw new ExpressionParseException("语法解析异常: 不支持模板语法");
    }

    @Override
    public StringBuilder visitTemplateStringLiteral(ExprParser.TemplateStringLiteralContext ctx) {
        throw new ExpressionParseException("语法解析异常: 不支持模板语法");
    }

}
