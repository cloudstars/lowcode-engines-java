package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * OQL查询字段
 * <p>
 * 区别于SqlSelectItem，OQL查询字段中的expr可能包含OQL相关的expr，但是SqlSelectItem中不会包含OqlXxxExpr
 *
 * 继承了OqlExpr是因为展开子句中存在别名的需要，
 * 如：object(1 as f1, f2 as fx)、field('1' as prop1, prop2 as propX)
 *
 * @author clouds
 */
public class OqlSelectItem extends AbstractOqlExprImpl {

    /**
     * 表达式
     */
    protected SqlExpr expr;

    /**
     * 别名
     */
    protected String alias;


    public OqlSelectItem() {
    }

    public OqlSelectItem(SqlExpr expr) {
        this.setExpr(expr);
    }

    public OqlSelectItem(SqlExpr expr, String alias) {
        this.setExpr(expr);
        this.alias = alias;
    }

    public SqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(SqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    protected void accept0(OqlAstVisitor v) {
        if (v.visit(this)) {
            if (this.expr != null) {
                this.expr.accept(v);
            }
        }

        v.endVisit(this);
    }

    @Override
    public OqlSelectItem cloneMe() {
        OqlSelectItem x = new OqlSelectItem();
        if (this.expr != null) {
            x.setExpr(this.expr.cloneMe());
        }
        x.setAlias(this.alias);
        return x;
    }

}
