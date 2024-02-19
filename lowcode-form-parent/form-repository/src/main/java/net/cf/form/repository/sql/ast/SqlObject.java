package net.cf.form.repository.sql.ast;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * Sql AST 中的节点的顶层抽象
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
