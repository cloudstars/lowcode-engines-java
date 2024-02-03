package net.cf.form.repository.sql.ast.expr.operation;

/**
 * 二元操作符
 *
 * @author clouds
 */
public enum SqlBinaryOperator {

    /* 算术运算符 */
    Multiply("*", 60),
    Divide("/", 60),
    Modulus("%", 60),
    Add("+", 70),
    Subtract("-", 70),

    /* 关系运算符 */
    Equality("=", 110),
    NotEqual("!=", 110),
    GreaterThan(">", 110),
    GreaterThanOrEqual(">=", 110),
    LessThan("<", 110),
    LessThanOrEqual("<=", 110),
    Like("LIKE", 110),
    NotLike("NOT_LIKE", 110),
    In("IN", 110),
    Contains("CONTAINS", 110),
    Is("IS", 110),
    IsNot("IS NOT", 110),
    Escape("ESCAPE", 110),
    /* 逻辑运算符 */
    BooleanAnd("AND", 140),
    BooleanXor("XOR", 150),
    BooleanOr("OR", 160),
    Not("NOT", 170);

    public final String name;

    public final int priority;

    public static int getPriority(SqlBinaryOperator operator) {
        return operator.getPriority();
    }

    private SqlBinaryOperator(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return this.name;
    }

    public int getPriority() {
        return this.priority;
    }

    /**
     * 是否关系运算符
     *
     * @return
     */
    public boolean isRelational() {
        switch (this) {
            case Equality:
            case NotEqual:
            case GreaterThan:
            case GreaterThanOrEqual:
            case LessThan:
            case LessThanOrEqual:
            case Like:
            case NotLike:
            case In:
                return true;
            default:
                return false;
        }
    }

    /**
     * 是否逻辑运算符
     *
     * @return
     */
    public boolean isLogical() {
        return this == BooleanAnd || this == BooleanOr || this == Not;
    }

    /**
     * 是否算术运算符
     *
     * @return
     */
    public boolean isArithmetic() {
        switch (this) {
            case Add:
            case Subtract:
            case Multiply:
            case Divide:
            case Modulus:
                return true;
            default:
                return false;
        }
    }
}
