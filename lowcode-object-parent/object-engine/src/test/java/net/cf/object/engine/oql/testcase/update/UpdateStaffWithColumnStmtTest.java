package net.cf.object.engine.oql.testcase.update;

import net.cf.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.infos.OqlInfosUtils;
import net.cf.object.engine.oql.infos.OqlUpdateInfos;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class UpdateStaffWithColumnStmtTest extends AbstractOqlTest implements UpdateStaffWithColumnTest {

    public UpdateStaffWithColumnStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testUpdateStaffDescrWithNameRela() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_DESCR_WITH_NAME_RELA);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlUpdateInfos updateInfos = OqlInfosUtils.parseOqlUpdateInfos(oqlStmt, oqlInfo.paramMap);
        SqlUpdateStatement sqlStmt = updateInfos.getMasterUpdateCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testUpdateStaffDescrWithNameRelaVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_DESCR_WITH_NAME_RELA_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlUpdateInfos updateInfos = OqlInfosUtils.parseOqlUpdateInfos(oqlStmt, oqlInfo.paramMap);
        SqlUpdateStatement sqlStmt = updateInfos.getMasterUpdateCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testUpdateStaffModifierWithCreator() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_MODIFIER_WITH_CREATOR);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlUpdateInfos updateInfos = OqlInfosUtils.parseOqlUpdateInfos(oqlStmt, oqlInfo.paramMap);
        SqlUpdateStatement sqlStmt = updateInfos.getMasterUpdateCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testUpdateStaffModifierWithCreatorVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_MODIFIER_WITH_CREATOR_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlUpdateInfos updateInfos = OqlInfosUtils.parseOqlUpdateInfos(oqlStmt, oqlInfo.paramMap);
        SqlUpdateStatement sqlStmt = updateInfos.getMasterUpdateCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testUpdateStaffAgeByIdVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_AGE_BY_ID_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlUpdateInfos updateInfos = OqlInfosUtils.parseOqlUpdateInfos(oqlStmt, oqlInfo.paramMap);
        SqlUpdateStatement sqlStmt = updateInfos.getMasterUpdateCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

}
