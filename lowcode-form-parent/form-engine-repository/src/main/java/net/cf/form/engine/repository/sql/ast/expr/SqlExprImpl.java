package net.cf.form.engine.repository.sql.ast.expr;

import net.cf.form.engine.repository.sql.ast.SqlObjectImpl;

import java.util.List;

/**
 * SQL 表达式实现抽象类
 *
 * @author clouds
 */
public abstract class SqlExprImpl extends SqlObjectImpl implements SqlExpr {

    @Override
    public SqlExpr _clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    /**
     * 不知道干啥用的，先留着
     *
     * @return
     */
    @Override
    public List<SqlExpr> getChildren() {
        return null;
    }
}
