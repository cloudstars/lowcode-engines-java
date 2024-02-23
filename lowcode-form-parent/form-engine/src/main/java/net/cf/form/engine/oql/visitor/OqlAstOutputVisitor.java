package net.cf.form.engine.oql.visitor;

import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.engine.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.oql.ast.statement.OqlSelect;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.visitor.SqlAstOutputVisitor;

import java.util.List;
import java.util.Map;

/**
 * 输出访问器
 *
 * @author clouds
 */
public class OqlAstOutputVisitor extends SqlAstOutputVisitor implements OqlAstVisitor {


    public OqlAstOutputVisitor(Appendable appender) {
        super(appender);
    }

    @Override
    public boolean visit(SqlJsonObjectExpr x) {
        this.print('{');
        int i = 0;
        for (Map.Entry<String, SqlExpr> entry : x.getItems().entrySet()) {
            if (i++ > 0) {
                print(", ");
            }
            this.printJsonChar(entry.getKey());
            this.print(":");
            SqlExpr value = entry.getValue();
            if (value instanceof SqlCharExpr) {
                this.printJsonChar(((SqlCharExpr) value).getText());
            } else {
                this.printExpr(value);
            }
        }
        this.print('}');

        return false;
    }


    private void printJsonChar(String text) {
        this.print("\"");
        this.print(text);
        this.print("\"");
    }

    @Override
    public boolean visit(SqlJsonArrayExpr x) {
        this.print('[');
        List<SqlExpr> items = x.getItems();
        if (!items.isEmpty()) {
            int i = 0;
            for (SqlExpr item : x.getItems()) {
                if (i++ > 0) {
                    this.print(",");
                }
                this.printExpr(item);
            }
        }
        this.print(']');

        return false;
    }

    @Override
    public boolean visit(OqlSelect x) {
        this.print("select ");
        int i = 0;
        for (SqlSelectItem selectItem : x.getSelectItems()) {
            if (i++ > 0) {
                this.print(",");
            }
            this.visit(selectItem);
        }

        this.print(" from ");
        this.visit((OqlExprObjectSource) x.getFrom());
        if (x.getWhere() != null) {
            this.print(" where ");
            this.printExpr(x.getWhere());
        }

        return false;
    }

    @Override
    public boolean visit(OqlExprObjectSource x) {
        this.printExpr(x.getExpr());
        if (x.getAlias() != null) {
            this.print(" as " + x.getAlias());
        }

        return false;
    }


}
