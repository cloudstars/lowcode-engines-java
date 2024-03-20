package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import org.bson.Document;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.*;

public class MongoDataConverter {


    public static ObjectId convertObjectId(Object id) {
        return new ObjectId(String.valueOf(id));
    }

    public static Decimal128 convertDecimal(Object data) {
        if (data instanceof BigDecimal) {
            return new Decimal128((BigDecimal) data);
        } else if (data instanceof Number) {
            BigDecimal decimal = new BigDecimal(((Number)data).toString());
            return new Decimal128(decimal);
        }
        throw new RuntimeException("not support");
    }


    public static Object convertVariable(SqlExpr sqlExpr, Map<String, Object> varMap) {
        if (sqlExpr instanceof SqlVariantRefExpr) {
            SqlVariantRefExpr sqlVariantRefExpr = (SqlVariantRefExpr) sqlExpr;
            String var = sqlVariantRefExpr.getName();
            if ((var.startsWith("#{") || var.startsWith("${")) && var.endsWith("}")) {
                String varName = sqlVariantRefExpr.getVarName();
                return replaceVariable(varName, varMap);
            }
        }
        throw new RuntimeException("not support");
    }

    private static Object replaceVariable(String varKey, Map<String, Object> variables) {
        if (variables == null) {
            return varKey;
        }
        return variables.get(varKey);
    }

}
