package net.cf.object.engine.oql.testcase.select.property;

import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfPropertiesRepoTest extends AbstractOqlRepoTest implements SelectTravelSelfPropertiesTest {

    protected AbstractSelectTravelSelfPropertiesRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json"};
    }

    @Override
    public void testSelectTravelCreatorListById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_CREATOR_LIST_BY_ID);
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql);
        assert (dataList != null && dataList.size() == 1);
        assert (dataList.get(0).containsKey("creator"));
        Object creator = dataList.get(0).get("creator");
        assert (creator != null && creator instanceof Map);
        assert ("张三".equals(((Map<?, ?>) creator).get("name")));
        assert ("zhangsan".equals(((Map<?, ?>) creator).get("key")));
    }

    @Override
    public void testSelectTravelSingleCreatorListById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_SINGLE_CREATOR_LIST_BY_ID);
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql);
        assert (dataList != null && dataList.size() == 1);
        Map<String, Object> resultMap = dataList.get(0);
        assert (resultMap.get("creator") instanceof Map);
        Map<String, Object> creator = (Map<String, Object>) resultMap.get("creator");
        assert ("张三".equals(creator.get("name")));
        assert ("zhangsan".equals(creator.get("key")));
    }

    @Override
    public void testSelectTravelListBySingleCreator() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_BY_SINGLE_CREATOR);
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql);
        assert (dataList != null && dataList.size() == 1);
    }

    @Override
    public void testSelectTravelListBySingleCreatorVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_BY_SINGLE_CREATOR_VARS);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("creator.name", "张三");
        paramMap.put("creator.key", "zhangsan");
        List<Map<String, Object>> dataList = this.engineNew.queryList(oqlInfo.oql, paramMap);
        assert (dataList != null && dataList.size() == 1);
    }
}
