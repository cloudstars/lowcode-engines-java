package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.SqlExpr;

/**
 * SQL 中具有值的节点
 *
 * @author clouds
 */
public interface SqlValuableExpr extends SqlExpr {

    /**
     * 获取值
     *
     * @return
     */
    Object getValue();

    /**
     * 克隆
     *
     * @return
     */
    SqlValuableExpr cloneMe();

}
