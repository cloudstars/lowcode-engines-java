package net.cf.object.engine.oql.check;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;
import net.cf.object.engine.util.XObjectUtils;

import java.util.List;

/**
 * 更新语句的合法性校验器
 *
 * @author clouds
 */
public class DeleteStatementChecker implements OqlAstVisitor {

    /**
     * 从OQL语句中解析出来的主表记录ID的值
     */
    private List<SqlExpr> masterIdExprs;

    public DeleteStatementChecker() {
    }

    public List<SqlExpr> getMasterIdExprs() {
        return masterIdExprs;
    }

    @Override
    public boolean visit(OqlDeleteStatement x) {
        SqlExpr where = x.getWhere();
        if (where != null) {
            XObject fromObject = x.getFrom().getResolvedObject();
            WhereClauseChecker checker = new WhereClauseChecker(x, fromObject);
            where.accept(checker);

            if (XObjectUtils.hasDetailFields(fromObject) && !checker.isHasPrimaryField()) {
                throw new FastOqlException("带子模型的删除语句必须指定主键ID为查询条件");
            }

            this.masterIdExprs = checker.getPrimaryFieldValues();
        }

        return false;
    }
}
