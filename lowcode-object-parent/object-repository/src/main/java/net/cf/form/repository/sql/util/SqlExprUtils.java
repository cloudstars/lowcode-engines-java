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
import java.sql.Time;
import java.util.Date;
import java.util.List;

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

    public static SqlExpr and(final SqlExpr a, final SqlExpr b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }


        SqlBinaryOpExprGroup group;
        SqlExpr ta = a;
        SqlExpr tb = b;
        if (ta instanceof SqlBinaryOpExprGroup) {
            group = (SqlBinaryOpExprGroup) ta;
            if (group.getOperator() == SqlBinaryOperator.BOOLEAN_AND) {
                group.addItem(tb);
                return group;
            }

            if (group.getOperator() == SqlBinaryOperator.BOOLEAN_OR && group.getItems().size() == 1) {
                ta= (group.getItems().get(0)).cloneMe();
            }
        }

        if (tb instanceof SqlBinaryOpExpr) {
            SqlBinaryOpExpr bb = (SqlBinaryOpExpr) tb;
            if (bb.getOperator() == SqlBinaryOperator.BOOLEAN_AND) {
                return and(and(ta, bb.getLeft()), bb.getRight());
            }
        } else if (tb instanceof SqlBinaryOpExprGroup) {
            group = (SqlBinaryOpExprGroup) tb;
            if (group.getOperator() == SqlBinaryOperator.BOOLEAN_OR && group.getItems().size() == 1) {
                tb = (group.getItems().get(0)).cloneMe();
            }
        }

        if (ta instanceof SqlBinaryOpExpr && tb instanceof SqlBinaryOpExprGroup && ((SqlBinaryOpExprGroup) tb).getOperator() == SqlBinaryOperator.BOOLEAN_AND) {
            group = (SqlBinaryOpExprGroup) tb;
            group.addItem(0, ta);
            return group;
        } else {
            return new SqlBinaryOpExpr(ta, SqlBinaryOperator.BOOLEAN_AND, tb);
        }
    }

    public static SqlExpr and(SqlExpr a, SqlExpr b, SqlExpr c) {
        return and(and(a, b), c);
    }

    public static SqlExpr or(SqlExpr a, SqlExpr b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            if (a instanceof SqlBinaryOpExprGroup) {
                SqlBinaryOpExprGroup group = (SqlBinaryOpExprGroup) a;
                if (group.getOperator() == SqlBinaryOperator.BOOLEAN_OR) {
                    group.addItem(b);
                    return group;
                }
            }

            if (b instanceof SqlBinaryOpExpr) {
                SqlBinaryOpExpr bb = (SqlBinaryOpExpr) b;
                if (bb.getOperator() == SqlBinaryOperator.BOOLEAN_OR) {
                    return or(or(a, bb.getLeft()), bb.getRight());
                }
            }

            return new SqlBinaryOpExpr(a, SqlBinaryOperator.BOOLEAN_OR, b);
        }
    }

    public static SqlExpr or(List<? extends SqlExpr> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            SqlExpr first = list.get(0);

            for (int i = 1; i < list.size(); ++i) {
                first = or(first, list.get(i));
            }

            return first;
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
        } else if (o instanceof Time) {
            return new SqlTimeExpr((Time) o);
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
