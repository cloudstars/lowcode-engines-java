package net.cf.object.engine.oql.check;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * 更新语句的合法性校验器
 *
 * @author clouds
 */
public class UpdateStatementChecker implements OqlAstVisitor {

    public UpdateStatementChecker() {
    }

    @Override
    public boolean visit(OqlUpdateStatement x) {
        SqlExpr where = x.getWhere();
        if (where != null) {
            where.accept(new WhereClauseChecker());
        }

        return false;
    }
}
