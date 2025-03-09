package net.cf.object.engine.oql.testcase.select.lookup;

import io.github.cloudstars.lowcode.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.infos.OqlInfosUtils;
import net.cf.object.engine.oql.infos.OqlSelectInfos;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.sql.SqlSelectCmd;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class SelectStaffLookupHobbyMultiRefStmtTest extends AbstractOqlTest implements SelectStaffLookupHobbyMultiRefTest {

    public SelectStaffLookupHobbyMultiRefStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testSelectStaffLookupHobby() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_STAFF_LOOKUP_HOBBY);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectInfos oqlStmt = OqlInfosUtils.parseOqlSelectInfos(stmt, true);
        SqlSelectCmd selfSelectInfo = oqlStmt.getMasterSelectCmd();
        List<SqlSelectCmd> detailSelectInfos = oqlStmt.getDetailSelectCmds();
        List<SqlSelectCmd> lookupSelectInfos = oqlStmt.getLookupSelectCmds();
        assert (selfSelectInfo != null && selfSelectInfo.getStatement() != null);
        SqlSelectStatement selfSqlStmt = selfSelectInfo.getStatement();
        assert (selfSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(selfSqlStmt.toString(), oqlInfo.sql));
        assert (CollectionUtils.isEmpty(detailSelectInfos));
        assert (lookupSelectInfos != null && lookupSelectInfos.size() == 1);
        SqlSelectStatement lookupSqlStmt = lookupSelectInfos.get(0).getStatement();
        assert (lookupSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(lookupSqlStmt.toString(), oqlInfo.lookupSql));
    }

}
