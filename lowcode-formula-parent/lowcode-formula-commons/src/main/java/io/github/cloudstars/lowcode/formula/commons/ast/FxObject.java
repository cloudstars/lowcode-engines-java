package io.github.cloudstars.lowcode.formula.commons.ast;

import io.github.cloudstars.lowcode.formula.commons.visitor.FxAstVisitor;

/**
 *
 * 公式解析过程中的对象，可能是AST上的节点，也可能是注释等
 *
 * @author clouds
 *
 */
public interface FxObject {

    /**
     * 接受一个访问器
     *
     * @param visitor
     */
    void accept(FxAstVisitor visitor);

    /**
     * 克隆
     *
     * @return
     */
    FxObject clone();

    /**
     * 获取父节点
     * 
     * @return 父节点
     */
    FxObject getParent();

    /**
     * 设置父节点
     *
     * @param parent
     */
    void setParent(FxObject parent);

    /**
     * 输出到 Appendable
     *
     * @param appendable
     */
    void output(Appendable appendable);
}
