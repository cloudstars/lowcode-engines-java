package net.cf.form.repository.mongo.data.select;

import net.cf.form.repository.mongo.data.ExprTypeEnum;
import net.cf.form.repository.sql.ast.expr.SqlExpr;

public class MongoSelectItem {

    private SqlExpr sqlExpr;

    private ExprTypeEnum exprEnum;

    /**
     * 别名
     */
    private String alias;

    /**
     * 原始解析得到的字段名
     */
    private String originFieldName;

    /**
     * 替换字段名
     */
    private String replaceName;

    /**
     * operation解析时上下文内使用的字段名
     */
    private String fieldName;

    private boolean isAggr = false;


    public SqlExpr getSqlExpr() {
        return sqlExpr;
    }

    public void setSqlExpr(SqlExpr sqlExpr) {
        this.sqlExpr = sqlExpr;
    }

    public ExprTypeEnum getExprEnum() {
        return exprEnum;
    }

    public void setExprEnum(ExprTypeEnum exprEnum) {
        this.exprEnum = exprEnum;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isAggr() {
        return isAggr;
    }

    public void setAggr(boolean aggr) {
        isAggr = aggr;
    }

    public String getOriginFieldName() {
        return originFieldName;
    }

    public void setOriginFieldName(String originFieldName) {
        this.originFieldName = originFieldName;
    }

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
