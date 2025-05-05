package io.github.cloudstars.lowcode.object.repository.sql.ast.expr.identifier;

import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.SqlExpr;

/**
 * 带名称的表达式
 *
 * @author clouds
 */
public interface SqlName extends SqlExpr {

    @Override
    SqlName cloneMe();

    /**
     * 获取名称
     *
     * @return
     */
    String getName();

}
