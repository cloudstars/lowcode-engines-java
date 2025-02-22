package net.cf.formula.engine.ast.expr;

import net.cf.formula.engine.ast.FxObject;

/**
 *
 * @author clouds
 *
 * OQL 表达式
 */
public interface FxExpr extends FxObject {

    /**
     * 克隆
     *
     * @return
     */
    FxExpr clone();
}
