package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.dataset.IDataSet;
import net.cf.commons.test.dataset.JsonDataSetLoader;
import net.cf.commons.test.dataset.MysqlDataSetOperator;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.object.XObjectTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import net.cf.object.engine.sqlbuilder.OqlStatementUtils;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfNoWhereRepoTest extends AbstractOqlRepoTest implements SelectTravelSelfNoWhereTest {

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

    protected AbstractSelectTravelSelfNoWhereRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testSelectTravelList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oqlInfo.oql);
        XObjectTestUtils.resolveObject(oqlStmt.getSelect().getFrom());
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(oqlStmt);
        List<Map<String, Object>> dataList = this.repository.selectList(sqlStmt);
        assert (dataList != null && dataList.size() == 2);
    }
}
