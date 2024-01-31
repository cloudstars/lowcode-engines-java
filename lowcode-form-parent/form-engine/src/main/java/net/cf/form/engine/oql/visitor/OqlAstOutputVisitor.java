package net.cf.form.engine.oql.visitor;

import net.cf.form.engine.oql.ast.OqlCommentHint;
import net.cf.form.engine.oql.ast.OqlObject;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.identifier.*;
import net.cf.form.engine.oql.ast.expr.literal.*;
import net.cf.form.engine.oql.ast.expr.identifier.*;
import net.cf.form.engine.oql.ast.expr.literal.*;
import net.cf.form.engine.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.oql.ast.expr.operation.OqlNotExpr;
import net.cf.form.engine.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.oql.ast.statement.OqlSelect;
import net.cf.form.engine.oql.ast.statement.OqlSelectItem;
import net.cf.form.engine.oql.parser.Token;

import java.util.List;
import java.util.Map;

/**
 * 输出访问器
 *
 * @author clouds
 */
public class OqlAstOutputVisitor extends OqlAstPrintableVisitorAdaptor {


    public OqlAstOutputVisitor(Appendable appender) {
        super(appender);
    }

    @Override
    public boolean visit(OqlCommentHint x) {
        this.print("/*");
        this.print(x.getText());
        this.print("*/");

        return false;
    }

    @Override
    public boolean visit(OqlNotExpr x) {
        this.print("not(");
        this.printExpr(x.getExpr());
        this.print(')');

        return false;
    }

    @Override
    public boolean visit(OqlCharExpr x) {
        this.printChars(x.getText());

        return false;
    }

    @Override
    public boolean visit(OqlBooleanExpr x) {
        this.printChars(x.getValue() ? "true" : "false");

        return false;
    }


    @Override
    public boolean visit(OqlIntegerExpr x) {
        Integer value = x.getValue();
        this.print(value.toString());

        return false;
    }

    @Override
    public boolean visit(OqlNumberExpr x) {
        this.print(x.getNumber().toString());

        return false;
    }

    @Override
    public boolean visit(OqlNullExpr x) {
        this.print("null");

        return false;
    }

    @Override
    public boolean visit(OqlJsonObjectExpr x) {
        this.print('{');
        int i = 0;
        for (Map.Entry<String, OqlExpr> entry : x.getItems().entrySet()) {
            if (i++ > 0) {
                print(", ");
            }
            this.printJsonChar(entry.getKey());
            this.print(":");
            OqlExpr value = entry.getValue();
            if (value instanceof OqlCharExpr) {
                this.printJsonChar(((OqlCharExpr) value).getText());
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
    public boolean visit(OqlJsonArrayExpr x) {
        this.print('[');
        List<OqlExpr> items = x.getItems();
        if (!items.isEmpty()) {
            this.outputByCommaSeparated(items, (item) -> {
                this.printExpr((OqlExpr) item);
            });
        }
        this.print(']');

        return false;
    }

    @Override
    public boolean visit(OqlIdentifierExpr x) {
        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(OqlPropertyExpr x) {
        OqlNameExpr owner = x.getOwner();
        if (owner != null) {
            if (owner instanceof OqlIdentifierExpr) {
                visit((OqlIdentifierExpr) owner);
            } else if (owner instanceof OqlPropertyExpr) {
                visit((OqlPropertyExpr) owner);
            }
            this.print('.');
        }
        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(OqlVariantRefExpr x) {
        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(OqlBinaryOpExpr x) {
        this.outputByParenthesized(x, (e) -> {
            this.printExpr(x.getLeft());
            this.print(' ');
            this.print(x.getOperator().name);
            this.print(' ');
            this.printExpr(x.getRight());
        });

        return false;
    }

    @Override
    public boolean visit(OqlMethodInvokeExpr x) {
        this.printMethod(x);

        return false;
    }

    /**
     * 加小括号输出表达式
     *
     * @param x
     * @param function
     */
    protected void outputByParenthesized(OqlObject x, OqlObjectPrinterFunction function) {
        print("(");
        function.print(x);
        print(")");
    }

    @Override
    public boolean visit(OqlSelect x) {
        this.print("select ");
        this.outputByCommaSeparated(x.getSelectItems(), (selectItem) -> {
            this.visit((OqlSelectItem) selectItem);
        });
        this.print(" from ");
        this.visit((OqlExprObjectSource) x.getFrom());
        if (x.getWhere() != null) {
            this.print(" where ");
            this.printExpr(x.getWhere());
        }

        return false;
    }

    @Override
    public boolean visit(OqlSelectItem x) {
        this.printExpr(x.getExpr());
        if (x.getAlias() != null) {
            this.print(" as " + x.getAlias());
        }

        return false;
    }

    @Override
    public boolean visit(OqlAllFieldExpr x) {
        this.print(Token.STAR.name);

        return false;
    }

    @Override
    public boolean visit(OqlExprObjectSource x) {
        this.printExpr(x.getFlashback());
        if (x.getAlias() != null) {
            this.print(" as " + x.getAlias());
        }

        return false;
    }


}
