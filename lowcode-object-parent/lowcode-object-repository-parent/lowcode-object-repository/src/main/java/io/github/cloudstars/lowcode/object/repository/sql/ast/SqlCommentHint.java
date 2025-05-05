package io.github.cloudstars.lowcode.object.repository.sql.ast;


import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

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
    public void accept(SqlAstVisitor visitor) {
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
