package net.cf.form.engine.repository.sql.ast;

import net.cf.form.engine.repository.sql.visitor.SqlAstVisitor;

import java.util.List;
import java.util.Map;

/**
 * SQL AST 中的节点的顶层抽象
 *
 * @author clouds
 */
public interface SqlObject {

    /**
     * 接受一个访问器的访问
     *
     * @param visitor
     */
    void accept(SqlAstVisitor visitor);

    /**
     * 克隆
     *
     * 备注：起名为"_clone"是为了避免与Object的clone名称冲突
     *
     * @return
     */
    SqlObject _clone();

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

    Map<String, Object> getAttributes();

    boolean containsAttribute(String var1);

    Object getAttribute(String var1);

    void putAttribute(String var1, Object var2);

    Map<String, Object> getAttributesDirect();

    /**
     * 获取子节点列表
     *
     * @return
     */
    /*default List<T> getChildren() {
        return Collections.EMPTY_LIST;
    }*/

    /**
     * 输出到 Appendable
     *
     * @param appendable
     */
    void output(Appendable appendable);

    void addBeforeComment(String var1);

    void addBeforeComment(List<String> var1);

    List<String> getBeforeCommentsDirect();

    void addAfterComment(String var1);

    void addAfterComment(List<String> var1);

    List<String> getAfterCommentsDirect();

    boolean hasBeforeComment();

    boolean hasAfterComment();

}
