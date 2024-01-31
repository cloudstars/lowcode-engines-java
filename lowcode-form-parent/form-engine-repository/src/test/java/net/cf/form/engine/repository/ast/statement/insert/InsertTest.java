package net.cf.form.engine.repository.ast.statement.insert;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertInto;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlStatement;
import net.cf.form.engine.repository.oql.parser.OqlStatementParser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@Deprecated
@RunWith(JUnit4.class)
@Ignore
public class InsertTest {

    // 简单的插入语句
    private static final String INSERT_SIMPLE = "insert into tableName(f1, f2, f3) values ('a', 1, 1.3);";

    // 批量的插入语句
    private static final String INSERT_MULTI_SIMPLE = "insert into tableName(f1, f2, f3) values ('a', 1, 1.3), ('b', 2, 3.3), ('c', 3, 2.3);";

    @Test
    public void testInsertSimple() {
        SQLStatementParser p = new SQLStatementParser(INSERT_SIMPLE);
        List<SQLStatement> stmts = p.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SQLInsertStatement);

        OqlStatementParser parser = new OqlStatementParser(INSERT_SIMPLE);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlInsertStatement);

        OqlInsertStatement insertStmt = (OqlInsertStatement) statements.get(0);
        OqlInsertInto insert = insertStmt.getInsertInto();
        Assert.assertTrue(insert.getFields().size() == 3);
        Assert.assertTrue(insert.getObjectSource().getFlashback() instanceof OqlIdentifierExpr);
        Assert.assertTrue(insert.getValuesList().size() == 1);
        Assert.assertTrue(insert.getValuesList().get(0).getValues().size() == 3);
    }

    @Test
    public void testInsertMulti() {
        SQLStatementParser p = new SQLStatementParser(INSERT_MULTI_SIMPLE);
        List<SQLStatement> stmts = p.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SQLInsertStatement);

        OqlStatementParser parser = new OqlStatementParser(INSERT_MULTI_SIMPLE);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlInsertStatement);

        OqlInsertStatement insertStmt = (OqlInsertStatement) statements.get(0);
        OqlInsertInto insert = insertStmt.getInsertInto();
        Assert.assertTrue(insert.getFields().size() == 3);
        Assert.assertTrue(insert.getObjectSource().getFlashback() instanceof OqlIdentifierExpr);
        Assert.assertTrue(insert.getValuesList().size() == 3);
        Assert.assertTrue(insert.getValuesList().get(0).getValues().size() == 3);
        Assert.assertTrue(insert.getValuesList().get(1).getValues().size() == 3);
        Assert.assertTrue(insert.getValuesList().get(2).getValues().size() == 3);
    }

}
