package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.SqlObjectImpl;
import net.cf.form.repository.sql.util.SqlUtils;

public abstract class SqlTableSourceImpl extends SqlObjectImpl implements SqlTableSource {

    protected String alias;

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
    public boolean containsAlias(String alias) {
        return SqlUtils.nameEquals(this.alias, alias);
    }

}
