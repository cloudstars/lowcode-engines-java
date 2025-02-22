package net.cf.object.fx.parser.ast;

import net.cf.object.fx.parser.ast.visitor.FxAstVisitor;

/**
 *
 * @author clouds
 *
 * 最基础的一个概念，在 公式的 AST 上任何一个节点都是一种 Fx 对象
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
