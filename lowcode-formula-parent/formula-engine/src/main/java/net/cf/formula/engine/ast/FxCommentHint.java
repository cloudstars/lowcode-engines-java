package net.cf.formula.engine.ast;

import net.cf.formula.engine.visitor.FxAstVisitor;

/**
 * 注释
 *
 * @author clouds
 */
public class FxCommentHint extends FxObjectImpl implements FxHint {
    private String text;

    public FxCommentHint() {
    }

    public FxCommentHint(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    protected void accept0(FxAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    public FxCommentHint clone() {
        return new FxCommentHint(this.text);
    }

    public String toString() {
        return "/*" + this.text + "*/";
    }
}
