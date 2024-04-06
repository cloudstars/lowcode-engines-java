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
            Map<String, Object> paramMap = this.getCreateParamMap();
            int effectedRows = this.engine.create(oqlStmt, paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select *, trips.* from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            List<Map<String, Object>> data = this.engine.queryList(selectOqlStmt, paramMap);
            assert (data.size() == 3);
        }
    }

}
