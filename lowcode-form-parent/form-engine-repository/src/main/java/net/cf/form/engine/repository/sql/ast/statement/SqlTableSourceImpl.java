package net.cf.form.engine.repository.sql.ast.statement;

import net.cf.form.engine.repository.sql.ast.SqlObjectImpl;
import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;
import net.cf.form.engine.repository.sql.util.SqlUtils;

public abstract class SqlTableSourceImpl extends SqlObjectImpl implements SqlTableSource {

    protected String alias;

    protected SqlExpr flashback;

    public SqlTableSourceImpl() {
    }

    public SqlTableSourceImpl(String alias) {
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public SqlTableSource _clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public SqlExpr getFlashback() {
        return this.flashback;
    }

    @Override
    public void setFlashback(SqlExpr flashback) {
        if (flashback != null) {
            flashback.setParent(this);
        }

        this.flashback = flashback;
    }

    @Override
    public boolean containsAlias(String alias) {
        return SqlUtils.nameEquals(this.alias, alias);
    }

}
