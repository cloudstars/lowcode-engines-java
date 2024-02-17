package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

/**
 * 表达式形式的对象源
 *
 * @author clouds
 */
public class OqlExprObjectSource extends OqlObjectSourceImpl implements OqlReplaceable {

    protected OqlExpr expr;

    protected XObject resolvedObject;

    public OqlExprObjectSource() {
    }

    public OqlExprObjectSource(OqlExpr expr) {
        this(expr, null);
    }

    public OqlExprObjectSource(String objectName) {
        this(new OqlIdentifierExpr(objectName), null);
    }

    public OqlExprObjectSource(String objectName, String alias) {
        super(new OqlIdentifierExpr(objectName), alias);
    }

    public OqlExprObjectSource(OqlExpr expr, String alias) {
        this.setExpr(expr);
        this.setAlias(alias);
    }

    public OqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(OqlExpr x) {
        if (x != null) {
            x.setParent(this);
        }

        this.expr = x;
    }

    public XObject getResolvedObject() {
        return resolvedObject;
    }

    public void setResolvedObject(XObject resolvedObject) {
        this.resolvedObject = resolvedObject;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.expr != null) {
                this.expr.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }


    @Override
    public OqlExprObjectSource _clone() {
        OqlExprObjectSource x = new OqlExprObjectSource();
        x.alias = this.alias;
        if (this.expr != null) {
            x.setExpr(this.expr.clone());
        }

        return x;
    }

    @Override
    public boolean replace(OqlExpr source, OqlExpr target) {
        if (source == this.expr && target instanceof OqlIdentifierExpr) {
            this.setExpr(target);
            return true;
        } else {
            return false;
        }
    }
}
