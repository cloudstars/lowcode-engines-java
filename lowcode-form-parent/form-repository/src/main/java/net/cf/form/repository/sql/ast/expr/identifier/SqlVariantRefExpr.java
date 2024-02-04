package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExprImpl;

/**
 * SQL AST 中的变量引用节点
 *
 * @author clouds
 */
public class SqlVariantRefExpr extends SqlExprImpl {

    private String name;

    private int index;

    public SqlVariantRefExpr() {
        this.index = -1;
    }

    public SqlVariantRefExpr(String name) {
        this.index = -1;
        this.name = name;
    }

    public SqlVariantRefExpr(String name, SqlObject parent) {
        this.index = -1;
        this.name = name;
        this.parent = parent;
    }

    /**
     * 获取变量引用的名称
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置变量引用的名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlVariantRefExpr _clone() {
        SqlVariantRefExpr var = new SqlVariantRefExpr(this.name);
        return var;
    }
}