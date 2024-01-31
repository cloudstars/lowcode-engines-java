package net.cf.form.engine.oql.ast.expr.identifier;

import net.cf.form.engine.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.oql.ast.statement.OqlObjectSource;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

/**
 * Select语中的所有字段的表达式
 *
 * @author clouds
 */
public final class OqlAllFieldExpr extends OqlExprImpl {

    private transient OqlObjectSource resolvedObjectSource;

    public OqlAllFieldExpr() {
    }

    public OqlObjectSource getResolvedObjectSource() {
        return this.resolvedObjectSource;
    }

    public void setResolvedObjectSource(OqlObjectSource resolvedObjectSource) {
        this.resolvedObjectSource = resolvedObjectSource;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public OqlAllFieldExpr clone() {
        OqlAllFieldExpr x = new OqlAllFieldExpr();
        x.resolvedObjectSource = this.resolvedObjectSource;
        return x;
    }

}
