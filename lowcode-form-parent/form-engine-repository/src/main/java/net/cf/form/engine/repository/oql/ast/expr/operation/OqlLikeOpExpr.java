package net.cf.form.engine.repository.oql.ast.expr.operation;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

@Deprecated
public class OqlLikeOpExpr extends OqlBinaryOpExpr {

    /**
     * 转义字符
     */
    private String escape;

    public OqlLikeOpExpr() {
    }

    public OqlLikeOpExpr(QqlExpr left, QqlExpr right) {
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
