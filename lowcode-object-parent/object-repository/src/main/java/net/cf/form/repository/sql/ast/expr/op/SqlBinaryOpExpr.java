package net.cf.form.repository.sql.ast.expr.op;


import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlLiteralExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlNullExpr;
import net.cf.form.repository.sql.util.SqlExprUtils;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlBinaryOpExpr extends AbstractSqlExprImpl implements SqlReplaceable {

    protected SqlExpr left;
    protected SqlExpr right;
    protected SqlBinaryOperator operator;
    private boolean parenthesized;
    protected transient List<SqlObject> mergedList;

    public SqlBinaryOpExpr() {
    }

    public SqlBinaryOpExpr(SqlExpr left, SqlBinaryOperator operator, SqlExpr right) {
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

    public SqlExpr getLeft() {
        return this.left;
    }

    public void setLeft(SqlExpr left) {
        if (left != null) {
            left.setParent(this);
        }

        this.left = left;
    }

    public SqlExpr getRight() {
        return this.right;
    }

    public void setRight(SqlExpr right) {
        if (right != null) {
            right.setParent(this);
        }

        this.right = right;
    }

    public SqlBinaryOperator getOperator() {
        return this.operator;
    }

    public void setOperator(SqlBinaryOperator operator) {
        this.operator = operator;
    }

    public boolean isParenthesized() {
        return this.parenthesized;
    }

    public void setParenthesized(boolean parenthesized) {
        this.parenthesized = parenthesized;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
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
    public List<SqlObject> getChildren() {
        return Arrays.asList(this.left, this.right);
    }

    @Override
    public SqlBinaryOpExpr cloneMe() {
        SqlBinaryOpExpr x = new SqlBinaryOpExpr();
        this.cloneT(x);
        return x;
    }

    protected void cloneT(SqlBinaryOpExpr x) {
        if (this.left != null) {
            x.setLeft(this.left.cloneMe());
        }

        if (this.right != null) {
            x.setRight(this.right.cloneMe());
        }

        x.operator = this.operator;
        x.parenthesized = this.parenthesized;
        if (this.hint != null) {
            x.hint = this.hint.cloneMe();
        }
    }

    public static SqlExpr combine(List<? extends SqlExpr> items, SqlBinaryOperator op) {
        if (items != null && op != null) {
            int size = items.size();
            if (size == 0) {
                return null;
            } else if (size == 1) {
                return items.get(0);
            } else {
                SqlBinaryOpExpr expr = new SqlBinaryOpExpr(items.get(0), op, items.get(1));
                for (int i = 2; i < size; ++i) {
                    SqlExpr item = items.get(i);
                    expr = new SqlBinaryOpExpr(expr, op, item);
                }

                return expr;
            }
        } else {
            return null;
        }
    }

    public static List<SqlExpr> split(SqlBinaryOpExpr x) {
        return split(x, x.getOperator());
    }

    public static List<SqlExpr> split(SqlExpr x, SqlBinaryOperator op) {
        if (x instanceof SqlBinaryOpExprGroup) {
            SqlBinaryOpExprGroup group = (SqlBinaryOpExprGroup) x;
            if (group.getOperator() == op) {
                return new ArrayList(group.getItems());
            }
        } else if (x instanceof SqlBinaryOpExpr) {
            return split((SqlBinaryOpExpr) x, op);
        }

        List<SqlExpr> list = new ArrayList(1);
        list.add(x);
        return list;
    }

    public static List<SqlExpr> split(SqlBinaryOpExpr x, SqlBinaryOperator op) {
        ArrayList groupList;
        if (x.getOperator() != op) {
            groupList = new ArrayList(1);
            groupList.add(x);
            return groupList;
        } else {
            groupList = new ArrayList();
            split(groupList, x, op);
            return groupList;
        }
    }

    public static void split(List<SqlExpr> outList, SqlExpr expr, SqlBinaryOperator op) {
        if (expr != null) {
            if (!(expr instanceof SqlBinaryOpExpr)) {
                outList.add(expr);
            } else {
                SqlBinaryOpExpr binaryExpr = (SqlBinaryOpExpr) expr;
                if (binaryExpr.getOperator() != op) {
                    outList.add(binaryExpr);
                } else {
                    List<SqlExpr> rightList = new ArrayList();
                    rightList.add(binaryExpr.getRight());
                    SqlExpr left = binaryExpr.getLeft();

                    while (true) {
                        if (left instanceof SqlBinaryOpExpr) {
                            SqlBinaryOpExpr leftBinary = (SqlBinaryOpExpr) left;
                            if (leftBinary.operator == op) {
                                left = leftBinary.getLeft();
                                rightList.add(leftBinary.getRight());
                                continue;
                            }

                            outList.add(leftBinary);
                            break;
                        }

                        outList.add(left);
                        break;
                    }

                    for (int i = rightList.size() - 1; i >= 0; --i) {
                        SqlExpr right = (SqlExpr) rightList.get(i);
                        if (right instanceof SqlBinaryOpExpr) {
                            SqlBinaryOpExpr binaryRight = (SqlBinaryOpExpr) right;
                            if (binaryRight.operator == op) {
                                SqlExpr rightRight = binaryRight.getLeft();
                                SqlBinaryOpExpr rightRightBinary;
                                if (rightRight instanceof SqlBinaryOpExpr) {
                                    rightRightBinary = (SqlBinaryOpExpr) rightRight;
                                    if (rightRightBinary.operator == op) {
                                        split(outList, rightRightBinary, op);
                                    } else {
                                        outList.add(rightRightBinary);
                                    }
                                } else {
                                    outList.add(rightRight);
                                }

                                rightRight = binaryRight.getRight();
                                if (rightRight instanceof SqlBinaryOpExpr) {
                                    rightRightBinary = (SqlBinaryOpExpr) rightRight;
                                    if (rightRightBinary.operator == op) {
                                        split(outList, rightRightBinary, op);
                                    } else {
                                        outList.add(rightRightBinary);
                                    }
                                } else {
                                    outList.add(rightRight);
                                }
                            } else {
                                outList.add(binaryRight);
                            }
                        } else {
                            outList.add(right);
                        }
                    }

                }
            }
        }
    }

    public static SqlExpr and(final SqlExpr a, final SqlExpr b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        SqlExpr tA = a;
        SqlExpr tB = b;
        SqlBinaryOpExprGroup group;
        if (tA instanceof SqlBinaryOpExprGroup) {
            group = (SqlBinaryOpExprGroup) tA;
            if (group.getOperator() == SqlBinaryOperator.BOOLEAN_AND) {
                group.add(tB);
                return group;
            }

            if (group.getOperator() == SqlBinaryOperator.BOOLEAN_OR && group.getItems().size() == 1) {
                tA = ((SqlExpr) group.getItems().get(0)).cloneMe();
            }
        }

        if (tB instanceof SqlBinaryOpExpr) {
            SqlBinaryOpExpr bb = (SqlBinaryOpExpr) tB;
            if (bb.operator == SqlBinaryOperator.BOOLEAN_AND) {
                return and(and(tA, bb.left), bb.right);
            }
        } else if (tB instanceof SqlBinaryOpExprGroup) {
            group = (SqlBinaryOpExprGroup) tB;
            if (group.getOperator() == SqlBinaryOperator.BOOLEAN_OR && group.getItems().size() == 1) {
                tB = ((SqlExpr) group.getItems().get(0)).cloneMe();
            }
        }

        if (a instanceof SqlBinaryOpExpr && b instanceof SqlBinaryOpExprGroup && ((SqlBinaryOpExprGroup) b).getOperator() == SqlBinaryOperator.BOOLEAN_AND) {
            group = (SqlBinaryOpExprGroup) b;
            group.add(0, a);
            return group;
        } else {
            return new SqlBinaryOpExpr(a, SqlBinaryOperator.BOOLEAN_AND, b);
        }
    }

    public static SqlExpr and(SqlExpr a, SqlExpr b, SqlExpr c) {
        return and(and(a, b), c);
    }

    public static SqlExpr or(SqlExpr a, SqlExpr b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            if (a instanceof SqlBinaryOpExprGroup) {
                SqlBinaryOpExprGroup group = (SqlBinaryOpExprGroup) a;
                if (group.getOperator() == SqlBinaryOperator.BOOLEAN_OR) {
                    group.add(b);
                    return group;
                }
            }

            if (b instanceof SqlBinaryOpExpr) {
                SqlBinaryOpExpr bb = (SqlBinaryOpExpr) b;
                if (bb.operator == SqlBinaryOperator.BOOLEAN_OR) {
                    return or(or(a, bb.left), bb.right);
                }
            }

            return new SqlBinaryOpExpr(a, SqlBinaryOperator.BOOLEAN_OR, b);
        }
    }

    public static SqlExpr or(List<? extends SqlExpr> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            SqlExpr first = (SqlExpr) list.get(0);

            for (int i = 1; i < list.size(); ++i) {
                first = or(first, (SqlExpr) list.get(i));
            }

            return first;
        }
    }

    public static SqlBinaryOpExpr isNotNull(SqlExpr expr) {
        return new SqlBinaryOpExpr(expr, SqlBinaryOperator.IS_NOT, new SqlNullExpr());
    }

    public static SqlBinaryOpExpr isNull(SqlExpr expr) {
        return new SqlBinaryOpExpr(expr, SqlBinaryOperator.IS, new SqlNullExpr());
    }

    public boolean replace(SqlExpr expr, SqlExpr target) {
        SqlObject parent = this.getParent();
        if (this.left == expr) {
            if (target == null) {
                return parent instanceof SqlReplaceable ? ((SqlReplaceable) parent).replace(this, this.right) : false;
            } else {
                this.setLeft(target);
                return true;
            }
        } else if (this.right == expr) {
            if (target == null) {
                return parent instanceof SqlReplaceable ? ((SqlReplaceable) parent).replace(this, this.left) : false;
            } else {
                this.setRight(target);
                return true;
            }
        } else {
            if (this.left instanceof SqlBinaryOpExpr) {
                SqlBinaryOperator operator = ((SqlBinaryOpExpr) this.left).getOperator();
                if (operator == SqlBinaryOperator.BOOLEAN_AND && ((SqlBinaryOpExpr) this.left).replace(expr, target)) {
                    return true;
                }
            }

            return false;
        }
    }

    public SqlExpr other(SqlExpr x) {
        if (x == this.left) {
            return this.right;
        } else {
            return x == this.right ? this.left : null;
        }
    }

    public boolean contains(SqlExpr item) {
        if (item instanceof SqlBinaryOpExpr) {
            if (this.equalsIgoreOrder((SqlBinaryOpExpr) item)) {
                return true;
            } else {
                return this.left.equals(item) || this.right.equals(item);
            }
        } else {
            return false;
        }
    }

    public boolean equalsIgoreOrder(SqlBinaryOpExpr other) {
        if (this == other) {
            return true;
        } else if (other == null) {
            return false;
        } else if (this.operator != other.operator) {
            return false;
        } else {
            return ObjectUtils.nullSafeEquals(this.left, other.left);
        }
    }

    public List<SqlObject> getMergedList() {
        return this.mergedList;
    }

    private static boolean mergeEqual(SqlExpr a, SqlExpr b) {
        if (!(a instanceof SqlBinaryOpExpr)) {
            return false;
        } else if (!(b instanceof SqlBinaryOpExpr)) {
            return false;
        } else {
            SqlBinaryOpExpr binaryA = (SqlBinaryOpExpr) a;
            SqlBinaryOpExpr binaryB = (SqlBinaryOpExpr) b;
            if (binaryA.operator != SqlBinaryOperator.EQUALITY) {
                return false;
            } else if (binaryB.operator != SqlBinaryOperator.EQUALITY) {
                return false;
            } else if (!(binaryA.right instanceof SqlLiteralExpr) && !(binaryA.right instanceof SqlVariantRefExpr)) {
                return false;
            } else {
                return !(binaryB.right instanceof SqlLiteralExpr) && !(binaryB.right instanceof SqlVariantRefExpr) ? false : binaryA.left.equals(binaryB.left);
            }
        }
    }

    public static boolean isOr(SqlExpr x) {
        return x instanceof SqlBinaryOpExpr && ((SqlBinaryOpExpr) x).getOperator() == SqlBinaryOperator.BOOLEAN_OR;
    }

    public static boolean isAnd(SqlExpr x) {
        return x instanceof SqlBinaryOpExpr && ((SqlBinaryOpExpr) x).getOperator() == SqlBinaryOperator.BOOLEAN_AND;
    }

    public boolean isLeftNameAndRightLiteral() {
        return this.left instanceof SqlName && this.right instanceof SqlLiteralExpr;
    }

    public boolean isLeftFunctionAndRightLiteral() {
        return this.left instanceof SqlMethodInvokeExpr && this.right instanceof SqlLiteralExpr;
    }

    public boolean isNameAndLiteral() {
        return this.left instanceof SqlLiteralExpr && this.right instanceof SqlName || this.left instanceof SqlName && this.right instanceof SqlLiteralExpr;
    }

    public boolean isBothName() {
        return this.left instanceof SqlName && this.right instanceof SqlName;
    }

    public boolean isLeftLiteralAndRightName() {
        return this.right instanceof SqlName && this.left instanceof SqlLiteralExpr;
    }

    public static SqlBinaryOpExpr conditionEq(String column, String value) {
        return new SqlBinaryOpExpr(SqlExprUtils.toSqlExpr(column), SqlBinaryOperator.EQUALITY, new SqlCharExpr(value));
    }

    public static SqlBinaryOpExpr conditionEq(String column, int value) {
        return new SqlBinaryOpExpr(SqlExprUtils.toSqlExpr(column), SqlBinaryOperator.EQUALITY, new SqlIntegerExpr(value));
    }

    public static SqlBinaryOpExpr conditionLike(String column, String value) {
        return new SqlBinaryOpExpr(SqlExprUtils.toSqlExpr(column), SqlBinaryOperator.LIKE, new SqlCharExpr(value));
    }

    public static SqlBinaryOpExpr conditionLike(String column, SqlExpr value) {
        return new SqlBinaryOpExpr(SqlExprUtils.toSqlExpr(column), SqlBinaryOperator.LIKE, value);
    }

    public static SqlBinaryOpExpr eq(SqlExpr a, SqlExpr b) {
        return new SqlBinaryOpExpr(a, SqlBinaryOperator.EQUALITY, b);
    }
}
