package net.cf.form.engine.repository.mysql.data.delete;

import net.cf.form.engine.repository.RepositoryDriver;
import net.cf.form.engine.repository.mysql.data.ObjectRowWrapper;
import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.repository.oql.util.OqlUtils;
import net.cf.form.engine.repository.testcases.statement.delete.DeleteMainTest;
import net.cf.form.engine.repository.mysql.TestDataObjectsHolder;
import net.cf.form.engine.repository.mysql.data.JdbcUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class DeleteMainDataTest implements DeleteMainTest {

    @Resource
    private RepositoryDriver driver;

    @Resource
    private JdbcUtils jdbcUtils;

    @Test
    @Override
    public void testDeleteTravel() {
        OqlDeleteStatement statement = OqlUtils.parseSingleDeleteStatement(DELETE_TRAVEL_OQL);
        this.driver.delete(statement);

        checkDeleteTravelResult();
    }

    @Test
    @Override
    public void testDeleteTravelVar() {
        OqlDeleteStatement statement = OqlUtils.parseSingleDeleteStatement(DELETE_TRAVEL_VAR_OQL);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", 1);
        this.driver.delete(statement, paramMap);

        checkDeleteTravelResult();
    }

    /**
     * 检查删除后的结果
     */
    private void checkDeleteTravelResult() {
        // 初始化数据有两条，删除后只剩一条ID为2
        String sql = "select * from OQL.TRV_TB";
        List<Map<String, Object>> resultMapList = jdbcUtils.queryForList(sql, new ObjectRowWrapper(TestDataObjectsHolder.getTravelObject()));
        Assert.assertTrue(resultMapList.size() == 1);
        Map<String, Object> resultMap = resultMapList.get(0);
        Assert.assertTrue(2 == (Integer) resultMap.get("applyId"));
    }

    @Test
    @Override
    public void testDeleteTravelIn() {
        OqlDeleteStatement statement = OqlUtils.parseSingleDeleteStatement(DELETE_TRAVEL_IN_OQL);
        this.driver.delete(statement);

        checkDeleteTravelInResult();
    }

    @Test
    @Override
    public void testDeleteTravelInVar() {
        OqlDeleteStatement statement = OqlUtils.parseSingleDeleteStatement(DELETE_TRAVEL_IN_VAR_OQL);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId0", 1);
        paramMap.put("applyId1", 2);

        this.driver.delete(statement, paramMap);

        checkDeleteTravelInResult();
    }


    /**
     * 检查删除（in过滤）后的结果
     */
    private void checkDeleteTravelInResult() {
        // 初始化数据有两条，删除后没有了
        String sql = "select * from OQL.TRV_TB";
        List<Map<String, Object>> resultMapList = jdbcUtils.queryForList(sql, new ObjectRowWrapper(TestDataObjectsHolder.getTravelObject()));
        Assert.assertTrue(resultMapList.size() == 0);
    }


}
