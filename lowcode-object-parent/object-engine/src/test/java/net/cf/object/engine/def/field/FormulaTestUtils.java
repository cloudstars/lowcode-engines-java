package net.cf.object.engine.def.field;

import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(JUnit4ClassRunner.class)
public class FormulaTestUtils {

    private static final ExpressionParser EXPR_PARSER = new SpelExpressionParser();

    private static final Map<String, Expression> EXPRESSION_CACHE = new ConcurrentHashMap<>();

    private FormulaTestUtils() {}

    /**
     * 计算一个公式的值
     *
     * @param formula
     * @return
     */
    public static Object eval(final String formula) {
        return eval(formula, new HashMap<>());
    }

    /**
     * 计算一个公式的值
     *
     * @param formula
     * @param paramMap
     * @return
     */
    public static Object eval(final String formula, Map<String, Object> paramMap) {
        String evalFormula = formula;
        if (evalFormula == null || (evalFormula = evalFormula.trim()).length() == 0) {
            return null;
        }

        Expression expr = EXPRESSION_CACHE.get(evalFormula);
        if (expr == null) {
            expr = EXPR_PARSER.parseExpression(evalFormula);
            EXPRESSION_CACHE.put(evalFormula, expr);
        }

        EvaluationContext context = new StandardEvaluationContext();
        paramMap.forEach((key, value) -> {
            context.setVariable(key, value);
        });
        return expr.getValue(context);
    }

}
