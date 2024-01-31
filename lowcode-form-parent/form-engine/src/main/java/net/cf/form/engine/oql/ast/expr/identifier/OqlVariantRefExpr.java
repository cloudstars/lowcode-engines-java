package net.cf.form.engine.oql.ast.expr.identifier;

import net.cf.form.engine.oql.ast.OqlObject;
import net.cf.form.engine.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

/**
 * 变量引用表达式
 *
 * @author clouds
 */
public class OqlVariantRefExpr extends OqlExprImpl {

    private String name;

    public OqlVariantRefExpr(String name) {
        this.name = name;
    }

    public OqlVariantRefExpr(String varName, boolean parameterized) {
        if (parameterized) {
            this.name = "#{" + varName +"}";
        } else {
            this.name = "${" + varName +"}";
        }
    }

    public OqlVariantRefExpr(String name, OqlObject parent) {
        this.name = name;
        this.parent = parent;
    }

    public OqlVariantRefExpr() {
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

    /**
     * 是否可替换参数
     *
     * @return
     */
    public boolean isReplaceable() {
        return this.name.startsWith("${");
    }

    /**
     * 获取变量的名称
     *
     * @return
     */
    public String getVarName() {
        return this.name.substring(2, this.name.length() - 1);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public OqlVariantRefExpr clone() {
        OqlVariantRefExpr var = new OqlVariantRefExpr(this.name);
        return var;
    }
}