package net.cf.object.engine.oql.testcase.insert;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.OqlEngine;
import net.cf.object.engine.object.ObjectTestResolver;
import net.cf.object.engine.object.ObjectTestUtils;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.testcase.Travel;
import net.cf.object.engine.oql.util.OqlUtils;
import net.cf.object.engine.util.OqlStatementUtils;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
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
    private OqlEngine engine;

    @Resource
    private MySqlDataSetOperator dataSetOperator;

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
            XObject object = ObjectTestResolver.resolveObject(Travel.NAME);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(object, oqlInfo.oql);
            ObjectTestUtils.resolveObject(oqlStmt.getObjectSource());
            int effectedRows = this.engine.create(oqlStmt);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select * from Travel";
            XObject object = ObjectTestResolver.resolveObject(Travel.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
            ObjectTestUtils.resolveObject(selectOqlStmt.getSelect().getFrom());
            SqlSelectStatement selectSqlStmt = OqlStatementUtils.toSqlSelect(selectOqlStmt);
            List<Map<String, Object>> dataList = this.repository.selectList(selectSqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }

    @Override
    public void testInsertTravelVars() {
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL);
            XObject object = ObjectTestResolver.resolveObject(Travel.NAME);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(object, oqlInfo.oql);
            ObjectTestUtils.resolveObject(oqlStmt.getObjectSource());
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("applyId", "434743DSS#FEL3232-323KLFJFDS-323FDSD");
            dataMap.put("applyName", "测试申请单的名称");
            int effectedRows = this.engine.create(oqlStmt, dataMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel";
            XObject object = ObjectTestResolver.resolveObject(Travel.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
            ObjectTestUtils.resolveObject(selectOqlStmt.getSelect().getFrom());
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }
}
