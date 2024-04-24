package net.cf.form.repository.mongo.data.insert;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;

public class MongoInsertItem {

    private SqlIdentifierExpr column;

    private String colName;

    private SqlExpr valueExpr;

    public SqlIdentifierExpr getColumn() {
        return column;
    }

    public void setColumn(SqlIdentifierExpr column) {
        this.column = column;
    }

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
