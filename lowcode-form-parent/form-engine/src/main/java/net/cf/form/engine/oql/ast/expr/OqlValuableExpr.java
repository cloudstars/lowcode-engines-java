package net.cf.form.engine.oql.ast.expr;

/**
 * OQL 中具有值的表达式
 *
 * @author clouds
 */
public interface OqlValuableExpr extends OqlExpr {

    /**
     * 获取值
     *
     * @return
     */
    Object getValue();

}
