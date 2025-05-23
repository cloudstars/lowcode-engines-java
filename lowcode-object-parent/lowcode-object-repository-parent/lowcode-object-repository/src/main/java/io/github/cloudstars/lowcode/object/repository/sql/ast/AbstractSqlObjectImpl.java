package io.github.cloudstars.lowcode.object.repository.sql.ast;


import io.github.cloudstars.lowcode.object.repository.sql.util.SqlVisitorUtils;
import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL AST 节点实现类
 *
 * @author clouds
 */
public abstract class AbstractSqlObjectImpl<V extends SqlAstVisitor> implements SqlObject<V> {

    /**
     * 父节点
     */
    protected SqlObject parent;

    /*
     * 注释信息
     */
    protected SqlCommentHint hint;

    /**
     * 节点的属性
     */
    protected final Map<String, Object> attributes = new HashMap<>();

    /**
     * 表达式在源码中的行号
     */
    protected int sourceLine;

    /**
     * 表达式在源码中的列表
     */
    protected int sourceColumn;

    public AbstractSqlObjectImpl() {
    }

    @Override
    public void accept(V visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException("visitor is null.");
        } else {
            visitor.preVisit(this);
            this.accept0(visitor);
            visitor.postVisit(this);
        }
    }

    /**
     * 接受一个visitor
     *
     * @param visitor
     */
    protected void accept0(V visitor) {}

    /**
     * 让儿子节点接受一个visitor
     *
     * @param visitor
     * @param children
     */
    protected final void nullSafeAcceptChildren(SqlAstVisitor visitor, List<? extends SqlObject> children) {
        if (children != null) {
            for (int i = 0; i < children.size(); ++i) {
                this.nullSafeAcceptChild(visitor, children.get(i));
            }
        }
    }

    /**
     * 让儿子节点接受一个visitor
     *
     * @param visitor
     * @param child
     */
    protected final void nullSafeAcceptChild(SqlAstVisitor visitor, SqlObject child) {
        if (child != null) {
            child.accept(visitor);
        }
    }

    @Override
    public SqlObject getParent() {
        return this.parent;
    }

    @Override
    public void setParent(SqlObject parent) {
        this.parent = parent;
    }

    /**
     * 添加一个儿子
     *
     * @param child
     */
    protected void addChild(SqlObject child) {
        if (child != null) {
            child.setParent(this);
        }
    }

    /**
     * 添加一组儿子
     *
     * @param children
     */
    protected void addChildren(Collection<? extends SqlObject> children) {
        if (children != null) {
            for (SqlObject child : children) {
                this.addChild(child);
            }
        }
    }

    @Override
    public SqlObject cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public void output(Appendable appendable) {
        this.accept(this.createAstOutputVisitor(appendable));
    }

    protected V createAstOutputVisitor(Appendable appendable) {
        return (V) SqlVisitorUtils.createAstOutputVisitor(appendable);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.output(builder);
        return builder.toString();
    }

    @Override
    public SqlCommentHint getHint() {
        return this.hint;
    }

    public void setHint(SqlCommentHint hint) {
        this.hint = hint;
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public Object getAttribute(String name) {
        return this.attributes == null ? null : this.attributes.get(name);
    }

    public boolean containsAttribute(String name) {
        return this.attributes == null ? false : this.attributes.containsKey(name);
    }

    public void putAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public int getSourceLine() {
        return this.sourceLine;
    }

    public void setSourceLine(int sourceLine) {
        this.sourceLine = sourceLine;
    }

    public int getSourceColumn() {
        return this.sourceColumn;
    }

    public void setSourceColumn(int sourceColumn) {
        this.sourceColumn = sourceColumn;
    }
}
