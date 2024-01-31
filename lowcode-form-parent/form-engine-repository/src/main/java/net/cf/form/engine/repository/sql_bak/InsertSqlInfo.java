package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertInto;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertValues;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入子表的SQL信息
 *
 * @author clouds
 */
@Deprecated
public class InsertSqlInfo extends AbstractSqlInfo<OqlInsertStatement, InsertDetailSqlInfo> {

    public InsertSqlInfo(DataObject object) {
        super(object);
    }

    /**
     * 本表的字段
     */
    protected final List<QqlExpr> fields = new ArrayList<>();

    /**
     * 本表的值列表
     */
    protected final List<QqlExpr> valuesList = new ArrayList<>();

    public List<QqlExpr> getFields() {
        return fields;
    }

    public void addFields(List<QqlExpr> fields) {
        this.fields.addAll(fields);
    }

    public List<QqlExpr> getValuesList() {
        return valuesList;
    }

    public void addValuesList(List<OqlInsertValues> valuesList) {
        for (OqlInsertValues values : valuesList) {
            this.valuesList.add(new OqlListExpr(values.getValues()));
        }
    }

    /**
     * 子对象的SQL信息
     */
    /*private final Map<String, InsertDetailSqlInfo> detailSqlInfoMap = new HashMap<>();

    public Map<String, InsertDetailSqlInfo> getDetailSqlInfoMap() {
        return detailSqlInfoMap;
    }

    public void addDetailSqlInfo(String detailFieldName, InsertDetailSqlInfo detailSqlInfo) {
        this.detailSqlInfoMap.put(detailFieldName, detailSqlInfo);
    }

    public InsertDetailSqlInfo getDetailSqlInfo(String detailFieldName) {
        return this.detailSqlInfoMap.get(detailFieldName);
    }*/

    @Override
    public OqlInsertStatement buildStatement() {
        OqlInsertInto insertInto = new OqlInsertInto();
        insertInto.setObjectSource(new OqlExprObjectSource(object.getName()));
        insertInto.addFields(this.fields);
        for (QqlExpr values : valuesList) {
            insertInto.addValues(new OqlInsertValues(((OqlListExpr) values).getItems()));
        }
        return new OqlInsertStatement(insertInto);
    }
}
