package net.cf.form.engine.oql.ast;

import net.cf.form.engine.oql.ast.expr.OqlExpr;

/**
 * OQL 中的可替换对象
 *
 * @author clouds
 */
public interface OqlReplaceable {

    /**
     * 将源替换为目标
     *
     * @param source
     * @param target
     * @return
     */
    boolean replace(OqlExpr source, OqlExpr target);
}
