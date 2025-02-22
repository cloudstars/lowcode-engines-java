package net.cf.formula.engine;

import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.identifier.FxIdentifierExpr;
import net.cf.formula.engine.ast.expr.identifier.FxNameExpr;
import net.cf.formula.engine.ast.expr.identifier.FxPropertyExpr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableValuesLoader implements VariablesValueLoader {

    @Override
    public Map<String, Object> loadValues(List<FxNameExpr> exprs) {
        Map<String, Object> values = new HashMap<>();
        for (FxNameExpr expr : exprs) {
            if (expr instanceof FxIdentifierExpr) {
                if (!values.containsKey(expr.getName())) {
                    values.put(expr.getName(), 1);
                }
            } else if (expr instanceof FxPropertyExpr) {
                Object childValue = 2;
                FxExpr pntExpr = expr;
                while (pntExpr instanceof FxPropertyExpr) {
                    Map<String, Object> dataMap = new HashMap<>();
                    String pntName = ((FxPropertyExpr) pntExpr).getName();
                    dataMap.put(pntName, childValue);
                    childValue = dataMap;
                    pntExpr = ((FxPropertyExpr) pntExpr).getOwner();
                }

                String name = ((FxNameExpr) pntExpr).getName();
                if (!values.containsKey(name)) {
                    values.put(name, childValue);
                }
            }
        }

        return values;
    }
}
