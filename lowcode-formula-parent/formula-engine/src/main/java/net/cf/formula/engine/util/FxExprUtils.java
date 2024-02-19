package net.cf.formula.engine.util;

import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.literal.FxLiteralExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;

public class FxExprUtils {

    public FxExprUtils() {
    }

    public static boolean equals(FxExpr a, FxExpr b) {
        if (a == b) {
            return true;
        } else if (a != null && b != null) {
            /*Class<?> clazz_a = a.getClass();
            Class<?> clazz_b = b.getClass();
            if (clazz_a == FxPropertyExpr.class && clazz_b == FxIdentifierExpr.class) {
                return ((FxPropertyExpr) a).equals((FxIdentifierExpr) b);
            } else if (clazz_a != clazz_b) {
                return false;
            } else if (clazz_a == FxIdentifierExpr.class) {
                FxIdentifierExpr x_a = (FxIdentifierExpr) a;
                FxIdentifierExpr x_b = (FxIdentifierExpr) b;
                return x_a.hashCode() == x_b.hashCode();
            } else if (clazz_a == FxBinaryOpExpr.class) {
                FxBinaryOpExpr x_a = (FxBinaryOpExpr) a;
                FxBinaryOpExpr x_b = (FxBinaryOpExpr) b;
                return x_a.equals(x_b);
            } else */
            {
                return a.equals(b);
            }
        } else {
            return false;
        }
    }

    public static boolean isLiteralExpr(FxExpr expr) {
        if (expr instanceof FxLiteralExpr) {
            return true;
        } else if (!(expr instanceof FxBinaryOpExpr)) {
            return false;
        } else {
            FxBinaryOpExpr binary = (FxBinaryOpExpr) expr;
            return isLiteralExpr(binary.getLeft()) && isLiteralExpr(binary.getRight());
        }
    }

    /*public static FxExpr fromJavaObject(Object o) {
        return fromJavaObject(o, (TimeZone) null);
    }

    public static FxExpr fromJavaObject(Object o, TimeZone timeZone) {
        if (o == null) {
            return new FxNullExpr();
        } else if (o instanceof String) {
            return new FxCharExpr((String) o);
        } else if (o instanceof BigDecimal) {
            return new FxDecimalExpr((BigDecimal) o);
        } else if (!(o instanceof Byte) && !(o instanceof Short) && !(o instanceof Integer) && !(o instanceof Long) && !(o instanceof BigInteger)) {
            if (o instanceof Number) {
                return new FxNumberExpr((Number) o);
            } else if (o instanceof Date) {
                return new FxTimestampExpr((Date) o, timeZone);
            } else {
                throw new ParserException("not support class : " + o.getClass());
            }
        } else {
            return new FxIntegerExpr((Number) o);
        }
    }

    public static FxInListExpr conditionIn(String column, List<Object> values, TimeZone timeZone) {
        FxInListExpr in = new FxInListExpr();
        in.setExpr(FxUtils.toFxExpr(column));
        Iterator var4 = values.iterator();

        while (var4.hasNext()) {
            Object value = var4.next();
            in.addTarget(fromJavaObject(value, timeZone));
        }

        return in;
    }

    public static String quote(String str, DbType dbType, char quote) {
        Object expr;
        if (quote == '`') {
            expr = new FxIdentifierExpr(str);
        } else if (quote == '"') {
            if (dbType != DbType.oracle && dbType != DbType.presto && dbType != DbType.trino) {
                expr = new FxCharExpr(str);
            } else {
                expr = new FxIdentifierExpr(str);
            }
        } else {
            if (quote != '\'') {
                throw new FastFxException("quote not support");
            }

            expr = new FxCharExpr(str);
        }

        return FxUtils.toFxString((FxObject) expr, dbType);
    }

    public static FxDataType createDataTypeFromJdbc(DbType dbType, int jdbType, Integer precision, Integer scale) {
        Object dataType;
        dataType = null;
        label30:
        switch (jdbType) {
            case 4:
                if (dbType == null) {
                    return new FxDataTypeImpl("integer");
                }

                switch (dbType) {
                    case myFx:
                        return new FxDataTypeImpl("int");
                    default:
                        break label30;
                }
            case 12:
                switch (dbType) {
                    case myFx:
                        return new FxDataTypeImpl("varchar");
                }
        }

        if (dataType != null) {
            if (dbType != null) {
                ((FxDataType) dataType).setDbType(dbType);
            }

            return (FxDataType) dataType;
        } else {
            throw new FastFxException("type " + jdbType + " not support");
        }
    }*/
}
