package net.cf.form.engine.repository.oql.ast.expr;

/**
 * OQL 中具有值的表达式
 *
 * @author clouds
 */
@Deprecated
public interface QqlValuableExpr extends QqlExpr {

    /**
     * 获取值
     *
     * @return
     */
    Object getValue();

}
