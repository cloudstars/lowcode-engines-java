package net.cf.object.oql.gen;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class OqlParserTest {

    @Test
    public void test0() {
        // 词法分析，解析出token
        CharStream charStream = CharStreams.fromString("select 1  table");
        OqlLexer lexer = new OqlLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        tokenStream.fill();;
        System.out.println("语法分析前，通过tokenStream.fill()得到的tokens：");
        for (Token token : tokenStream.getTokens()) {
            System.out.println(token);
        }

        // 基于token进行语法分析，得到语法解析树
        OqlParser parser = new OqlParser(tokenStream);
        OqlParser.RootContext root = parser.root();
        System.out.println("语法分析后的tokens：");
        for (Token token : tokenStream.getTokens()) {
            System.out.println(token);
        }

        // 打印语法解析树并对其进行visit遍历
        System.out.printf("root对应的语法分析树如下：\n%s\n", root.toStringTree(parser));
        OqlParserBaseVisitor<Integer> visitor = new OqlParserBaseVisitor<>();
        visitor.visitRoot(root);
    }
}
