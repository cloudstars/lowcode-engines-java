package net.cf.form.repository.testcases.statement.util;

/**
 * SQL 工具类
 *
 * @author clouds
 */
public final class SqlUtils {

    private SqlUtils() {}

    /**
     * 比较两个SQL是否相等
     *
     * @param sql1
     * @param sql2
     * @return
     */
    public static boolean equals(String sql1, String sql2) {
        if (sql1 != null) {
            if (sql2 != null) {
                String s1 = sql1.replaceAll("[\\s\\r\\n\\t;]+", "");
                String s2 = sql2.replaceAll("[\\s\\r\\n\\t;]+", "");
                return s1.equalsIgnoreCase(s2);
            } else {
                return false;
            }
        } else {
            return sql2 == null;
        }
    }
}
