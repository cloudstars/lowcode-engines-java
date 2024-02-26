package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.SqlObject;

/**
 * SQL 语句接口
 *
 * @author clouds
 */
public interface SqlStatement extends SqlObject {

    /**
     * 本语句是否在 ";" 之后
     *
     * @return
     */
    boolean isAfterSemi();

    /**
     * 设置本语句在 ";" 之后
     *
     * @param isAfterSemi
     */
    void setAfterSemi(boolean isAfterSemi);

}
