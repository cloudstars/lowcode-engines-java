package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlReplaceable;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

/**
 * OQL 查询的字段
 *
 * @author clouds
 */
@Deprecated
public class OqlSelectItem extends OqlExprImpl implements OqlReplaceable {

    /**
     * 字段表达式
     */
    protected QqlExpr expr;

    /**
     * 别名
     */
    protected String alias;

    public OqlSelectItem() {
    }

    public OqlSelectItem(QqlExpr expr) {
        this(expr, null);
    }
    public OqlSelectItem(QqlExpr expr, String alias) {
        this.expr = expr;
        this.alias = alias;
        this.setExpr(expr);
    }

    public QqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(QqlExpr expr) {
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
        if (v.visit(this) && this.expr != null) {
            this.expr.accept(v);
        }

        v.endVisit(this);
    }

    @Override
    public OqlSelectItem clone() {
        OqlSelectItem x = new OqlSelectItem();
        x.alias = this.alias;
        if (this.expr != null) {
            x.setExpr(this.expr.clone());
        }

        return x;
    }

    public boolean replace(QqlExpr expr, QqlExpr target) {
        if (this.expr == expr) {
            this.setExpr(target);
            return true;
        } else {
            return false;
        }
    }
}

