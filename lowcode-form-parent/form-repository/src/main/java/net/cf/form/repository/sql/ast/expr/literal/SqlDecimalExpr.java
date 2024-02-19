package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.SqlValuableExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.math.BigDecimal;

public class SqlDecimalExpr extends AbstractSqlNumericLiteralExpr implements SqlValuableExpr {
    //public static final SqlDataType DATA_TYPE = new SqlDataTypeImpl("DECIMAL");

    //private BigDecimal value;

    private transient String literal;

    public SqlDecimalExpr() {
    }

    public SqlDecimalExpr(BigDecimal value) {
        this.setNumber(value);
    }

    public SqlDecimalExpr(String value) {
        this.number = new BigDecimal(value);
        this.literal = value;
    }

    public String getLiteral() {
        return this.literal;
    }

    @Override
    public SqlDecimalExpr _clone() {
        return new SqlDecimalExpr((BigDecimal) this.number);
    }

    public BigDecimal getValue() {
        return (BigDecimal) this.number;
    }

    public void setValue(BigDecimal value) {
        this.number = value;
    }

    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public BigDecimal getNumber() {
        return (BigDecimal) this.number;
    }

    @Override
    public void setNumber(Number number) {
        if (number == null) {
            this.setValue(null);
        } else {
            this.setValue((BigDecimal) number);
        }
    }




}
