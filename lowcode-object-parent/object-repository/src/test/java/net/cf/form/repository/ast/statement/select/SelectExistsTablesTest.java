package net.cf.form.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.repository.ast.statement.AbstractSqlTest;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.form.repository.sql.util.SqlStatementUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * 测试表的子查询作为where条件
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class SelectExistsTablesTest extends AbstractSqlTest {

    public SelectExistsTablesTest() {
        super("sql/select/SelectExistsTables.json");
    }

    @Test
    public void testSelectSubQueryWhere() {
        String sql = this.sqlMap.get("SELECT_SUB_QUERY_WHERE");
        assert (sql != null);

        SqlSelectStatement stmt0 = SqlStatementUtils.parseSingleSelectStatement(sql);
        SqlTableSource tableSource = stmt0.getSelect().getFrom();
        Assert.assertTrue(tableSource instanceof SqlExprTableSource);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmt0, druidStmts.get(0)));
    }

}