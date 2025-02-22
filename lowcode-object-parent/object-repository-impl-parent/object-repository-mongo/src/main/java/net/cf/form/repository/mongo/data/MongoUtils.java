package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;

import java.util.Map;

public class MongoUtils {

    /**
     * 判断是否为变量形式
     *
     * @param paramMap
     * @return
     */
    public static boolean isVariableEnable(Map<String, Object> paramMap) {
        if (paramMap != null && paramMap.size() > 0) {
            return true;
        }
        return false;
    }

    public static String getOriginExprAlias(SqlExpr sqlExpr) {
        String originExpr = getOriginExpr(sqlExpr);
//        if (sqlExpr instanceof SqlCharExpr && !StringUtils.isEmpty(originExpr)) {
//            return originExpr.substring(1, originExpr.length() - 1);
//        }
        return originExpr;
    }

    public static String getOriginExpr(SqlExpr sqlExpr) {
        String originExpr = sqlExpr.toString();
        return originExpr;
    }
}
