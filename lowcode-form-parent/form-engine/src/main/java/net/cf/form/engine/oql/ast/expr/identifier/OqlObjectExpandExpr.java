package net.cf.form.engine.oql.ast.expr.identifier;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.List;

/**
 * 对象展开表达式（用于相关表、主表、子表的C/R/U操作）
 *
 * @author clouds
 */
public class OqlObjectExpandExpr extends OqlMethodInvokeExpr {

    public OqlObjectExpandExpr() {
    }

    public OqlObjectExpandExpr(String objectFieldName) {
        super(objectFieldName);
    }

    public OqlObjectExpandExpr(String objectFieldName, OqlExpr... args) {
        super(objectFieldName, args);
    }

    public String getObjectFieldName() {
        return this.methodName;
    }

    public void setObjectFieldName(String objectFieldName) {
        super.setMethodName(objectFieldName);
    }

    public List<OqlExpr> getFields() {
        return this.arguments;
    }

    public void setField(int i, OqlExpr field) {
        super.setArgument(i, field);
    }

    public void addField(OqlExpr field) {
        super.addArgument(field);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (OqlExpr field : this.arguments) {
                if (field != null) {
                    field.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);
    }

}

