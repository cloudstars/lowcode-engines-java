package io.github.cloudstars.lowcode.object.repository.sql.ast.statement;

import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlObject;

/**
 * SQL AST 中的表
 *
 * @author clouds
 */
public interface SqlTableSource extends SqlObject {

    /**
     * 是否有"alias"关键字
     */
    boolean isHasAliasKeyword();

    /**
     * 设置是否有alias关键字
     *
     * @param hasAliasKeyword
     */
    void setHasAliasKeyword(boolean hasAliasKeyword);

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
