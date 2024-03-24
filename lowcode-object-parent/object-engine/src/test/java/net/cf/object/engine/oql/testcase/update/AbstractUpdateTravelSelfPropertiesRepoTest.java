package net.cf.object.engine.oql.testcase.update;

import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;

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

    }

    @Override
    public void testUpdateTravelModifierByIdVars() {
        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_MODIFIER_BY_ID_VARS);
            XObject object = this.resolver.resolve(TravelObject.NAME);
            OqlUpdateStatement oqlStmt = OqlUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("applyId", TravelObject.RECORD1);
            Map<String, Object> modifier = new HashMap<>();
            modifier.put("name", "更新人姓名");
            modifier.put("key", "更新人编号");
            paramMap.put("modifier", modifier);
            int effectedRows = this.engine.modify(oqlStmt, paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName, modifier from Travel where applyId = #{applyId}";
            XObject object = this.resolver.resolve(TravelObject.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
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
    }

    @Override
    public void testUpdateTravelExpandModifierById() {

    }

    @Override
    public void testUpdateTravelExpandModifierByIdVars() {

    }

    @Override
    public void testUpdateTravelSingleModifierById() {

    }

    @Override
    public void testUpdateTravelSingleModifierByIdVars() {

    }
}
