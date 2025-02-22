package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.object.engine.oql.ast.OqlExpr;
import net.cf.object.engine.oql.ast.OqlFieldExpr;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.Stack;

/**
 * Where语句重排Ast遍历器
 *
 * 默认逻辑符号and，默认层级1级
 * 多个and里面出现一个or，层级加+1，a = 1 and b = c + 1 or f = length(g)
 * 多个or里面出现一个and，a = 1 or b = 2 and c = 3
 *
 *
 * @author clouds
 */
public class WhereRerangeAstVisitor implements OqlAstVisitor {

    private OqlExpr targetExpr;

    public WhereRerangeAstVisitor() {
    }

    private Stack<OqlExpr> stack = new Stack<>();


    {
        OqlExpr topExpr = stack.pop();
        while (topExpr != null) {

        }
    }

    @Override
    public boolean visit(SqlBinaryOpExpr x) {
        // a = 1 or b = 2
        SqlExpr left = x.getLeft();
        SqlBinaryOperator op = x.getOperator();
        SqlExpr right = x.getRight();
        return true;
    }

    @Override
    public boolean visit(OqlFieldExpr x) {
        return true;
    }
}
