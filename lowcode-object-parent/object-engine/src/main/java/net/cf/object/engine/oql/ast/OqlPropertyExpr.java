package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;
import net.cf.object.engine.sqlbuilder.SqlDataTypeConvert;

import java.util.Collections;
import java.util.List;

/**
 * OQL字段属性表达式，如：object.field.property, field.property
 *
 * @author clouds
 */
public class OqlPropertyExpr extends AbstractOqlExprImpl implements SqlName {

    /**
     * 属性归属的字段（可能为空，如字段展开中的属性）
     */
    private final OqlFieldExpr owner;

    /**
     * 属性的名称
     */
    private String name;

    /**
     * OQL解析时生成的字段属性
     */
    private XProperty resolvedProperty;

    public OqlPropertyExpr(OqlFieldExpr owner) {
        this.owner = owner;
        this.addChild(owner);
    }

    public OqlPropertyExpr(OqlFieldExpr owner, String name) {
        this.owner = owner;
        this.addChild(owner);
        this.name = name;
    }


    public OqlFieldExpr getOwner() {
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
    public OqlPropertyExpr cloneMe() {
        OqlFieldExpr ownerX = null;
        if (this.owner != null) {
            ownerX = this.owner.cloneMe();
        }

        OqlPropertyExpr x = new OqlPropertyExpr(ownerX);
        x.setName(this.name);
        x.setResolvedProperty(this.resolvedProperty);
        return x;
    }

    @Override
    public SqlDataType computeSqlDataType() {
        return SqlDataTypeConvert.toSqlDataType(this.resolvedProperty.getOwner());
    }

    @Override
    public List<SqlExpr> getChildren() {
        return Collections.singletonList(this.owner);
    }

    public XProperty getResolvedProperty() {
        return resolvedProperty;
    }

    public void setResolvedProperty(XProperty resolvedProperty) {
        this.resolvedProperty = resolvedProperty;
        if (this.owner != null) {
            this.owner.setResolvedField(resolvedProperty.getOwner());
        }
    }
}
