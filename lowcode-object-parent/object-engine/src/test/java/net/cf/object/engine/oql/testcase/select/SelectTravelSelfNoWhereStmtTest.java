package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.util.StringTestUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.object.XObjectTestUtils;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.util.OqlUtils;
import net.cf.object.engine.sqlbuilder.OqlStatementUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SelectTravelSelfNoWhereStmtTest extends AbstractOqlTest implements SelectTravelSelfNoWhereTest {

    public SelectTravelSelfNoWhereStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testSelectTravelList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST);
        assert (oqlInfo != null && oqlInfo.oql != null && oqlInfo.sql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oqlInfo.oql);
        assert (oqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(oqlStmt.toString(), oqlInfo.oql));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = oqlStmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && "Travel".equals(((SqlIdentifierExpr) osExpr).getName()));

        // 断言OQL能转换成一条SQL语句，并且SQL语句是符合预期的
        XObjectTestUtils.resolveObject(objectSource);
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(oqlStmt);
        assert (sqlStmt != null && StringTestUtils.equalsIgnoreWhiteSpace(sqlStmt.toString(), oqlInfo.sql));
    }
}
