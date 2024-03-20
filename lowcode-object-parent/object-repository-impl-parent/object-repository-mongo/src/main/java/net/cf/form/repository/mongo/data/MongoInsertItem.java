package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;

public class MongoInsertItem {

    private String colName;

    private SqlExpr valueExpr;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public SqlExpr getValueExpr() {
        return valueExpr;
    }

    public void setValueExpr(SqlExpr valueExpr) {
        this.valueExpr = valueExpr;
    }
}
