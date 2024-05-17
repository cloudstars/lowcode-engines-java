package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.Map;

public abstract class AbstractSelectStaffSelfMethodRepoTest
        extends AbstractOqlRepoTest
        implements SelectStaffSelfMethodTest {

    protected AbstractSelectStaffSelfMethodRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Override
    public void testSelectStaffTextMethods() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_STAFF_TEXT_METHODS);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(stmt);
        assert (DataCompareTestUtils.isAssignableFromMap(oqlInfo.resultMap, resultMap));
    }


    @Override
    public void testSelectStaffDateMethods() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_STAFF_DATE_METHODS);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(stmt);
        assert (DataCompareTestUtils.isAssignableFromMap(oqlInfo.resultMap, resultMap));
    }
}
