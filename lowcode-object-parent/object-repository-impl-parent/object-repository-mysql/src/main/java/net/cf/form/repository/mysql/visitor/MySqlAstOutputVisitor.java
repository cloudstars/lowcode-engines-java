package net.cf.form.repository.mysql.visitor;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
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
    public boolean visit(SqlVariantRefExpr x) {
        this.print(':');
        String varName = x.getVarName();
        this.print(varName);

        return false;
    }

    @Override
    public boolean visit(SqlContainsOpExpr x) {
        visitContains(x.getLeft(), x.getRight());
        return false;
    }

    private void visitContains(SqlExpr left, SqlExpr right) {
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
            //todo
        }
        this.print(") = 1");
    }
}
