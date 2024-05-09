package net.cf.object.engine.oql.testcase.select.lookup;

import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.List;
import java.util.Map;

public abstract class AbstractSelectExpenseLookupTravelOneRefWhereRepoTest
        extends AbstractOqlRepoTest
        implements SelectExpenseLookupTravelOneRefWhereTest {

    protected AbstractSelectExpenseLookupTravelOneRefWhereRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Expense.json", "dataset/Travel.json"};
    }

    @Override
    public void testSelectTravelLookupTravelByTravelName() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LOOKUP_TRAVEL_BY_TRAVEL_NAME);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultMaps = this.engine.queryList(stmt);
        assert (DataCompareTestUtils.equalsList(resultMaps, oqlInfo.resultMaps));
    }
}
