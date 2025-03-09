package net.cf.object.engine.oql.testcase.update;

import io.github.cloudstars.lowcode.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;
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
public class UpdateTravelSelfPropertiesStmtTest extends AbstractOqlTest implements UpdateTravelSelfPropertiesTest {

    public UpdateTravelSelfPropertiesStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testUpdateTravelModifierById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_MODIFIER_BY_ID);
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
    public void testUpdateTravelModifierByIdVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_MODIFIER_BY_ID_VARS);
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
    public void testUpdateTravelWithAttachesByIdVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_WITH_ATTACHES_BY_ID_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getObjectSource();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlUpdateInfos updateInfos = OqlInfosUtils.parseOqlUpdateInfos(oqlStmt, oqlInfo.paramMap);
        SqlUpdateStatement sqlStmt = updateInfos.getMasterUpdateCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

}
