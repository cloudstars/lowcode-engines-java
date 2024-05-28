package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

public class MongoDataConverter {

    private static final Logger logger = LoggerFactory.getLogger(MongoDataConverter.class);

    /**
     * @param id
     * @return
     */
    public static ObjectId convertObjectId(Object id) {
        return new ObjectId(String.valueOf(id));
    }

    /**
     * @param data
     * @return
     */
    public static Object convert(Object data) {
        if (data instanceof BigDecimal) {
            return new Decimal128((BigDecimal) data);
        } else if (data instanceof Number) {
            BigDecimal decimal = new BigDecimal(((Number) data).toString());
            return new Decimal128(decimal);
        }
        return data;
    }

    /**
     * @param sqlExpr
     * @param dataMap
     * @return
     */
    public static Object convertVariable(SqlExpr sqlExpr, Map<String, Object> dataMap) {
        if (sqlExpr instanceof SqlVariantRefExpr) {
            if (dataMap == null || dataMap.size() == 0) {
                return null;
            }
            SqlVariantRefExpr sqlVariantRefExpr = (SqlVariantRefExpr) sqlExpr;
            return replaceVariable(sqlVariantRefExpr.getVarName(), dataMap);
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

        if (varKey.contains(".")) {
            // 如果没有获取值，且含有 ".", 则进行路径解析
            String var = varKey.substring(0, varKey.indexOf("."));
            String newVarKey = varKey.substring(varKey.indexOf(".") + 1);
            if (variables.containsKey(var)) {
                if (variables.get(var) instanceof Map) {
                    return replaceVariable(newVarKey, (Map<String, Object>) variables.get(var));
                }
            }
        }
        logger.error("变量集合 : {}", variables.keySet());
        logger.error("异常变量 : {}", varKey);
        throw new RuntimeException("var error : " + varKey);
    }
}
