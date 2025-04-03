package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL 查询语句
 *
 * @author clouds
 */
public class SqlSelectStatement extends AbstractSqlStatementImpl {

    protected SqlSelect select;

    public SqlSelectStatement() {
    }


    public SqlSelectStatement(SqlSelect select) {
        this.setSelect(select);
    }

    public SqlSelect getSelect() {
        return select;
    }

    public void setSelect(SqlSelect select) {
        this.select = select;
        this.addChild(select);
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.select);
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlStatement cloneMe() {
        return null;
    }
}
