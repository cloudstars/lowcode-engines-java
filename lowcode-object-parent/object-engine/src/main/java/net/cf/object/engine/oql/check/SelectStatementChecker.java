package net.cf.object.engine.oql.check;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * 查询语句的合法性校验器
 *
 * @author clouds
 */
public class SelectStatementChecker implements OqlAstVisitor {

    public SelectStatementChecker() {
    }

    @Override
    public boolean visit(OqlSelectStatement x) {
        SqlExpr where = x.getSelect().getWhere();
        if (where != null) {
            where.accept(new WhereClauseChecker());
        }

        return false;
    }
}
