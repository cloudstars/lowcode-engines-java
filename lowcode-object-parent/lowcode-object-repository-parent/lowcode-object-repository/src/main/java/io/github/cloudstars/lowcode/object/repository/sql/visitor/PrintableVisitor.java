package io.github.cloudstars.lowcode.object.repository.sql.visitor;

/**
 * 可打印的迭代器
 *
 * @author clouds
 */
public interface PrintableVisitor extends SqlAstVisitor {

    /**
     * 是否转大写打印
     *
     * @return
     */
    boolean isUppercase();

    /**
     * 打印一个字符串
     *
     * @param text
     */
    void print(String text);

    /**
     * 打印单个字符
     *
     * @param value
     */
    void print(char value);

}
