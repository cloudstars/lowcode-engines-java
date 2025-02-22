package net.cf.object.fx.parser.ast.expr;

import net.cf.object.fx.parser.ast.FxObject;

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
