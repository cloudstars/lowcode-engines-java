package net.cf.form.repository.sql.ast;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * Sql Ast 中的节点的顶层抽象
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
     * 克隆自已
     *
     * 备注：起名为"cloneMe"是为了避免与Object的clone名称冲突
     *
     * @return
     */
    SqlObject cloneMe();

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
     * 输出到 Appendable
     *
     * @param appendable
     */
    void output(Appendable appendable);
}
