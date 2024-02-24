package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * 数字类表达式
 *
 * @author clouds
 */
public class SqlNumberExpr extends AbstractSqlNumericLiteralExpr implements SqlValuableExpr {

    public SqlNumberExpr() {
    }

    public SqlNumberExpr(Number number) {
        super(number);
    }

    @Override
    public Number getValue() {
        return this.getNumber();
    }

    @Override
    public Number getNumber() {
        return this.number;
    }

    @Override
    public void setNumber(Number number) {
        super.setNumber(number);
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
