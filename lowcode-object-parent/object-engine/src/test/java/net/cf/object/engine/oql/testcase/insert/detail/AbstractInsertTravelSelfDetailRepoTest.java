package net.cf.object.engine.oql.testcase.insert.detail;

import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;
import net.cf.object.engine.object.TestConstants;

import java.util.*;

/**
 * 插入兴趣爱好表的测试集
 *
 * @author clouds
 */
public abstract class AbstractInsertTravelSelfDetailRepoTest extends AbstractOqlRepoTest implements InsertTravelSelfDetailTest {

    protected AbstractInsertTravelSelfDetailRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testIInsertTravelAndTripVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_AND_TRIPS_VARS);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            // 行程数据
            List<Map<String, Object>> trips = new ArrayList<>();
            Map<String, Object> trip1 = new HashMap<>();
            trip1.put("creator", TestConstants.CREATOR);
            trip1.put("modifier", TestConstants.MODIFIER);
            Map<String, Object> trip2 = new HashMap<>();
            trip2.put("creator", TestConstants.CREATOR);
            trip2.put("modifier", TestConstants.MODIFIER);
            trips.add(trip1);
            trips.add(trip2);

            // 出差主数据
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("applyId", "test----------");
            paramMap.put("applyName", "测试申请单的名称");
            paramMap.put("trips", Arrays.asList(trip1, trip2));
            int effectedRows = this.engine.create(oqlStmt, paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select *, TravelTrip from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            List<Map<String, Object>> data = this.engine.queryList(selectOqlStmt, paramMap);
            assert (data.size() == 3);
        }
    }

}
