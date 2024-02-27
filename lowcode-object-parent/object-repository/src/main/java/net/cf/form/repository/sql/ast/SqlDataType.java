package net.cf.form.repository.sql.ast;

/**
 * Sql数据类型
 *
 * @author clouds 
 */
public enum SqlDataType {
    STRING,
    INTEGER,
    DECIMAL,
    TIMESTAMP,
    DATE,
    TIME,
    BOOLEAN,
    OBJECT,
    /**
     * 对应数据库自动生成的ID，如Mongo的_id，MySQL的自增主键
     */
    AUTO_ID;
}