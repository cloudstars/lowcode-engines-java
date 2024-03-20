package net.cf.object.engine.oql.testcase.delete;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.object.engine.object.TestObjectResolver;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 删除出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractDeleteTravelSelfSimpleRepoTest extends AbstractOqlRepoTest implements DeleteTravelSelfSimpleTest {

    @Resource
    private MySqlDataSetOperator dataSetOperator;

    private IDataSet dataSet;

    @Before
    public void setup() {
        this.dataSet = JsonDataSetLoader.loadFromClassPath(new String[]{"dataset/Travel.json"});
        this.dataSetOperator.setUp(dataSet);
    }

    @After
    public void tearDown() {
        this.dataSetOperator.tearDown(this.dataSet);
    }

    protected AbstractDeleteTravelSelfSimpleRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testDeleteTravelById() {
        {
            // 删除数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_BY_ID);
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(object, oqlInfo.oql);
            this.engine.remove(oqlStmt);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
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
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(object, oqlInfo.oql);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("applyId", TravelObject.RECORD1);
            this.engine.remove(oqlStmt, dataMap);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 0);
        }
    }
}
