package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public class SqlUpdateSetItem extends AbstractSqlObjectImpl implements SqlReplaceable {

    private SqlExpr field;

    private SqlExpr value;

    public SqlUpdateSetItem() {
    }

    public SqlUpdateSetItem(SqlExpr field, SqlExpr value) {
        this.field = field;
        this.value = value;
    }

    public SqlExpr getField() {
        return field;
    }

    public void setField(SqlExpr field) {
        this.field = field;
        this.addChild(field);
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
        if (this.field != null) {
            x.setField(this.field.cloneMe());
        }

        if (this.value != null) {
            x.setValue(this.value.cloneMe());
        }

        return x;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.field);
            this.nullSafeAcceptChild(visitor, this.value);
        }

        visitor.endVisit(this);
    }

    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        if (source == this.field) {
            this.setField(target);
            return true;
        } else if (source == this.value) {
            this.setValue(target);
            return true;
        } else {
            return false;
        }
    }

}

