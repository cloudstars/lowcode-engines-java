package net.cf.form.repository.sql.ast;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL 注释
 *
 * @author clouds
 */
public class SqlCommentHint extends AbstractSqlObjectImpl implements SqlHint {
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
    public SqlCommentHint cloneMe() {
        return new SqlCommentHint(this.text);
    }

    @Override
    public String toString() {
        return "/*" + this.text + "*/";
    }
}
