package net.cf.object.engine.oql.testcase.select.detail;

import io.github.cloudstars.lowcode.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class SelectTravelDetailStmtTest extends AbstractOqlTest implements SelectTravelDetailTest {

    public SelectTravelDetailStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testSelectTravelAndTripIdsById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_IDS_BY_ID);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlSelectInfosParser parser = new OqlSelectInfosParser(oqlStmt, false);
        OqlSelectInfos selectInfos = parser.parse();
        SqlSelectStatement sqlStmt = selectInfos.getMasterSelectCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        /*OqlSelectInfoParser oqlInfoParser = new OqlSelectInfoParser(oqlStmt);
        oqlInfoParser.parse();
        List<OqlSelectInfo> detailOqlStmts = oqlInfoParser.getDetailObjectSelectInfos();
        assert (detailOqlStmts != null && detailOqlStmts.size() == 1);
        SqlSelectStatement detailSqlStmt = selectInfos.getDetailSelectInfos().get(0).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
        */
    }

    @Test
    @Override
    public void testSelectTravelAndTripById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_BY_ID);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlSelectInfosParser parser = new OqlSelectInfosParser(oqlStmt, false);
        OqlSelectInfos selectInfos = parser.parse();
        SqlSelectStatement sqlStmt = selectInfos.getMasterSelectCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        /*OqlSelectInfoParser oqlInfoParser = new OqlSelectInfoParser(oqlStmt);
        oqlInfoParser.parse();
        List<OqlSelectInfo> detailOqlStmts = oqlInfoParser.getDetailObjectSelectInfos();
        assert (detailOqlStmts != null && detailOqlStmts.size() == 1);
        SqlSelectStatement detailSqlStmt = selectInfos.getDetailSelectInfos().get(0).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
        */
    }

    @Test
    @Override
    public void testSelectTravelAndTripByIdVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_BY_ID_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlSelectInfosParser parser = new OqlSelectInfosParser(oqlStmt, false);
        OqlSelectInfos selectInfos = parser.parse();
        SqlSelectStatement sqlStmt = selectInfos.getMasterSelectCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        /*OqlSelectInfoParser oqlInfoParser = new OqlSelectInfoParser(oqlStmt);
        oqlInfoParser.parse();
        List<OqlSelectInfo> detailOqlStmts = oqlInfoParser.getDetailObjectSelectInfos();
        assert (detailOqlStmts != null && detailOqlStmts.size() == 1);
        SqlSelectStatement detailSqlStmt = selectInfos.getDetailSelectInfos().get(0).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    */}

    @Test
    @Override
    public void testSelectTravelAndTripList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_LIST);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlSelectInfosParser parser = new OqlSelectInfosParser(oqlStmt, false);
        OqlSelectInfos selectInfos = parser.parse();
        SqlSelectStatement sqlStmt = selectInfos.getMasterSelectCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        /*OqlSelectInfoParser oqlInfoParser = new OqlSelectInfoParser(oqlStmt, true);
        oqlInfoParser.parse();
        List<OqlSelectInfo> detailOqlStmts = oqlInfoParser.getDetailObjectSelectInfos();
        assert (detailOqlStmts != null && detailOqlStmts.size() == 1);
        SqlSelectStatement detailSqlStmt = selectInfos.getDetailSelectInfos().get(0).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
        */
    }

    @Test
    @Override
    public void testSelectTravelAndTripListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_LIST_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement oqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        OqlSelectInfosParser parser = new OqlSelectInfosParser(oqlStmt, false);
        OqlSelectInfos selectInfos = parser.parse();
        SqlSelectStatement sqlStmt = selectInfos.getMasterSelectCmd().getStatement();
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyIds", Arrays.asList(TravelObject.RECORD1, TravelObject.RECORD2));
        /*OqlSelectInfoParser oqlInfoParser = new OqlSelectInfoParser(oqlStmt, paramMap, true);
        oqlInfoParser.parse();
        List<OqlSelectInfo> detailOqlStmts = oqlInfoParser.getDetailObjectSelectInfos();
        assert (detailOqlStmts != null && detailOqlStmts.size() == 1);
        SqlSelectStatement detailSqlStmt = selectInfos.getDetailSelectInfos().get(0).getStatement();
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
        */
    }

}
