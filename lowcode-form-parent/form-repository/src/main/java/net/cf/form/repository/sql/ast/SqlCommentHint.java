package net.cf.form.repository.sql.ast;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * 注释
 *
 * @author clouds
 */
public class SqlCommentHint extends SqlObjectImpl implements SqlHint {
    private String text;

    public SqlCommentHint() {
    }

    public SqlCommentHint(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlCommentHint _clone() {
        return new SqlCommentHint(this.text);
    }

    @Override
    public String toString() {
        return "/*" + this.text + "*/";
    }
}
