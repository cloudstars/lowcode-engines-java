package net.clouds.lowcode.formula.ast.visitor;

import net.clouds.lowcode.formula.ast.expr.LiteralExprTest;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.parser.FxExprParser;
import net.cf.formula.engine.impl.FxAstJuelOutputVisitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JuelASTVisitorTest {

    @Test
    public void testLiteralInteger1() {
        FxExprParser parser = new FxExprParser(LiteralExprTest.LITERAL_INTEGER1);
        FxExpr expr = parser.expr();

        StringBuilder builder = new StringBuilder();
        expr.accept(new FxAstJuelOutputVisitor(builder));
        System.out.println(builder.toString());
    }
}
