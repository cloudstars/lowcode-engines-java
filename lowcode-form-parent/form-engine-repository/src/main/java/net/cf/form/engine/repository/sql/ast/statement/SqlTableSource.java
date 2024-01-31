package net.cf.form.engine.repository.sql.ast.statement;

import net.cf.form.engine.repository.sql.ast.SqlObject;
import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;

/**
 * SQL AST 中的表
 *
 * @author clouds
 */
public interface SqlTableSource extends SqlObject {

    /**
     * 获取别名
     *
     * @return
     */
    String getAlias();

    /**
     * 设置别名
     *
     * @param alias
     */
    void setAlias(String alias);

    boolean containsAlias(String alias);

    /**
     * 获取表名
     *
     * @return
     */
    SqlExpr getFlashback();

    /**
     * 设置表名
     *
     * @param flashback
     */
    void setFlashback(SqlExpr flashback);

    @Override
    SqlTableSource _clone();

}
