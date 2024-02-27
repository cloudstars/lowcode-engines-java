package net.cf.object.engine.oql.visitor;

/**
 * 可打印的遍历器
 *
 * @author clouds
 */
public interface OqlAstPrintableVisitor extends OqlAstVisitor {

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
