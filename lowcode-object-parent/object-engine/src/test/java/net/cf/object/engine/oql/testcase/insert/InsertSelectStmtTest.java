package net.cf.object.engine.oql.testcase.insert;

import io.github.cloudstars.lowcode.commons.test.util.StringTestUtils;
import net.cf.object.engine.oql.ast.OqlInsertSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class InsertSelectStmtTest extends AbstractOqlTest implements InsertSelectTest {

    public InsertSelectStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void InsertHobbySelectFromHobby() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_SELECT_FROM_HOBBY);
        assert (oqlInfo != null && oqlInfo.oql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlInsertSelectStatement oqlStmt = OqlStatementUtils.parseSingleInsertSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));
    }

    @Test
    @Override
    public void InsertTravelTripSelectFromTravelTripLiteral() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_TRIP_SELECT_FROM_TRAVEL_TRIP_LITERAL);
        assert (oqlInfo != null && oqlInfo.oql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlInsertSelectStatement oqlStmt = OqlStatementUtils.parseSingleInsertSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));
    }

    @Test
    @Override
    public void InsertTravelTripSelectFromTravelTripFields() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_TRIP_SELECT_FROM_TRAVEL_TRIP_FIELDS);
        assert (oqlInfo != null && oqlInfo.oql != null);

        // 断言解析出一条OQL语句，并且OQL语句输出OQL文本是符合预期的
        OqlInsertSelectStatement oqlStmt = OqlStatementUtils.parseSingleInsertSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));
    }
}
