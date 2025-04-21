package io.github.cloudstars.lowcode.expression;

import io.github.cloudstars.lowcode.expression.exception.ExpressionParseException;
import io.github.cloudstars.lowcode.expression.listener.ExpressionParseErrorListener;
import io.github.cloudstars.lowcode.expression.parser.ExprParser;
import io.github.cloudstars.lowcode.expression.parser.base.JavaScriptLexer;
import io.github.cloudstars.lowcode.expression.visitor.ExpressionVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2025/4/21
 */
public class ExpressionParser {

    /**
     * 解析js表达式 变成 groovy 表达式
     *
     * @param expr                表达式
     * @param dynamicFunctionName 动态表达式名称
     * @return
     */
    public static String parse(String expr, List<String> dynamicFunctionName) {
        if (ObjectUtils.isEmpty(expr)) {
            return expr;
        }

        JavaScriptLexer lexer = new JavaScriptLexer(CharStreams.fromString(expr));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ExpressionParseErrorListener());
        ParseTree tree;
        try {
            tree = parser.exprProgram();
            ExpressionVisitor extendVisitor = new ExpressionVisitor(dynamicFunctionName);
            return extendVisitor.visit(tree).toString();
        } catch (ExpressionParseException e) {
            throw new ExpressionParseException("表达式：'" + expr + "' 解析异常；异常信息：" + e.getMessage());
        } catch (RuntimeException e) {
            throw new ExpressionParseException("表达式：'" + expr + "' 解析异常；请检查表达式是否正确");
        }
    }
}
