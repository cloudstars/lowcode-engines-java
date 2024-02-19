package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class SqlDeleteStatement extends SqlStatementImpl implements SqlStatement {

    protected SqlTableSource tableSource;

    protected SqlExpr where;

    public SqlDeleteStatement() {
    }

    public SqlTableSource getTableSource() {
        return tableSource;
    }

    public void setTableSource(SqlTableSource tableSource) {
        this.tableSource = tableSource;
        this.addChild(tableSource);
    }

    public SqlExpr getWhere() {
        return this.where;
    }

    public void setWhere(SqlExpr where) {
        if (where != null) {
            where.setParent(this);
        }

        this.where = where;
    }


    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.tableSource);
            this.nullSafeAcceptChild(visitor, this.where);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        children.add(this.tableSource);
        if (this.where != null) {
            children.add(this.where);
        }

        return children;
    }

    @Override
    public SqlDeleteStatement _clone() {
        SqlDeleteStatement statement = new SqlDeleteStatement();
        statement.setTableSource(this.tableSource._clone());
        if (this.where != null) {
            statement.setWhere(this.where._clone());
        }

        return statement;
    }
}
