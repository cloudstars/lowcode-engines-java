package net.cf.formula.engine.visitor;

import net.cf.formula.engine.FastFxException;
import net.cf.formula.engine.ast.FxCommentHint;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.FxNotExpr;
import net.cf.formula.engine.ast.expr.identifier.FxIdentifierExpr;
import net.cf.formula.engine.ast.expr.identifier.FxMethodInvokeExpr;
import net.cf.formula.engine.ast.expr.identifier.FxNameExpr;
import net.cf.formula.engine.ast.expr.identifier.FxPropertyExpr;
import net.cf.formula.engine.ast.expr.literal.*;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxUnaryOpExpr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 输出访问器
 *
 * @author clouds
 */
public class FxAstOutputVisitor extends FxAstVisitorAdaptor implements PrintableVisitor {

    /**
     * 输出源
     */
    protected final Appendable appender;

    /**
     * 是否大写输出
     */
    protected boolean ucase = true;


    public FxAstOutputVisitor(Appendable appender) {
        this.features |= VisitorFeature.OutputPrettyFormat.mask;
        this.appender = appender;
    }

    @Override
    public boolean visit(FxCommentHint x) {
        return super.visit(x);
    }

    @Override
    public boolean visit(FxNotExpr x) {
        this.print(this.ucase ? "NOT(" : "not(");
        this.printExpr(x.getExpr());
        this.print(')');

        return false;
    }

    @Override
    public boolean visit(FxCharExpr x) {
        this.printChars(x.getText());

        return false;
    }

    @Override
    public boolean visit(FxBooleanExpr x) {
        if (this.ucase) {
            this.printChars(x.getValue() ? "TRUE" : "FALSE");
        } else {
            this.printChars(x.getValue() ? "true" : "false");
        }

        return false;
    }


    @Override
    public boolean visit(FxIntegerExpr x) {
        Integer value = x.getValue();
        this.print(value.toString());

        return false;
    }

    @Override
    public boolean visit(FxNumberExpr x) {
        if (x.getLiteral() != null) {
            this.print(x.getLiteral());
        } else {
            this.print(x.getNumber().toString());
        }

        return false;
    }

    @Override
    public boolean visit(FxNullExpr x) {
        this.print(this.ucase ? "NULL" : "null");

        return false;
    }

    @Override
    public boolean visit(FxJsonObjectExpr x) {
        this.print('{');
        Map<String, FxExpr> items = x.getItems();
        int i = 0;
        for (Map.Entry<String, FxExpr> entry : items.entrySet()) {
            if (i++ > 0) {
                print(", ");
            }
            this.printChars(entry.getKey());
            this.print(":");
            this.printExpr(entry.getValue());
        }
        this.print('}');

        return false;
    }

    @Override
    public boolean visit(FxJsonArrayExpr x) {
        this.print('[');
        List<FxExpr> items = x.getItems();
        if (!items.isEmpty()) {
            this.printExpr(items.get(0));
            for (int i = 1; i < items.size(); i++) {
                this.print(", ");
                this.printExpr(items.get(i));
            }
        }
        this.print(']');

        return false;
    }

    @Override
    public boolean visit(FxIdentifierExpr x) {
        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(FxPropertyExpr x) {
        FxNameExpr owner = x.getOwner();
        if (owner != null) {
            if (owner instanceof FxIdentifierExpr) {
                visit((FxIdentifierExpr) owner);
            } else if (owner instanceof FxPropertyExpr) {
                visit((FxPropertyExpr) owner);
            }
            this.print('.');
        }
        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(FxUnaryOpExpr x) {
        this.print(x.getOperator().getName());
        this.print('(');
        printExpr(x.getExpr());
        this.print(')');

        return false;
    }

    @Override
    public boolean visit(FxBinaryOpExpr x) {
        outputByParenthesized(x, () -> {
            this.printExpr(x.getLeft());
            this.print(' ');
            this.print(x.getOperator().name);
            this.print(' ');
            this.printExpr(x.getRight());
        });

        return false;
    }


    @Override
    public boolean visit(FxMethodInvokeExpr x) {
        this.print(x.getMethodName());
        this.print('(');
        List<FxExpr> args = x.getArguments();
        if (args.size() > 0) {
            this.printExpr(args.get(0));
            for (int i = 1; i < args.size(); i++) {
                this.print(", ");
                this.printExpr(args.get(i));
            }
        }
        this.print(')');

        return false;
    }

    /**
     * @param x
     * @param output
     */
    private void outputByParenthesized(FxBinaryOpExpr x, ParenthesizedOutput output) {
        if (x.isParenthesized()) {
            print("(");
            output.go();
            print(")");
        } else {
            output.go();
        }
    }

    @Override
    public boolean isUpperCase() {
        return this.ucase;
    }

    @Override
    public void print(char value) {
        if (this.appender != null) {
            try {
                this.appender.append(value);
            } catch (IOException e) {
                throw new FastFxException("print error", e);
            }
        }
    }

    @Override
    public void print(String text) {
        if (this.appender != null) {
            try {
                this.appender.append(text);
            } catch (IOException e) {
                throw new FastFxException("println error", e);
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
            this.print(this.ucase ? "NULL" : "null");
        } else {
            this.print('\'');
            this.print(text);
            this.print('\'');
        }
    }

    /**
     * 打印一个表达式
     *
     * @param x
     */
    protected final void printExpr(FxExpr x) {
        Class<?> clazz = x.getClass();
        if (clazz == FxCharExpr.class) {
            this.visit((FxCharExpr) x);
        } else if (clazz == FxBooleanExpr.class) {
            this.visit((FxBooleanExpr) x);
        }else if (clazz == FxIntegerExpr.class) {
            this.visit((FxIntegerExpr) x);
        } else if (clazz == FxNumberExpr.class) {
            this.visit((FxNumberExpr) x);
        } else if (clazz == FxNullExpr.class) {
            this.visit((FxNullExpr)x);
        } else if (clazz == FxJsonObjectExpr.class) {
            this.visit((FxJsonObjectExpr) x);
        } else if (clazz == FxJsonArrayExpr.class) {
          this.visit((FxJsonArrayExpr) x);
        } else if (clazz == FxIdentifierExpr.class) {
            this.visit((FxIdentifierExpr) x);
        } else if (clazz == FxPropertyExpr.class) {
            this.visit((FxPropertyExpr) x);
        } else if (clazz == FxUnaryOpExpr.class) {
            this.visit((FxUnaryOpExpr) x);
        } else if (clazz == FxBinaryOpExpr.class) {
            this.visit((FxBinaryOpExpr) x);
        } else if (clazz == FxMethodInvokeExpr.class) {
            this.visit((FxMethodInvokeExpr) x);
        } else {
            x.accept(this);
        }
    }


}
