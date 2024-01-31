package net.cf.form.engine.repository.sql.ast;

import net.cf.form.engine.repository.data.DbType;
import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;

import java.util.List;

/**
 * SQL 数据类型
 *
 * 不确定这个怎么用，先保留着
 *
 * @author clouds
 */
public interface SqlDataType extends SqlObject {
    String getName();

    void setName(String var1);

    List<SqlExpr> getArguments();

    Boolean getWithTimeZone();

    void setWithTimeZone(Boolean var1);

    boolean isWithLocalTimeZone();

    void setWithLocalTimeZone(boolean var1);

    SqlDataType _clone();

    void setDbType(DbType var1);

    DbType getDbType();

    int jdbcType();

    boolean isInt();

    boolean isNumberic();

    boolean isString();

    boolean hasKeyLength();

    public interface Constants {
        String CHAR = "CHAR";
        String NCHAR = "NCHAR";
        String VARCHAR = "VARCHAR";
        String VARBINARY = "VARBINARY";
        String DATE = "DATE";
        String TIMESTAMP = "TIMESTAMP";
        String XML = "XML";
        String DECIMAL = "DECIMAL";
        String NUMBER = "NUMBER";
        String REAL = "REAL";
        String DOUBLE_PRECISION = "DOUBLE PRECISION";
        String DOUBLE = "DOUBLE";
        String TINYINT = "TINYINT";
        String SMALLINT = "SMALLINT";
        String INT = "INT";
        String BIGINT = "BIGINT";
        String TEXT = "TEXT";
        String BYTEA = "BYTEA";
        String BOOLEAN = "BOOLEAN";
        String FLOAT = "FLOAT";
    }
}

