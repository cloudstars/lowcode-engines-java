package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.expr.SqlExpr;

/**
 * 带名称的表达式
 *
 * @author clouds
 */
public interface SqlName extends SqlExpr {

    /**
     * 获取名称
     *
     * @return
     */
    String getSimpleName();

    @Override
    SqlName _clone();

}
