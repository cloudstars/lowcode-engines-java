package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.ast.OqlReplaceable;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;

/**
 * 表达式形式的对象源
 *
 * @author clouds
 */
@Deprecated
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

    @Override
    public boolean replace(QqlExpr source, QqlExpr target) {
        if (source == this.flashback && target instanceof OqlIdentifierExpr) {
            this.setExpr((OqlIdentifierExpr) target);
            return true;
        } else {
            return false;
        }
    }
}
