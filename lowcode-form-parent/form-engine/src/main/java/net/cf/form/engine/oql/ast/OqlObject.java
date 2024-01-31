package net.cf.form.engine.oql.ast;

import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.Collections;
import java.util.List;

/**
 * OQL AST 中的基类
 *
 * @author clouds
 */
public interface OqlObject<T extends OqlObject> {

    /**
     * 接受一个访问器的访问
     *
     * @param visitor
     */
    void accept(OqlAstVisitor visitor);

    /**
     * 克隆
     *
     * @return
     */
    T clone();

    /**
     * 获取父节点
     *
     * @return
     */
    OqlObject getParent();

    /**
     * 设置父节点
     *
     * @param parent
     */
    void setParent(OqlObject parent);

    /**
     * 获取子节点列表
     *
     * @return
     */
    default List<T> getChildren() {
        return Collections.EMPTY_LIST;
    }


    /**
     * 输出到Appendable
     *
     * @param appendable
     */
    void output(Appendable appendable);

}
