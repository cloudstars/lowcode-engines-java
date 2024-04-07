package net.cf.object.engine.oql.check;

import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlFieldExpandExpr;
import net.cf.object.engine.oql.ast.OqlObjectExpandExpr;

/**
 * Where子句的合法性校验器
 *
 * @author clouds
 */
public class WhereClauseChecker extends AbstractStatementChecker {

    public WhereClauseChecker() {
    }

    @Override
    public void endVisit(OqlObjectExpandExpr x) {
        if (x.isStarExpanded() || !x.isDefaultExpanded()) {
            throw new FastOqlException("OQL查询条件中不允许使用模型展开语法：" + x);
        }
    }

    @Override
    public void endVisit(OqlFieldExpandExpr x) {
        if (x.isStarExpanded() || !x.isDefaultExpanded()) {
            throw new FastOqlException("OQL查询条件中不允许使用字段展开语法：" + x);
        }
    }
}
