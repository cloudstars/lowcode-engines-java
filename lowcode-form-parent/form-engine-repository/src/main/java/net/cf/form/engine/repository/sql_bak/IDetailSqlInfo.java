package net.cf.form.engine.repository.sql_bak;

@Deprecated
public interface IDetailSqlInfo<T extends AbstractSqlInfo> {

    T getMasterSqlInfo();
}
