package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
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

    /**
     * 获取对象名
     *
     * @return
     */
    OqlExpr getFlashback();

    /**
     * 设置对象名
     *
     * @param flashback
     */
    void setFlashback(OqlExpr flashback);

    @Override
    T clone();

}
