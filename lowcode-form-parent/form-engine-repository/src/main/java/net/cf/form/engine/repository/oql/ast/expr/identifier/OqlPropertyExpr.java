package net.cf.form.engine.repository.oql.ast.expr.identifier;

import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

import java.util.Collections;
import java.util.List;

/**
 * 带属性的表达式，如：o1.o2.xx
 *
 * @author clouds
 */
@Deprecated
public class OqlPropertyExpr extends OqlExprImpl implements QqlNameExpr {

    /**
     * 属性的归属
     */
    private QqlNameExpr owner;

    /**
     * 属性的名称
     */
    private String name;


    private DataField resolvedField;

    private DataObject resolvedOwnerObject;

    public OqlPropertyExpr(String owner, String name) {
        this(new OqlIdentifierExpr(owner), name);
    }

    public OqlPropertyExpr(QqlNameExpr owner, String name) {
        this.setOwner(owner);
        this.name = name;
    }


    public QqlNameExpr getOwner() {
        return owner;
    }

    /**
     * 设置属性的归属
     *
     * @param owner
     */
    public void setOwner(QqlNameExpr owner) {
        this.owner = owner;
        this.addChild(owner);
    }

    public DataField getResolvedField() {
        return resolvedField;
    }

    public void setResolvedField(DataField resolvedField) {
        this.resolvedField = resolvedField;
    }

    public DataObject getResolvedOwnerObject() {
        return resolvedOwnerObject;
    }

    public void setResolvedOwnerObject(DataObject resolvedOwnerObject) {
        this.resolvedOwnerObject = resolvedOwnerObject;
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
        QqlNameExpr owner_x = null;
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
