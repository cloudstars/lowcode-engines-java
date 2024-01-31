package net.cf.form.engine.repository.mysql.data.update;

import net.cf.commons.util.JsonUtils;
import net.cf.form.engine.repository.RepositoryDriver;
import net.cf.form.engine.repository.mysql.data.ObjectRowWrapper;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.repository.oql.util.OqlUtils;
import net.cf.form.engine.repository.testcases.statement.update.UpdateMainTest;
import net.cf.form.engine.repository.testcases.statement.util.DateUtils;
import net.cf.form.engine.repository.mysql.TestDataObjectsHolder;
import net.cf.form.engine.repository.mysql.data.JdbcUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 更新主对象数据测试
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class UpdateMainDataTest implements UpdateMainTest {

    @Resource
    private RepositoryDriver driver;

    @Resource
    private JdbcUtils jdbcUtils;

    @Test
    @Override
    public void testUpdateTravel() {
        OqlUpdateStatement statement = OqlUtils.parseSingleUpdateStatement(UPDATE_TRAVEL_OQL);
        this.driver.update(statement);
        this.checkUpdateTravelResult();
    }

    @Test
    @Override
    public void testUpdateTravelVar() {
        OqlUpdateStatement statement = OqlUtils.parseSingleUpdateStatement(UPDATE_TRAVEL_VAR_OQL);
        Map<String, Object> paramMap = JsonUtils.loadMapFromClasspath("data/testUpdateTravel.json");
        this.driver.update(statement, paramMap);
        this.checkUpdateTravelResult();
    }

    private void checkUpdateTravelResult() {
        // 更新后查询数据数被更新的记录
        String sql = "select * from OQL.TRV_TB where APPLY_ID = 1";
        List<Map<String, Object>> resultList = jdbcUtils.queryForList(sql, new ObjectRowWrapper(TestDataObjectsHolder.getTravelObject()));
        Assert.assertTrue(resultList.size() == 1);
        Map<String, Object> resultMap = resultList.get(0);
        Assert.assertTrue("zhangsan".equals(resultMap.get("userId")));
        Object startDateValue = resultMap.get("startDate");
        Assert.assertTrue(startDateValue instanceof Date);
        Assert.assertTrue("2023-06-08".equals(DateUtils.formatDate((Date) startDateValue)));
        Object endDateValue = resultMap.get("endDate");
        Assert.assertTrue(endDateValue instanceof Date);
        Assert.assertTrue("2023-06-11".equals(DateUtils.formatDate((Date) endDateValue)));
    }
}
