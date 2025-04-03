package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL AST 中方法调用节点
 *
 * @author clouds
 */
public class SqlMethodInvokeExpr extends AbstractSqlExprImpl {

    /**
     * 方法的名称
     */
    protected String methodName;

    /**
     * 方法的参数
     */
    protected final List<SqlExpr> arguments = new ArrayList();

    /**
     * 对应数据表列
     */
    private String resolvedColumn;

    /**
     * 归属的数据库表
     */
    private String resolvedOwnerTable;

    public SqlMethodInvokeExpr() {
    }

    public SqlMethodInvokeExpr(String methodName) {
        this.methodName = methodName;
    }

    public SqlMethodInvokeExpr(String methodName, SqlExpr... args) {
        this.methodName = methodName;

        for (int i = 0; i < args.length; i++) {
            this.addArgument(args[i]);
        }
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<SqlExpr> getArguments() {
        return this.arguments;
    }

    public void addArgument(SqlExpr arg) {
        this.arguments.add(arg);
        this.addChild(arg);
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
    public void accept(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (SqlExpr arg : this.arguments) {
                if (arg != null) {
                    arg.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public List getChildren() {
        return this.arguments;
    }

    @Override
    public SqlMethodInvokeExpr cloneMe() {
        SqlMethodInvokeExpr x = new SqlMethodInvokeExpr();
        x.methodName = this.methodName;
        for (SqlExpr arg : this.arguments) {
            x.addArgument(arg.cloneMe());
        }

        return x;
    }
}

