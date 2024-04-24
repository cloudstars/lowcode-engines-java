package net.cf.object.engine.oql.testcase.select.lookup;

import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.List;
import java.util.Map;

public abstract class AbstractSelectExpenseLookupTravelOneRefRepoTest
        extends AbstractOqlRepoTest
        implements SelectExpenseLookupTravelOneRefTest {

    protected AbstractSelectExpenseLookupTravelOneRefRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Expense.json", "dataset/Travel.json"};
    }

    @Override
    public void testSelectTravelLookupTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LOOKUP_TRAVEL);
        OqlSelectStatement stmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultMaps = this.engineNew.queryList(stmt);
        assert (DataCompareTestUtils.equalsList(resultMaps, oqlInfo.resultMaps));
    }

}
