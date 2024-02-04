package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObject;

/**
 * OQL 对象源
 *
 * @author clouds
 */
public interface OqlObjectSource<T extends OqlObjectSource> extends OqlObject<T> {

    /**
     * 获取别名
     *
     * @return
     */
    String getAlias();

    /**
     * 设置别名
     *
     * @param alias
     */
    void setAlias(String alias);

    T _clone();

}
