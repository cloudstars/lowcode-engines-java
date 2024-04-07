package net.cf.object.engine.oql.check;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * 更新语句的合法性校验器
 *
 * @author clouds
 */
public class DeleteStatementChecker implements OqlAstVisitor {

    public DeleteStatementChecker() {
    }

    @Override
    public boolean visit(OqlDeleteStatement x) {
        SqlExpr where = x.getWhere();
        if (where != null) {
            where.accept(new WhereClauseChecker());
        }

        return false;
    }
}
