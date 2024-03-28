package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.form.repository.sql.ast.expr.SqlExpr;

/**
 * 可展开的表达式
 *
 * @author clouds
 */
public abstract class AbstractExpandableOqlExprImpl extends AbstractOqlExprImpl {

    /**
     * 是否默认全部展开，即不带后面的部分，如：field、refObject
     */
    protected boolean isDefaultExpanded = false;

    /**
     * 是否“*”全部展开，即field.*
     */
    protected boolean isStarExpanded = false;

    @Override
    public SqlExpr cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public SqlDataType computeSqlDataType() {
        return SqlDataType.OBJECT;
    }

    public boolean isDefaultExpanded() {
        return isDefaultExpanded;
    }

    public void setDefaultExpanded(boolean defaultExpanded) {
        isDefaultExpanded = defaultExpanded;
    }

    public boolean isStarExpanded() {
        return isStarExpanded;
    }

    public void setStarExpanded(boolean starExpanded) {
        isStarExpanded = starExpanded;
    }

}
