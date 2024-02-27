package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.SqlObject;

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

    /**
     * 是否包含别名
     *
     * @param alias
     * @return
     */
    boolean containsAlias(String alias);

    @Override
    SqlTableSource cloneMe();

}
