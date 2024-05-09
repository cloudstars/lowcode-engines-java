package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.infos.OqlSelectInfos;
import net.cf.object.engine.oql.infos.OqlSelectInfosParser;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class SelectStaffColumnExprWhereStmtTest extends AbstractOqlTest implements SelectStaffColumnExprWhereTest {

    public SelectStaffColumnExprWhereStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testSelectListWithCreateEqualsOwner() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_LIST_WITH_CREATOR_EQUALS_OWNER);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlSelectInfosParser parser = new OqlSelectInfosParser(oqlStmt, false);
        OqlSelectInfos selectInfos = parser.parse();
        SqlSelectStatement sqlStmt = selectInfos.getMasterSelectCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

}
