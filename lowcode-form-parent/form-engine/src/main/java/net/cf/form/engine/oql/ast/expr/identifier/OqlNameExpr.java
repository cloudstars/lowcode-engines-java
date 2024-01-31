package net.cf.form.engine.oql.ast.expr.identifier;

import net.cf.form.engine.oql.ast.expr.OqlExpr;

/**
 * 带名称的表达式
 *
 * @author clouds
 */
public interface OqlNameExpr extends OqlExpr {

    @Override
    OqlNameExpr clone();

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

}
