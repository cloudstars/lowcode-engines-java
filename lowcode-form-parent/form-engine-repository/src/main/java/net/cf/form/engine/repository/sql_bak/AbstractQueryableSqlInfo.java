package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.statement.OqlStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlWhereClause;

/**
 * 抽象有查询能力的SQL信息
 *
 * @author clouds
 */
@Deprecated
public abstract class AbstractQueryableSqlInfo<T extends OqlStatement, D extends AbstractQueryableSqlInfo> extends AbstractSqlInfo<T, D> {

    /**
     * 查询条件OQL
     */
    protected OqlWhereClause whereClause;

    public AbstractQueryableSqlInfo(DataObject object) {
        super(object);
    }


    public OqlWhereClause getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(OqlWhereClause whereClause) {
        this.whereClause = whereClause;
    }
}
