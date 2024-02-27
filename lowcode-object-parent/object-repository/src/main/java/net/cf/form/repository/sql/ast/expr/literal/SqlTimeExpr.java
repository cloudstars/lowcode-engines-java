package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlTimeExpr extends AbstractSqlTextLiteralExpr implements SqlValuableExpr {

    private static String TIME_PATTERN = "HH:mm:ss";

    public SqlTimeExpr() {
    }

    public SqlTimeExpr(String time) {
        this.text = time;
    }

    public SqlTimeExpr(Date time) {
        this.setTime(time);
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

    @Override
    public SqlTimeExpr cloneMe() {
        SqlTimeExpr x = new SqlTimeExpr();
        if (this.text != null) {
            x.setText(this.text);
        }

        return x;
    }

    public Date getTime() {
        if (this.text != null && this.text.length() > 0) {
            SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN);
            try {
                return format.parse(this.text);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public void setTime(Date x) {
        if (x == null) {
            this.text = null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN);
            this.text = format.format(x);
        }
    }
}
