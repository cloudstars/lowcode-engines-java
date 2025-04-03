package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL删除语句
 *
 * @author clouds
 */
public class OqlDeleteStatement extends AbstractOqlStatementImpl implements OqlStatement {

    protected OqlExprObjectSource from;

    /**
     * 要删除的子表
     */
    @Deprecated
    protected List<OqlExprObjectSource> detailFroms;

    protected SqlExpr where;

    public OqlDeleteStatement() {
    }

    public OqlExprObjectSource getFrom() {
        return from;
    }

    public void setFrom(OqlExprObjectSource from) {
        this.from = from;
        this.addChild(from);
    }

    public List<OqlExprObjectSource> getDetailFroms() {
        return detailFroms;
    }

    public void setDetailFroms(List<OqlExprObjectSource> detailFroms) {
        this.detailFroms = detailFroms;
    }

    public SqlExpr getWhere() {
        return where;
    }

    public void setWhere(SqlExpr where) {
        this.where = where;
        this.addChild(where);
    }

    @Override
    public void accept(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.from);
            this.nullSafeAcceptChild(visitor, this.where);
        }

        visitor.endVisit(this);
    }


    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList<>();
        children.add(this.from);
        if (this.where != null) {
            children.add(this.where);
        }

        return children;
    }

    @Override
    public OqlDeleteStatement cloneMe() {
        OqlDeleteStatement statement = new OqlDeleteStatement();
        statement.setFrom(this.from.cloneMe());
        if (this.where != null) {
            statement.setWhere(this.where.cloneMe());
        }

        return statement;
    }
}
