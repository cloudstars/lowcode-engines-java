package net.cf.formula.engine.builder;

import net.cf.formula.engine.ast.expr.FxExpr;

/**
 * 表达式构建器接口
 *
 * @author clouds
 */
public interface ExprBuilder {

    /**
     * 在原表达式上添加一个或逻辑
     *
     * @param expr
     * @return
     */
    ExprBuilder or(FxExpr expr);

    /**
     * 在原表达式上添加一个与逻辑
     *
     * @param expr
     * @return
     */
    ExprBuilder and(FxExpr expr);

    /**
     * 构建表达式
     *
     * @return
     */
    FxExpr builer();
}
