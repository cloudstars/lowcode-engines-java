package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.OqlObject;

/**
 * OQL 对象源
 *
 * @author clouds
 */
@Deprecated
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
    QqlExpr getFlashback();

    /**
     * 设置对象名
     *
     * @param flashback
     */
    void setFlashback(QqlExpr flashback);

    @Override
    T clone();

}
