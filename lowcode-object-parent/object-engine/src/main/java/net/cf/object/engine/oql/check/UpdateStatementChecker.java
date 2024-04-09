package net.cf.object.engine.oql.check;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;
import net.cf.object.engine.util.OqlUtils;
import net.cf.object.engine.util.XObjectUtils;

/**
 * 更新语句的合法性校验器
 *
 * @author clouds
 */
public class UpdateStatementChecker implements OqlAstVisitor {

    private boolean hasDetailSetItems;

    public UpdateStatementChecker() {
    }

    @Override
    public boolean visit(OqlUpdateStatement x) {
        this.hasDetailSetItems = OqlUtils.hasDetailSetItems(x);

        SqlExpr where = x.getWhere();
        if (where != null) {
            XObject object = x.getObjectSource().getResolvedObject();
            WhereClauseChecker checker = new WhereClauseChecker(x, object);
            where.accept(checker);

            if (XObjectUtils.hasDetailFields(object) && !checker.isHasPrimaryField()) {
                throw new FastOqlException("带子模型的删除语句必须指定主键ID为查询条件");
            }
        }

        return false;
    }
}
