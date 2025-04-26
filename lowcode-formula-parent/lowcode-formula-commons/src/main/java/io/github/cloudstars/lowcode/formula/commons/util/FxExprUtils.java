package io.github.cloudstars.lowcode.formula.commons.util;

import io.github.cloudstars.lowcode.formula.commons.ast.expr.FxExpr;
import io.github.cloudstars.lowcode.formula.commons.ast.expr.literal.FxLiteralExpr;
import io.github.cloudstars.lowcode.formula.commons.ast.expr.operation.FxBinaryOpExpr;

public class FxExprUtils {

    public FxExprUtils() {
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
