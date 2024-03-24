package net.cf.object.engine.oql.testcase.delete;

import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 删除出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractDeleteTravelSelfSimpleRepoTest extends AbstractOqlRepoTest implements DeleteTravelSelfSimpleTest {

    protected AbstractDeleteTravelSelfSimpleRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json"};
    }

    @Override
    public void testDeleteTravelById() {
        {
            // 删除数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_BY_ID);
            OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
            this.engine.remove(oqlStmt);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 0);
        }
    }

    @Test
    @Override
    public void testDeleteTravelByIdVars() {
        {
            // 删除数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_BY_ID_VARS);
            OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("applyId", TravelObject.RECORD1);
            this.engine.remove(oqlStmt, dataMap);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 0);
        }
    }
}
