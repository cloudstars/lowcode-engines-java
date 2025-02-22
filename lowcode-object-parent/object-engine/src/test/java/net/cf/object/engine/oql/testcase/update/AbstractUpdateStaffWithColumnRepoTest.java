package net.cf.object.engine.oql.testcase.update;

import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新出差表本表的测试集
 *
 * @author clouds
 */
public abstract class
AbstractUpdateStaffWithColumnRepoTest extends AbstractOqlRepoTest implements UpdateStaffWithColumnTest {

    protected AbstractUpdateStaffWithColumnRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Override
    public void testUpdateStaffModifierWithCreator() {
        // 更新数据
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_MODIFIER_WITH_CREATOR);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        int effectedRows = this.engine.modify(oqlStmt);
        assert (effectedRows == 1);

        // 重新查询后断言
        this.assertUpdateRecords();
    }

    @Override
    public void testUpdateStaffModifierWithCreatorVars() {
        // 更新数据
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_MODIFIER_WITH_CREATOR_VARS);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> creator = new HashMap<>();
        creator.put("name", "张三");
        creator.put("key", "zhangsan");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("creator", creator);
        paramMap.put("staffCode", "zhangsan");
        int effectedRows = this.engine.modify(oqlStmt, paramMap);
        assert (effectedRows == 1);

        // 重新查询后断言
        this.assertUpdateRecords();
    }

    /**
     * 断言更新后的结果
     */
    private void assertUpdateRecords() {
        // 重新查出来作断言
        String selectOql = "select * from Staff where staffCode = #{staffCode}";
        OqlSelectStatement selectOqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, selectOql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("staffCode", "zhangsan");
        List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 1);
        Object modifier = dataList.get(0).get("modifier");
        assert (modifier != null && modifier instanceof Map);
        Object creator = dataList.get(0).get("creator");
        assert (creator != null && creator instanceof Map);
        Map<String, Object> modifierMap = (Map<String, Object>) modifier;
        Map<String, Object> creatorMap = (Map<String, Object>) creator;
        assert (modifierMap.get("name").equals(creatorMap.get("name")));
        assert (modifierMap.get("key").equals(creatorMap.get("key")));
    }


    @Override
    public void testUpdateStaffDescrWithNameRela() {
        // 更新数据
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_DESCR_WITH_NAME_RELA);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        int effectedRows = this.engine.modify(oqlStmt);
        assert (effectedRows == 1);

        // 重新查询后断言
        this.assertUpdateDescr();
    }

    @Override
    public void testUpdateStaffDescrWithNameRelaVars() {
        // 更新数据
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_DESCR_WITH_NAME_RELA_VARS);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("staffName", "张三");
        int effectedRows = this.engine.modify(oqlStmt, paramMap);
        assert (effectedRows == 1);

        // 重新查询后断言
        this.assertUpdateDescr();
    }

    /**
     * 断言更新后的结果
     */
    private void assertUpdateDescr() {
        // 重新查出来作断言
        String selectOql = "select * from Staff where staffCode = #{staffCode}";
        OqlSelectStatement selectOqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, selectOql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("staffCode", "zhangsan");
        List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 1);
        assert ("这是张三的描述".equals(dataList.get(0).get("descr")));
    }


    @Override
    public void testUpdateStaffAgeByIdVars() {
        // 更新数据
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_AGE_BY_ID_VARS);
        OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("staffCode", "zhangsan");
        int effectedRows = this.engine.modify(oqlStmt, paramMap);
        assert (effectedRows == 1);

        // 重新查询后断言
        this.assertUpdateAge();
    }

    /**
     * 断言更新后的结果
     */
    private void assertUpdateAge() {
        // 重新查出来作断言
        String selectOql = "select age from Staff where staffCode = #{staffCode}";
        OqlSelectStatement selectOqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, selectOql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("staffCode", "zhangsan");
        List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 1);
        assert (31 == (Integer) dataList.get(0).get("age"));
    }

}
