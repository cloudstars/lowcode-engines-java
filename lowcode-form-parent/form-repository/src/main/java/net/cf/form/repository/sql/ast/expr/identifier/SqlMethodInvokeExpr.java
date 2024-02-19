package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.SqlExprImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL AST 中方法调用节点
 *
 * @author clouds
 */
public class SqlMethodInvokeExpr extends SqlExprImpl {

    protected String methodName;

    protected final List<SqlExpr> arguments = new ArrayList();


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

    public void setArgument(int i, SqlExpr arg) {
        this.arguments.set(i, arg);
        this.addChild(arg);
    }

    public void addArgument(int i, SqlExpr arg) {
        this.arguments.add(i, arg);
        this.addChild(arg);
    }

    public void addArgument(SqlExpr arg) {
        this.arguments.add(arg);
        this.addChild(arg);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
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
    public SqlMethodInvokeExpr _clone() {
        SqlMethodInvokeExpr x = new SqlMethodInvokeExpr();
        x.methodName = this.methodName;
        for (SqlExpr arg : this.arguments) {
            x.addArgument(arg._clone());
        }

        return x;
    }
}

