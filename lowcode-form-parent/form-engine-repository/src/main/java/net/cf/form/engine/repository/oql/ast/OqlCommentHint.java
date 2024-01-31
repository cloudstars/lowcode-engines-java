package net.cf.form.engine.repository.oql.ast;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;

/**
 * 注释
 *
 * @author clouds
 */
@Deprecated
public class OqlCommentHint extends OqlObjectImpl implements OqlHint {
    private String text;

    public OqlCommentHint() {
    }

    public OqlCommentHint(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public OqlCommentHint clone() {
        return new OqlCommentHint(this.text);
    }

    @Override
    public String toString() {
        return "/*" + this.text + "*/";
    }
}
