package io.github.cloudstars.lowcode.object.repository.sql.ast.expr;

import io.github.cloudstars.lowcode.object.repository.sql.ast.AbstractSqlObjectImpl;
import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlDataType;

/**
 * SQL 表达式抽象实现类
 *
 * @author clouds
 */
public abstract class AbstractSqlExprImpl extends AbstractSqlObjectImpl implements SqlExpr {

    private SqlDataType sqlDataType;

    public AbstractSqlExprImpl() {
    }

    @Override
    public SqlExpr cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    public void setSqlDataType(SqlDataType sqlDataType) {
        this.sqlDataType = sqlDataType;
    }

    @Override
    public SqlDataType computeSqlDataType() {
        return this.sqlDataType;
    }
}
