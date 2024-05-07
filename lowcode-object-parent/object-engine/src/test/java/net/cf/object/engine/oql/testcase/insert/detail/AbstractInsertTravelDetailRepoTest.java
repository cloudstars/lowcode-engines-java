package net.cf.object.engine.oql.testcase.insert.detail;

import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插入兴趣爱好表的测试集
 *
 * @author clouds
 */
public abstract class AbstractInsertTravelDetailRepoTest extends AbstractOqlRepoTest implements InsertTravelDetailTest {

    protected AbstractInsertTravelDetailRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testInsertTravelAndTrip() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_AND_TRIPS);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            int effectedRows = this.engineNew.create(oqlStmt);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select *, trips.* from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            List<Map<String, Object>> data = this.engineNew.queryList(selectOqlStmt, paramMap);
            assert (data.size() == 3);
            assert (((List) data.get(2).get("trips")).size() == 2);
        }
    }

    @Override
    public void testInsertMultiTravelAndTrip() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_MULTI_TRAVEL_AND_TRIPS);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            int effectedRows = this.engineNew.create(oqlStmt);
            assert (effectedRows == 2);
        }

        {
            // 重新查出来作断言
            String selectOql = "select *, trips.* from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            List<Map<String, Object>> data = this.engineNew.queryList(selectOqlStmt, paramMap);
            assert (data.size() == 4);
            assert (((List) data.get(2).get("trips")).size() == 2);
            assert (((List) data.get(3).get("trips")).size() == 3);
        }
    }

    @Override
    public void testBatchInsertTravelAndTripVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_BATCH_INSERT_TRAVEL_AND_TRIPS_VARS);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            int[] effectedRowsArray = this.engineNew.createList(oqlStmt, oqlInfo.paramMaps);
            assert (effectedRowsArray.length == 2);
        }

        {
            // 重新查出来作断言
            String selectOql = "select *, trips.* from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            List<Map<String, Object>> data = this.engineNew.queryList(selectOqlStmt, paramMap);
            assert (data.size() == 4);
            assert (((List) data.get(2).get("trips")).size() == 2);
            assert (((List) data.get(3).get("trips")).size() == 3);
        }
    }

    public void testBatchInsertMultiTravelAndTripVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_BATCH_INSERT_MULTI_TRAVEL_AND_TRIPS_VARS);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            int[] effectedRowsArray = this.engineNew.createList(oqlStmt, oqlInfo.paramMaps);
            assert (effectedRowsArray.length == 2);
        }

        {
            // 重新查出来作断言
            String selectOql = "select *, trips.* from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            List<Map<String, Object>> data = this.engineNew.queryList(selectOqlStmt, paramMap);
            assert (data.size() == 6);
            for (int i = 2; i < 6; i++) {
                Map<String, Object> record = data.get(i);
                if ("UNIT_TEST001".equals(record.get("applyId"))) {
                    assert (((List) record.get("trips")).size() == 2);
                } else if ("UNIT_TEST002".equals(record.get("applyId"))) {
                    assert (((List) record.get("trips")).size() == 3);
                } else if ("UNIT_TEST003".equals(record.get("applyId"))) {
                    assert (((List) record.get("trips")).size() == 1);
                } else if ("UNIT_TEST004".equals(record.get("applyId"))) {
                    assert (((List) record.get("trips")).size() == 2);
                }
            }
        }
    }

}