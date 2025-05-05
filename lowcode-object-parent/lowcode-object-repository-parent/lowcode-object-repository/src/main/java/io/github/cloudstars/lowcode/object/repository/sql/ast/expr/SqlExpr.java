package io.github.cloudstars.lowcode.object.repository.sql.ast.expr;


import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlDataType;
import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlObject;

/**
 *
 * SQL AST 中的表达式节点
 *
 * @author clouds
 */
public interface SqlExpr extends SqlObject {

    /**
     * 克隆
     *
     * @return SQL表达式
     */
    @Override
    SqlExpr cloneMe();

    /**
     * 获取SQL的数据类型
     *
     * @return
     */
    SqlDataType computeSqlDataType();

}
