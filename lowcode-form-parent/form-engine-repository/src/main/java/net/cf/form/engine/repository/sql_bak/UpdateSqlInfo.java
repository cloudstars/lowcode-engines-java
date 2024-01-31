package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateSetItem;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * 更新子表的SQL信息
 *
 * @author clouds
 */
@Deprecated
public class UpdateSqlInfo extends AbstractQueryableSqlInfo<OqlUpdateStatement, UpdateDetailSqlInfo> {

    /**
     * 本表的字段
     */
    protected final List<QqlExpr> fields = new ArrayList<>();

    /**
     * 本表的值列表
     */
    protected final List<QqlExpr> values = new ArrayList<>();

    /**
     * Where子句
     */
    //protected OqlWhereClause whereClause;

    public UpdateSqlInfo(DataObject object) {
        super(object);
    }

    /**
     * 子对象的SQL信息
     */
    //private final Map<String, UpdateDetailSqlInfo> detailSqlInfoMap = new HashMap<>();

    public void addSetItems(List<OqlUpdateSetItem> setItems) {
        for (OqlUpdateSetItem setItem : setItems) {
            this.fields.add(setItem.getField());
            this.values.add(setItem.getValue());
        }
    }

    /*public OqlWhereClause getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(OqlWhereClause whereClause) {
        this.whereClause = whereClause;
    }*/


    /*public Map<String, UpdateDetailSqlInfo> getDetailSqlInfoMap() {
        return detailSqlInfoMap;
    }

    public void addDetailSqlInfo(String detailFieldName, UpdateDetailSqlInfo detailSqlInfo) {
        this.detailSqlInfoMap.put(detailFieldName, detailSqlInfo);
    }

    public UpdateDetailSqlInfo getDetailSqlInfo(String detailFieldName) {
        return this.detailSqlInfoMap.get(detailFieldName);
    }*/

    @Override
    public OqlUpdateStatement buildStatement() {
        OqlUpdateStatement statement = new OqlUpdateStatement();
        statement.setObjectSource(new OqlExprObjectSource(this.object.getName()));
        assert (fields.size() == values.size());
        for (int i = 0; i < this.fields.size(); i++) {
            OqlUpdateSetItem setItem = new OqlUpdateSetItem();
            setItem.setField(fields.get(i));
            setItem.setValue(values.get(i));
            statement.addSetItem(setItem);
        }
        statement.setWhereClause(whereClause);
        return statement;
    }
}
