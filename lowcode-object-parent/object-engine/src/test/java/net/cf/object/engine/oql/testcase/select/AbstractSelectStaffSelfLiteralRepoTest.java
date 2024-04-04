package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.Map;

public abstract class AbstractSelectStaffSelfLiteralRepoTest
        extends AbstractOqlRepoTest
        implements SelectStaffSelfLiteralTest {

    protected AbstractSelectStaffSelfLiteralRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Override
    public void testSelectSelfLiteral() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_SELF_LITERAL);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(oqlStmt);
        assert (DataCompareTestUtils.equalsMap(resultMap, oqlInfo.resultMap));
    }
}
