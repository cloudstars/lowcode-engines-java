package net.cf.object.engine.oql.testcase.delete.detail;

import net.cf.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.object.TravelTripObject;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.oql.cmd.OqlDeleteInfos;
import net.cf.object.engine.oql.infos.OqlDeleteInfosParser;
import net.cf.object.engine.sql.SqlDeleteCmd;
import net.cf.object.engine.util.OqlUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class DeleteTravelDetailStmtTest extends AbstractOqlTest implements DeleteTravelDetailTest {

    public DeleteTravelDetailStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testDeleteTravelAndTripById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_AND_TRIP_BY_ID);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlExprObjectSource objectSource = oqlStmt.getFrom();
        assert (objectSource != null);
        SqlExpr osExpr =  objectSource.getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlDeleteInfosParser parser = new OqlDeleteInfosParser(oqlStmt, oqlInfo.paramMap);
        OqlDeleteInfos deleteInfos = parser.parse();
        SqlDeleteStatement sqlStmt = deleteInfos.getMasterDeleteCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        Map<XObject, SqlDeleteCmd> deleteCmds = deleteInfos.getDetailDeleteCmds();
        assert (deleteCmds != null && deleteCmds.size() == 1);
        XObject detailObject = this.resolver.resolve(TravelTripObject.NAME);
        SqlDeleteStatement detailSqlStmt = deleteCmds.get(detailObject).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }

    @Test
    @Override
    public void testDeleteTravelAndTripByIdVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_AND_TRIP_BY_ID_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlExprObjectSource objectSource = oqlStmt.getFrom();
        assert (objectSource != null);
        SqlExpr osExpr =  objectSource.getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlDeleteInfosParser parser = new OqlDeleteInfosParser(oqlStmt, oqlInfo.paramMap);
        OqlDeleteInfos deleteInfos = parser.parse();
        SqlDeleteStatement sqlStmt = deleteInfos.getMasterDeleteCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        Map<XObject, SqlDeleteCmd> deleteCmds = deleteInfos.getDetailDeleteCmds();
        assert (deleteCmds != null && deleteCmds.size() == 1);
        XObject detailObject = this.resolver.resolve(TravelTripObject.NAME);
        SqlDeleteStatement detailSqlStmt = deleteCmds.get(detailObject).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }

    @Test
    @Override
    public void testDeleteTravelAndTripInIds() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_AND_TRIP_IN_IDS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlExprObjectSource objectSource = oqlStmt.getFrom();
        assert (objectSource != null);
        SqlExpr osExpr =  objectSource.getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlDeleteInfosParser parser = new OqlDeleteInfosParser(oqlStmt, oqlInfo.paramMap);
        OqlDeleteInfos deleteInfos = parser.parse();
        SqlDeleteStatement sqlStmt = deleteInfos.getMasterDeleteCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        Map<XObject, SqlDeleteCmd> deleteCmds = deleteInfos.getDetailDeleteCmds();
        assert (deleteCmds != null && deleteCmds.size() == 1);
        XObject detailObject = this.resolver.resolve(TravelTripObject.NAME);
        SqlDeleteStatement detailSqlStmt = deleteCmds.get(detailObject).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }

    @Test
    @Override
    public void testDeleteTravelAndTripInIdsVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_AND_TRIP_IN_IDS_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlExprObjectSource objectSource = oqlStmt.getFrom();
        assert (objectSource != null);
        SqlExpr osExpr =  objectSource.getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlDeleteInfosParser parser = new OqlDeleteInfosParser(oqlStmt, oqlInfo.paramMap);
        OqlDeleteInfos deleteInfos = parser.parse();
        SqlDeleteStatement sqlStmt = deleteInfos.getMasterDeleteCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        Map<XObject, SqlDeleteCmd> deleteCmds = deleteInfos.getDetailDeleteCmds();
        assert (deleteCmds != null && deleteCmds.size() == 1);
        XObject detailObject = this.resolver.resolve(TravelTripObject.NAME);
        SqlDeleteStatement detailSqlStmt = deleteCmds.get(detailObject).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }
}
