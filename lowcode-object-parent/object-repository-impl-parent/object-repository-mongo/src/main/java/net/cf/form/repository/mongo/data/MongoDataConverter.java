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
            if (varMap == null || varMap.size() == 0) {
                return null;
            }
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
            return null;
        }
        // 如果直接能获取值，则返回
        if (variables.containsKey(varKey)) {
            return variables.get(varKey);
        }
        // 如果没有子路径，直接从变量中获取值，没有则返回空
        if (!varKey.contains(".")) {
            return variables.get(varKey);
        }
        // 如果没有获取值，且含有 ".", 则进行路径解析
        String var = varKey.substring(0, varKey.indexOf("."));
        String newVarKey = varKey.substring(varKey.indexOf(".") + 1);
        if (variables.containsKey(var)) {
            if (variables.get(var) instanceof Map) {
                return replaceVariable(newVarKey, (Map<String, Object>) variables.get(var));
            }
        }
        throw new RuntimeException("var error");
    }
}
