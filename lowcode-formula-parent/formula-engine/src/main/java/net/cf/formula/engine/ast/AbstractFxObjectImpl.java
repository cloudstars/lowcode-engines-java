package net.cf.formula.engine.ast;

import net.cf.formula.engine.util.FxUtils;
import net.cf.formula.engine.visitor.FxAstVisitor;

import java.util.List;

/**
 * 公式AST对象抽象实现类
 *
 * @author clouds
 */
public abstract class AbstractFxObjectImpl implements FxObject {

    /**
     * 父节点
     */
    protected FxObject parent;

    public AbstractFxObjectImpl() {
    }

    @Override
    public final void accept(FxAstVisitor visitor) {
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
    protected abstract void accept0(FxAstVisitor visitor);

    protected final void acceptChild(FxAstVisitor visitor, List<? extends FxObject> children) {
        if (children != null) {
            for (int i = 0; i < children.size(); ++i) {
                this.acceptChild(visitor, children.get(i));
            }
        }
    }

    protected final void acceptChild(FxAstVisitor visitor, FxObject child) {
        if (child != null) {
            child.accept(visitor);
        }
    }

    @Override
    public FxObject clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public FxObject getParent() {
        return this.parent;
    }

    @Override
    public void setParent(FxObject parent) {
        this.parent = parent;
    }

    @Override
    public void output(Appendable buf) {
        this.accept(FxUtils.createOutputVisitor(buf));
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        this.output(buf);
        return buf.toString();
    }
}
