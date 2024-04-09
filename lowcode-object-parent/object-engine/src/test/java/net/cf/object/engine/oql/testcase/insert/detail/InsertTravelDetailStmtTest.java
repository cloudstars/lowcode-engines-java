package net.cf.object.engine.oql.testcase.insert.detail;

import net.cf.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.info.OqlDetailInsertInfo;
import net.cf.object.engine.oql.info.OqlInsertInfoParser;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.sqlbuilder.Oql2SqlUtils;
import net.cf.object.engine.sqlbuilder.insert.SqlInsertStatementBuilder;
import net.cf.object.engine.util.OqlUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class InsertTravelDetailStmtTest extends AbstractOqlTest implements InsertTravelDetailTest {

    public InsertTravelDetailStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testIInsertTravelAndTripVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_AND_TRIPS_VARS);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getObjectSource();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        SqlInsertStatementBuilder builder = new SqlInsertStatementBuilder();
        SqlInsertStatement sqlStmt = Oql2SqlUtils.toSqlInsert(oqlStmt, builder);
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        Map<String, Object> paramMap = this.getCreateParamMap();
        OqlInsertInfoParser oqlInfoParser = new OqlInsertInfoParser(oqlStmt, paramMap);
        oqlInfoParser.parse();
        List<OqlDetailInsertInfo> detailInsertInfos = oqlInfoParser.getDetailInsertInfos();
        assert (detailInsertInfos != null && detailInsertInfos.size() == 1);
        OqlInsertStatement detailOqlStmt = detailInsertInfos.get(0).getStatement();
        SqlInsertStatement detailSqlStmt = Oql2SqlUtils.toSqlInsert(detailOqlStmt);
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }

}
