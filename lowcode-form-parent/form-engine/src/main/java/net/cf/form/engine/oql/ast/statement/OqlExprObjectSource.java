package net.cf.form.engine.oql.ast.statement;

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

    public OqlExprObjectSource() {
    }

    public OqlExprObjectSource(OqlIdentifierExpr expr) {
        this(expr, null);
    }

    public OqlExprObjectSource(String objectName) {
        this(new OqlIdentifierExpr(objectName), null);
    }

    public OqlExprObjectSource(String objectName, String alias) {
        super(new OqlIdentifierExpr(objectName), alias);
    }

    public OqlExprObjectSource(OqlIdentifierExpr expr, String alias) {
        super(expr, alias);
    }

    public OqlIdentifierExpr getExpr() {
        return (OqlIdentifierExpr) this.flashback;
    }

    public void setExpr(OqlIdentifierExpr x) {
        super.setFlashback(x);
    }

    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.flashback != null) {
                this.flashback.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public boolean replace(OqlExpr source, OqlExpr target) {
        if (source == this.flashback && target instanceof OqlIdentifierExpr) {
            this.setExpr((OqlIdentifierExpr) target);
            return true;
        } else {
            return false;
        }
    }
}
