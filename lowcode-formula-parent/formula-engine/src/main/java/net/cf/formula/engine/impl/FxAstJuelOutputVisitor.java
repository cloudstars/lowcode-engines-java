package net.cf.formula.engine.impl;

import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.identifier.FxIdentifierExpr;
import net.cf.formula.engine.ast.expr.identifier.FxMethodInvokeExpr;
import net.cf.formula.engine.ast.expr.identifier.FxNameExpr;
import net.cf.formula.engine.ast.expr.identifier.FxPropertyExpr;
import net.cf.formula.engine.ast.expr.literal.FxCharExpr;
import net.cf.formula.engine.ast.expr.literal.FxIntegerExpr;
import net.cf.formula.engine.ast.expr.literal.FxNullExpr;
import net.cf.formula.engine.ast.expr.literal.FxNumberExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;
import net.cf.formula.engine.visitor.FxAstOutputVisitor;
import net.cf.formula.engine.visitor.ParenthesizedOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * 将 AST 转换为 JUEL 表达式
 *
 * @author clouds
 */
public class FxAstJuelOutputVisitor extends FxAstOutputVisitor {

    /**
     * 带名称的表达式列表
     */
    private List<FxNameExpr> nameExprs = new ArrayList<>();
    ;

    public FxAstJuelOutputVisitor(Appendable appender) {
        super(appender);
    }

    @Override
    public boolean visit(FxCharExpr x) {
        this.print(x.getValue().toString());

        return false;
    }

    @Override
    public boolean visit(FxIntegerExpr x) {
        this.print(x.getValue().toString());

        return false;
    }

    @Override
    public boolean visit(FxNumberExpr x) {
        this.print(x.getValue().toString());

        return false;
    }


    @Override
    public boolean visit(FxNullExpr x) {
        this.print("null");

        return false;
    }

    @Override
    public boolean visit(FxIdentifierExpr x) {
        this.nameExprs.add(x);

        print(x.getName());

        return false;
    }

    @Override
    public boolean visit(FxPropertyExpr x) {
        this.nameExprs.add(x);

        FxNameExpr owner = x.getOwner();
        if (owner instanceof FxIdentifierExpr) {
            visit((FxIdentifierExpr) owner);
        } else if (owner instanceof FxPropertyExpr) {
            visit((FxPropertyExpr) owner);
        }

        this.print('.');
        this.print(x.getName());

        return false;
    }

    @Override
    public boolean visit(FxBinaryOpExpr x) {
        outputByParenthesized(x, () -> {
            x.getLeft().accept(this);
            print(x.getOperator().name);
            x.getRight().accept(this);
        });

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
    public boolean visit(FxMethodInvokeExpr x) {
        print("fx:");
        print(x.getMethodName());
        print("(");
        List<FxExpr> args = x.getArguments();
        if (args.size() > 0) {
            for (int i = 0; i < args.size(); i++) {
                if (i > 0) {
                    print(", ");
                }
                printExpr(args.get(i));
            }
        }
        print(")");

        return false;
    }


    public List<FxNameExpr> getNameExprs() {
        return nameExprs;
    }
}
