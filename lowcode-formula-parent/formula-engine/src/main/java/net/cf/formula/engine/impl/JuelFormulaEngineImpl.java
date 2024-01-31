package net.cf.formula.engine.impl;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.identifier.FxNameExpr;
import net.cf.formula.engine.parser.FxExprParser;
import net.cf.formula.engine.FormulaEngine;
import net.cf.formula.engine.VariablesValueLoader;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JuelFormulaEngineImpl implements FormulaEngine {

    private VariablesValueLoader valuesLoader;

    private static Map<String, Method> functions = new HashMap<>();

    public JuelFormulaEngineImpl(VariablesValueLoader valuesLoader) {
        this.valuesLoader = valuesLoader;
    }

    public static void registerMethod(String name, Method method) {
        functions.put(name, method);
    }

    @Override
    public Object getValue(String fx) {
        FxExprParser parser = new FxExprParser(fx);
        FxExpr expr = parser.expr();

        StringBuilder strBuilder = new StringBuilder();
        FxAstJuelOutputVisitor visitor = new FxAstJuelOutputVisitor(strBuilder);
        expr.accept(visitor);
        String juelExpr = strBuilder.toString();
        List<FxNameExpr> nameExprs = visitor.getNameExprs();
        Map<String, Object> values = valuesLoader.loadValues(nameExprs);

        Object result = eval(juelExpr, values);
        return result;
    }

    private Object eval(String expression, Map<String, Object> values) {
        ExpressionFactory factory = new ExpressionFactoryImpl();
        SimpleContext context = new SimpleContext();

        if (!values.isEmpty()) {
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                context.setVariable(entry.getKey(), factory.createValueExpression(entry.getValue(), Object.class));
            }
        }

        if (!functions.isEmpty()) {
            for (Map.Entry<String, Method> entry : functions.entrySet()) {
                context.setFunction("fx", entry.getKey(), entry.getValue());
            }
        }

        ValueExpression ve = factory.createValueExpression(context, "${" + expression + "}", Object.class);
        return ve.getValue(context);
    }
}
