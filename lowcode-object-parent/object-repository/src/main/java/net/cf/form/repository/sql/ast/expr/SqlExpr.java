package net.cf.form.repository.sql.ast.expr;

import net.cf.form.repository.sql.ast.SqlObject;

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

}
