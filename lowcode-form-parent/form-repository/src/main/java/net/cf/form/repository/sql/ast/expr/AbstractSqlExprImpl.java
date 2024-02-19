package net.cf.form.repository.sql.ast.expr;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;

import java.util.Collections;
import java.util.List;

/**
 * SQL 表达式实现抽象类
 *
 * @author clouds
 */
public abstract class AbstractSqlExprImpl extends AbstractSqlObjectImpl implements SqlExpr {

    public AbstractSqlExprImpl() {}

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
        return Collections.emptyList();
    }
}
