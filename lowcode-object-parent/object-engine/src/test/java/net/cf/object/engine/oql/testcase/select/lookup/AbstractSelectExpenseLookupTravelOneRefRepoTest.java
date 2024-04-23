package net.cf.object.engine.oql.testcase.select.lookup;

import net.cf.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.OqlEngineNew;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectExpenseLookupTravelOneRefRepoTest
        extends AbstractOqlRepoTest
        implements SelectExpenseLookupTravelOneRefTest {

    @Resource
    private OqlEngineNew engineNew;

    protected AbstractSelectExpenseLookupTravelOneRefRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Expense.json"};
    }

    @Override
    public void testSelectTravelLookupTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LOOKUP_TRAVEL);
        List<Map<String, Object>> resultMaps = this.engineNew.queryList(oqlInfo.oql, Collections.emptyMap());
        assert (DataCompareTestUtils.equalsList(resultMaps, oqlInfo.resultMaps));
    }
}
