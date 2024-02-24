package net.cf.form.repository.sql.ast;

/**
 * Sql数据类型
 *
 * @author clouds 
 */
public enum SqlDataType {
    NULL,
    STRING,
    NUMBER,
    DATE,
    TIME,
    BOOLEAN,
    ARRAY,
    /**
     * 对应数据库自动生成的ID，如Mongo的_id，MySQL的自增主键
     */
    OBJECT_ID;
}