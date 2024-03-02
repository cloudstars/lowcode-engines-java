package net.cf.object.engine.oql.testcase.insert;

import net.cf.commons.test.util.StringTestUtils;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class InsertTravelSelfStmtTest extends AbstractOqlTest implements InsertTravelSelfTest {

    public InsertTravelSelfStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Test
    @Override
    public void testInsertTravel() {
        String oql = this.oqlMap.get(OQL_INSERT_TRAVEL);
        assert (oql != null);

        // 断言解析出一条OQL语句，并且OQL转句输出OQL文本是符合预期的
        OqlInsertStatement stmt = OqlUtils.parseSingleInsertStatement(oql);
        assert (stmt != null && StringTestUtils.equalsIgnoreWhiteSpace(stmt.toString(), oql));
    }

}
