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
    Equal("==", 110),
    NotEqual("!=", 110),
    GreaterThan(">", 110),
    GreaterThanOrEqual(">=", 110),
    LessThan("<", 110),
    LessThanOrEqual("<=", 110),
    //Like("LIKE", 110),

    /* 逻辑运算符 */
    And("&&", 150),
    Or("||", 160);

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
            case Equal:
            case NotEqual:
            case GreaterThan:
            case GreaterThanOrEqual:
            case LessThan:
            case LessThanOrEqual:
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
        return this == And || this == Or;
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
