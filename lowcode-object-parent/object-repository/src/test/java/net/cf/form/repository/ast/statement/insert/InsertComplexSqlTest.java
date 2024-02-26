package net.cf.form.repository.ast.statement.insert;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.repository.ast.statement.AbstractSqlTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class InsertComplexSqlTest extends AbstractSqlTest {

    public InsertComplexSqlTest() {
        super("sql/insert/InsertComplex.json");
    }

    /**
     * 测试从源表插入目标表的SQL语句
     */
    @Test
    public void testInsertAFromB() {
        String sql = this.sqlMap.get("insert-a-from-b");
        assert (sql != null);

        // 通过Druid解析，主要用于调试时对比
        SQLStatementParser parser = new SQLStatementParser(sql);
        List<SQLStatement> stmts = parser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SQLInsertStatement);
        SQLInsertStatement stmt = (SQLInsertStatement) stmts.get(0);
        Assert.assertTrue(stmt.getTableSource() instanceof SQLExprTableSource);
        Assert.assertTrue("A".equals(stmt.getTableName().getSimpleName()));
        Assert.assertTrue(stmt.getColumns().size() == 3);
        Assert.assertTrue(stmt.getValuesList().size() == 0);
        SQLSelect query = stmt.getQuery();
        Assert.assertTrue(query != null);
        SQLTableSource tableSource = query.getQueryBlock().getFrom();
        Assert.assertTrue(tableSource instanceof SQLExprTableSource);
        Assert.assertTrue("B".equals(((SQLExprTableSource) tableSource).getTableName()));
    }
}
