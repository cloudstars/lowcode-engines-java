package net.cf.form.engine.oql.ast.expr.operation;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.AbstractOqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.Arrays;
import java.util.List;

/**
 * 二元操作表达式
 *
 * @author clouds
 */
public class OqlBinaryOpExpr extends AbstractOqlExprImpl {

    /**
     * 左表达式
     */
    protected OqlExpr left;

    /**
     * 右表达式
     */
    protected OqlExpr right;

    /**
     * 二元操作符
     */
    protected OqlBinaryOperator operator;

    /**
     * 是否有加小括号
     */
    private boolean parenthesized = false;


    public OqlBinaryOpExpr() {
    }

    public OqlBinaryOpExpr(OqlExpr left, OqlBinaryOperator operator, OqlExpr right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.addChildren(Arrays.asList(left, right));
    }

    public OqlExpr getLeft() {
        return left;
    }

    public void setLeft(OqlExpr left) {
        this.left = left;
    }

    public OqlExpr getRight() {
        return right;
    }

    public void setRight(OqlExpr right) {
        this.right = right;
    }

    public OqlBinaryOperator getOperator() {
        return operator;
    }

    public void setOperator(OqlBinaryOperator operator) {
        this.operator = operator;
    }

    public boolean isParenthesized() {
        return parenthesized;
    }

    public void setParenthesized(boolean parenthesized) {
        this.parenthesized = parenthesized;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.left != null) {
                this.left.accept(visitor);
            }

            if (this.right != null) {
                this.right.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public List getChildren() {
        return Arrays.asList(this.left, this.right);
    }


    @Override
    public OqlBinaryOpExpr clone() {
        OqlBinaryOpExpr x = new OqlBinaryOpExpr();
        cloneTarget(x);
        return x;
    }

    protected void cloneTarget(OqlBinaryOpExpr x) {
        if (this.left != null) {
            x.setLeft(this.left.clone());
        }

        if (this.right != null) {
            x.setRight(this.right.clone());
        }

        x.operator = this.operator;
        x.parenthesized = this.parenthesized;
    }
}
