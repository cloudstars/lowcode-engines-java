package net.cf.form.engine.oql.ast.expr.identifier;

import net.cf.form.engine.oql.ast.expr.AbstractOqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.Collections;
import java.util.List;

/**
 * 带属性的表达式，如：o1.o2.xx
 *
 * @author clouds
 */
public class OqlPropertyExpr extends AbstractOqlExprImpl implements OqlNameExpr {

    /**
     * 属性的归属
     */
    private OqlNameExpr owner;

    /**
     * 属性的名称
     */
    private String name;

    public OqlPropertyExpr(String owner, String name) {
        this(new OqlIdentifierExpr(owner), name);
    }

    public OqlPropertyExpr(OqlNameExpr owner, String name) {
        this.setOwner(owner);
        this.name = name;
    }


    public OqlNameExpr getOwner() {
        return owner;
    }

    /**
     * 设置属性的归属
     *
     * @param owner
     */
    public void setOwner(OqlNameExpr owner) {
        this.owner = owner;
        this.addChild(owner);
    }

    @Override
    public List getChildren() {
        return Collections.singletonList(this.owner);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.owner != null) {
                this.owner.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlPropertyExpr clone() {
        OqlNameExpr owner_x = null;
        if (this.owner != null) {
            owner_x = this.owner.clone();
        }

        OqlPropertyExpr x = new OqlPropertyExpr(owner_x, this.name);
        return x;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
