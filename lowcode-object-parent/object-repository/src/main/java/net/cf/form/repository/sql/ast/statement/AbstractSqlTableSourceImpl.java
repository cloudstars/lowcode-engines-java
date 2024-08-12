package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.util.SqlUtils;

public abstract class AbstractSqlTableSourceImpl extends AbstractSqlObjectImpl implements SqlTableSource {

    /**
     * 是否有"alias"关键字
     */
    protected boolean hasAliasKeyword;

    /**
     * 表的别名
     */
    protected String alias;

    public AbstractSqlTableSourceImpl() {
    }

    public AbstractSqlTableSourceImpl(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean isHasAliasKeyword() {
        return hasAliasKeyword;
    }

    @Override
    public void setHasAliasKeyword(boolean hasAliasKeyword) {
        this.hasAliasKeyword = hasAliasKeyword;
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
    public SqlTableSource cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public boolean containsAlias(String alias) {
        return SqlUtils.nameEquals(this.alias, alias);
    }

}
