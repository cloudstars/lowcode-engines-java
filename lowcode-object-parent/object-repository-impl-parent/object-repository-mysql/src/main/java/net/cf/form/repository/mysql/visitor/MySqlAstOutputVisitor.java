package net.cf.form.repository.mysql.visitor;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlContainsOpExpr;
import net.cf.form.repository.sql.visitor.SqlAstOutputVisitor;

public class MySqlAstOutputVisitor extends SqlAstOutputVisitor {

    public MySqlAstOutputVisitor(StringBuilder builder) {
        super(builder);
    }

    public MySqlAstOutputVisitor(StringBuilder builder, boolean parameterized) {
        super(builder, parameterized);
    }

    @Override
    public boolean visit(SqlPropertyExpr x) {
        SqlName owner = x.getOwner();
        if (owner != null) {
            owner.accept(this);
            this.print('.');
        }

        this.print('`');
        this.print(x.getName());
        this.print('`');

        return false;
    }

    @Override
    public boolean visit(SqlIdentifierExpr x) {
        this.print('`');
        this.print(x.getName());
        this.print('`');

        return false;
    }

    @Override
    public boolean visit(SqlVariantRefExpr x) {
        this.print(':');
        String varName = x.getVarName();
        this.print(varName);

        return false;
    }

    @Override
    public boolean visit(SqlContainsOpExpr x) {
        SqlExpr left = x.getLeft();
        SqlExpr right = x.getRight();
        this.print("JSON_CONTAINS(");
        left.accept(this);
        this.print(",");
        if (right instanceof SqlCharExpr) {
            this.print("'\"");
            this.print(((SqlCharExpr) right).getValue());
            this.print("\"'");
        } else if (right instanceof SqlVariantRefExpr) {
            this.print(":");
            this.print(((SqlVariantRefExpr) right).getVarName());
        } else {
            throw new RuntimeException("不支持的contains右值表达式" + right.toString());
        }

        if (x.isNot()) {
            this.print(") = 0");
        } else {
            this.print(") = 1");
        }

        return false;
    }
}
