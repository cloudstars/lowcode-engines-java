package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型字段展开表达式
 *
 * @author clouds
 */
public class OqlFieldExpandExpr extends AbstractOqlExprImpl {

    /**
     * 字段实例
     */
    protected final XField resolvedField;

    /**
     * 属性列表
     */
    protected final List<SqlIdentifierExpr> properties;

    public OqlFieldExpandExpr(XField resolvedField) {
        this(resolvedField, new ArrayList<>());

    }

    public OqlFieldExpandExpr(XField resolvedField, List<SqlIdentifierExpr> properties) {
        this.resolvedField = resolvedField;
        this.properties = properties;
    }

    public XField getResolvedField() {
        return resolvedField;
    }

    public List<SqlIdentifierExpr> getProperties() {
        return properties;
    }

    public void setProperty(int i, SqlIdentifierExpr field) {
        this.properties.add(i, field);
    }

    public void addProperty(SqlIdentifierExpr field) {
        this.properties.add(field);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChildren(visitor, this.properties);
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlFieldExpandExpr cloneMe() {
        return new OqlFieldExpandExpr(this.resolvedField, this.properties);
    }

    @Override
    public List<? extends SqlObject> getChildren() {
        return this.properties;
    }
}
