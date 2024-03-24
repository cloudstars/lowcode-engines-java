package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.Collections;
import java.util.List;

/**
 * OQL字段表达式，如：object.field, field, object(field)中的field
 *
 * @author clouds
 */
public class OqlFieldExpr extends AbstractOqlExprImpl implements SqlName {

    /**
     * 归属的模型(如本模模的字段前面不带"object.”)
     */
    private final SqlIdentifierExpr owner;

    /**
     * 字段的名称
     */
    private String name;

    /**
     * OQL解析时生成的字段
     */
    private XField resolvedField;

    /**
     * 不带owner的构造函数，用于本表中的字段、模型展开中的字段
     */
    public OqlFieldExpr() {
        this.owner = null;
    }

    public OqlFieldExpr(String owner) {
        this(new SqlIdentifierExpr(owner));
    }

    public OqlFieldExpr(SqlIdentifierExpr owner) {
        this.owner = owner;
        this.addChild(owner);
    }

    public OqlFieldExpr(SqlIdentifierExpr owner, String  name) {
        this.owner = owner;
        this.addChild(owner);
        this.name = name;
    }

    public SqlIdentifierExpr getOwner() {
        return owner;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
    public OqlFieldExpr cloneMe() {
        SqlIdentifierExpr ownerX = null;
        if (this.owner != null) {
            ownerX = this.owner.cloneMe();
        }

        OqlFieldExpr x = new OqlFieldExpr(ownerX);
        x.setName(this.name);
        x.setResolvedField(this.resolvedField);
        return x;
    }

    @Override
    public List<SqlExpr> getChildren() {
        return Collections.singletonList(this.owner);
    }

    public XField getResolvedField() {
        return resolvedField;
    }

    public void setResolvedField(XField resolvedField) {
        this.resolvedField = resolvedField;
    }
}
