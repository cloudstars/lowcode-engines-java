package net.cf.form.repository.sql.ast.expr.op;


import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.Collections;
import java.util.List;

/**
 * SQL exists 表达式
 *
 * @author clouds 
 */
public final class SqlExistsExpr extends AbstractNotableExpr {

    /**
     * 子查询
     */
    public SqlSelect subQuery;

    public SqlExistsExpr() {
    }

    public SqlExistsExpr(SqlSelect subQuery) {
        this.setSubQuery(subQuery);
    }

    public SqlExistsExpr(SqlSelect subQuery, boolean not) {
        this.setSubQuery(subQuery);
        this.not = not;
    }

    public SqlSelect getSubQuery() {
        return this.subQuery;
    }

    public void setSubQuery(SqlSelect subQuery) {
        if (subQuery != null) {
            subQuery.setParent(this);
        }

        this.subQuery = subQuery;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.subQuery);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<SqlObject> getChildren() {
        return Collections.singletonList(this.subQuery);
    }

    @Override
    public SqlExistsExpr cloneMe() {
        SqlExistsExpr x = new SqlExistsExpr();
        x.not = this.not;
        if (this.subQuery != null) {
            x.setSubQuery(this.subQuery.cloneMe());
        }

        return x;
    }
}
