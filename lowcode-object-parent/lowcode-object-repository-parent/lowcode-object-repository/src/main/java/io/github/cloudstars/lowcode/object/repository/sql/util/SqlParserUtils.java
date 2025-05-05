package io.github.cloudstars.lowcode.object.repository.sql.util;

import io.github.cloudstars.lowcode.object.repository.sql.parser.SqlExprParser;
import io.github.cloudstars.lowcode.object.repository.sql.parser.SqlParserFeature;

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

