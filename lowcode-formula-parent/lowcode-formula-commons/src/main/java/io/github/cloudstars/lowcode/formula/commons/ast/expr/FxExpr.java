package io.github.cloudstars.lowcode.formula.commons.ast.expr;

import io.github.cloudstars.lowcode.formula.commons.ast.FxObject;

/**
 * 公式AST的节点
 *
 * @author clouds
 *
 */
public interface FxExpr extends FxObject {

    /**
     * 克隆
     *
     * @return
     */
    FxExpr clone();
}
