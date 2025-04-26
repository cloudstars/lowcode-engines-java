package io.github.cloudstars.lowcode.formula.commons.ast.expr.identifier;

import io.github.cloudstars.lowcode.formula.commons.ast.expr.FxExpr;

/**
 * 带名称的表达式
 *
 * @author clouds
 */
public interface FxNameExpr extends FxExpr {

    /**
     * 获取名称
     *
     * @return
     */
    String getName();

    /**
     * 设置名称
     */
    void setName(String name);

    /**
     * 克隆
     *
     * @return
     */
    FxNameExpr clone();
}
