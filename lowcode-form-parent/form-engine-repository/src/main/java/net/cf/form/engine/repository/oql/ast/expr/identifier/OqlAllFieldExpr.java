package net.cf.form.engine.repository.oql.ast.expr.identifier;

import net.cf.form.engine.repository.oql.ast.statement.OqlObjectSource;
import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

/**
 * Select语中的所有字段的表达式
 *
 * @author clouds
 */
@Deprecated
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
