package net.cf.object.engine.oql.testcase.update;

import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量更新员工表的测试集
 *
 * @author clouds
 */
public abstract class AbstractUpdateStaffSelfBatchRepoTest extends AbstractOqlRepoTest implements UpdateStaffSelfBatchTest {

    protected AbstractUpdateStaffSelfBatchRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Override
    public void testUpdateStaffBatchVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_STAFF_BATCH_VARS);
            OqlUpdateStatement oqlStmt = OqlUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
            assert (oqlInfo.paramMaps.size() == 2);
            int[] effectedRowsArray = this.engineNew.modifyList(oqlStmt, oqlInfo.paramMaps);
            assert (effectedRowsArray.length == 2);
            assert (oqlInfo.paramMaps.get(0).get("modifier") != null);
            assert (oqlInfo.paramMaps.get(1).get("modifier") != null);
        }

        {
            // 重新查出来作断言
            String selectOql = "select staffId, staffCode, staffName from Staff";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            List<Map<String, Object>> resultList = this.engineNew.queryList(selectOqlStmt, paramMap);
            assert (resultList != null && resultList.size() == 2);
        }
    }

}
