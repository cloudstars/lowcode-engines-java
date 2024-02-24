package net.cf.form.repository.sql.ast.expr;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.ast.SqlDataType;

/**
 * SQL 表达式抽象实现类
 *
 * @author clouds
 */
public abstract class AbstractSqlExprImpl extends AbstractSqlObjectImpl implements SqlExpr {

    /**
     * 表达式的数据类型
     */
    protected SqlDataType dataType;

    public AbstractSqlExprImpl() {
    }

    @Override
    public SqlExpr cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public SqlDataType computeDataType() {
        return SqlDataType.NULL;
    }
}
