package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.AbstractOqlObjectImpl;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型展开表达式（用于相关表、主表、子表的C/R/U操作）
 *
 * @author clouds
 */
public class OqlObjectExpandExpr extends AbstractOqlObjectImpl {

    /**
     * 模型名称
     */
    protected final XObject resolvedObject;

    /**
     *  字段列表
     */
    protected final List<SqlExpr> fields;

    public OqlObjectExpandExpr(XObject resolvedObject) {
        this(resolvedObject, new ArrayList<>());
    }

    public OqlObjectExpandExpr(XObject resolvedObject, List<SqlExpr> fields) {
        this.resolvedObject = resolvedObject;
        this.fields = fields;
    }

    public XObject getResolvedObject() {
        return resolvedObject;
    }

    public List<SqlExpr> getFields() {
        return fields;
    }

    public void setField(int i, SqlExpr field) {
        this.fields.add(i, field);
    }

    public void addField(SqlExpr field) {
        this.fields.add(field);
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
        return new OqlObjectExpandExpr(this.resolvedObject, this.fields);
    }

    @Override
    public List<? extends SqlObject> getChildren() {
        return this.fields;
    }
}

