package net.cf.form.engine.repository.sql.ast.expr;

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

}
