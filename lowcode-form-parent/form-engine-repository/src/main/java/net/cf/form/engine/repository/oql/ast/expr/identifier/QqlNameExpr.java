package net.cf.form.engine.repository.oql.ast.expr.identifier;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

/**
 * 带名称的表达式
 *
 * @author clouds
 */
@Deprecated
public interface QqlNameExpr extends QqlExpr {

    @Override
    QqlNameExpr clone();

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
