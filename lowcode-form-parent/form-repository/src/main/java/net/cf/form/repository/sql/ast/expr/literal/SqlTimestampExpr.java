package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlTimestampExpr extends AbstractSqlTextLiteralExpr implements SqlValuableExpr {

    private static String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public SqlTimestampExpr() {
    }

    public SqlTimestampExpr(String timestamp) {
        this.text = timestamp;
    }

    public SqlTimestampExpr(Date timestamp) {
        this.setTimestamp(timestamp);
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
    public SqlTimestampExpr cloneMe() {
        SqlTimestampExpr x = new SqlTimestampExpr();
        if (this.text != null) {
            x.setText(this.text);
        }

        return x;
    }

    public Date getTimestamp() {
        if (this.text != null && this.text.length() > 0) {
            SimpleDateFormat format = new SimpleDateFormat(TIMESTAMP_PATTERN);
            try {
                return format.parse(this.text);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public void setTimestamp(Date x) {
        if (x == null) {
            this.text = null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(TIMESTAMP_PATTERN);
            this.text = format.format(x);
        }
    }


}
