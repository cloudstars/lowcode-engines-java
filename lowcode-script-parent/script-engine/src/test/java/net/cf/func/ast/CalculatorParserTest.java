package net.cf.func.ast;

import net.cf.commons.test.util.FileTestUtils;
import net.cf.func.ast.lexer.CalculatorLexer;
import net.cf.func.ast.parser.CalculatorParser;
import net.cf.func.ast.visitor.CalculatorBaseVisitor;
import net.cf.func.ast.visitor.CalculatorVisitorImpl;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculatorParserTest {

    @Test
    public void test1() {
        String func1 = FileTestUtils.loadTextFromClasspath("func/func1.cfjs");
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(func1));
        TokenStream tokenStream = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokenStream);
        parser.setBuildParseTree(true);
        ParseTree root = parser.program();

        CalculatorBaseVisitor<String> visitor = new CalculatorVisitorImpl();
        String elSrc = root.accept(visitor);
        assert ("转码之后的可被某个EL引擎执行的源代码".equals(elSrc));
    }

    @Test
    public void test2() {
        String func1 = FileTestUtils.loadTextFromClasspath("func/func1.cfjs");
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(func1));
        TokenStream tokenStream = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokenStream);
        CalculatorBaseVisitor<String> visitor = new CalculatorVisitorImpl();
        parser.removeErrorListeners();;
        parser.addErrorListener(new TestErrorHandler());
        ParseTree root = parser.program();
        root.accept(visitor);
        int num = parser.getNumberOfSyntaxErrors();
        assert (num > 0);
    }
}
