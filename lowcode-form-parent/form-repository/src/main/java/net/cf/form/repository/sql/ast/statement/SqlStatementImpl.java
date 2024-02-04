package net.cf.form.repository.sql.ast.statement;


import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.SqlObjectImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.List;

/**
 * SQL 语句抽象实现类
 *
 * @author clouds
 */
public abstract class SqlStatementImpl extends SqlObjectImpl implements SqlStatement {

    /**
     * SQL语句后是否有冒号
     */
    protected boolean afterSemi;

    public SqlStatementImpl() {
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

    protected void accept0(SqlAstVisitor v) {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    public SqlStatement _clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

}
