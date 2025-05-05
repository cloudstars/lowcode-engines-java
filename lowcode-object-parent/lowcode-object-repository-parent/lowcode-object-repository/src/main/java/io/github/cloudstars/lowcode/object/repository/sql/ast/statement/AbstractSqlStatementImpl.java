package io.github.cloudstars.lowcode.object.repository.sql.ast.statement;


import io.github.cloudstars.lowcode.object.repository.sql.ast.AbstractSqlObjectImpl;
import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlObject;
import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

import java.util.List;

/**
 * SQL 语句抽象实现类
 *
 * @author clouds
 */
public abstract class AbstractSqlStatementImpl extends AbstractSqlObjectImpl implements SqlStatement {

    /**
     * SQL语句后是否有冒号
     */
    protected boolean afterSemi;

    public AbstractSqlStatementImpl() {
    }

    public List<SqlObject> getChildren() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public boolean isAfterSemi() {
        return this.afterSemi;
    }

    @Override
    public void setAfterSemi(boolean afterSemi) {
        this.afterSemi = afterSemi;
    }

    public void accept(SqlAstVisitor v) {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    public SqlStatement cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

}
