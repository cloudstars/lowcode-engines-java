package net.cf.form.engine.repository.sql.util;

import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;
import net.cf.form.engine.repository.sql.ast.expr.operation.SqlBinaryOpExpr;
import net.cf.form.engine.repository.sql.ast.expr.operation.SqlBinaryOpExprGroup;
import net.cf.form.engine.repository.sql.ast.expr.operation.SqlBinaryOperator;
import net.cf.form.engine.repository.sql.parser.ParserException;
import net.cf.form.engine.repository.sql.parser.SqlExprParser;
import net.cf.form.engine.repository.sql.parser.SqlParserFeature;
import net.cf.form.engine.repository.sql.parser.Token;

import java.util.List;

public final class SqlExprUtils {

    private SqlExprUtils() {
    }

    public static SqlExpr toSqlExpr(String sql) {
        SqlExprParser parser = SqlParserUtils.createExprParser(sql, new SqlParserFeature[0]);
        SqlExpr expr = parser.expr();
        if (parser.getLexer().token() != Token.EOF) {
            throw new ParserException("illegal sql expr : " + sql + ", " + parser.getLexer().info());
        } else {
            return expr;
        }
    }

    public static SqlExpr and(SqlExpr a, SqlExpr b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            SqlBinaryOpExprGroup group;
            if (a instanceof SqlBinaryOpExprGroup) {
                group = (SqlBinaryOpExprGroup) a;
                if (group.getOperator() == SqlBinaryOperator.BooleanAnd) {
                    group.add(b);
                    return group;
                }

                if (group.getOperator() == SqlBinaryOperator.BooleanOr && group.getItems().size() == 1) {
                    a = (group.getItems().get(0))._clone();
                }
            }

            if (b instanceof SqlBinaryOpExpr) {
                SqlBinaryOpExpr bb = (SqlBinaryOpExpr) b;
                if (bb.getOperator() == SqlBinaryOperator.BooleanAnd) {
                    return and(and(a, bb.getLeft()), bb.getRight());
                }
            } else if (b instanceof SqlBinaryOpExprGroup) {
                group = (SqlBinaryOpExprGroup) b;
                if (group.getOperator() == SqlBinaryOperator.BooleanOr && group.getItems().size() == 1) {
                    b = (group.getItems().get(0))._clone();
                }
            }

            if (a instanceof SqlBinaryOpExpr && b instanceof SqlBinaryOpExprGroup && ((SqlBinaryOpExprGroup) b).getOperator() == SqlBinaryOperator.BooleanAnd) {
                group = (SqlBinaryOpExprGroup) b;
                group.add(0, a);
                return group;
            } else {
                return new SqlBinaryOpExpr(a, SqlBinaryOperator.BooleanAnd, b);
            }
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
                if (group.getOperator() == SqlBinaryOperator.BooleanOr) {
                    group.add(b);
                    return group;
                }
            }

            if (b instanceof SqlBinaryOpExpr) {
                SqlBinaryOpExpr bb = (SqlBinaryOpExpr) b;
                if (bb.getOperator() == SqlBinaryOperator.BooleanOr) {
                    return or(or(a, bb.getLeft()), bb.getRight());
                }
            }

            return new SqlBinaryOpExpr(a, SqlBinaryOperator.BooleanOr, b);
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

}
