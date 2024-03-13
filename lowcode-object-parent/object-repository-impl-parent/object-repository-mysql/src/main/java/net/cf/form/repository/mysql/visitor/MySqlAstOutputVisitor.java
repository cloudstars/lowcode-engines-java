package net.cf.form.repository.mysql.visitor;

import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
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
        this.print(x.getVarName());
        return false;
    }
}
