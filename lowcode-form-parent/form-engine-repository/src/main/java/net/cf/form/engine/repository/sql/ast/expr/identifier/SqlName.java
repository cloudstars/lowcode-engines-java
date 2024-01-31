package net.cf.form.engine.repository.sql.ast.expr.identifier;

import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;

/**
 * 带名称的表达式
 *
 * @author clouds
 */
public interface SqlName extends SqlExpr {

    @Override
    SqlName _clone();

    /**
     * 获取名称
     *
     * @return
     */
    String getSimpleName();

}
