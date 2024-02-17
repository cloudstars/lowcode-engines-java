package net.cf.form.repository.mysql.data.insert;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlCaseExpr;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.SqlListExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.literal.*;
import net.cf.form.repository.sql.ast.expr.operation.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.operation.SqlBinaryOpExprGroup;
import net.cf.form.repository.sql.ast.expr.operation.SqlNotExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.visitor.SqlAstVisitorAdaptor;

import java.util.List;

/**
 * 插入SQL语句AST访问器
 *
 * @author clouds
 */
public class InsertSqlAstVisitor extends SqlAstVisitorAdaptor {

    private final StringBuilder builder;

    protected boolean uppercase = true;

    public InsertSqlAstVisitor(StringBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(SqlInsertStatement x) {
        this.print(this.uppercase ? "INSERT INTO " : "insert into ");

        x.getTableSource().accept(this);
        this.printInsertColumns(x.getColumns());

        if (!x.getValuesList().isEmpty()) {
            this.println();
            this.print(this.uppercase ? "VALUES " : "values ");
            this.printAndAccept(x.getValuesList(), ", ");
        }

        return false;
    }

    @Override
    public boolean visit(SqlInsertStatement.ValuesClause x) {
        this.print('(');
        this.printAndAccept(x.getValues(), ", ");
        this.print(')');

        return false;
    }

    private void printInsertColumns(List<SqlExpr> columns) {
        int size = columns.size();
        if (size > 0) {
            this.print(' ');
            this.print('(');

            for (int i = 0; i < size; ++i) {
                if (i != 0) {
                    this.print(", ");
                }

                SqlExpr column = columns.get(i);
                if (column instanceof SqlIdentifierExpr) {
                    this.visit((SqlIdentifierExpr) column);
                } else {
                    this.printExpr(column);
                }
            }

            this.print(')');
        }
    }

    protected void printAndAccept(List<? extends SqlObject> nodes, String seperator) {
        for (int i = 0, l = nodes.size(); i < l; ++i) {
            if (i != 0) {
                this.print(seperator);
            }
            nodes.get(i).accept(this);
        }
    }

    protected final void printExpr(SqlExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == SqlIdentifierExpr.class) {
            this.visit((SqlIdentifierExpr) x);
        } else if (clazz == SqlPropertyExpr.class) {
            this.visit((SqlPropertyExpr) x);
        } else if (clazz == SqlAllColumnExpr.class) {
            this.print('*');
        } else if (clazz == SqlAggregateExpr.class) {
            this.visit((SqlAggregateExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            this.visit((SqlBinaryOpExpr) x);
        } else if (clazz == SqlNullExpr.class) {
            this.visit((SqlNullExpr) x);
        } else if (clazz == SqlDateTimeExpr.class) {
            this.visit((SqlDateTimeExpr) x);
        } else if (clazz == SqlDateExpr.class) {
            this.visit((SqlDateExpr) x);
        } else if (clazz == SqlTimeExpr.class) {
            this.visit((SqlTimeExpr) x);
        } else if (clazz == SqlCharExpr.class) {
            this.visit((SqlCharExpr) x);
        } else if (clazz == SqlIntegerExpr.class) {
            this.visit((SqlIntegerExpr) x);
        } else if (clazz == SqlNumberExpr.class) {
            this.visit((SqlNumberExpr) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            this.visit((SqlMethodInvokeExpr) x);
        } else if (clazz == SqlVariantRefExpr.class) {
            this.visit((SqlVariantRefExpr) x);
        } else if (clazz == SqlBinaryOpExprGroup.class) {
            this.visit((SqlBinaryOpExprGroup) x);
        } else if (clazz == SqlCaseExpr.class) {
            this.visit((SqlCaseExpr) x);
        } else if (clazz == SqlListExpr.class) {
            this.visit((SqlListExpr) x);
        } else if (clazz == SqlNotExpr.class) {
            this.visit((SqlNotExpr) x);
        } else {
            x.accept(this);
        }
    }

    @Override
    public boolean visit(SqlIdentifierExpr x) {
        this.print(x.getSimpleName());
        return false;
    }

    @Override
    public boolean visit(SqlPropertyExpr x) {
        SqlName owner = x.getOwner();
        if (owner != null) {
            if (owner instanceof SqlIdentifierExpr) {
                visit((SqlIdentifierExpr) owner);
            } else if (owner instanceof SqlPropertyExpr) {
                visit((SqlPropertyExpr) owner);
            }
            this.print('.');
        }
        this.print(x.getSimpleName());

        return false;
    }

    @Override
    public boolean visit(SqlNullExpr x) {
        this.print("null");

        return false;
    }

    @Override
    public boolean visit(SqlDateTimeExpr x) {
        this.printChars(x.getText());
        return false;
    }

    @Override
    public boolean visit(SqlDateExpr x) {
        this.printChars(x.getText());
        return false;
    }

    @Override
    public boolean visit(SqlTimeExpr x) {
        this.printChars(x.getText());
        return false;
    }

    @Override
    public boolean visit(SqlBooleanExpr x) {
        this.print(x.getValue() ? "true" : "false");

        return false;
    }

    @Override
    public boolean visit(SqlCharExpr x) {
        this.printChars(x.getText());
        return false;
    }

    @Override
    public boolean visit(SqlIntegerExpr x) {
        this.print(x.getNumber().toString());
        return false;
    }

    @Override
    public boolean visit(SqlNumberExpr x) {
        this.print(x.getNumber().toString());
        return false;
    }

    protected void printChars(String text) {
        if (text == null) {
            this.print(this.uppercase ? "NULL" : "null");
        } else {
            this.print('\'');
            int index = text.indexOf(39);
            if (index >= 0) {
                text = text.replaceAll("'", "''");
            }

            this.print(text);
            this.print('\'');
        }

    }

    protected void print(long value) {
        this.print(Long.toString(value));
    }

    protected void print(char value) {
        this.builder.append(value);
    }

    protected void print(String text) {
        this.builder.append(text);
    }

    protected void println() {
        //if (!this.isPrettyFormat()) {
        //    this.print(' ');
        //} else {
        this.print('\n');
        //   ++this.lines;
        //   this.printIndent();
        // }
    }

}
