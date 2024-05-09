package net.cf.object.engine.oql.testcase.insert;

import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

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
            OqlInsertStatement oqlStmt = OqlStatementUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            int effectedRows = this.engine.create(oqlStmt);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel";
            OqlSelectStatement selectOqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }

    @Override
    public void testInsertTravelVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL_VARS);
            OqlInsertStatement oqlStmt = OqlStatementUtils.parseSingleInsertStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("applyId", "434743DSS#FEL3232-323KLFJFDS-323FDSD");
            paramMap.put("applyName", "测试申请单的名称");
            int effectedRows = this.engine.create(oqlStmt, paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel";
            OqlSelectStatement selectOqlStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }

}
