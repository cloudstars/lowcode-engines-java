package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public class SqlUpdateSetItem extends AbstractSqlObjectImpl implements SqlReplaceable {

    private SqlExpr column;

    private SqlExpr value;

    public SqlUpdateSetItem() {
    }

    public SqlUpdateSetItem(SqlExpr column, SqlExpr value) {
        this.column = column;
        this.value = value;
    }

    public SqlExpr getColumn() {
        return column;
    }

    public void setColumn(SqlExpr column) {
        this.column = column;
        this.addChild(column);
    }

    public SqlExpr getValue() {
        return this.value;
    }

    public void setValue(SqlExpr value) {
        this.value = value;
        this.addChild(value);
    }

    @Override
    public SqlUpdateSetItem cloneMe() {
        SqlUpdateSetItem x = new SqlUpdateSetItem();
        if (this.column != null) {
            x.setColumn(this.column.cloneMe());
        }

        if (this.value != null) {
            x.setValue(this.value.cloneMe());
        }

        return x;
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.column);
            this.nullSafeAcceptChild(visitor, this.value);
        }

        visitor.endVisit(this);
    }

    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        if (source == this.column) {
            this.setColumn(target);
            return true;
        } else if (source == this.value) {
            this.setValue(target);
            return true;
        } else {
            return false;
        }
    }

}

