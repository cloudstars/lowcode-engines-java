package net.cf.form.engine.repository.sql.ast;

import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;

/**
 * SQL 中的可替换节点
 *
 * @author clouds
 */
public interface SqlReplaceable {

    /**
     * 将源替换为目标
     *
     * @param source
     * @param target
     * @return
     */
    boolean replace(SqlExpr source, SqlExpr target);
}
