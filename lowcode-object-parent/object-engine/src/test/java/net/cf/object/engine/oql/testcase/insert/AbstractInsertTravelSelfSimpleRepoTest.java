package net.cf.object.engine.oql.testcase.insert;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.IDataSetOperator;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.object.engine.object.TestObjectResolver;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插入出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractInsertTravelSelfSimpleRepoTest extends AbstractOqlRepoTest implements InsertTravelSelfSimpleTest {

    @Resource
    private IDataSetOperator dataSetOperator;

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

    protected AbstractInsertTravelSelfSimpleRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testInsertTravel() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL);
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(object, oqlInfo.oql);
            int effectedRows = this.engine.create(oqlStmt);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel";
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }

    @Override
    public void testInsertTravelVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL);
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(object, oqlInfo.oql);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("applyId", "434743DSS#FEL3232-323KLFJFDS-323FDSD");
            paramMap.put("applyName", "测试申请单的名称");
            int effectedRows = this.engine.create(oqlStmt, paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel";
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }


    /**
     * 测试批量插入出差记录（带变量）
     */
    public void testBatchInsertTravelVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_VARS);
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(object, oqlInfo.oql);
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("applyId", "634743DSS#FEL3232-323KLFJFDS-323FDSD");
            paramMap1.put("applyName", "测试申请单的名称");
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("applyId", "734743DSS#FEL3232-323KLFJFDS-323FDSD");
            paramMap2.put("applyName", "测试申请单的名称");
            int[] effectedRowsArray = this.engine.createList(oqlStmt, Arrays.asList(paramMap1, paramMap2));
            assert (effectedRowsArray.length == 2);
            for (int i = 0; i < effectedRowsArray.length; i++) {
                assert (effectedRowsArray[i] == 1);
            }
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel";
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 4);
        }
    }

}
