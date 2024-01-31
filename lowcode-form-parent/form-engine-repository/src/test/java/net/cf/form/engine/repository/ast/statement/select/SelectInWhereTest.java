package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlInOpExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlStatement;
import net.cf.form.engine.repository.oql.parser.OqlStatementParser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * 模糊查询测试
 *
 * @author clouds
 */
@Ignore
@RunWith(JUnit4.class)
@Deprecated
public class SelectInWhereTest {

    private static final String SELECT_IN = "select * from objectName where f1 in ('1', '2') and f2 in (1, 2)";

    @Test
    public void testSelectIn() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_IN);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_IN);
        List<SQLStatement> statements1 = parser1.parseStatementList();
        List<OqlStatement> statements2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(statements1.size() == 1);
        Assert.assertTrue(statements2.size() == 1);
        SQLStatement firstStatement1 = statements1.get(0);
        OqlStatement firstStatement2 = statements2.get(0);
        Assert.assertTrue(firstStatement1 instanceof SQLSelectStatement);
        Assert.assertTrue(firstStatement2 instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) firstStatement2;
        QqlExpr where = stmt.getSelect().getWhere().getExpr();
        Assert.assertTrue(where instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boe = (OqlBinaryOpExpr) where;
        Assert.assertTrue(boe.getOperator() == OqlBinaryOperator.And);
        Assert.assertTrue(boe.getLeft() instanceof OqlInOpExpr);
        OqlInOpExpr boeL = (OqlInOpExpr) boe.getLeft();
        Assert.assertTrue(boeL.getValues() instanceof OqlListExpr);

        Assert.assertTrue(boe.getRight() instanceof OqlInOpExpr);
        OqlInOpExpr boeR = (OqlInOpExpr) boe.getRight();
        Assert.assertTrue(boeR.getValues() instanceof OqlListExpr);
    }

}