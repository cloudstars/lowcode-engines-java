package net.cf.object.engine.oql.testcase.select;

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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class SelectTravelSelfSimpleStmtTest extends AbstractOqlTest implements SelectTravelSelfSimpleTest {

    public SelectTravelSelfSimpleStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testSelectTravelOne() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_ONE);
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
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testSelectTravelOneVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_ONE_VARS);
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
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testSelectTravelList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST);
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
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testSelectTravelListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_VARS);
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
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testSelectTravelInList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST);
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
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testSelectTravelInListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST_VARS);
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
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testSelectTravelLikeList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIKE_LIST);
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
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

    @Test
    @Override
    public void testSelectTravelLikeListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIKE_LIST_VARS);
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
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }

}
