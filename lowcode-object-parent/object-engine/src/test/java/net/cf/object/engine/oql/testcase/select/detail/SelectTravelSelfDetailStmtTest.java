package net.cf.object.engine.oql.testcase.select.detail;

import net.cf.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectExpandExpr;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import net.cf.object.engine.sqlbuilder.Oql2SqlUtils;
import net.cf.object.engine.sqlbuilder.select.SqlSelectStatementBuilder;
import net.cf.object.engine.util.OqlUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class SelectTravelSelfDetailStmtTest extends AbstractOqlTest implements SelectTravelSelfDetailTest {

    public SelectTravelSelfDetailStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testSelectTravelAndTripById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && TravelObject.NAME.equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = Oql2SqlUtils.toSqlSelect(oqlStmt, builder);
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));

        // 断言子表的生成
        List<OqlObjectExpandExpr> detailObjectExpandExprs = builder.getDetailObjectExpandExprs();
        assert (detailObjectExpandExprs.size() == 1);
        OqlSelectStatement detailOqlStmt = OqlUtils.buildDetailObjectSelectStatement(detailObjectExpandExprs.get(0));
        SqlSelectStatement detailSqlStmt = Oql2SqlUtils.toSqlSelect(detailOqlStmt);
        assert (detailSqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(detailSqlStmt.toString(), oqlInfo.detailSql));
    }

}
