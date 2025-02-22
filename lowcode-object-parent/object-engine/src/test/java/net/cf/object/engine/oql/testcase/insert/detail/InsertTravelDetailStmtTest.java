package net.cf.object.engine.oql.testcase.insert.detail;

import net.cf.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.TravelTripObject;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.infos.OqlInsertInfos;
import net.cf.object.engine.oql.infos.OqlInsertInfosParser;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.sql.SqlInsertCmd;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class InsertTravelDetailStmtTest extends AbstractOqlTest implements InsertTravelDetailTest {

    public InsertTravelDetailStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testInsertTravelAndTrip() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_AND_TRIPS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlInsertStatement oqlStmt = OqlStatementUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言子表的生成
        OqlInsertInfosParser insertInfosParser = new OqlInsertInfosParser(oqlStmt, new HashMap<>());
        OqlInsertInfos insertInfos = insertInfosParser.parse();
        Map<XObject, SqlInsertCmd> detailInsertCmds = insertInfos.getDetailInsertCmds();
        assert (detailInsertCmds != null && detailInsertCmds.size() == 1);
        XObject travelTripObject = this.resolver.resolve(TravelTripObject.NAME);
        SqlInsertStatement detailSqlStmt = detailInsertCmds.get(travelTripObject).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }

    @Test
    @Override
    public void testInsertMultiTravelAndTrip() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_MULTI_TRAVEL_AND_TRIPS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlInsertStatement oqlStmt = OqlStatementUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言子表的生成
        OqlInsertInfosParser insertInfosParser = new OqlInsertInfosParser(oqlStmt, new HashMap<>());
        OqlInsertInfos insertInfos = insertInfosParser.parse();
        Map<XObject, SqlInsertCmd> detailInsertCmds = insertInfos.getDetailInsertCmds();
        assert (detailInsertCmds != null && detailInsertCmds.size() == 1);
        XObject travelTripObject = this.resolver.resolve(TravelTripObject.NAME);
        SqlInsertStatement detailSqlStmt = detailInsertCmds.get(travelTripObject).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }

    @Test
    @Override
    public void testBatchInsertTravelAndTripVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_BATCH_INSERT_TRAVEL_AND_TRIPS_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlInsertStatement oqlStmt = OqlStatementUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言子表的生成
        OqlInsertInfosParser insertInfosParser = new OqlInsertInfosParser(oqlStmt, oqlInfo.paramMaps.get(0));
        OqlInsertInfos insertInfos = insertInfosParser.parse();
        Map<XObject, SqlInsertCmd> detailInsertCmds = insertInfos.getDetailInsertCmds();
        assert (detailInsertCmds != null && detailInsertCmds.size() == 1);
        XObject travelTripObject = this.resolver.resolve(TravelTripObject.NAME);
        SqlInsertStatement detailSqlStmt = detailInsertCmds.get(travelTripObject).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }


    @Test
    @Override
    public void testBatchInsertTravelAndTripExpandedVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_BATCH_INSERT_TRAVEL_AND_TRIPS_EXPAND_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlInsertStatement oqlStmt = OqlStatementUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

    }

    @Test
    @Override
    public void testBatchInsertMultiTravelAndTripVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_BATCH_INSERT_MULTI_TRAVEL_AND_TRIPS_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlInsertStatement oqlStmt = OqlStatementUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言子表的生成
        OqlInsertInfosParser insertInfosParser = new OqlInsertInfosParser(oqlStmt, oqlInfo.paramMaps.get(0));
        OqlInsertInfos insertInfos = insertInfosParser.parse();
        Map<XObject, SqlInsertCmd> detailInsertCmds = insertInfos.getDetailInsertCmds();
        assert (detailInsertCmds != null && detailInsertCmds.size() == 1);
        XObject travelTripObject = this.resolver.resolve(TravelTripObject.NAME);
        SqlInsertStatement detailSqlStmt = detailInsertCmds.get(travelTripObject).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }
}
