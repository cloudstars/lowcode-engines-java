package net.cf.object.engine.oql.testcase.select;

import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

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
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> data = this.engine.queryOne(stmt);
        assert (data != null && TravelObject.RECORD1.equals(data.get("applyId")));
        Object[] dataRange = (Object[]) data.get("dateRange");
        assert (dataRange.length == 2);
    }

    @Override
    public void testSelectTravelOneVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_ONE_VARS);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        Map<String, Object> data = this.engine.queryOne(stmt, paramMap);
        assert (data != null && TravelObject.RECORD1.equals(data.get("applyId")));
        Object[] dataRange = (Object[]) data.get("dateRange");
        assert (dataRange.length == 2);
    }

    @Override
    public void testSelectTravelList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(stmt);
        assert (dataList != null && dataList.size() == 2);
        for (Map<String, Object> data : dataList) {
            assert (data.containsKey("applyId") && data.containsKey("applyName"));
        }
    }

    @Override
    public void testSelectTravelListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_VARS);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        List<Map<String, Object>> dataList = this.engine.queryList(stmt, paramMap);
        assert (dataList != null && dataList.size() == 1);
    }

    @Override
    public void testSelectTravelInList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(stmt);
        assert (dataList != null && dataList.size() == 2);
        for (Map<String, Object> data : dataList) {
            assert (data.containsKey("applyId") && data.containsKey("applyName"));
        }
    }

    @Override
    public void testSelectTravelInListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST_VARS);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyIds", Arrays.asList(TravelObject.RECORD1, TravelObject.RECORD2));
        List<Map<String, Object>> dataList = this.engine.queryList(stmt, paramMap);
        assert (dataList != null && dataList.size() == 2);
    }

    @Override
    public void testSelectTravelLikeList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIKE_LIST);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(stmt);
        assert (dataList != null && dataList.size() == 2);
    }

    @Override
    public void testSelectTravelLikeListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIKE_LIST_VARS);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyName", "%测试申请单%");
        List<Map<String, Object>> dataList = this.engine.queryList(stmt, paramMap);
        assert (dataList != null && dataList.size() == 2);
    }
}
