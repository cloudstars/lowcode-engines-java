package net.cf.form.repository.sql.ast;

import net.cf.form.repository.sql.util.SqlVisitorUtils;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.*;

/**
 * SQL AST 节点实现类
 *
 * @author clouds
 */
public abstract class SqlObjectImpl implements SqlObject {

    protected SqlObject parent;
    protected Map<String, Object> attributes;
    protected SqlCommentHint hint;
    protected int sourceLine;
    protected int sourceColumn;

    public SqlObjectImpl() {
    }

    @Override
    public final void accept(SqlAstVisitor visitor) {
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
    protected abstract void accept0(SqlAstVisitor visitor);

    /**
     * 让儿子节点接受一个visitor
     *
     * @param visitor
     * @param children
     */
    protected final void nullSafeAcceptChild(SqlAstVisitor visitor, List<? extends SqlObject> children) {
        if (children != null) {
            for(int i = 0; i < children.size(); ++i) {
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
    protected <T extends SqlObject> void addChildren(Collection<T> children) {
        if (children != null) {
            for (T child : children) {
                this.addChild(child);
            }
        }
    }

    @Override
    public void output(Appendable appendable) {
        this.accept(SqlVisitorUtils.createAstOutputVisitor(appendable));
    }

    @Override
    public SqlObject _clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.output(builder);
        return builder.toString();
    }

    public Map<String, Object> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap(1);
        }

        return this.attributes;
    }

    public Object getAttribute(String name) {
        return this.attributes == null ? null : this.attributes.get(name);
    }

    public boolean containsAttribute(String name) {
        return this.attributes == null ? false : this.attributes.containsKey(name);
    }

    public void putAttribute(String name, Object value) {
        if (this.attributes == null) {
            this.attributes = new HashMap(1);
        }

        this.attributes.put(name, value);
    }

    public Map<String, Object> getAttributesDirect() {
        return this.attributes;
    }

    public void addBeforeComment(String comment) {
        if (comment != null) {
            if (this.attributes == null) {
                this.attributes = new HashMap(1);
            }

            List<String> comments = (List)this.attributes.get("rowFormat.before_comment");
            if (comments == null) {
                comments = new ArrayList(2);
                this.attributes.put("rowFormat.before_comment", comments);
            }

            ((List)comments).add(comment);
        }
    }

    public void addBeforeComment(List<String> comments) {
        if (this.attributes == null) {
            this.attributes = new HashMap(1);
        }

        List<String> attrComments = (List)this.attributes.get("rowFormat.before_comment");
        if (attrComments == null) {
            this.attributes.put("rowFormat.before_comment", comments);
        } else {
            attrComments.addAll(comments);
        }

    }

    public List<String> getBeforeCommentsDirect() {
        return this.attributes == null ? null : (List)this.attributes.get("rowFormat.before_comment");
    }

    public void addAfterComment(String comment) {
        if (this.attributes == null) {
            this.attributes = new HashMap(1);
        }

        List<String> comments = (List)this.attributes.get("rowFormat.after_comment");
        if (comments == null) {
            comments = new ArrayList(2);
            this.attributes.put("rowFormat.after_comment", comments);
        }

        ((List)comments).add(comment);
    }

    public void addAfterComment(List<String> comments) {
        if (comments != null) {
            if (this.attributes == null) {
                this.attributes = new HashMap(1);
            }

            List<String> attrComments = (List)this.attributes.get("rowFormat.after_comment");
            if (attrComments == null) {
                this.attributes.put("rowFormat.after_comment", comments);
            } else {
                attrComments.addAll(comments);
            }

        }
    }

    public List<String> getAfterCommentsDirect() {
        return this.attributes == null ? null : (List)this.attributes.get("rowFormat.after_comment");
    }

    public boolean hasBeforeComment() {
        if (this.attributes == null) {
            return false;
        } else {
            List<String> comments = (List)this.attributes.get("rowFormat.before_comment");
            if (comments == null) {
                return false;
            } else {
                return !comments.isEmpty();
            }
        }
    }

    public boolean hasAfterComment() {
        if (this.attributes == null) {
            return false;
        } else {
            List<String> comments = (List)this.attributes.get("rowFormat.after_comment");
            if (comments == null) {
                return false;
            } else {
                return !comments.isEmpty();
            }
        }
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

    public SqlCommentHint getHint() {
        return this.hint;
    }

    public void setHint(SqlCommentHint hint) {
        this.hint = hint;
    }

    /**
     * 不确定这个怎么用，先保留着
     *
     * @return
     */
    public SqlDataType computeDataType() {
        return null;
    }
}
