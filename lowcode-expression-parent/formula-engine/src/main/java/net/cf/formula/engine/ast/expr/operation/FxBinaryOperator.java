package net.cf.formula.engine.ast.expr.operation;

/**
 * 二元操作符
 *
 * @author clouds
 */
public enum FxBinaryOperator {

    /* 算术运算符 */
    MULTIPLY("*", 60),
    DIVIDE("/", 60),
    MODULUS("%", 60),
    ADD("+", 70),
    SUBTRACT("-", 70),

    /* 关系运算符 */
    EQUAL("==", 110),
    NOT_EQUAL("!=", 110),
    GREATER_THAN(">", 110),
    GREATER_THAN_OR_EQUAL(">=", 110),
    LESS_THAN("<", 110),
    LESS_THAN_OR_EQUAL("<=", 110),

    /* 逻辑运算符 */
    AND("&&", 150),
    OR("||", 160);

    public final String name;

    public final int priority;

    public static int getPriority(FxBinaryOperator operator) {
        return operator.getPriority();
    }

    private FxBinaryOperator(String name, int priority) {
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
            case EQUAL:
            case NOT_EQUAL:
            case GREATER_THAN:
            case GREATER_THAN_OR_EQUAL:
            case LESS_THAN:
            case LESS_THAN_OR_EQUAL:
            //case Like:
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
        return this == AND || this == OR;
    }

    /**
     * 是否算术运算符
     *
     * @return
     */
    public boolean isArithmetic() {
        switch (this) {
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case DIVIDE:
            case MODULUS:
                return true;
            default:
                return false;
        }
    }
}
