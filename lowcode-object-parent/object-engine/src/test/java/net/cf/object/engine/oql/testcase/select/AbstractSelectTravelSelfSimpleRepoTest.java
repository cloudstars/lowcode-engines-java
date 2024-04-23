package net.cf.object.engine.oql.testcase.select;

import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfSimpleRepoTest
        extends AbstractOqlRepoTest
        implements SelectTravelSelfSimpleTest {

    protected AbstractSelectTravelSelfSimpleRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json"};
    }

    @Override
    public void testSelectTravelOne() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_ONE);
        Map<String, Object> data = this.engineNew.queryOne(oqlInfo.oql);
        assert (data != null && TravelObject.RECORD1.equals(data.get("applyId")));
    }

    @Override
    public void testSelectTravelOneVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_ONE_VARS);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        Map<String, Object> data = this.engineNew.queryOne(oqlInfo.oql, paramMap);
        assert (data != null && TravelObject.RECORD1.equals(data.get("applyId")));
    }

    @Override
    public void testSelectTravelList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST);
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql);
        assert (dataList != null && dataList.size() == 2);
        for (Map<String, Object> data : dataList) {
            assert (data.containsKey("applyId") && data.containsKey("applyName"));
        }
    }

    @Override
    public void testSelectTravelListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_VARS);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql, paramMap);
        assert (dataList != null && dataList.size() == 1);
    }

    @Override
    public void testSelectTravelInList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST);
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql);
        assert (dataList != null && dataList.size() == 2);
        for (Map<String, Object> data : dataList) {
            assert (data.containsKey("applyId") && data.containsKey("applyName"));
        }
    }

    @Override
    public void testSelectTravelInListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST_VARS);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyIds", Arrays.asList(TravelObject.RECORD1, TravelObject.RECORD2));
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql, paramMap);
        assert (dataList != null && dataList.size() == 2);
    }

    @Override
    public void testSelectTravelLikeList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIKE_LIST);
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql);
        assert (dataList != null && dataList.size() == 2);
    }

    @Override
    public void testSelectTravelLikeListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIKE_LIST_VARS);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyName", "%测试申请单%");
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql, paramMap);
        assert (dataList != null && dataList.size() == 2);
    }
}
