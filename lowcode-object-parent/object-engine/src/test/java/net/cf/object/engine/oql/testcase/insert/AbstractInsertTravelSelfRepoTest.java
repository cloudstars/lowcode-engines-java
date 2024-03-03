package net.cf.object.engine.oql.testcase.insert;

import net.cf.commons.test.dataset.IDataSet;
import net.cf.commons.test.dataset.JsonDataSetLoader;
import net.cf.commons.test.dataset.MysqlDataSetOperator;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.object.ObjectTestUtils;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import net.cf.object.engine.sqlbuilder.OqlStatementUtils;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 插入出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractInsertTravelSelfRepoTest extends AbstractOqlRepoTest implements InsertTravelSelfTest {

    @Resource
    private MysqlDataSetOperator dataSetOperator;

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

    protected AbstractInsertTravelSelfRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testInsertTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_TRAVEL);
        OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(oqlInfo.oql);
        ObjectTestUtils.resolveObject(oqlStmt.getObjectSource());
        SqlInsertStatement sqlStmt = OqlStatementUtils.toSqlInsert(oqlStmt);
        int effectedRows = this.repository.insert(sqlStmt);
        assert (effectedRows == 1);

        {
            // 重新查出来作断言
            String selectOql = "select * from Travel";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(selectOql);
            ObjectTestUtils.resolveObject(selectOqlStmt.getSelect().getFrom());
            SqlSelectStatement selectSqlStmt = OqlStatementUtils.toSqlSelect(selectOqlStmt);
            List<Map<String, Object>> dataList = this.repository.selectList(selectSqlStmt);
            assert (dataList != null && dataList.size() == 3);
        }
    }

}
