package net.cf.form.repository.util;

import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;

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
