package net.cf.formula.engine.visitor;

/**
 * 可打印的迭代器
 *
 * @author clouds
 */
public interface PrintableVisitor extends FxAstVisitor {

    /**
     * 是否转大写打印
     *
     * @return
     */
    boolean isUpperCase();

    /**
     * 打印单个字符
     *
     * @param value
     */
    void print(char value);

    /**
     * 打印一个字符串
     *
     * @param text
     */
    void print(String text);
}
