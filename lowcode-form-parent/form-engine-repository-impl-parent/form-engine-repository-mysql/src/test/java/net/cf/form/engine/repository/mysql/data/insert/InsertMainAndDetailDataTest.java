package net.cf.form.engine.repository.mysql.data.insert;

import net.cf.commons.util.JsonUtils;
import net.cf.form.engine.repository.RepositoryDriver;
import net.cf.form.engine.repository.mysql.data.ObjectRowWrapper;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.util.OqlUtils;
import net.cf.form.engine.repository.testcases.statement.insert.InsertMainAndDetailTest;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class InsertMainAndDetailDataTest implements InsertMainAndDetailTest {

    @Resource
    private RepositoryDriver driver;

    @Resource
    private JdbcUtils jdbcUtils;

    @Test
    @Override
    public void testInsertTravelsAndSchedules() {
        OqlInsertStatement statement = OqlUtils.parseSingleInsertStatement(INSERT_TRAVELS_AND_SCHEDULES_OQL);
        this.driver.insert(statement);

        this.checkInsertTravelAndSchedulesResult();
    }

    @Test
    @Override
    public void testInsertTravelsAndSchedulesVar() {
        OqlInsertStatement statement = OqlUtils.parseSingleInsertStatement(INSERT_TRAVELS_AND_SCHEDULES_VAR_OQL);
        Map<String, Object> dataMap = JsonUtils.loadMapFromClasspath("data/testInsertTravelSchedules.json");
        this.driver.insert(statement, dataMap);

        this.checkInsertTravelAndSchedulesResult();
    }

    /**
     * 校验单条插入后的结果
     */
    private void checkInsertTravelAndSchedulesResult() {
        // 插入后数据库应该多一条记录，并且ID是3（初始化数据中有两条）
        String sql = "select * from OQL.TRV_TB where APPLY_ID = 3";
        List<Map<String, Object>> resultList = jdbcUtils.queryForList(sql, new ObjectRowWrapper(TestDataObjectsHolder.getTravelObject()));
        Assert.assertTrue(resultList.size() == 1);
        Assert.assertTrue("wangwu".equals(resultList.get(0).get("userId")));

        // 插入后数据库应该多一条记录，并且ID是3（初始化数据中有两条）
        String detailSql = "select * from OQL.TRV_SCHE_TB where APPLY_ID = 3";
        List<Map<String, Object>> detailResultList = jdbcUtils.queryForList(detailSql, new ObjectRowWrapper(TestDataObjectsHolder.getTravelScheduleObject()));
        Assert.assertTrue(detailResultList.size() == 2);
    }

    @Test
    @Override
    public void testInsertMultiTravelsAndSchedules() {
        OqlInsertStatement statement = OqlUtils.parseSingleInsertStatement(INSERT_MULTI_TRAVELS_AND_SCHEDULES_OQL);
        this.driver.insert(statement);

        this.checkInsertMultiTravelAndSchedulesResult();
    }

    @Test
    @Override
    public void testInsertMultiTravelsAndSchedulesVar() {
        OqlInsertStatement statement = OqlUtils.parseSingleInsertStatement(INSERT_MULTI_TRAVELS_AND_SCHEDULES_VAR_OQL);
        Map<String, Object> dataMap = JsonUtils.loadMapFromClasspath("data/testInsertMultiTravelSchedules.json");
        this.driver.insert(statement, dataMap);

        this.checkInsertMultiTravelAndSchedulesResult();
    }

    /**
     * 校验多条插入后的结果
     */
    private void checkInsertMultiTravelAndSchedulesResult() {
        // 插入后数据库应该多2条记录，并且ID是3、4（初始化数据中有2条）
        String sql = "select * from OQL.TRV_TB where APPLY_ID > 2";
        List<Map<String, Object>> resultList = jdbcUtils.queryForList(sql, new ObjectRowWrapper(TestDataObjectsHolder.getTravelObject()));
        Assert.assertTrue(resultList.size() == 2);
        Assert.assertTrue("wangwu".equals(resultList.get(0).get("userId")));
        Assert.assertTrue("wangwu".equals(resultList.get(1).get("userId")));

        // 插入后数据库应该多5条记录，并且ID是6、7、8、9、10（初始化数据中有5条）
        String detailSql = "select * from OQL.TRV_SCHE_TB where SCHE_ID > 5";
        List<Map<String, Object>> detailResultList = jdbcUtils.queryForList(detailSql, new ObjectRowWrapper(TestDataObjectsHolder.getTravelScheduleObject()));
        Assert.assertTrue(detailResultList.size() == 5);
    }

}