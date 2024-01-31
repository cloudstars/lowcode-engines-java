package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;

@Deprecated
public class DeleteSqlInfo extends AbstractQueryableSqlInfo<OqlDeleteStatement, DeleteDetailSqlInfo> {

    public DeleteSqlInfo(DataObject object) {
        super(object);
    }

    @Override
    public OqlDeleteStatement buildStatement() {
        return null;
    }
}
