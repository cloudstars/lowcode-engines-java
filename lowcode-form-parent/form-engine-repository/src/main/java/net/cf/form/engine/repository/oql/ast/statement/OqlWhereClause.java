package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

import java.util.Arrays;
import java.util.List;

/**
 * OQL where子句
 *
 * @author clouds
 */
@Deprecated
public class OqlWhereClause extends OqlExprImpl {

    private QqlExpr expr;

    public OqlWhereClause(QqlExpr expr) {
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

    public QqlExpr getExpr() {
        return expr;
    }

    public void setExpr(QqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    @Override
    public OqlWhereClause clone() {
        OqlWhereClause whereClause = new OqlWhereClause(this.expr.clone());
        return whereClause;
    }
}
