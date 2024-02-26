package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlDateExpr extends AbstractSqlTextLiteralExpr implements SqlValuableExpr, SqlLiteralExpr {
    //public static final SqlDataType DATA_TYPE = new SqlDataTypeImpl("date");
    private static String DATE_PATTERN = "yyyy-MM-dd";

    public SqlDateExpr() {
    }

    public SqlDateExpr(String date) {
        this.text = date;
    }

    public SqlDateExpr(Date date) {
        this.setDate(date);
    }

    @Override
    public SqlDateExpr cloneMe() {
        SqlDateExpr x = new SqlDateExpr();
        x.text = this.text;
        return x;
    }

    @Override
    public String getValue() {
        return this.text;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    public Date getDate() {
        Date date = null;
        if (this.text != null && this.text.length() > 0) {
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            try {
                date = format.parse(this.text);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return date;
    }

    public void setDate(Date x) {
        if (x == null) {
            this.text = null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            this.text = format.format(x);
        }
    }

}
