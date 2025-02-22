package net.cf.object.fx.parser.ast.literal;

import net.cf.object.fx.FxExprParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LiteralParserTest {

    public final String LITERAL_CHAR0 = "\'abc\'";

    public final String LITERAL_CHAR1 = "\'123\\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\'";

    public final String LITERAL_CHAR2 = "\'abc\\'ab\\'\'";

    public static final String LITERAL_INTEGER1 = "123";

    public static final String LITERAL_INTEGER2 = "-123";


    public final String LITERAL_FLOAT1 = "123.456";

    public final String LITERAL_FLOAT5 = "-123.456";

    public final String LITERAL_FLOAT2 = ".123";

    public final String LITERAL_FLOAT3 = "0.123";

    public final String LITERAL_FLOAT4 = "1.";

    public final String LITERAL_TRUE = "true";

    public final String LITERAL_FALSE = "false";

    public final String LITERAL_NULL = "null";


    @Test
    public void testParseInt() {
        FxExprParser p = new FxExprParser(LITERAL_CHAR0);
        p.p();
    }
}
