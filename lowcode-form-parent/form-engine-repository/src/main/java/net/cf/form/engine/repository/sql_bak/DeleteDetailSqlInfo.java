package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;

@Deprecated
public class DeleteDetailSqlInfo extends DeleteSqlInfo {

    /**
     * 主表的SQL信息
     */
    private DeleteSqlInfo masterSqlInfo;

    public DeleteDetailSqlInfo(DataObject object, DeleteSqlInfo masterSqlInfo) {
        super(object);
        this.masterSqlInfo = masterSqlInfo;
    }
    
    public DeleteSqlInfo getMasterSqlInfo() {
        return masterSqlInfo;
    }
}
