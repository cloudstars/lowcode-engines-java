package net.cf.form.repository.sql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public final class SqlLimit extends AbstractSqlObjectImpl implements SqlReplaceable {
    private SqlExpr rowCount;
    private SqlExpr offset;

    public SqlLimit() {
    }

    public SqlLimit(int rowCount) {
        this.setRowCount(new SqlIntegerExpr(rowCount));
    }

    public SqlLimit(SqlExpr rowCount) {
        this.setRowCount(rowCount);
    }

    public SqlLimit(SqlExpr offset, SqlExpr rowCount) {
        this.setOffset(offset);
        this.setRowCount(rowCount);
    }

    public SqlExpr getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(SqlExpr rowCount) {
        if (rowCount != null) {
            rowCount.setParent(this);
        }

        this.rowCount = rowCount;
    }

    public void setRowCount(int rowCount) {
        this.setRowCount(new SqlIntegerExpr(rowCount));
    }

    public SqlExpr getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.setOffset(new SqlIntegerExpr(offset));
    }

    public void setOffset(SqlExpr offset) {
        if (offset != null) {
            offset.setParent(this);
        }

        this.offset = offset;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.offset != null) {
                this.offset.accept(visitor);
            }

            if (this.rowCount != null) {
                this.rowCount.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlLimit cloneMe() {
        SqlLimit x = new SqlLimit();
        if (this.offset != null) {
            x.setOffset(this.offset.cloneMe());
        }

        if (this.rowCount != null) {
            x.setRowCount(this.rowCount.cloneMe());
        }

        return x;
    }

    @Override
    public boolean replace(SqlExpr expr, SqlExpr target) {
        if (this.rowCount == expr) {
            this.setRowCount(target);
            return true;
        } else if (this.offset == expr) {
            this.setOffset(target);
            return true;
        } else {
            return false;
        }
    }

}
