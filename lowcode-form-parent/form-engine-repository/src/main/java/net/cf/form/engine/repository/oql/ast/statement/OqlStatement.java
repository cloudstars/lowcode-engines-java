package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.ast.OqlObject;

/**
 * OQL 语句
 *
 * @author clouds
 */
@Deprecated
public interface OqlStatement<T extends OqlStatement> extends OqlObject<T> {

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

    @Override
    T clone();

}
