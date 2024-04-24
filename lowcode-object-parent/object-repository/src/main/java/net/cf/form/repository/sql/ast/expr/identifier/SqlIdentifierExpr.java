package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL AST 中的标识符
 *
 * @author clouds
 */
public class SqlIdentifierExpr extends AbstractSqlExprImpl implements SqlName {

    /**
     * 标识符的名称
     */
    protected String name;

    /**
     * 是否自动生成的列
     */
    private boolean autoGen;

    public SqlIdentifierExpr() {
    }

    public SqlIdentifierExpr(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLowerName() {
        return this.name == null ? null : this.name.toLowerCase();
    }

    public boolean isAutoGen() {
        return autoGen;
    }

    public void setAutoGen(boolean autoGen) {
        this.autoGen = autoGen;
    }

    /*public String getResolvedColumn() {
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
    }*/

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlIdentifierExpr cloneMe() {
        SqlIdentifierExpr x = new SqlIdentifierExpr(this.name);
        x.setAutoGen(this.autoGen);
        return x;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
