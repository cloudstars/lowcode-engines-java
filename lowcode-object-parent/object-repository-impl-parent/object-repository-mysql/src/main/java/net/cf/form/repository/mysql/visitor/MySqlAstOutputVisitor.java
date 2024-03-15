package net.cf.form.repository.mysql.visitor;

import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.visitor.SqlAstOutputVisitor;

import java.util.Collection;
import java.util.Map;

public class MySqlAstOutputVisitor extends SqlAstOutputVisitor {

    private final Map<String, Object> dataMap;

    public MySqlAstOutputVisitor(StringBuilder builder) {
        this(builder, null);
    }

    public MySqlAstOutputVisitor(StringBuilder builder, Map<String, Object> dataMap) {
        super(builder);
        this.dataMap = dataMap;
    }

    public MySqlAstOutputVisitor(StringBuilder builder, boolean parameterized) {
        super(builder, parameterized);
        this.dataMap = null;
    }


    @Override
    public boolean visit(SqlVariantRefExpr x) {
        String varName = x.getVarName();
        Object varValues = this.dataMap.get(varName);
        if (varValues != null && varValues instanceof Collection) {
            int i = 0;
            for (Object varValue : (Collection<?>) varValues) {
                String itemVarName = varName + "$" + i;
                if (i++ > 0) {
                    this.print(", ");
                }
                this.print(':' + itemVarName);
                this.dataMap.put(itemVarName, varValue);
            }
        } else {
            this.print(':');
            this.print(varName);
        }

        return false;
    }

}
