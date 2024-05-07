package net.cf.object.engine.oql.testcase.insert;

import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插入出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractInsertTravelSelfPropertiesRepoTest extends AbstractOqlRepoTest implements InsertTravelSelfPropertiesTest {

    protected AbstractInsertTravelSelfPropertiesRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json"};
    }

    @Override
    public void testInsertTravelWithCreator() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_WITH_CREATOR);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            int effectedRows = this.engineNew.create(oqlStmt);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName, creator from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engineNew.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }

    @Override
    public void testInsertTravelWithCreatorVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_WITH_CREATOR_VARS);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("applyId", TravelObject.RECORD1);
            dataMap.put("applyName", "测试申请单的名称");
            Map<String, Object> creator = new HashMap<>();
            creator.put("name", "张三");
            creator.put("key", "zhangsan");
            dataMap.put("creator", creator);
            int effectedRows = this.engineNew.create(oqlStmt, dataMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName, creator from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engineNew.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }

    @Override
    public void testInsertTravelWithAttachesVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_WITH_ATTACHES_VARS);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            int effectedRows = this.engineNew.create(oqlStmt, oqlInfo.paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName, creator from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engineNew.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }
}
