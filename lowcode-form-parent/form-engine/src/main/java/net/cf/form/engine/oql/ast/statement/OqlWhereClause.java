package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.Arrays;
import java.util.List;

/**
 * OQL where子句
 *
 * @author clouds
 */
public class OqlWhereClause extends OqlExprImpl {

    private OqlExpr expr;

    public OqlWhereClause(OqlExpr expr) {
        this.setExpr(expr);
    }

    @Override
    public List getChildren() {
        return Arrays.asList(expr);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.expr.accept(visitor);
        }

        visitor.endVisit(this);
    }

    public OqlExpr getExpr() {
        return expr;
    }

    public void setExpr(OqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    @Override
    public OqlWhereClause clone() {
        OqlWhereClause whereClause = new OqlWhereClause(this.expr.clone());
        return whereClause;
    }
}
