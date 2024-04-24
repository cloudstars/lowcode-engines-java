package net.cf.object.engine.oql.testcase.insert.batch;

import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量插入员工表的测试集
 *
 * @author clouds
 */
public abstract class AbstractInsertStaffSelfBatchRepoTest extends AbstractOqlRepoTest implements InsertStaffSelfBatchTest {

    protected AbstractInsertStaffSelfBatchRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Staff.json"};
    }

    @Override
    public void testInsertStaffBatchVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_STAFF_BATCH_VARS);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            assert (oqlInfo.paramMaps.size() == 2);
            int[] effectedRowsArray = this.engine.createList(oqlStmt, oqlInfo.paramMaps);
            assert (effectedRowsArray.length == 2);
            assert (oqlInfo.paramMaps.get(0).get("staffId") != null);
            assert (oqlInfo.paramMaps.get(1).get("staffId") != null);
        }

        {
            // 重新查出来作断言
            String selectOql = "select staffId, staffCode, staffName from Staff";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            List<Map<String, Object>> resultList = this.engineNew.queryList(selectOqlStmt, paramMap);
            assert (resultList != null && resultList.size() == 4);
        }
    }

}
