package net.cf.form.engine.oql.ast.expr;

import net.cf.form.engine.oql.ast.OqlObject;

/**
 *
 * OQL 表达式
 *
 * @author clouds
 */
public interface OqlExpr<T extends OqlExpr> extends OqlObject<T> {

    @Override
    T clone();
}
