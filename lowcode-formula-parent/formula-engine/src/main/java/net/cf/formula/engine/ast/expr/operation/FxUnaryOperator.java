package net.cf.formula.engine.ast.expr.operation;

/**
 * 一元操作符
 *
 * @author clouds
 */
public enum FxUnaryOperator {

    NOT("!", 170);
    private final String name;

    private final int priority;

    public String getName() {
        return name;
    }

    public static int getPriority(FxBinaryOperator operator) {
        return operator.getPriority();
    }

    private FxUnaryOperator(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}
