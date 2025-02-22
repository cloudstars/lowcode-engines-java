package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;
import net.cf.object.engine.sql.SqlDataTypeConvert;

/**
 * OQL字段表达式，如：object.field, field, object(field)中的field
 *
 * @author clouds
 */
public class OqlFieldExpr extends AbstractOqlExprImpl implements SqlName {

    /**
     * 归属的模型（本模型字段前面不加前缀"object.”)
     */
    private String owner;

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
    }

    public OqlFieldExpr(String name) {
        this.name = name;
    }

    public OqlFieldExpr(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public OqlFieldExpr cloneMe() {
        OqlFieldExpr x = new OqlFieldExpr(this.owner, this.name);
        x.setResolvedField(this.resolvedField);
        return x;
    }

    @Override
    public SqlDataType computeSqlDataType() {
        return SqlDataTypeConvert.toSqlDataType(this.resolvedField);
    }

    public XField getResolvedField() {
        return resolvedField;
    }

    public void setResolvedField(XField resolvedField) {
        this.resolvedField = resolvedField;
    }
}
