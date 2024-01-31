package net.cf.form.engine.oql.ast.expr.identifier;

import net.cf.form.engine.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;

/**
 * 标识符
 *
 * @author clouds
 */
public class OqlIdentifierExpr extends OqlExprImpl implements OqlNameExpr {

    protected String name;

    private DataField resolvedField;

    private DataObject resolvedOwnerObject;


    public OqlIdentifierExpr() {
    }

    public OqlIdentifierExpr(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Deprecated
    public DataField getResolvedField() {
        return resolvedField;
    }

    public void setResolvedField(DataField resolvedField) {
        this.resolvedField = resolvedField;
    }

    @Deprecated
    public DataObject getResolvedOwnerObject() {
        return resolvedOwnerObject;
    }

    public void setResolvedOwnerObject(DataObject resolvedOwnerObject) {
        this.resolvedOwnerObject = resolvedOwnerObject;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public OqlIdentifierExpr clone() {
        OqlIdentifierExpr x = new OqlIdentifierExpr(this.name);
        return x;
    }

}
