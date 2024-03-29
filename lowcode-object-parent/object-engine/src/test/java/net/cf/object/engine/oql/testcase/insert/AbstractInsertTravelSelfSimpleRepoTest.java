package net.cf.object.engine.oql.testcase.insert;

import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

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

    protected AbstractInsertTravelSelfSimpleRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json"};
    }

    @Override
    public void testInsertTravel() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            int effectedRows = this.engine.create(oqlStmt);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }

    @Override
    public void testInsertTravelVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("applyId", "434743DSS#FEL3232-323KLFJFDS-323FDSD");
            paramMap.put("applyName", "测试申请单的名称");
            int effectedRows = this.engine.create(oqlStmt, paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
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
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
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
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 4);
        }
    }

}
