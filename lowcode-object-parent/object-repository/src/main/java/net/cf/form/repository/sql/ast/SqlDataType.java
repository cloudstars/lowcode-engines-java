package net.cf.form.repository.sql.ast;

import java.sql.Types;

/**
 * Sql数据类型
 *
 * @author clouds 
 */
public enum SqlDataType {
    CHAR(Types.CHAR),
    INTEGER(Types.INTEGER),
    BIGINT(Types.BIGINT),
    DECIMAL(Types.DECIMAL),
    TIMESTAMP(Types.TIMESTAMP),
    DATE(Types.DATE),
    TIME(Types.TIME),
    BOOLEAN(Types.BOOLEAN),
    OBJECT(Types.JAVA_OBJECT);

    private int jdbcType;

    private SqlDataType(int jdbcType) {
        this.jdbcType = jdbcType;
    }

    public int toJdbcType() {
        return this.jdbcType;
    }
}