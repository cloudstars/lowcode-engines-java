package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.math.BigDecimal;

/**
 * 数字类表达式
 *
 * @author clouds
 */
public class SqlNumberExpr extends AbstractSqlNumericLiteralExpr implements SqlValuableExpr {

    private char[] chars;

    public SqlNumberExpr() {
    }

    public SqlNumberExpr(Number number) {
        super(number);
    }

    public SqlNumberExpr(char[] chars) {
        this.chars = chars;
    }

    /**
     * 获取字面量
     *
     * @return
     */
    public String getLiteral() {
        return this.chars == null ? null : new String(this.chars);
    }

    @Override
    public Number getValue() {
        return this.getNumber();
    }

    @Override
    public Number getNumber() {
        if (this.chars != null && this.number == null) {
            boolean exp = false;

            for (int i = 0; i < this.chars.length; ++i) {
                char ch = this.chars[i];
                if (ch == 'e' || ch == 'E') {
                    exp = true;
                }
            }

            if (exp) {
                this.number = Double.parseDouble(new String(this.chars));
            } else {
                this.number = new BigDecimal(this.chars);
            }
        }

        return this.number;
    }

    @Override
    public void setNumber(Number number) {
        super.setNumber(number);
        this.chars = null;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlNumberExpr cloneMe() {
        SqlNumberExpr x = new SqlNumberExpr();
        x.number = this.number;
        return x;
    }

}
