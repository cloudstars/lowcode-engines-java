package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.Collections;
import java.util.List;

/**
 * 带属性的表达式，如：(table | tableAlias).xx
 *
 * @author clouds
 */
public class SqlPropertyExpr extends AbstractSqlExprImpl implements SqlName {

    /**
     * 属性的归属
     */
    // private final SqlName owner;
    private final SqlIdentifierExpr owner;

    /**
     * 属性的名称
     */
    private String name;

    /**
     * 归属的数据库表
     */
    //private String resolvedOwnerTable;

    /**
     * 对应数据表列
     */
    //private String resolvedColumn;

    public SqlPropertyExpr(String owner) {
        this(new SqlIdentifierExpr(owner), null);
    }

    /*public SqlPropertyExpr(SqlName owner) {
        this.owner = owner;
        this.addChild(owner);
    }*/

    public SqlPropertyExpr(SqlIdentifierExpr owner, String name) {
        this.owner = owner;
        this.addChild(owner);
        this.name = name;
    }

    /*public SqlPropertyExpr(SqlName owner, String name) {
        this.owner = owner;
        this.addChild(owner);
        this.name = name;
    }*/

    /*public SqlName getOwner() {
        return owner;
    }*/
    public SqlIdentifierExpr getOwner() {
        return owner;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getResolvedOwnerTable() {
        return resolvedOwnerTable;
    }

    public void setResolvedOwnerTable(String resolvedOwnerTable) {
        this.resolvedOwnerTable = resolvedOwnerTable;
    }

    public String getResolvedColumn() {
        return resolvedColumn;
    }

    public void setResolvedColumn(String resolvedColumn) {
        this.resolvedColumn = resolvedColumn;
    }*/

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.owner != null) {
                this.owner.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlPropertyExpr cloneMe() {
        SqlIdentifierExpr ownerX = null;
        if (this.owner != null) {
            ownerX = this.owner.cloneMe();
        }

        SqlPropertyExpr x = new SqlPropertyExpr(ownerX, this.name);
        return x;
    }


    @Override
    public List<SqlExpr> getChildren() {
        return Collections.singletonList(this.owner);
    }

}
