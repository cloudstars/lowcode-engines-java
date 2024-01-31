package net.cf.form.engine.repository.oql.ast.expr.operation;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

import java.util.Arrays;
import java.util.List;

/**
 * 二元操作表达式
 *
 * @author clouds
 */
@Deprecated
public class OqlBinaryOpExpr extends OqlExprImpl {

    /**
     * 左表达式
     */
    protected QqlExpr left;

    /**
     * 右表达式
     */
    protected QqlExpr right;

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

    public OqlBinaryOpExpr(QqlExpr left, OqlBinaryOperator operator, QqlExpr right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.addChildren(Arrays.asList(left, right));
    }

    public QqlExpr getLeft() {
        return left;
    }

    public void setLeft(QqlExpr left) {
        this.left = left;
    }

    public QqlExpr getRight() {
        return right;
    }

    public void setRight(QqlExpr right) {
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
