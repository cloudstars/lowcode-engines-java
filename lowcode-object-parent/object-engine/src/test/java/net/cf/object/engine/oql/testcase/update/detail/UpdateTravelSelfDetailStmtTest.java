package net.cf.object.engine.oql.testcase.update.detail;

import net.cf.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.info.OqlDetailDeleteInfo;
import net.cf.object.engine.oql.info.OqlDetailInsertInfo;
import net.cf.object.engine.oql.info.OqlDetailUpdateInfo;
import net.cf.object.engine.oql.info.OqlUpdateInfoParser;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.sqlbuilder.Oql2SqlUtils;
import net.cf.object.engine.sqlbuilder.update.SqlUpdateStatementBuilder;
import net.cf.object.engine.util.OqlUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class UpdateTravelSelfDetailStmtTest extends AbstractOqlTest implements UpdateTravelSelfDetailTest {

    public UpdateTravelSelfDetailStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testUpdateTravelAndTripByIdVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlUpdateStatement oqlStmt = OqlUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getObjectSource();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        SqlUpdateStatementBuilder builder = new SqlUpdateStatementBuilder();
        SqlUpdateStatement sqlStmt = Oql2SqlUtils.toSqlUpdate(oqlStmt, builder);
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        Map<String, Object> paramMap = this.getEditParamMap(TravelObject.RECORD1, "MOCK_TRIP_ID");
        OqlUpdateInfoParser oqlInfoParser = new OqlUpdateInfoParser(oqlStmt, paramMap);
        oqlInfoParser.parse();
        List<OqlDetailInsertInfo> detailOqlInsertStmts = oqlInfoParser.getDetailInsertInfos();
        List<OqlDetailUpdateInfo> detailOqlUpdateStmts = oqlInfoParser.getDetailUpdateInfos();
        List<OqlDetailDeleteInfo> detailOqlDeleteStmts = oqlInfoParser.getDetailDeleteInfos();
        assert (detailOqlInsertStmts != null && detailOqlInsertStmts.size() == 1);
        assert (detailOqlInsertStmts.get(0).getParamMaps().size() == 1);
        assert (detailOqlUpdateStmts != null && detailOqlUpdateStmts.size() == 1);
        assert (detailOqlUpdateStmts.get(0).getParamMaps().size() == 1);
        assert (detailOqlDeleteStmts != null && detailOqlDeleteStmts.size() == 1);
        assert (detailOqlDeleteStmts.get(0).getRemainedRecordIds().contains("MOCK_TRIP_ID"));

        SqlInsertStatement detailSqlInsertStmt = Oql2SqlUtils.toSqlInsert(detailOqlInsertStmts.get(0).getStatement());
        assert (detailSqlInsertStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlInsertStmt.toString(), oqlInfo.detailUpdateInsertSql));
        SqlUpdateStatement detailSqlUpdateStmt = Oql2SqlUtils.toSqlUpdate(detailOqlUpdateStmts.get(0).getStatement());
        assert (detailSqlUpdateStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlUpdateStmt.toString(), oqlInfo.detailUpdateUpdateSql));
        SqlDeleteStatement detailSqlDeleteStmt = Oql2SqlUtils.toSqlDelete(detailOqlDeleteStmts.get(0).getStatement());
        assert (detailSqlDeleteStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlDeleteStmt.toString(), oqlInfo.detailUpdateDeleteSql));
    }

}
