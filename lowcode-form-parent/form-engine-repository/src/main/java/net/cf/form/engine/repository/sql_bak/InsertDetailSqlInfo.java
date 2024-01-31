package net.cf.form.engine.repository.sql_bak;

import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonArrayExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertInto;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertValues;

import java.util.List;

/**
 * 插入子表的SQL信息
 *
 * 如果主表的OQL语句的values中有多条，比如：values 格式：(f1, f2, [(d1, d2), (d1, d2), ...], f3), (f1, f2, detail(#{d1}, #{d2}), f3)
 * 那么子表中的值可能是一个JsonArray，如：[(d1, d2), (d1, d2), ...] 或者是一个方法调用，如：detail(#{d1}, #{d2})
 *
 * @author clouds
 */
@Deprecated
public class InsertDetailSqlInfo extends InsertSqlInfo {

    /**
     * 主表的SQL信息
     */
    private InsertSqlInfo masterOqlInfo;

    public InsertDetailSqlInfo(DataObject object, InsertSqlInfo masterOqlInfo) {
        super(object);
        this.masterOqlInfo = masterOqlInfo;
    }

    public InsertSqlInfo getMasterOqlInfo() {
        return masterOqlInfo;
    }

    /**
     * 添加常量的子表格式的值
     *
     * @param values
     */
    public void addValues(OqlJsonArrayExpr values) {
        List<QqlExpr> items = values.getItems();
        for (QqlExpr item : items) {
            assert (item instanceof OqlListExpr);
        }

        this.valuesList.add(values);
    }

    /**
     * 添加方法调用的子表格式的值
     *
     * @param values
     */
    public void addValues(OqlMethodInvokeExpr values) {
        this.valuesList.add(values);
    }

    @Override
    public OqlInsertStatement buildStatement() {
        OqlInsertInto insertInto = new OqlInsertInto();
        insertInto.setObjectSource(new OqlExprObjectSource(object.getName()));
        insertInto.addFields(this.fields);
        for (QqlExpr values : valuesList) {
            if (values instanceof OqlJsonArrayExpr) {
                List<QqlExpr> items = ((OqlJsonArrayExpr) values).getItems();
                for (QqlExpr item : items) {
                    insertInto.addValues(new OqlInsertValues(((OqlListExpr) item).getItems()));
                }
            } else if (values instanceof OqlMethodInvokeExpr) {
                insertInto.addValues(new OqlInsertValues(((OqlMethodInvokeExpr) values).getArguments()));
            }
        }
        return new OqlInsertStatement(insertInto);
    }
}
