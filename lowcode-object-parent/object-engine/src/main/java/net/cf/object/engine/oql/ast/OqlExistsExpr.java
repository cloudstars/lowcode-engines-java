package net.cf.object.engine.oql.ast;


import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.Collections;
import java.util.List;

/**
 * OQL exists 表达式
 *
 * @author clouds 
 */
public final class OqlExistsExpr extends AbstractOqlExprImpl {

    /**
     * 是否有not关键字
     */
    public boolean not;

    /**
     * 子查询
     */
    public OqlSelect subQuery;

    public OqlExistsExpr() {
    }

    public OqlExistsExpr(OqlSelect subQuery) {
        this.setSubQuery(subQuery);
    }

    public OqlExistsExpr(OqlSelect subQuery, boolean not) {
        this.setSubQuery(subQuery);
        this.not = not;
    }

    public OqlSelect getSubQuery() {
        return this.subQuery;
    }

    public void setSubQuery(OqlSelect subQuery) {
        if (subQuery != null) {
            subQuery.setParent(this);
        }

        this.subQuery = subQuery;
    }

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.subQuery);
        }

        visitor.endVisit(this);
    }

    @Override
    public List<SqlObject> getChildren() {
        return Collections.singletonList(this.subQuery);
    }

    @Override
    public OqlExistsExpr cloneMe() {
        OqlExistsExpr x = new OqlExistsExpr();
        x.not = this.not;
        if (this.subQuery != null) {
            x.setSubQuery(this.subQuery.cloneMe());
        }

        return x;
    }
}
