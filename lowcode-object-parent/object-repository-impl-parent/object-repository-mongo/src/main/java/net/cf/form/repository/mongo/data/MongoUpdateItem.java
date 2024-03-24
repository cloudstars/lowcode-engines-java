package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;

public class MongoUpdateItem {

    private SqlExpr fieldExpr;


    private SqlExpr valueExpr;

    private ExprTypeEnum fieldExprEnum;

    private ExprTypeEnum valueExprEnum;

    public SqlExpr getFieldExpr() {
        return fieldExpr;
    }

    public void setFieldExpr(SqlExpr fieldExpr) {
        this.fieldExpr = fieldExpr;
    }

    public ExprTypeEnum getValueExprEnum() {
        return valueExprEnum;
    }

    public void setValueExprEnum(ExprTypeEnum valueExprEnum) {
        this.valueExprEnum = valueExprEnum;
    }

    public SqlExpr getValueExpr() {
        return valueExpr;
    }

    public void setValueExpr(SqlExpr valueExpr) {
        this.valueExpr = valueExpr;
    }

    public ExprTypeEnum getFieldExprEnum() {
        return fieldExprEnum;
    }

    public void setFieldExprEnum(ExprTypeEnum fieldExprEnum) {
        this.fieldExprEnum = fieldExprEnum;
    }
}
