package net.cf.object.engine.oql.ast;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型展开表达式（用于相关表、主表、子表的C/R/U操作）
 *
 * @author clouds
 */
public class OqlObjectExpandExpr extends AbstractExpandableOqlExprImpl {

    /**
     * 展开的模型
     */
    private final String owner;

    /**
     *  模型中展开的字段列表（含常量、表达式等）
     */
    protected final List<OqlExpr> fields = new ArrayList<>();

    /**
     * 本模型时展开的字段
     */
    protected XObjectRefField resolvedObjectRefField;

    /**
     * 展开的关联模型
     */
    protected XObject resolvedRefObject;

    public OqlObjectExpandExpr(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public List<OqlExpr> getFields() {
        return fields;
    }

    public void addField(OqlExpr field) {
        this.fields.add(field);
    }

    @Override
    public final void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (OqlExpr field : this.fields) {
                if (field != null) {
                    field.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlObjectExpandExpr cloneMe() {
        OqlObjectExpandExpr x = new OqlObjectExpandExpr(this.owner);
        for (OqlExpr field : fields) {
            x.addField(field.cloneMe());
        }
        x.setResolvedObjectRefField(this.resolvedObjectRefField);
        x.setResolvedRefObject(this.resolvedRefObject);

        return x;
    }

    @Override
    public List<OqlExpr> getChildren() {
        return this.fields;
    }

    public XObjectRefField getResolvedObjectRefField() {
        return resolvedObjectRefField;
    }

    public void setResolvedObjectRefField(XObjectRefField resolvedObjectRefField) {
        this.resolvedObjectRefField = resolvedObjectRefField;
    }

    public XObject getResolvedRefObject() {
        return resolvedRefObject;
    }

    public void setResolvedRefObject(XObject resolvedRefObject) {
        this.resolvedRefObject = resolvedRefObject;
    }

}

