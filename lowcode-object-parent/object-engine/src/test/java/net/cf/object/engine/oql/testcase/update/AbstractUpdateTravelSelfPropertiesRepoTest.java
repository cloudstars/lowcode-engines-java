package net.cf.object.engine.oql.testcase.update;

import io.github.cloudstars.lowcode.commons.test.util.DataCompareTestUtils;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractUpdateTravelSelfPropertiesRepoTest extends AbstractOqlRepoTest implements UpdateTravelSelfPropertiesTest {

    protected AbstractUpdateTravelSelfPropertiesRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json"};
    }

    @Override
    public void testUpdateTravelModifierById() {
        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_MODIFIER_BY_ID);
            OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
            int effectedRows = this.engine.modify(oqlStmt);
            assert (effectedRows == 1);
        }

        this.assertUpdateRecords();
    }

    @Override
    public void testUpdateTravelModifierByIdVars() {
        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_MODIFIER_BY_ID_VARS);
            OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> paramMap = this.getEditParamMap();
            int effectedRows = this.engine.modify(oqlStmt, paramMap);
            assert (effectedRows == 1);
        }

        this.assertUpdateRecords();
    }

    /**
     * 断言更新后的结果
     */
    private void assertUpdateRecords() {
        // 重新查出来作断言
        String selectOql = "select applyId, applyName, modifier from Travel where applyId = #{applyId}";
        OqlSelectStatement selectOqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, selectOql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 1);
        Object modifier = dataList.get(0).get("modifier");
        assert (modifier != null && modifier instanceof Map);
        Map<String, Object> modifierMap = (Map<String, Object>) modifier;
        assert ("更新人姓名".equals(modifierMap.get("name")));
        assert ("更新人编号".equals(modifierMap.get("key")));
    }


    @Override
    public void testUpdateTravelWithAttachesByIdVars() {
        List<Map<String, Object>> newAttaches;
        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_WITH_ATTACHES_BY_ID_VARS);
            OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("applyId", TravelObject.RECORD1);
            Map<String, Object> attach1 = new HashMap<>();
            attach1.put("name", "附件1-1");
            attach1.put("key", "attach1-1");
            Map<String, Object> attach2 = new HashMap<>();
            attach2.put("name", "附件2-2");
            attach2.put("key", "attach2-2");
            newAttaches = Arrays.asList(attach1, attach2);
            paramMap.put("attaches", newAttaches);
            int effectedRows = this.engine.modify(oqlStmt, paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName, attaches from Travel where applyId = #{applyId}";
            OqlSelectStatement selectOqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("applyId", TravelObject.RECORD1);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt, paramMap);
            assert (dataList != null && dataList.size() == 1);
            Object attaches = dataList.get(0).get("attaches");
            assert (attaches != null && attaches instanceof List);
            List<Map<String, Object>> attachesList = (List<Map<String, Object>>) attaches;
            Assert.assertTrue(DataCompareTestUtils.isAssignableFromList(attachesList, newAttaches));
        }
    }
}
