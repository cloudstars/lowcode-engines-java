package net.cf.form.engine.oql.ast.statement;

import net.cf.form.repository.sql.ast.SqlObject;

/**
 * OQL 语句
 *
 * @author clouds
 */
public interface OqlStatement extends SqlObject {

    /**
     * 本语句是否在 ";" 之后
     *
     * @return
     */
    boolean isAfterSemi();

    /**
     * 设置本语句在 ";" 之后
     *
     * @param var1
     */
    void setAfterSemi(boolean var1);

    /**
     * 克隆自已
     *
     * @return
     */
    @Override
    OqlStatement cloneMe();

}
