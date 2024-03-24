package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
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
    private final SqlName owner;

    /**
     *  模型中展开的字段列表（含常量、表达式等）
     */
    protected final List<SqlExpr> fields = new ArrayList<>();

    /**
     * 展开模型的字段
     */
    protected XObjectRefField resolvedObjectRefField;

    public OqlObjectExpandExpr(String objectName) {
        this(new SqlIdentifierExpr(objectName));
    }


    public OqlObjectExpandExpr(SqlName objectName) {
        this.owner = objectName;
    }

    public SqlName getOwner() {
        return owner;
    }

    public List<SqlExpr> getFields() {
        return fields;
    }

    public void addField(SqlExpr field) {
        this.fields.add(field);
    }

    public XObjectRefField getResolvedObjectRefField() {
        return resolvedObjectRefField;
    }

    public void setResolvedObjectRefField(XObjectRefField resolvedObjectRefField) {
        this.resolvedObjectRefField = resolvedObjectRefField;
    }

    @Override
    public final void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (SqlExpr field : this.fields) {
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
        for (SqlExpr field : fields) {
            x.addField(field.cloneMe());
        }
        x.setResolvedObjectRefField(this.resolvedObjectRefField);

        return x;
    }

    @Override
    public List<SqlExpr> getChildren() {
        return this.fields;
    }
}

