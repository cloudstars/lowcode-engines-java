package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonArrayExpr;

import java.util.List;

@Deprecated
public class UpdateDetailSqlInfo extends UpdateSqlInfo {

    /**
     * 主表的SQL信息
     */
    private UpdateSqlInfo masterSqlInfo;

    public UpdateDetailSqlInfo(DataObject object, UpdateSqlInfo masterSqlInfo) {
        super(object);
    }

    public void addFieldsValues(OqlListExpr fields, OqlJsonArrayExpr values) {
        List<QqlExpr> items = values.getItems();
        int fieldSize = fields.getItems().size();
        for (QqlExpr item : items) {
            assert (item instanceof OqlListExpr);
            assert (fieldSize == ((OqlListExpr) item).getItems().size());
        }

        this.fields.add(fields);
        this.values.add(values);
    }

    public void addFieldsValues(OqlListExpr fields, OqlMethodInvokeExpr values) {
        assert (fields.getItems().size() == values.getArguments().size());
        this.fields.add(fields);
        this.values.add(values);
    }

    public UpdateSqlInfo getMasterSqlInfo() {
        return masterSqlInfo;
    }
}
