package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * 表达式形式的模型源
 *
 * @author clouds
 */
public class OqlExprObjectSource extends AbstractOqlObjectSourceImpl implements SqlReplaceable {

    /**
     *  展开的对象
     */
    protected SqlName expr;

    public OqlExprObjectSource() {
    }

    public OqlExprObjectSource(String objectName) {
        this(new SqlIdentifierExpr(objectName), null);
    }

    public OqlExprObjectSource(String objectName, String alias) {
        super(new SqlIdentifierExpr(objectName), alias);
    }

    public OqlExprObjectSource(SqlName expr, String alias) {
        this.setExpr(expr);
        this.setAlias(alias);
    }

    public SqlName getExpr() {
        return this.expr;
    }

    public void setExpr(SqlName x) {
        if (x != null) {
            x.setParent(this);
        }

        this.expr = x;
    }

    @Override
    public void accept(OqlAstVisitor visitor) {
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
        if (source == this.expr && target instanceof SqlName) {
            this.setExpr((SqlName) target);
            return true;
        } else {
            return false;
        }
    }

}
