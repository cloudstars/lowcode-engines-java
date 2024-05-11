package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.FastSqlException;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL AST 中的变量引用节点，#{x}, #{x.y}
 *
 * @author clouds
 */
public class SqlVariantRefExpr extends AbstractSqlExprImpl implements SqlName {

    /**
     * 变量含子变量（即变量展开）的匹配模式
     */
    protected final Pattern VAR_EXPAND_PATTERN = Pattern.compile("^#\\{([\\w\\d\\._]+)(\\(.+\\))?\\}$");

    /**
     * 变量引用的名称，如#{id}、${id}
     */
    private String name;

    /**
     * 变量的名称
     */
    private String varName;

    /**
     * 子变量的名称，如：#{var(v1, v2)}中的v1, v2
     */
    private List<String> subVarNames;

    public SqlVariantRefExpr() {
    }

    public SqlVariantRefExpr(String name) {
        this(name, null);
    }

    public SqlVariantRefExpr(String name, SqlObject parent) {
        this.setName(name);
        this.parent = parent;
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
        Matcher matcher = VAR_EXPAND_PATTERN.matcher(name);
        if (matcher.matches()) {
            this.varName = matcher.group(1);
            int groupCount = matcher.groupCount();
            if (groupCount == 3) { // #{var(sub1, sub2, ...)}
                this.subVarNames = new ArrayList();
                String subVarNamesStr = matcher.group(2);
                String[] subVarNamesArr = subVarNamesStr.split(",");
                for (int i = 0; i < subVarNamesArr.length; i++) {
                    this.subVarNames.add(subVarNamesArr[i].trim());
                }
            }
        } else {
            throw new FastSqlException("非法的变量表达式名称:" + name);
        }
    }

    public List<String> getSubVarNames() {
        return subVarNames;
    }

    public void addSubVarName(String subVarName) {
        if (this.subVarNames == null) {
            this.subVarNames = new ArrayList<>();
        }

        this.subVarNames.add(subVarName);
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