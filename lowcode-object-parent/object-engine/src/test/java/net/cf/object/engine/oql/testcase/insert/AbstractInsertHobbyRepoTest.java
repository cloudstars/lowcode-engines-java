package net.cf.object.engine.oql.testcase.insert;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.object.engine.OqlEngine;
import net.cf.object.engine.object.ObjectTestUtils;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 插入兴趣爱好表的测试集
 *
 * @author clouds
 */
public abstract class AbstractInsertHobbyRepoTest extends AbstractOqlRepoTest implements InsertHobbyTest {

    @Resource
    private OqlEngine engine;

    @Resource
    private MySqlDataSetOperator dataSetOperator;

    private IDataSet dataSet;

    @Before
    public void setup() {
        this.dataSet = JsonDataSetLoader.loadFromClassPath(new String[]{"dataset/Hobby.json"});
        this.dataSetOperator.setUp(dataSet);
    }

    @After
    public void tearDown() {
        this.dataSetOperator.tearDown(this.dataSet);
    }

    protected AbstractInsertHobbyRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testInsertHobby() {
        String recordId;
        {
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_INSERT_HOBBY);
            OqlInsertStatement oqlStmt = OqlUtils.parseSingleInsertStatement(oqlInfo.oql);
            ObjectTestUtils.resolveObject(oqlStmt.getObjectSource());
            Map<String, Object> dataMap = new HashMap<>();
            recordId = this.engine.create(oqlStmt, dataMap);
            assert (recordId != null);
        }

        {
            // 重新查出来作断言
            String selectOql = "select recordId, name, descr from Hobby where recordId = #{recordId}";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(selectOql);
            ObjectTestUtils.resolveObject(selectOqlStmt.getSelect().getFrom());
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("recordId", recordId);
            Map<String, Object> data = this.engine.queryOne(selectOqlStmt, paramMap);
            assert (data != null && data.get("recordId").equals(recordId));
        }
    }

}
