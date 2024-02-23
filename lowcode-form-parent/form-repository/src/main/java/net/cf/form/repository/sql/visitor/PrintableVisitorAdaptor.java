package net.cf.form.repository.sql.visitor;

import net.cf.form.repository.sql.FastSqlException;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.ast.expr.literal.*;
import net.cf.form.repository.sql.ast.expr.operation.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.operation.SqlNotExpr;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * AST访问适配器
 *
 * @author clouds
 */
public class PrintableVisitorAdaptor extends SqlAstVisitorAdaptor implements PrintableVisitor {

    /**
     * 输出源
     */
    protected final Appendable appender;

    /**
     * 是否大写输出
     */
    protected boolean upperCase = false;

    public PrintableVisitorAdaptor(Appendable appender) {
        this.features |= VisitorFeature.OUTPUT_PRETTY_FORMAT.mask;
        this.appender = appender;
    }

    @Override
    public boolean isUpperCase() {
        return this.upperCase;
    }

    @Override
    public void print(char value) {
        if (this.appender != null) {
            try {
                this.appender.append(value);
            } catch (IOException e) {
                throw new FastSqlException("print error", e);
            }
        }
    }

    @Override
    public void print(String text) {
        String tText = text;
        if (this.appender != null) {
            if (this.upperCase && tText != null) {
                tText = tText.toUpperCase();
            }
            try {
                this.appender.append(tText);
            } catch (IOException e) {
                throw new FastSqlException("println error", e);
            }
        }
    }


    /**
     * 打印字符串
     *
     * @param text
     */
    protected void printChars(String text) {
        if (text == null) {
            this.print("null");
        } else {
            this.print('\'');
            this.print(text);
            this.print('\'');
        }
    }

    /**
     * 以列表的形式（逗号分隔）输出
     *
     * @param list
     * @param output
     */
    protected <T extends SqlObject> void outputByCommaSeparated(Collection<T> list, SqlObjectPrinterFunction output) {
        int i = 0;
        for (SqlObject item : list) {
            if (i++ > 0) {
                print(", ");
            }
            output.print(item);
        }
    }


    /**
     * 打印一个方法调用
     *
     * @param x
     */
    protected final void printMethod(SqlMethodInvokeExpr x) {
        this.print(x.getMethodName());
        this.print('(');
        List<SqlExpr> args = x.getArguments();
        if (args.size() > 0) {
            this.outputByCommaSeparated(args, (arg) -> {
                this.printExpr((SqlExpr) arg);
            });
        }
        this.print(')');
    }


    /**
     * 打印一个表达式
     *
     * @param x
     */
    protected void printExpr(SqlExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == SqlCharExpr.class) {
            this.visit((SqlCharExpr) x);
        } else if (clazz == SqlBooleanExpr.class) {
            this.visit((SqlBooleanExpr) x);
        } else if (clazz == SqlIntegerExpr.class) {
            this.visit((SqlIntegerExpr) x);
        } else if (clazz == SqlNumberExpr.class) {
            this.visit((SqlNumberExpr) x);
        } else if (clazz == SqlNullExpr.class) {
            this.visit((SqlNullExpr) x);
        } else if (clazz == SqlIdentifierExpr.class) {
            this.visit((SqlIdentifierExpr) x);
        } else if (clazz == SqlPropertyExpr.class) {
            this.visit((SqlPropertyExpr) x);
        } else if (clazz == SqlNotExpr.class) {
            this.visit((SqlNotExpr) x);
        } else if (clazz == SqlBinaryOpExpr.class) {
            this.visit((SqlBinaryOpExpr) x);
        } else if (clazz == SqlMethodInvokeExpr.class) {
            this.visit((SqlMethodInvokeExpr) x);
        } else {
            x.accept(this);
        }
    }

}
