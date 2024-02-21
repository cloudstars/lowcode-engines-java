package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
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
    private SqlName owner;

    /**
     * 属性的名称
     */
    private String name;

    public SqlPropertyExpr(String owner, String name) {
        this(new SqlIdentifierExpr(owner), name);
    }

    public SqlPropertyExpr(SqlName owner, String name) {
        this.setOwner(owner);
        this.name = name;
    }


    public SqlName getOwner() {
        return owner;
    }

    /**
     * 设置属性的归属
     *
     * @param owner
     */
    public void setOwner(SqlName owner) {
        this.owner = owner;
        this.addChild(owner);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List getChildren() {
        return Collections.singletonList(this.owner);
    }

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
        SqlName ownerX = null;
        if (this.owner != null) {
            ownerX = this.owner.cloneMe();
        }

        SqlPropertyExpr x = new SqlPropertyExpr(ownerX, this.name);
        return x;
    }

    @Override
    public String getSimpleName() {
        return this.name;
    }
}
