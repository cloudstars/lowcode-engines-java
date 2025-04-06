package net.cf.formula.engine;

import net.cf.formula.engine.ast.expr.identifier.FxNameExpr;

import java.util.List;
import java.util.Map;

/**
 * 变量值加载器
 *
 * @author clouds
 */
public interface VariablesValueLoader {

    /**
     * 加载变量的值
     *
     * @param exprs
     * @return
     */
    Map<String, Object> loadValues(List<FxNameExpr> exprs);
}
