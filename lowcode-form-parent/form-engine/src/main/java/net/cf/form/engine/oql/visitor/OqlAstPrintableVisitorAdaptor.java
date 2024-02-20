package net.cf.form.engine.oql.visitor;

import net.cf.form.engine.oql.FastOqlException;
import net.cf.form.engine.oql.ast.OqlObject;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.oql.ast.expr.identifier.OqlPropertyExpr;
import net.cf.form.engine.oql.ast.expr.literal.*;
import net.cf.form.engine.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.oql.ast.expr.operation.OqlNotExpr;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * AST访问适配器
 *
 * @author clouds
 */
public class OqlAstPrintableVisitorAdaptor extends OqlAstVisitorAdaptor implements OqlAstPrintableVisitor {

    /**
     * 输出源
     */
    protected final Appendable appender;

    /**
     * 是否大写输出
     */
    protected boolean upperCase = false;

    public OqlAstPrintableVisitorAdaptor(Appendable appender) {
        this.features |= OqlAstVisitorFeature.OutputPrettyFormat.mask;
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
                throw new FastOqlException("print error", e);
            }
        }
    }

    @Override
    public void print(final String text) {
        if (this.appender != null) {
            String printText = text;
            if (this.upperCase && printText != null) {
                printText = printText.toUpperCase();
            }
            try {
                this.appender.append(printText);
            } catch (IOException e) {
                throw new FastOqlException("println error", e);
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
    protected <T extends OqlObject> void outputByCommaSeparated(Collection<T> list, OqlObjectPrinterFunction output) {
        int i = 0;
        for (OqlObject item : list) {
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
    protected final void printMethod(OqlMethodInvokeExpr x) {
        this.print(x.getMethodName());
        this.print('(');
        List<OqlExpr> args = x.getArguments();
        if (args.size() > 0) {
            this.outputByCommaSeparated(args, (arg) -> {
                this.printExpr((OqlExpr) arg);
            });
        }
        this.print(')');
    }


    /**
     * 打印一个表达式
     *
     * @param x
     */
    protected final void printExpr(OqlExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == OqlCharExpr.class) {
            this.visit((OqlCharExpr) x);
        } else if (clazz == OqlBooleanExpr.class) {
            this.visit((OqlBooleanExpr) x);
        } else if (clazz == OqlIntegerExpr.class) {
            this.visit((OqlIntegerExpr) x);
        } else if (clazz == OqlNumberExpr.class) {
            this.visit((OqlNumberExpr) x);
        } else if (clazz == OqlNullExpr.class) {
            this.visit((OqlNullExpr) x);
        } else if (clazz == OqlJsonObjectExpr.class) {
            this.visit((OqlJsonObjectExpr) x);
        } else if (clazz == OqlJsonArrayExpr.class) {
            this.visit((OqlJsonArrayExpr) x);
        } else if (clazz == OqlIdentifierExpr.class) {
            this.visit((OqlIdentifierExpr) x);
        } else if (clazz == OqlPropertyExpr.class) {
            this.visit((OqlPropertyExpr) x);
        } else if (clazz == OqlNotExpr.class) {
            this.visit((OqlNotExpr) x);
        } else if (clazz == OqlBinaryOpExpr.class) {
            this.visit((OqlBinaryOpExpr) x);
        } else if (clazz == OqlMethodInvokeExpr.class) {
            this.visit((OqlMethodInvokeExpr) x);
        } else {
            x.accept(this);
        }
    }

}
