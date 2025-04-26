package io.github.cloudstars.lowcode.formula.commons.ast;


import io.github.cloudstars.lowcode.formula.commons.visitor.FxAstVisitor;

/**
 * 注释
 *
 * @author clouds
 */
public class FxCommentHint extends AbstractFxObjectImpl implements FxHint {
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

    public void accept(FxAstVisitor visitor) {
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
