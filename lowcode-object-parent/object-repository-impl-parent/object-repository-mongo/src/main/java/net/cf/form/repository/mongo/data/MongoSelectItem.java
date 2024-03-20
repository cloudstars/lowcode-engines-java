package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;

public class MongoSelectItem {

    private SqlExpr sqlExpr;

    private ExprTypeEnum exprEnum;

    private String alias;

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
}
