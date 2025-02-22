package net.cf.object.fx.parser.ast.expr;

/**
 * 公式中具有值的表达式
 *
 * @author clouds
 */
public interface FxValuableExpr extends FxExpr {

    /**
     * 获取值
     *
     * @return
     */
    Object getValue();
}
