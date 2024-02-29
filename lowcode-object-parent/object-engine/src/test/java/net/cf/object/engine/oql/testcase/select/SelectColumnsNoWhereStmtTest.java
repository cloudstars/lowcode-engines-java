package net.cf.object.engine.oql.testcase.select;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.object.engine.oql.ast.OqlExprObjectSource;
import net.cf.object.engine.oql.ast.OqlObjectSource;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SelectColumnsNoWhereStmtTest extends AbstractOqlTest implements ISelectColumnsNoWhereTest {

    public SelectColumnsNoWhereStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testSelectTravelList() {
        String oql = this.oqlMap.get(OQL_SELECT_TRAVEL_LIST);
        assert (oql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlSelectStatement stmt = OqlUtils.parseSingleSelectStatement(oql);
        assert (stmt != null);
        assert (oql.equals(stmt.toString()));

        // 断言解析出来的OQL的一些关键信息是符合预期的
        OqlObjectSource objectSource = stmt.getSelect().getFrom();
        assert (objectSource instanceof OqlExprObjectSource);
        SqlExpr osExpr = ((OqlExprObjectSource) objectSource).getExpr();
        assert (osExpr instanceof SqlIdentifierExpr && "Travel".equals(((SqlIdentifierExpr) osExpr).getName()));
    }
}
