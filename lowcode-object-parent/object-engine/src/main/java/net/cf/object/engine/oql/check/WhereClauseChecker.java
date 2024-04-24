package net.cf.object.engine.oql.check;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlFieldExpr;
import net.cf.object.engine.oql.ast.OqlObjectExpandExpr;
import net.cf.object.engine.oql.ast.OqlStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Where条件检查OQL遍历器（select、update、delete中会出现where）
 * <p>
 * update、delete如果含子表，那么主表的where条件中必须有主键列作为查询条件
 *
 * @author clouds
 */
public class WhereClauseChecker implements OqlAstVisitor {

    private final OqlStatement statement;

    private final XObject selfObject;

    /**
     * 是否含有主键 = ...的条件
     */
    private boolean hasPrimaryField;

    /**
     * 主键的值
     */
    private List<SqlExpr> primaryFieldValues = new ArrayList<>();

    public WhereClauseChecker(OqlStatement statement, XObject selfObject) {
        this.statement = statement;
        this.selfObject = selfObject;
    }

    public boolean isHasPrimaryField() {
        return hasPrimaryField;
    }

    public List<SqlExpr> getPrimaryFieldValues() {
        return primaryFieldValues;
    }

    @Override
    public boolean visit(OqlObjectExpandExpr x) {
        throw new FastOqlException("OQL的查询条件中不允许使用显式模型展开表达式：" + x);
    }

    @Override
    public boolean visit(SqlBinaryOpExpr x) {
        if (x.getOperator() == SqlBinaryOperator.EQUALITY) {
            SqlExpr left = x.getLeft();
            if (left instanceof OqlFieldExpr) {
                OqlFieldExpr fieldExpr = (OqlFieldExpr) left;
                XField resolvedField = fieldExpr.getResolvedField();
                if (this.selfObject.getPrimaryField() == resolvedField) {
                    this.hasPrimaryField = true;
                    this.primaryFieldValues.add(x.getRight());
                }
            }
        }

        return true;
    }

    @Override
    public boolean visit(SqlInListExpr x) {
        SqlExpr left = x.getLeft();
        if (left instanceof OqlFieldExpr) {
            OqlFieldExpr fieldExpr = (OqlFieldExpr) left;
            XField resolvedField = fieldExpr.getResolvedField();
            if (this.selfObject.getPrimaryField() == resolvedField) {
                this.hasPrimaryField = true;
                this.primaryFieldValues.addAll(x.getTargetList());
            }
        }
        return true;
    }
}
