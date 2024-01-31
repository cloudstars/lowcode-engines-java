package net.cf.form.engine.repository.ast.statement.select;

import net.cf.form.engine.repository.oql.parser.OqlStatementParser;
import net.cf.form.engine.repository.oql.parser.ParserException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Ignore
@RunWith(JUnit4.class)
@Deprecated
public class SelectErrorTest {

    // 语法错误测试
    private static final String SELECT_ERROR1 = "select 1 aaa, fdsa from 'fdasfdaf objectName";

    @Test
    public void testError1() {
        OqlStatementParser parser = new OqlStatementParser(SELECT_ERROR1);

        Exception exception = null;
        try {
            parser.parseStatementList();
        } catch (ParserException e) {
            exception = e;
        }

        Assert.assertTrue(exception != null && exception instanceof ParserException);
        ParserException pe = (ParserException) exception;
        pe.getMessage().startsWith("unclosed str");
    }
}
