package net.cf.form.engine.repository.oql.ast.expr.identifier;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

import java.util.List;

/**
 * 对象展开表达式（用于相关表、主表、子表的C/R/U操作）
 *
 * @author clouds
 */
@Deprecated
public class OqlObjectExpandExpr extends OqlMethodInvokeExpr {

    public OqlObjectExpandExpr() {
    }

    public OqlObjectExpandExpr(String objectFieldName) {
        super(objectFieldName);
    }

    public OqlObjectExpandExpr(String objectFieldName, QqlExpr... args) {
        super(objectFieldName, args);
    }

    public String getObjectFieldName() {
        return this.methodName;
    }

    public void setObjectFieldName(String objectFieldName) {
        super.setMethodName(objectFieldName);
    }

    public List<QqlExpr> getFields() {
        return this.arguments;
    }

    public void setField(int i, QqlExpr field) {
        super.setArgument(i, field);
    }

    public void addField(QqlExpr field) {
        super.addArgument(field);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (QqlExpr field : this.arguments) {
                if (field != null) {
                    field.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);
    }

}

