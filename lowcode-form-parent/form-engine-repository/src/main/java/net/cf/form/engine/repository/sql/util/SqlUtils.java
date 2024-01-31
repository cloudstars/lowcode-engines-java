package net.cf.form.engine.repository.sql.util;

import net.cf.form.engine.repository.sql.ast.SqlObject;
import net.cf.form.engine.repository.sql.ast.SqlReplaceable;
import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;
import net.cf.form.engine.repository.sql.ast.statement.*;

/**
 * Sql 工具类
 *
 * @author clouds
 */
public final class SqlUtils {

    private SqlUtils() {
    }

    public static boolean replaceInParent(SqlTableSource src, SqlTableSource dest) {
        if (src == null) {
            return false;
        } else {
            /*SqlObject parent = src.getParent();
            if (parent instanceof SqlSelectQueryBlock) {
                SqlSelectQueryBlock queryBlock = (SqlSelectQueryBlock)parent;
                if (queryBlock.getFrom() == src) {
                    queryBlock.setFrom(dest);
                    return true;
                }
            }

            if (parent instanceof SqlJoinTableSource) {
                SqlJoinTableSource join = (SqlJoinTableSource)parent;
                return join.replace(src, dest);
            } else {
                return false;
            }*/
            return false;
        }
    }


    public static boolean nameEquals(String a, String b) {
        if (a == b) {
            return true;
        } else if (a != null && b != null) {
            if (a.equalsIgnoreCase(b)) {
                return true;
            } else {
                String normalize_a = normalize(a);
                String normalize_b = normalize(b);
                return normalize_a.equalsIgnoreCase(normalize_b);
            }
        } else {
            return false;
        }
    }


    public static String normalize(String name) {
        return normalize(name);
    }

    public static String normalize(String name, boolean isTrimmed) {
        if (name == null) {
            return null;
        } else {
            if (name.length() > 2) {
                char c0 = name.charAt(0);
                char x0 = name.charAt(name.length() - 1);
                if (c0 == '[' && x0 == ']' || c0 == '"' && x0 == '"' || c0 == '`' && x0 == '`' || c0 == '\'' && x0 == '\'') {
                    String normalizeName = name.substring(1, name.length() - 1);
                    if (isTrimmed) {
                        normalizeName = normalizeName.trim();
                    }

                    int dotIndex = normalizeName.indexOf(46);
                    if (dotIndex > 0 && c0 == '`') {
                        normalizeName = normalizeName.replaceAll("`\\.`", ".");
                    }

                    return normalizeName;
                }
            }

            return name;
        }
    }

    public static boolean replaceInParent(SqlExpr expr, SqlExpr target) {
        if (expr == null) {
            return false;
        } else {
            SqlObject parent = expr.getParent();
            return parent instanceof SqlReplaceable ? ((SqlReplaceable)parent).replace(expr, target) : false;
        }
    }
}
