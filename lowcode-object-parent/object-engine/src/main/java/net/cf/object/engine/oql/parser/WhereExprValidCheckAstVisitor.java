package net.cf.object.engine.oql.parser;

import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlFieldExpandExpr;
import net.cf.object.engine.oql.ast.OqlObjectExpandExpr;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * Where条件检查OQL遍历器
 *
 * @author clouds
 */
public class WhereExprValidCheckAstVisitor implements OqlAstVisitor {

    @Override
    public boolean visit(OqlObjectExpandExpr x) {
        if (x.isStarExpanded() || !x.isDefaultExpanded()) {
            throw new FastOqlException("OQL的查询条件中不允许使用显式模型展开表达式：" + x);
        }

        return true;
    }

    @Override
    public boolean visit(OqlFieldExpandExpr x) {
        throw new FastOqlException("OQL的查询条件中不允许使用字段展开表达式：" + x);
    }

}
