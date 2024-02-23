package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.object.XObject;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * 表达式形式的对象源
 *
 * @author clouds
 */
public class OqlExprObjectSource extends AbstractOqlObjectSourceImpl implements SqlReplaceable {

    protected SqlExpr expr;

    protected XObject resolvedObject;

    public OqlExprObjectSource() {
    }

    public OqlExprObjectSource(SqlExpr expr) {
        this(expr, null);
    }

    public OqlExprObjectSource(String objectName) {
        this(new SqlIdentifierExpr(objectName), null);
    }

    public OqlExprObjectSource(String objectName, String alias) {
        super(new SqlIdentifierExpr(objectName), alias);
    }

    public OqlExprObjectSource(SqlExpr expr, String alias) {
        this.setExpr(expr);
        this.setAlias(alias);
    }

    public SqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(SqlExpr x) {
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
    public OqlExprObjectSource cloneMe() {
        OqlExprObjectSource x = new OqlExprObjectSource();
        x.alias = this.alias;
        if (this.expr != null) {
            x.setExpr(this.expr.cloneMe());
        }

        return x;
    }

    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        if (source == this.expr && target instanceof SqlIdentifierExpr) {
            this.setExpr(target);
            return true;
        } else {
            return false;
        }
    }
}
