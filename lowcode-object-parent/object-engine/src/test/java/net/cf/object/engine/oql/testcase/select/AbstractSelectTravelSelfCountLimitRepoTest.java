package net.cf.object.engine.oql.testcase.select;

import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

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
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> data = this.engine.queryOne(stmt);
        Object count = data.get("c");
        assert (count != null && count instanceof Number && ((Number) count).intValue() == 2);
    }

    @Override
    public void testSelectCountStarTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_COUNT_STAR_TRAVEL);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> data = this.engine.queryOne(stmt);
        Object count = data.get("c");
        assert (count != null && count instanceof Number && ((Number) count).intValue() == 2);
    }

    @Override
    public void testSelectCountFieldTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_COUNT_FIELD_TRAVEL);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> data = this.engine.queryOne(stmt);
        Object count = data.get("c");
        assert (count != null && count instanceof Number && ((Number) count).intValue() == 2);
    }

    @Override
    public void testSelectTravelWithLimit() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_WITH_LIMIT);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(stmt);
        assert (dataList != null && dataList.size() == 2);
    }

    @Override
    public void testSelectTravelWithLimitOffset() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_WITH_LIMIT_OFFSET);
        OqlSelectStatement stmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(stmt);
        assert (dataList != null && dataList.size() == 2);
    }

}
