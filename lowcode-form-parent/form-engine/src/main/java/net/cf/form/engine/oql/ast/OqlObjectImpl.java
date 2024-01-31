package net.cf.form.engine.oql.ast;

import net.cf.form.engine.oql.util.OqlUtils;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.Collection;

/**
 * OQL AST对象抽象实现类
 *
 * @author clouds
 */
public abstract class OqlObjectImpl implements OqlObject {

    /**
     * 父节点
     */
    protected OqlObject parent;

    public OqlObjectImpl() {
    }

    @Override
    public final void accept(OqlAstVisitor visitor) {
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
    protected abstract void accept0(OqlAstVisitor visitor);

    /**
     * 儿子节点接受一个visitor
     *
     * @param visitor
     * @param children
     */
    protected final <T extends OqlObject> void acceptChildren(OqlAstVisitor visitor, Collection<T> children) {
        if (children != null) {
            for (T child : children) {
                this.acceptChild(visitor, child);
            }
        }
    }


    /**
     * 儿子节点接受一个visitor
     *
     * @param visitor
     * @param child
     */
    protected final void acceptChild(OqlAstVisitor visitor, OqlObject child) {
        if (child != null) {
            child.accept(visitor);
        }
    }

    @Override
    public OqlObject getParent() {
        return this.parent;
    }

    @Override
    public void setParent(OqlObject parent) {
        this.parent = parent;
    }

    /**
     * 添加一个儿子
     *
     * @param child
     */
    protected void addChild(OqlObject child) {
        if (child != null) {
            child.setParent(this);
        }
    }

    /**
     * 添加一组儿子
     *
     * @param children
     */
    protected <T extends OqlObject> void addChildren(Collection<T> children) {
        if (children != null) {
            for (T child : children) {
                this.addChild(child);
            }
        }
    }

    @Override
    public void output(Appendable buf) {
        this.accept(OqlUtils.createAstOutputVisitor(buf));
    }

    @Override
    public OqlObject clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.output(builder);
        return builder.toString();
    }
}
