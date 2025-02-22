package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;

/**
 *
 * OQL AST 中的表达式节点
 *
 * @author clouds
 */
public interface OqlExpr extends SqlExpr {

    /**
     * 克隆
     *
     * @return SQL表达式
     */
    @Override
    OqlExpr cloneMe();
}
