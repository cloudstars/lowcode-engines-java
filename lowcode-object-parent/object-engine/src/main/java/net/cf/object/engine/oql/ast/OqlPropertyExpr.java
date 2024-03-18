package net.cf.object.engine.oql.ast;

import net.cf.object.engine.object.XField;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * 模型字段属性表达式
 *
 * @author clouds
 */
public class OqlPropertyExpr extends AbstractOqlExprImpl {

    /**
     * 字段实例
     */
    protected final XField resolvedField;

    /**
     * 属性列表
     */
    protected final String property;

    /**
     * 对应数据表列
     */
    private String resolvedColumn;

    /**
     * 归属的数据库表
     */
    private String resolvedOwnerTable;

    public OqlPropertyExpr(XField resolvedField, String property) {
        this.resolvedField = resolvedField;
        this.property = property;
    }

    public XField getResolvedField() {
        return resolvedField;
    }

    public String getProperty() {
        return property;
    }

    public String getResolvedColumn() {
        return resolvedColumn;
    }

    public void setResolvedColumn(String resolvedColumn) {
        this.resolvedColumn = resolvedColumn;
    }

    public String getResolvedOwnerTable() {
        return resolvedOwnerTable;
    }

    public void setResolvedOwnerTable(String resolvedOwnerTable) {
        this.resolvedOwnerTable = resolvedOwnerTable;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public OqlPropertyExpr cloneMe() {
        return new OqlPropertyExpr(this.resolvedField, this.property);
    }

}
