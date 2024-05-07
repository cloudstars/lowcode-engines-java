package net.cf.form.repository.sql.util;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.*;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExprGroup;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.parser.SqlExprParser;
import net.cf.form.repository.sql.parser.SqlParseException;
import net.cf.form.repository.sql.parser.SqlParserFeature;
import net.cf.form.repository.sql.parser.Token;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public final class SqlExprUtils {

    private SqlExprUtils() {
    }

    public static SqlExpr toSqlExpr(String sql) {
        SqlExprParser parser = SqlParserUtils.createExprParser(sql, new SqlParserFeature[0]);
        SqlExpr expr = parser.expr();
        if (parser.getLexer().token() != Token.EOF) {
            throw new SqlParseException("illegal sql expr : " + sql + ", " + parser.getLexer().info());
        } else {
            return expr;
        }
    }

    /**
     * 两个表达式 and
     *
     * @param a
     * @param b
     * @return
     */
    public static SqlExpr and(final SqlExpr a, final SqlExpr b) {
        return SqlExprUtils.andOr(a, b, true);
    }

    /**
     * 两个表达式 or
     *
     * @param a
     * @param b
     * @return
     */
    public static SqlExpr or(SqlExpr a, SqlExpr b) {
        return SqlExprUtils.andOr(a, b, false);
    }


    private static SqlExpr andOr(SqlExpr a, SqlExpr b, boolean isAnd) {
        SqlBinaryOperator op = isAnd ? SqlBinaryOperator.BOOLEAN_AND : SqlBinaryOperator.BOOLEAN_OR;
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            if (a instanceof SqlBinaryOpExprGroup) {
                SqlBinaryOpExprGroup group = (SqlBinaryOpExprGroup) a;
                if (group.getOperator() == op) {
                    group.addItem(b);
                    return group;
                }
            }

            if (b instanceof SqlBinaryOpExprGroup) {
                SqlBinaryOpExprGroup group = (SqlBinaryOpExprGroup) b;
                if (group.getOperator() == op) {
                    group.addItem(0, a);
                    return group;
                }
            }

            return new SqlBinaryOpExpr(a, op, b);
        }
    }

    /**
     * 从Java对象转换为AST表达式对象
     *
     * @param o
     * @return
     */
    public static SqlExpr fromJavaObject(Object o) {
        if (o == null) {
            return new SqlNullExpr();
        } else if (o instanceof String) {
            return new SqlCharExpr((String) o);
        } else if (o instanceof BigDecimal) {
            return new SqlDecimalExpr((BigDecimal) o);
        } else if (o instanceof Date) {
            return new SqlDateExpr((Date) o);
        } else if (o instanceof Number) {
            if ((o instanceof Byte) || (o instanceof Short) || (o instanceof Integer) || (o instanceof Long) || o instanceof BigInteger) {
                return new SqlIntegerExpr((Number) o);
            } else {
                return new SqlDecimalExpr((Number) o);
            }
        } else {
            throw new SqlParseException("not support class : " + o.getClass());
        }
    }

}
