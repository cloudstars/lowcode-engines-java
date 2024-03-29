package net.cf.object.engine.oql.check;

import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

public abstract class AbstractStatementChecker implements OqlAstVisitor {

    @Override
    public boolean visit(OqlFieldExpr x) {
        if (x.getResolvedField() == null) {
            throw new FastOqlException("OQL语句未正设置ObjectSource解析后的模型！");
        }

        return true;
    }

    @Override
    public boolean visit(OqlPropertyExpr x) {
        if (x.getResolvedProperty() == null) {
            throw new FastOqlException("OQL语句未正设置OqlPropertyExpr表达式解析后的字段属性！");
        }

        return true;
    }

    @Override
    public boolean visit(OqlObjectExpandExpr x) {
        if (x.getResolvedObjectRefField() == null) {
            throw new FastOqlException("OQL语句未正设置OqlObjectExpandExpr表达式解析后的字段！");
        }

        if (x.getResolvedRefObject() == null) {
            throw new FastOqlException("OQL语句未正设置OqlObjectExpandExpr表达式解析后的引用模型！");
        }

        return true;
    }

    @Override
    public boolean visit(OqlFieldExpandExpr x) {
        if (x.getResolvedField() == null) {
            throw new FastOqlException("OQL语句未正设置OqlFieldExpandExpr表达式解析后的字段！");
        }

        return true;
    }

    @Override
    public boolean visit(OqlExprObjectSource x) {
        if (x.getResolvedObject() == null) {
            throw new FastOqlException("OQL语句未正设置ObjectSource解析后的模型！");
        }

        return true;
    }
}
