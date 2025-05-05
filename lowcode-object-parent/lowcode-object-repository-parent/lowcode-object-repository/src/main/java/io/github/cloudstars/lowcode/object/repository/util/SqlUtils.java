package io.github.cloudstars.lowcode.object.repository.util;

import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.identifier.SqlVariantRefExpr;

public final class SqlUtils {

    private SqlUtils() {}

    /**
     * 从变量名构建一个变量引用表达式
     *
     * @param varName
     * @return
     */
    public static SqlVariantRefExpr buildSqlVariantRefExpr(String varName) {
        return new SqlVariantRefExpr("#{" + varName + "}");
    }
}
