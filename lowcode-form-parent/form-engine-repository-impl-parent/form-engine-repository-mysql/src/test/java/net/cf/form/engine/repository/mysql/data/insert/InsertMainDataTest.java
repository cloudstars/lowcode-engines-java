package net.cf.form.engine.repository.mysql.data.insert;

import net.cf.commons.util.JsonUtils;
import net.cf.form.engine.repository.RepositoryDriver;
import net.cf.form.engine.repository.mysql.data.ObjectRowWrapper;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.util.OqlUtils;
import net.cf.form.engine.repository.testcases.statement.insert.InsertMainTest;
import net.cf.form.engine.repository.mysql.TestDataObjectsHolder;
import net.cf.form.engine.repository.mysql.data.JdbcUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 插入主对象数据测试
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class InsertMainDataTest implements InsertMainTest {

    @Resource
    private RepositoryDriver driver;

    @Resource
    private JdbcUtils jdbcUtils;

    @Test
    @Override
    public void testInsertTravel() {
        OqlInsertStatement statement = OqlUtils.parseSingleInsertStatement(INSERT_TRAVEL_OQL);
        this.driver.insert(statement);

        checkInsertTravelResult();
    }

    @Test
    @Override
    public void testInsertTravelVar() {
        OqlInsertStatement statement = OqlUtils.parseSingleInsertStatement(INSERT_TRAVEL_VAR_OQL);
        Map<String, Object> dataMap = JsonUtils.loadMapFromClasspath("data/testInsertTravel.json");
        this.driver.insert(statement, dataMap);

        checkInsertTravelResult();
    }

    /**
     * 校验单条插入后的结果
     */
    private void checkInsertTravelResult() {
        // 初始化数据有两条，新插入的ID为3
        String sql = "select * from OQL.TRV_TB where APPLY_ID = 3";
        List<Map<String, Object>> resultMapList = jdbcUtils.queryForList(sql, new ObjectRowWrapper(TestDataObjectsHolder.getTravelObject()));
        Assert.assertTrue(resultMapList.size() == 1);
        Map<String, Object> resultMap = resultMapList.get(0);
        Assert.assertTrue("zhangsan".equals(resultMap.get("userId")));
    }

    @Test
    @Override
    public void testInsertMultiTravels() {
        OqlInsertStatement statement = OqlUtils.parseSingleInsertStatement(INSERT_MULTI_TRAVELS_OQL);
        this.driver.insert(statement);

        checkInsertMultiTravelResult();
    }

    @Test
    @Override
    public void testInsertMultiTravelsVar() {
        OqlInsertStatement statement = OqlUtils.parseSingleInsertStatement(INSERT_MULTI_TRAVELS_VAR_OQL);
        Map<String, Object> dataMap = JsonUtils.loadMapFromClasspath("data/testInsertMultiTravels.json");
        this.driver.insert(statement, dataMap);

        checkInsertMultiTravelResult();
    }


    /**
     * 校验多条插入后的结果
     */
    private void checkInsertMultiTravelResult() {
        // 初始化数据有两条，新插入革两条的ID从3开始
        String sql = "select * from OQL.TRV_TB where APPLY_ID > 2";
        List<Map<String, Object>> resultMapList = jdbcUtils.queryForList(sql, new ObjectRowWrapper(TestDataObjectsHolder.getTravelObject()));
        Assert.assertTrue(resultMapList.size() == 2);
        Map<String, Object> resultMap0 = resultMapList.get(0);
        Assert.assertTrue("zhangsan".equals(resultMap0.get("userId")));
        Map<String, Object> resultMap1 = resultMapList.get(1);
        Assert.assertTrue("zhangsan".equals(resultMap1.get("userId")));
    }

}