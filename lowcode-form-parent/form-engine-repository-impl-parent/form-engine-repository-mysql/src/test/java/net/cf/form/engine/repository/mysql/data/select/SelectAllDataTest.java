package net.cf.form.engine.repository.mysql.data.select;

import net.cf.commons.util.JsonUtils;
import net.cf.form.engine.repository.RepositoryDriver;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.util.OqlUtils;
import net.cf.form.engine.repository.testcases.statement.select.SelectAllTest;
import net.cf.form.engine.repository.mysql.TestApplication;
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
 * 测试 含相关表、主子表的查询全部数据
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("h2")
public class SelectAllDataTest implements SelectAllTest {

    @Resource
    private RepositoryDriver driver;

    @Test
    @Override
    public void testSelectTravelAll() {
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement(SELECT_TRAVEL_ALL_OQL);
        List<Map<String, Object>> dataList = this.driver.select(statement);
        Assert.assertTrue(dataList.size() == 2);
    }

    @Override
    public void testSelectScheduleAll() {

    }

    @Override
    public void testSelectClaimAll() {

    }

    @Test
    @Override
    public void testSelectTravelAndScheduleAll() {
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement(SELECT_TRAVEL_AND_SCHEDULE_ALL_OQL);
        List<Map<String, Object>> dataList = this.driver.select(statement);
        Assert.assertTrue(dataList.size() == 2);

        List<Map<String, Object>> expectedList = JsonUtils.loadListFromClasspath("json/selectTravelSchedulesOutput.json");
        Assert.assertTrue(JsonUtils.isAssignableFromList(dataList, expectedList));
    }

    @Override
    public void testSelectScheduleAndTravelAll() {

    }

    @Override
    public void testSelectClaimAndScheduleAll() {

    }

    @Override
    public void testSelectClaimAndStaffAll() {

    }
}
