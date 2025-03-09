package net.cf.object.engine.oql.testcase.select.exists;

import io.github.cloudstars.lowcode.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.object.ExpenseObject;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.infos.OqlSelectInfos;
import net.cf.object.engine.oql.infos.OqlSelectInfosParser;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class SelectExistsStmtTest extends AbstractOqlTest implements SelectExistsTest {

    public SelectExistsStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testSelectExists() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_EXISTS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Assert.assertEquals(oqlInfo.oql, oqlStmt.toString());
        assert (oqlStmt != null);
        StringTestUtils.assertEqualsIgnoreWhiteSpace(oqlInfo.oql, oqlStmt.toString());
        List<OqlSelectItem> selectItems = oqlStmt.getSelect().getSelectItems();
        assert (selectItems.size() == 2);

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && ExpenseObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));
        Assert.assertEquals(OqlExistsExpr.class, oqlStmt.getSelect().getWhere().getClass());

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlSelectInfosParser parser = new OqlSelectInfosParser(oqlStmt, true);
        OqlSelectInfos selectInfos = parser.parse();
        SqlSelectStatement sqlStmt = selectInfos.getMasterSelectCmd().getStatement();
        Assert.assertNotNull (sqlStmt);
        Assert.assertEquals(oqlInfo.sql, sqlStmt.toString());
    }

}
