package net.cf.object.engine.oql.testcase.select;

import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;

import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfCountLimitRepoTest
        extends AbstractOqlRepoTest
        implements SelectTravelSelfCountLimitTest {

    protected AbstractSelectTravelSelfCountLimitRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json"};
    }

    @Override
    public void testSelectCountOneTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_COUNT_ONE_TRAVEL);
        Map<String, Object> data = this.engineNew.queryOne(oqlInfo.oql);
        Object count = data.get("COUNT(1)");
        assert (count != null && count instanceof Number && ((Number) count).intValue() == 2);
    }

    @Override
    public void testSelectCountStarTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_COUNT_STAR_TRAVEL);
        Map<String, Object> data = this.engineNew.queryOne(oqlInfo.oql);
        Object count = data.get("COUNT(*)");
        assert (count != null && count instanceof Number && ((Number) count).intValue() == 2);
    }

    @Override
    public void testSelectCountFieldTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_COUNT_FIELD_TRAVEL);
        Map<String, Object> data = this.engineNew.queryOne(oqlInfo.oql);
        Object count = data.get("COUNT(applyId)");
        assert (count != null && count instanceof Number && ((Number) count).intValue() == 2);
    }

    @Override
    public void testSelectTravelWithLimit() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_WITH_LIMIT);
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql);
        assert (dataList != null && dataList.size() == 2);
    }

    @Override
    public void testSelectTravelWithLimitOffset() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_WITH_LIMIT_OFFSET);
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql);
        assert (dataList != null && dataList.size() == 2);
    }

}
