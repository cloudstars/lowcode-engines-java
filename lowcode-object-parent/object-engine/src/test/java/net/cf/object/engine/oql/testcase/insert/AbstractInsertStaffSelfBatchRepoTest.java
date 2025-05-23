package net.cf.object.engine.oql.testcase.insert;

import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

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
            OqlInsertStatement oqlStmt = OqlStatementUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            assert (oqlInfo.paramMaps.size() == 2);
            int[] effectedRowsArray = this.engine.createList(oqlStmt, oqlInfo.paramMaps);
            assert (effectedRowsArray.length == 2);
            // TODO 自增主键没有生成
            assert (oqlInfo.paramMaps.get(0).get("staffId") != null);
            assert (oqlInfo.paramMaps.get(1).get("staffId") != null);
        }

        {
            // 重新查出来作断言
            String selectOql = "select staffId, staffCode, staffName from Staff";
            OqlSelectStatement selectOqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, selectOql);
            Map<String, Object> paramMap = new HashMap<>();
            List<Map<String, Object>> resultList = this.engine.queryList(selectOqlStmt, paramMap);
            assert (resultList != null && resultList.size() == 4);
        }
    }

}
