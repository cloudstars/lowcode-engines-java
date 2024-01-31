package net.cf.form.engine.repository.oql.ast.expr;

import net.cf.form.engine.repository.oql.ast.OqlObject;

/**
 *
 * OQL 表达式
 *
 * @author clouds
 */
@Deprecated
public interface QqlExpr<T extends QqlExpr> extends OqlObject<T> {

    @Override
    T clone();
}
