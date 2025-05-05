package io.github.cloudstars.lowcode.object.repository.sql.ast;


import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

import java.util.Collections;
import java.util.List;

/**
 * SQL AST 中的节点的顶层抽象
 *
 * @author clouds
 */
public interface SqlObject<V extends SqlAstVisitor> {

    /**
     * 接受一个访问器的访问
     *
     * @param visitor
     */
    void accept(V visitor);

    /**
     * 获取父节点
     *
     * @return
     */
    SqlObject getParent();

    /**
     * 设置父节点
     *
     * @param parent
     */
    void setParent(SqlObject parent);

    /**
     * 获取子节点列表
     *
     * @return
     */
    default List<? extends SqlObject> getChildren() {
        return Collections.emptyList();
    }

    /**
     * 克隆
     *
     * @return
     */
    SqlObject cloneMe();

    /**
     * 输出到 Appendable
     *
     * @param appendable
     */
    void output(Appendable appendable);

    /**
     * 获取注释信息
     *
     * @return
     */
    SqlCommentHint getHint();
}
