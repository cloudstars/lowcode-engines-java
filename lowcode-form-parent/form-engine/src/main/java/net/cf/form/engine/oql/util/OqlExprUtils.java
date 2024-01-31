package net.cf.form.engine.oql.util;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.oql.ast.expr.literal.OqlCharExpr;
import net.cf.form.engine.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.oql.ast.expr.literal.OqlLiteralExpr;
import net.cf.form.engine.oql.ast.expr.literal.OqlNullExpr;
import net.cf.form.engine.oql.ast.expr.operation.OqlBinaryOpExpr;

import java.util.Date;
import java.util.List;

public class OqlExprUtils {

    public OqlExprUtils() {
    }

    public static boolean equals(OqlExpr a, OqlExpr b) {
        if (a == b) {
            return true;
        } else if (a != null && b != null) {
            Class<?> clazz_a = a.getClass();
            Class<?> clazz_b = b.getClass();
            /*if (clazz_a == OqlPropertyExpr.class && clazz_b == OqlIdentifierExpr.class) {
                return ((OqlPropertyExpr) a).equals((OqlIdentifierExpr) b);
            } else if (clazz_a != clazz_b) {
                return false;
            } else if (clazz_a == OqlIdentifierExpr.class) {
                OqlIdentifierExpr x_a = (OqlIdentifierExpr) a;
                OqlIdentifierExpr x_b = (OqlIdentifierExpr) b;
                return x_a.hashCode() == x_b.hashCode();
            } else if (clazz_a == OqlBinaryOpExpr.class) {
                OqlBinaryOpExpr x_a = (OqlBinaryOpExpr) a;
                OqlBinaryOpExpr x_b = (OqlBinaryOpExpr) b;
                return x_a.equals(x_b);
            } else */
            {
                return a.equals(b);
            }
        } else {
            return false;
        }
    }

    public static boolean isLiteralExpr(OqlExpr expr) {
        if (expr instanceof OqlLiteralExpr) {
            return true;
        } else if (!(expr instanceof OqlBinaryOpExpr)) {
            return false;
        } else {
            OqlBinaryOpExpr binary = (OqlBinaryOpExpr) expr;
            return isLiteralExpr(binary.getLeft()) && isLiteralExpr(binary.getRight());
        }
    }


    /**
     * 将一个 Object 解析为一个OQL表达式
     *
     * @param value
     * @return
     */
    public static OqlExpr parseObject(Object value) {
        if (value == null) {
            return new OqlNullExpr();
        }

        if (value instanceof String || value instanceof Date) {
            return new OqlCharExpr(value.toString());
        }
        if (value instanceof Integer || value instanceof Long) {
            return new OqlIntegerExpr(value.toString());
        }

        throw new RuntimeException("暂不支持的ID类型！");
    }

    /**
     * 将一个 Object列表解析为一个OqlList表达式
     *
     * @param values
     * @return
     */
    public static OqlListExpr parseList(List<Object> values) {
        OqlListExpr listExpr = new OqlListExpr();
        for (Object value : values) {
            listExpr.addItem(parseObject(value));
        }

        return listExpr;
    }
}
