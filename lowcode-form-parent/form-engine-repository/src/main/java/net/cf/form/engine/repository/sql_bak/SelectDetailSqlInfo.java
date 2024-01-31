package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;

@Deprecated
public class SelectDetailSqlInfo extends SelectSqlInfo implements IDetailSqlInfo<SelectSqlInfo> {

    /**
     * 主表的SQL信息
     */
    private SelectSqlInfo masterSqlInfo;

    /**
     * 子查询返回的结果的别名
     */
    private String alias;

    public SelectDetailSqlInfo(DataObject object, SelectSqlInfo masterSqlInfo) {
        super(object);
        this.masterSqlInfo = masterSqlInfo;
    }

    @Override
    public SelectSqlInfo getMasterSqlInfo() {
        return masterSqlInfo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
