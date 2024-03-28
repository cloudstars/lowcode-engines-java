package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL AST 中的变量引用节点
 *
 * @author clouds
 */
public class SqlVariantRefExpr extends AbstractSqlExprImpl implements SqlName {

    /**
     * 变量引用的名称，如#{id}、${id}
     */
    private String name;

    /**
     * 变量的名称
     */
    private String varName;

    /**
     * 变量出现在SQL语名中的序号（从0开始）
     */
    private int index;

    public SqlVariantRefExpr() {
        this.index = -1;
    }

    public SqlVariantRefExpr(String name) {
        this(name, null);
    }

    public SqlVariantRefExpr(String name, SqlObject parent) {
        if ((name.startsWith("#{") && name.endsWith("}")) || (name.startsWith("${") && name.endsWith("}"))) {
            this.setVarNameByName(name);
        }
        this.name = name;
        this.parent = parent;
        this.index = -1;
    }

    @Override
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
        this.setVarNameByName(name);
    }

    /**
     * 获取变量的名称
     *
     * @return
     */
    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
        this.name = "#{" + varName + "}";
    }

    private void setVarNameByName(String name) {
        this.varName = name.substring(2, name.length() -1);
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
    public SqlVariantRefExpr cloneMe() {
        SqlVariantRefExpr var = new SqlVariantRefExpr(this.name);
        return var;
    }

}