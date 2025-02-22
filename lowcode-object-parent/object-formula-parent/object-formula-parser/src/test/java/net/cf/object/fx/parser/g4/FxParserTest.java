package net.cf.object.fx.parser.g4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;

public class FxParserTest {

    @Test
    public void test1() {
        // 词法分析，解析出token
        CharStream charStream = CharStreams.fromString("select 1 \ntable");
        FxLexer lexer = new FxLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        //tokenStream.fill();
        System.out.println("语法分析前，通过tokenStream.fill()得到的tokens：");
        try {
            tokenStream.getTokens();
        } catch (Exception e) {
            System.out.println("语法解析出错" + e);
        }
        for (Token token : tokenStream.getTokens()) {
            System.out.println(token);
        }

        // 基于token进行语法分析，得到语法解析树
        FxParser parser = new FxParser(tokenStream);
        FxParser.RootContext root = parser.root();
        System.out.println("rule index = " + root.getRuleIndex());
        System.out.println("start token = " + root.getStart());
        System.out.println("stop token = " + root.getStop());
        System.out.println("sql = " + root.sqlStatement());
        System.out.println("语法分析后的tokens：");
        for (Token token : tokenStream.getTokens()) {
            System.out.println(token);
        }

        // 打印语法解析树并对其进行visit遍历
        System.out.printf("root对应的语法分析树如下：\n%s\n", root.toStringTree(parser));
        FxParserBaseVisitor<Integer> visitor = new FxParserBaseVisitor<>();
        Object o = visitor.visitRoot(root);
        System.out.println("visit = " + o);

    }
}
