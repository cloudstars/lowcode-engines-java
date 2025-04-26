package io.github.cloudstars.lowcode.formula.commons.ast.expr.operation;

import io.github.cloudstars.lowcode.formula.commons.ast.expr.AbstractFxExprImpl;
import io.github.cloudstars.lowcode.formula.commons.ast.expr.FxExpr;
import io.github.cloudstars.lowcode.formula.commons.visitor.FxAstVisitor;

import java.util.Arrays;
import java.util.List;

/**
 * 二元操作表达式
 *
 * @author clouds
 */
public class FxBinaryOpExpr extends AbstractFxExprImpl {

    /**
     * 左表达式
     */
    protected FxExpr left;

    /**
     * 右表达式
     */
    protected FxExpr right;

    /**
     * 二元操作符
     */
    protected FxBinaryOperator operator;

    /**
     * 是否有加小括号
     */
    private boolean parenthesized = false;


    public FxBinaryOpExpr() {
    }

    public FxBinaryOpExpr(FxExpr left, FxBinaryOperator operator, FxExpr right) {
        if (left != null) {
            left.setParent(this);
        }

        if (right != null) {
            right.setParent(this);
        }

        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public FxExpr getLeft() {
        return left;
    }

    public void setLeft(FxExpr left) {
        this.left = left;
    }

    public FxExpr getRight() {
        return right;
    }

    public void setRight(FxExpr right) {
        this.right = right;
    }

    public FxBinaryOperator getOperator() {
        return operator;
    }

    public void setOperator(FxBinaryOperator operator) {
        this.operator = operator;
    }

    public boolean isParenthesized() {
        return parenthesized;
    }

    public void setParenthesized(boolean parenthesized) {
        this.parenthesized = parenthesized;
    }

    @Override
    public void accept(FxAstVisitor visitor) {
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
    public FxExpr clone() {
        FxBinaryOpExpr x = new FxBinaryOpExpr();
        if (this.left != null) {
            x.setLeft(this.left.clone());
        }

        if (this.right != null) {
            x.setRight(this.right.clone());
        }

        x.operator = this.operator;
        x.parenthesized = this.parenthesized;

        return x;
    }
}
