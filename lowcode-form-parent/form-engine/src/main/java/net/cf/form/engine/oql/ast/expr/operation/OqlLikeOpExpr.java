package net.cf.form.engine.oql.ast.expr.operation;

import net.cf.form.engine.oql.ast.expr.OqlExpr;

public class OqlLikeOpExpr extends OqlBinaryOpExpr {

    /**
     * 转义字符
     */
    private String escape;

    public OqlLikeOpExpr() {
    }

    public OqlLikeOpExpr(OqlExpr left, OqlExpr right) {
        super(left, OqlBinaryOperator.Like, right);
    }

    public String getEscape() {
        return escape;
    }

    public void setEscape(String escape) {
        this.escape = escape;
    }

    @Override
    public OqlLikeOpExpr clone() {
        OqlLikeOpExpr x = new OqlLikeOpExpr();
        cloneTarget(x);
        x.setEscape(this.escape);
        return x;
    }
}
