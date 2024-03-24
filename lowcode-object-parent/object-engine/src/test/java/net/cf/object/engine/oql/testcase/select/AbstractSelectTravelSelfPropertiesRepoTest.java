package net.cf.object.engine.oql.testcase.select;

import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;

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
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 1);
        assert (dataList.get(0).containsKey("creator"));
        Object creator = dataList.get(0).get("creator");
        assert (creator != null && creator instanceof Map);
        assert ("张三".equals(((Map<?, ?>) creator).get("name")));
        assert ("zhangsan".equals(((Map<?, ?>) creator).get("key")));
    }

    @Override
    public void testSelectTravelExpandCreatorListById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_EXPAND_CREATOR_LIST_BY_ID);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
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
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 1);
        assert (dataList.get(0).containsKey("creator.name") && dataList.get(0).containsKey("creator.key") );
        assert ("张三".equals(((Map<?, ?>) dataList.get(0)).get("creator.name")));
        assert ("zhangsan".equals(((Map<?, ?>) dataList.get(0)).get("creator.key")));
    }

    @Override
    public void testSelectTravelListBySingleCreator() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_BY_SINGLE_CREATOR);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 1);
    }

    @Override
    public void testSelectTravelListBySingleCreatorVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_BY_SINGLE_CREATOR_VARS);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("creator.name", "张三");
        paramMap.put("creator.key", "zhangsan");
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 1);
    }
}
