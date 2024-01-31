package net.cf.formula.engine.ast.expr.identifier;

import net.cf.formula.engine.ast.expr.FxExprImpl;
import net.cf.formula.engine.visitor.FxAstVisitor;

import java.util.Collections;
import java.util.List;

/**
 * 带属性的表达式，如：o1.o2.xx
 *
 * @author clouds
 */
public class FxPropertyExpr extends FxExprImpl implements FxNameExpr {

    /**
     * 属性的归属
     */
    private FxNameExpr owner;

    /**
     * 属性的名称
     */
    private String name;

    public FxPropertyExpr(String owner, String name) {
        this(new FxIdentifierExpr(owner), name);
    }

    public FxPropertyExpr(FxNameExpr owner, String name) {
        this.setOwner(owner);
        this.name = name;
    }


    public FxNameExpr getOwner() {
        return owner;
    }

    /**
     * 设置属性的归属
     *
     * @param owner
     */
    public void setOwner(FxNameExpr owner) {
        if (owner != null) {
            owner.setParent(this);
        }

        this.owner = owner;
    }

    @Override
    public List getChildren() {
        return Collections.singletonList(this.owner);
    }

    @Override
    protected void accept0(FxAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.owner != null) {
                this.owner.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public FxPropertyExpr clone() {
        FxNameExpr owner_x = null;
        if (this.owner != null) {
            owner_x = this.owner.clone();
        }

        FxPropertyExpr x = new FxPropertyExpr(owner_x, this.name);
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
