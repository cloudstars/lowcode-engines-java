package net.cf.form.engine.repository.oql.ast;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

/**
 * OQL 中的可替换对象
 *
 * @author clouds
 */
@Deprecated
public interface OqlReplaceable {

    /**
     * 将源替换为目标
     *
     * @param source
     * @param target
     * @return
     */
    boolean replace(QqlExpr source, QqlExpr target);
}
