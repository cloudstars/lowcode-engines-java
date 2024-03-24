package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型字段展开表达式
 *
 * @author clouds
 */
public class OqlFieldExpandExpr extends AbstractExpandableOqlExprImpl {

    /**
     *  展开的字段
     */
    private final SqlName owner;

    /**
     *  字段中展开的属性列表（含常量、表达式等）
     */
    protected final List<SqlExpr> properties = new ArrayList<>();

    /**
     * OQL解析时生成的字段
     */
    protected XField resolvedField;

    public OqlFieldExpandExpr(String fieldName) {
        this(new SqlIdentifierExpr(fieldName));
    }

    public OqlFieldExpandExpr(SqlName owner) {
        this.owner = owner;
    }

    public SqlName getOwner() {
        return owner;
    }

    public List<SqlExpr> getProperties() {
        return properties;
    }

    public <T extends SqlExpr> void addProperties(List<T> properties) {
        this.properties.addAll(properties);
    }

    public void addProperty(SqlExpr property) {
        this.properties.add(property);
    }

    public XField getResolvedField() {
        return resolvedField;
    }

    public void setResolvedField(XField resolvedField) {
        this.resolvedField = resolvedField;
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
        OqlFieldExpandExpr x = new OqlFieldExpandExpr(this.owner);
        for (SqlExpr property : this.properties) {
            x.addProperty(property);
        }
        x.isDefaultExpanded = this.isDefaultExpanded;
        x.isStarExpanded = this.isStarExpanded;
        x.resolvedField = this.resolvedField;

        return x;
    }

}
