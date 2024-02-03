package net.cf.form.repository.sql.util;

import net.cf.form.repository.sql.parser.SqlExprParser;
import net.cf.form.repository.sql.parser.SqlParserFeature;

/**
 * SQL 解析工具类
 *
 * @author clouds
 */
public final class SqlParserUtils {

    private SqlParserUtils() {
    }

    public static SqlExprParser createExprParser(String sql, SqlParserFeature... features) {
        return new SqlExprParser(sql);
    }
}

